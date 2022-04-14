package ocy;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import ohs.RaceProjFrame;

public class EntryPointMain extends JFrame {
	
	EntryPointMain entryPointMain = this;
	RaceProjFrame frame;
	
	LoginPanel loginPanel = new LoginPanel();
	
	TCPClient tc;
	
	JTextField idField;
	JPasswordField pwField;
	
	class LoginPanel extends JPanel implements ActionListener {
		
		public LoginPanel() {
			setSize(1600, 1000);
			setLayout(null);
			setBackground(new Color(1,88,54));
			
			JLabel titleLabel = new JLabel("달려라 왕바우");
			titleLabel.setForeground(Color.white);
			titleLabel.setBounds(650, 200, 300, 50);
			titleLabel.setFont(new Font("돋움", Font.PLAIN, 50));
			
			idField = new JTextField();
			idField.setBounds(700, 350, 200, 50);
			pwField = new JPasswordField();
			pwField.setBounds(700, 400, 200, 50);
			
			JButton loginButton = new JButton("로그인");
			loginButton.setBounds(725, 450, 150, 75);
			loginButton.addActionListener(this);
			
			JButton signUpButton = new JButton("회원가입");
			signUpButton.setBounds(725, 600, 150, 75);
			signUpButton.addActionListener(event -> {
				new SignUp(tc);
			});
			
			JButton idFineButton = new JButton("ID 찾기");
			idFineButton.setBounds(650, 675, 150, 75);
			idFineButton.addActionListener(event -> {
				// 아이디찾기 프레임 생성
				new Find(tc, "ID 찾기");
			});
			
			JButton pwFineButton = new JButton("PW 재설정");
			pwFineButton.setBounds(800, 675, 150, 75);
			pwFineButton.addActionListener(event -> {
				// 비번찾기 프레임 생성
				new Find(tc, "PW 재설정");
			});
			
			add(titleLabel);
			add(idField);
			add(pwField);
			add(loginButton);
			add(signUpButton);
			add(idFineButton);
			add(pwFineButton);
			
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			tc.requestLogIn(this, idField.getText(), pwField.getText());
		}
		
		public void notice(String response) {
			if(response.equals("ID_NOT_EXIST")) {
				JOptionPane.showMessageDialog(null, "아이디가 존재하지 않습니다.", "로그인 실패", JOptionPane.PLAIN_MESSAGE);
				idField.setText("");
				pwField.setText("");
			} else if(response.equals("PW_NOT_CORRECT")) {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "로그인 실패", JOptionPane.PLAIN_MESSAGE);
				pwField.setText("");
			} else if(response.equals("COMPLETE")) {
				tc.requestUserInfo(frame, idField.getText());
				JOptionPane.showMessageDialog(null, "로그인하셨습니다!", "로그인 성공", JOptionPane.PLAIN_MESSAGE);
				
				entryPointMain.dispose();
				frame = new RaceProjFrame(tc);
				tc.raceProjFrame = frame;
				//new BGM();
			}
		}
	}
	
	public EntryPointMain() {
		
		try {
		    //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		try {
		    tc = new TCPClient();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setSize(1600, 1000);
		setLayout(null);
		
		//화면 중앙에 띄우기
		Dimension frameSize = getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2,
					(windowSize.height - frameSize.height) / 2);
		
		add(loginPanel);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new EntryPointMain();
	}

}
