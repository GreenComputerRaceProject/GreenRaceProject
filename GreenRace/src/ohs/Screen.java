package ohs;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ocy.TCPClient;
import ohs.BattingScreen.battingTimer;

public class Screen extends JPanel{
	
	
	TCPClient tc;
	RaceProjFrame rpf;

	Screenproduce screenproduce;
	Screen screen = this;
	
	ImageIcon img1 = new ImageIcon("img/bowimg.jpg");
	
	JLabel imglabel;
	JLabel notice;

	int time;

	public Screen(TCPClient tc, RaceProjFrame rpf) {
		
		this.tc = tc;
		this.rpf = rpf;
		
		setBounds(0, 70, 1585, 500);
		setOpaque(true);
		setBackground(Color.gray);
		setLayout(null);
		
		imglabel = new JLabel(img1);
		imglabel.setBounds(0, 0, 1585, 500);
		
		notice = new JLabel("로딩중 입니다....");
		notice.setBounds(0, 450, 1585, 50);
		notice.setHorizontalAlignment(JLabel.CENTER);
		notice.setFont(new Font("휴먼둥근체", Font.BOLD, 32));
		
		imglabel.add(notice);
		add(imglabel);
		
		setVisible(true);
		
		cheakUser();
		getTime2();
		
		new Screenproduce();
	

	}

	public void getTime2() {
		tc.get_time2(this);
		// 타이머 불린값 펄스 주기
	}
	
	public void goTimer2(int time) {
		this.time = time;

	}
	
	public void cheakUser() {
		tc.chesk_uesr(this);
	
	}

	class Screenproduce extends Thread{
		
		public Screenproduce() {
			start();
		}

		@Override
		public void run() {
			try {
				Thread.sleep(3000);

				if(time > 0) {
					screen.removeAll();
					JPanel jp = new BattingScreen(tc, rpf);
					add(jp);
					
				} else {
					screen.removeAll();
					JPanel jp = new WaitingScreen(tc, rpf);
					add(jp);
					
				}
				
				repaint();
				
				
			} catch (InterruptedException e) {

			}
		}
	}

}


