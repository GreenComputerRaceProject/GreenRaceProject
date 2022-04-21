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

public class Find extends JFrame {
	
	TCPClient tc;
	
	JFrame findFrame = this;
	
	InnerFind innerFind;
	
	JTextField firstField, phoneField, certificationField, pwField, pwField2;
	JButton findBt, certificationBt;
	
	String certNum = "";
	
	class InnerFind extends JPanel implements ActionListener {
		
		String mode;
		
		public InnerFind(String mode) {
			setSize(400, 750);
			setLayout(null);
			this.mode = mode;
			
			JLabel titleLabel = new JLabel();
			//titleLabel.setForeground(Color.white);
			titleLabel.setBounds(150, 20, 300, 50);
			titleLabel.setFont(new Font("돋움", Font.PLAIN, 30));
			titleLabel.setText(mode);
			
			JLabel phoneFieldLabel = new JLabel("전화번호");
			phoneFieldLabel.setBounds(40, 150, 50, 50);
			phoneField = new JTextField();
			phoneField.setBounds(100, 150, 200, 50);
			
			firstField = new JTextField();
			firstField.setBounds(100, 100, 200, 50);
			
			if(mode.equals("ID 찾기")) {
				//setBackground(Color.blue);
				
				JLabel nameFieldLabel = new JLabel("이름");
				nameFieldLabel.setBounds(60, 100, 50, 50);
				
				add(nameFieldLabel);
			} else if(mode.equals("PW 재설정")){
				//setBackground(Color.red);
				
				JLabel idFieldLabel = new JLabel("아이디");
				idFieldLabel.setBounds(50, 100, 50, 50);
				
				add(idFieldLabel);
			}
			
			findBt = new JButton("인증번호 받기");
			findBt.setBounds(150, 200, 100, 50);
			findBt.addActionListener(this);
			
			add(titleLabel);
			add(phoneFieldLabel);
			add(phoneField);
			add(firstField);
			add(findBt);
			
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(!firstField.getText().isEmpty() && !phoneField.getText().isEmpty()) {
				JLabel certificationFieldLabel = new JLabel("인증번호");
				certificationFieldLabel.setBounds(40, 300, 50, 50);
				certificationField = new JTextField();
				certificationField.setBounds(100, 300, 200, 50);
				
				certificationBt = new JButton("인증 완료");
				certificationBt.setBounds(160, 350, 80, 50);
				certificationBt.addActionListener(event -> {
					// 인증번호 입력하고 누르는 버튼
					if(certificationField.getText().equals(certNum) && !certificationField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "전화번호 인증에 성공하였습니다", "인증 성공", JOptionPane.PLAIN_MESSAGE);
						
						if(this.mode.equals("ID 찾기")) {
							tc.requestFindID(this, phoneField.getText());
						} else if(this.mode.equals("PW 재설정")) {
							firstField.setEnabled(false);
							phoneField.setEnabled(false);
							findBt.setEnabled(false);
							certificationField.setEnabled(false);
							certificationBt.setEnabled(false);

							JLabel pwFieldLabel = new JLabel("<HTML>비밀번호<br> 재설정");
							pwFieldLabel.setBounds(40, 450, 100, 50);
							pwField = new JPasswordField();
							pwField.setBounds(100, 450, 200, 50);
							
							JLabel pwFieldLabel2 = new JLabel("<HTML>비밀번호<br> 확인");
							pwFieldLabel2.setBounds(40, 500, 100, 50);
							pwField2 = new JPasswordField();
							pwField2.setBounds(100, 500, 200, 50);
							
							JButton pwButton = new JButton("재설정");
							pwButton.setBounds(160, 550, 80, 50);
							pwButton.addActionListener(event2 -> {
								
								if((!pwField.getText().isEmpty() && !pwField2.getText().isEmpty()) && 
										(pwField.getText().equals(pwField2.getText()))) {
									
									tc.requestUpdatePW(this, phoneField.getText(), pwField.getText());
								} else {
									JOptionPane.showMessageDialog(null, "재설정할 비밀번호를 다시 확인해주세요", "재설정 실패", JOptionPane.PLAIN_MESSAGE);
									pwField.setText("");
									pwField2.setText("");
								}
							});
							
							add(pwFieldLabel);
							add(pwField);
							add(pwFieldLabel2);
							add(pwField2);
							add(pwButton);
							
							this.repaint();
						}
					} else {
						JOptionPane.showMessageDialog(null, "전화번호 인증에 실패하였습니다", "인증 실패", JOptionPane.PLAIN_MESSAGE);
					}
				});
				
				add(certificationFieldLabel);
				add(certificationField);
				add(certificationBt);
				
				this.repaint();
				
				JOptionPane.showMessageDialog(null, "인증번호를 전송했습니다", "안내", JOptionPane.PLAIN_MESSAGE);
				// 메세지 발송 메소드
				certNum = new SMSSend(phoneField.getText()).send();
			} else {
				JOptionPane.showMessageDialog(null, "빈 내용을 채워주세요", "입력해주세요", JOptionPane.PLAIN_MESSAGE);
			}
		}
		
		public void id_find_notice(TCPData response) {
			if(response.msg.equals("COMPLETE")) {
				JOptionPane.showMessageDialog(null, firstField.getText() + "님의 ID : " + response.user.id, "ID 찾기 완료", JOptionPane.PLAIN_MESSAGE);
				findFrame.dispose();
			}
		}
		
		public void pw_update_notice(TCPData response) {
			if(response.msg.equals("COMPLETE")) {
				JOptionPane.showMessageDialog(null, firstField.getText() + "님의 비밀번호가 재설정되었습니다.", "PW 재설정 완료", JOptionPane.PLAIN_MESSAGE);
				findFrame.dispose();
			}
		}
	}

	public Find(TCPClient tc, String mode) {
		this.tc = tc;
		
		setSize(400, 750);
		setLayout(null);
		
		//화면 중앙에 띄우기
		Dimension frameSize = getSize();
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((windowSize.width - frameSize.width) / 2,
					(windowSize.height - frameSize.height) / 2);
		
		innerFind = new InnerFind(mode);
		add(innerFind);
		
		setVisible(true);
	}
}
