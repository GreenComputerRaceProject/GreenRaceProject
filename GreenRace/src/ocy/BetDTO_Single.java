package ocy;

import java.io.Serializable;

public class BetDTO_Single implements Serializable {
	
	private static final long serialVersionUID = 54896L;
	
	String hname;
	long money;
	
	public BetDTO_Single() {
		
	}

	public BetDTO_Single(String hname, long money) {
		super();
		this.hname = hname;
		this.money = money;
	}

	public String getHname() {
		return hname;
	}

	public void setHname(String hname) {
		this.hname = hname;
	}

	public long getMoney() {
		return money;
	}

	public void setMoney(long money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "[" + hname + "," + money + "]";
	}

}
