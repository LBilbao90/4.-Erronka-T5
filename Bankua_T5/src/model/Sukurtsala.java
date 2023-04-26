package model;

import java.util.ArrayList;
import java.util.Objects;

public class Sukurtsala {
	private int idSukurtsala;
	private String kodSukurtsala;
	private String kokalekua;
	private EntitateBankario entitateBankario;
	private ArrayList<KontuBankario> kontuBankarioak;
	
	// Bezerorako Sortzailea
	public Sukurtsala(int idSukurtsala, String kodSukurtsala, String kokalekua,EntitateBankario entitateBankario) {
		this.idSukurtsala = idSukurtsala;
		this.kodSukurtsala = kodSukurtsala;
		this.kokalekua = kokalekua;
		this.entitateBankario=entitateBankario;
	}
	// Langilerako Sortzailea
	public Sukurtsala(int idSukurtsala, String kodSukurtsala, String kokalekua,EntitateBankario entitateBankario,ArrayList<KontuBankario> kontuBankarioak) {
		this.idSukurtsala = idSukurtsala;
		this.kodSukurtsala = kodSukurtsala;
		this.kokalekua = kokalekua;
		this.entitateBankario=entitateBankario;
		this.kontuBankarioak=kontuBankarioak;
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
	public String getKodSukurtsala() {
		return kodSukurtsala;
	}
	public void setKodSukurtsala(String kodSukurtsala) {
		this.kodSukurtsala = kodSukurtsala;
	}
	public EntitateBankario getEntitateBankario() {
		return entitateBankario;
	}
	public void setEntitateBankario(EntitateBankario entitateBankario) {
		this.entitateBankario = entitateBankario;
	}
	public ArrayList<KontuBankario> getKontuBankarioak() {
		return kontuBankarioak;
	}
	public void setKontuBankarioak(ArrayList<KontuBankario> kontuBankarioak) {
		this.kontuBankarioak = kontuBankarioak;
	}

	// ToString
	@Override
	public String toString() {
		return "Sukurtsala idSukurtsala=" + idSukurtsala + ", kodSukurtsala=" + kodSukurtsala + ", kokalekua="
				+ kokalekua;
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
