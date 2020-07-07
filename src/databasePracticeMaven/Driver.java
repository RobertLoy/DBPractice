package databasePracticeMaven;

import java.util.Scanner;

public class Driver {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		do {
			// Create object of the Database code
			Practice p = new Practice();

			System.out.println("Enter user: ");
			String username = sc.nextLine();

			System.out.println("Enter pass: ");
			String password = sc.nextLine();
			
			// U:Ann
			// P:password
			// SQL INJECTION:" ""="
			// if (p.loginConcatenation(username, password)) {
			int user_id = p.loginParameters(username, password);
			if (user_id >= 0) {
				System.out.println("Congratulations you are logged in!");
				Application ap = new Application();
				ap.start(user_id);
			}
			else {
				System.out.println("Who are you?  I don't recognize those credintials.");			
			}
		} while (true);

	}

}
