package controlador;

import java.text.DecimalFormat;

import model.Bezeroa;
import model.Langilea;

public class TaulaMetodoak {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    
	/**
	 * Metodo honen funtzioa da, zehaztutako lursailaren banku-kontuak bilatzea eta haien xehetasunak String-en matrize batean itzultztea.
	 * @param langilea Nork egiten du kontsulta
	 * @param sukurtsal_izen Zein sukurtsalaren kontu bankarioak nahi ditugu
	 * @return Matrize bat topatutako kontu bankario guztien informazioarekin
	 */
	public String[][] langilearenSukurtsalarenKontuak(Langilea langilea, String sukurtsal_izen){
		String[][] kontuak = new String[0][3];
		//Sukurtsalak arakatu
		for(int i=0;i<langilea.getSukurtsalak().size();i++) {
			if(langilea.getSukurtsalak().get(i).getKokalekua().equals(sukurtsal_izen)) {
				//Kontu bankarioak arakatu
				for(int j=0;j<langilea.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
					String[][] kontuak_prob = new String[kontuak.length+1][3];
					for(int k=0;k<kontuak.length;k++) {
						for(int h=0;h<kontuak[k].length;h++) {
							kontuak_prob[k][h]=kontuak[k][h];
						}
					}
					kontuak_prob[kontuak.length][0]=langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban();
					kontuak_prob[kontuak.length][1]=String.valueOf(df.format(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getSaldoa())+" €");
					kontuak_prob[kontuak.length][2]=langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getEgoera();
					kontuak=kontuak_prob;
				}
			}
		}		
		return kontuak;
	}
		
	/**
	 * Metodo honek langile baten kontutan transferentzia egin diren informazioa itzultzen du
	 * @param langilea transferentzial ikusi nahi dituen langilea
	 * @param iban transferentziak ikusi nahi diren kontuaren IBAN zenbakia
	 * @return transfererentzia guztien informazioa matrize batean bueltatzen du, lerro bakoitza transferentzia bat
	 */
	public String[][] langileKontuTransfer(Langilea langilea, String iban) {
		String[][] transfer_info = new String[0][5];
		//Sukurtsalak arakatu
		for(int i=0;i<langilea.getSukurtsalak().size();i++) {
			//Kontu Bankarioak arakatu
			for(int j=0;j<langilea.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
				if(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban().equals(iban)) {
					//Transferentziak arakatu
					for(int k=0;k<langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getTransferentziak().size();k++) {
						
						String[][] transfer_prob = new String[transfer_info.length+1][5];
						for(int l=0;l<transfer_info.length;l++) {
							for(int h=0;h<transfer_info[l].length;h++) {
								transfer_prob[l][h]=transfer_info[l][h];
							}
						}
						transfer_prob[transfer_info.length][0] = df.format(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getTransferentziak().get(k).getKantitatea())+" €";
						transfer_prob[transfer_info.length][1] = langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getTransferentziak().get(k).getTransferentziaData();
						String jasotzaile_iban = langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getTransferentziak().get(k).getJasotzailea();
						String iban_jaso = jasotzaile_iban.substring(0,4)+" "+jasotzaile_iban.substring(4,8)+" "+jasotzaile_iban.substring(8,12)+" "+" "+jasotzaile_iban.substring(12,14)+" "+" "+jasotzaile_iban.substring(14);
						transfer_prob[transfer_info.length][2] = iban_jaso;
						transfer_prob[transfer_info.length][3] = langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getTransferentziak().get(k).getKotzeptua();
						transfer_prob[transfer_info.length][4] = langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getTransferentziak().get(k).getKomisioa()+" %";
						transfer_info=transfer_prob;
					}
				}
			}
		}		
		return transfer_info;
	}
	
