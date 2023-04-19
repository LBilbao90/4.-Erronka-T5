package model;

import java.util.Date;

public class Transferentzia implements Komisioa{
	private int idTransferentzia;
	private double kantitatea;
	private Date transferentziaData;
	private String kotzeptua;
	private String jasotzailea;
	private final double komisioa;
	
	// Constructors
	public Transferentzia(int idTransferentzia, double kantitatea, Date transferentziaData, String kotzeptua, String jasotzailea, double komisioa) {
		this.idTransferentzia = idTransferentzia;
		this.kantitatea = kantitatea;
		this.transferentziaData = transferentziaData;
		this.kotzeptua = kotzeptua;
		this.jasotzailea = jasotzailea;
		this.komisioa = komisioa;
	}

	// Getters and Setters
	public int getIdTransferentzia() {
		return idTransferentzia;
	}
	public void setIdTransferentzia(int idTransferentzia) {
		this.idTransferentzia = idTransferentzia;
	}
	public double getKantitatea() {
		return kantitatea;
	}
	public void setKantitatea(double kantitatea) {
		this.kantitatea = kantitatea;
	}
	public Date getTransferentziaData() {
		return transferentziaData;
	}
	public void setTransferentziaData(Date transferentziaData) {
		this.transferentziaData = transferentziaData;
	}
	public String getKotzeptua() {
		return kotzeptua;
	}
	public void setKotzeptua(String kotzeptua) {
		this.kotzeptua = kotzeptua;
	}
	public String getJasotzailea() {
		return jasotzailea;
	}
	public void setJasotzailea(String jasotzailea) {
		this.jasotzailea = jasotzailea;
	}
	public double getKomisioa() {
		return komisioa;
	}
	
	// ToString
	@Override
	public String toString() {
		return "Transferentzia idTransferentzia=" + idTransferentzia + ", kantitatea=" + kantitatea
				+ ", transferentziaData=" + transferentziaData + ", kotzeptua=" + kotzeptua + ", jasotzailea="
				+ jasotzailea + ", komisioa=" + komisioa;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Transferentzia other = (Transferentzia) obj;
		return idTransferentzia == other.idTransferentzia;
	}

	// Methods
	@Override
	public double kalkulatuPrezioa() {
		return 0;
	}

}
