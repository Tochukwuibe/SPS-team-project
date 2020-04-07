package com.google.sps.data;

import java.util.ArrayList;
import java.util.List;

public class LearningPath {

	private final String name;
	private final String description;
	private final long id;
	private final List<LearningSection> sections = new ArrayList<>();

	public LearningPath(long id, String name, String description) {
		this.name = name;
		this.id = id;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public List<LearningSection> getSections() {
		return sections;
	}
}
