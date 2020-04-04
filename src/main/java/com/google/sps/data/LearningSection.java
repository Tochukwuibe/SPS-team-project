package com.google.sps.data;

import java.util.ArrayList;
import java.util.List;

public class LearningSection {
	private long id;
	private String name;
	private long sequence;

	// Should not be Strings, just for example
	private List<String> items = new ArrayList<>();

	public LearningSection(String name, long id, long sequence) {
		this.name = name;
		this.id = id;
		this.sequence = sequence;
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

	public long getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
}
