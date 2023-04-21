package model;

import java.util.Date;

public abstract class Langilea extends Pertsona{
	protected String sukurtsala;
	
	// Constructors
	public Langilea(String nan, String izena, String abizena, Date jaiotzeData, Generoa sexua, String telefonoa, String pasahitza, String sukurtzala) {
		super(nan, izena, abizena, jaiotzeData, sexua, telefonoa, pasahitza);
		this.sukurtsala = sukurtzala;
	}

	// Getters and Setters
	public String getSukurtzala() {
		return sukurtsala;
	}
	public void setSukurtzala(String sukurtzala) {
		this.sukurtsala = sukurtzala;
	}
	
	// ToString
	@Override
	public String toString() {
		return super.toString() + " Sukurtzala=" + sukurtsala;
	}
	
	// Methods
	public void ikusiEntitateKontua() {}
}