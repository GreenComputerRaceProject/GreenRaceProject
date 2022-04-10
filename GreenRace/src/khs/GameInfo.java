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

	GameScreenMain gameScreenMain = new GameScreenMain();
	HorseInfo horseInfo;
	String track_length, track_con;  
	String horse_entry;
	int horse_num;
	JLabel jtrack_length, jtrack_con;
	JButton jhorse_entry;
	ArrayList<String> ALtrack_length = new ArrayList<String>();
	ArrayList<String> ALtrack_con = new ArrayList<String>();
	ArrayList<String> ALhorse_entry= new ArrayList<String>();
	ArrayList<Integer> ALhorse_num= new ArrayList<Integer>();
	
	public GameInfo() {
		setBounds(100, 50, 300, 300);
		setLayout(null);
		Random r = new Random();
		int a = 0;
		int i = 0;
//		int [] b = new b [ ALhorse_entry.size()];
//		ALhorse_entry.add(gameScreenMain.entrys(1));
		gameinfo();
		
		
		a = r.nextInt(ALtrack_length.size());
		
		jtrack_length = new JLabel("트랙 길이:"+ALtrack_length.get(a));
		jtrack_length.setBounds(0, 0, 200, 50);
		jtrack_con = new JLabel("트랙 상태:"+ALtrack_con.get(a));
		jtrack_con.setBounds(0, 50, 200, 50);
		
		for (i = 0; i <= ALhorse_entry.size(); i++) {
			int b = i;
			jhorse_entry = new JButton(ALhorse_entry.get(i));
			jhorse_entry.setBounds(1*i, 100, 50, 50);
			jhorse_entry.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					horseInfo = new HorseInfo(ALhorse_num.get(b));
					
				}
			});
			
		}
		
		
		
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
	
//	void horseinfo() {
//		
//	}
	
	
//	public static void main(String[] args) {
//		
//		new GameInfo(); 
//
//	}

}
