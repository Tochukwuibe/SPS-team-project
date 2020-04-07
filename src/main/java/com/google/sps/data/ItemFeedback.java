package com.google.sps.data;

public class ItemFeedback {

    private long id;
    private long learningPath;
    private long learningItem;
    private long userId;
    private int rating;
    private boolean completed;


    public ItemFeedback (long id, long learningPath, long learningItem) {
        this(id, learningPath, learningItem, 0, 0, false);
    }

    public ItemFeedback (long id, long learningPath, long learningItem, long userId, int rating, boolean completed) {
        this.id = id;
        this.learningPath = learningPath;
        this.learningItem = learningItem;
        this.userId = userId;
        this.rating = rating;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public long getLearningPath() {
        return learningPath;
    }

    public long getLeanrningItem() {
        return learningItem;
    }

    public long getUserId() {
        return userId;
    }

    public int getRating() {
        return rating;
    }

    public boolean isCompleted() {
        return completed;
    }

}