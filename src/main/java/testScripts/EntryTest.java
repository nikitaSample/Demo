package testScripts;

import java.io.File;
import java.util.Map;

import org.testng.annotations.Test;

import appSpecificLibrary.APIWrapper;
import appSpecificLibrary.BBCWeatherPageMethods;
import io.restassured.response.Response;
import utilities.CommonActionsUI;
import utilities.Helpers;

public class EntryTest extends CommonActionsUI {
  
	/*@Test()
	public void weatherFromApi() throws Exception{
		
		APIWrapper objApi = new APIWrapper();
		Map<String,String> testData = Helpers.readJson("Weather", "");
		Response resp = objApi.getCurrentWeatherCityName(testData);
		System.out.println(resp.prettyPrint());
		///assert and reports
		
	}
	
	@Test
	public void weatherFromBBC() throws Exception {
		getUrl();
		BBCWeatherPageMethods obj = new BBCWeatherPageMethods();
		Map<String,String> output = obj.getWeatherDetails("London, Greater London"); ////parameterize using excel
		///assert and reports
		
	}*/
	
	@Test
	public void sample() {
		String path = System.getProperty("user.dir")+File.separator+Helpers.readConfigFile("SOURCE_FOLDER")+File.separator+"Weather.json";
		System.out.println(path);
	}
}
