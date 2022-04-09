package ocy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO {

	Connection con = null;
	Statement stmt;
	ResultSet rs = null;
	
	String sql = null;
	
	public UserDAO() {
		
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
	
	public String logIn(UserDTO dto) {
		sql = "select * from user";
		
		String res = null;
		
		try {
			boolean id_accordence = false;
			boolean pw_accordence = false;
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) { 
				if(rs.getString("id").equals(dto.id)) {
					id_accordence = true;
					
					if(rs.getString("pw").equals(dto.pw)) {
						pw_accordence = true;
					}
				}
			}
			
			if(!id_accordence) {
				res = "ID_NOT_EXIST";
			} else if(!pw_accordence) {
				res = "PW_NOT_CORRECT";
			} else if(id_accordence && pw_accordence) {
				res = "COMPLETE";
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close();
		}
		
		return res;
	}
	
	public UserDTO user_info(UserDTO dto) {
		sql = "select * from user";
		
		UserDTO res = null;
		
		try {
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) { 
				res = new UserDTO();
				
				res.id = rs.getString("id");
				res.pw = rs.getString("pw");
				res.name = rs.getString("name");
				res.nickname = rs.getString("nickname");
				res.phone = rs.getString("phone");
				res.about = rs.getString("about");
				res.money = rs.getLong("money");
				res.tot = rs.getInt("tot");
				res.win = rs.getInt("win");
				res.lose = rs.getInt("lose");
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close();
		}
		
		return res;
	}
	
	public String id_verification(UserDTO dto) {
		sql = "select * from user";
		
		String res = null;
		
		try {
			boolean id_existence = false;
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) { 
				if(rs.getString("id").equals(dto.id)) {
					id_existence = true;
				}
			}
			
			if(id_existence) {
				res = "ID_EXIST";
			} else {
				res = "COMPLETE";
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close();
		}
		
		return res;
	}
	
	public String nickname_verification(UserDTO dto) {
		sql = "select * from user";
		
		String res = null;
		
		try {
			boolean nickname_existence = false;
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) { 
				if(rs.getString("nickname").equals(dto.nickname)) {
					nickname_existence = true;
				}
			}
			
			if(nickname_existence) {
				res = "NICKNAME_EXIST";
			} else {
				res = "COMPLETE";
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close();
		}
		
		return res;
	}
	
	public String phone_verification(UserDTO dto) {
		sql = "select * from user";
		
		String res = null;
		
		try {
			boolean phone_existence = false;
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) { 
				if(rs.getString("phone").equals(dto.phone)) {
					phone_existence = true;
				}
			}
			
			if(phone_existence) {
				res = "PHONE_EXIST";
			} else {
				res = "NEED_CERTIFICATION";
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close();
		}
		
		return res;
	}
	
	public String user_insert(UserDTO dto) {
		sql = "INSERT into user(id, pw, name, nickname, phone, money, totgame, win, lose, rank, about) "
				+ "values('"+dto.id+"', '"+dto.pw+"', '"+dto.name+"','"
				+dto.nickname+"', '"+dto.phone+"', "+10000000+", "+0+", "+0+", "+0+", "+1+", '"+dto.about+"')";
		
		String res = null;
		
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
	
	public String id_find(UserDTO dto) {
		sql = "select id from user where phone = '"+dto.phone+"'";
		
		String id = "";
		
		try {
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				id = rs.getString("id");
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			close();
		}
		
		return id;
	}
	
	public String pw_update(UserDTO dto) {
		sql = "update user set pw = '"+dto.pw+"' where phone = '"+dto.phone+"'";
		
		String res = "";
		
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
	
	void close() {
		if(rs != null) try {rs.close();} catch (SQLException e) {}
		if(stmt != null) try {stmt.close();} catch (SQLException e) {}
		if(con != null) try {con.close();} catch (SQLException e) {}
	}
	
}
