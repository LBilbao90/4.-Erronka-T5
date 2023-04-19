package model;

import java.util.Date;

public class Hipoteka implements Komisioa{
	private int idHipoteka;
	private double kantitatea;
	private double ordaindutakoa;
	private final double komisioa;
	private Date hasieraData;
	private Date amaieraData;
	
	
	public Hipoteka(int idHipoteka, double kantitatea, double ordaindutakoa, double komisioa, Date hasieraData, Date amaieraData) {
		this.idHipoteka = idHipoteka;
		this.kantitatea = kantitatea;
		this.ordaindutakoa = ordaindutakoa;
		this.komisioa = komisioa;
		this.hasieraData = hasieraData;
		this.amaieraData = amaieraData;
	}

	// Getters and Setters
	public int getIdHipoteka() {
		return idHipoteka;
	}
	public void setIdHipoteka(int idHipoteka) {
		this.idHipoteka = idHipoteka;
	}
	public double getKantitatea() {
		return kantitatea;
	}
	public void setKantitatea(double kantitatea) {
		this.kantitatea = kantitatea;
	}
	public double getOrdaindutakoa() {
		return ordaindutakoa;
	}
	public void setOrdaindutakoa(double ordaindutakoa) {
		this.ordaindutakoa = ordaindutakoa;
	}
	public double getKomisioa() {
		return komisioa;
	}
	public Date getHasieraData() {
		return hasieraData;
	}
	public void setHasieraData(Date hasieraData) {
		this.hasieraData = hasieraData;
	}
	public Date getAmaieraData() {
		return amaieraData;
	}
	public void setAmaieraData(Date amaieraData) {
		this.amaieraData = amaieraData;
	}


	@Override
	public double kalkulatuPrezioa() {
		return 0;
	}

}
