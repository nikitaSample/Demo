package utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ReportSetUp {

		private static ExtentReports report = new ExtentReports(Helpers.readConfigFile("REPORTS_PATH"), true);
		private static ExtentTest test;
		
		private ReportSetUp() {
			
		}
		
		public static ExtentTest reportLog(String testName, String description) {
			if(test == null) {				
				test = report.startTest(testName, description);
			}
			return test;			
		}
		
		public static void closeReport() {
			report.endTest(test);
			report.flush();
		}
}
