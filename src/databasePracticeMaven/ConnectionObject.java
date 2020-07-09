package databasePracticeMaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionObject {

	// Database Server and DB credentials
	static final String DB_URL 	= "jdbc:mysql://127.0.0.1/sakila";
	static final String USER 	= "root";
	static final String PASS 	= "Marco_62";
	
	private static ConnectionObject connectionObject = null;
	
	private ConnectionObject() {
		try {
			// Register the JDBC Driver
			// The driver was added to the project in the pom.xml file
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {

		try {
			// Make the connection to the DB server and DB by passing in server credentials
			return DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static ConnectionObject getInstance() {
		if (connectionObject == null) {
			connectionObject = new ConnectionObject();
		}
		return connectionObject;
	}
	
	public static void main(String [] args) {
		ConnectionObject conn = ConnectionObject.getInstance();
	}
}
