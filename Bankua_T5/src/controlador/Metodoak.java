package controlador;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.Bezeroa;
import model.EntitateBankarioa;
import model.Langilea;

public class Metodoak {
	final String url = "jdbc:mysql://localhost:3306/bankua";
	final String erabiltzaile = "root";
	final String pass="";
	
	// Entitatebankarioa
	final String entitatebankario = "entitatebankario";
	final String id_entitate= "id_entitate";
	final String entitate_izena = "izena";
	final String entitateZenbaki = "entitateZenbaki";
	final String urlImg = "url";
	final String bounds = "bounds";
	
	// Bezeroa
	final String nan = "nan";
	final String izena = "izena";
	final String abizenak = "abizenak";
	final String jaiotzeData = "jaiotzeData";
	final String Generoa = "generoa";
	final String telefonoa = "telefonoa";
	final String pasahitza = "pasahitza";
	
	// Langile
	final String id_langile = "id_langile";
	final String lanpostua = "lanpostua";
	
	
	// Sukurtsala
	final String kodSukurtsala = "kodSukurtsala";
	final String kokalekua = "kokalekua";
	final String id_sukurtsal = "id_sukurtsal";
	
	// KontuBankarioa
	final String iban = "iban";
	final String saldoa = "saldoa";
	final String hilekoLimitea = "hilekoLimitea";
	final String sorreraData = "sorreraData";
	final String egoera = "egoera";
	
	// DiruSarrera
	final String idSarrera = "idSarrera";
	final String kantitatea = "kantitatea";
	final String sarreraData = "sarreraData";
	final String igortzaile = "igortzaile";
	final String kotzeptua = "kotzeptua";
	final String ibanjasotzaile = "ibanjasotzaile";
	
	// Transferentzia
	final String trasferentzia = "trasferentzia";
	final String TrasnferentziaData = "TrasnferentziaData";
	final String jasotzailea = "jasotzailea";
	final String komisioa = "komisioa";
	final String ibanIgortzaile = "ibanIgortzaile";
	
	// Hipoteka
	final String idHipoteka = "idHipoteka";
	final String ordaindutakoa = "ordaindutakoa";
	final String hasieraData = "hasieraData";
	final String amaieraData = "amaieraData";
	
	// Txartela
	final String idTxartela = "idTxartela";
	final String nanBezeroa = "nanBezeroa";
	final String segurtasunKodea = "segurtasunKodea";
	
	public EntitateBankarioa[] botoiakSortu() {
		EntitateBankarioa[] bankuak = new EntitateBankarioa[0];
		
		Connection conn;					
		try {
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comando = (Statement) conn.createStatement();	
			ResultSet request = comando.executeQuery("Select * from "+entitatebankario+";");
			
			while(request.next()) {
				EntitateBankarioa bankua = new EntitateBankarioa();
				bankua.setIdEntitatea(Integer.parseInt(request.getString(1)));
				bankua.setIzena(request.getString(2));
				bankua.setEntitateZbk(request.getString(3));
				bankua.setUrl(request.getString(4));
				bankua.setBounds(request.getString(5));
				

				EntitateBankarioa[] bankuak_prob = new EntitateBankarioa[bankuak.length+1];
				for(int i=0;i<bankuak.length;i++) {
					bankuak_prob[i]=bankuak[i];
				}
				bankuak_prob[bankuak.length]=bankua;
				bankuak=bankuak_prob;				
			}
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}
		
		return bankuak;
	}
	
	public  boolean LoginBezeroa(String nan, String pasahitza, ArrayList<Bezeroa> bezeroak) {
		boolean balidatu= false;
		
		for(int i=0; i<bezeroak.size() && !balidatu; i++) {
			if(bezeroak.get(i).getNan().equalsIgnoreCase(nan) && bezeroak.get(i).getPasahitza().equals(pasahitza)) {
				balidatu=true;
			}
		}
		return balidatu;
	}
	
	public  boolean LoginLangilea(String nan, String pasahitza, ArrayList<Langilea> langileak) {
		boolean balidatu= false;
		
		for(int i=0; i<langileak.size() && !balidatu; i++) {
			if(langileak.get(i).getNan().equalsIgnoreCase(nan) && langileak.get(i).getPasahitza().equals(pasahitza)) {
				balidatu=true;
			}
		}
		return balidatu;
	}
}
