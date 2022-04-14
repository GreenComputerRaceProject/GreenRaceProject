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

import ocy.TCPClient;
import ocy.UserDTO;

public class RankIcon extends JButton implements ActionListener{
	
	TCPClient tc;
	String imgIcon = "";
	ImageIcon icon;
	JLabel jnickname, jmoney, jtotgame, jwin, jlose, jrank;
	String user_id, nickname;
	int totgame, win, lose, rank;
	long money;

	
		public RankIcon(TCPClient tc, String nickname) { //,String imgIcon
			super("인포");
			this.tc = tc;
//			this.nickname = tc.user.getNickname();
			
			
				this.nickname = nickname;
//				this.money = tc.user.getMoney();
//				this.totgame = tc.user.getTotGame();
//				this.win = tc.user.getWin();
//				this.lose = tc.user.getLose();
//				this.rank = tc.user.getRank();	
			
			
			setBounds(0, 0, 20, 20);
			
//			icon = new ImageIcon(imgIcon);
//			setIcon(icon);
			System.out.println("버튼 생성");

			
			addActionListener(this);
			
		}
		
		void userInfo() {
//			try {
//				Class.forName("org.mariadb.jdbc.Driver");
//				
//				
//				Connection con = DriverManager.getConnection( 
//						"jdbc:mariadb://localhost:3306/race_db",  
//						"race",                             	
//						"123456"                                
//						);
//				
//				
//				Statement stmt = con.createStatement();
//				
//				
//				ResultSet rs = stmt.executeQuery("select nickname,money,totgame,win,lose,"
//						+ "rank from user where nickname = '"+nickname+"'");
//				
//				
//				while(rs.next()) { 
//					
//					nickname = rs.getString("nickname");
//					money = rs.getLong("money");
//					totgame = rs.getInt("totgame");
//					win = rs.getInt("win");
//					lose = rs.getInt("lose");
//					rank = rs.getInt("rank");
//					
//				}
//				
//				rs.close();
//				stmt.close();
//				con.close();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("누름");
		
//		userNum();
		JFrame userInfo = new JFrame();
		userInfo.setBounds(50, 50, 500, 400);
		userInfo.setLayout(null);
		jnickname = new JLabel("닉네임:"+tc.user.getNickname()); 
		jnickname.setBounds(20,20,200,30);
		jmoney = new JLabel("머니:"+tc.user.getMoney()); 
		jmoney.setBounds(20,60,200,30); 
		jtotgame = new JLabel("판수:"+tc.user.getTotGame());
		jtotgame.setBounds(20,100,200,30);
		jwin = new JLabel("승리:"+tc.user.getWin()); 
		jwin.setBounds(20,140,200,30); 
		jlose = new JLabel("패배:"+tc.user.getLose()); 
		jlose.setBounds(20,180,200,30);
		jrank = new JLabel("랭크:"+tc.user.getRank()); 
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


