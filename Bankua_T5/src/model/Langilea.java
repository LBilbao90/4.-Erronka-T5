package model;

import java.util.Date;

public abstract class Langilea extends Pertsona{
	protected String sukurtsala;
	
	// Constructors
	public Langilea(String nan, String izena, String abizena, Date jaiotzeData, Generoa sexua, String telefonoa, String pasahitza, String sukurtsala) {
		super(nan, izena, abizena, jaiotzeData, sexua, telefonoa, pasahitza);
		this.sukurtsala = sukurtsala;
	}

	// Getters and Setters
	public String getSukurtsala() {
		return sukurtsala;
	}
	public void setSukurtsala(String sukurtzala) {
		this.sukurtsala = sukurtzala;
	}
	
	// ToString
	@Override
	public String toString() {
		return super.toString() + " Sukurtsala=" + sukurtsala;
	}
	
	// Methods
	public void ikusiEntitateKontua() {}
}