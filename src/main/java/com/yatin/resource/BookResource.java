package com.yatin.resource;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yatin.domain.Book;
import com.yatin.service.BookService;

@Component
@Path("/books")
public class BookResource {
	
	private final Logger logger = LoggerFactory.getLogger(BookResource.class);

	@Autowired
	private BookService service;
	
	public BookResource() {
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBook(@PathParam("id") String id, @Context UriInfo info) {
		
		Book firstBook = new Book(1, "yatin", "the first book", "this is the first book");
		service.put(1, firstBook);
		
		int bookId = Integer.parseInt(id);
		Book result = service.get(bookId);
		
		logger.debug("******** book : " + result.toString());
		
		return Response.ok(result).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getBooks() {
		
		return Response.ok(service.values()).build();
	}
}
