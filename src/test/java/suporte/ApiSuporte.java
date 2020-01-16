package suporte;

import java.util.HashMap;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;



public class ApiSuporte {
	
	private static Response response;
	private static RequestSpecification requestSpecification;

	
	public static Response getResponse() {
		return response;
	}

	public static void setResponse(Response response) {
		ApiSuporte.response = response;
	}

	public static RequestSpecification getRequestSpecification() {
		return requestSpecification;
	}

	public static void setRequestSpecification(RequestSpecification requestSpecification) {
		ApiSuporte.requestSpecification = requestSpecification;
	}

	public static void setGiven(String url) {
		RequestSpecification request = RestAssured.given().baseUri(url).contentType(ContentType.JSON);

		setRequestSpecification(request);
	}
	
	public static void setWhenPOST(Object json) {
		Response _response = getRequestSpecification().when()
		.body(json)
		.post();
		
		setResponse(_response);
		
	}
	

	
	public static void setThen(int statusCode) {
		Response response = getResponse().then()
		.statusCode(201)
		.extract().response();
		setResponse(response);
	}
	
	public static void executePost(String url, String json, int statusCode) {
		setGiven(url);
		setWhenPOST(json);
		setThen(statusCode);
	}
	
	public static void executePost(String url, HashMap<String, Object> json, int statusCode) {
		setGiven(url);
		setWhenPOST(json);
		setThen(statusCode);
	}
	
	public static Object getKey(String key) {
		return getResponse().getBody().jsonPath().get(key);
	}

}
