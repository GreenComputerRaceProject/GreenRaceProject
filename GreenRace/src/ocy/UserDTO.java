package ocy;

import java.io.Serializable;

public class UserDTO implements Serializable{
	
	private static final long serialVersionUID = 54897L;

	String id, pw, name, nickname, phone, about;
	long money;
	int tot, win, lose;
	
	public UserDTO() {
		
	}
	
	public UserDTO(TCPData data) {
		super();
		this.id = data.user.id;
		this.pw = data.user.pw;
		this.name = data.user.name;
		this.nickname = data.user.nickname;
		this.phone = data.user.phone;
		this.about = data.user.about;
		this.money = data.user.money;
		this.tot = data.user.tot;
		this.win = data.user.win;
		this.lose = data.user.lose;
	}

	public UserDTO(String id, String pw, String name, String nickname, String phone, String about, long money, int tot,
			int win, int lose) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nickname = nickname;
		this.phone = phone;
		this.about = about;
		this.money = money;
		this.tot = tot;
		this.win = win;
		this.lose = lose;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	public int getTot() {
		return tot;
	}

	public void setTot(int tot) {
		this.tot = tot;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	@Override
	public String toString() {
		return "[" + id + "," + pw + "," + name + "," + nickname + "," + phone
				+ "," + about + "," + money + "," + tot + "," + win + "," + lose + "]";
	}
	
	
}
