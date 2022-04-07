package khs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class UserInfo extends JFrame{

	RaceListMain raceListMain = new RaceListMain();
	
	JLabel jnickname, jmoney, jtotgame, jwin, jlose, jrank;
	

	
	public UserInfo() {
		
//		raceListMain.user_num.get(user_num) = user_num;
		setBounds(50, 50, 500, 400);
		setLayout(null);
		
		
//		System.out.println("유저닉네임:"+raceListMain.user_nickname);
//		System.out.println("유저머니:"+raceListMain.user_money);
//		System.out.println("유저총게임:"+raceListMain.user_totgame);
//		System.out.println("유저승리:"+raceListMain.user_win);
//		System.out.println("유저패배:"+raceListMain.user_lose);
//		System.out.println("유저랭크:"+raceListMain.user_rank);
		
		
			
		jnickname = new JLabel("닉네임:"+raceListMain.user_nickname); 
		jnickname.setBounds(20,20,200,30);
		jmoney = new JLabel("머니:"+raceListMain.user_money); 
		jmoney.setBounds(20,60,200,30); 
		jtotgame = new JLabel("판수:"+raceListMain.user_totgame);
		jtotgame.setBounds(20,100,200,30);
		jwin = new JLabel("승리:"+raceListMain.user_win); 
		jwin.setBounds(20,140,200,30); 
		jlose = new JLabel("패배:"+raceListMain.user_lose); 
		jlose.setBounds(20,180,200,30);
		jrank = new JLabel("랭크:"+raceListMain.user_rank); 
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
	
	
//	public static void main(String[] args) {
//		
//		new UserInfo(2);
//		
//	}

}
