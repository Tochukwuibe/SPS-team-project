package com.google.sps.data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;

import java.util.List;
import java.util.stream.Collectors;

public class ItemFeedbackService {

    private static final String ITEM_FEEDBACK = "itemFeedback";

    private final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
//    private final LearningPathService learningPathService = new LearningPathService();


    public LearningPath updateRating(long pathId, ItemFeedback feedback) throws EntityNotFoundException {
//        LearningPath path = learningPathService.load(pathId);
        ItemFeedback existing_feedback = getOne(feedback.getUserId(), pathId);

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
//        double new_average_rating = ((path.getAverageRating() * path.getNumberOfRatings()) + rating_diff) / (path.getNumberOfRatings() + total_rating_diff);
//
//        path.setAverageRating(new_average_rating);
//        path.setNumberOfRatings(path.getNumberOfRatings() + total_rating_diff);
//
//        learningPathService.store(path);
//
//        return path;
        return null;
    }

    public List<ItemFeedback> findByLearningPath(long learningPathId) {

        Query query = new Query(ITEM_FEEDBACK)
                .setFilter(new Query.FilterPredicate("learningPath", Query.FilterOperator.EQUAL, learningPathId));

        List<Entity> feedbacks = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());

        return feedbacks.stream().map(e -> new ItemFeedback(e.getKey().getId(), (long ) e.getProperty("learningPath"), (long) e.getProperty("leanrningItem"))
		).collect(Collectors.toList());

    }

    public List<ItemFeedback> findByUserAndPathId(long userId, long learningPath) {

        Query query = new Query(ITEM_FEEDBACK)
        .setFilter(new Query.FilterPredicate("userId", Query.FilterOperator.EQUAL, userId))
        .setFilter(new Query.FilterPredicate("learningPath", Query.FilterOperator.EQUAL, learningPath));

        List<Entity> feedbacks = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());

        return feedbacks.stream().map(e -> new ItemFeedback(
            e.getKey().getId(), 
            (long) e.getProperty("learningPath"),
            (long) e.getProperty("leanrningItem"),
            (String) e.getProperty("userId"),
            (int) e.getProperty("rating"),
            (boolean) e.getProperty("completed")
        )).collect(Collectors.toList());
     
    }

    public ItemFeedback getOne(long feedbackId) {
        Entity task = new Entity(ITEM_FEEDBACK, feedbackId);


        return new ItemFeedback(
            task.getKey().getId(), 
            (long) task.getProperty("learningPath"),
            (long) task.getProperty("leanrningItem"),
            (String) task.getProperty("userId"),
            (int) task.getProperty("rating"),
            (boolean) task.getProperty("completed")
        );
    }

    public ItemFeedback getOne(String userId, long learningPath) {

        Query query = new Query(ITEM_FEEDBACK)
        .setFilter(new Query.FilterPredicate("userId", Query.FilterOperator.EQUAL, userId))
        .setFilter(new Query.FilterPredicate("learningPath", Query.FilterOperator.EQUAL, learningPath));

        List<Entity> feedbacks = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());

        Entity feedback = feedbacks.get(0);
		
        return new ItemFeedback(
            feedback.getKey().getId(), 
            (long) feedback.getProperty("learningPath"),
            (long) feedback.getProperty("leanrningItem"),
            (String) feedback.getProperty("userId"),
            (int) feedback.getProperty("rating"),
            (boolean) feedback.getProperty("completed")
        );
    }

 

    public void store(ItemFeedback feedback) {

        Entity task = new Entity(ITEM_FEEDBACK, feedback.getId());

        task.setProperty("learningPath", feedback.getLearningPath());
		task.setProperty("userId", feedback.getUserId());
        task.setProperty("rating", feedback.getRating());
        task.setProperty("completed", feedback.isCompleted());
        task.setProperty("learningItem", feedback.getLeanrningItem());

        datastore.put(task);
    }

    public void submitFeedback(long pathId, long item1Id, String userId, int rating, boolean completed) {

    }
}