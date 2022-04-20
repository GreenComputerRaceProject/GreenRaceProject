package ohs;

import java.awt.Color;
import java.awt.Font;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.FontUIResource;

import ocy.TCPClient;

public class GameScreen3 extends JPanel{

	TCPClient tc;
	RaceProjFrame rpf;
	
	GameScreen3 gameScreen3 = this;

	ArrayList<HorseClass2> entry3 = new ArrayList<HorseClass2>();
	BattingScreen battingScreen;
	FontClass fc;
	
	ImageIcon trackicon = new ImageIcon("img/track.jpg");
//	ImageIcon goal = new ImageIcon("img/goalline.png");
	ImageIcon icon1 = new ImageIcon("img/horse1.gif");
	ImageIcon icon2 = new ImageIcon("img/horse2.gif");
	ImageIcon icon3 = new ImageIcon("img/horse3.gif");
	ImageIcon icon4 = new ImageIcon("img/horse4.gif");
	ImageIcon icon5 = new ImageIcon("img/horse5.gif");
	ImageIcon icon6 = new ImageIcon("img/horse6.gif");
	ImageIcon icon7 = new ImageIcon("img/horse7.gif");
	ImageIcon icon8 = new ImageIcon("img/horse8.gif");

	//RandomEntry re = new RandomEntry();
	
	ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>();
	ArrayList<JLabel> hos = new ArrayList<JLabel>();
	ArrayList<Integer> running = new ArrayList<Integer>();
	ArrayList<Integer> grading = new ArrayList<Integer>();
	ArrayList<JLabel> count = new ArrayList<JLabel>();
	Map<Integer, String> grading2 = new HashMap<Integer, String>();

	JLabel track ,goalline, ready, go, counting;
	//	 boolean set = true;
	boolean start = false;

	int [] grade = {1,2,3,4,5,6,7,8,9}; 
	int x = 0 , y = 22;
	int a = 1;
	int max = 0;
	
	JLabel racehorse;
	
	public GameScreen3(TCPClient tc, RaceProjFrame rpf, BattingScreen battingScreen) {
		
		fc.setUIFont(new FontUIResource(new Font("휴먼둥근체",Font.BOLD,14)));
		
		this.tc = tc;
		this.rpf = rpf;

		track = new JLabel(trackicon);
		track.setBounds(0, 0, 1585, 500);
		
		
	//	goalline = new JLabel(goal);
	//	goalline.setBounds(1400, 0, 20, 500);
	//	setVisible(true);	
		
		ready = new JLabel("경기      준비");
		ready.setSize(700, 200);
		ready.setLocation(380, 150);
		ready.setHorizontalAlignment(JLabel.CENTER);
		ready.setFont(new FontUIResource("휴먼둥근체", Font.BOLD, 40));
		
		for (int i = 0; i < 3; i++) {
			counting = new JLabel("" + (i+1));
			count.add(counting);
			count.get(i).setSize(700, 200);
			count.get(i).setLocation(380, 150);
			count.get(i).setHorizontalAlignment(JLabel.CENTER);
			count.get(i).setFont(new Font("휴먼둥근체", Font.BOLD, 40));
			count.get(i).setVisible(false);
		}
		
		go = new JLabel("경기      시작");
		go.setSize(700, 200);
		go.setLocation(380, 150);
		go.setHorizontalAlignment(JLabel.CENTER);
		go.setFont(new FontUIResource("휴먼둥근체", Font.BOLD, 40));
		go.setVisible(false);

		setBounds(0, 0, 1585, 500);
		setBackground(new Color(210, 190, 165));
		setLayout(null);

		entry3 = battingScreen.entry2;
		
		icons.add(icon1);
		icons.add(icon2);
		icons.add(icon3);
		icons.add(icon4);
		icons.add(icon5);
		icons.add(icon6);
		icons.add(icon7);
		icons.add(icon8);
		
	/*	
		for (int i = 1; i < 9; i++) {
			ImageIcon icon = new ImageIcon("img/horse" + i + ".gif");
			icons.add(icon);
		}
	*/

		for (int i = 0; i < 8; i++) {
			JLabel racehorse = new JLabel(icons.get(i));
			racehorse.setText(i+1 + "번 " + entry3.get(i).hname);
			racehorse.setHorizontalTextPosition(JLabel.RIGHT);
			racehorse.setForeground(Color.black);

			hos.add(racehorse); 

			running.add(x);
		}

		/*	for (int i = 0; i < hos.size(); i++) {
			hos.get(i).setSize(120,60);
	        hos.get(i).setLocation(0, 60 * i);
	        add(hos.get(i));
		} */

		for (int i = 0; i < hos.size(); i++) {
			hos.get(i).setSize(160,63);
			hos.get(i).setLocation(0, y+(51*i));
			add(hos.get(i));
		}

	//	add(goalline);
		add(ready);
		add(count.get(0));
		add(count.get(1));
		add(count.get(2));
		add(go);
		add(track);

		repaint();
		new runs();
	}

