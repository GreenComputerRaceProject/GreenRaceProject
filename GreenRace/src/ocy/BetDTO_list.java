package ocy;

import java.io.Serializable;
import java.util.ArrayList;

public class BetDTO_list implements Serializable {
	
	private static final long serialVersionUID = 51279L;
	
	ArrayList<BetDTO_Single> single;
	ArrayList<BetDTO_Place> place;
	ArrayList<BetDTO_Quinella> quinella;

	public BetDTO_list() {
		single = new ArrayList<BetDTO_Single>();
		place = new ArrayList<BetDTO_Place>();
		quinella = new ArrayList<BetDTO_Quinella>();
	}

	public BetDTO_list(ArrayList<BetDTO_Single> single, ArrayList<BetDTO_Place> place,
			ArrayList<BetDTO_Quinella> quinella) {
		super();
		this.single = single;
		this.place = place;
		this.quinella = quinella;
	}

	public ArrayList<BetDTO_Single> getSingle() {
		return single;
	}

	public void setSingle(ArrayList<BetDTO_Single> single) {
		this.single = single;
	}

	public ArrayList<BetDTO_Place> getPlace() {
		return place;
	}

	public void setPlace(ArrayList<BetDTO_Place> place) {
		this.place = place;
	}

	public ArrayList<BetDTO_Quinella> getQuinella() {
		return quinella;
	}

	public void setQuinella(ArrayList<BetDTO_Quinella> quinella) {
		this.quinella = quinella;
	}

	@Override
	public String toString() {
		return "BetDTO_list [single=" + single + ", place=" + place + ", quinella=" + quinella + "]";
	}

	
	
	
}
