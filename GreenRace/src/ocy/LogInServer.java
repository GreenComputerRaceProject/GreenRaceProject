package ocy;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class LogInServer extends Thread {
	
	MulticastSocket socket = null;
	MulticastSocket sender_ms = null;
	InetAddress sender_addr;
	
	String client_addr;
	
	@Override
	public void run() {
		
		try {
			socket = new MulticastSocket(Integer.parseInt("8888"));
			
			socket.joinGroup(sender_addr);
			
			while(true) {
				
				byte[] buf = new byte[1024];
				DatagramPacket data = new DatagramPacket(buf, buf.length);
				socket.receive(data);
				
				String[] orders = new String(buf).split(",");
				
				if(orders[0].equals("가입")) {
					
				} else if(orders[0].equals("로그인")) {
					responseLogIn(orders[1], orders[2], orders[3]);
				} else if(orders[0].equals("ID찾기")) {
					
				} else if(orders[0].equals("PW찾기")) {
					
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	void connect() {
		try {
			sender_ms = new MulticastSocket(Integer.parseInt("8888"));
			sender_addr = InetAddress.getByName("230.0.0.3");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	void responseLogIn(String client_addr, String id, String pw) {
		try {
//			Class.forName("org.mariadb.jdbc.Driver");
//			
//			Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/race_db","race","123456");
//			Statement stmt = con.createStatement();
//			
//			ResultSet rs = stmt.executeQuery("select * from user");
//			
//			boolean id_accordence = false;
//			boolean pw_accordence = false;
//			
//			while(rs.next()) { 
//				if(rs.getString("id").equals(id)) {
//					id_accordence = true;
//					
//					if(rs.getString("pw").equals(pw)) {
//						pw_accordence = true;
//					}
//				}
//			}
//			
//			rs.close();
//			stmt.close();
//			con.close();
//			
//			if(!id_accordence) {
//				
//			} else if(!pw_accordence) {
//				
//			} else if(id_accordence && pw_accordence) {
//				
//			}
			
			System.out.println("됏나?");
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public LogInServer() {
		connect();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new LogInServer().start();
	}

}
