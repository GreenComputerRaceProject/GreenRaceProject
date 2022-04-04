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
	
	JButton user_grade;	// ?šŒ?›?˜ ?­?¬
	JButton my_money;	// ë³´ìœ ë¨¸ë‹ˆ
	JButton m_charge;	// ê²Œì„ë¨¸ë‹ˆì¶©ì „
	JButton game_info;	// ê²½ê¸°?ï¿½ï¿½ë³´ì¡°?ï¿½ï¿½
	JButton exit;		// ê²Œì„ì¢…ë£Œ
	
	JPanel jp;
	
	
	
	public Test() {
		super("horserace");
		setSize(1600, 1000);
		setLayout(null);
		
		rankIcon = new RankIcon();
//		user_grade = new JButton("?ï¿½ï¿½?ï¿½ï¿½?ï¿½ï¿½ï¿??(?ï¿½ï¿½?ï¿½ï¿½)");
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
