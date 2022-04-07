package khs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameInfo extends JFrame{

	int track_length;
	String track_con;  
	JLabel jtrack_length;
	JLabel jtrack_con;
	
	
	public GameInfo() {
		setBounds(50, 50, 300, 300);
		setLayout(null);
		
		gameinfo();
		jtrack_length = new JLabel("트랙 길이:"+track_length);
		jtrack_length.setBounds(0, 0, 200, 50);
		jtrack_con = new JLabel("날씨:"+track_con);
		jtrack_con.setBounds(0, 50, 200, 50);
		// 말 엔트리
		
		
		add(jtrack_length);
		add(jtrack_con);
		
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
			
			
			ResultSet rs = stmt.executeQuery("select * from gameInfo");
		
			while(rs.next()) {
				track_length = rs.getInt("track_length");
				track_con =rs.getString("track_con");
			}
			
			rs.close();
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
