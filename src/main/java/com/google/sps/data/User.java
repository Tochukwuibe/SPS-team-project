package com.google.sps.data;

public class User {
	private final long id;
	private final String name;
	// TODO other useful attributes

	public User(String name, long id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}
}
