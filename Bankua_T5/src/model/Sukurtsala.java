package model;

import java.util.Objects;

public class Sukurtsala {
	private int idSukurtsala;
	private String kodSukurtsala;
	private String kokalekua;
	private EntitateBankario entitateBankario;
	
	// Constructors
	public Sukurtsala(int idSukurtsala, String kodSukurtsala, String kokalekua,EntitateBankario entitateBankario) {
		this.idSukurtsala = idSukurtsala;
		this.kodSukurtsala = kodSukurtsala;
		this.kokalekua = kokalekua;
		this.entitateBankario=entitateBankario;
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
