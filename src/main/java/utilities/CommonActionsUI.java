package utilities;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonActionsUI {

	////to launch url mentioned in config file
	public void getUrl() {
		DriverSetUp.getBrowser().get(Helpers.readConfigFile("APP_URL"));
		DriverSetUp.getBrowser().manage().window().maximize();
	}

	////dynamic wait for element to be displayed
	public void waitForElementToBeVisible(WebElement ele, int timeout) {
		WebDriverWait wait;
		wait = new WebDriverWait(DriverSetUp.getBrowser(), timeout);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	///WebDriver click
	public void Click(WebElement ele) {
		ele.click();
	}

	///check for presence of element
	public boolean isElementPresent(WebElement ele) {
		try {
			if(ele.isDisplayed()) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e){
			return false;
		}	
	}
	
	///Webdriver sendkeys i.e. enter text data
	public void inputText(WebElement ele, String inputText) {
		ele.sendKeys(inputText);
	}
	
	///read text from an element
	public String getText(WebElement ele) {
		return ele.getAttribute("innerHTML");
	}
	
	///select the required value from a dynamic list
	public void selectFromDynamicDropdown(List<WebElement> eleList, String selectValue) {
		if(eleList.size() > 0) {
			for(int itr = 0; itr < eleList.size(); itr++) {
				if(eleList.get(itr).getText().contains(selectValue)) {
					eleList.get(itr).click();
					break;
				}
			}
		}
	}
	
	///wait so that all the elements sharing the locator can be collected
	public void waitForElementList(List<WebElement> eleList, int timeOut) {
		WebDriverWait wait;
		wait = new WebDriverWait(DriverSetUp.getBrowser(), timeOut);
		wait.until(ExpectedConditions.visibilityOfAllElements(eleList));
	}
}
