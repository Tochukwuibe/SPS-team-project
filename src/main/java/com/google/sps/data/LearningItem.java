package com.google.sps.data;

import com.google.appengine.api.datastore.Entity;

public class LearningItem {

	private final long id;
	private final long sequence;

	private final String name;
	private final String description;
	private final String url;
	private final long learningSection;
	private final long learningPath;

	// total ratings count by all users
	private long ratingCount;
	// total score by all users
	private long ratingTotal;

	// item completed by current user
	private boolean completed;
	// Rating assigned by the current user
	private long userRating;

	public LearningItem(String name, long id, String description, long sequence, String url, int ratingCount, int ratingTotal) {
		this.name = name;
		this.id = id;
		this.description = description;
		this.sequence = sequence;
		this.url = url;
		this.ratingCount = ratingCount;
		this.ratingTotal = ratingTotal;
		this.learningPath = 0;
		this.learningSection = 0;
	}

	public LearningItem(Entity e) {
		this.id = (long) e.getKey().getId();
		this.learningSection = (long) e.getProperty("learningSection");
		this.learningPath = (long) e.getProperty("learningPath");
		this.name = (String) e.getProperty("name");
		this.description = (String) e.getProperty("description");
		this.sequence = (long) e.getProperty("sequence");
		this.url = (String) e.getProperty("url");
		this.ratingCount = ((Long) e.getProperty("ratingCount")).intValue();
		this.ratingTotal = ((Long) e.getProperty("ratingTotal")).intValue();

	}


	public long getId() {
		return id;
	}

	public long getLearningSection() {
		return learningSection;
	}

	public long getLearningPath() {
		return learningPath;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public long getSequence() {
		return sequence;
	}

	public long getRatingCount() {
		return ratingCount;
	}

	public void setRatingCount(long ratingCount) {
		this.ratingCount = ratingCount;
	}

	public void setRatingTotal(long ratingTotal) {
		this.ratingTotal = ratingTotal;
	}


	public long getRatingTotal() {
		return ratingTotal;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public long getUserRating() {
		return userRating;
	}

	public void setUserRating(long userRating) {
		this.userRating = userRating;
	}

	/**
	 * Update this LearningItem with specific user feedback
	 *
	 * @param fb
	 */
	public void setUserValues(ItemFeedback fb) {
		setCompleted(fb.isCompleted());
		setUserRating(fb.getRating());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LearningItem other = (LearningItem) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}