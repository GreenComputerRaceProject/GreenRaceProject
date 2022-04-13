package ocy;

import java.io.Serializable;

public class BetDTO_Place implements Serializable {
	
	private static final long serialVersionUID = 54599L;

	String hname;
	long money;
	
	public BetDTO_Place() {
		
	}

	public BetDTO_Place(String hname, long money) {
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
