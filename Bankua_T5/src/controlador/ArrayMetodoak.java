package controlador;

import java.text.DecimalFormat;

import model.Bezeroa;
import model.Langilea;

public class ArrayMetodoak {

    private static final DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * Bezero baten entitate bankarioak ikusteko
	 * @param bezero Zein bezeroren entitate bankarioak atera nahi diren
	 * @return Bezerorarenn entitatebankarioak
	 */
	public String[] bezeroarenEntitateak(Bezeroa bezero) {
		String[] entitateak = new String[0];
		
		for(int i=0;i<bezero.getTxartelak().size();i++) {
				String ent_id = bezero.getTxartelak().get(i).getKontuBankario().getSukurtsala().getEntitateBankario().getIdEntitatea();
				boolean aurkituta = false;
				for(int j=0;j<entitateak.length;j++) {
					if(entitateak[j].equals(ent_id)) {
						aurkituta=true;
					}
				}			
				if(!aurkituta) {
					String[] prob = new String[entitateak.length+1];
					for(int k=0;k<entitateak.length;k++) {
						prob[k]=entitateak[k];
					}
					prob[entitateak.length]=ent_id;
					entitateak=prob;
				}
		}		
		return entitateak;
	}

	/**
	 * Langilearen entitate bankarioak kargatzen ditu
	 * @param langilea Langilearen objetua
	 * @return Langilearen entitate bankarioen izenak String motatako Array batean
	 */
	public String[] langilearenEntitateak(Langilea langilea) {
		String[] entitateak = new String[0];
		boolean aurkitu = false;
		for(int i=0;i<langilea.getSukurtsalak().size();i++) {
			aurkitu = false;
			if(entitateak.length==0) {
				String[] entitateak_prob = new String[entitateak.length+1];
				for(int k=0;k<entitateak.length;k++) {
					entitateak_prob[k]=entitateak[k];
				}
				entitateak_prob[entitateak.length]= langilea.getSukurtsalak().get(i).getEntitateBankario().getIzena();
				entitateak = entitateak_prob;
			}else {
				for(int j=0;j<entitateak.length;j++) {
					if(langilea.getSukurtsalak().get(i).getEntitateBankario().getIzena().equals(entitateak[j])) {
						aurkitu=true;
					}else if(!langilea.getSukurtsalak().get(i).getEntitateBankario().getIzena().equals(entitateak[j]) && j==entitateak.length-1 && !aurkitu) {
						String[] entitateak_prob = new String[entitateak.length+1];
						for(int k=0;k<entitateak.length;k++) {
							entitateak_prob[k]=entitateak[k];
						}
						entitateak_prob[entitateak.length]= langilea.getSukurtsalak().get(i).getEntitateBankario().getIzena();
						entitateak = entitateak_prob;
					}
				}
			}
		}		
		return entitateak;
	}
	
	/**
	 * Langilearen entitate bankarioaren eta lanpostuaren arabera dituen sukurtsalak kargatzen ditu
	 * @param langilea Langilearen objetua
	 * @param entitatea Langileak aukeratutako entitate bankarioaren izena
	 * @return Langileak aukeratutako entitateko sukurtsalak String motatako Array batean
	 */
	public String[] langilearenSukurtsalak(Langilea langilea, String entitatea) {
		String[] sukurtsalak = new String[0];
		
		for(int i=0;i<langilea.getSukurtsalak().size();i++) {
			if(langilea.getSukurtsalak().get(i).getEntitateBankario().getIzena().equals(entitatea)) {
				String[] sukurtsalak_prob = new String[sukurtsalak.length+1];
				for(int j=0;j<sukurtsalak.length;j++) {
					sukurtsalak_prob[j]=sukurtsalak[j];
				}
				sukurtsalak_prob[sukurtsalak.length]=langilea.getSukurtsalak().get(i).getKokalekua();
				sukurtsalak=sukurtsalak_prob;
			}
		}		
		return sukurtsalak;
	}
	
	/**
	 * Kontu bankario bakar baten informazioa bilatzeko
	 * @param langilea Zein langile egiteng du kontsulta
	 * @param iban Zein kontu bankarioren informazioa nahi da
	 * @param sukurtsal_izen zein sukurtsalean bilatuko da kontu bankarioa
	 * @return kontu bankario horren informazioa
	 */
	public String[] langileKontuInfo(Langilea langilea, String iban, String sukurtsal_izen) {
		String[] informazio = new String[5];
		informazio[1]="";
		for(int i=0;i<langilea.getSukurtsalak().size();i++) {
			if(langilea.getSukurtsalak().get(i).getKokalekua().equals(sukurtsal_izen)) {
				//Kontu bankarioak arakatu
				for(int j=0;j<langilea.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
					if(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban().equals(iban)) {
						informazio[0]= iban.substring(0,4)+" "+iban.substring(4,8)+" "+iban.substring(8,12)+" "+" "+iban.substring(12,14)+" "+" "+iban.substring(14);
						for(int k=0;k<langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getTxartelak().size();k++) {
							if(k==0) {
								informazio[1]=langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getTxartelak().get(k).getBezeroa().getIzena()+" "+ langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getTxartelak().get(k).getBezeroa().getAbizena();
							}else {
								informazio[1]=informazio[1]+", "+langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getTxartelak().get(k).getBezeroa().getIzena()+" "+ langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getTxartelak().get(k).getBezeroa().getAbizena();
							}
						}
						informazio[2]=String.valueOf(df.format(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getSaldoa())) +" €";
						informazio[3]=langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getEgoera();
						informazio[4]=String.valueOf(df.format(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHilekoLimitea()));
					}
				}
			}
		}		
		return informazio;		
	}

