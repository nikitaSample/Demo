package appSpecificLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.DriverSetUp;

public class BBCWeatherReadTempObjects {

	WebDriver driver = DriverSetUp.getBrowser();
	public BBCWeatherReadTempObjects() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@id='daylink-0']")
	private WebElement tempCard;
	public WebElement getTempCard() {
		return tempCard;
	}

	@FindBy(xpath = "//a[@id='daylink-0']//span[@class='wr-day-temperature__high-value']//span[@class='wr-value--temperature--f']")
	private WebElement highTemp;
	public WebElement getHighTemp() {
		return highTemp;
	}

	@FindBy(xpath = "//a[@id='daylink-0']//span[@class='wr-day-temperature__low-value']//span[@class='wr-value--temperature--f']")
	private WebElement lowTemp;
	public WebElement getLowTemp() {
		return lowTemp;
	}
}
