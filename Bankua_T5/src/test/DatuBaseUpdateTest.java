package test;

import static org.junit.Assert.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import controlador.DatuBaseUpdate;
import controlador.ObjetuMetodoak;
import controlador.PertsonakKargatu;
import model.Bezeroa;
import model.SalbuespenaOrdainketa;

public class DatuBaseUpdateTest {

	@Test
	public void testHipotekaUpdate() throws SalbuespenaOrdainketa{
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		ObjetuMetodoak metodoak = new ObjetuMetodoak();
		DatuBaseUpdate datuBaseUpdate = new DatuBaseUpdate();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		b1 = metodoak.hipotekaOrdaindu(b1, "ES7023450002734016972210", "1000");
		assertTrue(datuBaseUpdate.hipotekaUpdate(b1, "ES7023450002734016972210", "1234"));
		
		Format f = new SimpleDateFormat("MM/dd/yyyy");
		String data = f.format(new Date());
		String[] data_array = data.split("/");
		data = data_array[2]+"-"+data_array[0]+"-"+data_array[1];
		
		String kantitatea = "";
		String ordaindutakoa = "";
		String komisioa = "";
		String hasieraData = "";
		String egoera = "";
		String iban = "";
		String epeMuga = "";
		
		Connection conn;		
		try {
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();
			ResultSet req = comand.executeQuery("select kantitatea, ordaindutakoa, komisioa, hasieraData, egoera, iban, epeMuga from hipoteka where iban = 'ES7023450002734016972210'");

			while(req.next()) {
				kantitatea = req.getString(1);
				ordaindutakoa = req.getString(2);
				komisioa = req.getString(3);
				hasieraData = req.getString(4);
				egoera = req.getString(5);
				iban = req.getString(6);
				epeMuga = req.getString(7);
				
				assertEquals("70000", kantitatea);
				assertEquals("2000", ordaindutakoa);
				assertEquals("3.5", komisioa);
				assertEquals("2023-04-04", hasieraData);
				assertEquals("onartuta", egoera);
				assertEquals("ES7023450002734016972210", iban);
				assertEquals("5 urte", epeMuga);
			}
			
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}	
		
	}
	
	@Test
	public void testBezeroAldaketakUpdate() {
		DatuBaseUpdate datuBaseUpdate = new DatuBaseUpdate();
		
		assertTrue(datuBaseUpdate.bezeroAldaketakUpdate("67982379Z", "Ainhoa", "Jimenez", "emakumea", "333222111", "1234", "aktiboa"));
		
		String nan = "";
		String izena = "";
		String abizenak = "";
		String sexua = "";
		String telefonoa = "";
		String pasahitza = "";
		String egoera = "";
		
		Connection conn;					
		try {
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();
			ResultSet req = comand.executeQuery("select nan, izena, abizenak, sexua, telefonoa, pasahitza, egoera from bezeroa where nan = '67982379Z'");
			
			while(req.next()) {
				nan = req.getString(1);
				izena = req.getString(2);
				abizenak = req.getString(3);
				sexua = req.getString(4);
				telefonoa = req.getString(5);
				pasahitza = req.getString(6);
				egoera = req.getString(7);

			}
			
			assertEquals("67982379Z", nan);
			assertEquals("Ainhoa", izena);
			assertEquals("Jimenez", abizenak);
			assertEquals("emakumea", sexua);
			assertEquals("333222111", telefonoa);
			assertEquals("1234", pasahitza);
			assertEquals("aktiboa", egoera);
			
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());		
		}
	}
	
	@Test
	public void testLangileKontuAldaketak() {
		DatuBaseUpdate datuBaseUpdate = new DatuBaseUpdate();
		
		assertTrue(datuBaseUpdate.langileKontuAldaketak("aktiboa", "1000", "ES3454320001655285505955", "12345678Z", "1234"));
	}
	
	@Test
	public void testIxtekoKontuUpdate() {
		DatuBaseUpdate datuBaseUpdate = new DatuBaseUpdate();
		
		assertTrue(datuBaseUpdate.ixtekoKontuUpdate("ES8867890003395016874189"));
		
		String ego = "";
		
		Connection conn;					
		try {
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();
			ResultSet req = comand.executeQuery("select egoera from kontuBankario where iban = 'ES8867890003395016874189'");
			
			while(req.next()) {
				ego = req.getString(1);
			}
			
			assertEquals("ixteko", ego);
			
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());		
		}
	}
	
	@Test
	public void testLangileAldaketakUpdate() {
		DatuBaseUpdate datuBaseUpdate = new DatuBaseUpdate();
		
		assertTrue(datuBaseUpdate.langileAldaketakUpdate("90138299B", "Jon", "Perez", "gizona", "111222333", "1234", "gerentea", "9", "aktiboa"));
		
		String nan = "";
		String izena = "";
		String abizenak = "";
		String sexua = "";
		String telefonoa = "";
		String pasahitza = "";
		String lanpostua = "";
		String id_sukurtsal = "";
		
		Connection conn;					
		try {
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();
			ResultSet req = comand.executeQuery("select nan, izena, abizenak, sexua, telefonoa, pasahitza, lanpostua, id_sukurtsal from langile where nan = '90138299B'");
			
			while(req.next()) {
				nan = req.getString(1);
				izena = req.getString(2);
				abizenak = req.getString(3);
				sexua = req.getString(4);
				telefonoa = req.getString(5);
				pasahitza = req.getString(6);
				lanpostua = req.getString(7);
				id_sukurtsal = req.getString(8);
			}
			
			assertEquals("90138299B", nan);
			assertEquals("Jon", izena);
			assertEquals("Perez", abizenak);
			assertEquals("gizona", sexua);
			assertEquals("111222333", telefonoa);
			assertEquals("1234", pasahitza);
			assertEquals("gerentea", lanpostua);
			assertEquals("9", id_sukurtsal);
			
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());		
		}
	}
}
