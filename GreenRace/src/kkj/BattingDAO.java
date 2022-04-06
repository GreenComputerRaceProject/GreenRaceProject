package kkj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BattingDAO {

	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	public BattingDAO() {
		
		String url = "jdbc:mariadb://localhost:3306/race_db";
		String username = "race";
		
		try {

			Class.forName("org.mariadb.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/race_db", "race", "123456");
			
			stmt = con.createStatement();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	  public ArrayList<BattingDTO>List() {
		ArrayList<BattingDTO> res = new ArrayList<BattingDTO>();
		sql = "select * from dansik";
		
		try {
			
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
//				System.out.println(rs.getString("name"));
				
				BattingDTO dto = new BattingDTO();
				dto.horse_num = rs.getInt("horse_num");
				dto.nickname = rs.getString("nickname");
				dto.gambling_money = rs.getLong("gambling_money");
				dto.tot_money = rs.getLong("tot_money");
				
				res.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return res;
	}


		public void detail(int i) {
			// TODO Auto-generated method stub
		
		}
	

	void close() {
		
		if(rs!=null) try {rs.close();} catch (SQLException e) {}
		if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		if(con!=null) try {con.close();} catch (SQLException e) {}
			
	}

}

		
	
	
	
	
	
	
	
	
	
	
	
	
	
	

