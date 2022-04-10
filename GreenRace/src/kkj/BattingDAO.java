package kkj;

import java.awt.Color;
import java.awt.Panel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import model.MemberDAO;
import model.MemberDTO;

public class BattingDAO {
	// 프레임, 레이블, 버튼을 만들고 레이블이랑 버튼을 프레임에 붙인다 
	//jTextField에 값을 입력하고 엔터키를 햇을시 레이블에 값이 반영
	
//	int[] horse_num = {1,2,3,4,5,6,7,8};
//	int horse_num;
//	ArrayList<String> nickname = new ArrayList<String>();
//	ArrayList<Long> gambling_money, tot_money = new ArrayList<Long>();
	
	Connection con = null;	
	Statement stmt = null;	
	ResultSet rs = null;	
	
	String sql =  null;
	
	public BattingDAO() {
		String url = "jdbc:mariadb://localhost:3306/race_db";
		String username = "race";
		String password = "123456";
	
		
	try {

			Class.forName("org.mariadb.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/race_db", "race", "123456");
			
			stmt = con.createStatement();
			
			
//			 ResultSet rs = stmt.executeQuery("select * from dansik");
			 
//				while(rs.next()) {
//					
//					System.out.println(rs.getString("horse_num"));
//					System.out.println(rs.getString("nickname"));
//					System.out.println(rs.getString("gambling_money"));
//					System.out.println(rs.getString("tot_money"));
//					
//				}
//				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	ArrayList<BattingDTO> list() {
		ArrayList<BattingDTO> res = new ArrayList<BattingDTO>();
		sql = "select * from dansik";
		
		try {
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
//				System.out.println(rs.getString("nickname"));
				
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
		}
		return res;
	}

	public BattingDTO detail(int horse_num) {
		BattingDTO res = null;
		
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
				res = new BattingDTO();
				res.horse_num = rs.getInt("horse_num");
				res.nickname = rs.getString("nickname");
				res.gambling_money = rs.getLong("gambling_money");
				res.tot_money = rs.getLong("tot_money");
				
//				res.add(dto);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			close();
		}
		return res;
	}

	public long write(BattingDTO dto) {
		long res = 0;
		
		sql = "insert into dansik (horse_num, nickname, gambling_money) "+ "values("
			+dto.horse_num+"','"+dto.nickname+"','"+dto.gambling_money+") ";
	
		System.out.println(sql);
		
		try {
			res = stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			close();
		}
	
		return res;
	}
	
	public long modify(BattingDTO dto) {
		long res = 0;
		
		
		sql = "update dansik set "
				+" horse_num = '"+dto.horse_num	
				+"', nickname = '"  +dto.nickname
				+", gambling_money = " +dto.gambling_money;
//				+ " tot_money = "+dto.tot_money;
		
		System.out.println(sql);
		
		try {
			res = stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			close();
		}
		
		return res;
	}
	
	public long delete(String nickname) {
		long res = 0;
		
		sql = "delete from dansik where nickname = "+nickname ;
		
		System.out.println(sql);
		try {
			
			res = stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			close();
		}
		return res;
	}
	
	 void close() {
		 if(rs!=null) try {rs.close();} catch (SQLException e) {}
		 if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		 if(con!=null) try {con.close();} catch (SQLException e) {}		
	}

	 
	 public static void main(String[] args) {

	 }
}