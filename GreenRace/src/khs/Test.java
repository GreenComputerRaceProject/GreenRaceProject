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
//	ChattingList chattingList;
	RankIcon rankIcon; 
	RaceListMain raceListMain = new RaceListMain();
	String imgIcon = "";
//	JButton user_grade;	// ?? ?­?¬
	JButton my_money;	// λ³΄μ λ¨Έλ
	JButton m_charge;	// κ²μλ¨ΈλμΆ©μ 
	JButton game_info;	// κ²½κΈ°? λ³?
	JButton exit;		// κ²μμ’λ£
	
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
		rankIcon = new RankIcon("?­?¬ λ²νΌ"); //imgIcon 
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
