package ocy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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

import ohs.EntryInfoSender;
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
					
					if(msg.substring(0, 2).equals("입장")) {
						reSelectCurrentUser();
					} else if(msg.substring(0, 2).equals("퇴장")) {
						
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
	
	void currentUserInsert(String userNickname) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/race_db","race","123456");
			Statement stmt = con.createStatement();
			
			String sql = "insert into currentUser() values ( '"+userNickname+"' )";
			
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
	
	void currentUserDelete(String userNickname) {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/race_db","race","123456");
			Statement stmt = con.createStatement();
			
			String sql = "delete from currentUser where nickname = '"+userNickname+"'";
			
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
			
			currentUserList.clear();
			
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
		
		frame.user_list.removeAll();
		
		for (String currentUser : currentUserList) {
			JPanel jp = new JPanel();
			jp.setPreferredSize(new Dimension(380, 35));
			jp.setBackground(Color.red);
			
			JLabel jl = new JLabel(currentUser);
			jl.setBackground(Color.pink);
			jl.setOpaque(true);
			jp.add(jl);
			
			JButton jb = new JButton("인포");// RankIcon으로 바꿔야 함
			jp.add(jb);
			
			frame.user_list.add(jp);
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
		this.send(("입장 : " + nickname + "님이 입장하셨습니다.").getBytes());
		taPosition();
		
		currentUserInsert(nickname);
	}
	
	public void user_exit() {
		this.send(("퇴장 : " + nickname + "님이 퇴장하셨습니다.").getBytes());
		taPosition();
		
		currentUserDelete(nickname);
		
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
		
		tf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				send((nickname + " : " + tf.getText()).getBytes());
				taPosition();
			}
		});
	}

}
