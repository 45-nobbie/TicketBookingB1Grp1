package com.proj.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public static Connection getConnectiontoDatabase() {
	Connection con = null;
	try {
	Class.forName("org.postgresql.Driver");//Load the driver to JVM
	con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/ProjDB", "postgres", "ramji");
	}catch(Exception e) {
		e.printStackTrace();
	}
	if (con != null) {
		
	}
	return con;
	}
}
