package com.yatin.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.yatin.domain.Book;

@Service
public class BookService extends HashMap<Integer, Book>{

	private static final long serialVersionUID = 4806767727278618719L;
	
	public BookService() {
		super();
	}
}
