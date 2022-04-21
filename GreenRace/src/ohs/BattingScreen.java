package ohs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.FontUIResource;

import ocy.TCPClient;

public class BattingScreen extends JPanel{

	TCPClient tc;
	RaceProjFrame rpf;

	ArrayList<HorseClass2> entry2;
	BattingScreen battingScreen = this;
	// FontClass fc;
	
	ImageIcon bgimg = new ImageIcon("img/background.png");
	
	LineBorder lb = new LineBorder(Color.black, 1);
	
	JPanel info;
	JLabel info0, info1, info2, info3, info4, info5, info6, info7, info8, info9, info10, info11;
	JLabel timer = new JLabel();
	JLabel bg = new JLabel(bgimg);

	String fsp, sp, lsp, st;

	RandomEntry re;

	int time;


	public BattingScreen(TCPClient tc, RaceProjFrame rpf) {
		this.tc = tc;
		this.rpf = rpf;
		
	//	fc.setUIFont(new FontUIResource(new Font("휴먼둥근체",Font.BOLD,25)));
		
		bg.setBounds(0, 0, 1585, 500);

		setBounds(0, 0, 1585, 500);
		setLayout(null);

		timer.setBounds(0, 0, 1585, 50);

		add(timer);
		getEntry();

		info = new JPanel();
		info.setBounds(0, 50, 1585, 30);
		info.setLayout(new GridLayout(1, 7));
		info.setBackground(Color.white);

		info0 = new JLabel("엔트리 번호");
		info0.setBorder(lb);
		info0.setFont(new Font("휴먼둥근체",Font.BOLD,25));
		info0.setHorizontalAlignment(JLabel.CENTER);
		info1 = new JLabel("이름");
		info1.setBorder(lb);
		info1.setFont(new Font("휴먼둥근체",Font.BOLD,25));
		info1.setHorizontalAlignment(JLabel.CENTER);
		info2 = new JLabel("타입");
		info2.setBorder(lb);
		info2.setFont(new Font("휴먼둥근체",Font.BOLD,25));
		info2.setHorizontalAlignment(JLabel.CENTER);
		info3 = new JLabel("평균속도");
		info3.setBorder(lb);
		info3.setFont(new Font("휴먼둥근체",Font.BOLD,25));
		info3.setHorizontalAlignment(JLabel.CENTER);
		info4 = new JLabel("출발속도");
		info4.setBorder(lb);
		info4.setFont(new Font("휴먼둥근체",Font.BOLD,25));
		info4.setHorizontalAlignment(JLabel.CENTER);
		info5 = new JLabel("막판속도");
		info5.setBorder(lb);
		info5.setFont(new Font("휴먼둥근체",Font.BOLD,25));
		info5.setHorizontalAlignment(JLabel.CENTER);
		//	info6 = new JLabel("스태미나");
		//	info7 = new JLabel("성별");
		//	info8 = new JLabel("나이");
		//	info9 = new JLabel("무게");
		info10 = new JLabel("컨디션");
		info10.setBorder(lb);
		info10.setFont(new Font("휴먼둥근체",Font.BOLD,25));
		info10.setHorizontalAlignment(JLabel.CENTER);
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
	//	add(bg);

		setVisible(true);
		setOpaque(true);
		setBackground(Color.white);

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
		JPanel jp = new showEntry();
		add(jp);
	}

	class showEntry extends JPanel{



		JLabel entrynum ,hname, type, speed, firstspeed, lastspeed, stamina, gender, year,
		weight, state, recentrecord;


