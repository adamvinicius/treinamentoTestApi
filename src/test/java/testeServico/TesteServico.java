package testeServico;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import suporte.ApiSuporte;

public class TesteServico {

	@Test
	public void exemploTesteApi() {
		Response response = RestAssured.given().baseUri("https://jsonplaceholder.typicode.com/posts")
				.contentType(ContentType.JSON)

				.when().body("{\"titulo\":\"teste titulo\"}").post()

				.then().statusCode(201).extract().response();

		

		System.out.println(response.getBody().asString());
		
		

	}

	@Test
	public void exemploTestApiFrameowrk() {
		ApiSuporte.executePost("https://jsonplaceholder.typicode.com/posts", "{\"titulo\":\"teste titulo\"}", 201);
		assertEquals(101, ApiSuporte.getKey("id"));
		
	}
	
	@Test
	public void exemploTestApiFrameworkHashMap() {
		HashMap<String, Object> json = new HashMap<String, Object>();
		json.put("titulo", "teste titulo");
		
		ApiSuporte.executePost("https://jsonplaceholder.typicode.com/posts", json, 201);
		assertEquals(101, ApiSuporte.getKey("id"));
		
	}
}
