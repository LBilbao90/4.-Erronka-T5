package model;

import java.util.ArrayList;
import java.util.Objects;

public class Sukurtsala {
	private int idSukurtsala;
	private String kokalekua;
	private int postaKodea;
	private ArrayList<KontuBankarioa> kontuBankarioak;
	
	// Constructors
	public Sukurtsala(int idSukurtsala, String kokalekua, int postaKodea, ArrayList<KontuBankarioa> kontuBankarioak) {
		this.idSukurtsala = idSukurtsala;
		this.kokalekua = kokalekua;
		this.postaKodea = postaKodea;
		this.kontuBankarioak = kontuBankarioak;
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

	// ToString
	@Override
	public String toString() {
		return "Sukurtsala idSukurtsala=" + idSukurtsala + ", kokalekua=" + kokalekua + ", postaKodea=" + postaKodea
				+ ", kontuBankarioak=" + kontuBankarioak;
	}
	
	// Equals
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Sukurtsala other = (Sukurtsala) obj;
		return idSukurtsala == other.idSukurtsala;
	}
}
