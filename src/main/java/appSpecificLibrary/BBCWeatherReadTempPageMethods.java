package appSpecificLibrary;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import utilities.CommonActionsUI;

public class BBCWeatherReadTempPageMethods extends BBCWeatherReadTempObjects {	

	CommonActionsUI obj = new CommonActionsUI();
	int timeOut = 10;

	public Map<String,String> getWeatherDetails() throws Exception{

		Map<String,String> uiData = new HashMap<String,String>();
		obj.waitForElementToBeVisible(getTempCard(), timeOut);
		Assert.assertTrue(getTempCard().isDisplayed());

		try {
			uiData.put("High", obj.getText(getHighTemp()));
			uiData.put("Low", obj.getText(getLowTemp()));
		}catch(Exception e) {
			throw new Exception("Temperature not displayed on site.");
		}
		return uiData;
	}
}

