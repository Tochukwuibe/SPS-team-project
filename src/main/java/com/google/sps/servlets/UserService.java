package com.google.sps.servlets;

import com.google.appengine.api.users.UserServiceFactory;
import com.google.sps.data.User;

public class UserService {
	public static User getUser() {
		com.google.appengine.api.users.UserService userService = UserServiceFactory.getUserService();
		if (userService.isUserLoggedIn()) {
			com.google.appengine.api.users.User current = userService.getCurrentUser();
			String logoutUrl = userService.createLogoutURL("/");
			return new User(current.getUserId(), current.getNickname(), current.getEmail(),
					"", logoutUrl, true);
		}
		String loginURL = userService.createLoginURL("/");
		return new User("", "Anonymous", "", loginURL, "", false);
	}
}
