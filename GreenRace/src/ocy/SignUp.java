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
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class SignUp extends JFrame {
	
	JFrame signUpFrame = this;
	
	InnerSignUp innerSignup;
	
	JTextField idField, nameField, nicknameField, phoneField, certField;
	JPasswordField pwField, pwField2;
	JTextArea aboutArea;
	JButton signUp, idButton, nicknameButton, phoneButton, certButton;
	
	String certNum = "";
	
	boolean isIdComplete, isNicknameComplete, isPhoneComplete;
	
	class InnerSignUp extends JPanel implements ActionListener {
		
		public InnerSignUp() {
			setSize(400, 750);
			setLayout(null);
			setBackground(Color.pink);
			
			JLabel titleLabel = new JLabel("회원가입");
			titleLabel.setForeground(Color.white);
			titleLabel.setBounds(150, 20, 300, 50);
			titleLabel.setFont(new Font("돋움", Font.PLAIN, 30));
			
			JLabel idFieldLabel = new JLabel("아이디");
			idFieldLabel.setBounds(50, 100, 50, 50);
			idField = new JTextField();
			idField.setBounds(100, 100, 200, 50);
			idButton = new JButton("중복확인");
			idButton.setBounds(300, 100, 80, 50);
			idButton.addActionListener(this);
			
			JLabel pwFieldLabel = new JLabel("비밀번호");
			pwFieldLabel.setBounds(40, 150, 50, 50);
			pwField = new JPasswordField();
			pwField.setBounds(100, 150, 200, 50);
			
			JLabel pwFieldLabel2 = new JLabel("<HTML>비밀번호<BR>확인");
			pwFieldLabel2.setBounds(40, 200, 100, 50);
			pwField2 = new JPasswordField();
			pwField2.setBounds(100, 200, 200, 50);
			
			JLabel nameFieldLabel = new JLabel("이름");
			nameFieldLabel.setBounds(60, 250, 50, 50);
			nameField = new JTextField();
			nameField.setBounds(100, 250, 200, 50);
			
			JLabel nicknameFieldLabel = new JLabel("닉네임");
			nicknameFieldLabel.setBounds(50, 300, 50, 50);
			nicknameField = new JTextField();
			nicknameField.setBounds(100, 300, 200, 50);
			nicknameButton = new JButton("중복확인");
			nicknameButton.setBounds(300, 300, 80, 50);
			nicknameButton.addActionListener(this);
			
			JLabel phoneFieldLabel = new JLabel("전화번호");
			phoneFieldLabel.setBounds(40, 350, 50, 50);
			phoneField = new JTextField();
			phoneField.setBounds(100, 350, 200, 50);
			phoneButton = new JButton("코드받기");
			phoneButton.setBounds(300, 350, 80, 50);
			phoneButton.addActionListener(this);
			
			JLabel certFieldLabel = new JLabel("인증번호");
			certFieldLabel.setBounds(40, 400, 50, 50);
			certField = new JTextField();
			certField.setBounds(100, 400, 200, 50);
			certField.setEnabled(false);
			certButton = new JButton("인증하기");
			certButton.setBounds(300, 400, 80, 50);
			certButton.setEnabled(false);
			certButton.addActionListener(this);
			
			JLabel aboutAreaLabel = new JLabel("자기소개");
			aboutAreaLabel.setBounds(40, 475, 50, 50);
			aboutArea = new JTextArea();
			aboutArea.setLineWrap(true);
			JScrollPane scrollPane = new JScrollPane(aboutArea);
			scrollPane.setBounds(100, 475, 200, 150);
			
			signUp = new JButton("회원가입 완료");
			signUp.setBounds(160, 650, 80, 50);
			signUp.addActionListener(this);
			
			add(titleLabel);
			add(idFieldLabel);
			add(idField);
			add(idButton);
			add(pwFieldLabel);
			add(pwField);
			add(pwFieldLabel2);
			add(pwField2);
			add(nameFieldLabel);
			add(nameField);
			add(nicknameFieldLabel);
			add(nicknameField);
			add(nicknameButton);
			add(phoneFieldLabel);
			add(phoneField);
			add(phoneButton);
			add(certFieldLabel);
			add(certField);
			add(certButton);
			add(aboutAreaLabel);
			add(scrollPane);
			add(signUp);
			
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource().equals(signUp)) {
				
				if(idField.getText().isEmpty() || pwField.getText().isEmpty() || pwField2.getText().isEmpty() || 
						nameField.getText().isEmpty() || nicknameField.getText().isEmpty() || phoneField.getText().isEmpty()
						|| certField.getText().isEmpty() || aboutArea.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "빈 내용을 채워주세요", "회원가입 실패", JOptionPane.PLAIN_MESSAGE);
				} else {
					if(isIdComplete && isNicknameComplete && isPhoneComplete) {
						if(pwField.getText().equals(pwField2.getText())) {
							insertUser();
						} else {
							JOptionPane.showMessageDialog(null, "비밀번호를 다시 확인해주세요", "회원가입 실패", JOptionPane.PLAIN_MESSAGE);
							pwField.setText("");
							pwField2.setText("");
						}
					} else if(!isIdComplete) {
						JOptionPane.showMessageDialog(null, "아이디 중복확인을 해주세요", "회원가입 실패", JOptionPane.PLAIN_MESSAGE);
					} else if(!isNicknameComplete) {
						JOptionPane.showMessageDialog(null, "닉네임 중복확인을 해주세요", "회원가입 실패", JOptionPane.PLAIN_MESSAGE);
					} else if(!isPhoneComplete) {
						JOptionPane.showMessageDialog(null, "전화번호 인증을 해주세요", "회원가입 실패", JOptionPane.PLAIN_MESSAGE);
					}
					
				}
			} else if(e.getSource().equals(idButton)) {
				
				if(!idField.getText().isEmpty()) {		
					String pattern = "^[a-zA-Z0-9]{5,12}$";
					boolean regex = Pattern.matches(pattern, idField.getText());
					
					if(regex) {
						id_verification();
					} else {
						JOptionPane.showMessageDialog(null, "아이디는 영문, 숫자로만 이루어진 5 ~ 12자 이하로 작성해주세요", "중복확인 실패", JOptionPane.PLAIN_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "아이디를 입력해주세요", "중복확인 실패", JOptionPane.PLAIN_MESSAGE);
				}
			} else if(e.getSource().equals(nicknameButton)) {
				
				if(!nicknameField.getText().isEmpty()) {
					String pattern = "^[a-zA-Z가-힣]{2,8}$";
					boolean regex = Pattern.matches(pattern, nicknameField.getText());
					
					if(regex) {
						nickname_verification();
					} else {
						JOptionPane.showMessageDialog(null, "닉네임은 영문, 한글로만 이루어진 2 ~ 8자 이하로 작성해주세요", "중복확인 실패", JOptionPane.PLAIN_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "닉네임을 입력해주세요", "중복확인 실패", JOptionPane.PLAIN_MESSAGE);
				}
			} else if(e.getSource().equals(phoneButton)) {
				
				if(!phoneField.getText().isEmpty()) {
					String pattern = "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$";
					boolean regex = Pattern.matches(pattern, phoneField.getText());
					
					if(regex) {
						phone_verification();
					} else {
						JOptionPane.showMessageDialog(null, "전화번호를 다시 입력해주세요(하이픈(-)을 포함하지 말아주세요)", "중복확인 실패", JOptionPane.PLAIN_MESSAGE);
						phoneField.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(null, "전화번호를 입력해주세요", "인증번호 전송 실패", JOptionPane.PLAIN_MESSAGE);
				}
			} else if(e.getSource().equals(certButton)) {
				
				if(certField.getText().equals(certNum) && !certField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "본인인증이 완료되었습니다", "인증 성공", JOptionPane.PLAIN_MESSAGE);
					isPhoneComplete = true;
				} else if(certField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "인증번호를 입력해주세요", "인증 실패", JOptionPane.PLAIN_MESSAGE);
				} else if(!certField.getText().equals(certNum)) {
					JOptionPane.showMessageDialog(null, "인증번호가 틀렸습니다", "인증 실패", JOptionPane.PLAIN_MESSAGE);
				}
			}
			
		}
		
		public void id_verification() {
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				
				Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/race_db","race","123456");
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("select * from user");
				
				boolean id_existence = false;
				
				while(rs.next()) { 
					if(rs.getString("id").equals(idField.getText())) {
						id_existence = true;
					}
				}
				
				rs.close();
				stmt.close();
				con.close();
				
				if(id_existence) {
					JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.", "중복확인 실패", JOptionPane.PLAIN_MESSAGE);
					idField.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.", "중복확인 완료", JOptionPane.PLAIN_MESSAGE);
					isIdComplete = true;
				}
				
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		
		public void nickname_verification() {
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				
				Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/race_db","race","123456");
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("select * from user");
				
				boolean nickname_existence = false;
				
				while(rs.next()) { 
					if(rs.getString("nickname").equals(nicknameField.getText())) {
						nickname_existence = true;
					}
				}
				
				rs.close();
				stmt.close();
				con.close();
				
				if(nickname_existence) {
					JOptionPane.showMessageDialog(null, "이미 존재하는 닉네임입니다.", "중복확인 실패", JOptionPane.PLAIN_MESSAGE);
					nicknameField.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "사용 가능한 닉네임입니다.", "중복확인 완료", JOptionPane.PLAIN_MESSAGE);
					isNicknameComplete = true;
				}
				
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		
		public void phone_verification() {
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				
				Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/race_db","race","123456");
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("select * from user");
				
				boolean phone_existence = false;
				
				while(rs.next()) { 
					if(rs.getString("phone").equals(phoneField.getText())) {
						phone_existence = true;
					}
				}
				
				rs.close();
				stmt.close();
				con.close();
				
				if(phone_existence) {
					JOptionPane.showMessageDialog(null, "이미 가입한 사용자입니다.", "중복확인 실패", JOptionPane.PLAIN_MESSAGE);
					phoneField.setText("");
				} else {
					certNum = new SMSSend(phoneField.getText()).send();
					JOptionPane.showMessageDialog(null, "인증번호를 전송했습니다", "인증번호 전송 성공", JOptionPane.PLAIN_MESSAGE);
					certField.setEnabled(true);
					certButton.setEnabled(true);
				}
				
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		
		public void insertUser() {
			try {
				Class.forName("org.mariadb.jdbc.Driver");
				
				Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/race_db","race","123456");
				Statement stmt = con.createStatement();
				
				int rs = stmt.executeUpdate("INSERT into user(id, pw, name, nickname, phone, money, totgame, win, lose, rank, about) "
						+ "values('"+idField.getText()+"', '"+pwField.getText()+"', '"+nameField.getText()+"','"
						+nicknameField.getText()+"', '"+phoneField.getText()+"', "+10000000+", "+0+", "+0+", "+0+", "+1+", '"+aboutArea.getText()+"')");
				
				JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다", "회원가입 성공", JOptionPane.PLAIN_MESSAGE);
				
				stmt.close();
				con.close();
				
				signUpFrame.dispose();
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
	}
	
	public SignUp() {
		setSize(400, 750);
		setLayout(null);
		
		//화면 중앙에 띄우기
		Dimension frameSize = getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2,
					(windowSize.height - frameSize.height) / 2);
		
		innerSignup = new InnerSignUp();
		add(innerSignup);
		
		setVisible(true);
	}
}
