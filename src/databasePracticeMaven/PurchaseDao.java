package databasePracticeMaven;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseDao {

	Connection conn = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	public void create(Purchase p) {
		String sql = "INSERT INTO purchase (user_id, name, price) VALUES (?,?,?)";
		try {
			stmt = ConnectionObject.getConnection().prepareStatement(sql);
			stmt.setInt(1,p.getUser_id());
			stmt.setString(2,p.getName());		
			stmt.setDouble(3,p.getPrice());
			stmt.execute();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Purchase> readAll(){
		List<Purchase> purchases = new ArrayList<>();
		String sql = "SELECT id, user_id, name, price, creeate_date, first_name FROM purchase, staff WHERE purchase.user_id = staff.staff_id";
		try {
			stmt = ConnectionObject.getConnection().prepareStatement(sql);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Date date = new java.util.Date(rs.getTimestamp("creeate_date").getTime());
				purchases.add(new Purchase(rs.getInt("id"), rs.getInt("user_id"),rs.getString("name"),
						rs.getDouble("price"),date,rs.getString("first_name")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return purchases;
	}


	public List<Purchase> readByStaffId(int staff){
		List<Purchase> purchases = new ArrayList<>();
		String sql = "SELECT id, name, price, creeate_date, first_name FROM purchase, staff WHERE purchase.user_id = staff.staff_id AND staff_id = ?";
		try {
			stmt = ConnectionObject.getConnection().prepareStatement(sql);
			stmt.setInt(1,staff);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Date date = new java.util.Date(rs.getTimestamp("creeate_date").getTime());
				purchases.add(new Purchase(rs.getInt("id"), rs.getInt("user_id"),rs.getString("name"),
						rs.getDouble("price"),date, rs.getString("first_name")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return purchases;
	}

	public Purchase readByPurchaseId(int id) {
		Purchase p = new Purchase();
		try {
			// GET INDIVIDUAL RECORD
			String sql = "SELECT name, price, creeate_date, user_id, id, first_name FROM purchase, staff "
					+ "WHERE id = ? AND staff.staff_id = purchase.user_id";
			stmt = ConnectionObject.getConnection().prepareStatement(sql);
			stmt.setInt(1,id);
			rs = stmt.executeQuery();
			rs.next();
			p.setName(rs.getString("name"));
			p.setPrice(rs.getDouble("price"));
			Date date = new java.util.Date(rs.getTimestamp("creeate_date").getTime());
			p.setCreate_date(date);
			p.setUser_id(rs.getInt("user_id"));
			p.setStaffName(rs.getString("first_name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return p;
	}

	public boolean update(Purchase p, int id) {

		return true;
	}

	public boolean delete(int id) {
		return true;
	}

}
