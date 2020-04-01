package com.google.sps.html;

import com.google.sps.data.LearningPath;
import com.google.sps.data.User;

/**
 * Variables used for "path.ftl" (And any included files)
 */
public class LearningPathModel {

	private User user;
	private LearningPath path;

	public LearningPathModel(User user, LearningPath path) {
		this.user = user;
		this.path = path;
	}

	public User getUser() {
		return user;
	}

	public LearningPath getPath() {
		return path;
	}
}
