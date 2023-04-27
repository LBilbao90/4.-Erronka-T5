package model;


public class Txartela {
	private String segurtasunKodea;
	private String mota; // debito, kredito
	private KontuBankario kontuBankario;
	private Bezeroa bezero;

	// Bezerorako Sortzailea
	public Txartela(String segurtasunKodea, String mota, KontuBankario kontuBankario) {
		this.segurtasunKodea = segurtasunKodea;
		this.mota = mota;
		this.kontuBankario=kontuBankario;
	}
	// Langilerako Sortzailea
	public Txartela(String segurtasunKodea, String mota, Bezeroa bezero) {
		this.segurtasunKodea = segurtasunKodea;
		this.mota = mota;
		this.bezero=bezero;
	}
		
	public Txartela() {
	}	
	
	//Getters
	public String getSegurtasunKodea() {
		return segurtasunKodea;
	}
	public String getMota() {
		return mota;
	}
	public KontuBankario getKontuBankario() {
		return kontuBankario;
	}
	//Setters
	public void setSegurtasunKodea(String segurtasunKodea) {
		this.segurtasunKodea = segurtasunKodea;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public void setKontuBankario(KontuBankario kontuBankario) {
		this.kontuBankario = kontuBankario;
	}
	public Bezeroa getBezero() {
		return bezero;
	}
	public void setBezero(Bezeroa bezero) {
		this.bezero = bezero;
	}
	
	// ToString
	@Override
	public String toString() {
		return "Txartela segurtasunKodea=" + segurtasunKodea + ", mota=" + mota + ", kontuBankario=" + kontuBankario;
	}

	// Equals
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		Txartela other = (Txartela) obj;
		return false;//Objects.equals(idTxartela, other.idTxartela);
	}

}
