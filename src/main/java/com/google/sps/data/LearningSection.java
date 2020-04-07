package com.google.sps.data;

import java.util.ArrayList;
import java.util.List;

public class LearningSection {
	private final long id;
	private final String name;
	private final String description;
	private final long sequence;

	// Should not be Strings, just for example
	private List<LearningItem> items = new ArrayList<>();

	public LearningSection(long id, String name, String description, long sequence) {
		this.name = name;
		this.description = description;
		this.id = id;
		this.sequence = sequence;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public List<LearningItem> getItems() {
		return items;
	}

	public long getSequence() {
		return sequence;
	}

	public String getDescription() {
		return description;
	}
}
