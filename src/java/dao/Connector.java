package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {

	private Connection connection;

	public Connector() {
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Shopper;encrypt=false;trustServerCertificate=false;";
		String user = "sa";
		String password = "181198";
		setConnection(driver, url, user, password);
	}

	private void setConnection(String driver, String url, String user, String password) {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public Connection getConnection() {
		return this.connection;
	}
}
