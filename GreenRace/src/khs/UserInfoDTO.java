package khs;

import java.io.Serializable;

public class UserInfoDTO implements Serializable{
	
	private static final long serialVersionUID = 1685L;
	
	String user_id, nickname;
	Integer totgame, win, lose, rank;
	long money;

	public UserInfoDTO() {
		// TODO Auto-generated constructor stub
	}

	public UserInfoDTO(String user_id, String nickname, Integer totgame, Integer win, Integer lose, Integer rank,
			long money) {
		super();
		this.user_id = user_id;
		this.nickname = nickname;
		this.totgame = totgame;
		this.win = win;
		this.lose = lose;
		this.rank = rank;
		this.money = money;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getTotgame() {
		return totgame;
	}

	public void setTotgame(Integer totgame) {
		this.totgame = totgame;
	}

	public Integer getWin() {
		return win;
	}

	public void setWin(Integer win) {
		this.win = win;
	}

	public Integer getLose() {
		return lose;
	}

	public void setLose(Integer lose) {
		this.lose = lose;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "[" + nickname + "," + user_id + "," + money + "," + totgame + "," + win+ "," + lose +  "," + rank + "]";
	}
	
	
}
