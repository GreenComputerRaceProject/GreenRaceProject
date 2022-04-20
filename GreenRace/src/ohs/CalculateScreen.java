package ohs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ocy.BetDTO_Place;
import ocy.BetDTO_Quinella;
import ocy.BetDTO_Single;
import ocy.TCPClient;
import ocy.UserDAO;

public class CalculateScreen extends JPanel{

	CalculateScreen calculateScreen = this;
	GameScreen3 gameScreen3;
	BattingScreen battingScreen;
	TCPClient tc;
	RaceProjFrame rpf;
	UserDAO userDAO;
	
	ArrayList<JLabel> num = new ArrayList<JLabel>();
	
	JLabel timer = new JLabel();
	
	JPanel win1, win2, win3;
	JLabel dan, yun, bok;
	JLabel h1, h2, h3, h4, h5; 
	JLabel odds1, odds2, odds3, odds4;
	


	public CalculateScreen(TCPClient tc, GameScreen3 gameScreen3, RaceProjFrame rpf) {

		
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
		
		repaint();
		
		new calculateTimer().start();
		tc.bet_adjustment(rpf);
		
	
		
	//	tc.bet_adjustment(rpf);

	}

	class odds extends JPanel{
		
		
		
		
		public odds(GameScreen3 gameScreen3) {
			
			num = gameScreen3.hos;
			
			setBounds(0, 50, 1585, 450);
			setBackground(Color.orange);
			setLayout(null);
			
			dan = new JLabel("단식 우승");
			yun = new JLabel("연식 우승");
			bok = new JLabel("복식 우승");
			
			
			for (int i = 0; i < num.size(); i++) {
				if(num.get(i).getText().substring(
				   num.get(i).getText().length()-2, num.get(i).getText().length()).equals("1등")) {
					h1 = new JLabel(num.get(i).getText());
					h2 = new JLabel(num.get(i).getText());
					h4 = new JLabel(num.get(i).getText());
				}
				
				if(num.get(i).getText().substring(
						   num.get(i).getText().length()-2, num.get(i).getText().length()).equals("2등")) {
							h3 = new JLabel(num.get(i).getText());
							h5 = new JLabel(num.get(i).getText());
						}
			}

			/*
			odds1 = new JLabel("2배");
			odds2 = new JLabel("1.5배");
			odds3 = new JLabel("1.5배");
			odds4 = new JLabel("6배");
			*/
			

			win1 = new JPanel();
			win1.setBounds(0, 0, 1585, 150);
			win1.setBackground(Color.yellow);
			win1.setLayout(new GridLayout(1, 2));
			win1.add(dan);
			win1.add(h1);
		//	win1.add(odds1);

			win2 = new JPanel();
			win2.setBounds(0, 150, 1585, 150);
			win2.setBackground(Color.orange);
			win2.setLayout(new GridLayout(1,3));
			win2.add(yun);
			win2.add(h2);
		//	win2.add(odds2);
			win2.add(h3);
		//	win2.add(odds3);
			
			win3 = new JPanel();
			win3.setBackground(Color.green);
			win3.setBounds(0, 300, 1585, 150);
			win3.setLayout(new GridLayout(1, 3));
			win3.add(bok);
			win3.add(h4);
			win3.add(h5);
		//	win3.add(odds4);

			add(win1);
			add(win2);
			add(win3);
			
			tc.update_recent_game(calculateScreen, h1.getText().substring(3), h3.getText().substring(3));
			 
			for (BetDTO_Single bs : tc.bet_list.single) {
				if(bs.getHname().equals(h1.getText().substring(0, 1))) {
					tc.win_list.single.add(bs);
				}
			}
			
			for (BetDTO_Place bs : tc.bet_list.place) {
				if(bs.getHname().equals(h1.getText().substring(0, 1))) {
					tc.win_list.place.add(bs);
				} else if (bs.getHname().equals(h3.getText().substring(0, 1))) {
					tc.win_list.place.add(bs);
				}
			}
			
			for (BetDTO_Quinella bs : tc.bet_list.quinella) {
				if((bs.getHname1().equals(h1.getText().substring(0, 1)) || bs.getHname1().equals(h3.getText().substring(0, 1))) &&
						((bs.getHname2().equals(h1.getText().substring(0, 1)) || bs.getHname2().equals(h3.getText().substring(0, 1))))) {
					tc.win_list.quinella.add(bs);
				}
			}
			
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
					//	changeScreen3();
						startTime();
					}
						
					
					sleep(1000);
				} 
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
	}
	
	public void startTime() {
		tc.start_time(this);
		
		
		
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
