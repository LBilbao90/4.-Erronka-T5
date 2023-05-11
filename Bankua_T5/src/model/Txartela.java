package model;

import java.util.Objects;

public class Txartela {
	private String segurtasunKodea;
	private String mota; // debito, kredito
	private KontuBankario kontuBankario;
	private Bezeroa bezeroa;

	// Constructors
	
	// Bezerorako Sortzailea
	public Txartela(String segurtasunKodea, String mota, KontuBankario kontuBankario) {
		this.segurtasunKodea = segurtasunKodea;
		this.mota = mota;
		this.kontuBankario = kontuBankario;
	}
	
	// Langilerako Sortzailea
	public Txartela(String segurtasunKodea, String mota, Bezeroa bezeroa) {
		this.segurtasunKodea = segurtasunKodea;
		this.mota = mota;
		this.bezeroa = bezeroa;
	}
		
	public Txartela() {
	}	
	
	//Getters
	public String getSegurtasunKodea() {
		return segurtasunKodea;
	}
	public String getMota() {
		return mota;
	}
	public KontuBankario getKontuBankario() {
		return kontuBankario;
	}
	public Bezeroa getBezeroa() {
		return bezeroa;
	}
	
	//Setters
	public void setSegurtasunKodea(String segurtasunKodea) {
		this.segurtasunKodea = segurtasunKodea;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public void setKontuBankario(KontuBankario kontuBankario) {
		this.kontuBankario = kontuBankario;
	}
	public void setBezeroa(Bezeroa bezeroa) {
		this.bezeroa = bezeroa;
	}
	
	// ToString
	@Override
	public String toString() {
		return "Txartela segurtasunKodea=" + segurtasunKodea + ", mota=" + mota;
	}

	// Equals
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Txartela other = (Txartela) obj;
		return Objects.equals(segurtasunKodea, other.segurtasunKodea)
		&& Objects.equals(mota, other.mota)
		&& Objects.equals(kontuBankario, other.kontuBankario)
		&& Objects.equals(bezeroa, other.bezeroa);
	}
}