	/**
	 * Metodo honek langile baten kontutan diru sarrerak egin diren informazioa itzultzen du
	 * @param langilea diru sarrerak ikusi nahi dituen langilea
	 * @param iban diru sarrerak ikusi nahi diren kontuaren IBAN zenbakia
	 * @return diru sarreren guztien informazioa matrize batean bueltatzen du, lerro bakoitza sarrera bat
	 */
	public String[][] langileKontuSarrerak(Langilea langilea, String iban) {
		String[][] sarrera_info = new String[0][4];
		//Sukurtsalak arakatu
		for(int i=0;i<langilea.getSukurtsalak().size();i++) {
			//Kontu Bankarioak arakatu
			for(int j=0;j<langilea.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
				if(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban().equals(iban)) {
					//Transferentziak arakatu
					for(int k=0;k<langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getDiruSarrerak().size();k++) {
						
						String[][] sarrera_prob = new String[sarrera_info.length+1][5];
						for(int l=0;l<sarrera_info.length;l++) {
							for(int h=0;h<sarrera_info[l].length;h++) {
								sarrera_prob[l][h]=sarrera_info[l][h];
							}
						}
						sarrera_prob[sarrera_info.length][0] = df.format(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getDiruSarrerak().get(k).getKantitatea())+" €";
						sarrera_prob[sarrera_info.length][1] = langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getDiruSarrerak().get(k).getSarreraData();
						String igor_iban = langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getDiruSarrerak().get(k).getIgortzailea();
						String iban_igor = igor_iban.substring(0,4)+" "+igor_iban.substring(4,8)+" "+igor_iban.substring(8,12)+" "+" "+igor_iban.substring(12,16)+" "+" "+igor_iban.substring(16,20)+" "+" "+igor_iban.substring(20);
						sarrera_prob[sarrera_info.length][2] = iban_igor;
						sarrera_prob[sarrera_info.length][3] = langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getDiruSarrerak().get(k).getKontzeptua();
						sarrera_info=sarrera_prob;
					}
				}
			}
		}		
		return sarrera_info;
	}

	/**
	 * Langile baten sukurtsal batean "ixteko" egoeran dauden kontu bankarioak bilatzen ditu
	 * @param langilea Zein langile egiten du kontsulta
	 * @param sukurtsala Zein sukurtsalean bilatu nahi du
	 * @return Ixteko dauden kontu bankarioen matrize bat, lerro bakoitza kontu bat
	 */
	public String[][] ixtekoKontuak(Langilea langilea, String sukurtsala){
		String[][] kontuak = new String[0][3];
		//Sukurtsalak arakatu
		for(int i=0;i<langilea.getSukurtsalak().size();i++) {
			if(langilea.getSukurtsalak().get(i).getKokalekua().equals(sukurtsala)) {
				//Kontu bankarioak arakatu
				for(int j=0;j<langilea.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
					if(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getEgoera().equals("ixteko")) {
						String[][] prob = new String[kontuak.length+1][5];
						for(int l=0;l<kontuak.length;l++) {
							for(int h=0;h<kontuak[l].length;h++) {
								prob[l][h]=kontuak[l][h];
							}
						}
						String iban = langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban();
						String iban_ixteko = iban.substring(0,4)+" "+iban.substring(4,8)+" "+iban.substring(8,12)+" "+" "+iban.substring(12,14)+" "+" "+iban.substring(14);
						prob[kontuak.length][0]=iban_ixteko;				
						prob[kontuak.length][1]=String.valueOf(df.format(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getSaldoa()))+" €";
						prob[kontuak.length][2]=langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getEgoera();
						kontuak=prob;
					}
				}
			}
		}		
		return kontuak;
	}
	
	/**
	 * Metodo honek sukurtsal batean eskatuta dauden hipotekak biltzen ditu
	 * @param langilea Zein langile egiten du kontsulta
	 * @param sukurtsal_izen Zein sukurtsalean bilatu nahi den
	 * @return Hipoteka duten kontu bankario guztiak matrize batean, lerro bakoitza kontu bankario bat
	 */
	public String[][] eskatutakoHipotekak(Langilea langilea, String sukurtsal_izen){
		String[][] eskatutak = new String [0][5];		
		//Sukurtsalak arakatu
		for(int i=0;i<langilea.getSukurtsalak().size();i++) {
			if(langilea.getSukurtsalak().get(i).getKokalekua().equals(sukurtsal_izen)) {
				//Kontu bankarioak arakatu
				for(int j=0;j<langilea.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
					if(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka()!=null && langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEgoera().equals("eskatuta")) {
						String[][] prob = new String[eskatutak.length+1][5];
						for(int k=0;k<eskatutak.length;k++) {
							for(int l=0;l<eskatutak[k].length;l++) {
								prob[k][l]=eskatutak[k][l];
							}
						}
						String iban = langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban();
						String iban_eskatuta = iban.substring(0,4)+" "+iban.substring(4,8)+" "+iban.substring(8,12)+" "+" "+iban.substring(12,14)+" "+" "+iban.substring(14);
						prob[eskatutak.length][0]=iban_eskatuta;
						prob[eskatutak.length][1]= String.valueOf(df.format(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getKantitatea()))+" €";
						prob[eskatutak.length][2]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getKomisioa()+" %";
						prob[eskatutak.length][3]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEpeMuga();
						prob[eskatutak.length][4]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEgoera();
						eskatutak=prob;
					}
				}
			}
		}		
		return eskatutak;
	}
	
