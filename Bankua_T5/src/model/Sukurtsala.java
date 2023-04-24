package model;

import java.util.ArrayList;
import java.util.Objects;

public class Sukurtsala {
	private int idSukurtsala;
	private int kodSukurtsala;
	private String kokalekua;
	private int postaKodea;
	private ArrayList<KontuBankarioa> kontuBankarioak;
	
	// Constructors
	public Sukurtsala(int idSukurtsala, int kodSukurtsala, String kokalekua, int postaKodea, ArrayList<KontuBankarioa> kontuBankarioak) {
		this.idSukurtsala = idSukurtsala;
		this.kodSukurtsala = kodSukurtsala;
		this.kokalekua = kokalekua;
		this.postaKodea = postaKodea;
		this.kontuBankarioak = kontuBankarioak;
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
	public int getPostaKodea() {
		return postaKodea;
	}
	public void setPostaKodea(int postaKodea) {
		this.postaKodea = postaKodea;
	}	
	public ArrayList<KontuBankarioa> getKontuBankarioak() {
		return kontuBankarioak;
	}
	public void setKontuBankarioak(ArrayList<KontuBankarioa> kontuBankarioak) {
		this.kontuBankarioak = kontuBankarioak;
	}
	public int getKodSukurtsala() {
		return kodSukurtsala;
	}
	public void setKodSukurtsala(int kodSukurtsala) {
		this.kodSukurtsala = kodSukurtsala;
	}

	// ToString
	@Override
	public String toString() {
		return "Sukurtsala idSukurtsala=" + idSukurtsala + ", kodSukurtsala=" + kodSukurtsala + ", kokalekua="
				+ kokalekua + ", postaKodea=" + postaKodea + ", kontuBankarioak=" + kontuBankarioak;
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
