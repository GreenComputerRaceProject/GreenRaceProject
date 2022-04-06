package ohs;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JLabel;

class TCPSingleEntrySender extends Thread {

	ObjectOutputStream oos;
	public TCPSingleEntrySender(Socket soc) {
		try {
			oos = new ObjectOutputStream(soc.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@Override
	public void run() {

		try {
			while(oos != null) {

				//	oos.writeObject(entry);
			}

		} catch (Exception e) {

		}
	}

}




class TCPSingleEntry extends Thread{

	ObjectOutputStream oos;
	ObjectInputStream ois;
	ArrayList<HorseClass> entry;

	public TCPSingleEntry(Socket soc) {
	
		try {
			oos = new ObjectOutputStream(soc.getOutputStream());
			ois = new ObjectInputStream(soc.getInputStream());

			entry = new ArrayList<HorseClass>();
		
			


		} catch (IOException e) {

			e.printStackTrace();
		}


	}

	@Override
	public void run() {


		while(true) {	
			try {

				for (int i = 0; i < 8; i++) {
					entry.remove(0);
				} // 엔트리 정보 초기화

				Class.forName("org.mariadb.jdbc.Driver");

				Connection con = DriverManager.getConnection(
						"jdbc:mariadb://localhost:3306/race_db",
						"root",
						"123456"
						);

				Statement stmt = con.createStatement();

				ResultSet rs = stmt.executeQuery("select * from horse order by rand() limit 8");

				while(rs.next()) { // db 데이터 가져오는 반복문

					entry.add(new HorseClass(rs.getString("hid"),rs.getString("hname"), // 엔트리 리스트에 말 정보넣기
							rs.getString("speed"), rs.getString("firstspeed"), rs.getString("lastspeed"),
							rs.getString("stamina"), rs.getString("state")));      

				}
				stmt.close();
				con.close();
				
				while(oos != null) {
					oos.writeObject(entry);
				}
				
				for (HorseClass horseClass : entry) {
					System.out.println(horseClass);
				}
				
				
				
			} catch (Exception e) {

			} 

			try {
				System.out.println("정부 투척");
				Thread.sleep(30000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

}

public class EntryServerMain {

	public static void main(String[] args) {

		ServerSocket server;
		try {

			server = new ServerSocket(8888);

			while(true) {
				Socket client = server.accept();
				System.out.println("서버(왈) :" + client.getInetAddress() + " 접속");

				new TCPSingleEntry(client).start();
			
			}



		} catch (Exception e) {

			e.printStackTrace();
		}



	}

}
