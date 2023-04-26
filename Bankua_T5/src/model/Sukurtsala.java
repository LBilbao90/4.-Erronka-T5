package model;

import java.util.ArrayList;
import java.util.Objects;

public class Sukurtsala {
	private int idSukurtsala;
	private String kodSukurtsala;
	private String kokalekua;
	private ArrayList<KontuBankario> kontuBankarioak;
	private ArrayList<Langilea> langileak;
	
	// Constructors
	public Sukurtsala(int idSukurtsala, String kodSukurtsala, String kokalekua, ArrayList<KontuBankario> kontuBankarioak, ArrayList<Langilea> langileak) {
		this.idSukurtsala = idSukurtsala;
		this.kodSukurtsala = kodSukurtsala;
		this.kokalekua = kokalekua;
		this.kontuBankarioak = kontuBankarioak;
		this.langileak = langileak;
	}

	public Sukurtsala() {
	}
	
	// Getters and Setters
	public int getIdSukurtsala() {
		return idSukurtsala;
	}
	public void setIdSukurtsala(int idSukurtsala) {
		this.idSukurtsala = idSukurtsala;
	}
	public String getKokalekua() {
		return kokalekua;
	}
	public void setKokalekua(String kokalekua) {
		this.kokalekua = kokalekua;
	}	
	public ArrayList<KontuBankario> getKontuBankarioak() {
		return kontuBankarioak;
	}
	public void setKontuBankarioak(ArrayList<KontuBankario> kontuBankarioak) {
		this.kontuBankarioak = kontuBankarioak;
	}
	public String getKodSukurtsala() {
		return kodSukurtsala;
	}
	public void setKodSukurtsala(String kodSukurtsala) {
		this.kodSukurtsala = kodSukurtsala;
	}
	public ArrayList<Langilea> getLangileak() {
		return langileak;
	}
	public void setLangileak(ArrayList<Langilea> langileak) {
		this.langileak = langileak;
	}

	// ToString
	@Override
	public String toString() {
		return "Sukurtsala idSukurtsala=" + idSukurtsala + ", kodSukurtsala=" + kodSukurtsala + ", kokalekua="
				+ kokalekua + ", kontuBankarioak=" + kontuBankarioak;
	}
	
	// Equals
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Sukurtsala other = (Sukurtsala) obj;
		return Objects.equals(idSukurtsala, other.idSukurtsala);
	}
}
