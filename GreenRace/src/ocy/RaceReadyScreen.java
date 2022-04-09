package ocy;

import java.awt.Color;
import java.awt.Font;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ohs.GameScreen2;
import ohs.RaceProjFrame;
import ohs.ServerGameScreen;

public class RaceReadyScreen extends JPanel {
	
	RaceProjFrame rpf;
	
	JLabel jl = new JLabel();
	
	class ReadyTimer extends Thread {
		
		RaceReadyScreen rrs;
		
		public ReadyTimer(RaceReadyScreen rrs) {
			this.rrs = rrs;
		}
		
		@Override
		public void run() {
			try {
				for (int i = 10; i >= 0; i--) {
					jl.setText("배팅을 해주세요... 경기시작까지 남은시간 : " + i);
					jl.setFont(new Font("휴먼둥근체", Font.BOLD, 32));
					jl.repaint();
					sleep(1000);
				}
				
				rrs.setVisible(false);
				
				rpf.gsm = new GameScreen2();
				rpf.add(rpf.gsm);
				rpf.repaint();
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public RaceReadyScreen(RaceProjFrame rpf) {
		
		this.rpf = rpf;
		
		setBounds(0, 70, 1585, 500);
		setBackground(Color.white);
		setLayout(null);
		
		JPanel info = new JPanel();
		info.setBounds(0, 50, 1585, 450);
		info.setBackground(Color.red);
		
		
		add(info);
		add(jl);
		
		new ReadyTimer(this).start();
	}
}
