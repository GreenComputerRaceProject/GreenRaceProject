package khs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ocy.MultiServer;

public class RankIcon extends JButton implements ActionListener{
	
	Test test;
	UserInfo userInfo;
	RaceListMain raceListMain = new RaceListMain();
	String imgIcon = "";
	ImageIcon icon;
	MultiServer multiServer;
	JLabel jnickname, jmoney, jtotgame, jwin, jlose, jrank;
	String user_id, nickname, money, totgame, win, lose, rank;
	ArrayList<String> user_nickname = new ArrayList<String>();
	ArrayList<String> user_money = new ArrayList<String>();
	ArrayList<String> user_totgame = new ArrayList<String>();
	ArrayList<String> user_win = new ArrayList<String>();
	ArrayList<String> user_lose = new ArrayList<String>();	
	ArrayList<String> user_rank = new ArrayList<String>();
	
	
		public RankIcon(int a, String imgIcon) {
			
			setBounds(50, 50*a, 20, 20); 
			
			icon = new ImageIcon(imgIcon);
			setIcon(icon);
			System.out.println("버튼 생성");

			
			addActionListener(this);
			
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
						+ "rank from user where id = '"+multiServer.user_id+"'"); //다른 패키지라 못 읽음
				
				
				while(rs.next()) { 
					
					nickname = rs.getString("nickname");
					money = rs.getString("money");
					totgame = rs.getString("totgame");
					win = rs.getString("win");
					lose = rs.getString("lose");
					rank = rs.getString("rank");
				
					
					
					user_nickname.add(nickname);
					user_money.add(money);
					user_totgame.add(totgame);
					user_win.add(win);
					user_lose.add(lose);
					user_rank.add(rank);
					
				}
				
				rs.close();
				stmt.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	
	@Override
	public void actionPerformed(ActionEvent e) { //클릭 시 회원의 정보창 띄움
		System.out.println("누름");
		
		userNum();
		JFrame userInfo = new JFrame();
		userInfo.setBounds(50, 50, 500, 400);
		userInfo.setLayout(null);
		jnickname = new JLabel("닉네임:"+user_nickname); 
		jnickname.setBounds(20,20,200,30);
		jmoney = new JLabel("머니:"+user_money); 
		jmoney.setBounds(20,60,200,30); 
		jtotgame = new JLabel("판수:"+user_totgame);
		jtotgame.setBounds(20,100,200,30);
		jwin = new JLabel("승리:"+user_win); 
		jwin.setBounds(20,140,200,30); 
		jlose = new JLabel("패배:"+user_lose); 
		jlose.setBounds(20,180,200,30);
		jrank = new JLabel("랭크:"+user_rank); 
		jrank.setBounds(20,220,200,30);
		
		
		userInfo.add(jnickname);
		userInfo.add(jmoney);
		userInfo.add(jtotgame);
		userInfo.add(jwin);
		userInfo.add(jlose);
		userInfo.add(jrank);
		
		
		userInfo.setVisible(true);
		userInfo.setResizable(false);
		
	}
		
	}
	


