package khs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ocy.MultiServer;

public class UserInfo extends JFrame{

	Test test;
	MultiServer multiServer;
	JLabel jnickname, jmoney, jtotgame, jwin, jlose, jrank;
	String user_id, nickname, money, totgame, win, lose, rank;
	
	
	public UserInfo() {
			
		userNum();
		setBounds(50, 50, 500, 400);
		setLayout(null);
		
		jnickname = new JLabel("닉네임:"+nickname); 
		jnickname.setBounds(20,20,200,30);
		jmoney = new JLabel("머니:"+money); 
		jmoney.setBounds(20,60,200,30); 
		jtotgame = new JLabel("판수:"+totgame);
		jtotgame.setBounds(20,100,200,30);
		jwin = new JLabel("승리:"+win); 
		jwin.setBounds(20,140,200,30); 
		jlose = new JLabel("패배:"+lose); 
		jlose.setBounds(20,180,200,30);
		jrank = new JLabel("랭크:"+rank); 
		jrank.setBounds(20,220,200,30);
		
		
		add(jnickname);
		add(jmoney);
		add(jtotgame);
		add(jwin);
		add(jlose);
		add(jrank);
		
		
		setVisible(true);
		setResizable(false);
	
	}
		
		
		void userNum() {
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				
				
				Connection con = DriverManager.getConnection(
						"jdbc:mariadb://localhost:3306/race_db",  
						"race",                             	
						"123456"                                
						);
				
				
				Statement stmt = con.createStatement();
				
				
				ResultSet rs = stmt.executeQuery("select nickname,money,totgame,win,lose,"
						+ "rank from user where nickname = '"+multiServer.current_User+"'"); //그냥 nickname으로해도 특정 가능한가?
				
				
				while(rs.next()) { 
					
					nickname = rs.getString("nickname");
					money = rs.getString("money");
					totgame = rs.getString("totgame");
					win = rs.getString("win");
					lose = rs.getString("lose");
					rank = rs.getString("rank");
					
				}
				
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	
	
	
//	public static void main(String[] args) {
//		
//		new UserInfo();
//		
//	}

}
