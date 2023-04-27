package model;

import java.util.Objects;

public abstract class Pertsona {
	protected String nan;
	protected String izena;
	protected String abizena;
	protected String jaiotzeData;
	protected String sexua;
	protected String telefonoa;
	protected String pasahitza;
	
	// Constructors
	public Pertsona(String nan, String izena, String abizena, String jaiotzeData, String sexua, String telefonoa, String pasahitza) {
		this.nan = nan;
		this.izena = izena;
		this.abizena = abizena;
		this.jaiotzeData = jaiotzeData;
		this.sexua = sexua;
		this.telefonoa = telefonoa;
		this.pasahitza = pasahitza;
	}
	
	public Pertsona() {}
	
	// Getters
	public String getNan() {
		return nan;
	}
	public String getIzena() {
		return izena;
	}
	public String getAbizena() {
		return abizena;
	}
	public String getJaiotzeData() {
		return jaiotzeData;
	}
	public String getSexua() {
		return sexua;
	}
	public String getTelefonoa() {
		return telefonoa;
	}
	public String getPasahitza() {
		return pasahitza;
	}
	
	// Setters
	public void setNan(String nan) {
		this.nan = nan;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}
	public void setJaiotzeData(String jaiotzeData) {
		this.jaiotzeData = jaiotzeData;
	}
	public void setSexua(String sexua) {
		this.sexua = sexua;
	}
	public void setTelefonoa(String telefonoa) {
		this.telefonoa = telefonoa;
	}
	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}
	
	// ToString
	@Override
	public String toString() {
		return "Pertsona nan=" + nan + ", izena=" + izena + ", abizena=" + abizena + ", jaiotzeData=" + jaiotzeData
				+ ", sexua=" + sexua + ", telefonoa=" + telefonoa + ", pasahitza=" + pasahitza;
	}
	
	// Equals
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Pertsona other = (Pertsona) obj;
		return Objects.equals(nan, other.nan);
	}

	// Methods
	public void erakutsiMugimenduak() {	
	}
}
