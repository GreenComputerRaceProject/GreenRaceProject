package khs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import khs.RaceChatting.MulReceiver;

public class RaceChatting extends JFrame {

	InetAddress addr;
	
	JTextArea ta;
	JTextField tf;
	JButton rank;
	MulReceiver ur;
	
	MulticastSocket mul_socket = null;
	
	
	class MulReceiver extends Thread{
		
		MulticastSocket receiver_socket = null;
		
		@Override
		public void run() {
			try {
				mul_socket = new MulticastSocket();
				addr = InetAddress.getByName("230.0.0.1"); 
				receiver_socket = new MulticastSocket(8888);
				receiver_socket.joinGroup(addr);
				
				while(true) {
									
					byte [] buf = new byte[1024];
					
					DatagramPacket dp = new DatagramPacket(buf, buf.length); 
					
					receiver_socket.receive(dp);

					String msg = new String(buf);
					

					ta.append("아이디"+":"+msg+"\n");
					ta.setCaretPosition(ta.getDocument().getLength());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					receiver_socket.leaveGroup(addr);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				receiver_socket.close();

			}
		}
	}
	
	
	
	public RaceChatting() {
		
		setBounds(50, 50, 400, 500);
		
		ta = new JTextArea();
		ta.setEditable(false);
		add(new JScrollPane(ta),"Center");
		
		tf = new JTextField();
		tf.setEditable(true);
		add(tf,"South");
		
		
		setVisible(true);
		tf.requestFocus();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ur = new MulReceiver();
		ur.start();
		
		tf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					
					try {
						byte [] buf = tf.getText().getBytes();
						
						DatagramPacket data = new DatagramPacket(
								buf, 
								buf.length, 
								addr, 
								8888
								);
						    
							mul_socket.send(data);
							
//							ta.append("나"+tf.getText()+"\n");
							ta.setCaretPosition(ta.getDocument().getLength());
							
							tf.setText("");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
				    }
			}
		});
		
		
}
	
	public static void main(String[] args) {
	 
		new RaceChatting();	
		
	}

}
