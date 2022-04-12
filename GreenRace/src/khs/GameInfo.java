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

import ohs.GameScreenMain;
import ohs.RandomEntry;

public class GameInfo extends JFrame{

	HorseInfo horseInfo;
	RecentlyTenGame recentlyTenGame;
	String track_length, track_con, horse_entry;
	int horse_num;
	JLabel JLtrack_length, JLtrack_con, JLhorse_entry, JLten_game;
	JButton JBhorse_entry, JBten_game;
	ArrayList<String> ALtrack_length = new ArrayList<String>();
	ArrayList<String> ALtrack_con = new ArrayList<String>();
	ArrayList<String> ALhorse_entry= new ArrayList<String>();
	ArrayList<Integer> ALhorse_num= new ArrayList<Integer>();
	RandomEntry randomEntry = new RandomEntry();

	
	public GameInfo() {
		
		gameinfo();
		
		setBounds(100, 50, 800, 450);
		setLayout(null);
		Random r = new Random();
		int a = 0;
		int b = 0;
//		int [] b = new b [ ALhorse_entry.size()];
//		ALhorse_entry.add(gameScreenMain.entrys(1));
		
		
		a = r.nextInt(ALtrack_length.size()); //임의로 랜덤값 줌, 합칠 때 서버가 선택한 값이 들어가야 함
		b = r.nextInt(ALtrack_con.size());
		
		JLtrack_length = new JLabel("트랙 길이:"+ALtrack_length.get(a));
		JLtrack_length.setBounds(10, 0, 200, 20);
		JLtrack_con = new JLabel("트랙 상태:"+ALtrack_con.get(b));
		JLtrack_con.setBounds(10, 20, 200, 20);
		JLten_game = new JLabel("최근 10 경기 등수");
		JLten_game.setBounds(10, 50, 200, 20);
		
		for (int i = 0; i < 10; i++) {
			int d = i;
			JBten_game = new JButton();
			JBten_game.setBounds(50*i, 80, 50, 30);
			add(JBten_game);
			JBten_game.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
//					recentlyTenGame = new RecentlyTenGame(d);
					
				}
			});
		}
		
		
		
		JLhorse_entry = new JLabel("출전마");
		JLhorse_entry.setBounds(10, 110, 200, 30);

//		randomEntry.shuffle(); 서버에서 셔플해야 값이 들어감
		System.out.println("엔트리사이즈:"+randomEntry.entry.size());
		for (int i = 0; i < randomEntry.entry.size(); i++) {
			int c = i;
			
			System.out.println(randomEntry.entry.get(i).hname);
			
			JBhorse_entry = new JButton(randomEntry.entry.get(i).hname);
			JBhorse_entry.setBounds(50*i, 140, 50, 30);
			add(JBhorse_entry);
			JBhorse_entry.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					horseInfo = new HorseInfo(randomEntry.entry.get(c).hname);
				}
			});
		}
		
		
		
		add(JLtrack_length);
		add(JLtrack_con);
		add(JLten_game);
		add(JLhorse_entry);
	
//		add(jlhorse_entry);
		
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
	
	
	void gameinfo() {
	
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			
			Connection con = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3306/race_db",  
					"race",                             	
					"123456"                                
					);
			
			
			Statement stmt = con.createStatement();
			
			
			ResultSet rs1 = stmt.executeQuery("select * from gameInfo");
			ResultSet rs2 = stmt.executeQuery("select * from horse");
		
			while(rs1.next()) {
				track_length = rs1.getString("track_length");
				track_con =rs1.getString("track_con");
				
				ALtrack_length.add(track_length);
				ALtrack_con.add(track_con);
				
			}
			
			while(rs2.next()) {
				horse_entry = rs2.getString("hname");
				ALhorse_entry.add(horse_entry);
				horse_num = rs2.getInt("hid");
				ALhorse_num.add(horse_num);
			}
			
			rs1.close();
			rs2.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
		
	public static void main(String[] args) {
		
		new GameInfo();

	}

}
