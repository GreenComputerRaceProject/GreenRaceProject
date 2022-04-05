package kkj;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HorseRaceProjMain extends JFrame implements ActionListener{
	
	int horse_num;		// 말번호
	long batting_money;	//배팅액
	JFrame reFrame;		//기능버튼클릭시 추가 생성되는 화면
	JTextField field;	//배당률 안에 그리드로 쪼개기
	JTextField input;	
	JButton click;		//클릭시 기능활성시킬 버튼
	
	JButton user_info, m_charge, game_info, exit, b_single, b_yeon, b_bok;
			//회원정보,   게임머니충전,  경기정보조회,	게임종료,  단식,	 연식,	 복식
	
	JLabel my_money;	//보유머니
	
	JPanel game_screen, b_danglyul,	game_rule, chat, user_list; 	
			//게임화면,	  배당률,		 배팅방식,	    채팅,	  참가자리스트

	JPanel lyul_panel;	//배당률 상단나열바
	JPanel top_panel; 			//상단나열바
	JPanel b_single_inpanel;	//메인화면 단식버튼클릭했을때 열리는 기능화면에서 버튼들 마번 묶는 패널
	
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
		my_money.setBackground(Color.cyan);
		my_money.setOpaque(true);
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
		game_screen.setBackground(Color.red);
		add(game_screen);
		
		b_danglyul = new JPanel();		//배당률
		b_danglyul.setBounds(0, 570, 800, 392);
		b_danglyul.setLayout(null);
		b_danglyul.setBackground(Color.blue);
		add(b_danglyul);
			
		field = new JTextField();	//배당률 안에 그리드로 쪼개기
		field.setSize(0,70);
		field.setEnabled(false);
		
		lyul_panel = new JPanel();		//배당률 상단나열바
		lyul_panel.setBounds(0,0,800,70);
		lyul_panel.setLayout(new GridLayout( 1,9));
		lyul_panel.setBackground(Color.yellow);
		b_danglyul.add(lyul_panel);
		
		for (int i = 0; i < 10; i++) {
			lyul_panel.add(new JTextField());
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
		user_list.setBackground(Color.gray);
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
				reFrame.setLayout(null);
				reFrame.setBounds(800, 500, 413, 392);
			
				b_single_inpanel = new JPanel();		//메인화면 단식버튼클릭했을때 열리는 기능화면에서 버튼들 마번 묶는 패널
				b_single_inpanel.setSize(400,100);
				b_single_inpanel.setLayout(null);
				b_single_inpanel.setBackground(Color.yellow);
				reFrame.add(b_single_inpanel);
			
				click = new JButton();
				click.setSize(100,50);
				click.setBackground(Color.white);
				b_single_inpanel.add(click);
				
				input = new JTextField();
				input.setBounds(125, 245, 150, 50);
				input.setHorizontalAlignment(JTextField.RIGHT);				
				input.setBackground(Color.white);
				input.setEnabled(true);
				reFrame.add(input);
				
				batting_money = 0;
				if(equals(horse_num==1)){
					Scanner sc = new Scanner(System.in);
					batting_money = sc.nextLong();
					
				}
				
			
				reFrame.setResizable(false);
				reFrame.setVisible(true);
				
		}
			else if(e.getSource().equals(b_yeon)) {
				reFrame = new JFrame("연식");
				reFrame.setBounds(500, 100, 500, 500);
				reFrame.setResizable(false);
				reFrame.setVisible(true);
		}
			else if(e.getSource().equals(b_bok)) {
				reFrame = new JFrame("복식");
				reFrame.setBounds(500, 100, 500, 500);
				reFrame.setResizable(false);
				reFrame.setVisible(true);
		}else
				return;
		}

}


		
