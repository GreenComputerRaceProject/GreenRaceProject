package ohs;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ocy.TCPClient;

public class WaitingScreen extends JPanel{

	TCPClient tc;
	RaceProjFrame rpf;

	WaitingScreen waitingScreen = this;
	
	JLabel notice;
	JLabel imglabel;
	ImageIcon img1 = new ImageIcon("img/bowimg.jpg");

	public WaitingScreen(TCPClient tc, RaceProjFrame rpf) {

		this.tc = tc;

		setBounds(0, 0, 1585, 500); 
		setLayout(null);
		setOpaque(true);
		setBackground(Color.gray);
		
		imglabel = new JLabel(img1);
		imglabel.setBounds(0, 0, 1585, 500);
		
		notice = new JLabel("경기가 진행중입니다 잠시만 기다려주세요....");
		notice.setBounds(0, 450, 1585, 50);
		notice.setHorizontalAlignment(JLabel.CENTER);
		notice.setFont(new Font("휴먼둥근체", Font.BOLD, 32));
		
		imglabel.add(notice);
		add(imglabel);

		setVisible(true);
	}

}
