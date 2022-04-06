package ohs;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MultiServerMain extends JFrame implements ActionListener{
	
	 
	
	   JFrame refFrame;   //기능버튼클릭시 추가 생성되는 화면
	   JButton user_info, m_charge, game_info, exit, b_single, b_yeon, b_bok;
	         //회원정보,   게임머니충전,  경기정보조회,   게임종료,  단식,    연식,    복식
	   JPanel game_screen, b_danglyul,   game_rule, chat, user_list;    
	         //게임화면,     배당률,       배팅방식,       채팅,     참가자리스트
	   JLabel my_money;   //보유머니
	   JPanel jp;          //상단

	public MultiServerMain() {
		super("달려라 왕바우");
	      setSize(1600,1000);
	      setLayout(null);
	   
	      jp = new JPanel();
	      jp.setSize(1585,70);
	      jp.setBackground(Color.white);
	      jp.setLayout(null);
	      add(jp);
	      
	      user_info = new JButton("회원정보(랭크)");
	      user_info.setBounds(0, 0, 317, 70);
	      user_info.setBackground(Color.white);
	      jp.add(user_info);
	      
	      my_money = new JLabel("보유머니");
	      my_money.setBounds(317, 0, 317, 70);
	      my_money.setHorizontalAlignment(JLabel.CENTER);
	      my_money.setBackground(Color.cyan);
	      my_money.setOpaque(true);
	      jp.add(my_money);

	      m_charge = new JButton("게임머니충전");
	      m_charge.setBounds(634, 0, 317, 70);
	      m_charge.setBackground(Color.white);
	      jp.add(m_charge);
	      
	      game_info = new JButton("경기정보조회");
	      game_info.setBounds(951, 0, 317, 70);
	      game_info.setBackground(Color.white);
	      jp.add(game_info);
	      
	      exit = new JButton("게임종료");
	      exit.setBounds(1268, 0, 317, 70);
	      exit.setBackground(Color.white);
	      jp.add(exit);
	   
	      
	      JPanel gsm = new ServerGameScreen();
	      add(gsm);
	      
	      b_danglyul = new JPanel();
	      b_danglyul.setBounds(0, 570, 800, 392);
	      b_danglyul.setBackground(Color.blue);
	      add(b_danglyul);
	      
	      chat = new JPanel();
	      chat.setBounds(800, 570, 400, 392);
	      chat.setBackground(Color.black);
	      add(chat);
	      
	      game_rule = new JPanel();
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
	      
	      user_list = new JPanel();
	      user_list.setBounds(1200, 650, 385, 312);
	      user_list.setBackground(Color.gray);
	      add(user_list);
	      
	      refFrame = new JFrame();
	      
	      user_info.addActionListener(this);
	      m_charge.addActionListener(this);
	      game_info.addActionListener(this);
	      exit.addActionListener(this);
	      
	      
	      
//	      setResizable(false);
	      setVisible(true);
	      setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	
	
	
	public static void main(String[] args) {
	 
		new MultiServerMain();

	}
	
	@Override
	   public void actionPerformed(ActionEvent e) {
	   
	      if( e.getSource().equals(user_info) ||
	         e.getSource().equals(m_charge) ||
	         e.getSource().equals(game_info) ||
	         e.getSource().equals(exit) )    {
	         refFrame.setBounds(500, 100, 500, 500);
	         refFrame.setResizable(false);
	         refFrame.setVisible(true);
	      }else
	            return;
	      }
	

}
