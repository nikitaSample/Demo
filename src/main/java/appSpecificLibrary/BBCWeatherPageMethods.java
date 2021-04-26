package appSpecificLibrary;

import java.util.HashMap;
import java.util.Map;

import utilities.CommonActionsUI;

public class BBCWeatherPageMethods extends BBCWeatherPageObjects{

		CommonActionsUI obj = new CommonActionsUI();
		int timeOut = 10;
		
		public Map<String,String> getWeatherDetails(String cityName) throws Exception{
			
			Map<String,String> uiData = new HashMap<String,String>();
			
			obj.Click(getSearchCityField());
			obj.inputText(getSearchCityField(), cityName);
			obj.waitForElementList(getListCities(), timeOut);
			obj.selectFromDynamicDropdown(getListCities(), cityName);
			obj.waitForElementToBeVisible(getHighTemp(), timeOut);
			
			try {
				uiData.put("High", obj.getText(getHighTemp()));
				uiData.put("Low", obj.getText(getLowTemp()));
			}catch(Exception e) {
				throw new Exception("Temperature not displayed on site.");
			}
			return uiData;
		}
}
