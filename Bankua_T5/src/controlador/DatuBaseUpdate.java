package controlador;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.Bezeroa;

public class DatuBaseUpdate {
	final String url = "jdbc:mysql://10.5.14.109:3306/bankua";
	final String urlServer = "jdbc:mysql://10.5.14.109:3306/bankua";
	final String erabiltzaile = "L12345678Z";
	final String erabiltzaileServer= "L12345678Z";
	final String password="1234";
	final String passwordServer= "1234";  
	
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
	

	/**
	 * Hipotekaren ordainketa datubasean eguneratzen du
	 * @param bezero Zein bezeroren hipoteka
	 * @param iban_bez Zein kontu bankarioan ordaindu den
	 * @param pass_bezero bezeroaren pasahitza
	 * @return
	 */
	public boolean hipotekaUpdate(Bezeroa bezero, String iban_bez, String pass_bezero) {
		boolean aldatuta = false;
		String kant = "";
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Bezeroa logeatzeko kontsulta egiten dugu
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand = (Statement) conn.createStatement();
			ResultSet req = comand.executeQuery("Select "+kantitatea+" from "+hipoteka+" where iban = '"+iban_bez+"';");
			
			while(req.next()) {
				kant = req.getString(1);
			}
			
			for(int i=0;i<bezero.getTxartelak().size();i++) {
				if(bezero.getTxartelak().get(i).getKontuBankario().getIban().equals(iban_bez)) {
					if(bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getOrdaindutakoa() == Integer.parseInt(kant)) {
						comand.executeUpdate("Update "+hipoteka+" set "+ordaindutakoa+"= '"+bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getOrdaindutakoa()+"', "+amaieraData+"= '"+bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getAmaieraData()+"',"+egoera+"='"+bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getEgoera()+"' where "+iban+"='"+iban_bez+"';");
					}else {
						comand.executeUpdate("Update "+hipoteka+" set "+ordaindutakoa+"= '"+bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getOrdaindutakoa()+"', "+egoera+"='"+bezero.getTxartelak().get(i).getKontuBankario().getHipoteka().getEgoera()+"' where "+iban+"='"+iban_bez+"';");						
					}
					comand.executeUpdate("Update "+kontuBankario+" set "+saldoa+"= "+bezero.getTxartelak().get(i).getKontuBankario().getSaldoa()+" where "+iban+"='"+iban_bez+"';");
					aldatuta = true;
				}
			}
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}			
		return aldatuta;
	}

	/**
	 * Datu basean eguneratzen ditu bezeroaren datuak
	 * @param nan_bez Zein bezeroaren datuak eguneratuko dira
	 * @param izen_bez Eguneratzeko izena
	 * @param abizen_bez Eguneratzeko abizena
	 * @param sexu_bez Eguneratzeko sexua
	 * @param tel_bez Eguneratzeko telefonoa
	 * @param pass_bez Eguneratzeko pasahitza
	 * @param egoera_bez Eguneratzeko egoera
	 * @return Eguneraketa ondo egin bada true, bestela false
	 */
	public boolean bezeroAldaketakUpdate(String nan_bez,String izen_bez,String abizen_bez,String sexu_bez, String tel_bez,String pass_bez, String egoera_bez) {
		boolean aldatuta = false;
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Bezeroa logeatzeko kontsulta egiten dugu
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand = (Statement) conn.createStatement();	
			comand.executeUpdate("Update "+bezeroa+" set "+izena+"= '"+izen_bez+"', "+abizenak+"= '"+abizen_bez+"',"+sexua+"='"+sexu_bez+"',"+telefonoa+"='"+tel_bez+"',"+pasahitza+"='"+pass_bez+"',"+egoera+"='"+egoera_bez+"' where "+nan+"='"+nan_bez+"';");
			aldatuta = true;
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}			
		return aldatuta;
	}
	
	/**
	 * datu basean kontu bankario baten egoera hileko limitea aldatzen ditu.
	 * @param kontuEgoera aldatu nahi dugun egoerara
	 * @param limite aldatu nahi dugun hileko limitea
	 * @param kontu_iban Zein kontu bankarioren informazioa aldatu nahi dugu
	 * @param nan_langile langilearen NAN
	 * @param pass_langile langilearen pasahitza
	 * @return Updatea egiten bada True, ez bada egiten (errorea) false
	 */
	public boolean langileKontuAldaketak(String kontuEgoera, String limite, String kontu_iban, String nan_langile, String pass_langile) {
		boolean aldatuta = false;
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Kontu Bankarioa eguneratzen du
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

	/**
	 * ixteko egoera datubasean aldatzeko
	 * @param iban_bez Zein kontu bankario aldatuko den
	 * @return Aldaketa egin bada <b>true</b>, bestela <b>false</b>
	 */
	public boolean ixtekoKontuUpdate(String iban_bez) {
		boolean aldatuta = false;
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Bezeroa logeatzeko kontsulta egiten dugu
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand = (Statement) conn.createStatement();	
			comand.executeUpdate("Update "+kontuBankario+" set "+egoera+"='"+"ixteko"+"' where "+iban+"='"+iban_bez+"';");
			aldatuta = true;
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}			
		return aldatuta;
	}

	/**
	 * Datu basean eguneratzen ditu langilearen datuak
	 * @param nan_lang Zein langileren datuak eguneratuko dira
	 * @param izen_lang Eguneratzeko izena
	 * @param abizen_lang Eguneratzeko abizena
	 * @param sexu_lang Eguneratzeko sexua
	 * @param tel_lang Eguneratzeko telefonoa
	 * @param pass_lang Eguneratzeko pasahitza
	 * @param lanpostu_lang Eguneratzeko lanpostua
	 * @param id_sukurtsal_lang Eguneratzeko sukurtsala
	 * @return Eguneraketa ondo egin bada true, bestela false
	 */
	public boolean langileAldaketakUpdate(String nan_lang,String izen_lang,String abizen_lang,String sexu_lang, String tel_lang,String pass_lang, String lanpostu_lang,String id_sukurtsal_lang, String egoera_lang) {
		boolean aldatuta = false;
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Bezeroa logeatzeko kontsulta egiten dugu
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand = (Statement) conn.createStatement();	
			comand.executeUpdate("Update "+langile+" set "+izena+"= '"+izen_lang+"', "+abizenak+"= '"+abizen_lang+"',"+sexua+"='"+sexu_lang+"',"+telefonoa+"='"+tel_lang+"',"+pasahitza+"='"+pass_lang+"',"+lanpostua+"='"+lanpostu_lang+"',"+id_sukurtsal+"='"+id_sukurtsal_lang+"',"+egoera+"='"+egoera_lang+"' where "+nan+"='"+nan_lang+"';");
			aldatuta = true;
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}			
		return aldatuta;
	}
}
