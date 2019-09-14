package controller;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Statement;


public class Connection {

	private java.sql.Connection connection = null;
	private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private String DB_URL;

	public Connection(String user, String password, String bd) throws ClassNotFoundException, SQLException  {

		Class.forName(JDBC_DRIVER);
		DB_URL = String.format("jdbc:mysql://127.0.0.1/%1$s?autoReconnect=true&useSSL=false", bd);
		connection = DriverManager.getConnection(DB_URL, user, password);
	}

	public PreparedStatement query(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	
	public PreparedStatement queryGeneratedKeys(String sql) throws SQLException {
		return connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	}
	
	


	public void close() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}
}
