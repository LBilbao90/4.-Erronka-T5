package controlador;

import java.io.PrintWriter;
import java.math.BigDecimal;
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

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.Bezeroa;
import model.EntitateBankario;
import model.Gerentea;
import model.God;
import model.KontuBankario;
import model.Langilea;
import model.SalbuespenaLogin;
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
	final String ibanJasotzaile = "ibanJasotzaile";
	
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
	final String epeMuga = "epeMuga";
	
	// Txartela
	final String txartela = "txartela";
	final String id_txartela = "id_txartela";
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
	public boolean bezeroLogin(String nan_bez, String pas_bez) throws SalbuespenaLogin{
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
		if(!aurkituta) {
			throw new SalbuespenaLogin("Logina okerra da.");
		}
		
		return aurkituta;
	}
	
	/**
	 * Langilearen logina zuzena edo okerra den esaten du eta zer motatakoa
	 * @param nan_lang Langilearen NAN
	 * @param pas_lang Langilearen pasahitza
	 * @return <b>god</b> langilea GOD erabiltzailea erabiltzean, <b>zuzendaria</b> langilea Zuzendaria erabiltzailea erabiltzean, <b>gerentea</b> langilea Gerentea erabiltzailea erabiltzean eta <b>null</b> logina okerra bada.
	 */
	public String langileLogin(String nan_lang, String pas_lang) throws SalbuespenaLogin {
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
		if(login==null) {
			throw new SalbuespenaLogin("Logina okerra da.");
		}
				
		return login;
	}
	
	public void loginErregistratu(String nan, String mota) {
		File file = new File("src/logs/loginLogs.txt");
		
			try {
				FileWriter fw = new FileWriter(file,true);
		    	BufferedWriter bw = new BufferedWriter(fw);
		    	PrintWriter pw = new PrintWriter(bw);

	    	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    	   LocalDateTime eguna = LocalDateTime.now();
		    	
		    	pw.println(mota+"-> Nan: "+nan.toUpperCase()+" - Data: "+dtf.format(eguna));
				
		    	pw.close();
			
			} catch (IOException e1) {
				e1.printStackTrace();
			}			
	}
	
	public void loginOkerraErregistratu(String nan, String mota) {
		File file = new File("src/logs/errorLoginLogs.txt");
		
		try {
			FileWriter fw = new FileWriter(file,true);
	    	BufferedWriter bw = new BufferedWriter(fw);
	    	PrintWriter pw = new PrintWriter(bw);

    	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	   LocalDateTime eguna = LocalDateTime.now();
	    	
	    	pw.println(mota+"-> Nan: "+nan.toUpperCase()+" - Data: "+dtf.format(eguna));
			
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
				ResultSet req2 = comand2.executeQuery("select "+segurtasunKodea+","+mota+",t."+id_txartela+" from "+txartela+" t join "+kudeatu+" ku on t."+id_txartela+"=ku."+id_txartela+" where "+nan+"='"+nan_bezero+"';");
				//Emaitzik badaude
				while(req2.next()) {
					String segur_kode = req2.getString(1);
					String txartel_mota = req2.getString(2);
					String id_txartel_bez = req2.getString(3);
					
					KontuBankario kontuBank = null;
					//Kontu Bankarioak kargatzeko kontsulta egiten dugu
					Statement comand3 = (Statement) conn.createStatement();	
					ResultSet req3 = comand3.executeQuery("select k."+iban+",k."+saldoa+",k."+hilekoLimitea+",k."+sorreraData+",k."+egoera+" from "+kontuBankario+" k join "+kudeatu+" ku on k."+iban+"=ku."+iban+" where ku."+id_txartela+"='"+id_txartel_bez+"';");
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
						ResultSet req5 = comand5.executeQuery("select t."+kantitatea+",t."+sarreraData+",t."+igortzaile+",t."+kontzeptua+" from "+diruSarrera +" t join "+kontuBankario+"  k on t."+ibanJasotzaile+"=k."+iban+" where k."+iban+"='"+iban_bezero+"';");
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
						ResultSet req6 = comand6.executeQuery("select h."+kantitatea+",h."+ordaindutakoa+",h."+komisioa+",h."+hasieraData+",h."+amaieraData+",h."+egoera+",h."+epeMuga+" from "+hipoteka+" h join "+kontuBankario+"  k on h."+iban+"=k."+iban+" where k."+iban+"='"+iban_bezero+"';");
						//Emaitzik badaude
						while(req6.next()) {
							double h_kant = req6.getDouble(1);
							double h_ordaindu = req6.getDouble(2);
							double h_komi = req6.getDouble(3);
							String h_hasiera = req6.getString(4);
							String h_amaiera = req6.getString(5);
							String h_egoera = req6.getString(6);
							String h_epe = req6.getString(7);
							//Datuak hipotekan gorde
							hipoteka_kontu = new Hipoteka(h_kant,h_ordaindu,h_komi,h_hasiera,h_amaiera,h_egoera,h_epe);
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
						ResultSet req6 = comand6.executeQuery("select t."+kantitatea+",t."+sarreraData+",t."+igortzaile+",t."+kontzeptua+" from "+diruSarrera +" t join "+kontuBankario+"  k on t."+ibanJasotzaile+"=k."+iban+" where k."+iban+"='"+kont_iban+"';");
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
						ResultSet req7 = comand7.executeQuery("select h."+kantitatea+",h."+ordaindutakoa+",h."+komisioa+",h."+hasieraData+",h."+amaieraData+",h."+egoera+",h."+epeMuga+" from "+hipoteka+" h join "+kontuBankario+"  k on h."+iban+"=k."+iban+" where k."+iban+"='"+kont_iban+"';");
						//Emaitzik badaude
						while(req7.next()) {
							double h_kant = req7.getDouble(1);
							double h_ordaindu = req7.getDouble(2);
							double h_komi = req7.getDouble(3);
							String h_hasiera = req7.getString(4);
							String h_amaiera = req7.getString(5);
							String h_egoera = req7.getString(6);
							String h_epe = req7.getString(7);
							//Datuak hipotekan gorde
							hipoteka_kontu = new Hipoteka(h_kant,h_ordaindu,h_komi,h_hasiera,h_amaiera,h_egoera,h_epe);
						}
						//Txartelak kargatzeko kontsulta egiten dugu
						Statement comand8 = (Statement) conn.createStatement();	
						ResultSet req8 = comand8.executeQuery("select tx."+segurtasunKodea+", tx."+mota+",tx."+id_txartela+" from "+txartela+" tx join "+kudeatu+" ku on tx."+id_txartela+"=ku."+id_txartela+"  where ku."+iban+"='"+kont_iban+"';");
						//Emaitzik badaude
						while(req8.next()) {
							String tx_kod = req8.getString(1);
							String tx_mota = req8.getString(2);
							String tx_id = req8.getString(3);
							//Txartelan dagoen Bezeroa sortu
							Bezeroa tx_bezero = null;
							
							//Txartelak kargatzeko kontsulta egiten dugu
							Statement comand9 = (Statement) conn.createStatement();	
							ResultSet req9 = comand9.executeQuery("select b."+nan+",b."+izena+",b."+abizenak+",b."+jaiotzeData+",b."+sexua+",b."+telefonoa+",b."+pasahitza+" from "+bezeroa+" b join "+kudeatu+" ku on b."+nan+"=ku."+nan+" where ku."+id_txartela+"='"+tx_id+"';");
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
	
	public boolean langileKontuAldaketak(String kontuEgoera, String limite, String kontu_iban) {
		boolean aldatuta = false;
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Bezeroa logeatzeko kontsulta egiten dugu
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand = (Statement) conn.createStatement();	
			comand.executeUpdate("Update "+kontuBankario+" set "+egoera+"= '"+kontuEgoera+"', "+hilekoLimitea+"= '"+limite+"' where "+iban+"= '"+kontu_iban+"';");
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
						String iban_jaso = jasotzaile_iban.substring(0,4)+" "+jasotzaile_iban.substring(4,8)+" "+jasotzaile_iban.substring(8,12)+" "+" "+jasotzaile_iban.substring(12,14)+" "+" "+jasotzaile_iban.substring(14);
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
	
	public boolean bezeroSortu(String izena, String abizena, String nan, String pass, String urte, String hil, String egun, String genero, String tel){
		boolean erregistratuta = false;
		String data = urte+"-"+hil+"-"+egun;
		izena = izena.substring(0,1).toUpperCase() + izena.substring(1);
		abizena = abizena.substring(0,1).toUpperCase() + abizena.substring(1);
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
						prob[eskatutak.length][2]= String.valueOf(df.format(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getKomisioa()))+" %";
						prob[eskatutak.length][3]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEpeMuga();
						prob[eskatutak.length][4]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEgoera();
						eskatutak=prob;
					}
				}
			}
		}		
		return eskatutak;
	}
	
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
						prob[onartutak.length][3]= String.valueOf(df.format(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getKomisioa()))+" %";
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
						prob[errefusatutak.length][2]= String.valueOf(df.format(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getKomisioa()))+" %";
						prob[errefusatutak.length][3]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEpeMuga();
						prob[errefusatutak.length][4]= langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEgoera();
						errefusatutak=prob;
					}
				}
			}
		}		
		return errefusatutak;
	}

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
						prob[itxitak.length][3]= String.valueOf(df.format(langilea.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getKomisioa()))+" %";
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
	
	public String[][] diruSarrerakIkusi(Bezeroa bezeroa,String iban) {
		String[][] diruSarrerak = new String [0][4];
		
		for(int i = 0; i < bezeroa.getTxartelak().size(); i ++) {
			if(bezeroa.getTxartelak().get(i).getKontuBankario().getIban().equals(iban)) {
				for(int j = 0; j < bezeroa.getTxartelak().get(i).getKontuBankario().getDiruSarrerak().size(); j ++) {
					String[][] diruSarrerak_prob = new String[diruSarrerak.length+1][4];
					for(int k = 0; k < diruSarrerak.length; k ++) {
						for(int l = 0; l < diruSarrerak[l].length; l ++) {
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
	
	public boolean transferentziaIbanBalidatu(String ibanJasotzaile) {
		boolean aurkituta = false;
		
		Connection conn;					
		try {
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand = (Statement) conn.createStatement();	
			ResultSet req = comand.executeQuery("Select COUNT(*) as kant from kontubankario where "+iban+"= '"+ibanJasotzaile+"';");
			
			if(req.next()) {
				if(req.getInt("kant") == 1) {
					aurkituta = true;
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
	
	public boolean transferentziaSaldoaBalidatu(String saldo, String kontua) {
		boolean zuzena = false;
		saldo = saldo.replace(',','.');
		Connection conn;					
		try {
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand = (Statement) conn.createStatement();	
			ResultSet req = comand.executeQuery("Select "+saldoa+" from kontubankario where "+iban+"='"+kontua+"';");
			
			if(req.next()) {
				if(req.getDouble(saldoa) >= Double.parseDouble(saldo)) {
					zuzena = true;
				}
			}
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}	
		
		return zuzena;
	}
	
	public boolean segurtasunKodeaBalidatu(String kodea, String kontua) {
		boolean zuzena = false;
		Connection conn;					
		try {
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand = (Statement) conn.createStatement();	
			ResultSet req = comand.executeQuery("Select COUNT(*) as kant from kontubankario kon join kudeatu ku on kon."+ iban +"= ku."+ iban +" join txartela t on ku."+ id_txartela+"= t."+ id_txartela +" where ku."+iban+"='"+kontua+"' and "+ segurtasunKodea +"='"+ kodea +"';");
			
			if(req.next()) {
				if(req.getInt("kant") == 1) {
					zuzena = true;
				}
			}
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}	
		
		return zuzena;
	}
	
	public boolean hipotekaDut(Bezeroa bezeroa, String iban) {
		boolean zuzena = false;

		for(int i = 0; i < bezeroa.getTxartelak().size(); i ++) {
			if(bezeroa.getTxartelak().get(i).getKontuBankario().getIban().equals(iban)) {
				if(bezeroa.getTxartelak().get(i).getKontuBankario().getHipoteka() != null) {
					if(bezeroa.getTxartelak().get(i).getKontuBankario().getHipoteka().getEgoera().equals("eskatuta") || bezeroa.getTxartelak().get(i).getKontuBankario().getHipoteka().getEgoera().equals("onartuta")) {
						zuzena = true;
					}
				}
			}
		}
		
		return zuzena;
	}

	public static String sortuTxartelId() {
		String id = "";
		
		for(int i=0;i<16;i++) {
			id += String.valueOf((int) Math.floor(Math.random() *(9 - 0 + 1) + 0));			
		}
		return id;
	}

	public boolean txartelIdBalidatu(String txartelId) {
		boolean libre=true;
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Langilea logeatzeko kontsulta egiten dugu
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();	
			ResultSet req = comand.executeQuery("select "+id_txartela+" from "+txartela+" where "+id_txartela+"='"+txartelId+"';");
			//Daturik aurkitzen badu
			if(req.next()) {
				libre = false;	
			}
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}
		
		return libre;
	}

	public boolean nanBalidatu(String nan_balidatzeko) {
		boolean balidoa = false;
		String[] letrak = {"T","R","W","A","G","M","Y","F","P","D","X","B","N","J","Z","S","Q","V","H","L","C","K","E"};
		int nan_int = 0;
		try {
			nan_int = Integer.parseInt(nan_balidatzeko.substring(0,8));	
			if(letrak[nan_int%23].equals(nan_balidatzeko.substring(8).toUpperCase())) {
				balidoa=true;
			}else{
				JOptionPane.showMessageDialog(null,"NAN Okerra!","Error!", JOptionPane.ERROR_MESSAGE);			
			}		
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"NAN Okerra!","Error!", JOptionPane.ERROR_MESSAGE);			
		}
		
		return balidoa;
	}

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
	
	public static boolean diruBalidatu(String dirua) {
		boolean balidoa = false;
		try {
			String[] diru_split = dirua.split(",");
			@SuppressWarnings("unused")
			double balidaketa = Double.parseDouble(diru_split[0]);
			if(diru_split.length>1) {
				balidaketa = Double.parseDouble(diru_split[1]);
			}
			if(diru_split.length==1 || (diru_split[1].length()<=2 && diru_split.length<=2)) {
				balidoa = true;				
			}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,"Datu okerra!","Error!", JOptionPane.ERROR_MESSAGE);		
		}
		return balidoa;
	}

	public boolean bezeroExistitu(String nan_bez) {
		boolean existitu = false;
		Connection conn;					
		try {
			//Datu baseari konexioa eta Bezeroa nan kontsulta egiten da
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();	
			ResultSet req = comand.executeQuery("select "+nan+" from "+bezeroa+" where "+nan+"='"+nan_bez+"';");
			//Daturik aurkitzen badu
			if(req.next()) {
				existitu = true;	
			}else {
				JOptionPane.showMessageDialog(null,"Bezeroa ez da existitzen!","Error!", JOptionPane.ERROR_MESSAGE);					
			}
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());		
		}		
		return existitu;
	}

	public boolean kontuKorronteSortu(Langilea langilea, String iban_sortu,String saldoa_sortu,String hilekoLimite_sortu,String egoera_sortu,String sukurtsal_izen,String[][] bezeroak){
		boolean sortuta = false;
		String sukurtsalId_sortu= "";
		for(int i=0;i<langilea.getSukurtsalak().size();i++) {
			if(langilea.getSukurtsalak().get(i).getKokalekua().equals(sukurtsal_izen)) {
				sukurtsalId_sortu = langilea.getSukurtsalak().get(i).getIdSukurtsala();
			}
		}
		saldoa_sortu = saldoa_sortu.replace(',','.');
		Calendar c = Calendar.getInstance();
		String egun,hil, urte;							   
		egun = Integer.toString(c.get(Calendar.DATE));
		hil = Integer.toString(c.get(Calendar.MONTH));
		urte = Integer.toString(c.get(Calendar.YEAR));		
		String data_sortu= urte + "-" + hil +"-" + egun;
		Connection conn;					
		try {
			//Datu baseari konexioa eta Bezeroa nan kontsulta egiten da
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();	
			//Insert Kontu Bankarioan
			comand.executeUpdate("insert into "+kontuBankario+"  ("+iban+","+saldoa+","+hilekoLimitea+","+sorreraData+","+egoera+","+id_sukurtsal+") VALUES ('"+iban_sortu+"',"+saldoa_sortu+","+hilekoLimite_sortu+",'"+data_sortu+"','"+egoera_sortu+"',"+sukurtsalId_sortu+")");
			//Insert Txartelean
			for(int i=0;i<bezeroak.length;i++) {
				comand.executeUpdate("insert into "+txartela+" VALUES ('"+bezeroak[i][1]+"','"+bezeroak[i][2]+"','"+bezeroak[i][3]+"');");
			}
			//Insert Kudeatu
			for(int i=0;i<bezeroak.length;i++) {
				comand.executeUpdate("insert into "+kudeatu+" VALUES ('"+bezeroak[i][0].toUpperCase()+"','"+iban_sortu+"','"+bezeroak[i][1]+"');");
			}
			sortuta = true;
			conn.close();
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null,"Errorea datuak insertatzen!","Error!", JOptionPane.ERROR_MESSAGE);	
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());		
		}		
		
		
		return sortuta;
	}
	
	public Transferentzia transferentziaEgin(String ibanigortzaile, String ibanjasotzaile, String kantitatea_trans, String kontzeptua_trans, String komisioa_trans, String segurtasunKodea_trans, String[][] transferentziak){
		kantitatea_trans = kantitatea_trans.replace(',','.');
		String[] komisioa_prob = komisioa_trans.split("%");
		komisioa_trans = komisioa_prob[0];
		komisioa_trans = komisioa_trans.replace(',','.');
		Calendar c = Calendar.getInstance();
		String egun,hil, urte;							   
		egun = Integer.toString(c.get(Calendar.DATE));
		hil = Integer.toString(c.get(Calendar.MONTH));
		urte = Integer.toString(c.get(Calendar.YEAR));		
		String data_sortu= urte + "-" + hil +"-" + egun;
		
		Transferentzia t1 = new Transferentzia ();
		Connection conn;					
		try {
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();
			comand.executeUpdate("insert into "+transferentzia+" ("+kantitatea+","+transferentziaData+","+jasotzailea+","+kontzeptua+","+ibanIgortzaile+","+komisioa+") values ("+kantitatea_trans+",'"+data_sortu+"','"+ibanjasotzaile+"','"+kontzeptua_trans+"','"+ibanigortzaile+"',"+komisioa_trans+")");
			comand.executeUpdate("insert into "+diruSarrera+" ("+kantitatea+","+sarreraData+","+igortzaile+","+kontzeptua+","+ibanJasotzaile+") values ("+kantitatea_trans+",'"+data_sortu+"','"+ibanigortzaile+"','"+kontzeptua_trans+"','"+ibanjasotzaile+"')");
			comand.executeUpdate("update "+kontuBankario+" set "+saldoa+" = "+saldoa+" - "+kantitatea_trans+" where "+iban+" = '"+ibanigortzaile+"'");
			comand.executeUpdate("update "+kontuBankario+" set "+saldoa+" = "+saldoa+" + "+kantitatea_trans+" where "+iban+" = '"+ibanjasotzaile+"'");
			
			t1.setJasotzailea(ibanjasotzaile);
			t1.setKantitatea(Integer.parseInt(kantitatea_trans));
			t1.setKotzeptua(kontzeptua_trans);
			t1.setTransferentziaData(data_sortu);
			
			conn.close();
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null,"Errorea transferentzia egitean!","Error!", JOptionPane.ERROR_MESSAGE);	
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());		
		}
		return t1;
	}
	
	public void HipotekaEskatu(String kantitatea_hipo, String komisioa_hipo, String iban_hipo, String epemuga_hipo){
		kantitatea_hipo = kantitatea_hipo.replace(',','.');
		String[] komisioa_prob = komisioa_hipo.split("%");
		komisioa_hipo = komisioa_prob[0];
		komisioa_hipo = komisioa_hipo.replace(',','.');
		Calendar c = Calendar.getInstance();
		String egun,hil, urte;							   
		egun = Integer.toString(c.get(Calendar.DATE));
		hil = Integer.toString(c.get(Calendar.MONTH));
		urte = Integer.toString(c.get(Calendar.YEAR));		
		String data_sortu= urte + "-" + hil +"-" + egun;
		Connection conn;					
		try {
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();
			comand.executeUpdate("insert into "+hipoteka+" ( "+kantitatea+","+komisioa+","+hasieraData+","+iban+","+epeMuga+") values ( "+kantitatea_hipo+","+komisioa_hipo+",'"+data_sortu+"','"+iban_hipo+"','"+epemuga_hipo+"')");
			
			conn.close();
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null,"Errorea hipoteka eskatzean!","Error!", JOptionPane.ERROR_MESSAGE);	
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());		
		}
	}
	
	public String[] HipotekaEstatus(String kontua){
		String[] hipoteka_estatus = new String [5];
		Connection conn;					
		try {
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();
			ResultSet req = comand.executeQuery("select "+kantitatea+","+hasieraData+","+amaieraData+","+komisioa+","+ordaindutakoa+" from "+hipoteka+" where "+iban+" = '"+kontua+"'");
			
			if(req.next()) {
				hipoteka_estatus[0] = req.getString(1);
				hipoteka_estatus[1] = req.getString(2);
				if(req.getString(3) == null) {
					hipoteka_estatus[2] = "-----";
				}else {
					hipoteka_estatus[2] = req.getString(3);
				}
				hipoteka_estatus[3] = req.getString(4);
				hipoteka_estatus[4] = req.getString(5);
			}
			
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());		
		}
		return hipoteka_estatus;
	}
	
	public boolean transferentziakImprimatu(String[][] transferentziak){
		boolean zuzena = true;
    	Calendar cal = Calendar.getInstance();
		cal.set(Calendar.AM_PM, Calendar.AM);
    	String cal_s = cal.get(Calendar.YEAR)+"_"+cal.get(Calendar.MONTH)+"_"+cal.get(Calendar.DAY_OF_MONTH)+"_"+cal.get(Calendar.HOUR)+"_"+cal.get(Calendar.MINUTE);
		File file = new File("src/movimientos/Transferentziak/"+cal_s+".txt");
		BufferedWriter fichero;
		
		try {
	    	fichero = new BufferedWriter(new FileWriter(file));
	    	fichero.write("TRANSFERENTZIAK: \n");
	    	
	    	for(int i = 0; i < transferentziak.length; i ++) {
	    		fichero.write("IBAN: "+transferentziak[i][0]+" ,Kantitatea: "+transferentziak[i][1]+" ,Kontzeptua"+transferentziak[i][2]+" ,Transferetzia-data: "+ transferentziak[i][3] +"\n");
	    	}
	    	
	    	fichero.close();
		
		} catch (IOException e1) {
			zuzena = false;
			e1.printStackTrace();
		}
		return zuzena;
	}
	
	public boolean diruSarrerakImprimatu(String[][] diruSarrerak){
		boolean zuzena = true;
    	Calendar cal = Calendar.getInstance();
		cal.set(Calendar.AM_PM, Calendar.AM);
    	String cal_s = cal.get(Calendar.YEAR)+"_"+cal.get(Calendar.MONTH)+"_"+cal.get(Calendar.DAY_OF_MONTH)+"_"+cal.get(Calendar.HOUR)+"_"+cal.get(Calendar.MINUTE);
		File file = new File("src/movimientos/DiruSarrerak/"+cal_s+".txt");
		BufferedWriter fichero;
		
		try {
	    	fichero = new BufferedWriter(new FileWriter(file));
	    	fichero.write("DIRU SARRERAK: \n");
	    	
	    	for(int i = 0; i < diruSarrerak.length; i ++) {
	    		fichero.write("IBAN igortzailea: "+diruSarrerak[i][0]+" ,Kantitatea: "+diruSarrerak[i][1]+" ,Kontzeptua"+diruSarrerak[i][2]+" ,Sarrera-data: "+ diruSarrerak[i][3] +"\n");
	    	}
	    	
	    	fichero.close();
		
		} catch (IOException e1) {
			zuzena = false;
			e1.printStackTrace();
		}
		return zuzena;
	}
	
	public boolean mugimentuakImprimatu(String[][] diruSarrerak, String[][] transferentziak){
		boolean zuzena = true;
    	Calendar cal = Calendar.getInstance();
		cal.set(Calendar.AM_PM, Calendar.AM);
    	String cal_s = cal.get(Calendar.YEAR)+"_"+cal.get(Calendar.MONTH)+"_"+cal.get(Calendar.DAY_OF_MONTH)+"_"+cal.get(Calendar.HOUR)+"_"+cal.get(Calendar.MINUTE);
		File file = new File("src/movimientos/Mugimenduak/"+cal_s+".txt");
		BufferedWriter fichero;
		
		try {
			fichero = new BufferedWriter(new FileWriter(file));
	    	fichero.write("TRANSFERENTZIAK: \n");
	    	
	    	for(int i = 0; i < transferentziak.length; i ++) {
	    		fichero.write("IBAN: "+transferentziak[i][0]+" ,Kantitatea: "+transferentziak[i][1]+" ,Kontzeptua"+transferentziak[i][2]+" ,Transferetzia-data: "+ transferentziak[i][3] +"\n");
	    	}
	    	
	    	fichero.write("DIRU SARRERAK: \n");
	    	
	    	for(int i = 0; i < diruSarrerak.length; i ++) {
	    		fichero.write("IBAN igortzailea: "+diruSarrerak[i][0]+" ,Kantitatea: "+diruSarrerak[i][1]+" ,Kontzeptua"+diruSarrerak[i][2]+" ,Sarrera-data: "+ diruSarrerak[i][3] +"\n");
	    	}
	    	
	    	fichero.close();
		
		} catch (IOException e1) {
			zuzena = false;
			e1.printStackTrace();
		}
		return zuzena;
	}

	public String[][] bezeroZerrendaKargatu(){
		String[][] bezeroak = new String[0][8];		
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Bezeroa logeatzeko kontsulta egiten dugu
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();	
			ResultSet req = comand.executeQuery("select "+nan+","+izena+","+abizenak+","+jaiotzeData+","+sexua+","+telefonoa+","+pasahitza+","+egoera+" from "+bezeroa);
			
			while(req.next()) {
				String[][] bezero_prob = new String[bezeroak.length+1][8];
				for(int i=0;i<bezeroak.length;i++) {
					for(int j=0;j<bezeroak[i].length;j++) {
						bezero_prob[i][j]=bezeroak[i][j];
					}
				}
				bezero_prob[bezeroak.length][0]=req.getString(1);
				bezero_prob[bezeroak.length][1]=req.getString(2);
				bezero_prob[bezeroak.length][2]=req.getString(3);
				bezero_prob[bezeroak.length][3]=req.getString(4);
				bezero_prob[bezeroak.length][4]=req.getString(5);
				bezero_prob[bezeroak.length][5]=req.getString(6);
				bezero_prob[bezeroak.length][6]=req.getString(7);
				bezero_prob[bezeroak.length][7]=req.getString(8);
				bezeroak=bezero_prob;
			}
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}		
		return bezeroak;
	}

	public String[][] langileZerrendaKargatu(){
		String[][] langileak = new String[0][9];		
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Bezeroa logeatzeko kontsulta egiten dugu
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();	
			ResultSet req = comand.executeQuery("select "+nan+","+izena+","+abizenak+","+jaiotzeData+","+sexua+","+telefonoa+","+pasahitza+","+lanpostua+","+id_sukurtsal+" from "+langile);
			
			while(req.next()) {
				String[][] langile_prob = new String[langileak.length+1][9];
				for(int i=0;i<langileak.length;i++) {
					for(int j=0;j<langileak[i].length;j++) {
						langile_prob[i][j]=langileak[i][j];
					}
				}
				langile_prob[langileak.length][0]=req.getString(1);
				langile_prob[langileak.length][1]=req.getString(2);
				langile_prob[langileak.length][2]=req.getString(3);
				langile_prob[langileak.length][3]=req.getString(4);
				langile_prob[langileak.length][4]=req.getString(5);
				langile_prob[langileak.length][5]=req.getString(6);
				langile_prob[langileak.length][6]=req.getString(7);
				langile_prob[langileak.length][7]=req.getString(8);
				langile_prob[langileak.length][8]=req.getString(9);
				langileak=langile_prob;
			}
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}		
		return langileak;
	}

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

	public boolean bezeroAldaketakUpdate(String nan_bez,String izen_bez,String abizen_bez,String sexu_bez, String tel_bez,String pass_bez, String egoera_bez) {
		boolean gordeta = false;
		
		return gordeta;
	}
}
