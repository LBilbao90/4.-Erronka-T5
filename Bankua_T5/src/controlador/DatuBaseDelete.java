package controlador;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class DatuBaseDelete {
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
	 * Kontu bankarioa datu basetik kendu
	 * @param iban_itxi Zein kontu bankario itxiko da
	 * @param nan_lang Langilearen NAN
 	 * @param pass_lang langilearen pasahitza
	 * @return kontua itxi bada <b>true</b>, bestela <b> false </b>
	 */
	public boolean kontuItxiDelete(String iban_itxi,String nan_lang,String pass_lang) {
		boolean ezabatuta = false;
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Kontu Bankarioa ezabatzeko kontsulta
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand = (Statement) conn.createStatement();	
			comand.executeUpdate("Delete from "+kontuBankario+" where "+iban+"='"+iban_itxi+"';");
			ezabatuta = true;
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}
		
		return ezabatuta;
	}

	/**
	 * Erabiltzailea ezabatu datu basetik
	 * @param nan_bez Ezabatzeko erabiltzailearen NAN
	 * @return erabiltzailea ezabatu bada <b>true</b>, bestela <b> false </b>
	 */
	public boolean erabiltzaileEzabatu(String nan_bez) throws SQLException{
		boolean ezabatuta = false;
		
		Connection conn;	
			//Datu baseari konexioa eta Kontu Bankarioa ezabatzeko kontsulta
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand = (Statement) conn.createStatement();	
			comand.executeUpdate("Delete from "+bezeroa+" where "+nan+"='"+nan_bez+"';");
			ezabatuta = true;
			conn.close();
		
		return ezabatuta;
	}
	
	/**
	 * Langilea ezabatu datu basetik
	 * @param nan_lang Kaleratzeko langilearen NAN
	 * @return langilea kaleratu bada <b>true</b>, bestela <b> false </b>
	 */
	public boolean langileKaleratu(String nan_lang) {
		boolean kaleratuta = false;
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Kontu Bankarioa ezabatzeko kontsulta
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand = (Statement) conn.createStatement();	
			comand.executeUpdate("Delete from "+langile+" where "+nan+"='"+nan_lang+"';");
			kaleratuta = true;
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}				
		return kaleratuta;
	}
}