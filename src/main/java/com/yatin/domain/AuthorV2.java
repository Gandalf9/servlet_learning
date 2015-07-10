package com.yatin.domain;

public class AuthorV2 {

	private int id;
	private String authorId;
	private String city;

	public AuthorV2() {
	}

	public AuthorV2(int id, String authorId, String city) {
		super();
		this.id = id;
		this.authorId = authorId;
		this.city = city;
	}

	public AuthorV2(String authorId, String city) {
		super();
		this.authorId = authorId;
		this.city = city;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
