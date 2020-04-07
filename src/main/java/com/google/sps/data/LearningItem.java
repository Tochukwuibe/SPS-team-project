package com.google.sps.data;

public class LearningItem {

	private final long id;
	private final long sequence;

	private final String name;
	private final String description;
	private final String url;

	private long ratingCount;
	private long ratingTotal;

	public LearningItem(String name, long id, String description, long sequence, String url, int ratingCount, int ratingTotal) {
		this.name = name;
		this.id = id;
		this.description = description;
		this.sequence = sequence;
		this.url = url;
		this.ratingCount = ratingCount;
		this.ratingTotal = ratingTotal;
	}


    public long getId() {
        return id;
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


    public long getRatingTotal() {
        return ratingTotal;
    }

}