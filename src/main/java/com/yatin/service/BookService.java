package com.yatin.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.yatin.domain.BookV1;
import com.yatin.domain.BookV2;

public class BookService {

	public static final String ISBN = "isbn-isbn";
	public static final String THE_NAME = "The Name";
	public static final String THE_TILTE = "The Tilte";
	public static final String THE_AUTHOR = "The Author";
	
	Map<Integer, BookV1> bookV1Repo = new HashMap<>();
	Map<Integer, BookV2> bookV2Repo = new HashMap<>();
	
	private static AtomicInteger idCounter = new AtomicInteger(2);
	
	public BookService() {
		super();
		
		bookV1Repo.put(1, new BookV1(1, THE_AUTHOR, THE_TILTE));
		bookV2Repo.put(1, new BookV2(1, ISBN, THE_NAME));
	}
	
	public BookV1 getV1(int id) {
		return bookV1Repo.get(id);
	}
	
	public BookV2 getV2(int id) {
		return bookV2Repo.get(id);
	}

	public BookV1 save(BookV1 book) {
		int id = idCounter.getAndIncrement();
		book.setId(id);
		bookV1Repo.put(id, book);
		return book;
	}
	
	public BookV2 save(BookV2 book) {
		int id = idCounter.getAndIncrement();
		book.setId(id);
		bookV2Repo.put(id, book);
		return book;
	}
}