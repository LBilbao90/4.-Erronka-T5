package controlador;

import java.io.PrintWriter; 
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.Bezeroa;
import model.EntitateBankario;
import model.Gerentea;
import model.God;
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
	final String password="";
	
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
	

    private static final DecimalFormat df = new DecimalFormat("0.00");
    
	/**
	 * Bezeroaren logina zuzena edo okerra den esaten du.
	 * @param nan_bez Bezeroaren NAN
	 * @param pas_bez Bezeroaren pasahitza
	 * @return <b>true</b> login zuzena bada eta <b>false</b> login okerra bada
	 */
	public boolean bezeroLogin(String nan_bez, String pas_bez) {
		boolean aurkituta = false;
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Bezeroa logeatzeko kontsulta egiten dugu
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
	
	/**
	 * Langilearen logina zuzena edo okerra den esaten du eta zer motatakoa
	 * @param nan_lang Langilearen NAN
	 * @param pas_lang Langilearen pasahitza
	 * @return <b>god</b> langilea GOD erabiltzailea erabiltzean, <b>zuzendaria</b> langilea Zuzendaria erabiltzailea erabiltzean, <b>gerentea</b> langilea Gerentea erabiltzailea erabiltzean eta <b>null</b> logina okerra bada.
	 */
	public String langileLogin(String nan_lang, String pas_lang) {
		String login= null;
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Langilea logeatzeko kontsulta egiten dugu
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();	
			ResultSet req = comand.executeQuery("select "+lanpostua+" from "+langile+" where "+nan+"='"+nan_lang+"' and "+pasahitza+"='"+pas_lang+"';");
			//Daturik aurkitzen badu
			if(req.next()) {
				if(req.getString(1).equals("gerentea")) {
					login="gerentea";
				}else if(req.getString(1).equals("zuzendaria")) {
					login="zuzendaria";
				}else if(req.getString(1).equals("god")) {
					login="god";
				}
			}
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}
				
		return login;
	}
	
	public void loginErregistratu(String nan) {
		File file = new File("src/logs/loginLogs.txt");
		
			try {
				FileWriter fw = new FileWriter(file,true);
		    	BufferedWriter bw = new BufferedWriter(fw);
		    	PrintWriter pw = new PrintWriter(bw);

	    	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    	   LocalDateTime eguna = LocalDateTime.now();
		    	
		    	pw.println("Nan: "+nan.toUpperCase()+" - Data: "+dtf.format(eguna));
				
		    	pw.close();
			
			} catch (IOException e1) {
				e1.printStackTrace();
			}			
	}
	
	/**
	 * Bezeroaren klasea kargatzen du datu basetik eta bere barruan dauden atributu guztiak
	 * @param nan_bezero Bezeroaren NAN
	 * @return Bezeroaren objetua bere datuekin
	 */
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
				String[] data_split = bez_data.split("-");
				String bezero_data = data_split[1]+"-"+data_split[2]+"-"+data_split[0];
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
							ResultSet req8 = comand8.executeQuery("select e."+id_entitate+", e."+izena+", e."+entitateZenbaki+", e."+urlImg+", e."+bounds+" from "+entitatebankario+" e join "+sukurtsala+" s on e."+id_entitate+"=s."+id_entitate+" where s."+id_sukurtsal+"='"+suk_id+"';");
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
							sukurtsal_kontu = new Sukurtsala(suk_id,suk_kod,suk_kok,entitate_kontu);
						}
						//Datuak Kontu Bankarioan gorde
						kontuBank = new KontuBankario(iban_bezero,saldo_kontu,limit_kontu,data_kontu,egoera_kontu,diruSarrerak,transferentziak,hipoteka_kontu,sukurtsal_kontu);						
					}					
					//Txartela ArrayListean gorde
					Txartela txartel = new Txartela(segur_kode,txartel_mota,kontuBank);
					txartelak.add(txartel);
				}
				//Datuak Bezeroan gorde				
				bezero = new Bezeroa(bez_nan,bez_izen,bez_abiz,bezero_data,bez_sexu,bez_tel,bez_pass,txartelak);
			}				

			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}		
		return bezero;
	}
	
	/**
	 * Langilearen klasea kargatzen du datu basetik eta be barruan dauden atributu guztiak
	 * @param nan_langile Langilearen NAN
	 * @param lanpostu Langilearen pasahitza
	 * @return <b>God</b>, <b>Zuzendaria</b> edo <b>Gerentea</b> objetua kargatuta lanpostuaren arabera
	 */
	public Langilea langileaKargatu(String nan_langile, String lanpostu) {
		Langilea langilea = null;
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Langilea kargatzeko kontsulta egiten dugu
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand1 = (Statement) conn.createStatement();	
			ResultSet req1 = comand1.executeQuery("select l."+nan+",l."+izena+", l."+abizenak+", l."+jaiotzeData+", l."+sexua+", l."+telefonoa+", l."+pasahitza+", l."+lanpostua+" from "+langile+" l where l."+nan+"='"+nan_langile+"';");
			
			//Daturik aurkitzen badu
			if(req1.next()) {
				String lang_nan = req1.getString(1);
				String lang_izen = req1.getString(2);
				String lang_abiz = req1.getString(3);
				String lang_data = req1.getString(4);
				String[] data_split = lang_data.split("-");
				String langile_data = data_split[1]+"-"+data_split[2]+"-"+data_split[0];
				String lang_sex = req1.getString(5);
				String lang_tel = req1.getString(6);
				String lang_pass = req1.getString(7);
				String lang_lanpostu = req1.getString(8);
				//Langilean dauden ArrayListak sortu
				ArrayList<Sukurtsala> sukurtsalak = new ArrayList<Sukurtsala>();
				
				//Langilean dauden Sukurtsalen kontsulta
				Statement comand2 = (Statement) conn.createStatement();	
				ResultSet req2 = null;
				if(lanpostu.equals("zuzendaria")) {
					req2 = comand2.executeQuery("select s."+id_sukurtsal+", s."+kodSukurtsala+", s."+kokalekua+" from "+sukurtsala+" s join "+entitatebankario+" e on s."+id_entitate+"=e."+id_entitate+" where s."+id_entitate+"=(select s."+id_entitate+" from "+sukurtsala+" s JOIN "+langile+" l on s."+id_sukurtsal+"=l."+id_sukurtsal+" where l."+nan+"='"+nan_langile+"');");
				}else if(lanpostu.equals("gerentea")) {
					req2 = comand2.executeQuery("select s."+id_sukurtsal+", s."+kodSukurtsala+", s."+kokalekua+" from "+sukurtsala+" s join "+langile+" l on s."+id_sukurtsal+"=l."+id_sukurtsal+" where l."+nan+"='"+nan_langile+"';");
				}else if(lanpostu.equals("god")) {
					req2 = comand2.executeQuery("select s."+id_sukurtsal+", s."+kodSukurtsala+", s."+kokalekua+" from "+sukurtsala+" s;");
				}
				//Daturik aurkitzen badu
				while(req2.next()) {
					String suk_id = req2.getString(1);
					String suk_kod = req2.getString(2);
					String suk_kok = req2.getString(3);
					//Sukurtsalean dauden ArrayListak sortu
					ArrayList<KontuBankario> kontubankarioak = new ArrayList<>();
					EntitateBankario entitate_lang = null;					
					
					//Sukurtsalean dauden Entitate Bankarioaren kontsulta
					Statement comand3 = (Statement) conn.createStatement();	
					ResultSet req3 = comand3.executeQuery("select e."+id_entitate+", e."+izena+", e."+entitateZenbaki+", e."+urlImg+", e."+bounds+" from "+entitatebankario+" e join "+sukurtsala+" s on e."+id_entitate+"=s."+id_entitate+" where s."+id_sukurtsal+"='"+suk_id+"';");
					//Daturik aurkitzen badu
					if(req3.next()) {
						String ent_id = req3.getString(1);
						String ent_izen = req3.getString(2);
						String ent_zbk = req3.getString(3);
						String ent_url = req3.getString(4);
						String ent_bound = req3.getString(5);
						//Datuak Entitate Bankarioan gorde
						entitate_lang = new EntitateBankario(ent_izen,ent_id,ent_zbk,ent_bound,ent_url);
					}
					//Sukurtsalean dauden Kontu Bankarioaren kontsulta
					Statement comand4 = (Statement) conn.createStatement();	
					ResultSet req4 = comand4.executeQuery("select k."+iban+",k."+saldoa+",k."+hilekoLimitea+",k."+sorreraData+",k."+egoera+" from "+kontuBankario+" k join "+sukurtsala+" s on k."+id_sukurtsal+"=s."+id_sukurtsal+" where k."+id_sukurtsal+"='"+suk_id+"';");
					
					//Daturik aurkitzen badu
					while(req4.next()) {
						String kont_iban = req4.getString(1);
						double kont_saldo = req4.getDouble(2);
						double kont_limit = req4.getDouble(3);
						String kont_data = req4.getString(4);
						String kont_egoera = req4.getString(5);
						
						//Kontu Bankarioan dauden ArrayListak sortu
						ArrayList<DiruSarrera> diruSarrerak = new ArrayList<>();
						ArrayList<Transferentzia> transferentziak = new ArrayList<>();
						Hipoteka hipoteka_kontu = null;
						ArrayList<Txartela> txartelak = new ArrayList<>();
						
						//Transferentziak kargatzeko kontsulta egiten dugu
						Statement comand5 = (Statement) conn.createStatement();	
						ResultSet req5 = comand5.executeQuery("select t."+kantitatea+",t."+transferentziaData+",t."+jasotzailea+",t."+kontzeptua+" from "+transferentzia+" t join "+kontuBankario+"  k on t."+ibanIgortzaile+"=k."+iban+" where k."+iban+"='"+kont_iban+"';");
						//Emaitzik badaude
						while(req5.next()) {
							double t_kant = req5.getDouble(1);
							String t_data = req5.getString(2);
							String t_jaso = req5.getString(3);
							String t_kontzeptu = req5.getString(4);
							//Transferentzia ArrayListean gorde							
							Transferentzia tranzferentzia_kontu = new Transferentzia(t_kant,t_data,t_kontzeptu,t_jaso);
							transferentziak.add(tranzferentzia_kontu);
						}
						
						//Diru Sarrerak kargatzeko kontsulta egiten dugu
						Statement comand6 = (Statement) conn.createStatement();	
						ResultSet req6 = comand6.executeQuery("select t."+kantitatea+",t."+sarreraData+",t."+igortzaile+",t."+kontzeptua+" from "+diruSarrera +" t join "+kontuBankario+"  k on t."+ibanjasotzaile+"=k."+iban+" where k."+iban+"='"+kont_iban+"';");
						//Emaitzik badaude
						while(req6.next()) {
							double s_kant = req6.getDouble(1);
							String s_data = req6.getString(2);
							String s_igor = req6.getString(3);
							String s_kontzeptu = req6.getString(4);
							//Diru Sarrera ArrayListean gorde
							DiruSarrera diruSarrera_kontu = new DiruSarrera(s_kant,s_data,s_kontzeptu,s_igor);
							diruSarrerak.add(diruSarrera_kontu);
						}
						//Hipoteka kargatzeko kontsulta egiten dugu
						Statement comand7 = (Statement) conn.createStatement();	
						ResultSet req7 = comand7.executeQuery("select h."+kantitatea+",h."+ordaindutakoa+",h."+komisioa+",h."+hasieraData+",h."+amaieraData+",h."+egoera+" from "+hipoteka+" h join "+kontuBankario+"  k on h."+iban+"=k."+iban+" where k."+iban+"='"+kont_iban+"';");
						//Emaitzik badaude
						while(req7.next()) {
							double h_kant = req7.getDouble(1);
							double h_ordaindu = req7.getDouble(2);
							double h_komi = req7.getDouble(3);
							String h_hasiera = req7.getString(4);
							String h_amaiera = req7.getString(5);
							String h_egoera = req7.getString(6);
							//Datuak hipotekan gorde
							hipoteka_kontu = new Hipoteka(h_kant,h_ordaindu,h_komi,h_hasiera,h_amaiera,h_egoera);
						}
						//Txartelak kargatzeko kontsulta egiten dugu
						Statement comand8 = (Statement) conn.createStatement();	
						ResultSet req8 = comand8.executeQuery("select tx."+segurtasunKodea+", tx."+mota+",tx."+id_Txartela+" from "+txartela+" tx join "+kudeatu+" ku on tx."+id_Txartela+"=ku."+id_Txartela+"  where ku."+iban+"='"+kont_iban+"';");
						//Emaitzik badaude
						while(req8.next()) {
							String tx_kod = req8.getString(1);
							String tx_mota = req8.getString(2);
							String tx_id = req8.getString(3);
							//Txartelan dagoen Bezeroa sortu
							Bezeroa tx_bezero = null;
							
							//Txartelak kargatzeko kontsulta egiten dugu
							Statement comand9 = (Statement) conn.createStatement();	
							ResultSet req9 = comand9.executeQuery("select b."+nan+",b."+izena+",b."+abizenak+",b."+jaiotzeData+",b."+sexua+",b."+telefonoa+",b."+pasahitza+" from "+bezeroa+" b join "+kudeatu+" ku on b."+nan+"=ku."+nan+" where ku."+id_Txartela+"='"+tx_id+"';");
							//Emaitzik badaude
							while(req9.next()) {
								String bez_nan = req9.getString(1);
								String bez_izen = req9.getString(2);
								String bez_abiz = req9.getString(3);
								String bez_data = req9.getString(4);
								String[] bez_data_a = bez_data.split("-");
								String bezero_data = bez_data_a[1]+"-"+bez_data_a[2]+"-"+bez_data_a[0];
								String bez_sexu = req9.getString(5);
								String bez_tel = req9.getString(6);
								String bez_pass = req9.getString(7);
								//Datuak Baezeroan gorde
								tx_bezero = new Bezeroa(bez_nan,bez_izen,bez_abiz,bezero_data,bez_sexu,bez_tel,bez_pass);								
							}							
							//Datuak ArrayListean gorde
							Txartela txartel_kontu = new Txartela(tx_kod,tx_mota,tx_bezero);
							txartelak.add(txartel_kontu);
						}						
						//Datuak ArrayListean gorde
						KontuBankario kontuBankario = new KontuBankario(kont_iban,kont_saldo,kont_limit,kont_data,kont_egoera,diruSarrerak,transferentziak,hipoteka_kontu,txartelak);
						kontubankarioak.add(kontuBankario);
					}					
					//Datuak ArrayListean gorde
					Sukurtsala sukurtsal_lang = new Sukurtsala(suk_id,suk_kod,suk_kok,entitate_lang,kontubankarioak);
					sukurtsalak.add(sukurtsal_lang);
				}				
				//Lanpostuaren arabera Zuzendari edo Gerente bat sortzen du
				if(lang_lanpostu.equals("gerentea")) {
					langilea = new Gerentea(lang_nan,lang_izen,lang_abiz,langile_data,lang_sex,lang_tel,lang_pass,lang_lanpostu,sukurtsalak);
				}else if(lang_lanpostu.equals("zuzendaria")) {
					langilea = new Zuzendaria(lang_nan,lang_izen,lang_abiz,langile_data,lang_sex,lang_tel,lang_pass,lang_lanpostu,sukurtsalak);
				}else if(lang_lanpostu.equals("god")) {
					langilea = new God(lang_nan,lang_izen,lang_abiz,langile_data,lang_sex,lang_tel,lang_pass,lang_lanpostu,sukurtsalak);
					}
			}
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}
		
		return langilea;
	}
	
	/**
	 * Datu basean dauden entitate bankarioak kargatzen du botoiak sortzeko
	 * @return Entitate Bankario ArrayList bat entitate guztiekin
	 */
	public ArrayList<EntitateBankario> botoiakSortu(){
		ArrayList<EntitateBankario> entitateak = new ArrayList<>();		
		
		Connection conn;					
		try {			
			//Datu baseari konexioa eta Entitateak kargatzeko kontsulta egiten dugu
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();	
			ResultSet req = comand.executeQuery("select "+id_entitate+","+izena+","+entitateZenbaki+","+urlImg+","+bounds+" from "+entitatebankario+";");
			//Emaitzik badaude
			while(req.next()) {
				String ent_id = req.getString(1);
				String ent_izen = req.getString(2);
				String ent_zbk = req.getString(3);
				String ent_url = req.getString(4);
				String ent_bound = req.getString(5);
				//Datuak Entitate Bankarioan gorde
				EntitateBankario entitate_kontu = new EntitateBankario(ent_izen,ent_id,ent_zbk,ent_bound,ent_url);
				entitateak.add(entitate_kontu);
			}
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
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
	
	public String[] langileKontuInfo(Langilea langilea, String iban, String sukurtsal_izen) {
		String[] informazio = new String[5];
		informazio[1]="";
		for(int i=0;i<langilea.getSukurtsalak().size();i++) {
			if(langilea.getSukurtsalak().get(i).getKokalekua().equals(sukurtsal_izen)) {
				//Kontu bankarioak arakatu
				for(int j=0;j<langilea.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
					if(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban().equals(iban)) {
						informazio[0]= iban.substring(0,4)+" "+iban.substring(4,8)+" "+iban.substring(8,12)+" "+" "+iban.substring(12,16)+" "+" "+iban.substring(16,20)+" "+" "+iban.substring(20);
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
	
	public boolean langileKontuAldaketak(String kontuEgoera, String limite, String kontu_iban) {
		boolean aldatuta = false;
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Bezeroa logeatzeko kontsulta egiten dugu
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand = (Statement) conn.createStatement();	
			comand.executeUpdate("Update "+kontuBankario+" set "+egoera+"= '"+kontuEgoera+"', "+hilekoLimitea+"= "+limite+" where "+iban+"= '"+kontu_iban+"';");
			aldatuta = true;
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}		
		return aldatuta;
	}
	
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
						String iban_jaso = jasotzaile_iban.substring(0,4)+" "+jasotzaile_iban.substring(4,8)+" "+jasotzaile_iban.substring(8,12)+" "+" "+jasotzaile_iban.substring(12,16)+" "+" "+jasotzaile_iban.substring(16,20)+" "+" "+jasotzaile_iban.substring(20);
						transfer_prob[transfer_info.length][2] = iban_jaso;
						transfer_prob[transfer_info.length][3] = langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getTransferentziak().get(k).getKotzeptua();
						transfer_prob[transfer_info.length][4] = df.format(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getTransferentziak().get(k).getKomisioa())+" %";
						transfer_info=transfer_prob;
					}
				}
			}
		}		
		return transfer_info;
	}
	
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
						String iban_ixteko = iban.substring(0,4)+" "+iban.substring(4,8)+" "+iban.substring(8,12)+" "+" "+iban.substring(12,16)+" "+" "+iban.substring(16,20)+" "+" "+iban.substring(20);
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
	
	public boolean bezeroSortu(String izena, String abizena, String nan, String pass, String urte, String hil, String egun, String genero, String tel){
		boolean erregistratuta = false;
		String data = urte+"-"+hil+"-"+egun;
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Bezeroa logeatzeko kontsulta egiten dugu
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand = (Statement) conn.createStatement();	
			comand.executeUpdate("insert into "+bezeroa+" values ('"+nan.toUpperCase()+"','"+izena+"','"+abizena+"','"+data+"','"+genero+"','"+tel+"','"+pass+"');");			
			erregistratuta=true;
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}
		return erregistratuta;
	}	
}
