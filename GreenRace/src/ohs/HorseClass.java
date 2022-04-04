package ohs;

public class HorseClass {
	
	int hid;
	String hname;
	double speed;
	double fristspeed;
	double lastspeed;
	double stamina;
	double state;
	
	public HorseClass(String hid, String hname, String 
			speed, String fristspeed, String lastspeed, String stamina, String state) {
		super();
		this.hid = Integer.parseInt(hid);
		this.hname = hname;
		this.speed = Double.parseDouble(speed);
		this.fristspeed = Double.parseDouble(fristspeed);
		this.lastspeed = Double.parseDouble(lastspeed);
		this.stamina = Double.parseDouble(state);
		this.state = Double.parseDouble(state);
	}


	@Override
	public String toString() {
		return "HorseClass [hid=" + hid + ", hname=" + hname + ", speed=" + speed + ", fristspeed=" + fristspeed
				+ ", lastspeed=" + lastspeed + ", state=" + state + "]";
	}
	
	

}
