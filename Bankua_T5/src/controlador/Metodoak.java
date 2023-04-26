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
	final String trasferentzia = "trasferentzia";
	final String trasnferentziaData = "trasnferentziaData";
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
	final String idTxartela = "idTxartela";
	final String nanBezeroa = "nanBezeroa";
	final String segurtasunKodea = "segurtasunKodea";
	
	public ArrayList<EntitateBankario> entitateakKargatu(){
		ArrayList<EntitateBankario> entitateak = new ArrayList<>();
		ArrayList<Bezeroa> bezeroak = new ArrayList<>();
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta EntitateBankariok kargatzeko kontsulta egiten dugu
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand1 = (Statement) conn.createStatement();	
			ResultSet req1 = comand1.executeQuery("Select * from "+entitatebankario+";");
			
			while(req1.next()) {				
				String id_enti = req1.getString(1);
				String izen_ent = req1.getString(2);
				String ent_zenb = req1.getString(3);
				String url_ent = req1.getString(4);
				String bounds_ent = req1.getString(5);
				
				ArrayList<Sukurtsala> sukurtsalak = new ArrayList<>();				

				//Sukurtsalak kargatzeko kontsulta egiten dugu
				Statement comand2 = (Statement) conn.createStatement();	
				ResultSet req2 = comand2.executeQuery("Select "+kodSukurtsala+","+kokalekua+","+id_sukurtsal+" from "+sukurtsala+" WHERE "+id_entitate+"="+id_enti+";");
				//Kontsultaren datuak gorde
				while(req2.next()) {					
					String kod_suk = req2.getString(1);
					String kokaleku = req2.getString(2);
					int id_suk = Integer.parseInt(req2.getString(3));
					
					//Sukurtsalean dauden ArrayListak sortu
					ArrayList<KontuBankario> KontuBankariok= new ArrayList<>();
					ArrayList<Langilea> langileak = new ArrayList<>();
					
					//Langileak kargatzeko kontsulta egiten dugu
					Statement comand3 = (Statement) conn.createStatement();
					ResultSet req3 = comand3.executeQuery("Select "+nan+","+izena+","+abizenak+","+jaiotzeData+","+sexua+","+telefonoa+","+pasahitza+","+lanpostua+" from "+langile+" WHERE "+id_entitate+"="+id_enti+" AND "+id_sukurtsal+"="+id_suk+";");
					//Kontsultaren datuak gorde
					while(req3.next()) {						
						String nan_langile = req3.getString(1);
						String izen_langile = req3.getString(2);
						String abizen_langile = req3.getString(3);
						String jaiotze_langile = req3.getString(4);
						String sexu_langile = req3.getString(5);
						String tel_langile = req3.getString(6);
						String pass_langile = req3.getString(7);
						String lanpostu = req3.getString(8);
						
						//Lanpostuaren arabera Gerentea edo Zuzendaria sortzen du
						if(lanpostu.equals("gerentea")) {
							Langilea gerente = new Gerentea(nan_langile,izen_langile,abizen_langile,jaiotze_langile,sexu_langile,tel_langile,pass_langile,lanpostu);
							langileak.add(gerente);
						}else if(lanpostu.equals("zuzendaria")) {
							Langilea zuzendari = new Zuzendaria(nan_langile,izen_langile,abizen_langile,jaiotze_langile,sexu_langile,tel_langile,pass_langile,lanpostu);
							langileak.add(zuzendari);
						}
					}	
					
					//Kontu Bankarioan dauden ArrayListak sortu
					ArrayList<DiruSarrera> diruSarrerak = new ArrayList<>();
					ArrayList<Transferentzia> transferentziak = new ArrayList<>();
					ArrayList<Hipoteka> hipotekak = new ArrayList<>();
					//Kontu Bankarioak kargatzeko kontsulta egiten dugu
					Statement comand4 = (Statement) conn.createStatement();
					ResultSet req4 = comand4.executeQuery("Select "+iban+","+saldoa+","+hilekoLimitea+","+sorreraData+","+egoera+" from "+kontuBankario+" WHERE "+id_entitate+"="+id_enti+" AND "+id_sukurtsal+"="+id_suk+";");
					//Kontsultaren datuak gorde
					while(req4.next()) {
						String iban_kontu = req4.getString(1);
						double saldo_kontu = Double.parseDouble(req4.getString(2));
						double hil_limit = Double.parseDouble(req4.getString(3));
						String sorrera_kontu = req4.getString(4);
						String egoera_kontu = req4.getString(5);
						
						//Kontu Bankarioaren Diru Sarrerak kargatzeko kontsulta egiten dugu
						Statement comand5 = (Statement) conn.createStatement();
						ResultSet req5 = comand5.executeQuery("Select "+kantitatea+","+sarreraData+","+igortzaile+","+kontzeptua+" from "+diruSarrera+" WHERE "+ibanjasotzaile+"="+iban_kontu+";");
						//Kontsultaren datuak gorde
						while(req5.next()) {
							double kant_sarrera = Double.parseDouble(req5.getString(1));
							String data_sarrera = req5.getString(2);
							String igor_sarrera = req5.getString(3);
							String kontzeptu_sarrera = req5.getString(4);
							//Diru Sarrera ArrayListari gehitu
							DiruSarrera diruSarrera = new DiruSarrera(kant_sarrera,data_sarrera,kontzeptu_sarrera,igor_sarrera);
							diruSarrerak.add(diruSarrera);
						}
						
						//Kontu Bankarioaren Transferentziak kargatzeko kontsulta egiten dugu
						Statement comand6 = (Statement) conn.createStatement();
						ResultSet req6 = comand6.executeQuery("Select "+kantitatea+","+trasnferentziaData+","+jasotzailea+","+kontzeptua+" from "+diruSarrera+" WHERE "+ibanIgortzaile+"="+iban_kontu+";");
						//Kontsultaren datuak gorde
						while(req6.next()) {
							double kant_transfer = Double.parseDouble(req6.getString(1));
							String data_transfer = req6.getString(2);
							String jaso_transfer = req6.getString(3);
							String kontzeptu_transfer = req6.getString(4);
							//Transferentzia ArrayListari gehitu
							Transferentzia transferentzia = new Transferentzia(kant_transfer,data_transfer,kontzeptu_transfer,jaso_transfer);
							transferentziak.add(transferentzia);
						}
						
						//Kontu Bankarioaren Hipotekak kargatzeko kontsulta egiten dugu
						Statement comand7 = (Statement) conn.createStatement();
						ResultSet req7 = comand7.executeQuery("Select "+kantitatea+","+ordaindutakoa+","+komisioa+","+hasieraData+","+amaieraData+","+egoera+" from "+diruSarrera+" WHERE "+iban+"="+iban_kontu+";");
						//Kontsultaren datuak gorde
						while(req7.next()) {
							double kant_hipo = Double.parseDouble(req7.getString(1));
							double ordaindu_hipo = Double.parseDouble(req7.getString(2));
							double komi_hipo = Double.parseDouble(req7.getString(3));
							String hasiera_hipo = req7.getString(4);
							String amaiera_hipo = req7.getString(5);
							String egoera_hipo = req7.getString(6);
							//Hipoteka ArrayListari gehitu
							Hipoteka hipoteka = new Hipoteka(kant_hipo,ordaindu_hipo,komi_hipo,hasiera_hipo,amaiera_hipo,egoera_hipo);
							hipotekak.add(hipoteka);
						}
						//Kontu Bankarioa ArrayListari gehitu
						KontuBankario kontuBank = new KontuBankario(iban_kontu,saldo_kontu,hil_limit,sorrera_kontu,egoera_kontu,diruSarrerak,transferentziak,hipotekak);
						KontuBankariok.add(kontuBank);
					}
					//Sukurtsala ArrayListari gehitu
					Sukurtsala sukurtsal = new Sukurtsala(id_suk,kod_suk,kokaleku,KontuBankariok,langileak);	
					sukurtsalak.add(sukurtsal);
					
				}
				//Entitate Bankarioa ArrayListari gehitu
				EntitateBankario entitateBank = new EntitateBankario(izen_ent,id_enti,ent_zenb,sukurtsalak,bounds_ent,url_ent);
				entitateak.add(entitateBank);
			}
			/*
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comandx = (Statement) conn.createStatement();	
			ResultSet reqx = comandx.executeQuery("Select * from "+bezeroa+";");
			
			while(reqx.next()) {
				String nan = reqx.getString(1);
				String izen = reqx.getString(2);
				String abizen = reqx.getString(3);
				String jaiotze = reqx.getString(4);
				
				String[] data_array = jaiotze.split("-");
				String jaiotze_data = data_array[1]+"-"+data_array[2]+"-"+data_array[0];			   
				    
				String sexu = reqx.getString(5);
				String tel = reqx.getString(6);
				String pass = reqx.getString(7);
				
				Bezeroa bezero = new Bezeroa(nan,izen,abizen,jaiotze_data,sexu,tel,pass);
				
				bezeroak.add(bezero);								
			}*/
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}		
		return entitateak;
	}
	
	public String langileLogin(String erabiltzaile, String pasahitza, ArrayList<EntitateBankario> entitateak) {
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
	}
}
