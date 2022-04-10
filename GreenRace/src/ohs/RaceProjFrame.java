package ohs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ocy.MultiServer;
import ocy.RaceReadyScreen;
import ocy.TCPChat;
import ocy.TCPClient;
import khs.GameInfo;

public class RaceProjFrame extends JFrame implements ActionListener{
	
	TCPClient tc;
	BattingScreen rrs;
    public GameScreen2 gsm;
    
    GameInfo gameinfo;
	
    JTextField field;
    JFrame refFrame;   //기능버튼클릭시 추가 생성되는 화면
    JButton user_info, m_charge, game_info, exit, b_single, b_yeon, b_bok;
         //회원정보,   게임머니충전,  경기정보조회,   게임종료,  단식,    연식,    복식

    public JPanel user_list;
    JPanel game_screen, b_danglyul,   game_rule;    
         //게임화면,     배당률,       배팅방식,       채팅,     참가자리스트
   
    JLabel my_money;   //보유머니
    JPanel jp;          //상단나열바
   
    TCPChat chat;
   
    public RaceProjFrame(TCPClient tc) {
       super("달려라 왕바우");
       
       this.tc = tc;
       
       setSize(1600,1000);
       setLayout(null);
      
       Dimension frameSize = getSize();
       Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
       setLocation((windowSize.width - frameSize.width) / 2,
				  (windowSize.height - frameSize.height) / 2);
   
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
    /* 
       game_screen = new JPanel();
       game_screen.setBounds(0, 70, 1585, 500);
       game_screen.setBackground(Color.red);
       add(game_screen);
     */ 
      
       rrs = new BattingScreen(this);
       add(rrs);
      
//     gsm = new GameScreen2();
//     add(gsm);
      
       b_danglyul = new JPanel();
       b_danglyul.setBounds(0, 570, 800, 392);
       //b_danglyul.setBackground(Color.blue);
       add(b_danglyul);
       
       for (int i = 0; i < 120; i++) {
			field = new JTextField();
			field.setBounds(0, 0, 70, 70);
			b_danglyul.setLayout(new GridLayout(12,90,0,0));
			field.setText(i+""+"\n"+"0");
			b_danglyul.add(field);	
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
      
       chat = new TCPChat(tc, this);
       chat.setBounds(800, 570, 400, 392);
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
       user_list.setLayout(new FlowLayout());
       add(user_list);
      
       refFrame = new JFrame();
      
       user_info.addActionListener(this);
       m_charge.addActionListener(this);
       game_info.addActionListener(this);
       exit.addActionListener(this);
       b_single.addActionListener(this);
      
       setVisible(true);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
      
       chat.entranceChat();
      
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource().equals(user_info) ||
    			//e.getSource().equals(game_info)||
    			e.getSource().equals(m_charge)) {
    		refFrame.setBounds(500, 100, 500, 500);
    		refFrame.setResizable(false);
    		refFrame.setVisible(true);
    	}else if(e.getSource().equals(game_info)) {
    		gameinfo = new GameInfo();
    	} else if(e.getSource().equals(exit)) {
    		//chat.user_exit();
    	} else if(e.getSource().equals(b_single)) {
    		String bet_name = JOptionPane.showInputDialog(null, "배팅하실 말 이름을 입력하세요", "단식", JOptionPane.INFORMATION_MESSAGE);
    		
    		if(bet_name != null) {
    			String bet_money = JOptionPane.showInputDialog(null, "배팅하실 금액을 입력하세요", "단식", JOptionPane.INFORMATION_MESSAGE);
    			
    			if(bet_money != null) {
        			tc.bet_single(bet_name, bet_money);
        		}
    		}
			
    	} else {
    		return;
    	}
      
    }



}
      