package model;

import java.util.ArrayList;
import java.util.Objects;

public class EntitateBankario {
	private String izena;
	private String idEntitatea;
	private String entitateZbk;
	private ArrayList<Sukurtsala> sukurtsalak;
	private String url;
	private String bounds;
	
	// Constructors
	public EntitateBankario(String izena, String idEntitatea, String entitateZbk, ArrayList<Sukurtsala> sukurtsalak, String bounds, String url) {
		this.izena = izena;
		this.idEntitatea = idEntitatea;
		this.entitateZbk = entitateZbk;
		this.sukurtsalak = sukurtsalak;
		this.url=url;
		this.bounds=bounds;
	}
	
	public EntitateBankario() {}

	// Getters and Setters
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public String getIdEntitatea() {
		return idEntitatea;
	}
	public void setIdEntitatea(String idEntitatea) {
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
		EntitateBankario other = (EntitateBankario) obj;
		return Objects.equals(entitateZbk, other.entitateZbk);
	}
	
}
