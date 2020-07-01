package databasePracticeMaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Practice {

	// Database Server and DB credentials
	static final String DB_URL 	= "jdbc:mysql://127.0.0.1/sakila";
	static final String USER 	= "root";
	static final String PASS 	= "Marco_62";

	static Connection connection;

	Practice(){

		try {
			// Register the JDBC Driver
			// The driver was added to the project in the pom.xml file
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// Make the connection to the DB server and DB by passing in server credentials
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// This is the OLD and UNSECURE way to write SQL statements in JDBC
	public boolean loginConcatenation(String username, String password) {
		
		System.out.println("=================");
		System.out.println("      LOGIN      ");
		System.out.println("=================");
		
		try {
			// Create a Statement object to use when querying the DB
			Statement stmt = connection.createStatement();	
			
			// Write the SQL with string concatenation to insert the variable
			String sql = "SELECT username, password FROM staff "
					+ " WHERE username = \""+ username + "\" AND password = \"" + password + "\"";
			
			// System.out.println(sql);  // DEBUGGING PURPOSES ONLY - NEVER DISPLAY SQL 
			
			// Execute the query based on the sql variable
			ResultSet results = stmt.executeQuery(sql);
			
			// Loop through the results of the query
			while(results.next()) {
				System.out.println("USER = " + results.getString("username"));
				System.out.println("PASSWORD = " + results.getString("password"));
			}
			// Credentials are valid so return TRUE
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Credentials are NOT valid so return FALSE
		return false;
	}
	
	// This is the NEW and MORE SECURE way to write SQL statements in JDBC	
	public boolean loginParameters(String username, String password) {
		
		System.out.println("=================");
		System.out.println("      LOGIN      ");
		System.out.println("=================");
		
		try {
			// Write the SQL with ? as placeholders for user or code variables
			String sql = "SELECT username, password FROM staff WHERE username = ? AND password = ?";
			
			// Create a PreparedStatement object so it look for the ? placeholders
			PreparedStatement stmt = connection.prepareStatement(sql);	
			
			// Replace the ? with the correct number and type of variable (e.g. String)
			stmt.setString(1, username);
			stmt.setString(2, password);
			
			// System.out.println(sql);  // DEBUGGING PURPOSES ONLY - NEVER DISPLAY SQL 
			
			// Execute the query based on the PreparedStatement object, do not pass the sql variable
			ResultSet results = stmt.executeQuery();
			
			// Loop through the results of the query
			while(results.next()) {
				System.out.println("USER = " + results.getString("username"));
				System.out.println("PASSWORD = " + results.getString("password"));
			}
			// Credentials are valid so return TRUE
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Credentials are NOT valid so return FALSE
		return false;
	}

}
