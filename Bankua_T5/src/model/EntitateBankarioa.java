package model;

import java.util.ArrayList;

public class EntitateBankarioa {
	private String izena;
	private int idEntitatea;
	private int entitateZbk;
	private ArrayList<Sukurtsala> sukurtzalak;
	
	// Constructors
	public EntitateBankarioa(String izena, int idEntitatea, int entitateZbk, ArrayList<Sukurtsala> sukurtzalak) {
		this.izena = izena;
		this.idEntitatea = idEntitatea;
		this.entitateZbk = entitateZbk;
		this.sukurtzalak = sukurtzalak;
	}

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
	public int getEntitateZbk() {
		return entitateZbk;
	}
	public void setEntitateZbk(int entitateZbk) {
		this.entitateZbk = entitateZbk;
	}
	public ArrayList<Sukurtsala> getSukurtzalak() {
		return sukurtzalak;
	}
	public void setSukurtzalak(ArrayList<Sukurtsala> sukurtzalak) {
		this.sukurtzalak = sukurtzalak;
	}

	// ToString
	@Override
	public String toString() {
		return "EntitateBankarioa izena=" + izena + ", idEntitatea=" + idEntitatea + ", entitateZbk=" + entitateZbk
				+ ", sukurtzalak=" + sukurtzalak;
	}
	
	// Equals
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		EntitateBankarioa other = (EntitateBankarioa) obj;
		return entitateZbk == other.entitateZbk;
	}
	
}
