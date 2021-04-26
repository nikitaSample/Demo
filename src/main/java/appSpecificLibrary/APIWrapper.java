package appSpecificLibrary;

import java.util.Map;
import io.restassured.response.Response;
import utilities.Helpers;
import utilities.RESTUtilities;

public class APIWrapper {
	String baseURL = Helpers.readConfigFile("BASE_URL");
	RESTUtilities util;
	
	public Response getCurrentWeatherCityName(Map<String,String> testData) throws Exception {
		
		Response resp;
		try {
			resp = util.get(baseURL, testData);
			if(resp.getStatusCode()!=200) {
				throw new Exception("Failed to get current weather with city name : "+testData.get("q")+" and the response body is : "+resp.getBody());
			}
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		return resp;
	}
}
