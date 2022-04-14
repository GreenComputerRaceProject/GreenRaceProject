package ocy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import khs.RankIcon;
import ohs.RaceProjFrame;

public class TCPChat extends JPanel implements ActionListener {

	TCPClient tc;
	RaceProjFrame frame;
	
	JTextArea ta;
	JTextField tf;
	
	RankIcon rankIcon;
	
	public TCPChat(TCPClient tc, RaceProjFrame frame) {
		this.tc = tc;
		this.frame = frame;
		
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
	
	public void entranceChat() {
		tc.entrance_chat(this);
	}
	
	public void showChat(TCPData response) {
		
		ta.append(response.user.nickname + " : " + response.msg + "\n");
		ta.setCaretPosition(ta.getDocument().getLength());
	}
	
	public void currentUserList(TCPData response) {
		frame.user_list.removeAll();
		
		System.out.println(response);
		
		for (String s : response.mems) {
			JPanel jp = new JPanel();
			jp.setPreferredSize(new Dimension(380, 35));
			jp.setBackground(Color.red);
			
			JLabel jl = new JLabel(s);
			jl.setBackground(Color.pink);
			jl.setOpaque(true);
			jp.add(jl);
			
			JButton jb = new JButton("인포");// RankIcon으로 바꿔야 함
			jp.add(jb);
			
			frame.user_list.add(jp);
		}
		
		frame.user_list.revalidate();
		frame.user_list.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		tc.chat(tf.getText());
		tf.setText("");
	}
}
