package kkj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ocy.TCPClient;
import ocy.EntryPointMain.LoginPanel;

public class HorseRaceProjMain extends JFrame implements ActionListener{
	
//	int horse_num = 0;
	
	JFrame reFrame;		//기능버튼클릭시 추가 생성되는 화면
	JTextField field;	
	JButton btn;		//클릭시 기능활성시킬 버튼
	
	JButton user_info, m_charge, game_info, exit, b_single, b_yeon, b_bok;
			//회원정보,   게임머니충전,  경기정보조회,	게임종료,  단식,	 연식,	 복식
	
	JLabel my_money, label;
			//보유머니, 
	JPanel game_screen, b_danglyul,	game_rule, chat, user_list, top_panel, b_single_inpanel, lyul_panel;
			//게임화면,	  배당률,		 배팅방식,	    채팅,	  참가자리스트,  상단나열바, 단식버튼클릭했을때 열리는 기능화면에서 버튼들 마번 묶는 패널

//	TCPClient tc;
	
	public HorseRaceProjMain() {
		super("달려라 왕바우");
		setSize(1600,1000);
		setLayout(null);
		setLocationRelativeTo(null);
		
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
			
		
		for (int i = 0; i < 99; i++) {
			field = new JTextField();
			b_danglyul.setLayout(new GridLayout(11,90));
			field.setText(i+"");
			b_danglyul.add(field);	
			field.setEditable(false);
			field.setHorizontalAlignment(JTextField.CENTER);
			
			if(i==10 || i==11 || i==12 || i==13 || i==14 || i==15 || i==16 || 
				i==17 || i==19 || i==20 || i==21 || i==22 || i==23 || i==24 || i==25 || i==26 || i==29 || i==30 || i==31 || i==61 || i==60 || i==59 ||i==62 ||
				i==32 || i==33 || i==34 || i==35 ||i==39 || i==43 || i==44 || i==49 || i==53 || i==50 || i==51 || i==52 || i==40 || i==41 || i==42 ||
				i==69 || i==70 || i==71 || i==79 || i==80 || i==89)	{
				field.setText("");
			}else if(i==28) {
				field.setText("1");
				field.setBackground(new Color(190, 196, 211));
			}else if(i==38) {
				field.setText("2");
				field.setBackground(new Color(190, 196, 211));
			}else if(i==48) {
				field.setText("3");
				field.setBackground(new Color(190, 196, 211));
			}else if(i==58) {
				field.setText("4");
				field.setBackground(new Color(190, 196, 211));
			}else if(i==68) {
				field.setText("5");
				field.setBackground(new Color(190, 196, 211));
			}else if(i==78) {
				field.setText("6");
				field.setBackground(new Color(190, 196, 211));
			}else if(i==88) {
				field.setText("7");
				field.setBackground(new Color(190, 196, 211));
			}else if(i==98) {
				field.setText("8");
				field.setBackground(new Color(190, 196, 211));
			}else
//				;
			
					if(i==0) {
						field.setText("마번");
						field.setBackground(Color.GREEN);
					}else if(i==9) {
						field.setText("단식");
						field.setBackground(Color.yellow);
					}else if(i==18) {
						field.setText("연식");
						field.setBackground(Color.orange);
					}else if(i==27) {
						field.setText("복식");
						field.setBackground(Color.pink);
					}else if(i==36) {
						field.setText("2");
						field.setBackground(Color.red);
					}else if(i==45) {
						field.setText("3");
						field.setBackground(new Color(177,244,211));
					}else if(i==54) {
						field.setText("4");
						field.setBackground(Color.cyan);
					}else if(i==63) {
						field.setText("5");
						field.setBackground(new Color(77,140,50));
					}else if(i==72) {
						field.setText("6");
						field.setBackground(new Color(200,150,50));
					}else if(i==81) {
						field.setText("7");
						field.setBackground(new Color(22,180,250));
					}else if(i==90) {
						field.setText("8");
						field.setBackground(new Color(147,170,250));
					}else
//						;
		
	
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
		b_single.setBackground(Color.yellow);
		game_rule.add(b_single);
		
		b_yeon = new JButton("연식");
		b_yeon.setBounds(128, 0, 129, 80);
		b_yeon.setBackground(Color.orange);
		game_rule.add(b_yeon);
		
		b_bok = new JButton("복식");
		b_bok.setBounds(257, 0, 128, 80);
		b_bok.setBackground(Color.pink);
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
	}

	public static void main(String[] args) {
		
	
		new HorseRaceProjMain();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		if( e.getSource().equals(user_info)) {
			reFrame = new JFrame("회원정보(랭크)");
			reFrame.setSize(400,500);
			reFrame.setLocationRelativeTo(null); 
			reFrame.setResizable(false);
			reFrame.setVisible(true);
		}
			else if(e.getSource().equals(m_charge)) {
				reFrame = new JFrame("게임머니충전");
				reFrame.setSize(400,300);
				reFrame.setLocationRelativeTo(null); 
				reFrame.setResizable(false);
				reFrame.setVisible(true);
		}
			else if(e.getSource().equals(game_info)) {
				reFrame = new JFrame("경기정보조회");
				reFrame.setSize(400,500);
				reFrame.setLocationRelativeTo(null); 
				reFrame.setResizable(false);
				reFrame.setVisible(true);
		}
			
			else if(e.getSource().equals(exit)) {
				int answer = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "게임종료", 
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					if(answer==0) 
						System.exit(0);
				
					
		}
			else if(e.getSource().equals(b_single)) {
				String bet_num = JOptionPane.showInputDialog(null, "배팅하실 말 번호를 입력하세요",
						"단식", JOptionPane.INFORMATION_MESSAGE);
//	    		bat_num_dan.add(bet_num);
	    		if(bet_num != null) {
	    			String bet_money = JOptionPane.showInputDialog(null, "배팅하실 금액을 입력하세요",
	    					"단식", JOptionPane.INFORMATION_MESSAGE);
	    			
//	    			if(bet_money != null) {
//	        			tc.bet_single(this, bet_num, bet_money);
//	        		}
	    		}
				
		}
			else if(e.getSource().equals(b_yeon)) {
				String bet_num = JOptionPane.showInputDialog(null, "배팅하실 말 번호를 입력하세요",
						"연식", JOptionPane.INFORMATION_MESSAGE);
	    		
	    		if(bet_num != null) {
	    			String bet_money = JOptionPane.showInputDialog(null, "배팅하실 금액을 입력하세요",
	    					"연식", JOptionPane.INFORMATION_MESSAGE);
	    			
//	    			if(bet_money != null) {
//	        			tc.bet_place(this, bet_num, bet_money);
//	        		}
	    		}
		}
			else if(e.getSource().equals(b_bok)) {
				String bet_num1 = JOptionPane.showInputDialog(null, "배팅하실 첫번째 말 번호를 입력하세요",
						"복식", JOptionPane.INFORMATION_MESSAGE);
	    		
	    		if(bet_num1 != null) {
	    			String bet_num2 = JOptionPane.showInputDialog(null, "배팅하실 두번째 말 번호를 입력하세요",
	    					"복식", JOptionPane.INFORMATION_MESSAGE);
	    			
	    			if(bet_num1.equals(bet_num2)) {
	    				JOptionPane.showMessageDialog(null, "첫번째 말 번호와 다른 번호를 입력하세요",
	    						"복식", JOptionPane.INFORMATION_MESSAGE);
	    			} else {
	    				if(bet_num2 != null) {
	    					String bet_money = JOptionPane.showInputDialog(null, "배팅하실 금액을 입력하세요",
	    							"복식", JOptionPane.INFORMATION_MESSAGE);
	    					
//	    					if(bet_money != null) {
//	    						tc.bet_quinella(this, bet_num1, bet_num2, bet_money);
//	    					}
	    				}
	    			}
	    		}
				
		}else 
				return;
	}
}
	
	
