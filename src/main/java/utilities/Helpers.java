package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Helpers {

	////to read data from config file
	public static String readConfigFile(String key) {
		FileReader obj;
		try {
			obj = new FileReader(System.getProperty("user.dir")+File.separator+"config.properties");
			Properties prop = new Properties();

			prop.load(obj);
			return prop.getProperty(key);
		}
		catch(IOException e){
			e.getMessage();
			return "";
		}
	}

	////to read json
	@SuppressWarnings("deprecation")
	public static Map<String,String> readJson(String fileName, String node) throws Exception{
		String path = System.getProperty("user.dir")+File.separator+Helpers.readConfigFile("SOURCE_FOLDER")+File.separator+fileName+".json";
		System.err.println(path);
		Map<String,String> dataMap= new HashMap<String,String>();

		JsonElement jElement = new JsonParser().parse(new FileReader(path));
		JsonObject jObject = jElement.getAsJsonObject();
		JsonElement jEle = jObject.get(node);
		JsonObject obj = jEle.getAsJsonObject();

		///read the entries from the json file to add to map
		Iterator itr = obj.entrySet().iterator();
		while(itr.hasNext()) {
			Entry entry =  (Entry) itr.next();
			dataMap.put(entry.getKey().toString(), entry.getValue().toString());
		}
		return dataMap;
	}

	///to connect to and retieve data from DB
	public static ResultSet retrieveDBData(String query) throws Exception {

		Connection conn = null;
		Statement stmt = null;

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		///Open connection
		String dbName = Helpers.readConfigFile("DB_NAME");
		String dbUser = Helpers.readConfigFile("DB_USERNAME");
		String dbPass = Helpers.readConfigFile("DB_PASSWORD");
		String dbServer = Helpers.readConfigFile("DB_SERVER");
		String connectionStmt = "jdbc:sqlserver://"+dbServer+";databaseName="+dbName;
		conn = DriverManager.getConnection(connectionStmt, dbUser, dbPass);

		//Execute query and return data in resultset
		stmt = conn.createStatement();
		return stmt.executeQuery(query);

	}

	////to fetch required value from response body
	public static String readValueFromJSONResponse(Response resp, String key) throws Exception {
		try {
			return JsonPath.from(resp.getBody().asString()).get(key).toString();
		}catch(Exception e){
			throw new Exception("Specified key not found in the response body.Key : "+key);		
		}

	}

	///to read excel data using apache poi
	////to get workbook
	public static Workbook getExcelWorkbook(FileInputStream inputStream, String excelFilePath) throws IOException {

		Workbook workbook = null;
		if(excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		}else if(excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException("The specified file is not in excel format.");
		}
		return workbook;
	}

	/////to read data from specified excel file.File neme should be given with extension
	public static Object[][] readExcel(String fileName, String sheetName) throws IOException{

		Object[][] excelData=null;
		String excelFilePath = System.getProperty("user.dir")+File.separator+readConfigFile("SOURCE_FOLDER")+File.separator+fileName;

		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = getExcelWorkbook(inputStream, excelFilePath);
		Sheet worksheet = workbook.getSheet(sheetName);

		int rowCount = worksheet.getPhysicalNumberOfRows();
		int colCount = worksheet.getRow(0).getLastCellNum();
		excelData = new Object[rowCount-1][colCount];

		for(int iRow=1; iRow<rowCount; iRow++) {
			for(int iCol=0; iCol<colCount; iCol++) {
				excelData[iRow-1][iCol] = worksheet.getRow(iRow).getCell(iCol).toString();
			}			
		}
		return excelData;
	}

	////the function to access all the elements of page factory and create a wrapper for automatically handling alert 
	//////the function to handle pop up and setting up proxy is available in ElementProxy class.

	public static WebElement ElementGuard(WebElement ele) {
		ElementProxy proxy = new ElementProxy(ele);

		WebElement wrappedEle = (WebElement)Proxy.newProxyInstance(ElementProxy.class.getClassLoader(), 
				new Class[] {WebElement.class}, 
				proxy);

		return wrappedEle;
	}

	public static <T> void initElements(WebDriver driver, T pageobject, Class name) {

		PageFactory.initElements(driver, pageobject);

		////access all the elements and create a wrapper
		for(Field f : name.getDeclaredFields()) {

			if(f.getType().equals(WebElement.class)) {

				boolean accessible = f.isAccessible();
				f.setAccessible(true);

				//reset thewebelement with proxy object

				try {
					f.set(pageobject, Helpers.ElementGuard((WebElement) f.get(pageobject)));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IllegalArgumentException e) {
					e.printStackTrace();
				}
				f.setAccessible(accessible);
			}
		}

	}
}

