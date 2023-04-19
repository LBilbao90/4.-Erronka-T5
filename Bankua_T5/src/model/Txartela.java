package model;

import java.util.ArrayList;

public class Txartela {
	private String idTxartela;
	private int segurtasunKodea;
	private Mota mota;
	private ArrayList<KontuBankarioa> kontuBankarioak;
	private ArrayList<Pertsona> pertsonak;
	
	public enum Mota{
		debito, kredito
	}

	// Constructors
	public Txartela(String idTxartela, int segurtasunKodea, Mota mota, ArrayList<KontuBankarioa> kontuBankarioak) {
		super();
		this.idTxartela = idTxartela;
		this.segurtasunKodea = segurtasunKodea;
		this.mota = mota;
		this.kontuBankarioak = new ArrayList<KontuBankarioa>();
		this.pertsonak = new ArrayList<Pertsona>();
	}

	// Getters and Setters
	public String getIdTxartela() {
		return idTxartela;
	}
	public void setIdTxartela(String idTxartela) {
		this.idTxartela = idTxartela;
	}
	public int getSegurtasunKodea() {
		return segurtasunKodea;
	}
	public void setSegurtasunKodea(int segurtasunKodea) {
		this.segurtasunKodea = segurtasunKodea;
	}
	public Mota getMota() {
		return mota;
	}
	public void setMota(Mota mota) {
		this.mota = mota;
	}
	public ArrayList<KontuBankarioa> getKontuBankarioak() {
		return kontuBankarioak;
	}
	public void setKontuBankarioak(ArrayList<KontuBankarioa> kontuBankarioak) {
		this.kontuBankarioak = kontuBankarioak;
	}
	public ArrayList<Pertsona> getPertsonak() {
		return pertsonak;
	}
	public void setPertsonak(ArrayList<Pertsona> pertsonak) {
		this.pertsonak = pertsonak;
	}
}
