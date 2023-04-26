package model;


public abstract class Langilea extends Pertsona{
	protected String lanpostu;
	
	// Constructors
	public Langilea(String nan, String izena, String abizena, String jaiotzeData, String sexua, String telefonoa, String pasahitza, String lanpostu) {
		super(nan, izena, abizena, jaiotzeData, sexua, telefonoa, pasahitza);
		this.lanpostu = lanpostu;
	}
	
	public Langilea() {
	}

	// Getters and Setters
	public String getLanpostu() {
		return lanpostu;
	}
	public void setLanpostu(String lanpostu) {
		this.lanpostu = lanpostu;
	}
	
	// ToString
	@Override
	public String toString() {
		return super.toString() + " Lanpostu=" + lanpostu;
	}
	
	// Methods
	public void ikusiEntitateKontua() {}
}