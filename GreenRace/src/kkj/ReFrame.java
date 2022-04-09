package kkj;

import java.awt.BorderLayout;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ReFrame extends JFrame implements ActionListener {
	// 왜연결이안되징...실패..
	
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	JFrame reFrame = new ReFrame();	
	JTextField field;	
	JButton btn;		//클릭시 기능활성시킬 버튼
	JButton user_info, m_charge, game_info, exit, b_single, b_yeon, b_bok;	
	JLabel label;
	
	ActionListener listener;
	
	HorseRaceProjMain hrj = new HorseRaceProjMain();

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource().equals(user_info)) {
			reFrame = new JFrame("회원정보(랭크)");
			reFrame.setBounds(500, 100, 500, 500);
			reFrame.setResizable(false);
			reFrame.setVisible(true);
			hrj.add(reFrame);
		}
			else if(e.getSource().equals(m_charge)) {
				reFrame = new JFrame("게임머니충전");
				reFrame.setBounds(500, 100, 500, 500);
				reFrame.setResizable(false);
				reFrame.setVisible(true);
		}
			else if(e.getSource().equals(game_info)) {
				reFrame = new JFrame("경기정보조회");
				reFrame.setBounds(500, 100, 500, 500);
				reFrame.setResizable(false);
				reFrame.setVisible(true);
		}
			else if(e.getSource().equals(exit)) {
				reFrame = new JFrame("게임종료");
				reFrame.setSize(500,200);
				reFrame.setLocationRelativeTo(null); 
				reFrame.setVisible(true);
				label = new JLabel("종료하시겠습니까?");
				label.setBounds(0, 0, 100, 50);
				label.setHorizontalAlignment(JLabel.CENTER);
				label.setVerticalAlignment(JLabel.CENTER);
				reFrame.add(label);
//				btn = new JButton("네");
//				btn.setBounds(50, 140, 70, 70);
//				btn.setAlignmentX(SwingConstants.CENTER);
//				btn.setAlignmentX(SwingConstants.CENTER);
//				reFrame.add(btn);
				
				
		}
			else if(e.getSource().equals(b_single)) {
				reFrame = new JFrame("단식");
				reFrame.setSize(400,100);
				reFrame.setLocationRelativeTo(null); 
				field = new JTextField();
				
//				if(게임시작하면 배팅못하게끔 닫을꺼임)
//					field.setEnabled(false);
				
				label = new JLabel("배팅하실 말 번호를 입력해주세요");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setVerticalAlignment(SwingConstants.CENTER);
				
				btn = new JButton("확인");
					
				
				
			
					listener = new ActionListener() {
				
					@Override
					public void actionPerformed(ActionEvent e) {
						try {

							Class.forName("org.mariadb.jdbc.Driver");

							Connection con = DriverManager.getConnection(

									"jdbc:mariadb://localhost:3306/race_db","race", "123456" );

							Statement stmt = con.createStatement();

							ResultSet rs = stmt.executeQuery("select * from dansik");

							stmt.executeUpdate("insert into dansik " + 

						                    "(horse_num, nickname, gambling_money) " +  "VALUES " + 

									"()");

							while(rs.next()) {

								System.out.println(rs.getString("nickname"));

							}
						

							stmt.close();
							con.close();

						} catch (Exception error) {
							// TODO Auto-generated catch block
							error.printStackTrace();
						}
					}
					};
					
					if(!Pattern.matches(".*[0-9].*", field.getText())) {
						Integer.parseInt(field.getText());	
						field.setText("");	
						if(btn.equals("확인")) {
							Integer.parseInt(field.getText());
							System.out.println(Integer.parseInt(field.getText()));
						}else 
							System.out.println("배팅하실금액을 입력해주세요");
										
					
				}

				btn.addActionListener(listener);
				reFrame.add(btn, BorderLayout.SOUTH);
				reFrame.add(label, BorderLayout.NORTH);
				reFrame.add(field, BorderLayout.CENTER);
				
				reFrame.setResizable(false);
				reFrame.setVisible(true);
				
		}
			else if(e.getSource().equals(b_yeon)) {
				reFrame = new JFrame("연식");
				reFrame.setSize(400, 100);
				reFrame.setLocationRelativeTo(null);
				
				//	else if(게임시작하면 배팅못하게끔 닫을꺼임)
				//		field.setEnabled(false);
				
				label = new JLabel("배팅금액");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setVerticalAlignment(SwingConstants.CENTER);
				
				btn = new JButton("확인");
			
				listener = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println(field.getText());
						field.setText("");
					}
					};
				
				if(!Pattern.matches(".*[0-9].*", field.getText())) {
						System.out.println("숫자만 입력해주세요");
					}else 
						System.out.println("배팅하실금액을 입력해주세요");
				
				btn.addActionListener(listener);				
			
				reFrame.add(btn, BorderLayout.SOUTH);
				reFrame.add(label, BorderLayout.NORTH);
				reFrame.add(field, BorderLayout.CENTER);
				
				reFrame.setResizable(false);
				reFrame.setVisible(true);
		}
			else if(e.getSource().equals(b_bok)) {
				reFrame = new JFrame("연식");
				reFrame.setSize(400, 100);
				reFrame.setLocationRelativeTo(null);
				field = new JTextField();

//				if(게임시작하면 배팅못하게끔 닫을꺼임)
//				field.setEnabled(false);
				
				label = new JLabel("배팅금액");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setVerticalAlignment(SwingConstants.CENTER);
				
				btn = new JButton("확인");
				
				listener = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println(field.getText());
						field.setText("");
					}
				};
			
				if(!Pattern.matches(".*[0-9].*", field.getText())) {
						System.out.println("숫자만 입력해주세요");
					}else 
						System.out.println("배팅하실금액을 입력해주세요");
				
				btn.addActionListener(listener);
				
				reFrame.add(btn, BorderLayout.SOUTH);
				reFrame.add(label, BorderLayout.NORTH);
				reFrame.add(field, BorderLayout.CENTER);
				
				reFrame.setResizable(false);
				reFrame.setVisible(true);
		}else
				return;
	}

	}
	
