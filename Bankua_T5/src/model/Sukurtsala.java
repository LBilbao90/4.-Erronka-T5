package model;

import java.util.ArrayList;

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
		this.kontuBankarioak = new ArrayList<KontuBankarioa>();
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
}
