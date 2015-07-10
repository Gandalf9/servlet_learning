package com.yatin.resource;

import static javax.ws.rs.core.HttpHeaders.LOCATION;
import static javax.ws.rs.core.Response.Status.CREATED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static com.yatin.resource.AuthorResource.*;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.yatin.config.BaseFeatureTest;
import com.yatin.domain.AuthorV1;
import com.yatin.domain.AuthorV2;
import com.yatin.domain.BookV1;
import com.yatin.domain.BookV2;
import com.yatin.service.AuthorService;

public class AuthorResourceTest extends BaseFeatureTest {
	
	@Test
    public void shouldGetVersion1() throws UnirestException, IOException {

        HttpResponse<String> httpResponse = get("/authors/1")
                .header("Accept", AUTHOR_V1_JSON)
                .asString();

        ObjectMapper mapper = new ObjectMapper();
		AuthorV1 result = mapper.readValue(httpResponse.getBody().toString(), AuthorV1.class);

		assertThat(result.getName(), is(AuthorService.THE_NAME));
		assertThat(result.getDob(), is(AuthorService.THE_DOB));
    }
	
	@Test
    public void shouldGetVersion2() throws UnirestException, IOException {

        HttpResponse<String> httpResponse = get("/authors/1")
                .header("Accept", AUTHOR_V2_JSON)
                .asString();

        ObjectMapper mapper = new ObjectMapper();
		AuthorV2 result = mapper.readValue(httpResponse.getBody().toString(), AuthorV2.class);

		assertThat(result.getAuthorId(), is(AuthorService.AUTHOR_ID));
		assertThat(result.getCity(), is(AuthorService.CITY));
    }
	
	@Test
    public void shouldPostVersion1() throws UnirestException, IOException {

		String name = "name";
		String dob = "dob";
		
		AuthorV1 author = new AuthorV1(name, dob);
		
        HttpResponse<String> httpResponse = post("/authors")
                .header("Content-type", AUTHOR_V1_JSON)
                .body(mapper.writeValueAsString(author))
                .asString();

        AuthorV1 result = mapper.readValue(httpResponse.getBody().toString(), AuthorV1.class);
        
        assertThat(result.getName(), is(name));
        assertThat(result.getDob(), is(dob));
        
        assertThat(httpResponse.getStatus(), is(CREATED.getStatusCode()));
        assertThat(httpResponse.getHeaders().getFirst(LOCATION.toLowerCase()), is(baseUrl + "/authors/" + result.getId()));
    }
	
	@Test
    public void shouldPostVersion2() throws UnirestException, IOException {

		String authId = "aId";
		String city = "city";
		
		AuthorV2 author = new AuthorV2(authId, city);
		
        HttpResponse<String> httpResponse = post("/authors")
                .header("Content-type", AUTHOR_V2_JSON)
                .body(mapper.writeValueAsString(author))
                .asString();

        AuthorV2 result = mapper.readValue(httpResponse.getBody().toString(), AuthorV2.class);
        
        assertThat(result.getAuthorId(), is(authId));
        assertThat(result.getCity(), is(city));
        
        assertThat(httpResponse.getStatus(), is(CREATED.getStatusCode()));
        assertThat(httpResponse.getHeaders().getFirst(LOCATION.toLowerCase()), is(baseUrl + "/authors/" + result.getId()));
    }

}
