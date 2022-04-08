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
	
//	int[] horse_num = {1,2,3,4,5,6,7,8};
//	ArrayList<String> nickname = new ArrayList<String>();
//	ArrayList<Long> gambling_money, tot_money = new ArrayList<Long>();
//	ArrayList<BattingDTO> data = new ArrayList<BattingDTO>();
//	

	public static void main(String[] args) {


		try {

			Class.forName("org.mariadb.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/race_db", "race", "1234");
			
			Statement stmt = con.createStatement();
			
			 ResultSet rs = stmt.executeQuery("select * from dansik");
			 
			 stmt.executeUpdate("insert into dansik " + 
					 
   						"(horse_num, nickname, gambling_money, tot_money) " +  "VALUES " + 
   						
					 "(4,'파란악마', 800, 4000)");
			 
			 while(rs.next()) {
				 
				 System.out.println("==============================");
				 System.out.println("마번 : "+ rs.getString("horse_num"));
				 System.out.println("유저 닉네임 : "+ rs.getString("nickname"));
				 System.out.println("유저 배팅금액 : "+ rs.getString("gambling_money"));
				 System.out.println("전체유저 배팅금액 : "+ rs.getString("tot_money"));
				 System.out.println("==============================");
				 
			 }

				rs.close();
				stmt.close();
				con.close();
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	
	}
}

		
//		try {
//
//            Class.forName("org.mariadb.jdbc.Driver");
//
//            Connection con = DriverManager.getConnection(
//
//            		"jdbc:mariadb://localhost:3306/race_db","race", "1234" );
//
//            Statement stmt = con.createStatement();
//
//            stmt.executeUpdate("insert into dansik " + 
//            			
//                    "(horse_num, nickname, gambling_money, tot_money) " +  "VALUES " + 
//
//            		"(,'노란악마', 8000000000, 900000000000)");
//            
//            stmt.close();
//            con.close();
//
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
	







