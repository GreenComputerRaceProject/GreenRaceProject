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
	
	GameInfo gameInfo;
	HorseInfo horseInfo;
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
		
		setSize(1000, 900);
		setLayout(null);
		
//		for (i = 0; i < raceListMain.user_nickname.size(); i++) {
//			
//		}
	
//		if(raceListMain.user_rank.get(i)) {
//			imgIcon = "fff3/"+raceListMain.user_rank.get(i)+".jpg";
//		}
		rankIcon = new RankIcon("랭크 버튼"); //imgIcon 
		rankIcon.setBounds(10, 20, 20, 20);
		add(rankIcon);
		
		
		
		horseInfo = new HorseInfo();
		horseInfo.setBounds(10, 50, 20, 20);
		add(horseInfo);
		
//		gameInfo = new GameInfo();
//		gameInfo.setBounds(10, 80, 20, 20);
//		add(gameInfo);
		
//		System.out.println(raceListMain.user_rank);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		
	}
		
	
	
	public static void main(String[] args) {
		
		new Test();
	
	
	}
}
