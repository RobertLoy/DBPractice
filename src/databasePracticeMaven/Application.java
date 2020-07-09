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
	PurchaseDao pd = new PurchaseDao();

	List<Purchase> purchases = new ArrayList<>();

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

	private void addPurchases(int i) {
		System.out.println("=================");
		System.out.println("      INSERT     ");
		System.out.println("=================");

		System.out.println("Product name: ");
		String name = sc.nextLine();
		System.out.println("Product price: ");			
		Double price = sc.nextDouble();
		sc.nextLine();

		pd.create(new Purchase(i, name, price));

	}

	private void showPurchases(int i) {
		System.out.println("=================");
		System.out.println("      LIST       ");
		System.out.println("=================");

		System.out.println("Purchase Date \t\t\tPrice \tProduct");
		System.out.println("---------------------------------------------------------------");
		for (Purchase p: pd.readAll()) {
			System.out.println(p.getCreate_date() + "\t$" + p.getPrice() + "\t" + p.getName());
		}
	}

	private void viewPurchase(int i) {

		System.out.println("=================");
		System.out.println(" VIEW A PURCHASE ");
		System.out.println("=================");

		System.out.println("ID \tPurchase Date \t\t\tPrice \tProduct");
		System.out.println("---------------------------------------------------------------");
		for (Purchase p: pd.readAll()) {
			System.out.println(p.getId() + "\t" + p.getCreate_date() + "\t$" + p.getPrice() + "\t" + p.getName());
		}

		System.out.println("Which purchase item to view?");
		int opt = sc.nextInt();
		sc.nextLine();

		
}

private void updatePurchase(int i) {
	System.out.println("=================");
	System.out.println(" UPDATE PURCHASE ");
	System.out.println("=================");

	try {
		// GET LIST
		String sql = "SELECT name, id FROM purchase WHERE user_id = ?";
		PreparedStatement stmt = ConnectionObject.getConnection().prepareStatement(sql);
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
		stmt = ConnectionObject.getConnection().prepareStatement(sql);
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
			stmt = ConnectionObject.getConnection().prepareStatement(sql);
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
		PreparedStatement stmt = ConnectionObject.getConnection().prepareStatement(sql);
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
		sql = "DELETE FROM purchase";
		stmt = ConnectionObject.getConnection().prepareStatement(sql);
		//stmt.setInt(1,id);
		stmt.execute();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
