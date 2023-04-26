package model;

import java.util.Objects;

public class DiruSarrera {
	private double kantitatea;
	private String sarreraData;
	private String kontzeptua;
	private String igortzailea;
	
	// Constructors
	public DiruSarrera(double kantitatea, String sarreraData, String kontzeptua, String igortzailea) {
		this.kantitatea = kantitatea;
		this.sarreraData = sarreraData;
		this.kontzeptua = kontzeptua;
		this.igortzailea = igortzailea;
	}
	
	public DiruSarrera() {
	}
	
	// Getters and Setters
	public double getKantitatea() {
		return kantitatea;
	}
	public void setKantitatea(double kantitatea) {
		this.kantitatea = kantitatea;
	}
	public String getSarreraData() {
		return sarreraData;
	}
	public void setSarreraData(String sarreraData) {
		this.sarreraData = sarreraData;
	}
	public String getKontzeptua() {
		return kontzeptua;
	}
	public void setKontzeptua(String kontzeptua) {
		this.kontzeptua = kontzeptua;
	}
	public String getIgortzailea() {
		return igortzailea;
	}
	public void setIgortzailea(String igortzailea) {
		this.igortzailea = igortzailea;
	}
	
	// ToString
	@Override
	public String toString() {
		return "DiruSarrera kantitatea=" + kantitatea + ", sarreraData=" + sarreraData
				+ ", kontzeptua=" + kontzeptua + ", igortzailea=" + igortzailea;
	}
	
	// Equals
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		DiruSarrera other = (DiruSarrera) obj;
		return Objects.equals(igortzailea, other.igortzailea)
				&& Double.doubleToLongBits(kantitatea) == Double.doubleToLongBits(other.kantitatea)
				&& Objects.equals(kontzeptua, other.kontzeptua) && Objects.equals(sarreraData, other.sarreraData);
	}
}
