package ocy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JOptionPane;

import ocy.TCPServerMain.Timer;
import ohs.HorseClass2;
import ohs.RandomEntry;


class TCPData implements Serializable{

	private static final long serialVersionUID = 54898L;

	String msg;
	String dst, src;

	UserDTO user;
	BetDTO_list bet_list;
	BetDTO_list win_list;
	BetDTO_Single bet_single;
	BetDTO_Place bet_place;
	BetDTO_Quinella bet_quinella;
	
	ArrayList<String> mems;
	ArrayList<HorseClass2> entry;
	ArrayList<Double> rate;
	ArrayList<String> recentGame;

	int time;

	public TCPData() {
		// TODO Auto-generated constructor stub
		user = new UserDTO();
		bet_list = new BetDTO_list();
		bet_single = new BetDTO_Single();
		bet_place = new BetDTO_Place();
		bet_quinella = new BetDTO_Quinella();
		recentGame = new ArrayList<String>();
	}

	@Override
	public String toString() {
		return "TCPData [src=" + src + ", dst=" + dst + ", msg=" + msg + ", mems=" + mems + "]";
	}

}

public class TCPServerMain {

	HashMap<String, ObjectOutputStream> map;
	ArrayList<String> currentUser;
	ArrayList<HorseClass2> entry = new RandomEntry().shuffle();
	ArrayList<String> recentGame;
	int round = 1;

	Timer timer = new Timer();
	boolean isTimer = true;
	boolean isRecentUpdatable = true;
	
	String wh1, wh2;

