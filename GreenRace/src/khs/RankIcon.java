package khs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RankIcon extends JButton implements ActionListener{
	
	Test test;
	RaceListMain raceListMain;
	int user_rank = 0;
	
	public RankIcon() {
		
		setBounds(50, 50, 20, 20);
		raceListMain = new RaceListMain();
		user_rank = raceListMain.rank;
		
		switch(user_rank) {
		
		case 1: String img1 = "fff3/cl.png";
			break;
			
		case 2: String img2 = "fff3/g.png";
			break;	
			
		case 3: String img3 = "fff3/gr.png";
			break;
			
		case 4: String img4 = "fff3/new.jpg";
			break;	
			
		case 5: String img5 = "fff3/OIP.jpg";
			break;	
			
		case 6: String img6 = "fff3/p.png";
			break;
			
		case 7: String img7 = "fff3/stop.jpg";
			break;
			
		case 8: String img8 = "fff3/trp.png";
			break;
			
		case 9: String img9 = "fff3/wr.png";
			break;
			
		case 10: String img10 = "fff3/bth.jpg";
			break;
	
	}
		ImageIcon img = new ImageIcon("fff3/OIP.jpg");
		setIcon(img);
		System.out.println("Î≤ÑÌäº ?Éù?Ñ±");
		
		addActionListener(this);
		setVisible(true);
		
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e) { //?Å¥Î¶? ?ãú ?öå?õê?ùò ?†ïÎ≥¥Ï∞Ω ?ùÑ??
		System.out.println("?àÑÎ¶?");
		
		JFrame userInfo = new JFrame();
		userInfo.setBounds(50, 50, 500, 400);
		userInfo.setLayout(null);
		
		
		
		userInfo.setVisible(true);
		userInfo.setResizable(false);
		
//		userInfo.setDefaultCloseOperation(userInfo.EXIT_ON_CLOSE);
		
	}
	

}
