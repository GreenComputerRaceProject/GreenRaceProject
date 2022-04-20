package ohs;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JLabel;

public class RandomEntry {
	
	ArrayList<HorseClass2> entry;
	
	public ArrayList<HorseClass2> shuffle () {
		
		entry = new ArrayList<HorseClass2>();
		
		 try {
	         Class.forName("org.mariadb.jdbc.Driver");

	         Connection con = DriverManager.getConnection(
	               "jdbc:mariadb://localhost:3306/race_db",
	               "race",
	               "123456"
	               );

	         Statement stmt = con.createStatement();
	         
	         int res = stmt.executeUpdate("update horse set state = round((0.8 + rand() * (0.4)), 1)");
	         
	         ResultSet rs = stmt.executeQuery("select * from horse order by rand() limit 8");
	         
	         while(rs.next()) {
	        	 
	            entry.add(new HorseClass2(rs.getString("hname"), rs.getString("type"),
	                  rs.getString("speed"), rs.getString("firstspeed"), rs.getString("lastspeed"),
	                  rs.getString("stamina"),rs.getString("gender"), rs.getString("year"), 
	                  rs.getString("weight"), rs.getString("state"), rs.getString("recentrecord")));      

	         
	         }
	         stmt.close();
	         con.close();
	      } catch (Exception e) {

	      }
		 
		 return entry;
	}

}
