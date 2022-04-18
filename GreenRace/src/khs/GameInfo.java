package khs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.concurrent.Flow;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ocy.TCPClient;
import ohs.CalculateScreen;

public class GameInfo extends JFrame{

	TCPClient tc;
	JPanel jp;

	public GameInfo(TCPClient tc) {
		
		this.tc = tc;
		
		setBounds(800, 200, 500, 400);
		setLayout(new BorderLayout());
		
		jp = new JPanel();
		jp.setPreferredSize(new Dimension(480,400));
		jp.setBackground(Color.pink);
		jp.setLayout(new FlowLayout(FlowLayout.CENTER));
		
//		JLabel jl = new JLabel("가나다라마바사");
//		jl.setPreferredSize(new Dimension(470,50));
//		jl.setBackground(Color.yellow);
//		jl.setOpaque(true);
//		jp.add(jl);
//		
//		JLabel jl2 = new JLabel();
//		jl2.setPreferredSize(new Dimension(470,50));
//		jl2.setBackground(Color.green);
//		jl2.setOpaque(true);
//		jp.add(jl2);
		
		add(new JScrollPane(jp), BorderLayout.CENTER);
		
		setVisible(true);
		setResizable(false);
		
		tc.get_recent_game(this);
	}
	
	public void showRecentGame(ArrayList<String> recentGame) {
		
		jp.removeAll();
		
		for (String s : recentGame) {
			JLabel jl = new JLabel(s);
			jl.setPreferredSize(new Dimension(470,50));
			jl.setBackground(Color.yellow);
			jl.setOpaque(true);
			jp.add(jl);
		}
		
		jp.revalidate();
		jp.repaint();
	}
}
