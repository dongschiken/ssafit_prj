package com.cdu.ssafit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String url = "jdbc:mysql://localhost:3306/ssafit_prj?serverTimezone=UTC";
	private static final String username = "ssafy";
	private static final String password = "ssafy";
	private static final String driverName = "com.mysql.cj.jdbc.Driver";
	
	private static DBUtil instance = new DBUtil();
	
	static {
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static DBUtil getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}
