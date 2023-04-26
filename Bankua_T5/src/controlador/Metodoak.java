package controlador;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.Bezeroa;
import model.EntitateBankario;
import model.Gerentea;
import model.KontuBankario;
import model.Langilea;
import model.Sukurtsala;
import model.Zuzendaria;
import model.DiruSarrera;
import model.Transferentzia;
import model.Hipoteka;
import model.Txartela;

public class Metodoak {
	
	final String url = "jdbc:mysql://localhost:3306/bankua";
	final String erabiltzaile = "root";
	final String pass="";
	
	// EntitateBankario
	final String entitatebankario = "entitatebankario";
	final String id_entitate= "id_entitate";
	final String entitate_izena = "izena";
	final String entitateZenbaki = "entitateZenbaki";
	final String urlImg = "url";
	final String bounds = "bounds";
	
	// Bezeroa
	final String bezeroa = "bezeroa";
	final String nan = "nan";
	final String izena = "izena";
	final String abizenak = "abizenak";
	final String jaiotzeData = "jaiotzeData";
	final String sexua = "sexua";
	final String telefonoa = "telefonoa";
	final String pasahitza = "pasahitza";
	
	// Langile
	final String langile = "langile";
	final String id_langile = "id_langile";
	final String lanpostua = "lanpostua";
	
	
	// Sukurtsala
	final String sukurtsala = "sukurtsala";
	final String kodSukurtsala = "kodSukurtsala";
	final String kokalekua = "kokalekua";
	final String id_sukurtsal = "id_sukurtsal";
	
	// KontuBankario
	final String kontuBankario = "kontubankario";
	final String iban = "iban";
	final String saldoa = "saldoa";
	final String hilekoLimitea = "hilekoLimitea";
	final String sorreraData = "sorreraData";
	final String egoera = "egoera";
	
	// DiruSarrera
	final String diruSarrera = "dirusarrera";
	final String idSarrera = "idSarrera";
	final String kantitatea = "kantitatea";
	final String sarreraData = "sarreraData";
	final String igortzaile = "igortzaile";
	final String kontzeptua = "kontzeptua";
	final String ibanjasotzaile = "ibanjasotzaile";
	
	// Transferentzia
	final String transferentzia = "transferentzia";
	final String transferentziaData = "transferentziaData";
	final String jasotzailea = "jasotzailea";
	final String komisioa = "komisioa";
	final String ibanIgortzaile = "ibanIgortzaile";
	
	// Hipoteka
	final String hipoteka = "hipoteka";
	final String idHipoteka = "idHipoteka";
	final String ordaindutakoa = "ordaindutakoa";
	final String hasieraData = "hasieraData";
	final String amaieraData = "amaieraData";
	
	// Txartela
	final String txartela = "txartela";
	final String id_Txartela = "id_Txartela";
	final String nanBezeroa = "nanBezeroa";
	final String segurtasunKodea = "segurtasunKodea";
	final String mota = "mota";
	
	//Kudeatu
	final String kudeatu = "kudeatu";
	
	public boolean bezeroLogin(String nan_bez, String pas_bez) {
		boolean aurkituta = false;
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Bezeroa kargatzeko kontsulta egiten dugu
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();	
			ResultSet req = comand.executeQuery("select COUNT(*) as kant from "+bezeroa+" where "+nan+"='"+nan_bez+"' and "+pasahitza+"='"+pas_bez+"';");
			
			if(req.next()) {
				if(req.getInt("kant")==1) {
					aurkituta=true;
				}
			}

			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}
		
