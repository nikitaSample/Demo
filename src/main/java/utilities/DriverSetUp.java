package utilities;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSetUp {

	private static WebDriver driver = null;
	
	private DriverSetUp() {
		
	}
	
	public static WebDriver getBrowser(){
		if(driver == null) {
			if(Helpers.readConfigFile("BROWSER").equalsIgnoreCase("chrome")) {						
				System.out.println(Helpers.readConfigFile("CHROME_DRIVER"));
				driver = new ChromeDriver();
			}else if(Helpers.readConfigFile("BROWSER").equalsIgnoreCase("ie")) {
				///path to ie driver server
			}else if(Helpers.readConfigFile("BROWSER").equalsIgnoreCase("firefox")) {
				///path to firefox driver server
			}
			
		}
		return driver;
	}
	
	public static void closeDriver() {
		driver.quit();
	}
}
