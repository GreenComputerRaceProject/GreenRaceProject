package ohs;

import java.io.Serializable;

public class HorseClass2 implements Serializable{
	
	private static final long serialVersionUID = 54868L;

	String hname;
	String type;
	double speed;
	double firstspeed;
	double lastspeed;
	double stamina;
	boolean gender;
	int year;
	double weight;
	double state;
	String recentrecord;
	
	
	public HorseClass2(String hname, String type, String speed, String firstspeed,
						String lastspeed, String stamina,
						String gender, String year, String weight, String state, String recentrecord) {
		super();
		this.hname = hname;
		this.type = type;
		this.speed = Double.parseDouble(speed);
		this.firstspeed = Double.parseDouble(firstspeed);
		this.lastspeed = Double.parseDouble(lastspeed);
		this.stamina = Double.parseDouble(stamina);
		this.gender = Boolean.parseBoolean(gender);
		this.year = Integer.parseInt(year);
		this.weight = Double.parseDouble(weight);
		this.state = Double.parseDouble(state);
		this.recentrecord = recentrecord;
	}


	@Override
	public String toString() {
		return "HorseClass2 [hname=" + hname + ", type=" + type + ", speed=" + speed + ", firstspeed=" + firstspeed
				+ ", lastspeed=" + lastspeed + ", stamina=" + stamina + ", gender=" + gender + ", year=" + year
				+ ", weight=" + weight + ", state=" + state + ", recentrecord=" + recentrecord + "]";
	}



	
	
	
	
}
