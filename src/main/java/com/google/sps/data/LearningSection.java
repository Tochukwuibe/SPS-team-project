package com.google.sps.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

	public long getNumItems() {
		return items.size();
	}

	/**
	 * Find a learning item in this section by ID. May return no result if item (no longer) exists.
	 *
	 * @param learningItem the item Id
	 */
	public Optional<LearningItem> getItemById(long learningItem) {
		return items.stream().filter(item -> item.getId() == learningItem).findAny();
	}
}
