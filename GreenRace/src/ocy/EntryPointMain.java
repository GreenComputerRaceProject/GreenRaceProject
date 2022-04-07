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

import ohs.GameScreenMain;
import ohs.RaceProjFrame;

public class EntryPointMain extends JFrame {
	
	EntryPointMain entryframe = this;
	
	LoginPanel loginPanel = new LoginPanel();
	
	class LoginPanel extends JPanel implements ActionListener {
		
		JTextField idField;
		JPasswordField pwField;
		
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
				new SignUp();
			});
			
			JButton idFineButton = new JButton("ID 찾기");
			idFineButton.setBounds(650, 675, 150, 75);
			idFineButton.addActionListener(event -> {
				// 아이디찾기 프레임 생성
				new Find("ID 찾기");
			});
			
			JButton pwFineButton = new JButton("PW 재설정");
			pwFineButton.setBounds(800, 675, 150, 75);
			pwFineButton.addActionListener(event -> {
				// 비번찾기 프레임 생성
				new Find("PW 재설정");
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
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				
				Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/race_db","race","123456");
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("select * from user");
				
				boolean id_accordence = false;
				boolean pw_accordence = false;
				
				while(rs.next()) { 
					if(rs.getString("id").equals(idField.getText())) {
						id_accordence = true;
						
						if(rs.getString("pw").equals(pwField.getText())) {
							pw_accordence = true;
						}
					}
				}
				
				rs.close();
				stmt.close();
				con.close();
				
				if(!id_accordence) {
					JOptionPane.showMessageDialog(null, "아이디가 존재하지 않습니다.", "로그인 실패", JOptionPane.PLAIN_MESSAGE);
					idField.setText("");
					pwField.setText("");
				} else if(!pw_accordence) {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "로그인 실패", JOptionPane.PLAIN_MESSAGE);
					pwField.setText("");
				} else if(id_accordence && pw_accordence) {
					JOptionPane.showMessageDialog(null, "로그인하셨습니다!", "로그인 성공", JOptionPane.PLAIN_MESSAGE);
					
					entryframe.dispose();
					new RaceProjFrame(idField.getText());
					new BGM();
				}
				
				
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public EntryPointMain() {
		
		try {
		    //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
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
