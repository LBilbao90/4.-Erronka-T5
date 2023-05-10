package model;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Objects;

import javax.swing.JOptionPane;

public class Hipoteka implements Komisioa{
	private double kantitatea;
	private double ordaindutakoa;
	private final String komisioa;
	private String hasieraData;
	private String amaieraData;
	private String egoera; //eskatuta, onartuta, itxita, errefusatuta
	private String epeMuga;
	
	// Constructors
	public Hipoteka(double kantitatea, double ordaindutakoa, String komisioa, String hasieraData, String amaieraData, String egoera, String epeMuga) {
		this.kantitatea = kantitatea;
		this.ordaindutakoa = ordaindutakoa;
		this.komisioa = komisioa;
		this.hasieraData = hasieraData;
		this.amaieraData = amaieraData;
		this.egoera=egoera;
		this.epeMuga=epeMuga;
	}
	
	public Hipoteka() {
		this.komisioa = "";
	}

	// Getters
	public double getKantitatea() {
		return kantitatea;
	}
	public double getOrdaindutakoa() {
		return ordaindutakoa;
	}
	public String getKomisioa() {
		return komisioa;
	}
	public String getHasieraData() {
		return hasieraData;
	}
	public String getAmaieraData() {
		return amaieraData;
	}
	public String getEgoera() {
		return egoera;
	}
	public String getEpeMuga() {
		return this.epeMuga;
	}
	
	// Setters
	public void setKantitatea(double kantitatea) {
		this.kantitatea = kantitatea;
	}
	public void setOrdaindutakoa(double ordaindutakoa) {
		this.ordaindutakoa = ordaindutakoa;
	}
	public void setHasieraData(String hasieraData) {
		this.hasieraData = hasieraData;
	}
	public void setAmaieraData(String amaieraData) {
		this.amaieraData = amaieraData;
	}
	public void setEgoera(String egoera) {
		this.egoera = egoera;
	}
	public void setEpeMuga(String epeMuga) {
		this.epeMuga = epeMuga;
	}
	
	// ToString
	@Override
	public String toString() {
		return "Hipoteka kantitatea=" + kantitatea + ", ordaindutakoa=" + ordaindutakoa + ", komisioa=" + komisioa
				+ ", hasieraData=" + hasieraData + ", amaieraData=" + amaieraData + ", egoera=" + egoera + ", epeMuga="
				+ epeMuga;
	}
	
	
	// Equals
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Hipoteka other = (Hipoteka) obj;
		return Objects.equals(amaieraData, other.amaieraData) && Objects.equals(egoera, other.egoera)
				&& Objects.equals(hasieraData, other.hasieraData)
				&& Double.doubleToLongBits(kantitatea) == Double.doubleToLongBits(other.kantitatea)
				&& komisioa.equals(other.komisioa)
				&& Double.doubleToLongBits(ordaindutakoa) == Double.doubleToLongBits(other.ordaindutakoa)
				&& Objects.equals(epeMuga, other.epeMuga);
	}

	@Override
	public double kalkulatuPrezioa(String kantitatea, String komisioa) {
	    DecimalFormat df = new DecimalFormat("0.00");
		komisioa=komisioa.replace("%", "");	
		kantitatea=kantitatea.replace(",",".");	
		
		double komisio = Double.parseDouble(komisioa);
		double kantitate = Double.parseDouble(kantitatea);
		return ((kantitate*komisio)/100)+kantitate;
	}
	
	public Langilea hipotekaErrefusatu(Langilea langilea, String sukurtsal_izen,String bezero_iban) {
		//Sukurtsalak arakatu
		for(int i=0;i<langilea.getSukurtsalak().size();i++) {
			if(langilea.getSukurtsalak().get(i).getKokalekua().equals(sukurtsal_izen)) {
				//Kontu Bankarioak arakatu
				for(int j=0;j<langilea.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
					if(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban().equals(bezero_iban)) {
						langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().setEgoera("errefusatuta");			
						JOptionPane.showMessageDialog(null, "Hipoteka errefusatu da.","Info", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}		
		return langilea;
	}
	
	public Langilea hipotekaOnartu(Langilea langilea, String sukurtsal_izen,String bezero_iban) {
		//Sukurtsalak arakatu
		for(int i=0;i<langilea.getSukurtsalak().size();i++) {
			if(langilea.getSukurtsalak().get(i).getKokalekua().equals(sukurtsal_izen)) {
				//Kontu Bankarioak arakatu
				for(int j=0;j<langilea.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
					if(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban().equals(bezero_iban)) {
						langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().setEgoera("onartuta");
						Calendar c = Calendar.getInstance();
						String egun,hil, urte;							   
						egun = Integer.toString(c.get(Calendar.DATE));
						hil = Integer.toString(c.get(Calendar.MONTH));
						urte = Integer.toString(c.get(Calendar.YEAR));		
						langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().setHasieraData(urte + "-" + hil +"-" + egun);						
						JOptionPane.showMessageDialog(null, "Hipoteka onartu da.","Info", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}		
		return langilea;
	}

}
