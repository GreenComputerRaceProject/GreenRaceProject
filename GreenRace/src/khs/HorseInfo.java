package khs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ohs.GameScreen2;
import ohs.HorseClass2;
import ohs.RandomEntry;

public class HorseInfo extends JFrame {
	
	RandomEntry randomEntry;
	HorseClass2 horseClass2;
	
	JLabel Jhname, Jrecentrecord, Jtype, Jgender, Jyear, Jstate,
		Jspeed, Jfirstspeed, Jlastspeed, Jstamina, Jweight;
	
	public HorseInfo(HorseClass2 horseClass2) { //서버DB에서 값 받아오는 걸로 고쳐야 함
		
//		this.horseName = horseName;
//		horseClass2.hname = horseName;
//		horseDB();

		setBounds(100, 100, 400, 400); //말 엔트리 옆에 위치
		setLayout(null);
		
		
		Jhname = new JLabel("이름:"+horseClass2.hname); 
		Jhname.setBounds(20,10,200,30);
		Jtype = new JLabel("타입:"+horseClass2.type); 
		Jtype.setBounds(20,40,200,30); 
		Jgender = new JLabel("성별:"+horseClass2.gender);
		Jgender.setBounds(20,70,200,30);
		Jyear = new JLabel("나이:"+horseClass2.year); 
		Jyear.setBounds(20,100,200,30); 
		Jstate = new JLabel("컨디션:"+horseClass2.state); 
		Jstate.setBounds(20,130,200,30);
		Jspeed = new JLabel("평균 속도:"+horseClass2.speed); 
		Jspeed.setBounds(20,160,200,30);
		Jfirstspeed = new JLabel("초반 속도:"+horseClass2.firstspeed); 
		Jfirstspeed.setBounds(20,190,200,30);
		Jlastspeed = new JLabel("후반 속도:"+horseClass2.lastspeed); 
		Jlastspeed.setBounds(20,220,200,30);
		Jstamina = new JLabel("스테미나:"+horseClass2.stamina); 
		Jstamina.setBounds(20,250,200,30);
		Jweight = new JLabel("무게:"+horseClass2.weight); 
		Jweight.setBounds(20,280,200,30);
		Jrecentrecord = new JLabel("최근 성적:"+horseClass2.recentrecord); 
		Jrecentrecord.setBounds(20,310,200,30);
	
		add(Jhname);
		add(Jtype);
		add(Jgender);
		add(Jyear);
		add(Jstate);
		add(Jspeed);
		add(Jfirstspeed);
		add(Jlastspeed);
		add(Jstamina);
		add(Jweight);
		add(Jrecentrecord);
		
		
		setVisible(true);
		setResizable(false);
		
	}
	
	
//	void horseDB() {
//		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			
//			
//			Connection con = DriverManager.getConnection(
//					"jdbc:mariadb://localhost:3306/race_db",  
//					"race",                             	
//					"123456"                                
//					);
//			
//			
//			Statement stmt = con.createStatement();
//			
//			
//			ResultSet rs = stmt.executeQuery("select * from horse where hname = '"+horseName+"'"); 
//			//여기 말 엔트리의 이름 연결 where hname = '"+horseName+"'"
//			
//			
//			while(rs.next()) { 
//				hname = rs.getString("hname");
//				recentrecord = rs.getString("recentrecord");
//				type = rs.getString("type");
//				gender = rs.getString("gender");
//				year = rs.getString("year");
//				state = rs.getString("state");
//				speed = rs.getString("speed");
//				firstspeed = rs.getString("firstspeed");
//				lastspeed = rs.getString("lastspeed");
//				stamina = rs.getString("stamina");
//				weight = rs.getString("weight");
//				
//				
//			}
//			
//			rs.close();
//			stmt.close();
//			con.close();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(hname);
//	}

}
