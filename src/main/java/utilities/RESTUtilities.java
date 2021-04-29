package utilities;

import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RESTUtilities {

	public Response get(String url, Map<String,String> map) {
		Response resp;
		if(map.size()>0) {
			resp = RestAssured.given()
					.header("Content-Type","application/json;charset=UTF-8")
					.queryParams(map)
					.log().all()
					.get(url);

		}else {
			resp = RestAssured.given()
					.header("Content-Type","application/json;charset=UTF-8")
					.log().all()
					.get(url);
		}
		return resp;	
	}
	
	public Response post(String url, String body) {
		  Response resp = RestAssured.given()
				.contentType("application/json")
				.body(body)
				.log().all()
				.post(url);
		  return resp;
	}
	
	public Response put(String url, String body) {
		  Response resp = RestAssured.given()
				.contentType("application/json")
				.body(body)
				.log().all()
				.put(url);
		  return resp;
	}
	///add other request types
}
