package com.yatin.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.yatin.domain.BookV1;
import com.yatin.service.BookService;

@Path("v1/books")
public class BookResourceV1 {
	
	private BookService service;
	
	public BookResourceV1() {
		this.service = new BookService();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBook(@PathParam("id") int id) {
		
		BookV1 result = service.getV1(id);
		return Response.ok(result).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBook(@Context UriInfo info, BookV1 book) throws Exception{
		BookV1 savedBook = service.save(book);
		URI location = info.getAbsolutePathBuilder().segment(String.valueOf(savedBook.getId())).build();
		return Response.created(location).entity(savedBook).build();
		
	}
	//PUT
	//DELETE
	

}