	/**
	 * Metodo honek sukurtsal batean onartuta dauden hipotekak biltzen ditu
	 * @param langilea Zein langile egiten du kontsulta
	 * @param sukurtsal_izen Zein sukurtsalean bilatu nahi den
	 * @return Hipoteka onartuta duten kontu bankario guztiak matrize batean, lerro bakoitza kontu bankario bat
	 */
	public String[][] onartutakoHipotekak(Langilea langilea, String sukurtsal_izen){
		String[][] onartutak = new String [0][7];		
		//Sukurtsalak arakatu
		for(int i=0;i<langilea.getSukurtsalak().size();i++) {
			if(langilea.getSukurtsalak().get(i).getKokalekua().equals(sukurtsal_izen)) {
				//Kontu bankarioak arakatu
				for(int j=0;j<langilea.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
					if(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka()!=null && langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEgoera().equals("onartuta")) {
						String[][] prob = new String[onartutak.length+1][7];
						for(int k=0;k<onartutak.length;k++) {
							for(int l=0;l<onartutak[k].length;l++) {
								prob[k][l]=onartutak[k][l];
							}
						}
						String iban = langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban();
						String iban_eskatuta = iban.substring(0,4)+" "+iban.substring(4,8)+" "+iban.substring(8,12)+" "+" "+iban.substring(12,14)+" "+" "+iban.substring(14);
						prob[onartutak.length][0]=iban_eskatuta;
						prob[onartutak.length][1]= String.valueOf(df.format(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getKantitatea()))+" €";
						prob[onartutak.length][2]= String.valueOf(df.format(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getOrdaindutakoa()))+ " €";						
						prob[onartutak.length][3]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getKomisioa()+" %";
						prob[onartutak.length][4]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getHasieraData();						
						prob[onartutak.length][5]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEpeMuga();
						prob[onartutak.length][6]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEgoera();
						onartutak=prob;
					}
				}
			}
		}		
		return onartutak;
	}
	
	/**
	 * Metodo honek sukurtsal batean errefusatuta dauden hipotekak biltzen ditu
	 * @param langilea Zein langile egiten du kontsulta
	 * @param sukurtsal_izen Zein sukurtsalean bilatu nahi den
	 * @return Hipoteka errefusatuta duten kontu bankario guztiak matrize batean, lerro bakoitza kontu bankario bat
	 */
	public String[][] errefusatutakoHipotekak(Langilea langilea, String sukurtsal_izen){
		String[][] errefusatutak = new String [0][5];		
		//Sukurtsalak arakatu
		for(int i=0;i<langilea.getSukurtsalak().size();i++) {
			if(langilea.getSukurtsalak().get(i).getKokalekua().equals(sukurtsal_izen)) {
				//Kontu bankarioak arakatu
				for(int j=0;j<langilea.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
					if(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka()!=null && langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEgoera().equals("errefusatuta")) {
						String[][] prob = new String[errefusatutak.length+1][5];
						for(int k=0;k<errefusatutak.length;k++) {
							for(int l=0;l<errefusatutak[k].length;l++) {
								prob[k][l]=errefusatutak[k][l];
							}
						}
						String iban = langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban();
						String iban_eskatuta = iban.substring(0,4)+" "+iban.substring(4,8)+" "+iban.substring(8,12)+" "+" "+iban.substring(12,14)+" "+" "+iban.substring(14);
						prob[errefusatutak.length][0]=iban_eskatuta;
						prob[errefusatutak.length][1]= String.valueOf(df.format(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getKantitatea()))+" €";
						prob[errefusatutak.length][2]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getKomisioa()+" %";
						prob[errefusatutak.length][3]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEpeMuga();
						prob[errefusatutak.length][4]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEgoera();
						errefusatutak=prob;
					}
				}
			}
		}		
		return errefusatutak;
	}
	
