package khs;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JFrame {
	
	ChattingList chattingList;
	RankIcon rankIcon; 
	RaceListMain raceListMain = new RaceListMain();
	String imgIcon = "";
//	JButton user_grade;	// 회원 랭크
	JButton my_money;	// 보유머니
	JButton m_charge;	// 게임머니충전
	JButton game_info;	// 경기정보
	JButton exit;		// 게임종료
	
	JPanel jp;
	int i = 0;
	
	
	public Test() {
		super("horserace");
		setSize(1600, 1000);
		setLayout(null);
		
		for (i = 0; i < raceListMain.user_nickname.size(); i++) {
			imgIcon = "fff3/"+raceListMain.user_rank.get(i)+".jpg";
			
			rankIcon = new RankIcon(i,imgIcon); 
			add(rankIcon);
		}
		chattingList = new ChattingList();
		add(chattingList);
		
		
		System.out.println(raceListMain.user_rank);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		repaint();
		
		
		
	}
		
	
	
	public static void main(String[] args) {
		
		new Test();
	
	
	}
}
