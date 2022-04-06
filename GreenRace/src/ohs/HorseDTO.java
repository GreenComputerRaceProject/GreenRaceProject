package ohs;

public class HorseDTO {
	
	int hid;
	String hname;
	int type;
	double speed;
	double firstspeed;
	double lastspeed;
	double stamina;
	boolean gender;
	int year;
	double weight;
	int state;
	String recentrecord;
	
	
	public HorseDTO() {
		
	}
	
	public HorseDTO(int hid, String hname, int type, double speed, double firstspeed, double lastspeed, double stamina,
			boolean gender, int year, double weight, int state, String recentrecord) {
		super();
		this.hid = hid;
		this.hname = hname;
		this.type = type;
		this.speed = speed;
		this.firstspeed = firstspeed;
		this.lastspeed = lastspeed;
		this.stamina = stamina;
		this.gender = gender;
		this.year = year;
		this.weight = weight;
		this.state = state;
		this.recentrecord = recentrecord;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public String getHname() {
		return hname;
	}
	public void setHname(String hname) {
		this.hname = hname;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getFirstspeed() {
		return firstspeed;
	}
	public void setFirstspeed(double firstspeed) {
		this.firstspeed = firstspeed;
	}
	public double getLastspeed() {
		return lastspeed;
	}
	public void setLastspeed(double lastspeed) {
		this.lastspeed = lastspeed;
	}
	public double getStamina() {
		return stamina;
	}
	public void setStamina(double stamina) {
		this.stamina = stamina;
	}
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getRecentrecord() {
		return recentrecord;
	}
	public void setRecentrecord(String recentrecord) {
		this.recentrecord = recentrecord;
	}

	@Override
	public String toString() {
		return "HorseDTO [hid=" + hid + ", hname=" + hname + ", type=" + type + ", speed=" + speed + ", firstspeed="
				+ firstspeed + ", lastspeed=" + lastspeed + ", stamina=" + stamina + ", gender=" + gender + ", year="
				+ year + ", weight=" + weight + ", state=" + state + ", recentrecord=" + recentrecord + "]";
	}
		
}
