package com.google.sps.data;

import java.util.List;
import java.util.stream.Collectors;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;

public class ItemFeedbackService {

    private static final String ITEM_FEEDBACK = "itemFeedback";

    private final DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

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
            (long) e.getProperty("userId"),
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
            (long) task.getProperty("userId"),
            (int) task.getProperty("rating"),
            (boolean) task.getProperty("completed")
        );
    }

    public ItemFeedback getOne(long userId, long learningPath) {

        Query query = new Query(ITEM_FEEDBACK)
        .setFilter(new Query.FilterPredicate("userId", Query.FilterOperator.EQUAL, userId))
        .setFilter(new Query.FilterPredicate("learningPath", Query.FilterOperator.EQUAL, learningPath));

        List<Entity> feedbacks = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());

        Entity feedback = feedbacks.get(0);
		
        return new ItemFeedback(
            feedback.getKey().getId(), 
            (long) feedback.getProperty("learningPath"),
            (long) feedback.getProperty("leanrningItem"),
            (long) feedback.getProperty("userId"),
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

        Key taskKey = datastore.put(task);
    }

}