package ocy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ohs.RaceProjFrame;

public class MultiServer extends JPanel {

	RaceProjFrame frame;
	MulticastSocket sender_ms = null;
	InetAddress sender_addr;
	
	JTextArea ta;
	JTextField tf;
	
	MulReceiver mr;
	
	String user_id;
	public String nickname;
	ArrayList<String> currentUserList = new ArrayList<String>();

	class MulReceiver extends Thread {
		
		MulticastSocket socket = null;
		
		@Override
		public void run() {
			
			try {
				socket = new MulticastSocket(Integer.parseInt("8888"));
				socket.joinGroup(sender_addr);
				
				while(true) {
					
					byte[] buf = new byte[1024];
					DatagramPacket data = new DatagramPacket(buf, buf.length);
					socket.receive(data);
					
					String msg = new String(buf);
					
					ta.append(msg + "\n");
					ta.setCaretPosition(ta.getDocument().getLength());
					
					// 누군가 입장,퇴장 했을때 if문 걸어서 접속자 리스트 갱신해줘야함
					
					if(msg.substring(0, 2).equals("안내")) {
						reSelectCurrentUser();
					}
					
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	void connect() {
		try {
			sender_ms = new MulticastSocket(Integer.parseInt("8888"));
			sender_addr = InetAddress.getByName("230.0.0.1");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		mr = new MulReceiver();
		mr.start();
		
		tf.setEditable(true);
		tf.requestFocus();
	}
	
	void getUserInfo() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/race_db","race","123456");
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("select nickname from user where id = '"+user_id+"'");
			
			while(rs.next()) {
				nickname = rs.getString("nickname");
			}
			
			rs.close();
			stmt.close();
			con.close();
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	void currentUserInsert() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/race_db","race","123456");
			Statement stmt = con.createStatement();
			
			String sql = "insert into currentUser() values ( '"+nickname+"' )";
			
			stmt.executeUpdate(sql);
			
			stmt.close();
			con.close();
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "이미 접속중인 유저입니다.", "서버 접속 실패", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
	}
	
	void currentUserDelete() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/race_db","race","123456");
			Statement stmt = con.createStatement();
			
			String sql = "delete from currentUser where nickname = '"+nickname+"'";
			
			stmt.executeUpdate(sql);
			
			stmt.close();
			con.close();
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
	
	void reSelectCurrentUser() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/race_db","race","123456");
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("select nickname from currentUser");
			
			while(rs.next()) {
				currentUserList.add(rs.getString("nickname"));
			}
			
			rs.close();
			stmt.close();
			con.close();
			
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		
		//frame.user_list.add(new JLabel(currentUserList.get(1)));
		
		for (String currentUser : currentUserList) {
			frame.user_list.add(new JLabel(currentUser));
		}
		
		frame.user_list.repaint();
	}
	
	public void send(byte[] buf) {
		try {
			DatagramPacket data = new DatagramPacket(buf, buf.length, sender_addr, Integer.parseInt("8888"));
			sender_ms.send(data);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void user_entrance() {
		this.send(("안내 : " + nickname + "님이 입장하셨습니다.").getBytes());
		taPosition();
	}
	
	public void user_exit() {
		this.send(("안내 : " + nickname + "님이 퇴장하셨습니다.").getBytes());
		taPosition();
		
		currentUserDelete();
		
		sender_ms.close();
		sender_ms = null;
		
		try {
			mr.socket.leaveGroup(sender_addr);
			mr.stop();
			mr.socket.close();
			mr.socket = null;
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.exit(0);
	}
	
	public void taPosition() {
		ta.setCaretPosition(ta.getDocument().getLength());
		tf.setText("");
	}
	
	public MultiServer(RaceProjFrame frame, String user_id) {
		
		this.frame = frame;
		this.user_id = user_id;
		
		//setSize(400, 362);
		setBackground(Color.pink);
		setLayout(new BorderLayout());
		
		ta = new JTextArea();
		ta.setEditable(false);
		add(new JScrollPane(ta), BorderLayout.CENTER);
		
		tf = new JTextField();
		add(tf, BorderLayout.SOUTH);
		
		setVisible(true);
		
		getUserInfo();
		connect();
		currentUserInsert();
		
		tf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				send((nickname + " : " + tf.getText()).getBytes());
				taPosition();
			}
		});
	}

}
