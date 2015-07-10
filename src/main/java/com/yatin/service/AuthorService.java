package com.yatin.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.yatin.domain.AuthorV1;
import com.yatin.domain.AuthorV2;

public class AuthorService {

	public static final String CITY = "London";
	public static final String AUTHOR_ID = "4443";
	public static final String THE_DOB = "The Dob";
	public static final String THE_NAME = "The Name";

	Map<Integer, AuthorV1> authorV1Repo = new HashMap<>();
	Map<Integer, AuthorV2> authorV2Repo = new HashMap<>();
	
	private static AtomicInteger idCounter = new AtomicInteger(2);
	
	public AuthorService() {
		super();
		
		authorV1Repo.put(1, new AuthorV1(1, THE_NAME, THE_DOB));
		authorV2Repo.put(1, new AuthorV2(1, AUTHOR_ID, CITY));
	}
	
	public AuthorV1 getV1(int id) {
		return authorV1Repo.get(id);
	}
	
	public AuthorV2 getV2(int id) {
		return authorV2Repo.get(id);
	}

	public AuthorV1 saveV1(AuthorV1 author) {
		int id = idCounter.getAndIncrement();
		author.setId(id);
		authorV1Repo.put(id, author);
		return author;
	}
	
	public AuthorV2 saveV2(AuthorV2 author) {
		int id = idCounter.getAndIncrement();
		author.setId(id);
		authorV2Repo.put(id, author);
		return author;
	}
}
