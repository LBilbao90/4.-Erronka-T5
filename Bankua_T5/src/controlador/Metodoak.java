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
			ResultSet req1 = comand1.executeQuery("select "+nan+","+izena+","+abizenak+","+jaiotzeData+","+sexua+","+telefonoa+","+pasahitza+" from "+bezeroa+" WHERE "+nan+"="+nan_bezero+";");
			
			String bez_nan = req1.getString(1);
			String bez_izen = req1.getString(2);
			String bez_abiz = req1.getString(3);
			String bez_data = req1.getString(4);
			String bez_sexu = req1.getString(5);
			String bez_tel = req1.getString(6);
			String bez_pass = req1.getString(7);
			
			bezero = new Bezeroa(bez_nan,bez_izen,bez_abiz,bez_data,bez_sexu,bez_tel,bez_pass);
							

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
