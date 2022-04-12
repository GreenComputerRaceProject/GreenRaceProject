package ocy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BetDAO_Single {

	Connection con = null;
	Statement stmt;
	ResultSet rs = null;
	
	String sql = null;
	
	public BetDAO_Single() {
		
		String url = "jdbc:mariadb://localhost:3306/race_db";
		String username = "race";
		String password = "123456";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			Connection con = DriverManager.getConnection(url, username, password);
			
			stmt = con.createStatement();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public String betting(TCPData data) {
		
		sql = "update user set money = money - '"+data.bet_single.money+"' where nickname = '"+data.user.nickname+"'";
		
		String res = "";
		
		try {
			int rs = stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		sql = "update bet_single set money = money + '"+data.bet_single.money+"' where num = '"+data.bet_single.hname+"'";
		
		
		try {
			int rs = stmt.executeUpdate(sql);
			
			if(rs == 1) {
				res = "COMPLETE";
			} else {
				res = "WRONG";
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close();
		}
		
		return res;
	}
	
	public ArrayList<Double> rate(TCPData data) {
		
		sql = "select rate from bet_single";
		
		ArrayList<Double> res = new ArrayList<Double>();
		
		try {
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				res.add(rs.getDouble("rate"));
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close();
		}
		
		return res;
	}
	
	void close() {
		if(rs != null) try {rs.close();} catch (SQLException e) {}
		if(stmt != null) try {stmt.close();} catch (SQLException e) {}
		if(con != null) try {con.close();} catch (SQLException e) {}
	}
	
}
