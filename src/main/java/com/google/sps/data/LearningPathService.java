package com.google.sps.data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.sps.html.LearningPathSummary;

import java.util.List;
import java.util.stream.Collectors;

public class LearningPathService {

	private static final String LEARNING_PATH = "LearningPath";
	private static final String LEARNING_SECTION = "LearningSection";
	private static final String LEARNING_ITEM = "LearningItem";

	private final ItemFeedbackService itemFeedbackService = new ItemFeedbackService();
	private final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	public LearningPathService() {

	}

	public List<LearningPathSummary> listLearningPaths() {
		Query query = new Query(LEARNING_PATH).addSort("name");

		List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());

		return entities.stream().map(e -> new LearningPathSummary(e.getKey().getId(), (String) e.getProperty("name")))
				.collect(Collectors.toList());
	}

	/**
	 * Create or update a learning path, and all included sections+items
	 *
	 * @param path
	 */
	public void store(LearningPath path) {

		Entity task = new Entity(LEARNING_PATH, path.getId());
		task.setProperty("name", path.getName());
		Key taskKey = datastore.put(task);

		List<LearningSection> existing = loadSections(path.getId());

		// TODO optimize this to only delete no-longer present items
		for (LearningSection ex : existing) {
			datastore.delete(KeyFactory.createKey(LEARNING_SECTION, ex.getId()));
		}

		for (LearningSection section : path.getSections()) {
			storeSection(path, section);
		}
	}

	private void storeSection(LearningPath path, LearningSection section) {
		Entity task = new Entity(LEARNING_SECTION, section.getId());
		task.setProperty("learningPath", path.getId());
		task.setProperty("name", section.getName());
		task.setProperty("sequence", section.getSequence());
		Key taskKey = datastore.put(task);
	}

	public LearningPath load(long id) throws EntityNotFoundException {
		Entity path = datastore.get(KeyFactory.createKey(LEARNING_PATH, id));
		String name = (String) path.getProperty("name");

		List<LearningSection> sections = loadSections(id);

		LearningPath result = new LearningPath(path.getKey().getId(), name, "description");
		result.getSections().addAll(sections);
		return result;
	}

	public LearningPath updateRating(long pathId, ItemFeedback feedback) throws EntityNotFoundException {
		
		LearningPath path = load(pathId);
		ItemFeedback existing_feedback = itemFeedbackService.getOne(feedback.getUserId(), pathId);

		double rating_diff = feedback.getRating();
		int total_rating_diff = 1;

		if(existing_feedback != null) {
			/*	
				is the user already gave feedback, no need ot increase the 
				rating total, and the rating diff is the current rating value
				minus the existing feedback value
			*/
			total_rating_diff = 0;
			rating_diff = feedback.getRating() - existing_feedback.getRating();

		}

		// new average rating = (current_average * total ratings) + the current_rating_diff  / the new total ratigns
		double new_average_rating = ((path.getAverageRating() * path.getNumberOfRatings()) + rating_diff) / (path.getNumberOfRatings() + total_rating_diff);

		path.setAverageRating(new_average_rating);
		path.setNumberOfRatings(path.getNumberOfRatings() + total_rating_diff);

		this.store(path);

		return path;
		
	}

	private List<LearningSection> loadSections(long id) {
		Query query = new Query(LEARNING_SECTION).addSort("sequence")
				.setFilter(new Query.FilterPredicate("learningPath", Query.FilterOperator.EQUAL, id));

		List<Entity> sections = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());

		return sections.stream().map(e -> mapEntityToLearningSection(e)).collect(Collectors.toList());
	}

	private LearningSection mapEntityToLearningSection(Entity e) {
		LearningSection section = new LearningSection(e.getKey().getId(), (String) e.getProperty("name"), "description",
				(long) e.getProperty("sequence"));

		List<LearningItem> items = loadItems(section.getId());

		section.getItems().addAll(items);

		return section;
	}

	private List<LearningItem> loadItems(long id) {
		Query query = new Query(LEARNING_ITEM).addSort("sequence")
				.setFilter(new Query.FilterPredicate("learningSection", Query.FilterOperator.EQUAL, id));

		List<Entity> items = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());

		return items.stream().map(e -> mapEntityToLearningItem(id, e)).collect(Collectors.toList());
	}

	private LearningItem mapEntityToLearningItem(long id, Entity e) {
		String name = (String) e.getProperty("name");
		String description = (String) e.getProperty("description");
		long sequence = (long) e.getProperty("sequence");
		String url = (String) e.getProperty("url");
		int ratingCount = (int) e.getProperty("ratingCount");
		int ratingTotal = (int) e.getProperty("ratingTotal");

		return new LearningItem(name, id, description, sequence, url, ratingCount, ratingTotal);
	}

	// public void delete(long id) {
	// Key taskKey = KeyFactory.createKey(kind, id);
	// datastore.delete(taskKey);
	// }
}