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

	
		public RankIcon(TCPClient tc) { //,String imgIcon
			this.tc = tc;
			this.user_id = tc.user.getName();
			this.nickname = tc.user.getNickname();
			this.money = tc.user.getMoney();
			this.totgame = tc.user.getTotGame();
			this.win = tc.user.getWin();
			this.lose = tc.user.getLose();
			this.rank = tc.user.getRank();
			
			setBounds(0, 0, 20, 20);
			
//			icon = new ImageIcon(imgIcon);
//			setIcon(icon);
			System.out.println("Î≤ÑÌäº ?Éù?Ñ±");

			
			addActionListener(this);
			
		}
		
	
	@Override
	public void actionPerformed(ActionEvent e) { //?Å¥Î¶? ?ãú ?öå?õê?ùò ?†ïÎ≥¥Ï∞Ω ?ùÑ??
		System.out.println("?àÑÎ¶?");
		
//		userNum();
		JFrame userInfo = new JFrame();
		userInfo.setBounds(50, 50, 500, 400);
		userInfo.setLayout(null);
		jnickname = new JLabel("?ãâ?Ñ§?ûÑ:"+nickname); 
		jnickname.setBounds(20,20,200,30);
		jmoney = new JLabel("Î®∏Îãà:"+money); 
		jmoney.setBounds(20,60,200,30); 
		jtotgame = new JLabel("?åê?àò:"+totgame);
		jtotgame.setBounds(20,100,200,30);
		jwin = new JLabel("?äπÎ¶?:"+win); 
		jwin.setBounds(20,140,200,30); 
		jlose = new JLabel("?å®Î∞?:"+lose); 
		jlose.setBounds(20,180,200,30);
		jrank = new JLabel("?û≠?Å¨:"+rank); 
		jrank.setBounds(20,220,200,30);
		
		
		userInfo.add(jnickname);
		userInfo.add(jmoney);
		userInfo.add(jtotgame);
		userInfo.add(jwin);
		userInfo.add(jlose);
		userInfo.add(jrank);
		
		
		userInfo.setVisible(true);
		userInfo.setResizable(false);
		userInfo.setDefaultCloseOperation(userInfo.EXIT_ON_CLOSE);
		
	}
		
}	


