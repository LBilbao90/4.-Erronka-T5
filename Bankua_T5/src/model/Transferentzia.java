package model;

import java.util.Objects;

public class Transferentzia implements Komisioa{
	private double kantitatea;
	private String transferentziaData;
	private String kotzeptua;
	private String jasotzailea;
	private final double komisioa;
	
	// Constructors
	public Transferentzia(double kantitatea, String transferentziaData, String kotzeptua, String jasotzailea) {
		this.kantitatea = kantitatea;
		this.transferentziaData = transferentziaData;
		this.kotzeptua = kotzeptua;
		this.jasotzailea = jasotzailea;
		this.komisioa = 2;
	}
	
	public Transferentzia() {
		this.komisioa = 2;
	}

	// Getters
	public double getKantitatea() {
		return kantitatea;
	}
	public String getTransferentziaData() {
		return transferentziaData;
	}
	public String getKotzeptua() {
		return kotzeptua;
	}
	public String getJasotzailea() {
		return jasotzailea;
	}
	public double getKomisioa() {
		return komisioa;
	}
	
	// Setters
	public void setKantitatea(double kantitatea) {
		this.kantitatea = kantitatea;
	}
	public void setTransferentziaData(String transferentziaData) {
		this.transferentziaData = transferentziaData;
	}
	public void setKotzeptua(String kotzeptua) {
		this.kotzeptua = kotzeptua;
	}
	public void setJasotzailea(String jasotzailea) {
		this.jasotzailea = jasotzailea;
	}
	
	// ToString
	@Override
	public String toString() {
		return "Transferentzia kantitatea=" + kantitatea
				+ ", transferentziaData=" + transferentziaData + ", kotzeptua=" + kotzeptua + ", jasotzailea="
				+ jasotzailea + ", komisioa=" + komisioa;
	}
	
	// Equals
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Transferentzia other = (Transferentzia) obj;
		return Objects.equals(jasotzailea, other.jasotzailea)
				&& Double.doubleToLongBits(kantitatea) == Double.doubleToLongBits(other.kantitatea)
				&& Double.doubleToLongBits(komisioa) == Double.doubleToLongBits(other.komisioa)
				&& Objects.equals(kotzeptua, other.kotzeptua)
				&& Objects.equals(transferentziaData, other.transferentziaData);
	}

	// Methods
	@Override
	public double kalkulatuPrezioa() {
		return 0;
	}
}
