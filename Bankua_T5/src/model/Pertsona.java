package model;

import java.util.Date;
import java.util.Objects;

public class Pertsona {
	protected String nan;
	protected String izena;
	protected String abizena;
	protected Date jaiotzeData;
	protected Generoa  sexua;
	protected String telefonoa;
	protected String pasahitza;
	
	public enum Generoa {
		emakumea, gizona
	}
	
	// Constructors
	public Pertsona(String nan, String izena, String abizena, Date jaiotzeData, Generoa sexua, String telefonoa, String pasahitza) {
		this.nan = nan;
		this.izena = izena;
		this.abizena = abizena;
		this.jaiotzeData = jaiotzeData;
		this.sexua = sexua;
		this.telefonoa = telefonoa;
		this.pasahitza = pasahitza;
	}
	
	// Getters and Setters
	public String getNan() {
		return nan;
	}
	public void setNan(String nan) {
		this.nan = nan;
	}
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public String getAbizena() {
		return abizena;
	}
	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}
	public Date getJaiotzeData() {
		return jaiotzeData;
	}
	public void setJaiotzeData(Date jaiotzeData) {
		this.jaiotzeData = jaiotzeData;
	}
	public Generoa getSexua() {
		return sexua;
	}
	public void setSexua(Generoa sexua) {
		this.sexua = sexua;
	}
	public String getTelefonoa() {
		return telefonoa;
	}
	public void setTelefonoa(String telefonoa) {
		this.telefonoa = telefonoa;
	}
	public String getPasahitza() {
		return pasahitza;
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
