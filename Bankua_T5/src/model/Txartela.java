package model;

import java.util.Objects;

public class Txartela {
	private String idTxartela;
	private int segurtasunKodea;
	private String mota; // debito, kredito
	private KontuBankario kontuBankarioa;
	private Bezeroa bezeroa;

	// Constructors
	public Txartela(String idTxartela, int segurtasunKodea, String mota, KontuBankario kontuBankarioa, Bezeroa bezeroa) {
		super();
		this.idTxartela = idTxartela;
		this.segurtasunKodea = segurtasunKodea;
		this.mota = mota;
		this.kontuBankarioa = kontuBankarioa;
		this.bezeroa = bezeroa;
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
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public KontuBankario getKontuBankarioa() {
		return kontuBankarioa;
	}
	public void setKontuBankarioa(KontuBankario kontuBankarioa) {
		this.kontuBankarioa = kontuBankarioa;
	}
	public Bezeroa getBezeroa() {
		return bezeroa;
	}
	public void setBezeroa(Bezeroa bezeroa) {
		this.bezeroa = bezeroa;
	}

	// ToString
	@Override
	public String toString() {
		return "Txartela idTxartela=" + idTxartela + ", segurtasunKodea=" + segurtasunKodea + ", mota=" + mota
				+ ", kontuBankarioa=" + kontuBankarioa + ", bezeroa=" + bezeroa;
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
