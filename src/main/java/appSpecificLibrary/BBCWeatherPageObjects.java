package appSpecificLibrary;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.DriverSetUp;

public class BBCWeatherPageObjects {

		WebDriver driver = DriverSetUp.getBrowser();
		
		public BBCWeatherPageObjects() {
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(xpath = "//a[@class='wr-c-weather-logo']")
		private WebElement weatherLogo;
		public WebElement getWeatherLogo() {
			return weatherLogo;
		}
		
		@FindBy(id = "ls-c-search__input-label")
		private WebElement searchCityField;
		public WebElement getSearchCityField() {
			return searchCityField;
		}
		
		@FindBy(xpath = "//span[@class='gel-long-primer']")
		private List<WebElement> listCities;
		public List<WebElement> getListCities() {
			return listCities;
		}
		
		@FindBy(xpath = "//input[@type='submit']")
		private WebElement searchBtn;
		public WebElement getSearchBtn() {
			return searchBtn;
		}
}
