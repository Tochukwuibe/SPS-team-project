package com.google.sps.data;

import java.util.ArrayList;
import java.util.List;

public class LearningSection {
	private String name;
	private long id;

	// Should not be Strings, just for example
	private List<String> items = new ArrayList<>();

	public LearningSection(String name, long id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public List<String> getItems() {
		return items;
	}
}
