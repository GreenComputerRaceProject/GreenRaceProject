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

public class HorseInfo extends JFrame {
	
	String horseName, hname, recentrecord,type, gender, year, state, 
		speed, firstspeed, lastspeed, stamina, weight;
	
	JLabel jhname, jrecentrecord, jtype, jgender, jyear, jstate,
		jspeed, jfirstspeed, jlastspeed, jstamina, jweight;
	
	public HorseInfo(String horseName) {
		
		this.horseName = horseName;
		
//		this.horseName = horseName;
		setBounds(100, 100, 400, 400); //말 엔트리 옆에 위치
		setLayout(null);
		
		horseDB();
		
		jhname = new JLabel("이름:"+hname); 
		jhname.setBounds(20,10,200,30);
		jtype = new JLabel("타입:"+type); 
		jtype.setBounds(20,40,200,30); 
		jgender = new JLabel("성별:"+gender);
		jgender.setBounds(20,70,200,30);
		jyear = new JLabel("나이:"+year); 
		jyear.setBounds(20,100,200,30); 
		jstate = new JLabel("컨디션:"+state); 
		jstate.setBounds(20,130,200,30);
		jspeed = new JLabel("평균 속도:"+speed); 
		jspeed.setBounds(20,160,200,30);
		jfirstspeed = new JLabel("초반 속도:"+firstspeed); 
		jfirstspeed.setBounds(20,190,200,30);
		jlastspeed = new JLabel("후반 속도:"+lastspeed); 
		jlastspeed.setBounds(20,220,200,30);
		jstamina = new JLabel("스테미나:"+stamina); 
		jstamina.setBounds(20,250,200,30);
		jweight = new JLabel("무게:"+weight); 
		jweight.setBounds(20,280,200,30);
		jrecentrecord = new JLabel("최근 성적:"+recentrecord); 
		jrecentrecord.setBounds(20,310,200,30);
	
		add(jhname);
		add(jtype);
		add(jgender);
		add(jyear);
		add(jstate);
		add(jspeed);
		add(jfirstspeed);
		add(jlastspeed);
		add(jstamina);
		add(jweight);
		add(jrecentrecord);
		
		
		setVisible(true);
		setResizable(false);
	}
	
	
	void horseDB() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			
			Connection con = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3306/race_db",  
					"race",                             	
					"123456"                                
					);
			
			
			Statement stmt = con.createStatement();
			
			
			ResultSet rs = stmt.executeQuery("select * from horse where hname = '"+horseName+"'"); 
			//여기 말 엔트리의 이름 연결 where hname = '"+horseName+"'"
			
			
			while(rs.next()) { 
				hname = rs.getString("hname");
				recentrecord = rs.getString("recentrecord");
				type = rs.getString("type");
				gender = rs.getString("gender");
				year = rs.getString("year");
				state = rs.getString("state");
				speed = rs.getString("speed");
				firstspeed = rs.getString("firstspeed");
				lastspeed = rs.getString("lastspeed");
				stamina = rs.getString("stamina");
				weight = rs.getString("weight");
				
				
			}
			
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(hname);
	}

}
