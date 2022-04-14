package ocy;

import java.io.Serializable;

public class BetDTO_Quinella implements Serializable {
	
	private static final long serialVersionUID = 55296L;
	
	String hname1;
	String hname2;
	long money;
	
	public BetDTO_Quinella() {
		
	}

	public BetDTO_Quinella(String hname1, String hname2, long money) {
		super();
		this.hname1 = hname1;
		this.hname2 = hname2;
		this.money = money;
	}

	public String getHname1() {
		return hname1;
	}

	public void setHname1(String hname1) {
		this.hname1 = hname1;
	}

	public String getHname2() {
		return hname2;
	}

	public void setHname2(String hname2) {
		this.hname2 = hname2;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "[" + hname1 + "," + hname2 + "," + money + "]";
	}

	

}
