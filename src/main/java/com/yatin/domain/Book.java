package com.yatin.domain;

public class Book {
	
	private int isbn;
	private String author;
	private String title;
	private String description;
	
	public Book() {
	}
	
	public Book(int isbn, String author, String title, String description) {
		this.isbn = isbn;
		this.author = author;
		this.title = title;
		this.description = description;
	}

	public int getIsbn() {
		return isbn;
	}
	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
