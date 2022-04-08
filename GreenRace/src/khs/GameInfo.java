package khs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;

import ohs.GameScreenMain;

public class GameInfo extends JFrame{

	GameScreenMain gameScreenMain = new GameScreenMain();
	String track_length;
	String track_con;  
	String horse_entry;  
	JLabel jtrack_length;
	JLabel jtrack_con;
	JLabel jhorse_entry;
	ArrayList<String> ALtrack_length = new ArrayList<String>();
	ArrayList<String> ALtrack_con = new ArrayList<String>();
	ArrayList<Object> ALhorse_entry= new ArrayList<Object>();
	
	public GameInfo() {
		setBounds(100, 50, 300, 300);
		setLayout(null);
		Random r = new Random();
		int a = 0;
		
//		ALhorse_entry.add(gameScreenMain.entrys(1));
		gameinfo();
		
		
		a = r.nextInt(ALtrack_length.size());
		
		jtrack_length = new JLabel("트랙 길이:"+ALtrack_length.get(a));
		jtrack_length.setBounds(0, 0, 200, 50);
		jtrack_con = new JLabel("트랙 상태:"+ALtrack_con.get(a));
		jtrack_con.setBounds(0, 50, 200, 50);
//		jhorse_entry = new JLabel("츨전마 엔트리:"+gameScreenMain.entrys.);
//		jhorse_entry.setBounds(0, 100, 200, 50);
		
		
		
		add(jtrack_length);
		add(jtrack_con);
		
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
//			ResultSet rs2 = stmt.executeQuery("select * from horse");
		
			while(rs1.next()) {
				track_length = rs1.getString("track_length");
				track_con =rs1.getString("track_con");
				
				ALtrack_length.add(track_length);
				ALtrack_con.add(track_con);
				
			}
			
//			while(rs2.next()) {
//				horse_entry = rs2.getString("hname");
//				ALhorse_entry.add(horse_entry);
//			}
			
			rs1.close();
//			rs2.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	
//	void horseinfo() {
//		
//	}
	
	
//	public static void main(String[] args) {
//		
//		new GameInfo(); 
//
//	}

}
