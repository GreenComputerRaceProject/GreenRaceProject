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
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MultiCastChatting extends JPanel {

	MulticastSocket sender_ms = null;
	InetAddress sender_addr;
	
	JTextArea ta;
	JTextField tf;
	
	MulReceiver mr;
	
	String user_id;
	String nickname;

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
	
	void getUserNickname() {
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
	
	public MultiCastChatting(String user_id) {
		
		this.user_id = user_id;
		
		setSize(400, 362);
		setBackground(Color.pink);
		setLayout(new BorderLayout());
		
		ta = new JTextArea();
		ta.setEditable(false);
		add(new JScrollPane(ta), BorderLayout.CENTER);
		
		tf = new JTextField();
		add(tf, BorderLayout.SOUTH);
		
		setVisible(true);
		
		getUserNickname();
		connect();
		
		tf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					byte[] buf = (nickname + " : " + tf.getText()).getBytes();
					
					DatagramPacket data = new DatagramPacket(buf, buf.length, sender_addr, Integer.parseInt("8888"));
					
					sender_ms.send(data);
					
					ta.setCaretPosition(ta.getDocument().getLength());
					
					tf.setText("");
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

}
