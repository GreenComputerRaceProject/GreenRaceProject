package khs;

import javax.swing.JFrame;
import javax.swing.JLabel;

import ocy.TCPClient;

public class MyInfo extends JFrame{
	TCPClient tc;
	String user_id, nickname;
	int totgame, win, lose, rank;
	long money;
	JLabel Jnickname, Jmoney, Jtotgame, Jwin, Jlose, Jrank, Jabout;
	
	public MyInfo(TCPClient tc) {
		
		this.nickname = tc.user.getNickname();
		setBounds(50, 50, 300, 350);
		setLayout(null);
		Jnickname = new JLabel("나의 닉네임:"+tc.user.getNickname()); 
		Jnickname.setBounds(20,20,200,30);
		Jmoney = new JLabel("보유머니:"+tc.user.getMoney()); 
		Jmoney.setBounds(20,60,200,30); 
		Jtotgame = new JLabel("판수:"+tc.user.getTotGame());
		Jtotgame.setBounds(20,100,200,30);
		Jwin = new JLabel("승리:"+tc.user.getWin()); 
		Jwin.setBounds(20,140,200,30); 
		Jlose = new JLabel("패배:"+tc.user.getLose()); 
		Jlose.setBounds(20,180,200,30);
		Jrank = new JLabel("랭크:"+tc.user.getRank()); 
		Jrank.setBounds(20,220,200,30);
		Jabout = new JLabel("자기소개:"+tc.user.getAbout()); 
		Jabout.setBounds(20,260,200,30);
		
		add(Jnickname);
		add(Jmoney);
		add(Jtotgame);
		add(Jwin);
		add(Jlose);
		add(Jrank);
		add(Jabout);
		
		setVisible(true);
		setResizable(false);
	}
	

}
