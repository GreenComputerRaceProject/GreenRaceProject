package ohs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;

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
	
	// FontClass fc;
	LineBorder lb = new LineBorder(Color.black, 1);
	
	ArrayList<JLabel> num = new ArrayList<JLabel>();
	
	JLabel timer = new JLabel();
	
	JPanel win1, win2, win3, notice;
	JLabel notice1, notice2, notice3;
	JLabel dan_1, dan_2, dan_3, yeon_1, yeon_2, yeon_3, bok_1, bok_2, bok_3;
	JLabel h1, h2, h3, h4, h5;
	
	String firhos, sechos, firnum, secnum;
	


	public CalculateScreen(TCPClient tc, GameScreen3 gameScreen3, RaceProjFrame rpf) {

	// fc.setUIFont(new FontUIResource(new Font("휴먼둥근체",Font.BOLD,25)));
		
		this.tc = tc;
		this.rpf = rpf;

		setBounds(0, 0, 1585, 500);
		setLayout(null);
		setBackground(Color.gray);
		
		timer.setBounds(0, 0, 1585, 50);
		
		notice = new JPanel();
		notice.setBounds(0, 50, 1585, 60);
		notice.setLayout(new GridLayout(1,3));
		
		notice1 = new JLabel("게임 방식");
		notice1.setHorizontalAlignment(JLabel.CENTER);
		notice1.setFont(new Font("휴먼둥근체",Font.BOLD,25));
		notice1.setBorder(lb);
		notice.add(notice1);
		notice2 = new JLabel("우승 말");
		notice2.setHorizontalAlignment(JLabel.CENTER);
		notice2.setFont(new Font("휴먼둥근체",Font.BOLD,25));
		notice2.setBorder(lb);
		notice.add(notice2);
		notice3 = new JLabel("배당 번호");
		notice3.setHorizontalAlignment(JLabel.CENTER);
		notice3.setFont(new Font("휴먼둥근체",Font.BOLD,25));
		notice3.setBorder(lb);
		notice.add(notice3);
		
		add(timer);
		add(notice);
		
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
			
			setBounds(0, 110, 1585, 390);
			setBackground(Color.orange);
			setLayout(null);
			
			for (int i = 0; i < num.size(); i++) {
				if(num.get(i).getText().substring(
				   num.get(i).getText().length()-2, num.get(i).getText().length()).equals("1등")) {
					firhos = num.get(i).getText().substring(2);
					firnum = num.get(i).getText().substring(0,2);
					h1 = new JLabel(num.get(i).getText());
				}
				
				if(num.get(i).getText().substring(
						   num.get(i).getText().length()-2, num.get(i).getText().length()).equals("2등")) {
							sechos = num.get(i).getText().substring(2);
							secnum = num.get(i).getText().substring(0,2);
							h3 = new JLabel(num.get(i).getText());
						}
			}
			
			dan_1 = new JLabel("단식 우승");
			dan_1.setHorizontalAlignment(JLabel.CENTER);
			dan_1.setBorder(lb);
			dan_1.setFont(new Font("휴먼둥근체",Font.BOLD,25));
			dan_2 = new JLabel(firhos);
			dan_2.setHorizontalAlignment(JLabel.CENTER);
			dan_2.setBorder(lb);
			dan_2.setFont(new Font("휴먼둥근체",Font.BOLD,25));
			dan_3 = new JLabel(firnum);
			dan_3.setHorizontalAlignment(JLabel.CENTER);
			dan_3.setBorder(lb);
			dan_3.setFont(new Font("휴먼둥근체",Font.BOLD,25));
			
			yeon_1 = new JLabel("연식 우승");
			yeon_1.setHorizontalAlignment(JLabel.CENTER);
			yeon_1.setBorder(lb);
			yeon_1.setFont(new Font("휴먼둥근체",Font.BOLD,25));
			yeon_2 = new JLabel("<html>"+firhos+"<br />"+sechos);
			yeon_2.setHorizontalAlignment(JLabel.CENTER);
			yeon_2.setBorder(lb);
			yeon_2.setFont(new Font("휴먼둥근체",Font.BOLD,25));
			yeon_3 = new JLabel("<html>"+firnum+"<br />"+secnum);
			yeon_3.setHorizontalAlignment(JLabel.CENTER);
			yeon_3.setBorder(lb);
			yeon_3.setFont(new Font("휴먼둥근체",Font.BOLD,25));
			
			bok_1 = new JLabel("복식 우승");
			bok_1.setHorizontalAlignment(JLabel.CENTER);
			bok_1.setBorder(lb);
			bok_1.setFont(new Font("휴먼둥근체",Font.BOLD,25));
			bok_2 = new JLabel("<html>"+firhos+"<br />"+sechos);
			bok_2.setHorizontalAlignment(JLabel.CENTER);
			bok_2.setFont(new Font("휴먼둥근체",Font.BOLD,25));
			bok_2.setBorder(lb);
			if(Integer.parseInt(firnum.substring(0,1)) > Integer.parseInt(secnum.substring(0,1))) {
				String temp = "";
				String temp2 = "";
				String temp3 = "";
			
				temp = firnum.substring(0,1);
				temp2 = secnum.substring(0,1);
				temp3 = temp2 + "_" + temp + "번";
				bok_3 = new JLabel(temp3);
			} else {
				bok_3 = new JLabel(firnum.substring(0,1)+"_"+secnum);
			}
			bok_3.setHorizontalAlignment(JLabel.CENTER);
			bok_3.setBorder(lb);
			bok_3.setFont(new Font("휴먼둥근체",Font.BOLD,25));
			
			win1 = new JPanel();
			win1.setBounds(0, 0, 1585, 130);
			win1.setBackground(new Color(238, 238, 238));
			win1.setLayout(new GridLayout(1,3));
			win1.add(dan_1);
			win1.add(dan_2);
			win1.add(dan_3);

			win2 = new JPanel();
			win2.setBounds(0, 130, 1585, 130);
			win2.setBackground(new Color(238, 238, 238));
			win2.setLayout(new GridLayout(1,3));
			win2.add(yeon_1);
			win2.add(yeon_2);
			win2.add(yeon_3);
		
			
			win3 = new JPanel();
			win3.setBackground(new Color(238, 238, 238));
			win3.setBounds(0, 260, 1585, 130);
			win3.setLayout(new GridLayout(1,3));
			win3.add(bok_1);
			win3.add(bok_2);
			win3.add(bok_3);
		;

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
					timer.setHorizontalAlignment(JLabel.CENTER);
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
