package com.google.sps.data;


import com.google.appengine.api.users.UserServiceFactory;

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