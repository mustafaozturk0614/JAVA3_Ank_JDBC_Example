package com.bilgeadam;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private String url = "jdbc:postgresql://localhost:5432/school";
	private String username = "postgres";
	private String password = "root";

	public Connection getConnection() throws SQLException {

		return DriverManager.getConnection(url, username, password);

	}

}
