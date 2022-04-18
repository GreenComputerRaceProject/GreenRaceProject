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
	JFrame userInfo;
	String imgIcon = "";
	ImageIcon icon;
	JLabel jnickname, jmoney, jtotgame, jwin, jlose, jrank, jabout;
	String user_id, nickname, about;
	int totgame, win, lose, rank;
	long money;
	
	public RankIcon(TCPClient tc, String nickname) {
		super("인포");
		this.tc = tc;
		this.nickname = nickname;
		
		setBounds(0, 0, 20, 20);
		addActionListener(this);
		
		userInfo = new JFrame();
		userInfo.setBounds(50, 50, 500, 400);
		userInfo.setLayout(null);
		
		jnickname = new JLabel("닉네임:"); 
		jnickname.setBounds(20,20,200,30);
		jmoney = new JLabel("보유머니:"); 
		jmoney.setBounds(20,60,200,30); 
		jtotgame = new JLabel("판수:");
		jtotgame.setBounds(20,100,200,30);
		jwin = new JLabel("승리:"); 
		jwin.setBounds(20,140,200,30); 
		jlose = new JLabel("패배:"); 
		jlose.setBounds(20,180,200,30);
		jrank = new JLabel("랭크:"); 
		jrank.setBounds(20,220,200,30);
		jabout = new JLabel("자기소개:"); 
		jabout.setBounds(20,260,200,30);
		
		userInfo.add(jnickname);
		userInfo.add(jmoney);
		userInfo.add(jtotgame);
		userInfo.add(jwin);
		userInfo.add(jlose);
		userInfo.add(jrank);
		userInfo.add(jabout);
		
		userInfo.setVisible(false);
		userInfo.setResizable(false);
		
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		
		tc.get_User_Rank(this, nickname);
		userInfo.setVisible(true);
	}
	
	public void showUserRank(UserDTO dto) {
		
		jnickname.setText("닉네임:" + dto.getNickname());
		jmoney.setText("보유머니:" + dto.getMoney());
		jtotgame.setText("판수:" + dto.getTotGame());
		jwin.setText("승리:" + dto.getWin());
		jlose.setText("패배:" + dto.getLose());
		jrank.setText("랭크:" + dto.getRank());
		jabout.setText("자기소개:" + dto.getAbout());
		
		userInfo.revalidate();
		userInfo.repaint();
	}
		
}		


