package model;

import java.util.Date;
import java.util.Objects;

public class DiruSarrera {
	private int idSarrera;
	private double kantitatea;
	private Date sarreraData;
	private String kontzeptua;
	private String igortzailea;
	
	// Constructors
	public DiruSarrera(int idSarrera, double kantitatea, Date sarreraData, String kontzeptua, String igortzailea) {
		this.idSarrera = idSarrera;
		this.kantitatea = kantitatea;
		this.sarreraData = sarreraData;
		this.kontzeptua = kontzeptua;
		this.igortzailea = igortzailea;
	}
	
	public DiruSarrera() {
	}
	
	// Getters and Setters
	public int getIdSarrera() {
		return idSarrera;
	}
	public void setIdSarrera(int idSarrera) {
		this.idSarrera = idSarrera;
	}
	public double getKantitatea() {
		return kantitatea;
	}
	public void setKantitatea(double kantitatea) {
		this.kantitatea = kantitatea;
	}
	public Date getSarreraData() {
		return sarreraData;
	}
	public void setSarreraData(Date sarreraData) {
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
		return "DiruSarrera idSarrera=" + idSarrera + ", kantitatea=" + kantitatea + ", sarreraData=" + sarreraData
				+ ", kontzeptua=" + kontzeptua + ", igortzailea=" + igortzailea;
	}
	
	// Equals
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		DiruSarrera other = (DiruSarrera) obj;
		return Objects.equals(idSarrera, other.idSarrera);
	}
}