	/**
	 * 1900-2003 bitarteko urte guztiak string array batean sartzen ditu
	 * @return urteen arraya
	 */
	public static String[] urteakBete() {
		String[] urteak= new String[0];
		
		for(int i=2003;i>1900;i--) {
			String[] prob= new String[urteak.length+1];
			for(int j=0;j<urteak.length;j++) {
				prob[j]=urteak[j];
			}
			prob[urteak.length]=String.valueOf(i);
			urteak=prob;
		}		
		return urteak;
	}
	
	/**
	 * 1-12 bitarteko hilabete guztiak string array batean sartzen ditu
	 * @return hilabeteen arraya
	 */
	public static String[] hilakBete() {
		String[] hilak= new String[0];	
		for(int i=1;i<=12;i++) {
			String[] prob= new String[hilak.length+1];
			for(int j=0;j<hilak.length;j++) {
				prob[j]=hilak[j];
			}
			prob[hilak.length]=String.valueOf(i);
			hilak=prob;
		}		
		return hilak;		
	}
	
	/**
	 * hilabetearen egun kantitatea kalkulatzen du
	 * @param hila zein hilabeteren egunak bete nahi ditugu
	 * @return hilabete horren egun kantitatea (28,30 edo 31) array batean
	 */
	public static String[] egunakBete(int hila) {
		String[] egunak = new String[0];		
		for(int i=1;i<=31;i++) {
			if(i<=28 && hila==2) {
				String[] prob= new String[egunak.length+1];
				for(int j=0;j<egunak.length;j++) {
					prob[j]=egunak[j];
				}
				prob[egunak.length]=String.valueOf(i);
				egunak=prob;				
			}else if(i<=30 && (hila==4 || hila==6 || hila==9 || hila==11)) {
				String[] prob= new String[egunak.length+1];
				for(int j=0;j<egunak.length;j++) {
					prob[j]=egunak[j];
				}
				prob[egunak.length]=String.valueOf(i);
				egunak=prob;				
			}else if(hila==1 || hila==3 || hila==5 || hila==7 || hila==8 || hila==10 || hila==12) {
				String[] prob= new String[egunak.length+1];
				for(int j=0;j<egunak.length;j++) {
					prob[j]=egunak[j];
				}
				prob[egunak.length]=String.valueOf(i);
				egunak=prob;				
			}
		}		
		return egunak;
	}

	/**
	 * hipotekaren egoera ikusteko
	 * @param bezero Zeren bezeroa da hipoteka
	 * @param iban_bez Zein kontu bankarioren egoera ikusi nahi da
	 * @return array bat hipotekaren datuekin
	 */
	public String[] hipotekaEstatus(Bezeroa bezero, String iban_bez){
		String[] hipoteka_estatus = new String [6];		
		for(int i=0;i<bezero.getTxartelak().size();i++) {
			if(bezero.getTxartelak().get(i).getKontuBankario().getIban().equals(iban_bez)) {
				hipoteka_estatus[0] = String.valueOf(bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getKantitatea());
				hipoteka_estatus[1] = bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getHasieraData();
				if(bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getAmaieraData()==null || bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getAmaieraData().equals("")) {
					hipoteka_estatus[2]="- - -";
				}else {
					hipoteka_estatus[2]=bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getAmaieraData();
				}
				hipoteka_estatus[3] = String.valueOf(bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getKomisioa());
				hipoteka_estatus[4] = String.valueOf(bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getOrdaindutakoa());
				hipoteka_estatus[5] = String.valueOf(bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getEgoera());
			}
		}			
		return hipoteka_estatus;
	}

	/**
	 * Bezero baten informazioa bilatzen du
	 * @param bezeroak Bezero guztien matrizea
	 * @param nan Zein bezero bilatu nahi dugu
	 * @return Bezero horren array bat informazio guztiarekin
	 */
	public String[] bezeroInfo(String[][] bezeroak,String nan) {
		String[] bezero_info = new String[8];
		
		for(int i=0;i<bezeroak.length;i++) {
			if(bezeroak[i][0].equals(nan)) {
				bezero_info[0]=bezeroak[i][0];
				bezero_info[1]=bezeroak[i][1];
				bezero_info[2]=bezeroak[i][2];
				bezero_info[3]=bezeroak[i][3];
				bezero_info[4]=bezeroak[i][4];
				bezero_info[5]=bezeroak[i][5];
				bezero_info[6]=bezeroak[i][6];
				bezero_info[7]=bezeroak[i][7];
			}
		}		
		return bezero_info;
	}

	/**
	 *  Langile baten informazioa bilatzen du
	 * @param langileak Langile guztien matrizea
	 * @param nan Zein langile bilatu nahi dugu
	 * @return Langile horren array bat informazio guztiarekin
	 */
	public String[] langileInfo(String[][] langileak,String nan) {
		String[] bezero_info = new String[10];
		
		for(int i=0;i<langileak.length;i++) {
			if(langileak[i][0].equals(nan)) {
				bezero_info[0]=langileak[i][0];
				bezero_info[1]=langileak[i][1];
				bezero_info[2]=langileak[i][2];
				bezero_info[3]=langileak[i][3];
				bezero_info[4]=langileak[i][4];
				bezero_info[5]=langileak[i][5];
				bezero_info[6]=langileak[i][6];
				bezero_info[7]=langileak[i][7];
				bezero_info[8]=langileak[i][8];
				bezero_info[9]=langileak[i][9];
			}
		}		
		return bezero_info;
	}
}