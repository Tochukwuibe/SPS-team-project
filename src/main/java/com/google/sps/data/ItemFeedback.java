package com.google.sps.data;

import com.google.appengine.api.datastore.Entity;

public class ItemFeedback implements Json {

	private long id;
	private long learningPath;
	private long learningItem;
	private String userId;
	private int rating;
	private boolean completed;
	private long learningSection;


	public ItemFeedback(long id, long learningPath, long learningSection, long learningItem) {
		this(id, learningPath, learningSection, learningItem, "", 0, false);
	}

	public ItemFeedback(long learningPath, long learningSection, long learningItem, String userId, int rating, boolean completed) {
		this(0, learningPath, learningItem, learningSection, userId, rating, completed);
	}

	public ItemFeedback(long id, long learningPath, long learningSection, long learningItem, String userId, int rating, boolean completed) {
		this.id = id;
		this.learningPath = learningPath;
		this.learningSection = learningSection;
		this.learningItem = learningItem;
		this.userId = userId;
		this.rating = rating;
		this.completed = completed;
	}

	public ItemFeedback(Entity e) {
		this.id = e.getKey().getId();
		this.learningPath = (long) e.getProperty("learningPath");
		this.learningSection = (long) e.getProperty("learningSection");
		this.learningItem = (long) e.getProperty("learningItem");
		this.userId = (String) e.getProperty("userId");
		this.rating = ((Long) e.getProperty("rating")).intValue();
		this.completed = e.getProperty("completed") == Boolean.TRUE /* may be null */;
	}

	public long getId() {
		return id;
	}

	public long getLearningPath() {
		return learningPath;
	}

	public long getLearningSection() {
		return learningSection;
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