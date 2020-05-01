package com.google.sps.data;

import java.util.ArrayList;
import java.util.List;

public class LearningPath {

	private final String name;
	private final String description;
	private final long id;
	private double averageRating;
	private int numberOfRatings;
	private final List<LearningSection> sections = new ArrayList<>();
    private double completion;


	public LearningPath(long id, String name, String description) {
		this(id, name, description, 0, 0);
	}


	public LearningPath(long id, String name, String description, double averageRating, int numberOfRatings) {
		this.name = name;
		this.id = id;
		this.description = description;
		this.averageRating = averageRating;
		this.numberOfRatings = numberOfRatings;
        this.completion = 0;
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

	public String getDescription() {
		return description;
	}

	public double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(double averageRating) {
		this.averageRating = averageRating;
	}


	public int getNumberOfRatings() {
		return numberOfRatings;
	}

	public void setNumberOfRatings(int numberOfRatings) {
	  this.numberOfRatings = numberOfRatings;
	}

    public void setCompletion(double c){
      this.completion  = c;
    }
    public double getCompletion(){
      return completion;
    }

    public String getCompletionPercentage() {
		return String.format("%.0f", completion*100);
	}
}
