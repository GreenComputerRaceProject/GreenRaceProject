package kkj;

import java.awt.Color;
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

public class BattingDataMain {
	// 프레임, 레이블, 버튼을 만들고 레이블이랑 버튼을 프레임에 붙인다 
	//jTextField에 값을 입력하고 엔터키를 햇을시 레이블에 값이 반영
	// ------DAO
	
	int[] horse_num = {1,2,3,4,5,6,7,8};
	ArrayList<String> nickname = new ArrayList<String>();
	ArrayList<Long> gambling_money, tot_money = new ArrayList<Long>();
	ArrayList<BattingDTO> data = new ArrayList<BattingDTO>();
	
	Connection con = null;	
	Statement stmt = null;	
	ResultSet rs = null;	
	
	String sql = null;
	
	BattingDataMain(){

		try {

			Class.forName("org.mariadb.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/race_db", "root", "4546");
			
			stmt = con.createStatement();
			
			 ResultSet rs = stmt.executeQuery("select * from dansik");
			 
				while(rs.next()) {
					
					System.out.println(rs.getString("horse_num"));
					System.out.println(rs.getString("nickname"));
					System.out.println(rs.getString("gambling_money"));
					System.out.println(rs.getString("tot_money"));
					
				}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
	
	
	}

	 void close() {
		 if(rs!=null) try {rs.close();} catch (SQLException e) {}
		 if(stmt!=null) try {stmt.close();} catch (SQLException e) {}
		 if(con!=null) try {con.close();} catch (SQLException e) {}		
	}

	public static void main(String[] args) {

		new BattingDataMain();
		
		
	}
}


