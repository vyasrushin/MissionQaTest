package mission.ReqResAPI;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;
import mission.utils.LoadProp;

public class APIResReq {
	

    public APIResReq() {

    	String baseUrl = LoadProp.getProperty("apiBaseUrl");

        System.out.println("Base URL from properties = " + baseUrl);

        RestAssured.baseURI = baseUrl;
    }
    
    public RequestSpecification getRequest() {
    	return RestAssured.given()
    		.log().all()
            .header("x-api-key", LoadProp.getProperty("apikey"))
            .contentType(ContentType.JSON);
    }
    

	public Response getUser(int userId) {
		Response response =  getRequest()
	            .when()
	            .get("/api/users/" + userId)
	            .then()
	            .log().all()
	            .extract()
	            .response();
	    
	    return response;
	}
	
	public Response listUsers(int page) {
		Response response =  getRequest()
	            .queryParam("page", page)
	            .when()
	            .get("/api/users")
	            .then()
	            .log().all()
	            .extract()
	            .response();
		return response;
	}
	
	public Response listTotalUsers() {
		Response response =   getRequest()
	            .when()
	            .get("/api/users")
	            .then()
	            .log().all()
	            .extract()
	            .response();
	    return response;
	}
	
	public Response createUser(String name, String job) {
	    Map<String, Object> requestBody = new HashMap<>();
	    requestBody.put("name", name);
	    requestBody.put("job", job);
	
	    Response response = getRequest()
	            .body(requestBody)
	            .when()
	            .post("/api/users")
	            .then()
	            .log().all()
	            .extract()
	            .response();
	    return response;
	}
	
	public Response deleteUser(int userId) {
		Response response = getRequest()
	            .when()
	            .delete("/api/users/" + userId)
	            .then()
	            .log().all()
	            .extract()
	            .response();
	    return response;
	}
	
	public Response loginUser(String email, String password) {
		Map<String, Object> requestBody = new HashMap<>();
	    requestBody.put("email", email);
	    requestBody.put("password", password);

	    Response response = getRequest()
	            .body(requestBody)
	            .when()
	            .post("/api/login")
	            .then()
	            .log().all()
	            .extract()
	            .response();

	    return response;
	}
}
