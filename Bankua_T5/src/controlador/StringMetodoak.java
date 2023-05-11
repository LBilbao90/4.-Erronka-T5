package controlador;

import java.math.BigDecimal;

import model.Bezeroa;
import model.Langilea;

public class StringMetodoak {

	/**
	 * IBANak kalkulatzen du
	 * @param langilea zein langile dago sortzen IBANa
	 * @param sukurtsal_izen zein sukurtsalean egingo da
	 * @return Iban kontua
	 */
	public static String ibanKalkulatu(Langilea langilea, String sukurtsal_izen) {
		String iban_kontua = "";
		String es = "ES";
		String kontu_zenb ="";
		
		for(int i=0;i<langilea.getSukurtsalak().size();i++) {
			if(langilea.getSukurtsalak().get(i).getKokalekua().equals(sukurtsal_izen)) {
				String entitate_kod = langilea.getSukurtsalak().get(i).getEntitateBankario().getEntitateZbk();
				String sukurtsal_kod = langilea.getSukurtsalak().get(i).getKodSukurtsala();
				for(int j=0;j<10;j++) {
					kontu_zenb += String.valueOf((int) Math.floor(Math.random() *(9 - 0 + 1) + 0));
				}
				String kontrol_zenbakiak = kontrolDigitoak(entitate_kod,sukurtsal_kod,kontu_zenb);
				
				String iban_kontrol_zenb=entitate_kod+sukurtsal_kod+kontrol_zenbakiak+kontu_zenb+142800;
				
				BigDecimal b1= new BigDecimal(iban_kontrol_zenb);
				int hondarra = b1.remainder(new BigDecimal(97)).intValue();
				int emaitza = 98-((hondarra*97)/100);
				iban_kontua= es+emaitza+entitate_kod+sukurtsal_kod+kontrol_zenbakiak+kontu_zenb;
			}
		}		
		return iban_kontua;
	}
	
	/**
	 * IBAN kontu baten kontrol digitoak kalkulatzen ditu
	 * @param entitateZenb Entitatearen IBAN zenbakia
	 * @param sukurtsalZenb Sukurtsalaren IBAN zenbakia
	 * @param kontuZenb IBANerako sortutako zenbakia
	 * @return IBAN horren kontrol zenbakiak
	 */
	public static String kontrolDigitoak(String entitateZenb, String sukurtsalZenb, String kontuZenb) {
		String kontrol_zenbaki = "";
		int[] biderketaZenb = {1,2,4,8,5,10,9,7,3,6};
		int gehiketa=0;
		int zatiketa = 0;
		
		//Lehenengo kontrol zenbakia
		String A_zenbakiak = "00"+entitateZenb+sukurtsalZenb;
		for(int i=0;i<10;i++) {
			gehiketa+= biderketaZenb[i]*Character.getNumericValue(A_zenbakiak.charAt(i));
		}
		zatiketa = gehiketa%11;
		if(11-zatiketa==11) {
			kontrol_zenbaki="0";
		}else if(11-zatiketa==10){
			kontrol_zenbaki="1";			
		}else {
			kontrol_zenbaki=String.valueOf(11-zatiketa);			
		}
		//Bigarren kontrol zenbakia
		gehiketa=0;
		for(int i=0;i<kontuZenb.length();i++) {
			gehiketa+= biderketaZenb[i]*Character.getNumericValue(kontuZenb.charAt(i));
		}
		zatiketa = gehiketa%11;
		if(11-zatiketa==11) {
			kontrol_zenbaki+="0";
		}else if(11-zatiketa==10){
			kontrol_zenbaki+="1";			
		}else {
			kontrol_zenbaki+=String.valueOf(11-zatiketa);			
		}
		return kontrol_zenbaki;
	}
	
	/**
	 * Bezeroaren kontu bankarioa hipoteka duen ala ez bilatzen du
	 * @param bezeroa Zein bezeroena
	 * @param iban Zein kontu bankarioan
	 * @return Hipoteka badu true, bestela false
	 */
	public String hipotekaDut(Bezeroa bezeroa, String iban) {
		String  egoera = "";

		for(int i = 0; i < bezeroa.getTxartelak().size(); i ++) {
			if(bezeroa.getTxartelak().get(i).getKontuBankario().getIban().equals(iban)) {
				if(bezeroa.getTxartelak().get(i).getKontuBankario().getHipoteka() != null) {
					egoera = bezeroa.getTxartelak().get(i).getKontuBankario().getHipoteka().getEgoera();					
				}
			}
		}		
		return egoera;
	}
	
	/**
	 * sortzen du txartel zenbaki bat
	 * @return Txartel zenbakia
	 */
	public static String sortuTxartelId() {
		String id = "";
		
		for(int i=0;i<16;i++) {
			id += String.valueOf((int) Math.floor(Math.random() *(9 - 0 + 1) + 0));			
		}
		return id;
	}
}
