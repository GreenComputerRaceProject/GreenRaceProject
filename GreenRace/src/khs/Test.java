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
	
	RankIcon rankIcon;
	
	JButton user_grade;	// ?��?��?�� ?��?��
	JButton my_money;	// 보유머니
	JButton m_charge;	// 게임머니충전
	JButton game_info;	// 경기?��보조?��
	JButton exit;		// 게임종료
	
	JPanel jp;
	
	
	
	public Test() {
		super("horserace");
		setSize(1600, 1000);
		setLayout(null);
		
		rankIcon = new RankIcon();
//		user_grade = new JButton("?��?��?���??(?��?��)");
//		user_grade.setSize(320,50);
//		user_grade.setBackground(Color.white);
		
//		jp = new JPanel();
//		jp.setSize(320,100);
	
//		add(user_grade);
//		add(jp);
		add(rankIcon);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		
		new Test();
	
	
	}
}
