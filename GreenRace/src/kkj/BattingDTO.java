package kkj;

public class BattingDTO {
	int horse_num;		//말 번호
	String nickname;	//유저닉넴 
	long gambling_money, tot_money;	//유저가 배팅한 금액, 전체유저가 배팅한 총 금액
	
	public BattingDTO() {
		// TODO Auto-generated constructor stub
	}

	public BattingDTO(int horse_num, String nickname, long gambling_money, long tot_money) {
		super();
		this.horse_num = horse_num;
		this.nickname = nickname;
		this.gambling_money = gambling_money;
		this.tot_money = tot_money;
	}

	public int getHorse_num() {
		return horse_num;
	}

	public void setHorse_num(int horse_num) {
		this.horse_num = horse_num;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public long getGambling_money() {
		return gambling_money;
	}

	public void setGambling_money(long gambling_money) {
		this.gambling_money = gambling_money;
	}

	public long getTot_money() {
		return tot_money;
	}

	public void setTot_money(long tot_money) {
		this.tot_money = tot_money;
	}

	@Override
	public String toString() {
		return "BattingDTO [horse_num=" + horse_num + ", nickname=" + nickname + ", gambling_money=" + gambling_money
				+ ", tot_money=" + tot_money + "]";
	}

	











}
