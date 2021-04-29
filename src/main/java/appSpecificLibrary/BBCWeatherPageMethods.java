package appSpecificLibrary;

import java.util.HashMap;
import java.util.Map;

import utilities.CommonActionsUI;

public class BBCWeatherPageMethods extends BBCWeatherPageObjects{

		CommonActionsUI obj = new CommonActionsUI();
		int timeOut = 10;
		
		public void searchCityByName(String cityName) throws Exception{			
			
			obj.Click(getSearchCityField());
			obj.inputText(getSearchCityField(), cityName);
			obj.waitForElementList(getListCities(), timeOut);
			obj.selectFromDynamicDropdown(getListCities(), cityName);
			
		}
}
