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

public class HorseInfo extends JButton implements ActionListener {
	
	String horseName, hname, recentrecord,type, gender, year, state, 
		speed, firstspeed, lastspeed, stamina, weight;
	
	JLabel jhname, jrecentrecord, jtype, jgender, jyear, jstate,
		jspeed, jfirstspeed, jlastspeed, jstamina, jweight;
	
	public HorseInfo() { //String horseName
		
//		this.horseName = horseName;
		setBounds(100, 100, 50, 50); //말 엔트리 옆에 위치
		
		addActionListener(this);
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
			
			
			ResultSet rs = stmt.executeQuery("select * from horse where hname = '"+horseName+"'");//여기 말 엔트리의 이름 연결 
			
			
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
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JFrame Horse_Info = new JFrame();
		Horse_Info.setBounds(50, 50, 400, 400);
		
		jhname = new JLabel("이름:"+hname); 
		jhname.setBounds(20,20,200,30);
		jtype = new JLabel("타입:"+type); 
		jtype.setBounds(20,50,200,30); 
		jgender = new JLabel("성별:"+gender);
		jgender.setBounds(20,80,200,30);
		jyear = new JLabel("나이:"+year); 
		jyear.setBounds(20,110,200,30); 
		jstate = new JLabel(":"+state); 
		jstate.setBounds(20,140,200,30);
		jspeed = new JLabel("평균 속도:"+speed); 
		jspeed.setBounds(20,170,200,30);
		jfirstspeed = new JLabel("초반 속도:"+firstspeed); 
		jfirstspeed.setBounds(20,200,200,30);
		jlastspeed = new JLabel("후반 속도:"+lastspeed); 
		jlastspeed.setBounds(20,230,200,30);
		jstamina = new JLabel("스테미나:"+stamina); 
		jstamina.setBounds(20,260,200,30);
		jweight = new JLabel("무게:"+weight); 
		jweight.setBounds(20,290,200,30);
		jrecentrecord = new JLabel("최근 성적:"+recentrecord); 
		jrecentrecord.setBounds(20,320,200,30);
	
		Horse_Info.add(jhname);
		Horse_Info.add(jtype);
		Horse_Info.add(jgender);
		Horse_Info.add(jyear);
		Horse_Info.add(jstate);
		Horse_Info.add(jspeed);
		Horse_Info.add(jfirstspeed);
		Horse_Info.add(jlastspeed);
		Horse_Info.add(jstamina);
		Horse_Info.add(jweight);
		Horse_Info.add(jrecentrecord);
		
		
		Horse_Info.setVisible(true);
		Horse_Info.setResizable(false);
	}

}
