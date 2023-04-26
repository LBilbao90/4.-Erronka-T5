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
	private Hipoteka hipotekak;
	private Sukurtsala sukurtsala;
	
	// Constructors
	public KontuBankario(String iban, double saldoa, double hilekoLimitea, String sorreraData, String egoera, ArrayList<DiruSarrera> diruSarrerak, ArrayList<Transferentzia> transferentziak, Hipoteka hipotekak, Sukurtsala sukurtsala) {
		this.iban = iban;
		this.saldoa = saldoa;
		this.hilekoLimitea = hilekoLimitea;
		this.sorreraData = sorreraData;
		this.egoera = egoera;
		this.diruSarrerak = diruSarrerak;
		this.transferentziak = transferentziak;
		this.hipotekak = hipotekak;
		this.sukurtsala=sukurtsala;
	}
	
	public KontuBankario(){
	}
	
	// Getters and Setters
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}
	public double getSaldoa() {
		return saldoa;
	}
	public void setSaldoa(double saldoa) {
		this.saldoa = saldoa;
	}
	public double getHilekoLimitea() {
		return hilekoLimitea;
	}
	public void setHilekoLimitea(double hilekoLimitea) {
		this.hilekoLimitea = hilekoLimitea;
	}
	public String getSorreraData() {
		return sorreraData;
	}
	public void setSorreraData(String sorreraData) {
		this.sorreraData = sorreraData;
	}
	public String getEgoera() {
		return egoera;
	}
	public void setEgoera(String egoera) {
		this.egoera = egoera;
	}
	public ArrayList<DiruSarrera> getDiruSarrerak() {
		return diruSarrerak;
	}
	public void setDiruSarrerak(ArrayList<DiruSarrera> diruSarrerak) {
		this.diruSarrerak = diruSarrerak;
	}
	public ArrayList<Transferentzia> getTransferentziak() {
		return transferentziak;
	}
	public void setTransferentziak(ArrayList<Transferentzia> transferentziak) {
		this.transferentziak = transferentziak;
	}
	public Hipoteka getHipotekak() {
		return hipotekak;
	}
	public void setHipotekak(Hipoteka hipotekak) {
		this.hipotekak = hipotekak;
	}
	public Sukurtsala getSukurtsala() {
		return sukurtsala;
	}

	public void setSukurtsala(Sukurtsala sukurtsala) {
		this.sukurtsala = sukurtsala;
	}

	// ToString
	@Override
	public String toString() {
		return "KontuBankario iban=" + iban + ", saldoa=" + saldoa + ", hilekoLimitea=" + hilekoLimitea
				+ ", sorreraData=" + sorreraData + ", egoera=" + egoera + ", diruSarrerak=" + diruSarrerak
				+ ", transferentziak=" + transferentziak + ", hipotekak=" + hipotekak + ", sukurtsala=" + sukurtsala;
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
