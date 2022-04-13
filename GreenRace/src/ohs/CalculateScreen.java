package ohs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ocy.TCPClient;

public class CalculateScreen extends JPanel{

	CalculateScreen calculateScreen = this;
	GameScreen3 gameScreen3;
	
	TCPClient tc;
	RaceProjFrame rpf;
	
	JLabel timer = new JLabel();
	
	

	public CalculateScreen(TCPClient tc, RaceProjFrame rpf) {
		
		this.tc = tc;
		this.rpf = rpf;
		
		
		setBounds(0, 0, 1585, 500);
		setLayout(null);
		setBackground(Color.gray);
		
		timer.setBounds(0, 0, 1585, 50);
		
		add(timer);
		
		JPanel jp = new odds(gameScreen3);
		add(jp);
		
		setVisible(true);
		
		new calculateTimer().start();
		tc.bet_adjustment(rpf);

	}

	class odds extends JPanel{
		
		
		
		JPanel win1, win2, win3;

		JLabel dan, yun, bok;
		JLabel h1, h2, h3; 
		JLabel odds1, odds2, odds3, odds4;
		
		public odds(GameScreen3 gameScreen3) {
			
			
			setBounds(0, 50, 1585, 450);
			setBackground(Color.orange);
			setLayout(null);
			
			dan = new JLabel("단식 우승");
			yun = new JLabel("연식 우승");
			bok = new JLabel("복식 우승");
			
			
			
			h1 = new JLabel("1등말 이름");
			h2 = new JLabel("1등말 이름 2등말 이름");
			h3 = new JLabel("1등말 이름 2등말 이름");
			

			odds1 = new JLabel("단식 배당률");
			odds2 = new JLabel("연식 1번말 배당률 연식 2번말 배당률");
			odds3 = new JLabel("복식 배당률");
			

			win1 = new JPanel();
			win1.setBounds(0, 0, 1585, 150);
			win1.setBackground(Color.yellow);
			win1.setLayout(new GridLayout(1, 3));
			win1.add(dan);
			win1.add(h1);
			win1.add(odds1);

			win2 = new JPanel();
			win2.setBounds(0, 150, 1585, 150);
			win2.setBackground(Color.orange);
			win2.setLayout(new GridLayout(1,3));
			win2.add(yun);
			win2.add(h2);
			win2.add(odds2);
			

			win3 = new JPanel();
			win3.setBackground(Color.green);
			win3.setBounds(0, 300, 1585, 150);
			win3.setLayout(new GridLayout(1, 3));
			win3.add(bok);
			win3.add(h3);
			win3.add(odds3);

			add(win1);
			add(win2);
			add(win3);
			
			
		}		

	}
	
	class calculateTimer extends Thread{
		
		@Override
		public void run() {
			
			try {
				for (int i = 10; i >= 0; i--) {
					timer.setText(i + "초 후 배팅이 시작됩니다");
					timer.setFont(new Font("휴먼둥근체", Font.BOLD, 32));
					timer.repaint();
					
					if (i == 0) {
						changeScreen3();
					}
						
					
					sleep(1000);
				} 
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
	}
	
	void changeScreen3() {
		calculateScreen.removeAll();
		calculateScreen.repaint();
		JPanel bbs = new BattingScreen(tc, rpf);
		bbs.setBounds(0, 0, 1585, 500);
		add(bbs);
		repaint();
		
	}

}
