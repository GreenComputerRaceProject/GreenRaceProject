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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		for (int i = 1; i < 9; i++) {
			sql = "update bet_single set rate = round ((select sum(money) from bet_single) /"
					+ " if ((select money from bet_single where num = '"+i+"') = 0, 1, (select money from bet_single where num = '"+i+"')), 1) where num = '"+i+"'";
			
			try {
				int rs = stmt.executeUpdate(sql);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		try {
			res = "COMPLETE";
			
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		for (int i = 1; i < 9; i++) {
			sql = "update bet_place set rate = round (((select sum(money) from bet_place) / 2) /"
					+ " if ((select money from bet_place where num = '"+i+"') = 0, 1, (select money from bet_place where num = '"+i+"')), 1) where num = '"+i+"'";
			
			try {
				int rs = stmt.executeUpdate(sql);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		try {
			res = "COMPLETE";
			
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		String[] nums = {"1_2","1_3","1_4","1_5","1_6","1_7","1_8","2_3","2_4","2_5","2_6","2_7","2_8","3_4","3_5","3_6","3_7","3_8","4_5","4_6","4_7","4_8","5_6","5_7","5_8","6_7","6_8","7_8"};
		
		for (String num : nums) {
			sql = "update bet_quinella set rate = round ((select sum(money) from bet_quinella) /"
					+ " if ((select money from bet_quinella where num = '"+num+"') = 0, 1, (select money from bet_quinella where num = '"+num+"')), 1) where num = '"+num+"'";
			
			try {
				int rs = stmt.executeUpdate(sql);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	
		try {
			res = "COMPLETE";
			
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
