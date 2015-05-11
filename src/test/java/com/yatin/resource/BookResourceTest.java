package com.yatin.resource;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yatin.domain.Book;
import com.yatin.service.BookService;

@RunWith(MockitoJUnitRunner.class)
public class BookResourceTest {
	
	@Mock
    private BookService service;
	
	@Test
	public void testGetSingleBook() throws Exception {
		
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
		dispatcher.getRegistry().addSingletonResource(new BookResource(service));
		
		Book firstBook = new Book(1, "yatin", "the first book", "this is the first book");
		
		given(service.get(1)).willReturn(firstBook);
		
		MockHttpRequest request = MockHttpRequest.get("/books/1");
		request.contentType(MediaType.APPLICATION_JSON);
		request.accept(MediaType.APPLICATION_JSON);
		
        MockHttpResponse response = new MockHttpResponse();
        
        dispatcher.invoke(request, response);
        
        assertThat(response.getStatus(), is(HttpServletResponse.SC_OK));
        response.getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Book result = mapper.readValue(response.getOutput(), Book.class);
        
        assertThat(result.getIsbn(), is(1));
	}
	
	
	@Test
	public void testGetMultipleBooks() throws Exception {
		
		Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
		dispatcher.getRegistry().addSingletonResource(new BookResource(service));
		
		Book book1 = new Book(1, "yatin", "the 1 book", "this is the 1 book");
		Book book2 = new Book(2, "yatin", "the 2 book", "this is the 2 book");
		Book book3 = new Book(3, "yatin", "the 3 book", "this is the 3 book");
		
		Collection<Book> bookCollection = new ArrayList<>();
		bookCollection.add(book1);
		bookCollection.add(book2);
		bookCollection.add(book3);
		
		given(service.values()).willReturn(bookCollection);
		
		MockHttpRequest request = MockHttpRequest.get("/books");
		request.contentType(MediaType.APPLICATION_JSON);
		request.accept(MediaType.APPLICATION_JSON);
		
        MockHttpResponse response = new MockHttpResponse();
        
        dispatcher.invoke(request, response);
        
        assertThat(response.getStatus(), is(HttpServletResponse.SC_OK));
        response.getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        
        /*
    	 * Note here that we are using TypeReference to infer the type of the returned result
    	 */
        List<Book> result = mapper.readValue(response.getOutput(), new TypeReference<List<Book>>() { });
        
        assertThat(result.size(), is(3));
	}
}
