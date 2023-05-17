package controlador;

import java.util.Calendar;

import model.Bezeroa;
import model.Langilea;
import model.SalbuespenaOrdainketa;
import model.Transferentzia;
import model.Hipoteka;

public class ObjetuMetodoak {
	/**
	 * kontu bankario bat ixteko egoeran jartzeko
	 * @param bezero Bezero objetua (noren kontu bankarioa aldatuko den)
	 * @param iban_bez Zein kontu bankario aldatuko den
	 * @return Bezero objetu bat "ixteko" egoerarekin
	 */
	public Bezeroa ixtekoKontuAldatu(Bezeroa bezero, String iban_bez) {
		
		for(int i=0;i<bezero.getTxartelak().size();i++) {
			if(bezero.getTxartelak().get(i).getKontuBankario().getIban().equals(iban_bez)) {
				bezero.getTxartelak().get(i).getKontuBankario().setEgoera("ixteko");
			}
		}		
		return bezero;
	}

	/**
	 * Bezero baten kontu bankariotik dirua kentzen du eta transferentzia sortzen du
	 * @param bezero Zein bezeroren kontu bankarioa da
	 * @param iban_bez Zein kontu bankariotik kenduko da dirua
	 * @param kantitate_kendu zenbat kenduko da
	 * @param komisio_kendu zenbat da komisioa
	 * @param iban_jasotzaile Nor jasoko du dirua
	 * @return Bezero objetua dirua kenduta eta transferentzia eginda
	 */
	public Bezeroa diruaKendu(Bezeroa bezero, String iban_bez, String kantitate_kendu, String komisio_kendu, String iban_jasotzaile) {

		kantitate_kendu=kantitate_kendu.replace(",",".");	
		komisio_kendu=komisio_kendu.replace(",",".");

		Calendar c = Calendar.getInstance();
		String egun,hil, urte;							   
		egun = Integer.toString(c.get(Calendar.DATE));
		hil = Integer.toString(c.get(Calendar.MONTH));
		urte = Integer.toString(c.get(Calendar.YEAR));		
		String data_sortu= urte + "-" + hil +"-" + egun;
		
		Transferentzia t1 = new Transferentzia ();

		t1.setJasotzailea(iban_jasotzaile);
		t1.setKantitatea(Integer.parseInt(kantitate_kendu));
		t1.setKontzeptua(komisio_kendu);
		t1.setTransferentziaData(data_sortu);
		
		for(int i=0;i<bezero.getTxartelak().size();i++) {
			if(bezero.getTxartelak().get(i).getKontuBankario().getIban().equals(iban_bez)) {
				bezero.getTxartelak().get(i).getKontuBankario().setSaldoa(bezero.getTxartelak().get(i).getKontuBankario().getSaldoa()-t1.kalkulatuPrezioa(kantitate_kendu, komisio_kendu));;
			}
		}
		return bezero;
	}
	
	/**
	 * Hipoteka sortu eta eguneratzen dio bezeroari
	 * @param bezero Zein bezerori sortuko zaio hipoteka
	 * @param bezero_iban_kontua Zein kontu bankarioan sortuko da hipoteka
	 * @param kantitatea_hipo Zenbatekoa izango da hipoteka
	 * @param komisioa_hipo zenbateko komisioa
	 * @param epemuga_hipo hipotekaren epemuga data
	 * @return bezero objetua hipotekarekin sortuta
	 */
	public Bezeroa hipotekaSortu(Bezeroa bezero,String bezero_iban_kontua ,String kantitatea_hipo, String komisioa_hipo, String epemuga_hipo) {
		kantitatea_hipo = kantitatea_hipo.replace(",",".");
		Calendar c = Calendar.getInstance();
		String egun,hil, urte;							   
		egun = Integer.toString(c.get(Calendar.DATE));
		hil = Integer.toString(c.get(Calendar.MONTH)+1);
		urte = Integer.toString(c.get(Calendar.YEAR));		
		String data_sortu= urte + "-" + hil +"-" + egun;
		for(int i=0;i<bezero.getTxartelak().size();i++) {
			if(bezero.getTxartelak().get(i).getKontuBankario().getIban().equals(bezero_iban_kontua)) {
				Hipoteka hipoteka = new Hipoteka(Double.parseDouble(kantitatea_hipo),0.0,komisioa_hipo,data_sortu,"","eskatuta",epemuga_hipo);
				bezero.getTxartelak().get(i).getKontuBankario().setHipoteka(hipoteka);
				double saldo_prob = bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().kalkulatuPrezioa(kantitatea_hipo, komisioa_hipo);
				bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().setKantitatea(Math.round(saldo_prob * 100.0) / 100.0);
			}
		}		
		return bezero;
	}

	/**
	 * Hipoteka ordaintzeko 
	 * @param bezero Zein bezero egingo du ordainketa 
	 * @param iban_bez zein kontu bankarioren hipoteka ordainduko da
	 * @param kantitatea zenbat ordainduko da
	 * @return bezero objetua hipotekaren ordainketa eguneratuta
	 * @throws SalbuespenaOrdainketa
	 */
	public Bezeroa hipotekaOrdaindu(Bezeroa bezero, String iban_bez, String kantitatea) throws SalbuespenaOrdainketa {
		kantitatea = kantitatea.replace(",",".");
		for(int i=0;i<bezero.getTxartelak().size();i++) {
			if(bezero.getTxartelak().get(i).getKontuBankario().getIban().equals(iban_bez)) {
				if(bezero.getTxartelak().get(i).getKontuBankario().getSaldoa()>=Double.parseDouble(kantitatea) && bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getKantitatea()-bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getOrdaindutakoa()>= Double.parseDouble(kantitatea)) {
					bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().setOrdaindutakoa(bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getOrdaindutakoa()+Double.parseDouble(kantitatea));
					bezero.getTxartelak().get(i).getKontuBankario().setSaldoa(bezero.getTxartelak().get(i).getKontuBankario().getSaldoa()-Double.parseDouble(kantitatea));
					
					if(bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getOrdaindutakoa()==bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getKantitatea()) {
						bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().setEgoera("itxita");
						Calendar c = Calendar.getInstance();						
						String egun,hil, urte;							   
						egun = Integer.toString(c.get(Calendar.DATE));
						hil = Integer.toString(c.get(Calendar.MONTH)+1);
						urte = Integer.toString(c.get(Calendar.YEAR));		
						String data_sortu= urte + "-" + hil +"-" + egun;
						bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().setAmaieraData(data_sortu);
					}
				}else {
					throw new SalbuespenaOrdainketa("Ordainketa okerra.");
				}
			}
		}		
		return bezero;
	}

	/**
	 * kontu bankario bat ixten du 
	 * @param langilea Zein langile itxiko duen
	 * @param iban_itxi Zein kontu bankario itxiko da
	 * @param suk_izen Zein sukurtsalekoa dagoen
	 * @return Langile objetua kontu bankario hori gabe
	 */
	public Langilea kontuItxi(Langilea langilea, String iban_itxi, String suk_izen) {
		//Sukurtsalaka arakatu
		for(int i=0;i<langilea.getSukurtsalak().size();i++) {
			if(langilea.getSukurtsalak().get(i).getKokalekua().equals(suk_izen)) {
				//Kontu Bankarioak arakatu
				for(int j=0;j<langilea.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
					if(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban().equals(iban_itxi)) {
						langilea.getSukurtsalak().get(i).getKontuBankarioak().remove(j);
					}
				}
			}
		}		
		return langilea;
	}
}