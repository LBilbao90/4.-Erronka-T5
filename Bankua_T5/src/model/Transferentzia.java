package model;

import java.util.Objects;

public class Transferentzia implements Komisioa{
	private double kantitatea;
	private String transferentziaData;
	private String kontzeptua;
	private String jasotzailea;
	private final String komisioa;
	
	// Constructors
	public Transferentzia(double kantitatea, String transferentziaData, String kotzeptua, String jasotzailea) {
		this.kantitatea = kantitatea;
		this.transferentziaData = transferentziaData;
		this.kontzeptua = kotzeptua;
		this.jasotzailea = jasotzailea;
		this.komisioa = "1.5";
	}
	
	public Transferentzia() {
		this.komisioa = "1.5";
	}

	// Getters
	public double getKantitatea() {
		return kantitatea;
	}
	public String getTransferentziaData() {
		return transferentziaData;
	}
	public String getKotzeptua() {
		return kontzeptua;
	}
	public String getJasotzailea() {
		return jasotzailea;
	}
	public String getKomisioa() {
		return komisioa;
	}
	
	// Setters
	public void setKantitatea(double kantitatea) {
		this.kantitatea = kantitatea;
	}
	public void setTransferentziaData(String transferentziaData) {
		this.transferentziaData = transferentziaData;
	}
	public void setKontzeptua(String kotzeptua) {
		this.kontzeptua = kotzeptua;
	}
	public void setJasotzailea(String jasotzailea) {
		this.jasotzailea = jasotzailea;
	}
	
	// ToString
	@Override
	public String toString() {
		return "Transferentzia kantitatea=" + kantitatea
				+ ", transferentziaData=" + transferentziaData + ", kotzeptua=" + kontzeptua + ", jasotzailea="
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
				&& komisioa.equals(other.komisioa)
				&& Objects.equals(kontzeptua, other.kontzeptua)
				&& Objects.equals(transferentziaData, other.transferentziaData);
	}

	// Methods
	@Override
	public double kalkulatuPrezioa(String kantitatea, String komisioa) {
		kantitatea=kantitatea.replace(",",".");	
		double kantitate = Double.parseDouble(kantitatea);
		return ((kantitate*Double.parseDouble(this.komisioa))/100)+kantitate;
	}
}
