package model;

import java.util.Objects;

public class Hipoteka implements Komisioa{
	private double kantitatea;
	private double ordaindutakoa;
	private final double komisioa;
	private String hasieraData;
	private String amaieraData;
	private String egoera; //eskatuta, onartuta, itxita, errefusatuta
	
	// Constructors
	public Hipoteka(double kantitatea, double ordaindutakoa, double komisioa, String hasieraData, String amaieraData, String egoera) {
		this.kantitatea = kantitatea;
		this.ordaindutakoa = ordaindutakoa;
		this.komisioa = komisioa;
		this.hasieraData = hasieraData;
		this.amaieraData = amaieraData;
		this.egoera=egoera;
	}
	
	public Hipoteka() {
		this.komisioa = 10;
	}

	// Getters
	public double getKantitatea() {
		return kantitatea;
	}
	public double getOrdaindutakoa() {
		return ordaindutakoa;
	}
	public double getKomisioa() {
		return komisioa;
	}
	public String getHasieraData() {
		return hasieraData;
	}
	public String getAmaieraData() {
		return amaieraData;
	}
	public String getEgoera() {
		return egoera;
	}
	
	// Setters
	public void setKantitatea(double kantitatea) {
		this.kantitatea = kantitatea;
	}
	public void setOrdaindutakoa(double ordaindutakoa) {
		this.ordaindutakoa = ordaindutakoa;
	}
	public void setHasieraData(String hasieraData) {
		this.hasieraData = hasieraData;
	}
	public void setAmaieraData(String amaieraData) {
		this.amaieraData = amaieraData;
	}
	public void setEgoera(String egoera) {
		this.egoera = egoera;
	}
	
	// ToString
	@Override
	public String toString() {
		return "Hipoteka kantitatea=" + kantitatea + ", ordaindutakoa=" + ordaindutakoa
				+ ", komisioa=" + komisioa + ", hasieraData=" + hasieraData + ", amaieraData=" + amaieraData
				+ ", egoera=" + egoera;
	}
	
	// Equals
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Hipoteka other = (Hipoteka) obj;
		return Objects.equals(amaieraData, other.amaieraData) && Objects.equals(egoera, other.egoera)
				&& Objects.equals(hasieraData, other.hasieraData)
				&& Double.doubleToLongBits(kantitatea) == Double.doubleToLongBits(other.kantitatea)
				&& Double.doubleToLongBits(komisioa) == Double.doubleToLongBits(other.komisioa)
				&& Double.doubleToLongBits(ordaindutakoa) == Double.doubleToLongBits(other.ordaindutakoa);
	}

	@Override
	public double kalkulatuPrezioa() {
		return 0;
	}
}
