package ohs;

import java.awt.Color;
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

import ocy.TCPClient;

public class GameScreen3 extends JPanel{

	TCPClient tc;
	RaceProjFrame rpf;
	
	GameScreen3 gameScreen3 = this;

	ArrayList<HorseClass2> entry3 = new ArrayList<HorseClass2>();
	BattingScreen battingScreen;

	ImageIcon goal = new ImageIcon("img/goalline.png");
	ImageIcon icon1 = new ImageIcon("img/horse1.gif");
	ImageIcon icon2 = new ImageIcon("img/horse2.gif");
	ImageIcon icon3 = new ImageIcon("img/horse3.gif");
	ImageIcon icon4 = new ImageIcon("img/horse4.gif");
	ImageIcon icon5 = new ImageIcon("img/horse5.gif");
	ImageIcon icon6 = new ImageIcon("img/horse6.gif");
	ImageIcon icon7 = new ImageIcon("img/horse7.gif");
	ImageIcon icon8 = new ImageIcon("img/horse8.gif");

	RandomEntry re = new RandomEntry();
	
	ArrayList<ImageIcon> icons = new ArrayList<ImageIcon>();
	ArrayList<JLabel> hos = new ArrayList<JLabel>();
	ArrayList<Integer> running = new ArrayList<Integer>();
	ArrayList<Integer> grading = new ArrayList<Integer>();
	Map<Integer, String> grading2 = new HashMap<Integer, String>();

	JLabel goalline;
	//	 boolean set = true;
	boolean start = false;

	int [] grade = {1,2,3,4,5,6,7,8,9}; 
	int x = 0;
	int a = 1;
	int max = 0;



	JLabel racehorse;

	public GameScreen3(TCPClient tc, RaceProjFrame rpf, BattingScreen battingScreen) {

		this.tc = tc;
		this.rpf = rpf;

		goalline = new JLabel(goal);
		goalline.setBounds(1400, 0, 20, 500);
		setVisible(true);	

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
			hos.get(i).setSize(160,60);
			hos.get(i).setLocation(0, 60 * i);
			add(hos.get(i));
		}

		add(goalline);

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
				Thread.sleep(3000);
				start = true;
				System.out.println("경기 시작!");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			while(start) { // 말들이 달리는 반복문


				for (int i = 0; i < hos.size(); i++) {

					if(running.get(i) >= 1335 && running.get(i) < 1430) { // 골인 구간

						running.set(i, 1460);
						hos.get(i).setIcon(null);
						hos.get(i).setLocation(running.get(i), 60 * i);
						//   hos.get(i).setFont(f1);
						hos.get(i).setForeground(Color.black);
						hos.get(i).setText(hos.get(i).getText() +"-" + a + "등");
						hos.get(i).setHorizontalTextPosition(JLabel.CENTER);

						a++;
					}

					if( running.get(i) > 1200 && running.get(i) < 1430) { // lastspeed 구간
						running.set(i, (int) (running.get(i) - entry3.get(i).speed));
						running.set(i, (int) (running.get(i) + entry3.get(i).lastspeed));   
					}

					if (running.get(i) > 700 && running.get(i) < 1430) { // speed 구간
						running.set(i, (int) (running.get(i) - entry3.get(i).firstspeed));
						running.set(i, (int) (running.get(i) + entry3.get(i).speed));
					} 

					if(running.get(i) < 1430) { // 시작 -  firstspeed 구간
						running.set(i, (int) (running.get(i) + entry3.get(i).firstspeed));
						hos.get(i).setLocation((int)(running.get(i)), 60 * i);
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


