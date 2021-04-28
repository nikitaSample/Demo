package testScripts;


import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import appSpecificLibrary.APIWrapper;
import appSpecificLibrary.BBCWeatherPageMethods;
import io.restassured.response.Response;
import utilities.CommonActionsUI;
import utilities.DriverSetUp;
import utilities.Helpers;
import utilities.ReportSetUp;

public class EntryTest extends CommonActionsUI {
	
	String apiCity;
	String apiHighTemp;
	String apiLowTemp;
	ExtentTest logger = ReportSetUp.reportLog("Sample", "Match temperatures from OpenWeatherAPI and BBCWeatherUI");
  
	@Test()
	public void weatherFromApi() throws Exception{
		
		APIWrapper objApi = new APIWrapper();
		Map<String,String> testData = Helpers.readJson("Weather", "CurrentWeatherWithCityName");
		Response resp = objApi.getCurrentWeatherCityName(testData);
		
		if(resp.getStatusCode()==200) {
			logger.log(LogStatus.PASS, "Valid response code.");
		}else {
			logger.log(LogStatus.FAIL, "Invalid response code.");
		}
		logger.log(LogStatus.INFO, "API response : "+resp.prettyPrint());
		
		apiCity = Helpers.readValueFromJSONResponse(resp, "apiCity");
		apiHighTemp = Helpers.readValueFromJSONResponse(resp, "main.temp_max");
		apiLowTemp = Helpers.readValueFromJSONResponse(resp, "main.temp_min");
		
		///to verify that the response is of correct city
		if(testData.get("q").equals(apiCity)) {
			logger.log(LogStatus.PASS, "Response displayed is for the expected city.");
		}else {
			logger.log(LogStatus.FAIL, "Response displayed is not for the expected city.Expected city : "+testData.get("q")+" Actual city : "+apiCity);
		}
		
		logger.log(LogStatus.INFO, "High temperature from API response : "+apiHighTemp);
		logger.log(LogStatus.INFO, "Low temperature from API response : "+apiLowTemp);
	}
	
	/*@Test
	public void weatherFromBBC() throws Exception {
		getUrl();
		BBCWeatherPageMethods obj = new BBCWeatherPageMethods();
		Map<String,String> output = obj.getWeatherDetails("London, Greater London"); ////parameterize using excel
		
		String uiHighTemp = output.get("High").substring(0, output.get("High").length()-1);
		String uiLowTemp = output.get("Low").substring(0, output.get("Low").length()-1);
		
		if(uiHighTemp.equals(apiHighTemp)) {
			logger.log(LogStatus.PASS, "High temperature captured from UI and API are same.");
		}else {
			logger.log(LogStatus.FAIL, "High temperature captured from UI and API are different.");
		}
		
		if(uiLowTemp.equals(apiLowTemp)) {
			logger.log(LogStatus.PASS, "Low temperature captured from UI and API are same.");
		}else {
			logger.log(LogStatus.FAIL, "Low temperature captured from UI and API are different.");
			assertThat((new Object[]{uiHighTemp, uiLowTemp}), is(new Object[]{apiHighTemp, apiLowTemp}));
		}
	}
	
	@AfterClass
	public void tearDown() {
		DriverSetUp.closeDriver();
		ReportSetUp.closeReport();
	}*/
}
