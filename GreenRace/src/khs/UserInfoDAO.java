package khs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserInfoDAO {
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	public UserInfoDAO() {
		
		String url = "jdbc:mariadb://localhost:3306/race_db";
		String username = "race";
		String password = "123456";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			con = DriverManager.getConnection(url, username,password);
			
			stmt = con.createStatement();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<UserInfoDTO> user_info(String nickname){
		ArrayList<UserInfoDTO> res = new ArrayList<UserInfoDTO>();
		sql = "select * from user where nickname = " + nickname;
		
		try {
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				UserInfoDTO dto = new UserInfoDTO();
				dto.user_id = rs.getString("user_id");
				dto.nickname = rs.getString("nickname");
				dto.money = rs.getLong("money");
				dto.totgame = rs.getInt("totgame");
				dto.win = rs.getInt("win");
				dto.lose = rs.getInt("lose");
				dto.rank = rs.getInt("rank");
				
				
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
	
	void close() {
		if(rs!=null)try {rs.close();} catch (SQLException e) {}
		if(stmt!=null)try {stmt.close();} catch (SQLException e) {}
		if(con!=null)try {con.close();} catch (SQLException e) {}
		}
		
		
	}
