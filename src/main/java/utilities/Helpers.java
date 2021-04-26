package utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

}
	
