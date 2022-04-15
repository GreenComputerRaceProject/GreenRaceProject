package khs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ohs.BattingScreen;
import ohs.CalculateScreen;
import ohs.GameScreenMain;
import ohs.RandomEntry;

public class GameInfo extends JFrame{

	HorseInfo horseInfo;
	CalculateScreen calculateScreen;
	RecentlyTenGame recentlyTenGame;
	JLabel JLten_game;
	JButton JBten_game;

	public GameInfo() {
		
		setBounds(100, 50, 500, 400);
		setLayout(null);
		
		JLten_game = new JLabel("최근 10 경기 등수");
		JLten_game.setBounds(10, 10, 200, 20);
		
		for (int i = calculateScreen.getSize().; i < 10; i++) { //서버의 최근경기가 담긴 변수의 size를 넣어야 함
			int d = i;
			JBten_game = new JButton();
			JBten_game.setBounds(50*i, 80, 50, 30);
			add(JBten_game);
			JBten_game.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					recentlyTenGame = new RecentlyTenGame(d);
					
				}
			});
		}
		
		add(JLten_game);

		
		setVisible(true);
		setResizable(false);
		
	}
	
		
	public static void main(String[] args) {
		
		new GameInfo();

	}

}
