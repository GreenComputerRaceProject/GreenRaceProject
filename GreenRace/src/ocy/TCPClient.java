package ocy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import khs.GameInfo;
import khs.RankIcon;
import ocy.EntryPointMain.LoginPanel;
import ocy.Find.InnerFind;
import ocy.SignUp.InnerSignUp;
import ohs.BattingScreen;
import ohs.CalculateScreen;
import ohs.RaceProjFrame;
import ohs.Screen;
import ohs.WaitingScreen;

public class TCPClient {
	
	public UserDTO user;
	public BetDTO_list bet_list;
	public BetDTO_list win_list;
	
	LoginPanel loginPanel;
	InnerSignUp innerSignUp;
	InnerFind innerFind;
	TCPChat tcpChat;
	RaceProjFrame raceProjFrame;
	Screen screen;
	WaitingScreen waitingScreen;
	BattingScreen battingScreen;
	CalculateScreen calculateScreen;
	RankIcon rankIcon;
	GameInfo gameInfo;
	
	ObjectOutputStream oos;
	ObjectInputStream ois;
	
	InetAddress local = null;
	
	TCPClient tc = this;
	
	boolean isMoneyCharged = false;
	public boolean rc = true;
	
	class TCPClientReceiver extends Thread{
		
		@Override
		public void run() {
			try {
				TCPData first = new TCPData();
				first.src = local.getHostAddress();
				first.dst = "서버";
				first.msg = "서버는 처음이야";
				
				oos.writeObject(first);
				oos.flush();
				oos.reset();
				
				while(ois!=null) {
					TCPData response = (TCPData)ois.readObject();
					
					System.out.println(response);
					
					if(response.src.equals("LOGIN")) {
						loginPanel.notice(response.msg);
					} else if(response.src.equals("USER_INFO")) {
						user = new UserDTO(response);
						raceProjFrame.getMoney();
						raceProjFrame.getRank();
					} else if(response.src.equals("VERIFICATION_ID")) {
						innerSignUp.id_verification_notice(response.msg);
					} else if(response.src.equals("VERIFICATION_NICKNAME")) {
						innerSignUp.nickname_verification_notice(response.msg);
					} else if(response.src.equals("VERIFICATION_PHONE")) {
						innerSignUp.phone_verification_notice(response.msg);
					} else if(response.src.equals("USER_INSERT")) {
						innerSignUp.user_insert_notice(response.msg);
					} else if(response.src.equals("FIND_ID")) {
						innerFind.id_find_notice(response);
					} else if(response.src.equals("UPDATE_PW")) {
						innerFind.pw_update_notice(response);
					} else if(response.src.equals("CHAT")) {
						if(tcpChat != null) {
							tcpChat.showChat(response);
						}
					} else if(response.src.equals("ENTRANCE_CHAT")) {
						tcpChat.currentUserList(response);
					} else if(response.src.equals("BET_SINGLE")) {
						raceProjFrame.notice(response.msg);
						raceProjFrame.getBetRate_Single();
					} else if(response.src.equals("BET_PLACE")) {
						raceProjFrame.notice(response.msg);
						raceProjFrame.getBetRate_Place();
					} else if(response.src.equals("BET_QUINELLA")) {
						raceProjFrame.notice(response.msg);
						raceProjFrame.getBetRate_Quinella();
					} else if(response.src.equals("GET_TIME")) {
						if(battingScreen != null) {
							battingScreen.goTimer(response.time);
						}
						if(raceProjFrame != null) {
							raceProjFrame.getBetRate_Single();
							raceProjFrame.getBetRate_Place();
							raceProjFrame.getBetRate_Quinella();
						} else {
							System.out.println("rpf가 널이야");
						}
					} else if(response.src.equals("GET_TIME2")) {
						if(screen != null) {
							screen.goTimer2(response.time);
						} else {
							System.out.println("screen이 없어!");
						}
					} else if(response.src.equals("CALL_SCREEN")) {
						raceProjFrame.remove(screen);
						screen = new Screen(tc, raceProjFrame);
						raceProjFrame.add(screen);
						raceProjFrame.repaint();
						rc = false;
					}else if(response.src.equals("GET_ENTRY")) {
						if(battingScreen != null) {
							battingScreen.setEntry(response.entry);
						}
					} else if(response.src.equals("GET_BET_RATE_SINGLE")) {
						if(raceProjFrame != null) {
							raceProjFrame.setBetRate_Single(response.rate);
						}
					} else if(response.src.equals("GET_BET_RATE_PLACE")) {
						if(raceProjFrame != null) {
							raceProjFrame.setBetRate_Place(response.rate);
						}
					} else if(response.src.equals("GET_BET_RATE_QUINELLA")) {
						if(raceProjFrame != null) {
							raceProjFrame.setBetRate_Quinella(response.rate);
						}
					} else if(response.src.equals("BET_ADJUSTMENT")) {
						if(raceProjFrame != null) {
							raceProjFrame.notice(response.msg);
						} else {
							System.out.println("엥 정산이 안돼?");
						}
					} else if(response.src.equals("MONEY_CHARGE")) {
						if(raceProjFrame != null) {
							raceProjFrame.notice(response.msg);
						} else {
							System.out.println("엥 충전이 안돼?");
						}
					} else if(response.src.equals("GET_USER_RANK")) {
						if(rankIcon != null) {
							rankIcon.showUserRank(response.user);
						}
					} else if(response.src.equals("GET_RECENT_GAME")) {
						if(gameInfo != null) {
							gameInfo.showRecentGame(response.recentGame);
						}
					}
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	public void requestLogIn(LoginPanel loginPanel, String id, String pw) {
		this.loginPanel = loginPanel;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "LOGIN";
			data.user.id = id;
			data.user.pw = pw;
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void requestUserInfo(RaceProjFrame frame, String id) {
		this.raceProjFrame = frame;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "USER_INFO";
			data.user.id = id;
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void requestIdVerification(InnerSignUp innerSignUp, String id) {
		this.innerSignUp = innerSignUp;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "VERIFICATION_ID";
			data.user.id = id;
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void requestNickNameVerification(InnerSignUp innerSignUp, String nickname) {
		this.innerSignUp = innerSignUp;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "VERIFICATION_NICKNAME";
			data.user.nickname = nickname;
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void requestPhoneVerification(InnerSignUp innerSignUp, String phone) {
		this.innerSignUp = innerSignUp;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "VERIFICATION_PHONE";
			data.user.phone = phone;
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void requestInsertUser(InnerSignUp innerSignUp, String id, String pw, String name, String nickname, String phone, String about) {
		this.innerSignUp = innerSignUp;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "USER_INSERT";
			data.user.id = id;
			data.user.pw = pw;
			data.user.name = name;
			data.user.nickname = nickname;
			data.user.phone = phone;
			data.user.about = about;
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void requestFindID(InnerFind innerFind, String phone) {
		this.innerFind = innerFind;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "FIND_ID";
			data.user.phone = phone;
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void requestUpdatePW(InnerFind innerFind, String phone, String pw) {
		this.innerFind = innerFind;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "UPDATE_PW";
			data.user.phone = phone;
			data.user.pw = pw;
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void entrance_chat(TCPChat tcpChat) {
		this.tcpChat = tcpChat;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "ENTRANCE_CHAT";
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void chat(String msg) {
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "CHAT";
			data.user.nickname = this.user.nickname;
			data.msg = msg;
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void bet_single(RaceProjFrame raceProjFrame, String horse_name, String money) {
		this.raceProjFrame = raceProjFrame;
		this.bet_list.single.add(new BetDTO_Single(horse_name, Long.parseLong(money)));
		System.out.println(this.bet_list);
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "BET_SINGLE";
			data.user.nickname = this.user.nickname;
			data.bet_single.hname = horse_name;
			data.bet_single.money = Long.parseLong(money);
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void bet_place(RaceProjFrame raceProjFrame, String horse_name, String money) {
		this.raceProjFrame = raceProjFrame;
		this.bet_list.place.add(new BetDTO_Place(horse_name, Long.parseLong(money)));
		System.out.println(this.bet_list);
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "BET_PLACE";
			data.user.nickname = this.user.nickname;
			data.bet_place.hname = horse_name;
			data.bet_place.money = Long.parseLong(money);
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void bet_quinella(RaceProjFrame raceProjFrame, String horse_name1, String horse_name2, String money) {
		this.raceProjFrame = raceProjFrame;
		this.bet_list.quinella.add(new BetDTO_Quinella(horse_name1, horse_name2, Long.parseLong(money)));
		System.out.println(this.bet_list);
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "BET_QUINELLA";
			data.user.nickname = this.user.nickname;
			data.bet_quinella.hname1 = horse_name1;
			data.bet_quinella.hname2 = horse_name2;
			data.bet_quinella.money = Long.parseLong(money);
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void get_time(BattingScreen battingScreen) {
		this.battingScreen = battingScreen;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "GET_TIME";
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void get_time2(Screen screen) {
		this.screen = screen;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "GET_TIME2";
			this.bet_list = new BetDTO_list();
			this.win_list = new BetDTO_list();
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
	public void start_time(CalculateScreen calculateScreen) {
		this.calculateScreen = calculateScreen;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "START_TIME";
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void get_entry(BattingScreen battingScreen) {
		this.battingScreen = battingScreen;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "GET_ENTRY";
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void get_money(RaceProjFrame raceProjFrame) {
		this.raceProjFrame = raceProjFrame;
		
		if(user != null) {
			this.raceProjFrame.setMoney(user.money);
		} else {
			System.out.println("정보가 없어!");
		}
	}
	
	public void get_rank(RaceProjFrame raceProjFrame) {
		this.raceProjFrame = raceProjFrame;
		
		if(user != null) {
			this.raceProjFrame.setRank(user.rank);
		} else {
			System.out.println("정보가 없어!");
		}
	}
	
	public void get_bet_rate_single(RaceProjFrame raceProjFrame) {
		this.raceProjFrame = raceProjFrame;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "GET_BET_RATE_SINGLE";
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void get_bet_rate_place(RaceProjFrame raceProjFrame) {
		this.raceProjFrame = raceProjFrame;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "GET_BET_RATE_PLACE";
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void get_bet_rate_quinella(RaceProjFrame raceProjFrame) {
		this.raceProjFrame = raceProjFrame;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "GET_BET_RATE_QUINELLA";
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void money_charge(RaceProjFrame raceProjFrame) {
		this.raceProjFrame = raceProjFrame;
		
		if(!isMoneyCharged) {
			try {
				TCPData data = new TCPData();
				data.src = local.getHostAddress();
				data.dst = "MONEY_CHARGE";
				data.user = user;
				
				oos.writeObject(data);
				oos.flush();
				oos.reset();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			isMoneyCharged = true;
		} else {
			JOptionPane.showMessageDialog(null, "이미 충전을 하셨습니다.", "무료충전 불가", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
	public void bet_adjustment(RaceProjFrame raceProjFrame) {
		this.raceProjFrame = raceProjFrame;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "BET_ADJUSTMENT";
			data.user = user;
			data.win_list = win_list;
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void game_exit(RaceProjFrame raceProjFrame) {
		this.raceProjFrame = raceProjFrame;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "GAME_EXIT";
			data.user.nickname = user.nickname;
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void get_User_Rank(RankIcon rankIcon, String nickname) {
		this.rankIcon = rankIcon;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "GET_USER_RANK";
			data.user.nickname = nickname;
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void update_recent_game(CalculateScreen calculateScreen, String firstHorse, String secondHorse) {
		this.calculateScreen = calculateScreen;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "UPDATE_RECENT_GAME";
			data.msg = firstHorse + ", " + secondHorse;
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void get_recent_game(GameInfo gameInfo) {
		this.gameInfo = gameInfo;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "GET_RECENT_GAME";
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void chesk_uesr(Screen screen) {
		this.screen = screen;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "CHEAK_USER";
			
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public void get_screen(WaitingScreen waitingScreen) {
		this.waitingScreen = waitingScreen;
		try {
			TCPData data = new TCPData();
			data.src = local.getHostAddress();
			data.dst = "GET_SCREEN";
			
			oos.writeObject(data);
			oos.flush();
			oos.reset();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	public TCPClient() {
		try {
			System.out.println("클라이언트 : 연결합니다");
			// 서버 켠 컴퓨터의 로컬 ip주소 넣어주면 됨
			// 집 ip : 192.168.35.10

			Socket soc = new Socket("192.168.20.39", 8888);

			oos = new ObjectOutputStream(soc.getOutputStream());
			ois = new ObjectInputStream(soc.getInputStream());
			
			local = InetAddress.getLocalHost();
			
			// 컴 하나로 임시테스트할때는 가짜 ip주소 넣어줌.  클라 켤때마다 숫자 바꿔줘야함

			local = InetAddress.getByName("192.168.35.24");

			
			new TCPClientReceiver().start();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "서버에 접속할 수 없습니다.", "연결 실패", JOptionPane.PLAIN_MESSAGE);
			System.exit(0);
		}
	}
}