	/**
	 * Metodo honek sukurtsal batean itxi behar diren hipotekak biltzen ditu
	 * @param langilea Zein langile egiten du kontsulta
	 * @param sukurtsal_izen Zein sukurtsalean bilatu nahi den
	 * @return Hipoteka itxi behar diren kontu bankario guztiak matrize batean, lerro bakoitza kontu bankario bat
	 */
	public String[][] itxitakoHipotekak(Langilea langilea, String sukurtsal_izen){
		String[][] itxitak = new String [0][8];		
		//Sukurtsalak arakatu
		for(int i=0;i<langilea.getSukurtsalak().size();i++) {
			if(langilea.getSukurtsalak().get(i).getKokalekua().equals(sukurtsal_izen)) {
				//Kontu bankarioak arakatu
				for(int j=0;j<langilea.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
					if(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka()!=null && langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEgoera().equals("itxita")) {
						String[][] prob = new String[itxitak.length+1][8];
						for(int k=0;k<itxitak.length;k++) {
							for(int l=0;l<itxitak[k].length;l++) {
								prob[k][l]=itxitak[k][l];
							}
						}
						String iban = langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban();
						String iban_eskatuta = iban.substring(0,4)+" "+iban.substring(4,8)+" "+iban.substring(8,12)+" "+" "+iban.substring(12,14)+" "+" "+iban.substring(14);
						prob[itxitak.length][0]=iban_eskatuta;
						prob[itxitak.length][1]= String.valueOf(df.format(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getKantitatea()))+" €";
						prob[itxitak.length][2]= String.valueOf(df.format(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getOrdaindutakoa()))+ " €";						
						prob[itxitak.length][3]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getKomisioa()+" %";
						prob[itxitak.length][4]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getHasieraData();						
						prob[itxitak.length][5]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getAmaieraData();						
						prob[itxitak.length][6]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEpeMuga();
						prob[itxitak.length][7]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEgoera();
						itxitak=prob;
					}
				}
			}
		}		
		return itxitak;
	}

	/**
	 * bezero baten kontu bankarioak bilatzen ditu
	 * @param bezeroa Zein bezeroren kontu bankarioak bilatuko dira
	 * @param id_entitate Zein entitate bankarioan
	 * @return Matrize bat bezero horren kontu bankarioak entitate batean, lerro bat kontu bankario bat
	 */
	public String[][] bezeroarenKontuak(Bezeroa bezeroa, int id_entitate) {
		String[][] kontuak = new String[0][3];
		
		// Txartelak arakatu
		for(int i = 0; i < bezeroa.getTxartelak().size(); i++) {
			if(bezeroa.getTxartelak().get(i).getKontuBankario().getSukurtsala().getEntitateBankario().getIdEntitatea().equals(Integer.toString(id_entitate))){
				String[][] kontuak_prob = new String[kontuak.length+1][3];
				for(int j = 0; j < kontuak.length; j ++) {
					for(int k = 0; k < kontuak[j].length; k ++) {
						kontuak_prob[j][k] = kontuak[j][k];
					}
				}
				String iban =bezeroa.getTxartelak().get(i).getKontuBankario().getIban();
				kontuak_prob[kontuak.length][0] = iban.substring(0,4)+" "+iban.substring(4,8)+" "+iban.substring(8,12)+" "+" "+iban.substring(12,14)+" "+" "+iban.substring(14);
				kontuak_prob[kontuak.length][1] = String.valueOf(df.format(bezeroa.getTxartelak().get(i).getKontuBankario().getSaldoa())+" €");
				kontuak_prob[kontuak.length][2] = bezeroa.getTxartelak().get(i).getKontuBankario().getEgoera();
				kontuak = kontuak_prob;	
			}
		}	
		return kontuak;
	}
	
