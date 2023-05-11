package test;

import static org.junit.Assert.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import controlador.DatuBaseDelete;

public class DatuBaseDeleteTest {

	@Test
	public void testKontuItxiDelete() {
		DatuBaseDelete datuBaseDelete = new DatuBaseDelete();
		
		assertTrue(datuBaseDelete.kontuItxiDelete("ES8954320001660087768901", "12345678Z", "1234"));
		
		boolean aurkitu = false;
		
		Connection conn;					
		try {
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();	
			ResultSet req = comand.executeQuery("Select iban from kontuBankario where iban='ES8954320001660087768901';");
			
			if(req.next()) {
				aurkitu = true;
			}
			
			assertFalse(aurkitu);
			
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}
	}
	
	@Test
	public void testErabiltzaileEzabatu() {
		DatuBaseDelete datuBaseDelete = new DatuBaseDelete();
		
		assertTrue(datuBaseDelete.erabiltzaileEzabatu("84850597D"));
		
		boolean aurkitu = false;
		
		Connection conn;					
		try {
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();	
			ResultSet req = comand.executeQuery("Select nan from bezeroa where nan='84850597D';");
			
			if(req.next()) {
				aurkitu = true;
			}
			
			assertFalse(aurkitu);
			
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}
	}
	
	@Test
	public void testLangileKaleratu() {
		DatuBaseDelete datuBaseDelete = new DatuBaseDelete();
		
		assertTrue(datuBaseDelete.erabiltzaileEzabatu("47757325X"));
		
		boolean aurkitu = false;
		
		Connection conn;					
		try {
			conn = (Connection) DriverManager.getConnection ("jdbc:mysql://localhost:3306/bankua","root","");
			Statement comand = (Statement) conn.createStatement();	
			ResultSet req = comand.executeQuery("Select nan from bezeroa where nan='47757325X';");
			
			if(req.next()) {
				aurkitu = true;
			}
			
			assertFalse(aurkitu);
			
			conn.close();
		}catch(SQLException ex) {
			System.out.println("SQLException: "+ ex.getMessage());
			System.out.println("SQLState: "+ ex.getSQLState());
			System.out.println("ErrorCode: "+ ex.getErrorCode());
		}
	}

}
