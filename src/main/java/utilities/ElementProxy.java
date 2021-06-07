package utilities;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementProxy implements InvocationHandler {
	
	private final WebElement ele;
	
	public ElementProxy(WebElement element) {
		this.ele = element;
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		this.chkAndKillPopup();  ///to check and kill the alert
		Object result = method.invoke(ele, args); ////since the alert has been removed, it is safe to call the element action
		return result;
	}
	
	private void chkAndKillPopup() {
		WebDriver driver = DriverSetUp.getBrowser();
		try {
			driver.switchTo().alert().accept();
		}catch(Exception e) {}
	}

}
