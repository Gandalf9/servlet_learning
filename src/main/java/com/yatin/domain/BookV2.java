package com.yatin.domain;

public class BookV2 {
	
	private int id;
	private String isbn;
	private String author;
	
	public BookV2() {
	}
	
	public BookV2(String isbn, String author) {
		super();
		this.isbn = isbn;
		this.author = author;
	}

	public BookV2(int id, String isbn, String author) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
