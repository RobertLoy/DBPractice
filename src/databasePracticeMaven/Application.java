package databasePracticeMaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

	Scanner sc = new Scanner(System.in);

	// Database Server and DB credentials
	static final String DB_URL 	= "jdbc:mysql://127.0.0.1/sakila";
	static final String USER 	= "root";
	static final String PASS 	= "Marco_62";

	List<Purchase> purchases = new ArrayList<>();

	static Connection connection;

	Application(){

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

	public void start(int user_id) {
		System.out.println("=================");
		System.out.println(" THE APPLICATION");
		System.out.println("=================");
		System.out.println(" Welcome...");

		main:
			do {
				System.out.println("=================");
				System.out.println("       MENU      ");
				System.out.println("=================");
				System.out.println("1 ] INSERT PURCHASE");
				System.out.println("2 ] SELECT ALL PURCHASES");
				System.out.println("3 ] VIEW DETAILS OF ONE PURCHASE");
				System.out.println("4 ] UPDATE DETAILS OF ONE PURCHASE");
				System.out.println("5 ] DELETE ONE PURCHASE");
				System.out.println("Enter [X] to exit, which option?");
				char opt = sc.nextLine().toUpperCase().charAt(0);
				switch(opt) {
				case '1':
					addPurchases(user_id);
					break;
				case '2':
					showPurchases(user_id);
					break;
				case '3':
					viewPurchase(user_id);
					break;
				case '4':
					updatePurchase(user_id);
					break;
				case '5':
					deletePurchase(user_id);
					break;
				case 'X':
					System.out.println("Exiting application.");
					break main;
				default:
					System.out.println("Not a valid entry.");
				}
			}
			while (true);

		System.out.println("THANK YOU FOR USING OUR SYSTEM!");
	}

	private boolean addPurchases(int i) {
		System.out.println("=================");
		System.out.println("      INSERT     ");
		System.out.println("=================");

		String sql = "INSERT INTO purchase (user_id, name, price) VALUES (?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, i);
			System.out.println("Product name: ");
			stmt.setString(2,sc.nextLine());
			System.out.println("Product price: ");			
			stmt.setDouble(3, sc.nextDouble());
			sc.nextLine();
			stmt.execute();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	private boolean showPurchases(int i) {
		System.out.println("=================");
		System.out.println("      LIST       ");
		System.out.println("=================");

		String sql = "SELECT name, price, creeate_date FROM purchase WHERE user_id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,i);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				System.out.print(results.getDate("creeate_date"));
				System.out.print("\t" + results.getDouble("price"));
				System.out.println("\t" + results.getString("name"));
			}
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private void viewPurchase(int i) {

		System.out.println("=================");
		System.out.println(" VIEW A PURCHASE ");
		System.out.println("=================");

		try {
			// GET LIST
			String sql = "SELECT name, id FROM purchase WHERE user_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,i);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				System.out.print(results.getInt("id") + "] ");
				System.out.println("\t" + results.getString("name"));
			}
			System.out.println("Which purchase item to view?");
			int opt = sc.nextInt();
			sc.nextLine();

			// GET INDIVIDUAL RECORD
			sql = "SELECT name, price, creeate_date, user_id, id FROM purchase WHERE id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1,opt);
			results = stmt.executeQuery();
			while (results.next()) {
				System.out.println("NAME: " + results.getString("name"));
				System.out.println("PRICE: $" + results.getDouble("price"));
				System.out.println("PURCHASE DATE: " + results.getDate("creeate_date"));
				System.out.println("STAFF ID: " + results.getInt("user_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updatePurchase(int i) {
		System.out.println("=================");
		System.out.println(" UPDATE PURCHASE ");
		System.out.println("=================");

		try {
			// GET LIST
			String sql = "SELECT name, id FROM purchase WHERE user_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,i);
			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				System.out.print(results.getInt("id") + "] ");
				System.out.println("\t" + results.getString("name"));
			}
			System.out.println("Which purchase item to update?");
			int opt = sc.nextInt();
			sc.nextLine();

			// GET INDIVIDUAL RECORD
			sql = "SELECT name, price, creeate_date, user_id, id FROM purchase WHERE id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1,opt);
			results = stmt.executeQuery();
			while (results.next()) {

				// Allow the user to make changes
				System.out.println("NAME [" + results.getString("name") + "]: ");
				String name = sc.nextLine();
				System.out.println("PRICE [$" + results.getDouble("price") + "]: ");
				Double price = sc.nextDouble();
				sc.nextLine();
				int id = results.getInt("id");

				// Update the table
				sql = "UPDATE purchase SET name = ?, price = ? WHERE id = ?";
				stmt = connection.prepareStatement(sql);
				stmt.setString(1,name);
				stmt.setDouble(2,price);
				stmt.setInt(3,id);
				stmt.execute();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void deletePurchase(int i) {
		System.out.println("=================");
		System.out.println(" DELETE PURCHASE ");
		System.out.println("=================");
		// GET LIST

		try {
			String sql = "SELECT name, id FROM purchase WHERE user_id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1,i);

			ResultSet results = stmt.executeQuery();
			while (results.next()) {
				System.out.print(results.getInt("id") + "] ");
				System.out.println("\t" + results.getString("name"));
			}
			System.out.println("Which purchase item to delete [THERE IS NO UNDO]?");
			int id = sc.nextInt();
			sc.nextLine();


			// DELETE a record from the table
			sql = "DELETE FROM purchase WHERE id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1,id);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
