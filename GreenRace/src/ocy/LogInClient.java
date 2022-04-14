package ocy;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class LogInClient extends Thread{

	MulticastSocket socket = null;
	MulticastSocket sender_ms = null;
	InetAddress sender_addr;
	
	InetAddress local = null;
	
	@Override
	public void run() {
		
		try {
			socket = new MulticastSocket(8888);
			
			socket.joinGroup(sender_addr);
			
			while(true) {
				
				byte[] buf = new byte[1024];
				DatagramPacket data = new DatagramPacket(buf, buf.length);
				socket.receive(data);
				
				String[] responses = new String(buf).split(",");
				
				if(responses[0].equals(local.getHostAddress())) {
					
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	void connect() {
		try {
			sender_ms = new MulticastSocket(8888);
			sender_addr = InetAddress.getByName("230.0.0.3");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	void logIn(String id, String pw) {
		send(("로그인" + "," + local.getHostAddress() + "," + id + "," + pw).getBytes());
	}
	
	public void send(byte[] buf) {
		try {
			DatagramPacket data = new DatagramPacket(buf, buf.length, sender_addr, 8888);
			sender_ms.send(data);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public LogInClient() {
		try {
			local = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		connect();
		start();
	}
}