	/**
	 * kontu bankario baten transferentzia guztiak bilatzen ditu
	 * @param bezeroa Zein bezeroren transferentziak
	 * @param iban Bezeroaren zein kontu bankarioan
	 * @return Matrize bat kontu bankario horren transferentzia guztiekin, lerro bat transferentzia bat
	 */
	public String[][] transferentziakIkusi(Bezeroa bezeroa, String iban) {
		String[][] transferentziak = new String [0][4];
		
		for(int i = 0; i < bezeroa.getTxartelak().size(); i ++) {
			if(bezeroa.getTxartelak().get(i).getKontuBankario().getIban().equals(iban)) {
				for(int j = 0; j < bezeroa.getTxartelak().get(i).getKontuBankario().getTransferentziak().size(); j ++) {
					String[][] transferentziak_prob = new String[transferentziak.length+1][4];
					for(int k = 0; k < transferentziak.length; k ++) {
						for(int l = 0; l < transferentziak[k].length; l ++) {
							transferentziak_prob[k][l] = transferentziak[k][l];
						}
					}
					String ibana = bezeroa.getTxartelak().get(i).getKontuBankario().getTransferentziak().get(j).getJasotzailea();
					transferentziak_prob[transferentziak.length][0] = ibana.substring(0,4)+" "+ibana.substring(4,8)+" "+ibana.substring(8,12)+" "+" "+ibana.substring(12,14)+" "+" "+ibana.substring(14);
					transferentziak_prob[transferentziak.length][1] = String.valueOf(df.format(bezeroa.getTxartelak().get(i).getKontuBankario().getTransferentziak().get(j).getKantitatea())) + " €";
					transferentziak_prob[transferentziak.length][2] = bezeroa.getTxartelak().get(i).getKontuBankario().getTransferentziak().get(j).getKotzeptua();
					transferentziak_prob[transferentziak.length][3] = bezeroa.getTxartelak().get(i).getKontuBankario().getTransferentziak().get(j).getTransferentziaData();
					transferentziak = transferentziak_prob;	
				}
			}
		}
		
		return transferentziak;
	}
	
	/**
	 * kontu bankario baten diru sarrera guztiak bilatzen ditu
	 * @param bezeroa Zein bezeroren diru sarrera
	 * @param iban Bezeroaren zein kontu bankarioan
	 * @return Matrize bat kontu bankario horren sarrera guztiekin, lerro bat sarrera bat
	 */
	public String[][] diruSarrerakIkusi(Bezeroa bezeroa,String iban) {
		String[][] diruSarrerak = new String [0][4];
		
		for(int i = 0; i < bezeroa.getTxartelak().size(); i ++) {
			if(bezeroa.getTxartelak().get(i).getKontuBankario().getIban().equals(iban)) {
				for(int j = 0; j < bezeroa.getTxartelak().get(i).getKontuBankario().getDiruSarrerak().size(); j ++) {
					String[][] diruSarrerak_prob = new String[diruSarrerak.length+1][4];
					for(int k = 0; k < diruSarrerak.length; k ++) {
						for(int l = 0; l < diruSarrerak[k].length; l ++) {
							diruSarrerak_prob[k][l] = diruSarrerak[k][l];
						}
					}
					String ibana = bezeroa.getTxartelak().get(i).getKontuBankario().getDiruSarrerak().get(j).getIgortzailea();
					diruSarrerak_prob[diruSarrerak.length][0] = ibana.substring(0,4)+" "+ibana.substring(4,8)+" "+ibana.substring(8,12)+" "+" "+ibana.substring(12,14)+" "+" "+ibana.substring(14);
					diruSarrerak_prob[diruSarrerak.length][1] = String.valueOf(df.format(bezeroa.getTxartelak().get(i).getKontuBankario().getDiruSarrerak().get(j).getKantitatea())) + " €";
					diruSarrerak_prob[diruSarrerak.length][2] = bezeroa.getTxartelak().get(i).getKontuBankario().getDiruSarrerak().get(j).getKontzeptua();
					diruSarrerak_prob[diruSarrerak.length][3] = bezeroa.getTxartelak().get(i).getKontuBankario().getDiruSarrerak().get(j).getSarreraData();
					diruSarrerak = diruSarrerak_prob;	
				}
			}
		}
		
		return diruSarrerak;
	}

	/**
	 * Bezero bat sortzen dugunean bezeroen arrayan sartzeko
	 * @param nan_bez Bezeroaren NAN
	 * @param txartel_id bezeroare txartela
	 * @param pass bezeroaren pasahitza
	 * @param txartel_mota bezeroaren txartel mota
	 * @param bezeroak Existitzen diren bezeroak
	 * @return Matrize bat bezero berriarekin
	 */
	public static String[][] bezeroGordeArray(String nan_bez,String txartel_id, String pass,String txartel_mota, String[][] bezeroak){
		String[][] prob= new String[bezeroak.length+1][4];
		for(int i=0;i<bezeroak.length;i++) {
			for(int  j=0;j<bezeroak[i].length;j++) {
				prob[i][j]= bezeroak[i][j];
			}
		}
		prob[bezeroak.length][0]=nan_bez;
		prob[bezeroak.length][1]=txartel_id;
		prob[bezeroak.length][2]=pass;	
		prob[bezeroak.length][3]=txartel_mota;
		bezeroak=prob;
		
		return bezeroak;
	}
}
