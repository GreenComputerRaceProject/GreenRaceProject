package ohs;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestFrameMain extends JFrame {
	
	TestFrameMain testFrameMain = this;
	
	
	public TestFrameMain() {
		
	
		
		setBounds(0, 0, 1600, 1000);
		
		setLayout(null);
		
		
		JPanel jp = new BattingScreen();
		add(jp);
		
		
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	

	public static void main(String[] args) {
		
		new TestFrameMain();

	}

}
