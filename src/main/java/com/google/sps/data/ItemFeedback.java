package com.google.sps.data;
import com.google.appengine.api.datastore.Entity;
public class ItemFeedback implements Json {

    private long id;
    private long learningPath;
    private long learningItem;
    private String userId;
    private int rating;
    private boolean completed;


    public ItemFeedback (long id, long learningPath, long learningItem) {
        this(id, learningPath, learningItem, "", 0, false);
    }

    public ItemFeedback (long learningPath, long learningItem, String userId, int rating, boolean completed) {
        this(0, learningPath, learningItem, userId, rating, completed);
    }

    public ItemFeedback (long id, long learningPath, long learningItem, String userId, int rating, boolean completed) {
        this.id = id;
        this.learningPath = learningPath;
        this.learningItem = learningItem;
        this.userId = userId;
        this.rating = rating;
        this.completed = completed;
    }

    public ItemFeedback(Entity e) {
		this.id = (long) e.getKey().getId();
		this.learningPath = (long) e.getProperty("learningPath");
        this.learningItem = (long) e.getProperty("learningItem");
		this.userId = (String) e.getProperty("userId");
		this.rating = (int) e.getProperty("rating");
		this.completed = (boolean) e.getProperty("complete");
	}

    public long getId() {
        return id;
    }

    public long getLearningPath() {
        return learningPath;
    }

    public long getLearningItem() {
        return learningItem;
    }

    public String getUserId() {
        return userId;
    }

    public int getRating() {
        return rating;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}