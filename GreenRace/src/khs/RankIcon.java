package khs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RankIcon extends JButton implements ActionListener{
	
	Test test;
	RaceListMain raceListMain = new RaceListMain();
//	int user_rank = 0;
	String imgIcon = "";
	ImageIcon icon;
	
//	public RankIcon() {
//		raceListMain = new RaceListMain();
//
//			setBounds(50, 50, 20, 20);
//			
//			
//			icon = new ImageIcon(imgIcon);
//			setIcon(icon);
//			System.out.println("버튼 생성");
//			System.out.println("RankIcon:"+raceListMain.al_rank);
//			addActionListener(this);
//			
//			
//		}
		
		public RankIcon(int a, String imgIcon) {
			
			setBounds(50, 50*a, 20, 20); 
			
//			for (int user_rank : raceListMain.al_rank) {
			
//				System.out.println("유저랭크" +user_rank);
				
				switch(a) {
				
					case 1: imgIcon = "fff3/cl.png";
						break;
						
					case 2: imgIcon = "fff3/g.png";
						break;	
						
					case 3: imgIcon = "fff3/gr.png";
						break;
						
					case 4: imgIcon = "fff3/new.jpg";
						break;	
						
					case 5: imgIcon = "fff3/OIP.jpg";
						break;	
						
					case 6: imgIcon = "fff3/p.png";
						break;
						
					case 7: imgIcon = "fff3/stop.jpg";
						break;
						
					case 8: imgIcon = "fff3/trp.png";
						break;
						
					case 9: imgIcon = "fff3/wr.png";
						break;
						
					case 10: imgIcon = "fff3/bth.jpg";
						break;
						
					default: imgIcon = "fff3/bth.jpg";
						break;
				}
				
				icon = new ImageIcon(imgIcon);
				setIcon(icon);
				System.out.println("버튼 생성");
//			}
			
			addActionListener(this);
		}
	
	

	
	@Override
	public void actionPerformed(ActionEvent e) { //클릭 시 회원의 정보창 띄움
		System.out.println("누름");
		
		JFrame userInfo = new JFrame();
		userInfo.setBounds(50, 50, 500, 400);
		userInfo.setLayout(null);
		
		
		
		userInfo.setVisible(true);
		userInfo.setResizable(false);
		
//		userInfo.setDefaultCloseOperation(userInfo.EXIT_ON_CLOSE);
		
	}
	

}