	class runs extends Thread{

		public runs() {
			start();
		}

		@Override
		public void run() {


			try {
				
				Thread.sleep(1000);
				ready.setVisible(false);
				count.get(2).setVisible(true);
				Thread.sleep(1000);
				count.get(2).setVisible(false);
				count.get(1).setVisible(true);
				Thread.sleep(1000);
				count.get(1).setVisible(false);
				count.get(0).setVisible(true);
				Thread.sleep(1000);
				count.get(0).setVisible(false);
				go.setVisible(true);
				Thread.sleep(500);
				start = true;
				go.setVisible(false);
				System.out.println("경기 시작!");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			while(start) { // 말들이 달리는 반복문


				for (int i = 0; i < hos.size(); i++) {

					if(running.get(i) >= 1335 && running.get(i) < 1430) { // 골인 구간

						running.set(i, 1460);
						hos.get(i).setIcon(null);
						hos.get(i).setLocation(running.get(i), (y+13)+(51*i));
						//   hos.get(i).setFont(f1);
						hos.get(i).setForeground(Color.black);
						hos.get(i).setSize(100,60);
						hos.get(i).setText(hos.get(i).getText() +"-" + a + "등");
						hos.get(i).setHorizontalTextPosition(JLabel.CENTER);

						a++;
					}

					if( running.get(i) > 1200 && running.get(i) < 1430) { // lastspeed 구간
						running.set(i, (int) (running.get(i) - entry3.get(i).speed * entry3.get(i).state));
						running.set(i, (int) (running.get(i) + entry3.get(i).lastspeed * entry3.get(i).state));   
					}

					if (running.get(i) > 700 && running.get(i) < 1430) { // speed 구간
						running.set(i, (int) (running.get(i) - entry3.get(i).firstspeed * entry3.get(i).state));
						running.set(i, (int) (running.get(i) + entry3.get(i).speed * entry3.get(i).state));
					} 

					if(running.get(i) < 1430) { // 시작 -  firstspeed 구간
						running.set(i, (int) (running.get(i) + entry3.get(i).firstspeed * entry3.get(i).state));
						hos.get(i).setLocation((int)(running.get(i)), y+(51*i));
					}
				} 

				/*	
				for (int i = 0; i < hos.size(); i++) { // 레이스 중계
					grading.add(hos.get(i).getX());
					grading2.put(hos.get(i).getX(), hos.get(i).getText());
				}

				Collections.sort(grading, Collections.reverseOrder());
				Object [] mapkey = grading2.keySet().toArray();
				Arrays.sort(mapkey);

				for (Integer nkey : grading2.keySet()) { System.out.print(grading2.get(nkey)
						+ ", "); }

				grading2.clear();

				System.out.println();
				 */


				for (int i = 0; i < hos.size(); i++) { // 8등 말이 들어왔을시 경기종료 반복문
					if(hos.get(i).getText().substring(
							   hos.get(i).getText().length()-2, hos.get(i).getText().length()).equals("8등")) {
						System.out.println("경기종료!");

						try {
							Thread.sleep(3000);
							start = false;
							changeScreen2();
							System.out.println("정산화면 출력");

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

				try {
					Thread.sleep(30);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			} // start 끝
		}
	}// run 끝

	void changeScreen2() {
		gameScreen3.removeAll();
		gameScreen3.repaint();

		JPanel jp = new CalculateScreen(tc, gameScreen3, rpf);

		add(jp);
		repaint();

	}

}


