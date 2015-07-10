package com.yatin.resource;

import static javax.ws.rs.core.HttpHeaders.LOCATION;
import static javax.ws.rs.core.Response.Status.CREATED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.yatin.config.BaseFeatureTest;
import com.yatin.domain.BookV1;
import com.yatin.domain.BookV2;
import com.yatin.service.BookService;

public class BookResourceTest extends BaseFeatureTest {
	
	@Test
    public void shouldGetVersion1() throws UnirestException, IOException {

        HttpResponse<String> httpResponse = get("/v1/books/1")
                .header("Accept", MediaType.APPLICATION_JSON)
                .asString();

		BookV1 result = mapper.readValue(httpResponse.getBody().toString(), BookV1.class);

		assertThat(result.getName(), is(BookService.THE_AUTHOR));
		assertThat(result.getTitle(), is(BookService.THE_TILTE));
    }
	
	@Test
    public void shouldGetVersion2() throws UnirestException, IOException {

        HttpResponse<String> httpResponse = get("/v2/books/1")
                .header("Accept", MediaType.APPLICATION_JSON)
                .asString();

		BookV2 result = mapper.readValue(httpResponse.getBody().toString(), BookV2.class);

		assertThat(result.getAuthor(), is(BookService.THE_NAME));
		assertThat(result.getIsbn(), is(BookService.ISBN));
    }
	
	@Test
    public void shouldPostVersion1() throws UnirestException, IOException {

		String name = "name";
		String title = "title";
		
		BookV1 book = new BookV1(name,title);
		
        HttpResponse<String> httpResponse = post("/v1/books")
                .header("Content-type", MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(book))
                .asString();

        BookV1 result = mapper.readValue(httpResponse.getBody().toString(), BookV1.class);
        
        assertThat(result.getName(), is(name));
        assertThat(result.getTitle(), is(title));
        
        assertThat(httpResponse.getStatus(), is(CREATED.getStatusCode()));
        assertThat(httpResponse.getHeaders().getFirst(LOCATION.toLowerCase()), is(baseUrl + "/v1/books/" + result.getId()));
    }
	
	@Test
    public void shouldPostVersion2() throws UnirestException, IOException {

		String isbn = "isbn";
		String author = "auth";
		
		BookV2 book = new BookV2(isbn, author);
		
        HttpResponse<String> httpResponse = post("/v2/books")
                .header("Content-type", MediaType.APPLICATION_JSON)
                .body(mapper.writeValueAsString(book))
                .asString();
        
        BookV2 result = mapper.readValue(httpResponse.getBody().toString(), BookV2.class);
        
        assertThat(result.getIsbn(), is(isbn));
        assertThat(result.getAuthor(), is(author));
        
        assertThat(httpResponse.getStatus(), is(CREATED.getStatusCode()));
        assertThat(httpResponse.getHeaders().getFirst(LOCATION.toLowerCase()), is(baseUrl + "/v2/books/" + result.getId()));
    }
}