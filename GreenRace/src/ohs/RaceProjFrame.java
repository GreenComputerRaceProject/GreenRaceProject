package ohs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ocy.BetDTO_Place;
import ocy.BetDTO_Quinella;
import ocy.BetDTO_Single;
import ocy.BetDTO_list;
import ocy.MultiServer;
import ocy.RaceReadyScreen;
import ocy.TCPChat;
import ocy.TCPClient;
import khs.GameInfo;
import khs.RankIcon;

public class RaceProjFrame extends JFrame implements ActionListener{
	
	TCPClient tc;

	BattingScreen battingScreen;
	Screen screen;

    public GameScreen2 gsm;
    
    GameInfo gameinfo;
	
    JTextField field;
    JFrame refFrame;   //기능버튼클릭시 추가 생성되는 화면
    JButton user_info, m_charge, game_info, exit, b_single, b_yeon, b_bok;
         //회원정보,   게임머니충전,  경기정보조회,   게임종료,  단식,    연식,    복식

    public JPanel user_list, bet_list;
    JPanel game_screen, b_danglyul,   game_rule;    
         //게임화면,     배당률,       배팅방식,       채팅,     참가자리스트
   
    JLabel my_money;   //보유머니
    JPanel jp;          //상단나열바
   
    TCPChat chat;
    
    ArrayList<JTextField> rate_single;
    ArrayList<JTextField> rate_place;
    LinkedHashMap<Integer,JTextField> rate_quinella;
   
    public RaceProjFrame(TCPClient tc) {
       super("달려라 왕바우");
       
       this.tc = tc;
       
       setSize(1585,965);
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
      
       my_money = new JLabel("보유 머니 : ");
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

   //    battingScreen = new BattingScreen(tc, this);
   //    add(battingScreen);
       
       screen = new Screen(tc, this); 
       add(screen);

       b_danglyul = new JPanel();
       b_danglyul.setBounds(0, 570, 800, 392);
       //b_danglyul.setBackground(Color.blue);
       add(b_danglyul);
       
       rate_single = new ArrayList<JTextField>();
       rate_place = new ArrayList<JTextField>();
       rate_quinella = new LinkedHashMap<Integer, JTextField>();
       
       for (int i = 0; i < 99; i++) {
			field = new JTextField();
			b_danglyul.setLayout(new GridLayout(11,90));
			b_danglyul.add(field);	
			field.setEditable(false);
			field.setHorizontalAlignment(JTextField.CENTER);
			
			if(i >= 1 && i <= 8) {
				field.setText(Integer.toString(i));
				field.setBackground(new Color(190, 196, 211));
			}
			
			if(i >= 10 && i <= 17) {
				rate_single.add(field);
			}
			
			if(i >= 19 && i <= 26) {
				rate_place.add(field);
			}
			
			if(i==37||i==46||i==47||i==55||i==56||i==57||i==64||i==65||i==66||i==67||
					i==73||i==74||i==75||i==76||i==77||i==82||i==83||i==84||i==85||i==86||i==87||
					i==91||i==92||i==93||i==94||i==95||i==96||i==97) {
				rate_quinella.put(i,field);
			}
			
			if(i==28) {
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
			}
					
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
			}
		}
       
       LinkedHashMap<Integer, JTextField> copy = new LinkedHashMap<Integer, JTextField>();
       copy.putAll(rate_quinella);
       rate_quinella.clear();
       
       int[] nums = {37,46,55,64,73,82,91,47,56,65,74,83,92,57,66,75,84,93,67,76,85,94,77,86,95,87,96,97};
       
