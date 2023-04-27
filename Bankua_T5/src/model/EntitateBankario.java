package model;

import java.util.Objects;

public class EntitateBankario {
	private String izena;
	private String idEntitatea;
	private String entitateZbk;
	private String url;
	private String bounds;
	
	// Constructors
	public EntitateBankario(String izena, String idEntitatea, String entitateZbk, String bounds, String url) {
		this.izena = izena;
		this.idEntitatea = idEntitatea;
		this.entitateZbk = entitateZbk;
		this.url = url;
		this.bounds = bounds;
	}
	
	public EntitateBankario() {}

	// Getters
	public String getIzena() {
		return izena;
	}
	public String getIdEntitatea() {
		return idEntitatea;
	}
	public String getEntitateZbk() {
		return entitateZbk;
	}
	public String getUrl() {
		return url;
	}
	public String getBounds() {
		return bounds;
	}
	
	// Setters
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public void setIdEntitatea(String idEntitatea) {
		this.idEntitatea = idEntitatea;
	}
	public void setEntitateZbk(String entitateZbk) {
		this.entitateZbk = entitateZbk;
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
		return "EntitateBankarioa izena=" + izena + ", idEntitatea=" + idEntitatea + ", entitateZbk=" + entitateZbk;
	}
	
	// Equals
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		EntitateBankario other = (EntitateBankario) obj;
		return Objects.equals(entitateZbk, other.entitateZbk) && Objects.equals(idEntitatea, other.idEntitatea);
	}
	
}
