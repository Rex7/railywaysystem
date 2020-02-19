package com.mydemo.operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;



public class Dbhelper {
static Connection con=null;
	
	public static Connection getConnection() {
		
		
		if(con==null) {
	  
			try {
			
				PropertyFileHelper.createProperty();
				Properties prop=PropertyFileHelper.getProperty();
				
				
				con = DriverManager.getConnection(prop.getProperty("dbUrl"), prop.getProperty("username"),prop.getProperty("password"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return con;
	}
	

}
