package kkj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class HorseRaceProjMain extends JFrame implements ActionListener{
	
	long batting_money=0;
	JFrame reFrame;		//기능버튼클릭시 추가 생성되는 화면
	JTextField field;	
	JButton btn;		//클릭시 기능활성시킬 버튼
	
	JButton user_info, m_charge, game_info, exit, b_single, b_yeon, b_bok;
			//회원정보,   게임머니충전,  경기정보조회,	게임종료,  단식,	 연식,	 복식
	
	JLabel my_money, label;
			//보유머니, 
	JPanel game_screen, b_danglyul,	game_rule, chat, user_list, top_panel, b_single_inpanel, lyul_panel;
			//게임화면,	  배당률,		 배팅방식,	    채팅,	  참가자리스트,  상단나열바, 단식버튼클릭했을때 열리는 기능화면에서 버튼들 마번 묶는 패널

	public HorseRaceProjMain() {
		super("달려라 왕바우");
		setSize(1600,1000);
		setLayout(null);
	
		top_panel = new JPanel();	//상단나열바
		top_panel.setSize(1585,70);
		top_panel.setBackground(Color.white);
		top_panel.setLayout(null);
		add(top_panel);
		
		user_info = new JButton("회원정보(랭크)");
		user_info.setBounds(0, 0, 317, 70);
		user_info.setBackground(Color.white);
		top_panel.add(user_info);
		
		my_money = new JLabel("보유머니");
		my_money.setBounds(317, 0, 317, 70);
		my_money.setHorizontalAlignment(JLabel.CENTER);
//		my_money.setBackground(Color.cyan);
//		my_money.setOpaque(true);
		top_panel.add(my_money);

		m_charge = new JButton("게임머니충전");
		m_charge.setBounds(634, 0, 317, 70);
		m_charge.setBackground(Color.white);
		top_panel.add(m_charge);
		
		game_info = new JButton("경기정보조회");
		game_info.setBounds(951, 0, 317, 70);
		game_info.setBackground(Color.white);
		top_panel.add(game_info);
		
		exit = new JButton("게임종료");
		exit.setBounds(1268, 0, 317, 70);
		exit.setBackground(Color.white);
		top_panel.add(exit);
		
		game_screen = new JPanel();		//게임화면
		game_screen.setBounds(0, 70, 1585, 500);
//		game_screen.setBackground(Color.red);
		add(game_screen);
		
		b_danglyul = new JPanel();		//배당률
		b_danglyul.setBounds(0, 570, 800, 392);
//		b_danglyul.setLayout(null);
//		b_danglyul.setBackground(Color.blue);
		add(b_danglyul);
		
//		lyul_panel = new JPanel();		//배당률 상단나열바
//		lyul_panel.setBounds(0,0,800,70);
//		b_danglyul.add(lyul_panel);
			
		for (int i = 0; i < 120; i++) {
			field = new JTextField();
			field.setBounds(0, 0, 70, 70);
			b_danglyul.setLayout(new GridLayout(12,90,0,0));
//			field.setText("0");
			b_danglyul.add(field);	//배당률 안에 그리드로 쪼개기
			field.setEditable(false);
			field.setHorizontalAlignment(JTextField.CENTER);
				
				if(i==0) {
					field.setText("마번");
					field.setBackground(Color.GREEN);
				}else if(i==10) {
					field.setText("단식");
					field.setBackground(Color.yellow);
					}else if(i==20) {
						field.setText("연식");
						field.setBackground(Color.orange);
					}else if(i==30) {
						field.setText("복식");
						field.setBackground(Color.pink);
					}else if(i==40) {
						field.setText("2");
						field.setBackground(Color.red);
					}else if(i==50) {
						field.setText("3");
						field.setBackground(new Color(177,244,211));
					}else if(i==60) {
						field.setText("4");
						field.setBackground(Color.cyan);
						}else if(i==70) {
							field.setText("5");
							field.setBackground(new Color(77,140,50));
						}else if(i==80) {
							field.setText("6");
							field.setBackground(new Color(200,150,50));
						}else if(i==90) {
							field.setText("7");
							field.setBackground(new Color(22,180,250));
						}else if(i==100) {
							field.setText("8");
							field.setBackground(new Color(112,215,50));
						}else if(i==110) {
							field.setText("9");
							field.setBackground(new Color(147,170,250));
				}else 
					field.setText(i+"");
		}	
		
		chat = new JPanel();		//채팅
		chat.setBounds(800, 570, 400, 392);
		chat.setBackground(Color.black);
		add(chat);
		
		game_rule = new JPanel();		//배팅방식
		game_rule.setBounds(1200, 570, 385, 80);
		game_rule.setBackground(Color.orange);
		game_rule.setLayout(null);
		add(game_rule);
		
		b_single = new JButton("단식");
		b_single.setBounds(0, 0, 128, 80);
		b_single.setBackground(Color.pink);
		game_rule.add(b_single);
		
		b_yeon = new JButton("연식");
		b_yeon.setBounds(128, 0, 129, 80);
		b_yeon.setBackground(Color.green);
		game_rule.add(b_yeon);
		
		b_bok = new JButton("복식");
		b_bok.setBounds(257, 0, 128, 80);
		b_bok.setBackground(Color.yellow);
		game_rule.add(b_bok);
		
		user_list = new JPanel();		//참가자리스트
		user_list.setBounds(1200, 650, 385, 312);
//		user_list.setBackground(Color.gray);
		add(user_list);
		
	
		
		user_info.addActionListener(this);
		m_charge.addActionListener(this);
		game_info.addActionListener(this);
		exit.addActionListener(this);
		b_single.addActionListener(this);
		b_yeon.addActionListener(this);
		b_bok.addActionListener(this);
		
//		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	public static void main(String[] args) {
		
		new HorseRaceProjMain();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if( e.getSource().equals(user_info)) {
			reFrame = new JFrame("회원정보(랭크)");
			reFrame.setBounds(500, 100, 500, 500);
			reFrame.setResizable(false);
			reFrame.setVisible(true);
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
				reFrame.setBounds(500, 100, 500, 500);
				reFrame.setResizable(false);
				reFrame.setVisible(true);
		}
			else if(e.getSource().equals(b_single)) {
				reFrame = new JFrame("단식");
				reFrame.setBounds(500, 100, 400, 100);
				
				field = new JTextField();
				
//				if(게임시작하면 배팅못하게끔 닫을꺼임)
//					field.setEnabled(false);
				
				label = new JLabel("배팅금액");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setVerticalAlignment(SwingConstants.CENTER);
				
				btn = new JButton("확인");
					
				ActionListener listener = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println(Integer.parseInt(field.getText()));
						field.setText("");
						
					}
				};

				if(Pattern.matches(".*[0-9].*", field.getText())) {
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
			else if(e.getSource().equals(b_yeon)) {
				reFrame = new JFrame("연식");
				reFrame.setBounds(500, 100, 400, 100);
				
				//	else if(게임시작하면 배팅못하게끔 닫을꺼임)
				//		field.setEnabled(false);
				
				label = new JLabel("배팅금액");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setVerticalAlignment(SwingConstants.CENTER);
				
				btn = new JButton("확인");
			
				ActionListener listener = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println(field.getText());
						field.setText("");
					}
				};
			
				if(Pattern.matches(".*[0-9].*", field.getText())) {
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
				reFrame = new JFrame("복식");
				reFrame.setBounds(500, 100, 400, 100);
				
				field = new JTextField();

//				if(게임시작하면 배팅못하게끔 닫을꺼임)
//				field.setEnabled(false);
				
				label = new JLabel("배팅금액");
				label.setHorizontalAlignment(SwingConstants.CENTER);
				label.setVerticalAlignment(SwingConstants.CENTER);
				
				btn = new JButton("확인");
				
				ActionListener listener = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println(field.getText());
						field.setText("");
					}
				};
			
				if(Pattern.matches(".*[0-9].*", field.getText())) {
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


		
