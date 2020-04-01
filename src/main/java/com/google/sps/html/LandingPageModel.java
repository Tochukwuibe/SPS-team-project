package com.google.sps.html;

import com.google.sps.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Variables used for "home.ftl" (And any included files)
 */
public class LandingPageModel {

	private final User user;
	private final List<LearningPathSummary> learningPaths = new ArrayList<>();

	public LandingPageModel(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public List<LearningPathSummary> getLearningPaths() {
		return learningPaths;
	}
}
