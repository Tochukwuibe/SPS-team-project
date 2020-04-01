package com.google.sps.html;

/**
 * A limited view of a LearningPath that does not include all of the content (Sections, items, etc).
 */
public class LearningPathSummary {
	private long id;
	private String name;

	public LearningPathSummary(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
