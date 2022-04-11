package ohs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ocy.TCPClient;

public class BattingScreen extends JPanel{
	
	TCPClient tc;
	
	RaceProjFrame rpf;
	
	BattingScreen battingScreen = this;
	
	JLabel timer = new JLabel();
	
	int time;

	public BattingScreen(TCPClient tc, RaceProjFrame rpf) {
		this.tc = tc;
		
		setBounds(0, 70, 1585, 500);
		setLayout(null);
		
		timer.setBounds(0, 0, 1585, 50);
		
		add(timer);
		add(new showEntry());
		
		setVisible(true);
		setOpaque(true);
		setBackground(Color.gray);
		
		//new battingTimer().start();
	}
	
	public void getTime() {
		tc.get_time(this);
	}
	
	public void goTimer(int time) {
		this.time = time;
		System.out.println("남은 시간 : " + time);
		
		new battingTimer().start();
	}
	
	class showEntry extends JPanel{
		
		RandomEntry re = new RandomEntry();
		
		JLabel hname, type, speed, firstspeed, lastspeed, stamina, gender, year,
			   weight, state, recentrecord;
		
		
		public showEntry() {
			
			re.shuffle();
			
			setBounds(0, 50, 1585, 450);
			setBackground(Color.yellow);
			setLayout(new GridLayout(8,11));
			
			for (int i = 0; i < 8; i++) {
				 hname = new JLabel(re.entry.get(i).hname);
				 type = new JLabel(re.entry.get(i).type);
				 speed = new JLabel(Double.toString(re.entry.get(i).speed));
				 firstspeed = new JLabel(Double.toString(re.entry.get(i).firstspeed));
				 lastspeed = new JLabel(Double.toString(re.entry.get(i).lastspeed));
				 stamina = new JLabel(Double.toString(re.entry.get(i).stamina));
				 gender = new JLabel(Boolean.toString(re.entry.get(i).gender));
				 year = new JLabel(Integer.toString(re.entry.get(i).year));
				 weight = new JLabel(Double.toString(re.entry.get(i).weight));
				 state = new JLabel(Double.toString(re.entry.get(i).state));
				 recentrecord = new JLabel(re.entry.get(i).recentrecord);
				 
				 add(hname);
				 add(type);
				 add(speed);
				 add(firstspeed);
				 add(lastspeed);
				 add(stamina);
				 add(gender);
				 add(year);
				 add(weight);
				 add(state);
				 add(recentrecord);
			}
		
			setVisible(true);
			
		}
		
	}
	
	class battingTimer extends Thread{
		
		@Override
		public void run() {
			
			try {
				for (int i = time; i >= 0; i--) {
					timer.setText("배팅을 해주세요... 경기시작까지 남은시간 : " + i);
					timer.setFont(new Font("휴먼둥근체", Font.BOLD, 32));
					timer.repaint();
					
					sleep(1000);
				} 
				
				chageScreen1();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
	public void chageScreen1() {
		
		System.out.println("화면 체인지");
		battingScreen.removeAll();
		JPanel gs = new GameScreen3(); 
		battingScreen.add(gs);
		battingScreen.repaint();
		
	}
		
}
	
}
