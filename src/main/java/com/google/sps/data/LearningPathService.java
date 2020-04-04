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

	private final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	public LearningPathService() {

	}

	public List<LearningPathSummary> listLearningPaths() {
		Query query = new Query(this.LEARNING_PATH).addSort("name");

		List<Entity> entities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());

		return entities.stream()
				.map(e -> new LearningPathSummary(e.getKey().getId(), (String) e.getProperty("name")))
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

		LearningPath result = new LearningPath(name, path.getKey().getId());
		result.getSections().addAll(sections);
		return result;
	}

	private List<LearningSection> loadSections(long id) {
		Query query = new Query(LEARNING_SECTION)
				.addSort("sequence")
				.setFilter(new Query.FilterPredicate("learningPath", Query.FilterOperator.EQUAL, id));

		List<Entity> sections = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());

		return sections.stream()
				.map(e -> new LearningSection((String) e.getProperty("name"), e.getKey().getId(),
						(long) e.getProperty("sequence")))
				.collect(Collectors.toList());
	}

//    public void delete(long id) {
//        Key taskKey = KeyFactory.createKey(kind, id);
//        datastore.delete(taskKey);
//    }
}