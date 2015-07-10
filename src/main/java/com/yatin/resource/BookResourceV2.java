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
import javax.ws.rs.core.UriInfo;

import com.yatin.domain.BookV1;
import com.yatin.domain.BookV2;
import com.yatin.service.BookService;

@Path("v2/books")
public class BookResourceV2 {
	
	private BookService service;
	
	public BookResourceV2() {
		this.service = new BookService();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBook(@PathParam("id") int id) {
		
		BookV2 result = service.getV2(id);
		
		return Response.ok(result).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBook(@Context UriInfo info, BookV2 book) throws Exception{
		BookV2 savedBook = service.save(book);
		URI location = info.getAbsolutePathBuilder().segment(String.valueOf(savedBook.getId())).build();
		return Response.created(location).entity(savedBook).build();
		
	}

}
