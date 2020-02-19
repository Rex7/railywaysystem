package com.mydemo.operations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class PropertyFileHelper {
	private static final String DB_URL="jdbc:oracle:thin:@192.168.2.169:1521:xstore";
	private static final String DB_USER="system";
	private static final String DB_Password="system";
	
	public static boolean createProperty() {

		boolean statusCreated=false;
		try {
			Properties prop=new Properties();
		FileOutputStream out=new FileOutputStream("database.properties");
		
		prop.setProperty("dbUrl", DB_URL);
		prop.setProperty("username", DB_USER);
		prop.setProperty("password", DB_Password);
		prop.store(out, "databse");
	
	
		
		}
		catch(Exception ex) {
			
		}
		return statusCreated;
	}
	public static Properties getProperty() {
		Properties prop=new Properties();
		try {
		FileInputStream out=new FileInputStream("database.properties");			
		  prop.load(out);
		}
		catch(Exception ex) {
			
		}
		  return prop;
	}

}
