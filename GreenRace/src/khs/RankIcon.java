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
	JLabel jnickname, jmoney, jtotgame, jwin, jlose, jrank, jabout;
	String user_id, nickname, about;
	int totgame, win, lose, rank;
	long money;

	
		public RankIcon(TCPClient tc, String nickname) { //,String imgIcon
//			this.nickname = tc.user.getNickname();
			super("인포");
			
			if(nickname==tc.user.getNickname()) {
				this.nickname = nickname;
				this.money = tc.user.getMoney();
				this.totgame = tc.user.getTotGame();
				this.win = tc.user.getWin();
				this.lose = tc.user.getLose();
				this.rank = tc.user.getRank();
				this.about = tc.user.getAbout();
			}
						
			setBounds(0, 0, 20, 20);
			
//			icon = new ImageIcon(imgIcon);
//			setIcon(icon);
			System.out.println("버튼 생성");

			
			addActionListener(this);
			
		}
		

		
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("누름");
		
		
		JFrame userInfo = new JFrame();
		userInfo.setBounds(50, 50, 500, 400);
		userInfo.setLayout(null);
		jnickname = new JLabel("닉네임:"+nickname); 
		jnickname.setBounds(20,20,200,30);
		jmoney = new JLabel("보유머니:"+money); 
		jmoney.setBounds(20,60,200,30); 
		jtotgame = new JLabel("판수:"+totgame);
		jtotgame.setBounds(20,100,200,30);
		jwin = new JLabel("승리:"+win); 
		jwin.setBounds(20,140,200,30); 
		jlose = new JLabel("패배:"+lose); 
		jlose.setBounds(20,180,200,30);
		jrank = new JLabel("랭크:"+rank); 
		jrank.setBounds(20,220,200,30);
		jabout = new JLabel("자기소개:"+about); 
		jabout.setBounds(20,260,200,30);
		
		
		userInfo.add(jnickname);
		userInfo.add(jmoney);
		userInfo.add(jtotgame);
		userInfo.add(jwin);
		userInfo.add(jlose);
		userInfo.add(jrank);
		userInfo.add(jabout);
		
		
		userInfo.setVisible(true);
		userInfo.setResizable(false);
		
		
	}
		
}		


