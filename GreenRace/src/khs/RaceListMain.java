package khs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JLabel;

public class RaceListMain {
	
	String user_id, nickname, money, totgame, win, lose, rank;
	int num;	
	JLabel jnickname, jmoney, jtotgame, jwin, jlose, jrank;
	
	ArrayList<String> user_nickname = new ArrayList<String>();
	ArrayList<String> user_money = new ArrayList<String>();
	ArrayList<String> user_totgame = new ArrayList<String>();
	ArrayList<String> user_win = new ArrayList<String>();
	ArrayList<String> user_lose = new ArrayList<String>();	
	ArrayList<String> user_rank = new ArrayList<String>();	
	ArrayList<Integer> user_num = new ArrayList<Integer>();	
	
	public RaceListMain() {
		//1.DB driver ë¡œë”©
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			//2.connection
			Connection con = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3306/race_db", //URL  
					"race",                             	 //ê³„ì •
					"123456"                                 //?•”?˜¸
					);
			
			//3.sql êµ¬ë¬¸ ?‹¤?–‰ ê°ì²´ 
			Statement stmt = con.createStatement();
			
			//4.sql ?‹¤?–‰ ê²°ê³¼ ê°ì²´
			ResultSet rs = stmt.executeQuery("select * from user");
			
			//5.ê²°ê³¼ ?‹¤?–‰
			while(rs.next()) { // rs.next() --> ?‹¤?–‰ ê²°ê³¼ë¥? record?‹¨?œ„ë¡? ?•œ ?–‰?”© ì»¤ì„œê°? ?„˜?–´ê°„ë‹¤.
				System.out.println(rs.getString("name")); // ?ë£Œí˜•?— ë§ì¶° ì»¬ëŸ¼?˜ ê°’ì„ ê°?? ¸?˜¨?‹¤.
				System.out.println(rs.getString("nickname")); 
				System.out.println(rs.getString("money")); 
				System.out.println(rs.getString("totgame")); 
				System.out.println(rs.getString("win")); 
				System.out.println(rs.getString("lose")); 
				System.out.println(rs.getString("rank")); 
				System.out.println("-------------------------"); 
				
				nickname = rs.getString("nickname");
				money = rs.getString("money");
				totgame = rs.getString("totgame");
				win = rs.getString("win");
				lose = rs.getString("lose");
				rank = rs.getString("rank");
				num = rs.getInt("uid");
				
				
				user_nickname.add(nickname);
				user_money.add(money);
				user_totgame.add(totgame);
				user_win.add(win);
				user_lose.add(lose);
				user_rank.add(rank);
				user_num.add(num);
				
			}
			
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
		new RaceListMain();
	}

}
