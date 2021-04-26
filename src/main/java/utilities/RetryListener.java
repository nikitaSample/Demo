package utilities;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

public class RetryListener implements IRetryAnalyzer, IAnnotationTransformer{

	int count = 0;
	int retryAttempts = 3;
	
	public boolean retry(ITestResult result) {
		
		if(count< retryAttempts) {
			count++;
			return true;
		}		
		
		return false;
	}
	
	public void transform(ITestAnnotation arg0, Class arg1, Constructor arg2, Method arg3) {
		arg0.setRetryAnalyzer(utilities.RetryListener.class);
	}

}
