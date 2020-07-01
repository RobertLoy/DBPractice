package databasePracticeMaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Practice {

	static Scanner sc = new Scanner(System.in);

	static final String DB_URL 	= "jdbc:mysql://127.0.0.1/sakila";
	static final String USER 	= "root";
	static final String PASS 	= "Marco_62";

	static Connection connection;

	Practice(){

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean login() {
		String username = "Ann";
		String password = "password";
		try {
			Statement stmt = connection.createStatement();		
			String sql = "SELECT username, password FROM staff "
					+ " WHERE username = \""+ username + "\" AND password = \"" + password + "\"";

			
			System.out.println(sql);
			ResultSet results = stmt.executeQuery(sql);
			while(results.next()) {
				System.out.println("USER = " + results.getString("username"));
				System.out.println("PASSWORD = " + results.getString("password"));

			}
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

}
