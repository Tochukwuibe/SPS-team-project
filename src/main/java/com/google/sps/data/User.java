package com.google.sps.data;

public class User {
	private final String id;
	private final String name;
	private final String email;
	private final String loginLink, logoutLink;
	private final boolean isLoggedIn;

	public User(String id, String name, String email, String loginLink, String logoutLink, boolean isLoggedIn) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.loginLink = loginLink;
		this.logoutLink = logoutLink;
		this.isLoggedIn = isLoggedIn;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getLoginLink() {
		return loginLink;
	}

	public String getLogoutLink() {
		return logoutLink;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}
}