	public TCPServerMain() {
		try {
			map = new HashMap<String, ObjectOutputStream>();
			currentUser = new ArrayList<String>();
			recentGame = new ArrayList<String>();

			Collections.synchronizedMap(map);

			ServerSocket server = new ServerSocket(8888);
			System.out.println("서버시작");

			while(true) {
				Socket client = server.accept();
				System.out.println("서버 : 연결들어옴");
				new TCPSeverReceiver(client).start();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class Timer extends Thread {

		public int i = 60;

		@Override
		public void run() {
			try {
				for (; i >= 0; i--) {
					sleep(1000);
					if(i == 0 && currentUser.size() == 0) {
						i = 20;
					}
				}
				entry = new RandomEntry().shuffle();
				//	timer = null;
				//	isTimer = false;
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		public Timer() {
			start();
			new BetDAO().init_bet();
		}

		public int send_time() {
			return i;
		}
	}

	class TCPSeverReceiver extends Thread {

		ObjectInputStream ois;
		ObjectOutputStream oos;
		String name;

		public TCPSeverReceiver(Socket client) {
			try {
				oos = new ObjectOutputStream(client.getOutputStream());
				ois = new ObjectInputStream(client.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				while(true) {
					TCPData data = (TCPData)ois.readObject();

					System.out.println(data);

					if(data.dst.equals("서버")) {
						name = data.src;
						map.put(data.src, oos);

						for (Entry<String, ObjectOutputStream> entry : map.entrySet()) {
							System.out.println("[key]:" + entry.getKey() + ", [value]:" + entry.getValue());
						}
						firstGo(data);
					} else if(data.dst.equals("CHAT")) {
						chat(data);
					} else if(data.dst.equals("LOGIN")) {
						responseLogIn(data);
					} else if(data.dst.equals("USER_INFO")) {
						responseUserInfo(data);
					} else if(data.dst.equals("VERIFICATION_ID")) {
						responseIDVerification(data);
					} else if(data.dst.equals("VERIFICATION_NICKNAME")) {
						responseNickNameVerification(data);
					} else if(data.dst.equals("VERIFICATION_PHONE")) {
						responsePhoneVerification(data);
					} else if(data.dst.equals("USER_INSERT")) {
						responseUserInsert(data);
					} else if(data.dst.equals("FIND_ID")) {
						responseFindID(data);
					} else if(data.dst.equals("UPDATE_PW")) {
						responseUpdatePW(data);
					} else if(data.dst.equals("ENTRANCE_CHAT")) {
						entranceChat(data);
					} else if(data.dst.equals("BET_SINGLE")) {
						betSingle(data);
					} else if(data.dst.equals("BET_PLACE")) {
						betPlace(data);
					} else if(data.dst.equals("BET_QUINELLA")) {
						betQuinella(data);
					} else if(data.dst.equals("GET_TIME")) {
						sendTime(data);
					} else if(data.dst.equals("GET_TIME2")) {
						sendTime2(data);
					}else if(data.dst.equals("START_TIME")) {
						startTimer(data);
					}else if(data.dst.equals("GET_ENTRY")) {
						responseHorseEntry(data);
					} else if(data.dst.equals("GET_BET_RATE_SINGLE")) {
						responseBetRateSingle(data);
					} else if(data.dst.equals("GET_BET_RATE_PLACE")) {
						responseBetRatePlace(data);
					} else if(data.dst.equals("GET_BET_RATE_QUINELLA")) {
						responseBetRateQuinella(data);
					} else if(data.dst.equals("BET_ADJUSTMENT")) {
						excuteBetAdjustment(data);
					} else if(data.dst.equals("MONEY_CHARGE")) {
						excuteMoneyCharge(data);
					} else if(data.dst.equals("GAME_EXIT")) {
						System.out.println("클라가 나갔습니다");
						exitUser(data);
					} else if(data.dst.equals("GET_USER_RANK")) {
						responseUserRank(data);
					} else if(data.dst.equals("UPDATE_RECENT_GAME")) {
						if(isRecentUpdatable) {
							updateRecentGame(data);
						}
					} else if(data.dst.equals("GET_RECENT_GAME")) {
						responseRecentGame(data);
					} else {
						sendToOne(data);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				map.remove(name);
				TCPData data = new TCPData();

				data.src = "서버";
				data.dst = "서버";
				data.msg = "퇴장";
				firstGo(data);
			}
		}

		void startTimer(TCPData data) {

			if(timer.i <= 0) {
				System.out.println("타이머 초기화");
				timer = new Timer();
				isRecentUpdatable = true;

			}
			callScreen(data);
		}

		void firstGo(TCPData data) {
			data.mems = new ArrayList<String>(map.keySet());
			sendToAll(data);
		}

		void sendToAll(TCPData data) {
			for (ObjectOutputStream oo : map.values()) {
				try {
					oo.writeObject(data);
					oo.flush();
					oo.reset();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		void sendToOne(TCPData data) {
			try {
				ObjectOutputStream oo = map.get(data.dst);
				oo.writeObject(data);
				oo.flush();
				oo.reset();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		void responseLogIn(TCPData data) {

			TCPData response = new TCPData();
			response.src = "LOGIN";
			response.dst = data.src;
			response.msg = new UserDAO().logIn(data.user);

			sendToOne(response);
		}

		void responseUserInfo(TCPData data) {

			TCPData response = new TCPData();
			response.src = "USER_INFO";
			response.dst = data.src;
			response.user = new UserDAO().user_info(data.user);
			
			if(!currentUser.contains(response.user.nickname)) {
				currentUser.add(response.user.nickname);
			}

			sendToOne(response);
		}

		void responseIDVerification(TCPData data) {

			TCPData response = new TCPData();
			response.src = "VERIFICATION_ID";
			response.dst = data.src;
			response.msg = new UserDAO().id_verification(data.user);

			sendToOne(response);
		}

		void responseNickNameVerification(TCPData data) {

			TCPData response = new TCPData();
			response.src = "VERIFICATION_NICKNAME";
			response.dst = data.src;
			response.msg = new UserDAO().nickname_verification(data.user);

			sendToOne(response);
		}

		void responsePhoneVerification(TCPData data) {

			TCPData response = new TCPData();
			response.src = "VERIFICATION_PHONE";
			response.dst = data.src;
			response.msg = new UserDAO().phone_verification(data.user);

			sendToOne(response);
		}

		void responseUserInsert(TCPData data) {

			TCPData response = new TCPData();
			response.src = "USER_INSERT";
			response.dst = data.src;
			response.msg = new UserDAO().user_insert(data.user);

			sendToOne(response);
		}

		void responseFindID(TCPData data) {
			TCPData response = new TCPData();
			response.src = "FIND_ID";
			response.dst = data.src;
			response.user.id = new UserDAO().id_find(data.user);
			response.msg = "COMPLETE";

			sendToOne(response);
		}

		void responseUpdatePW(TCPData data) {
			TCPData response = new TCPData();
			response.src = "UPDATE_PW";
			response.dst = data.src;
			response.msg = new UserDAO().pw_update(data.user);

			sendToOne(response);
		}

		void chat(TCPData data) {
			TCPData response = new TCPData();
			response.src = "CHAT";
			response.user.nickname = data.user.nickname;
			response.msg = data.msg;

			sendToAll(response);
		}

		void entranceChat(TCPData data) {
			TCPData response = new TCPData();
			response.src = "ENTRANCE_CHAT";
			response.mems = currentUser;

			sendToAll(response);
		}

		void betSingle(TCPData data) {
			TCPData response = new TCPData();
			response.src = "BET_SINGLE";
			response.dst = data.src;
			response.msg = new BetDAO().single_betting(data);

			sendToOne(response);
		}

		void betPlace(TCPData data) {
			TCPData response = new TCPData();
			response.src = "BET_PLACE";
			response.dst = data.src;
			response.msg = new BetDAO().place_betting(data);

			sendToOne(response);
		}

		void betQuinella(TCPData data) {
			TCPData response = new TCPData();
			response.src = "BET_QUINELLA";
			response.dst = data.src;
			response.msg = new BetDAO().quinella_betting(data);

			sendToOne(response);
		}

		void sendTime(TCPData data) {
			TCPData response = new TCPData();
			response.src = "GET_TIME";
			response.dst = data.src;
			response.time = timer.send_time();

			sendToOne(response);
		}

		void sendTime2(TCPData data) {
			TCPData response = new TCPData();
			response.src = "GET_TIME2";
			response.dst = data.src;
			response.time = timer.send_time();

			sendToOne(response);
		}

		void startTime(TCPData data) {
			TCPData response = new TCPData();
			response.src = "START_TIME";
			response.dst = data.src;

			sendToOne(response);
		}

		void callScreen(TCPData data) {
			TCPData response = new TCPData();
			response.src = "CALL_SCREEN";
			response.dst = data.src;
			response.mems = currentUser;
			
			sendToOne(response);
		}

		void responseHorseEntry(TCPData data) {
			TCPData response = new TCPData();
			response.src = "GET_ENTRY";
			response.dst = data.src;
			response.entry = entry;

			System.out.println(entry);

			sendToOne(response);
		}

		void responseBetRateSingle(TCPData data) {
			TCPData response = new TCPData();
			response.src = "GET_BET_RATE_SINGLE";
			response.dst = data.src;
			response.rate = new BetDAO().single_rate(data);

			sendToAll(response);
		}

		void responseBetRatePlace(TCPData data) {
			TCPData response = new TCPData();
			response.src = "GET_BET_RATE_PLACE";
			response.dst = data.src;
			response.rate = new BetDAO().place_rate(data);

			sendToAll(response);
		}

		void responseBetRateQuinella(TCPData data) {
			TCPData response = new TCPData();
			response.src = "GET_BET_RATE_QUINELLA";
			response.dst = data.src;
			response.rate = new BetDAO().quinella_rate(data);

			sendToAll(response);
		}

		void excuteMoneyCharge(TCPData data) {
			TCPData response = new TCPData();
			response.src = "MONEY_CHARGE";
			response.dst = data.src;
			response.msg = new UserDAO().excute_money_charge(data.user);

			sendToOne(response);
		}
		
		void excuteBetAdjustment(TCPData data) {
			TCPData response = new TCPData();
			response.src = "BET_ADJUSTMENT";
			response.dst = data.src;
			response.msg = new UserDAO().excute_bet_adjustment(data);

			sendToOne(response);
		}
		
		void exitUser(TCPData data) {
			currentUser.remove(data.user.nickname);
			map.remove(data.src);
			
			try {
				oos.close();
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			entranceChat(data);
		}
		
		void responseUserRank(TCPData data) {
			TCPData response = new TCPData();
			response.src = "GET_USER_RANK";
			response.dst = data.src;
			response.user = new UserDAO().user_rank(data.user);

			sendToOne(response);
		}
		
		void updateRecentGame(TCPData data) {
			recentGame.add(round + "회차 : " + data.msg);
			round++;
			System.out.println(recentGame);
			isRecentUpdatable = false;
		}
		
		void responseRecentGame(TCPData data) {
			TCPData response = new TCPData();
			response.src = "GET_RECENT_GAME";
			response.dst = data.src;
			response.recentGame = recentGame;

			sendToOne(response);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new TCPServerMain();
	}

}
