package ocy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TCPChat extends JPanel implements ActionListener {

	TCPClient tc;
	
	JTextArea ta;
	JTextField tf;
	
	public TCPChat(TCPClient tc) {
		this.tc = tc;
		tc.connect_chat_panel(this);
		
		setBackground(Color.pink);
		setLayout(new BorderLayout());
		
		ta = new JTextArea();
		ta.setEditable(false);
		add(new JScrollPane(ta), BorderLayout.CENTER);
		
		tf = new JTextField();
		add(tf, BorderLayout.SOUTH);
		
		setVisible(true);
		
		tf.addActionListener(this);
	}
	
	public void showChat(TCPData response) {
		
		ta.append(response.user.nickname + " : " + response.msg + "\n");
		ta.setCaretPosition(ta.getDocument().getLength());
		//tf.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tc.chat(tf.getText());
		tf.setText("");
	}
}