       for (int n : nums) {
    	   rate_quinella.put(n, copy.get(n));
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
       
       bet_list = new JPanel();
       bet_list.setBounds(1200, 650, 200, 312);
       bet_list.setBackground(Color.green);
       bet_list.setLayout(new FlowLayout());
       add(bet_list);
      
       user_list = new JPanel();
       user_list.setBounds(1400, 650, 185, 312); // (1200, 650, 385, 312)
       user_list.setBackground(Color.gray);
       user_list.setLayout(new FlowLayout());
       add(user_list);
      
       refFrame = new JFrame();
      
       user_info.addActionListener(this);
       m_charge.addActionListener(this);
       game_info.addActionListener(this);
       exit.addActionListener(this);
       b_single.addActionListener(this);
       b_yeon.addActionListener(this);
       b_bok.addActionListener(this);
       
       setUndecorated(true);
       setVisible(true);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
      
       chat.entranceChat();
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	if(e.getSource().equals(user_info)) {
//    		refFrame.setBounds(500, 100, 500, 500);
//    		refFrame.setResizable(false);
//    		refFrame.setVisible(true);
    	} else if(e.getSource().equals(m_charge)) {
    		if(tc.user.getMoney() >= 200000) {
    			JOptionPane.showMessageDialog(null, "보유 머니가 20만원 이하일때만 가능합니다.", "무료충전 불가", JOptionPane.PLAIN_MESSAGE);
    		} else {
    			tc.money_charge(this);
    		}
    	} else if(e.getSource().equals(game_info)) {
    		gameinfo = new GameInfo(tc);
    	} else if(e.getSource().equals(exit)) {
    		int confirm = JOptionPane.showConfirmDialog(null, "게임을 종료하시겠습니까?", "게임 종료", JOptionPane.YES_NO_OPTION);
    		
    		if(confirm == 0) {
    			tc.game_exit(this);
    			System.exit(0);
    		}
    	} else if(e.getSource().equals(b_single)) { // 단식 배팅 버튼
    		Integer[] nums = {1,2,3,4,5,6,7,8};
    		String bet_num = Integer.toString(JOptionPane.showOptionDialog(null, "배팅하실 말 번호를 선택하세요", "단식", 0, 0, null, nums, null) + 1);
    		
    		if(!bet_num.equals("0")) {
    			String bet_money = JOptionPane.showInputDialog(null, "배팅하실 금액을 입력하세요", "단식", JOptionPane.INFORMATION_MESSAGE);
    			
    			if(bet_money != null) {
    				
    				if(Long.parseLong(bet_money) <= tc.user.getMoney()) {
    					tc.bet_single(this, bet_num, bet_money);
    				} else {
    					JOptionPane.showMessageDialog(null, "소지한 금액보다 배팅할 금액이 많습니다.", "배팅 실패", JOptionPane.PLAIN_MESSAGE);
    				}
    			}
    		}
    		
    	} else if(e.getSource().equals(b_yeon)) { // 연식 배팅 버튼
    		Integer[] nums = {1,2,3,4,5,6,7,8};
    		String bet_num = Integer.toString(JOptionPane.showOptionDialog(null, "배팅하실 말 번호를 선택하세요", "연식", 0, 0, null, nums, null) + 1);
    		
    		if(!bet_num.equals("0")) {
    			String bet_money = JOptionPane.showInputDialog(null, "배팅하실 금액을 입력하세요", "연식", JOptionPane.INFORMATION_MESSAGE);
    			
    			if(bet_money != null) {
    				
    				if(Long.parseLong(bet_money) <= tc.user.getMoney()) {
    					tc.bet_place(this, bet_num, bet_money);
    				} else {
    					JOptionPane.showMessageDialog(null, "소지한 금액보다 배팅할 금액이 많습니다.", "배팅 실패", JOptionPane.PLAIN_MESSAGE);
    				}
    			}
    		}
			
    	} else if(e.getSource().equals(b_bok)) { // 복식 배팅 버튼
    		Integer[] nums = {1,2,3,4,5,6,7,8};
    		String bet_num1 = Integer.toString(JOptionPane.showOptionDialog(null, "배팅하실 첫번째 말 번호를 선택하세요", "복식", 0, 0, null, nums, null) + 1);
    		
    		if(!bet_num1.equals("0")) {
    			String bet_num2 = Integer.toString(JOptionPane.showOptionDialog(null, "배팅하실 두번째 말 번호를 선택하세요", "복식", 0, 0, null, nums, null) + 1);
    			
    			if(!bet_num2.equals("0")) {
    				
    				if(bet_num2.equals(bet_num1)) {
    					JOptionPane.showMessageDialog(null, "첫번째 말 번호와 다른 번호를 입력하세요", "복식", JOptionPane.INFORMATION_MESSAGE);
    				} else {
    					
    					if(Integer.parseInt(bet_num1) > Integer.parseInt(bet_num2)) {
    						String temp = "";
    						
    						temp = bet_num1;
    						bet_num1 = bet_num2;
    						bet_num2 = temp;
    					}
    					
    					String bet_money = JOptionPane.showInputDialog(null, "배팅하실 금액을 입력하세요", "복식", JOptionPane.INFORMATION_MESSAGE);
    					
    					if(bet_money != null) {
    	    				
    	    				if(Long.parseLong(bet_money) <= tc.user.getMoney()) {
    	    					tc.bet_quinella(this, bet_num1, bet_num2, bet_money);
    	    				} else {
    	    					JOptionPane.showMessageDialog(null, "소지한 금액보다 배팅할 금액이 많습니다.", "배팅 실패", JOptionPane.PLAIN_MESSAGE);
    	    				}
    	    			}
    				}
    			}
    		}
    		
		} else {
			return;
		}
      
    }
    
    public void getMoney() {
    	tc.get_money(this);
    }
    
    public void setMoney(long money) {
    	my_money.setText("보유 머니 : " + money);
    	my_money.revalidate();
    	my_money.repaint();
    }
    
