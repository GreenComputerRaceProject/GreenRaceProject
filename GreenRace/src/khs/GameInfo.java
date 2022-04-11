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

public class GameInfo extends JFrame{

	HorseInfo horseInfo;
	String track_length, track_con;  
	String horse_entry;
	int horse_num;
	JLabel jltrack_length, jltrack_con, jlhorse_entry;
	JButton jbhorse_entry;
	ArrayList<String> ALtrack_length = new ArrayList<String>();
	ArrayList<String> ALtrack_con = new ArrayList<String>();
	ArrayList<String> ALhorse_entry= new ArrayList<String>();
	ArrayList<Integer> ALhorse_num= new ArrayList<Integer>();
	
	public GameInfo() {
		
		gameinfo();
		
		setBounds(100, 50, 800, 250);
		setLayout(null);
		Random r = new Random();
		int a = 0;
		int b = 0;
//		int [] b = new b [ ALhorse_entry.size()];
//		ALhorse_entry.add(gameScreenMain.entrys(1));
		
		
		a = r.nextInt(ALtrack_length.size());
		b = r.nextInt(ALtrack_con.size());
		
		jltrack_length = new JLabel("트랙 길이:"+ALtrack_length.get(a));
		jltrack_length.setBounds(10, 0, 200, 50);
		jltrack_con = new JLabel("트랙 상태:"+ALtrack_con.get(b));
		jltrack_con.setBounds(10, 20, 200, 50);
		jlhorse_entry = new JLabel("출전마");
		jlhorse_entry.setBounds(10, 50, 200, 50);
		
		for (int i = 0; i < 8; i++) {
			int c = r.nextInt(ALhorse_entry.size());
//			System.out.println(ALhorse_entry.size());
			System.out.println(ALhorse_entry.get(c));
			
			jbhorse_entry = new JButton(ALhorse_entry.get(c));
			jbhorse_entry.setBounds(50*i, 100, 50, 30);
			add(jbhorse_entry);
			jbhorse_entry.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					horseInfo = new HorseInfo(ALhorse_num.get(c));
					
				}
			});
			
		}
		
		
		
		add(jltrack_length);
		add(jltrack_con);
		add(jlhorse_entry);
		
		setVisible(true);
		setResizable(false);
		
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