		return aurkituta;
	}
	
	public Bezeroa bezeroaKargatu(String nan_bezero){
		Bezeroa bezero=null;
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Bezeroa kargatzeko kontsulta egiten dugu
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand1 = (Statement) conn.createStatement();	
			ResultSet req1 = comand1.executeQuery("select "+nan+","+izena+","+abizenak+","+jaiotzeData+","+sexua+","+telefonoa+","+pasahitza+" from "+bezeroa+" WHERE "+nan+"='"+nan_bezero+"';");
			//Emaitzik badago
			if(req1.next()) {
				String bez_nan = req1.getString(1);
				String bez_izen = req1.getString(2);
				String bez_abiz = req1.getString(3);
				String bez_data = req1.getString(4);
				String bez_sexu = req1.getString(5);
				String bez_tel = req1.getString(6);
				String bez_pass = req1.getString(7);
				
				//Bezeroan dauden ArrayListak sortu
				ArrayList<Txartela> txartelak = new ArrayList<>();
				
				//Txartelak kargatzeko kontsulta egiten dugu
				Statement comand2 = (Statement) conn.createStatement();	
				ResultSet req2 = comand2.executeQuery("select "+segurtasunKodea+","+mota+" from "+txartela+" t join "+kudeatu+" ku on t."+id_Txartela+"=ku."+id_Txartela+" where "+nan+"='"+nan_bezero+"';");
				//Emaitzik badaude
				while(req2.next()) {
					String segur_kode = req2.getString(1);
					String txartel_mota = req2.getString(2);
					
					KontuBankario kontuBank = null;
					//Kontu Bankarioak kargatzeko kontsulta egiten dugu
					Statement comand3 = (Statement) conn.createStatement();	
					ResultSet req3 = comand3.executeQuery("select k."+iban+",k."+saldoa+",k."+hilekoLimitea+",k."+sorreraData+",k."+egoera+" from "+kontuBankario+" k join "+kudeatu+" ku on k."+iban+"=ku."+iban+" where ku."+nan+"='"+nan_bezero+"';");
					//Emaitzik badaude
					if(req3.next()) {
						String iban_bezero = req3.getString(1);
						double saldo_kontu = req3.getDouble(2);
						double limit_kontu = req3.getDouble(3);
						String data_kontu = req3.getString(4);
						String egoera_kontu = req3.getString(5);
						
						//Kontu Bankarioan dauden ArrayListak sortu
						ArrayList<DiruSarrera> diruSarrerak = new ArrayList<>();
						ArrayList<Transferentzia> transferentziak = new ArrayList<>();
						Hipoteka hipoteka_kontu = null;
						Sukurtsala sukurtsal_kontu = null;
						
						//Transferentziak kargatzeko kontsulta egiten dugu
						Statement comand4 = (Statement) conn.createStatement();	
						ResultSet req4 = comand4.executeQuery("select t."+kantitatea+",t."+transferentziaData+",t."+jasotzailea+",t."+kontzeptua+" from "+transferentzia+" t join "+kontuBankario+"  k on t."+ibanIgortzaile+"=k."+iban+" where k."+iban+"='"+iban_bezero+"';");
						//Emaitzik badaude
						while(req4.next()) {
							double t_kant = req4.getDouble(1);
							String t_data = req4.getString(2);
							String t_jaso = req4.getString(3);
							String t_kontzeptu = req4.getString(4);
							//Transferentzia ArrayListean gorde							
							Transferentzia tranzferentzia_kontu = new Transferentzia(t_kant,t_data,t_kontzeptu,t_jaso);
							transferentziak.add(tranzferentzia_kontu);
						}

						//Diru Sarrerak kargatzeko kontsulta egiten dugu
						Statement comand5 = (Statement) conn.createStatement();	
						ResultSet req5 = comand5.executeQuery("select t."+kantitatea+",t."+sarreraData+",t."+igortzaile+",t."+kontzeptua+" from "+diruSarrera +" t join "+kontuBankario+"  k on t."+ibanjasotzaile+"=k."+iban+" where k."+iban+"='"+iban_bezero+"';");
						//Emaitzik badaude
						while(req5.next()) {
							double s_kant = req5.getDouble(1);
							String s_data = req5.getString(2);
							String s_igor = req5.getString(3);
							String s_kontzeptu = req5.getString(4);
							//Diru Sarrera ArrayListean gorde
							DiruSarrera diruSarrera_kontu = new DiruSarrera(s_kant,s_data,s_kontzeptu,s_igor);
							diruSarrerak.add(diruSarrera_kontu);
						}
						//Hipoteka kargatzeko kontsulta egiten dugu
						Statement comand6 = (Statement) conn.createStatement();	
						ResultSet req6 = comand6.executeQuery("select h."+kantitatea+",h."+ordaindutakoa+",h."+komisioa+",h."+hasieraData+",h."+amaieraData+",h."+egoera+" from "+hipoteka+" h join "+kontuBankario+"  k on h."+iban+"=k."+iban+" where k."+iban+"='"+iban_bezero+"';");
						//Emaitzik badaude
						while(req6.next()) {
							double h_kant = req6.getDouble(1);
							double h_ordaindu = req6.getDouble(2);
							double h_komi = req6.getDouble(3);
							String h_hasiera = req6.getString(4);
							String h_amaiera = req6.getString(5);
							String h_egoera = req6.getString(6);
							//Datuak hipotekan gorde
							hipoteka_kontu = new Hipoteka(h_kant,h_ordaindu,h_komi,h_hasiera,h_amaiera,h_egoera);
						}						
						//Sukurtsala kargatzeko kontsulta egiten dugu
						Statement comand7 = (Statement) conn.createStatement();	
						ResultSet req7 = comand7.executeQuery("select s."+kodSukurtsala+",s."+kokalekua+",s."+id_sukurtsal+" from "+sukurtsala+" s join "+kontuBankario+" k on s."+id_sukurtsal+"=k."+id_sukurtsal+" where k."+iban+"='"+iban_bezero+"';");
						//Emaitzik badaude
						if(req7.next()) {
							String suk_kod = req7.getString(1);
							String suk_kok = req7.getString(2);
							String suk_id = req7.getString(3);
							
							//Sukurtsalean dagoen Entitatea Sortu
							EntitateBankario entitate_kontu = null;
							//Sukurtsala kargatzeko kontsulta egiten dugu
							Statement comand8 = (Statement) conn.createStatement();	
							ResultSet req8 = comand8.executeQuery("select e."+id_entitate+", e."+izena+", e."+entitateZenbaki+", e."+url+", e."+bounds+" from "+entitatebankario+" e join "+sukurtsala+" s on e."+id_entitate+"=s."+id_entitate+" where s."+id_sukurtsal+"='"+suk_id+"';");
							//Emaitzik badaude
							if(req8.next()) {
								String ent_id = req8.getString(1);
								String ent_izen = req8.getString(2);
								String ent_zbk = req8.getString(3);
								String ent_url = req8.getString(4);
								String ent_bound = req8.getString(5);
								//Datuak Entitate Bankarioan gorde
								entitate_kontu= new EntitateBankario(ent_izen,ent_id,ent_zbk,ent_url,ent_bound);
							}							
							//Datuak Sukurtsalean gorde
							sukurtsal_kontu = new Sukurtsala(suk_id,suk_kod,suk_kod,entitate_kontu);
						}
						//Datuak Kontu Bankarioan gorde
						kontuBank = new KontuBankario(iban_bezero,saldo_kontu,limit_kontu,data_kontu,egoera_kontu,diruSarrerak,transferentziak,hipoteka_kontu,sukurtsal_kontu);						
					}					
					//Txartela ArrayListean gorde
					Txartela txartel = new Txartela(segur_kode,txartel_mota,kontuBank);
					txartelak.add(txartel);
				}
				//Datuak Bezeroan gorde				
				bezero = new Bezeroa(bez_nan,bez_izen,bez_abiz,bez_data,bez_sexu,bez_tel,bez_pass,txartelak);
			}				

			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}		
		return bezero;
	}
	
	/*public String langileLogin(String erabiltzaile, String pasahitza, ArrayList<EntitateBankario> entitateak) {
		String login= null;
		
		for(int i=0;i<entitateak.size() && login==null;i++) {
			for(int j=0;j<entitateak.get(i).getSukurtsalak().size() && login==null;j++) {
				for(int k=0;k<entitateak.get(i).getSukurtsalak().get(j).getLangileak().size() && login==null;k++) {
					if(entitateak.get(i).getSukurtsalak().get(j).getLangileak().get(k).getNan().equals(erabiltzaile) && entitateak.get(i).getSukurtsalak().get(j).getLangileak().get(k).getPasahitza().equals(pasahitza)) {
						login=entitateak.get(i).getSukurtsalak().get(j).getLangileak().get(k).getLanpostu();
					}
				}
			}
		}		
		return login;
	}*/
}
