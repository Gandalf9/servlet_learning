package com.yatin.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Component
@Path("/test")
public class TestResource {
	
	@GET
	public String getTest() {
		return "Hello World";
	}
}
