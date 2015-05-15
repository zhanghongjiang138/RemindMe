package com.sendtext.test;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleT {
	public static void main(String[] args) throws SQLException {
		 try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			java.sql.Connection connection =null;
			connection=DriverManager.getConnection("jdbc:oracle:thin:@3.242.164.116:1521:icamchina11","icam","icam");
			Statement statement=connection.createStatement();
			//Separate pages
			ResultSet rsResultSet=statement.executeQuery("select * from cost_model_ver");
			while(rsResultSet.next()){
				String result=rsResultSet.getString(2);
				System.out.println(result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		 

	}

}
