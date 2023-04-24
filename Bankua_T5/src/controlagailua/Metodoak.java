package controlagailua;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.EntitateBankarioa;

public class Metodoak {
	final String url = "jdbc:mysql://localhost:3306/bankua";
	final String erabiltzaile = "root";
	final String pass="";
	
	//entitatebankarioa
	final String entitatebankario = "entitatebankario";
	final String id_entitate= "id_entitate";
	final String izena = "izena";
	final String entitateZenbaki = "entitateZenbaki";
	final String urlImg = "url";
	final String bounds = "bounds";
	
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
}
