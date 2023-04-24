package model;

import java.util.ArrayList;
import java.util.Objects;

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
	public Txartela(String idTxartela, int segurtasunKodea, Mota mota, ArrayList<KontuBankarioa> kontuBankarioak, ArrayList<Pertsona> pertsonak) {
		super();
		this.idTxartela = idTxartela;
		this.segurtasunKodea = segurtasunKodea;
		this.mota = mota;
		this.kontuBankarioak = kontuBankarioak;
		this.pertsonak = pertsonak;
	}
	
	public Txartela() {
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

	// ToString
	@Override
	public String toString() {
		return "Txartela idTxartela=" + idTxartela + ", segurtasunKodea=" + segurtasunKodea + ", mota=" + mota
				+ ", kontuBankarioak=" + kontuBankarioak + ", pertsonak=" + pertsonak;
	}

	// Equals
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Txartela other = (Txartela) obj;
		return Objects.equals(idTxartela, other.idTxartela);
	}
}
