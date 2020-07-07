package databasePracticeMaven;

import java.time.LocalDate;

public class Purchase {
	
	private int id;
	private int user_id;
	private String name;
	private double price;
	private LocalDate create_date;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUser_id() {
		return user_id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public LocalDate getCreate_date() {
		return create_date;
	}
	
	public void setCreate_date(LocalDate create_date) {
		this.create_date = create_date;
	}
	
	public Purchase(int id, int user_id, String name, double price, LocalDate create_date) {

		this.id = id;
		this.user_id = user_id;
		this.name = name;
		this.price = price;
		this.create_date = create_date;
	}
	
	

}