		public showEntry() {
			setBounds(0, 80, 1585, 420);
		//	setBorder(new TitledBorder(new LineBorder(Color.black)));
			setLayout(new GridLayout(8,7));
			//setOpaque(true);
			setBackground(new Color(238, 238, 238));

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
				entrynum.setHorizontalAlignment(JLabel.CENTER);
				entrynum.setFont(new Font("휴먼둥근체",Font.BOLD,25));
				hname = new JLabel(entry2.get(i).hname);
				hname.setHorizontalAlignment(JLabel.CENTER);
				hname.setFont(new Font("휴먼둥근체",Font.PLAIN,25));
				type = new JLabel(entry2.get(i).type);
				type.setHorizontalAlignment(JLabel.CENTER);
				type.setFont(new Font("휴먼둥근체",Font.PLAIN,25));
				speed = new JLabel(sp);
				speed.setHorizontalAlignment(JLabel.CENTER);
				speed.setFont(new Font("휴먼둥근체",Font.PLAIN,25));
				firstspeed = new JLabel(fsp);
				firstspeed.setHorizontalAlignment(JLabel.CENTER);
				firstspeed.setFont(new Font("휴먼둥근체",Font.PLAIN,25));
				lastspeed = new JLabel(lsp);
				lastspeed.setHorizontalAlignment(JLabel.CENTER);
				lastspeed.setFont(new Font("휴먼둥근체",Font.PLAIN,25));
				// stamina = new JLabel(Double.toString(entry2.get(i).stamina));
				// gender = new JLabel(Boolean.toString(entry2.get(i).gender));
				// year = new JLabel(Integer.toString(entry2.get(i).year));
				// weight = new JLabel(Double.toString(entry2.get(i).weight));
				//state = new JLabel(Double.toString(entry2.get(i).state));
				// recentrecord = new JLabel(entry2.get(i).recentrecord);
				state = new JLabel(st);
				state.setHorizontalAlignment(JLabel.CENTER);
				state.setFont(new Font("휴먼둥근체",Font.PLAIN,25));
				
				if(i == 0) {
				entrynum.setOpaque(true);
				entrynum.setBackground(new Color(255, 102, 99));
				} else if (i == 1) {
					entrynum.setOpaque(true);
					entrynum.setBackground(new Color(253, 253, 151));
				} else if (i == 2) {
					entrynum.setOpaque(true);
					entrynum.setBackground(new Color(158, 224, 158));
				} else if (i == 3) {
					entrynum.setOpaque(true);
					entrynum.setBackground(new Color(158, 193, 207));
				} else if (i == 4) {
					entrynum.setOpaque(true);
					entrynum.setBackground(new Color(204, 153, 201));
				} else if (i == 5) {
					entrynum.setOpaque(true);
					entrynum.setBackground(new Color(168, 113, 57));
				} else if (i == 6) {
					entrynum.setOpaque(true);
					entrynum.setBackground(new Color(255, 255, 255));
				} else if (i == 7) {
					entrynum.setOpaque(true);
					entrynum.setBackground(new Color(128, 128, 128));
				}
				
				if(speed.getText().equals("빠름")) {
					speed.setForeground(Color.red);
				} else if (speed.getText().equals("보통")) {
					speed.setForeground(Color.black);
				} else if (speed.getText().equals("느림")) {
					speed.setForeground(Color.blue);
				}
				
				if(firstspeed.getText().equals("빠름")) {
					firstspeed.setForeground(Color.red);
				} else if (firstspeed.getText().equals("보통")) {
					firstspeed.setForeground(Color.black);
				} else if (firstspeed.getText().equals("느림")) {
					firstspeed.setForeground(Color.blue);
				}
				
				if(lastspeed.getText().equals("빠름")) {
					lastspeed.setForeground(Color.red);
				} else if (lastspeed.getText().equals("보통")) {
					lastspeed.setForeground(Color.black);
				} else if (lastspeed.getText().equals("느림")) {
					lastspeed.setForeground(Color.blue);
				}
				
				if(state.getText().equals("최고") || state.getText().equals("좋음")) {
					state.setForeground(new Color(0, 153, 0));
				} else if (state.getText().equals("보통")) {
					state.setForeground(Color.black);
				} else {
					state.setForeground(new Color(102, 0, 154));
				}
				
				entrynum.setBorder(lb);
				hname.setBorder(lb);
				type.setBorder(lb);
				speed.setBorder(lb);
				firstspeed.setBorder(lb);
				lastspeed.setBorder(lb);
				state.setBorder(lb);
				
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
