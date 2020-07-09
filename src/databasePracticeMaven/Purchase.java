package databasePracticeMaven;

import java.util.Date;

public class Purchase {
	
	private int id;
	private int user_id;
	private String name;
	private double price;
	private Date create_date;
	private String staffName;
	
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
	
	public Date getCreate_date() {
		return create_date;
	}
	
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
	public Purchase(int id, int user_id, String name, double price, Date date, String staffname) {

		this.id = id;
		this.user_id = user_id;
		this.name = name;
		this.price = price;
		this.create_date = date;
		this.staffName = staffName;
	}
	
	public Purchase(int id, int user_id, String name, double price, String staffname) {

		this.id = id;
		this.user_id = user_id;
		this.name = name;
		this.price = price;
		this.staffName = staffName;
	}
	
	public Purchase(int user_id, String name, double price) {

		this.user_id = user_id;
		this.name = name;
		this.price = price;
		
	}
	
	public Purchase() {

	}


	
	

}
