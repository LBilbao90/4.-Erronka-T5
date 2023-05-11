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

import controlador.DatuBaseInsert;
import controlador.PertsonakKargatu;
import controlador.TaulaMetodoak;
import model.Bezeroa;
import model.SalbuespenaErregistro;
import model.SalbuespenaTransferentzia;

public class DatuBaseInsertTest {

	@Test
	public void testBezeroSortu() throws SalbuespenaErregistro {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		DatuBaseInsert datuBaseInsert = new DatuBaseInsert();
		
		String nan = "";
		String izena = "";
		String abizena = "";
		String jaiotzeData = "";
		String sexua = "";
		String telefonoa = "";
		String pasahitza = "";
		
		assertTrue(datuBaseInsert.bezeroSortu("Juan", "Perez", "86460678K", "1234", "1999","06", "12", "gizona", "111222333", "12345678Z", "1234"));
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("86460678K");
		
		Connection conn;					
		try {
			String url = "jdbc:mysql://localhost:3306/bankua";
			conn = (Connection) DriverManager.getConnection (url, "root","");
			Statement comando = (Statement) conn.createStatement();	
			ResultSet request = comando.executeQuery("Select nan, izena, abizenak, jaiotzeData, sexua, telefonoa, pasahitza from bezeroa where nan = '"+ b1.getNan() +"';");
			
			while(request.next()) {
				
				nan = request.getString(1);
				izena = request.getString(2);
				abizena = request.getString(3);
				jaiotzeData = request.getString(4);
				String[] data_split = jaiotzeData.split("-");
				jaiotzeData = data_split[1]+"-"+data_split[2]+"-"+data_split[0];
				sexua = request.getString(5);
				telefonoa = request.getString(6);
				pasahitza = request.getString(7);	
			}
			
			
			
			assertEquals(nan, b1.getNan());
			assertEquals(izena, b1.getIzena());
			assertEquals(abizena, b1.getAbizena());
			assertEquals(jaiotzeData, b1.getJaiotzeData());
			assertEquals(sexua, b1.getSexua());
			assertEquals(telefonoa, b1.getTelefonoa());
			assertEquals(pasahitza, b1.getPasahitza());
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}
	}
	
	@Test
	public void testHipotekaEskatu() {
		DatuBaseInsert datuBaseInsert = new DatuBaseInsert();
		
		assertTrue(datuBaseInsert.hipotekaEskatu("100000", "4", "ES1998760011555340294968", "15 urte", "78950146R", "1234"));
		
		String kantitatea = "";
		String komisioa = "";
		String hasieraData= "";
		String iban = "";
		String epeMuga = "";
		
		Format f = new SimpleDateFormat("MM/dd/yyyy");
		String data = f.format(new Date());
		String[] data_array = data.split("/");
		data = data_array[2]+"-"+data_array[0]+"-"+data_array[1];
		
		Connection conn;					
		try {
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();
			ResultSet req = comand.executeQuery("select kantitatea, komisioa, hasieraData, iban, epeMuga from hipoteka where iban = 'ES1998760011555340294968'");
			
			while(req.next()) {
				kantitatea = req.getString(1);
				komisioa = req.getString(2);
				hasieraData = req.getString(3);
				iban = req.getString(4);
				epeMuga = req.getString(5);
			}
			
			assertEquals("100000", kantitatea);
			assertEquals("4", komisioa);
			assertEquals("ES1998760011555340294968", iban);
			assertEquals(data, hasieraData);
			assertEquals("15 urte", epeMuga);
			
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());		
		}
	}
	
	@Test
	public void testTransferentziaEgin() throws SalbuespenaTransferentzia {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		DatuBaseInsert datuBaseInsert = new DatuBaseInsert();
		TaulaMetodoak taulaMetodoak = new TaulaMetodoak();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		String[][] transferentziak = taulaMetodoak.transferentziakIkusi(b1,"ES7023450002734016972210");
		assertTrue(datuBaseInsert.transferentziaEgin("ES7023450002734016972210", "ES7023450002734016972210", "1000", "Susana", "1.5", "5678", transferentziak));
		
		String ibanJaso = "";
		String ibanIgor = "";
		String kant = "";
		String kon = "";
		String komi = "";
		
		Connection conn;		
		try {
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();
			ResultSet req = comand.executeQuery("select kantitatea, jasotzailea, kontzeptua, komisioa, ibanigortzaile from transferentzia where ibanigortzaile = 'ES7023450002734016972210' and kontzeptua = 'Susana'");

			while(req.next()) {
				ibanJaso = req.getString(2);
				ibanIgor = req.getString(5);
				kant = req.getString(1);
				kon = req.getString(3);
				komi = req.getString(4);
				
				assertEquals("ES7023450002734016972210", ibanJaso);
				assertEquals("ES7023450002734016972210", ibanIgor);
				assertEquals("1000", kant);
				assertEquals("Susana", kon);
				assertEquals("1.5", komi);
			}
			
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}
		
	}
	
	@Test
	public void testLangileErregistratu() {
		DatuBaseInsert datuBaseInsert = new DatuBaseInsert();
		
		assertTrue(datuBaseInsert.langileErregistratu("61028521E", "Sandra", "Ramirez", "1999", "09", "02", "aktiboa", "emakumea", "111222333", "1234", "gerentea", "9"));
		
		String nan = "";
		String izen = "";
		String abizen = "";
		String jaio = "";
		String ego = "";
		String sex = "";
		String tel = "";
		String pas = "";
		String lan = "";
		String suk = "";
		
		Connection conn;		
		try {
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();
			ResultSet req = comand.executeQuery("select nan, izena, abizenak, jaiotzeData, sexua, telefonoa, pasahitza, lanpostua, id_sukurtsal, egoera from langile where nan = '61028521E'");

			while(req.next()) {
				nan = req.getString(1);
				izen = req.getString(2);
				abizen = req.getString(3);
				jaio = req.getString(4);
				sex = req.getString(5);
				tel = req.getString(6);
				pas = req.getString(7);
				lan = req.getString(8);
				suk = req.getString(9);
				ego = req.getString(10);
				
				assertEquals("61028521E", nan);
				assertEquals("Sandra", izen);
				assertEquals("Ramirez", abizen);
				assertEquals("1999-09-02", jaio);
				assertEquals("emakumea", sex);
				assertEquals("111222333", tel);
				assertEquals("1234", pas);
				assertEquals("gerentea", lan);
				assertEquals("9", suk);
				assertEquals("aktiboa", ego);
			}
			
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}
		
	}

}
