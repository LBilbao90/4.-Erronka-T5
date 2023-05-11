package model;

import java.util.ArrayList;
import java.util.Objects;

public class KontuBankario {
	private String iban;
	private double saldoa;
	private double hilekoLimitea;
	private String sorreraData;
	private String egoera; // aktiboa, izoztuta, ixtita, ixteko
	private ArrayList<DiruSarrera> diruSarrerak;
	private ArrayList<Transferentzia> transferentziak;
	private Hipoteka hipoteka;
	private Sukurtsala sukurtsala;
	private ArrayList<Txartela> txartelak;
	
	// Bezerorako Sortzailea
	public KontuBankario(String iban, double saldoa, double hilekoLimitea, String sorreraData, String egoera, ArrayList<DiruSarrera> diruSarrerak, ArrayList<Transferentzia> transferentziak, Hipoteka hipoteka, Sukurtsala sukurtsala) {
		this.iban = iban;
		this.saldoa = saldoa;
		this.hilekoLimitea = hilekoLimitea;
		this.sorreraData = sorreraData;
		this.egoera = egoera;
		this.diruSarrerak = diruSarrerak;
		this.transferentziak = transferentziak;
		this.hipoteka = hipoteka;
		this.sukurtsala = sukurtsala;
	}
	// Langilerako Sortzailea
	public KontuBankario(String iban, double saldoa, double hilekoLimitea, String sorreraData, String egoera, ArrayList<DiruSarrera> diruSarrerak, ArrayList<Transferentzia> transferentziak, Hipoteka hipoteka,ArrayList<Txartela> txartelak) {
		this.iban = iban;
		this.saldoa = saldoa;
		this.hilekoLimitea = hilekoLimitea;
		this.sorreraData = sorreraData;
		this.egoera = egoera;
		this.diruSarrerak = diruSarrerak;
		this.transferentziak = transferentziak;
		this.hipoteka = hipoteka;
		this.txartelak = txartelak;
	}
	
	
	public KontuBankario(){
	}
	
	// Getters
	public String getIban() {
		return iban;
	}
	public double getSaldoa() {
		return saldoa;
	}
	public double getHilekoLimitea() {
		return hilekoLimitea;
	}
	public String getSorreraData() {
		return sorreraData;
	}
	public String getEgoera() {
		return egoera;
	}
	public ArrayList<DiruSarrera> getDiruSarrerak() {
		return diruSarrerak;
	}
	public ArrayList<Transferentzia> getTransferentziak() {
		return transferentziak;
	}
	public Hipoteka getHipoteka() {
		return hipoteka;
	}
	public Sukurtsala getSukurtsala() {
		return sukurtsala;
	}
	public ArrayList<Txartela> getTxartelak() {
		return txartelak;
	}
	
	// Setters
	public void setIban(String iban) {
		this.iban = iban;
	}
	public void setSaldoa(double saldoa) {
		this.saldoa = saldoa;
	}
	public void setHilekoLimitea(double hilekoLimitea) {
		this.hilekoLimitea = hilekoLimitea;
	}
	public void setSorreraData(String sorreraData) {
		this.sorreraData = sorreraData;
	}
	public void setEgoera(String egoera) {
		this.egoera = egoera;
	}
	public void setDiruSarrerak(ArrayList<DiruSarrera> diruSarrerak) {
		this.diruSarrerak = diruSarrerak;
	}
	public void setTransferentziak(ArrayList<Transferentzia> transferentziak) {
		this.transferentziak = transferentziak;
	}
	public void setHipoteka(Hipoteka hipoteka) {
		this.hipoteka = hipoteka;
	}
	public void setSukurtsala(Sukurtsala sukurtsala) {
		this.sukurtsala = sukurtsala;
	}
	public void setTxartelak(ArrayList<Txartela> txartelak) {
		this.txartelak = txartelak;
	}
	
	// ToString
	@Override
	public String toString() {
		return "KontuBankario iban=" + iban + ", saldoa=" + saldoa + ", hilekoLimitea=" + hilekoLimitea
				+ ", sorreraData=" + sorreraData + ", egoera=" + egoera + ", diruSarrerak=" + diruSarrerak
				+ ", transferentziak=" + transferentziak + ", hipoteka=" + hipoteka;
	}

	// Equals
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		KontuBankario other = (KontuBankario) obj;
		return Objects.equals(iban, other.iban);
	}
	
	
	
}
