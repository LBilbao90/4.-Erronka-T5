package model;

import java.util.ArrayList;

public abstract class Langilea extends Pertsona{
	protected String lanpostu;
	private ArrayList<Sukurtsala> sukurtsalak;
	
	// Constructors
	public Langilea(String nan, String izena, String abizena, String jaiotzeData, String sexua, String telefonoa, String pasahitza, String lanpostu, ArrayList<Sukurtsala> sukurtsalak) {
		super(nan, izena, abizena, jaiotzeData, sexua, telefonoa, pasahitza);
		this.lanpostu = lanpostu;
		this.sukurtsalak = sukurtsalak;
	}
	
	public Langilea() {
	}

	// Getters
	public String getLanpostu() {
		return lanpostu;
	}	
	public ArrayList<Sukurtsala> getSukurtsalak() {
		return sukurtsalak;
	}
	
	// Setters
	public void setLanpostu(String lanpostu) {
		this.lanpostu = lanpostu;
	}
	public void setSukurtsalak(ArrayList<Sukurtsala> sukurtsalak) {
		this.sukurtsalak = sukurtsalak;
	}

	// ToString
	@Override
	public String toString() {
		return super.toString() + ", Lanpostu=" + lanpostu + ", Sukurtsalak=" + sukurtsalak;
	}
	
	// Methods
	public void ikusiEntitateKontua() {}
}