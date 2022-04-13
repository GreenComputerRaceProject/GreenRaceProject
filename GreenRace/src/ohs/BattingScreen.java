package ohs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ocy.TCPClient;

public class BattingScreen extends JPanel{
	
	TCPClient tc;
	
	RaceProjFrame rpf;
	
	ArrayList<HorseClass2> entry2;
	BattingScreen battingScreen = this;
	
	JPanel info;
	
	JLabel info1, info2, info3, info4, info5, info6, info7, info8, info9, info10, info11;
	JLabel timer = new JLabel();
	
	RandomEntry re;
	
	int time;


	public BattingScreen(TCPClient tc, RaceProjFrame rpf) {
		this.tc = tc;
		this.rpf = rpf;
		
		setBounds(0, 70, 1585, 500);
		setLayout(null);
		
		timer.setBounds(0, 0, 1585, 50);
		
		add(timer);
		getEntry();
		
		info = new JPanel();
		info.setBounds(0, 50, 1585, 30);
		info.setLayout(new GridLayout(1, 11));
		info.setBackground(Color.orange);
		
		info1 = new JLabel("경주마 이름");
		info2 = new JLabel("경주마 타입");
		info3 = new JLabel("속도");
		info4 = new JLabel("초속");
		info5 = new JLabel("후속");
		info6 = new JLabel("스태미나");
		info7 = new JLabel("성별");
		info8 = new JLabel("나이");
		info9 = new JLabel("무게");
		info10 = new JLabel("컨디션");
		info11 = new JLabel("최근순위");
		
		info.add(info1);
		info.add(info2);
		info.add(info3);
		info.add(info4);
		info.add(info5);
		info.add(info6);
		info.add(info7);
		info.add(info8);
		info.add(info9);
		info.add(info10);
		info.add(info11);
	
		add(info);
		//add(new showEntry());
		
		setVisible(true);
		setOpaque(true);
		setBackground(Color.gray);
		
		getTime();
	}
	
	public void getTime() {
		tc.get_time(this);
	}
	
	public void goTimer(int time) {
		this.time = time;
		System.out.println("남은 시간 : " + time);
		
		new battingTimer().start();
	}
	
	public void getEntry() {
		tc.get_entry(this);
	}
	
	public void setEntry(ArrayList<HorseClass2> entry) {
		System.out.println("드디어 받아왔다! ");
		System.out.println(entry);
		entry2 = entry;
		add(new showEntry());
	}
	
	class showEntry extends JPanel{
		
		
		
		JLabel hname, type, speed, firstspeed, lastspeed, stamina, gender, year,
			   weight, state, recentrecord;
		
		
		public showEntry() {
//			RandomEntry re = new RandomEntry();
//			re.shuffle();
//			entry2 = re.entry;
			System.out.println("찍힘?");
			setBounds(0, 80, 1585, 420);
			setBackground(Color.yellow);
			setLayout(new GridLayout(8,11));
			
			for (int i = 0; i < 8; i++) {
				 hname = new JLabel(entry2.get(i).hname);
				 type = new JLabel(entry2.get(i).type);
				 speed = new JLabel(Double.toString(entry2.get(i).speed));
				 firstspeed = new JLabel(Double.toString(entry2.get(i).firstspeed));
				 lastspeed = new JLabel(Double.toString(entry2.get(i).lastspeed));
				 stamina = new JLabel(Double.toString(entry2.get(i).stamina));
				 gender = new JLabel(Boolean.toString(entry2.get(i).gender));
				 year = new JLabel(Integer.toString(entry2.get(i).year));
				 weight = new JLabel(Double.toString(entry2.get(i).weight));
				 state = new JLabel(Double.toString(entry2.get(i).state));
				 recentrecord = new JLabel(entry2.get(i).recentrecord);
				 
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
		battingScreen.removeAll();
		battingScreen.repaint();
		GameScreen3 gs = new GameScreen3(tc, rpf, battingScreen); 
		add(gs);
		repaint();
	
		
	}
		
}
	
}
