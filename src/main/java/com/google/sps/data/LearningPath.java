package com.google.sps.data;

import java.util.ArrayList;
import java.util.List;

public class LearningPath {

	private String name;
	private long id;
	private List<LearningSection> sections = new ArrayList<>();

	public LearningPath(String name, long id) {
		this.name = name;
		this.id = id;
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
