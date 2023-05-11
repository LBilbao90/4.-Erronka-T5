package controlador;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.Langilea;
import model.SalbuespenaErregistro;
import model.SalbuespenaTransferentzia;

public class DatuBaseInsert {
	final String url = "jdbc:mysql://localhost:3306/bankua";
	final String urlServer = "jdbc:mysql://10.5.14.109:3306/bankua";
	final String erabiltzaile = "root";
	final String erabiltzaileServer= "root";
	final String password="";
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
	 * Bezero bat datubasean insertatzen du
	 * @param izena bezeroaren izena
	 * @param abizena bezeroaren abizena
	 * @param nan bezeroaren nana
 	 * @param pass bezeroaren pasahitza
	 * @param urte bezeroaren jaiotze urtea
	 * @param hil bezeroaren jaiotze hila
	 * @param egun bzeroaren jaiotze eguna
	 * @param genero bezeroaren generoa
	 * @param tel bezeroaren telefeonoa
	 * @param nan_langile langilearen NAN
	 * @param pass_langile langilearen pasahitza
	 * @return insert ondo egin bada true, errore ematen badu false
	 * @throws SalbuespenaErregistro datu basea errorea ematen badu botako den salbuespena
	 */
	public boolean bezeroSortu(String izena, String abizena, String nan, String pass, String urte, String hil, String egun, String genero, String tel, String nan_langile, String pass_langile) throws SalbuespenaErregistro{
		boolean erregistratuta = false;
		String data = urte+"-"+hil+"-"+egun;
		izena = izena.substring(0,1).toUpperCase() + izena.substring(1);
		abizena = abizena.substring(0,1).toUpperCase() + abizena.substring(1);
		Connection conn;					
		try {
			//Datu baseari konexioa eta Bezeroa logeatzeko kontsulta egiten dugu
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand = (Statement) conn.createStatement();	
			comand.executeUpdate("insert into "+bezeroa+" values ('"+nan.toUpperCase()+"','"+izena+"','"+abizena+"','"+data+"','"+genero+"','"+tel+"','"+pass+"','aktiboa');");			
			erregistratuta=true;
			conn.close();
		}catch(SQLException ex) {
			throw new SalbuespenaErregistro("Erabiltzailea ezin da erregistratu.");
		}
		return erregistratuta;
	}	

