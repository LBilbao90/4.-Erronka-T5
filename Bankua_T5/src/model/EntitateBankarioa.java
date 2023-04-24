package model;

import java.util.ArrayList;
import java.util.Objects;

public class EntitateBankarioa {
	private String izena;
	private int idEntitatea;
	private String entitateZbk;
	private ArrayList<Sukurtsala> sukurtsalak;
	private String url;
	private String bounds;
	
	// Constructors
	public EntitateBankarioa(String izena, int idEntitatea, String entitateZbk, ArrayList<Sukurtsala> sukurtsalak) {
		this.izena = izena;
		this.idEntitatea = idEntitatea;
		this.entitateZbk = entitateZbk;
		this.sukurtsalak = sukurtsalak;
	}
	
	public EntitateBankarioa() {}

	// Getters and Setters
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public int getIdEntitatea() {
		return idEntitatea;
	}
	public void setIdEntitatea(int idEntitatea) {
		this.idEntitatea = idEntitatea;
	}
	public String getEntitateZbk() {
		return entitateZbk;
	}
	public void setEntitateZbk(String entitateZbk) {
		this.entitateZbk = entitateZbk;
	}
	public ArrayList<Sukurtsala> getSukurtsalak() {
		return sukurtsalak;
	}
	public void setSukurtsalak(ArrayList<Sukurtsala> sukurtzalak) {
		this.sukurtsalak = sukurtzalak;
	}
	

	public String getUrl() {
		return url;
	}
	public String getBounds() {
		return bounds;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setBounds(String bounds) {
		this.bounds = bounds;
	}
	// ToString
	@Override
	public String toString() {
		return "EntitateBankarioa izena=" + izena + ", idEntitatea=" + idEntitatea + ", entitateZbk=" + entitateZbk
				+ ", sukurtsalak=" + sukurtsalak;
	}
	
	// Equals
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		EntitateBankarioa other = (EntitateBankarioa) obj;
		return Objects.equals(entitateZbk, other.entitateZbk);
	}
	
}
