package model;

import java.util.ArrayList;
import java.util.Date;

public class KontuBankarioa {
	private String iban;
	private double saldoa;
	private double hilekoLimitea;
	private Date sorreraData;
	private Egoera egoera;
	private ArrayList<DiruSarrera> diruSarrerak;
	private ArrayList<Transferentzia> transferentziak;
	private ArrayList<Hipoteka> hipotekak;
	
	public enum Egoera{
		aktiboa, izoztuta, ixtita
	}
	
	// Constructors
	public KontuBankarioa(String iban, double saldoa, double hilekoLimitea, Date sorreraData, Egoera egoera, ArrayList<DiruSarrera> diruSarrerak, ArrayList<Transferentzia> transferentziak, ArrayList<Hipoteka> hipotekak) {
		this.iban = iban;
		this.saldoa = saldoa;
		this.hilekoLimitea = hilekoLimitea;
		this.sorreraData = sorreraData;
		this.egoera = egoera;
		this.diruSarrerak = new ArrayList<DiruSarrera>();
		this.transferentziak = new ArrayList<Transferentzia>();
		this.hipotekak = new ArrayList<Hipoteka>();
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
	public Date getSorreraData() {
		return sorreraData;
	}
	public void setSorreraData(Date sorreraData) {
		this.sorreraData = sorreraData;
	}
	public Egoera getEgoera() {
		return egoera;
	}
	public void setEgoera(Egoera egoera) {
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
	public ArrayList<Hipoteka> getHipotekak() {
		return hipotekak;
	}
	public void setHipotekak(ArrayList<Hipoteka> hipotekak) {
		this.hipotekak = hipotekak;
	}
	
}
