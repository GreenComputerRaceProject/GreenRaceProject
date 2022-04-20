package ohs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

import ocy.TCPClient;

public class BattingScreen extends JPanel{
	
	TCPClient tc;
	RaceProjFrame rpf;
	
	ArrayList<HorseClass2> entry2;
	BattingScreen battingScreen = this;
	FontClass fc;
	
	JPanel info;
	JLabel info0, info1, info2, info3, info4, info5, info6, info7, info8, info9, info10, info11;
	JLabel timer = new JLabel();
	
	String fsp, sp, lsp, st;
	
	RandomEntry re;
	
	int time;


	public BattingScreen(TCPClient tc, RaceProjFrame rpf) {
		this.tc = tc;
		this.rpf = rpf;
		
	//	setUIFont(new FontUIResource(new Font("휴먼둥근체",Font.BOLD,13)));
	fc.setUIFont(new FontUIResource(new Font("휴먼둥근체",Font.BOLD,20)));
	
		setBounds(0, 0, 1585, 500);
		setLayout(null);
		
		timer.setBounds(0, 0, 1585, 50);
		
		add(timer);
		getEntry();
		
		info = new JPanel();
		info.setBounds(0, 50, 1585, 30);
		info.setLayout(new GridLayout(1, 7));
		info.setBackground(Color.orange);
		
		info0 = new JLabel("엔트리 번호");
		info1 = new JLabel("경주마 이름");
		info2 = new JLabel("경주마 타입");
		info3 = new JLabel("속도");
		info4 = new JLabel("초속");
		info5 = new JLabel("후속");
	//	info6 = new JLabel("스태미나");
	//	info7 = new JLabel("성별");
	//	info8 = new JLabel("나이");
	//	info9 = new JLabel("무게");
		info10 = new JLabel("컨디션");
	//	info11 = new JLabel("최근순위");
		
		
		
		info.add(info0);
		info.add(info1);
		info.add(info2);
		info.add(info3);
		info.add(info4);
		info.add(info5);
	//	info.add(info6);
	//	info.add(info7);
	//	info.add(info8);
	//	info.add(info9);
		info.add(info10);
	//	info.add(info11);
	
		add(info);
		
		setVisible(true);
		setOpaque(true);
		setBackground(Color.gray);
		
		rpf.b_single.setEnabled(true);
		rpf.b_yeon.setEnabled(true);
		rpf.b_bok.setEnabled(true);
		
		rpf.cheak = true;
		
		getTime();
		rpf.showBetList();
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
		System.out.println(entry);
		entry2 = entry;
		add(new showEntry());
	}
	
	class showEntry extends JPanel{
		
		
		
		JLabel entrynum ,hname, type, speed, firstspeed, lastspeed, stamina, gender, year,
			   weight, state, recentrecord;
		
		
		public showEntry() {
			setBounds(0, 80, 1585, 420);
			setBackground(Color.yellow);
			setLayout(new GridLayout(8,7));
			
			for (int i = 0; i < 8; i++) {
				
				if(entry2.get(i).firstspeed <= 8) {
					fsp = "느림";
				} else if (entry2.get(i).firstspeed < 11 ) {
					fsp = "보통";
				} else {
					fsp = "빠름";
				}
				
				if(entry2.get(i).speed <= 8) {
					sp = "느림";
				} else if (entry2.get(i).speed < 11 ) {
					sp = "보통";
				} else {
					sp = "빠름";
				}
				
				if(entry2.get(i).lastspeed <= 8) {
					lsp = "느림";
				} else if (entry2.get(i).lastspeed < 11 ) {
					lsp = "보통";
				} else {
					lsp = "빠름";
				}
				
				if(entry2.get(i).state == 1.2) {
					st = "최고";
				} else if(entry2.get(i).state == 1.1) {
					st = "좋음";
				} else if(entry2.get(i).state == 1.0) {
					st = "보통";
				} else if(entry2.get(i).state == 0.9) {
					st = "하락";
				} else if(entry2.get(i).state == 0.8) {
					st = "최악";
				}
				 
				entrynum = new JLabel("" + (i+1));
				 hname = new JLabel(entry2.get(i).hname);
				 type = new JLabel(entry2.get(i).type);
				 speed = new JLabel(sp);
				 firstspeed = new JLabel(fsp);
				 lastspeed = new JLabel(lsp);
				// stamina = new JLabel(Double.toString(entry2.get(i).stamina));
				// gender = new JLabel(Boolean.toString(entry2.get(i).gender));
				// year = new JLabel(Integer.toString(entry2.get(i).year));
				// weight = new JLabel(Double.toString(entry2.get(i).weight));
				 //state = new JLabel(Double.toString(entry2.get(i).state));
				// recentrecord = new JLabel(entry2.get(i).recentrecord);
				 state = new JLabel(st);
				 
				 add(entrynum);
				 add(hname);
				 add(type);
				 add(speed);
				 add(firstspeed);
				 add(lastspeed);
			//	 add(stamina);
			//	 add(gender);
			//	 add(year);
			//	 add(weight);
				 add(state);
			//	 add(recentrecord);
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
					timer.setHorizontalAlignment(JLabel.CENTER);
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
		
		rpf.b_single.setEnabled(false);
		rpf.b_yeon.setEnabled(false);
		rpf.b_bok.setEnabled(false);
		rpf.cheak = false;
		
		GameScreen3 gs = new GameScreen3(tc, rpf, battingScreen); 
		add(gs);
		repaint();
	
		
	}
		
}
	
}