    public void getBetRate_Single() {
    	tc.get_bet_rate_single(this);
    }
    
    public void setBetRate_Single(ArrayList<Double> rates) {
    	for (int i = 0; i < rates.size(); i++) {
			rate_single.get(i).setText(Double.toString(rates.get(i)));
		}
    	
    	b_danglyul.revalidate();
    	b_danglyul.repaint();
    }
    
    public void getBetRate_Place() {
    	tc.get_bet_rate_place(this);
    }
    
    public void setBetRate_Place(ArrayList<Double> rates) {
    	for (int i = 0; i < rates.size(); i++) {
			rate_place.get(i).setText(Double.toString(rates.get(i)));
		}
    	
    	b_danglyul.revalidate();
    	b_danglyul.repaint();
    }
    
    public void getBetRate_Quinella() {
    	tc.get_bet_rate_quinella(this);
    }
    
    public void setBetRate_Quinella(ArrayList<Double> rates) {
    	int[] list = {37,46,55,64,73,82,91,47,56,65,74,83,92,57,66,75,84,93,67,76,85,94,77,86,95,87,96,97};
    	
    	for (int i = 0; i < rates.size(); i++) {
    		rate_quinella.get(list[i]).setText(Double.toString(rates.get(i)));
		}
    	
    	b_danglyul.revalidate();
    	b_danglyul.repaint();
    }
    
    public void showBetList() {
    	
    	if(tc.bet_list != null) {
    		
    		bet_list.removeAll();
    		
    		for (BetDTO_Single single : tc.bet_list.single) {
    			JPanel jp = new JPanel();
    			jp.setPreferredSize(new Dimension(380, 25));
    			jp.setBackground(Color.yellow);
    			
    			JLabel jl = new JLabel("단식 : " + single.getHname() + "번 " + single.getMoney() + "원");
    			jl.setBackground(Color.pink);
    			jl.setOpaque(true);
    			jp.add(jl);
    			
    			bet_list.add(jp);
    		}
    		
    		for (BetDTO_Place place : tc.bet_list.place) {
    			JPanel jp = new JPanel();
    			jp.setPreferredSize(new Dimension(380, 25));
    			jp.setBackground(Color.yellow);
    			
    			JLabel jl = new JLabel("연식 : " + place.getHname() + "번 " + place.getMoney() + "원");
    			jl.setBackground(Color.pink);
    			jl.setOpaque(true);
    			jp.add(jl);
    			
    			bet_list.add(jp);
    		}
    		
    		for (BetDTO_Quinella quinella : tc.bet_list.quinella) {
    			JPanel jp = new JPanel();
    			jp.setPreferredSize(new Dimension(380, 25));
    			jp.setBackground(Color.yellow);
    			
    			JLabel jl = new JLabel("복식 : " + quinella.getHname1() + "_" + quinella.getHname2() + "번 " + quinella.getMoney() + "원");
    			jl.setBackground(Color.pink);
    			jl.setOpaque(true);
    			jp.add(jl);
    			
    			bet_list.add(jp);
    		}
    		
    		bet_list.revalidate();
    		bet_list.repaint();
    	} else {
    		System.out.println("없음");
    	}
    }
    
    public void notice(String response) {
    	System.out.println(response);
    	if(response.equals("COMPLETE")) {
			JOptionPane.showMessageDialog(null, "배팅이 완료되었습니다.", "배팅 성공", JOptionPane.PLAIN_MESSAGE);
			tc.requestUserInfo(this, tc.user.getId()); // 배팅 후 유저인포 다시 받고 머니 표시 갱신
			showBetList();
		} else if(response.equals("WRONG")) {
			JOptionPane.showMessageDialog(null, "배팅이 실패하였습니다.", "배팅 실패", JOptionPane.PLAIN_MESSAGE);
		} else if(response.equals("ADJUSTMENT_COMPLETE")) {
			//JOptionPane.showMessageDialog(null, "정산 완료되었습니다.", "정산 성공", JOptionPane.PLAIN_MESSAGE);
			tc.requestUserInfo(this, tc.user.getId());
		} else if(response.equals("ADJUSTMENT_WRONG")) {
			JOptionPane.showMessageDialog(null, "정산 실패하였습니다.", "정산 실패", JOptionPane.PLAIN_MESSAGE);
		} else if(response.equals("MONEY_CHARGED")) {
			tc.requestUserInfo(this, tc.user.getId());
			JOptionPane.showMessageDialog(null, "무료 충전이 완료되었습니다.", "무료충전 완료", JOptionPane.PLAIN_MESSAGE);
		} else if(response.equals("MONEY_CHARGE_FAIL")) {
			JOptionPane.showMessageDialog(null, "무료 충전이 실패하였습니다.", "무료충전 실패", JOptionPane.PLAIN_MESSAGE);
		} 
    }

}
      