package ocy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BetDAO {

	Connection con = null;
	Statement stmt;
	ResultSet rs = null;
	
	String sql = null;
	
	public BetDAO() {
		
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
	
	public String single_betting(TCPData data) {
		
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
	
	public String place_betting(TCPData data) {
		
		sql = "update user set money = money - '"+data.bet_place.money+"' where nickname = '"+data.user.nickname+"'";
		
		String res = "";
		
		try {
			int rs = stmt.executeUpdate(sql);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		sql = "update bet_place set money = money + '"+data.bet_place.money+"' where num = '"+data.bet_place.hname+"'";
		
		
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

	public String quinella_betting(TCPData data) {
	
	sql = "update user set money = money - '"+data.bet_quinella.money+"' where nickname = '"+data.user.nickname+"'";
	
	String res = "";
	
	try {
		int rs = stmt.executeUpdate(sql);
	} catch (Exception ex) {
		ex.printStackTrace();
	}
	
	sql = "update bet_quinella set money = money + '"+data.bet_quinella.money+"' where num = '"+data.bet_quinella.hname1 + "_" + data.bet_quinella.hname2+"'";
	
	
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
	
	public ArrayList<Double> single_rate(TCPData data) {
		
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
	
	public ArrayList<Double> place_rate(TCPData data) {
		
		sql = "select rate from bet_place";
		
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

	public ArrayList<Double> quinella_rate(TCPData data) {
	
	sql = "select rate from bet_quinella";
	
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
