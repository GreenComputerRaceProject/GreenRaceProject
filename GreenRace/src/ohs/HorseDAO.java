package ohs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class HorseDAO {
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	public HorseDAO() {
		
		String url = "jdbc:mariadb://localhost:3306/race_db";
		String username = "race";
		String password = "123456";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			con = DriverManager.getConnection(
					url,
					username,
					password
					);
			
			stmt = con.createStatement();
			
		} catch (Exception e) {
			
		} // try 끝
	}
		
	public ArrayList<HorseDTO> List(){
			ArrayList<HorseDTO> res = new ArrayList<HorseDTO>();
			sql = "select * from horse";
			
			try {
				rs = stmt.executeQuery(sql);
				
				while(rs.next()) {
					HorseDTO dto = new HorseDTO();
					dto.hid = rs.getInt("hid");
					dto.hname = rs.getString("hname");
					dto.type = rs.getInt("type");
					dto.speed = rs.getDouble("speed");
					dto.firstspeed = rs.getDouble("firstspeed");
					dto.lastspeed = rs.getDouble("lastspeed");
					dto.stamina = rs.getDouble("stamina");
					dto.gender = rs.getBoolean("gender");
					dto.year = rs.getInt("year");
					dto.weight = rs.getDouble("weight");
					dto.state = rs.getInt("state");
					dto.recentrecord = rs.getString("recentrecord");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close();
			}
			
			return res;
		}
		
		
		


	
	void close() {
		// 실행객체 해제
		if(rs!=null) try {rs.close();} catch (Exception e) {}
		if(stmt!=null) try {stmt.close();} catch (Exception e) {}
		if(con!=null) try {con.close();} catch (Exception e) {}
	}
	
}

