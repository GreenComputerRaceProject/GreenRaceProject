package ohs;

import java.io.Serializable;

public class HorseClass2 implements Serializable{
	
	private static final long serialVersionUID = 54868L;

	String hname;
	String type;
	double speed;
	double firstspeed;
	double lastspeed;
	double state;
	
	
	public HorseClass2(String hname, String type, String speed, String firstspeed,
						String lastspeed,String state) {
		super();
		this.hname = hname;
		this.type = type;
		this.speed = Double.parseDouble(speed);
		this.firstspeed = Double.parseDouble(firstspeed);
		this.lastspeed = Double.parseDouble(lastspeed);
		this.state = Double.parseDouble(state);
	}


	@Override
	public String toString() {
		return "HorseClass2 [hname=" + hname + ", type=" + type + ", speed=" + speed + ", firstspeed=" + firstspeed
				+ ", lastspeed=" + lastspeed + ", state=" + state + "]";
	}
	
}
