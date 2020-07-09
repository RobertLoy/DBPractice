package databasePracticeMaven;

import java.util.ArrayList;
import java.util.List;

public class PurchaseDaoImpl {
	
	
	
	public boolean create(Purchase p) {
		
		return true;
		
	}
	
	public List<Purchase> readAll(){
		List<Purchase> purchases = new ArrayList<>();
		
		return purchases;
	}
	
	public Purchase readById(int id) {
		Purchase p = new Purchase();
		
		return p;
	}
	
	public boolean update(Purchase p, int id) {
		
		return true;
	}
	
	public boolean delete(int id) {
		return true;
	}

}
