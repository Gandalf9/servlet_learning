package com.yatin.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.yatin.domain.AuthorV1;
import com.yatin.domain.AuthorV2;
import com.yatin.domain.BookV1;
import com.yatin.service.AuthorService;

@Path("authors")
public class AuthorResource {
	
	public static final String AUTHOR_V1_JSON = "application/vnd.ipt.author-v1+json";
	public static final String AUTHOR_V2_JSON = "application/vnd.ipt.author-v2+json";
	private static final String id = "{id}";

	private AuthorService service;
	
	public AuthorResource() {
		this.service = new AuthorService();
	}
	
	@GET
	@Path("/{id}")
	@Produces(AUTHOR_V1_JSON)
	public Response getAuthorV1(@PathParam("id") int id) {
		
		AuthorV1 result = service.getV1(id);
		
		return Response.ok(result).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(AUTHOR_V2_JSON)
	public Response getAuthorV2(@PathParam("id") String id) {
		
		AuthorV2 result = service.getV2(Integer.valueOf(id));
		
		return Response.ok(result).build();
	}
	
	@POST
	@Consumes(AUTHOR_V1_JSON)
	@Produces(AUTHOR_V1_JSON)
	public Response createAuthorV1(@Context UriInfo info, AuthorV1 author) {
		
		AuthorV1 savedAuthor = service.saveV1(author);
		URI location = info.getAbsolutePathBuilder().segment(String.valueOf(savedAuthor.getId())).build();
		return Response.created(location).entity(savedAuthor).build();
	}
	
	@POST
	@Consumes(AUTHOR_V2_JSON)
	@Produces(AUTHOR_V2_JSON)
	public Response createAuthorV2(@Context UriInfo info, AuthorV2 author) {
		AuthorV2 savedAuthor = service.saveV2(author);
		URI location = info.getAbsolutePathBuilder().segment(String.valueOf(savedAuthor.getId())).build();
		return Response.created(location).entity(savedAuthor).build();
	}
}
