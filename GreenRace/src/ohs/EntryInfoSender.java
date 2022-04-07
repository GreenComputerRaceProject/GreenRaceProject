package ohs;

import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class EntryInfoSender extends Thread implements Serializable {

	ArrayList<String> ety = new ArrayList<String>();
	byte [] buf;

	MulticastSocket ms;
	InetAddress addr;
	//	ArrayList<String> et = new ArrayList();

	public EntryInfoSender() {
	
		start();
	}


	@Override
	public void run() {


		while(true) {	
			try {
				entry();

				Thread.sleep(15000);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void entry() {
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:mariadb://localhost:3306/race_db",
					"root",
					"123456"
					);

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery("select * from horse order by rand() limit 8");

			while(rs.next()) {

				ety.add(rs.getString("hid")); // 8마리 엔트리 저장

			}

			stmt.close();
			con.close();
			
			
			
			/*
			 * for (String str : ety) { // 콘솔 확인용 System.out.print(str + " "); }
			 */
			
			MulticastSocket ms = new MulticastSocket();
			InetAddress addr = InetAddress.getByName("230.0.0.2");
			
			for (int i = 0; i < ety.size(); i++) {
			byte [] buf	= ety.get(i).getBytes();
			
			System.out.println(new String(buf));
			
			DatagramPacket data = new DatagramPacket(
					buf, 
					buf.length,
					addr,
					8888);
			
				ms.send(data);
			}
			
			ms.close();
			
			ety.clear();
			System.out.println();
		
			
	
		} catch (Exception e) { 
			e.printStackTrace();
		}	
	} // 메소드 끝


	public static void main(String[] args) {

		try {
			new EntryInfoSender();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