	/**
	 * Kontu korronte bat sortzen du
	 * @param langilea Zein langile sortzen duen
	 * @param iban_sortu Zein IBANarekin sortuko den kontu bankarioa
	 * @param saldoa_sortu zenbat saldorekin sortuko da
	 * @param hilekoLimite_sortu kontuaren hileko limitea
	 * @param egoera_sortu kontuaren egoera
	 * @param sukurtsal_izen kontuaren sukurtsala
	 * @param bezeroak Zein bezeroen den kontua
	 * @param nan_langile langilearen NAN
	 * @param pass_langile langilearen pasahitza
 	 * @return ondo insertatzen bada datubasea true, bestela false
	 */
	public boolean kontuKorronteSortu(Langilea langilea, String iban_sortu,String saldoa_sortu,String hilekoLimite_sortu,String egoera_sortu,String sukurtsal_izen,String[][] bezeroak, String nan_langile, String pass_langile){
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
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
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

	/**
	 * Hipoteka sortzeko eta datubasean gordetzeko
	 * @param kantitatea_hipo hipotekaren kantitatea ordaintzeko
	 * @param komisioa_hipo hipotekaren komisioa
	 * @param iban_hipo Zein kontu bankarioari sortuko da hipoteka
	 * @param epemuga_hipo ordaintzeko epemuga
	 * @param nan_bezero bezeroaren NAN
	 * @param pass_bezero bezeroaren pasahitza
	 */
	public boolean hipotekaEskatu(String kantitatea_hipo, String komisioa_hipo, String iban_hipo, String epemuga_hipo,String nan_bezero, String pass_bezero){
		boolean eskatuta = false;
		kantitatea_hipo = kantitatea_hipo.replace(',','.');
		String[] komisioa_prob = komisioa_hipo.split("%");
		komisioa_hipo = komisioa_prob[0];
		komisioa_hipo = komisioa_hipo.replace(',','.');
		Calendar c = Calendar.getInstance();
		String egun,hil, urte;							   
		egun = Integer.toString(c.get(Calendar.DATE));
		hil = Integer.toString(c.get(Calendar.MONTH)+1);
		urte = Integer.toString(c.get(Calendar.YEAR));		
		String data_sortu= urte + "-" + hil +"-" + egun;
		Connection conn;					
		try {
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand = (Statement) conn.createStatement();
			comand.executeUpdate("insert into "+hipoteka+" ("+kantitatea+","+komisioa+","+hasieraData+","+iban+","+epeMuga+") values ( "+Math.round(Double.parseDouble(kantitatea_hipo) * 100.0) / 100.0+","+komisioa_hipo+",'"+data_sortu+"','"+iban_hipo+"','"+epemuga_hipo+"')");
			eskatuta = true;
			conn.close();
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null,"Errorea hipoteka eskatzean!","Error!", JOptionPane.ERROR_MESSAGE);	
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());		
		}
		return eskatuta;
	}
	
	/**
	 * Transferentzia bat egiten du eta datubasean gordetzen du
	 * @param ibanigortzaile Nork egiten du transferentzia
	 * @param ibanjasotzaile Nork jasotzen du transferentzia
	 * @param kantitatea_trans Zenbat diru bidaltzen da
	 * @param kontzeptua_trans Zein kontzeptuarekin
	 * @param komisioa_trans Zenbat komisioarekin
	 * @param segurtasunKodea_trans Segurtasunkodea transferentziarako
	 * @param transferentziak Trasferentzia gordeko den arraya
	 * @return Transeferentziaren objetua
	 */
	public boolean transferentziaEgin(String ibanigortzaile, String ibanjasotzaile, String kantitatea_trans, String kontzeptua_trans, String komisioa_trans, String segurtasunKodea_trans, String[][] transferentziak) throws SalbuespenaTransferentzia{
		boolean transferituta = false;

		kantitatea_trans=kantitatea_trans.replace(",",".");	
		komisioa_trans=komisioa_trans.replace(",",".");	
		komisioa_trans=komisioa_trans.replace("%","");	
		Calendar c = Calendar.getInstance();
		String egun,hil, urte;							   
		egun = Integer.toString(c.get(Calendar.DATE));
		hil = Integer.toString(c.get(Calendar.MONTH));
		urte = Integer.toString(c.get(Calendar.YEAR));		
		String data_sortu= urte + "-" + hil +"-" + egun;
		
		Connection conn;					
		try {
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand = (Statement) conn.createStatement();
			ResultSet req = comand.executeQuery("select "+egoera+" from "+kontuBankario+" where "+iban+"='"+ibanjasotzaile+"';");
			if(req.next() && req.getString(1).equals("aktiboa")) {
				comand.executeUpdate("insert into "+transferentzia+" ("+kantitatea+","+transferentziaData+","+jasotzailea+","+kontzeptua+","+ibanIgortzaile+","+komisioa+") values ("+kantitatea_trans+",'"+data_sortu+"','"+ibanjasotzaile+"','"+kontzeptua_trans+"','"+ibanigortzaile+"',"+komisioa_trans+");");
				comand.executeUpdate("insert into "+diruSarrera+" ("+kantitatea+","+sarreraData+","+igortzaile+","+kontzeptua+","+ibanJasotzaile+") values ("+kantitatea_trans+",'"+data_sortu+"','"+ibanigortzaile+"','"+kontzeptua_trans+"','"+ibanjasotzaile+"');");
				comand.executeUpdate("update "+kontuBankario+" set "+saldoa+" = "+saldoa+" - "+kantitatea_trans+" where "+iban+" = '"+ibanigortzaile+"';");
				comand.executeUpdate("update "+kontuBankario+" set "+saldoa+" = "+saldoa+" + "+kantitatea_trans+" where "+iban+" = '"+ibanjasotzaile+"';");
				transferituta = true;
			}else {
				throw new SalbuespenaTransferentzia("Jasotzailearen kontua ezin ditu transferentziak jaso.");
			}
			
			conn.close();
		}catch(SQLException ex) {
			JOptionPane.showMessageDialog(null,"Errorea transferentzia egitean!","Error!", JOptionPane.ERROR_MESSAGE);	
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());		
		}
		return transferituta;
	}

	/**
	 * Langile bat erregistratzen du datu basean
	 * @param nan_lang erregistratuko den langilearen NAN
	 * @param izen_lang erregistratuko den langilearen izena
	 * @param abizen_lang erregistratuko den langilearen abizena
	 * @param urte erregistratuko den langilearen urtea
	 * @param hil erregistratuko den langilearen hila
	 * @param egun erregistratuko den langilearen eguna
	 * @param egoera_lang erregistratuko den langilearen egoera
	 * @param sexu_lang erregistratuko den langilearen sexua
	 * @param tel_lang erregistratuko den langilearen telefonoa
	 * @param pass_lang erregistratuko den langilearen pasahitza
	 * @param lanpostu_lang erregistratuko den langilearen lanpostua
	 * @param id_suk erregistratuko den langilearen sukurtsala
	 * @return transferentzia burutu bada <b>true</b>, bestela <b> false </b>
	 */
	public boolean langileErregistratu(String nan_lang, String izen_lang, String abizen_lang, String urte,String hil, String egun, String egoera_lang, String sexu_lang, String tel_lang, String pass_lang, String lanpostu_lang, String id_suk) {
		boolean erregistratuta=false;
		
		Connection conn;					
		try {
			//Datu baseari konexioa eta Kontu Bankarioa ezabatzeko kontsulta
			conn = (Connection) DriverManager.getConnection (url,erabiltzaile,password);
			Statement comand = (Statement) conn.createStatement();	
			comand.executeUpdate("insert into "+langile+" ("+nan+","+izena+","+abizenak+","+jaiotzeData+","+sexua+","+telefonoa+","+pasahitza+","+lanpostua+","+id_sukurtsal+","+egoera+") values ('"+nan_lang+"','"+izen_lang+"','"+abizen_lang+"','"+urte+"-"+hil+"-"+egun+"','"+sexu_lang+"','"+tel_lang+"','"+pass_lang+"','"+lanpostu_lang+"',"+id_suk+",'"+egoera_lang+"');");
			erregistratuta = true;
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}			
		return erregistratuta;
	}
}
