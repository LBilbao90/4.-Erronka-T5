package test;

import static org.junit.Assert.*;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import controlador.Metodoak;
import model.Bezeroa;
import model.EntitateBankario;
import model.Langilea;

public class MetodoakTest {

	@Test
	public void testBezeroakKargatu() {
		Metodoak metodoak = new Metodoak();
		Bezeroa b1 = metodoak.bezeroaKargatu("12345678A");
		
		assertEquals("12345678A", b1.getNan());
		assertEquals("Aingeru", b1.getIzena());
		assertEquals("Siranaula", b1.getAbizena());
		assertEquals("10-21-2002", b1.getJaiotzeData());
		assertEquals("gizona", b1.getSexua());
		assertEquals("111222333", b1.getTelefonoa());
		assertEquals("12345678", b1.getPasahitza());
	}
	
	@Test
	public void testBezeroaLogin() {
		Metodoak metodoak = new Metodoak();
		
		assertTrue(metodoak.bezeroLogin("12345678A", "12345678"));
	}
	
	@Test
	public void testLangileLogin() {
		Metodoak metodoak = new Metodoak();
		
		assertEquals("zuzendaria", metodoak.langileLogin("12345678B", "1234"));
	}
	
	@Test
	public void testBotoiakSortu() {
		Metodoak metodoak = new Metodoak();
		ArrayList<EntitateBankario> botoiakEntitate = metodoak.botoiakSortu();
		
		assertEquals("1", botoiakEntitate.get(0).getIdEntitatea());
		assertEquals("BBK", botoiakEntitate.get(0).getIzena());
		assertEquals("2345", botoiakEntitate.get(0).getEntitateZbk());
		assertEquals("218/253/128/45", botoiakEntitate.get(0).getBounds());
		assertEquals("src/res/bbk_logo.png", botoiakEntitate.get(0).getUrl());
	}
	
	@Test
	public void testLangileaKargatu() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("12345678B", "zuzendaria");
		
		assertEquals("12345678B", l1.getNan());
		assertEquals("Ibai", l1.getIzena());
		assertEquals("Alvarez", l1.getAbizena());
		assertEquals("10-21-2000", l1.getJaiotzeData());
		assertEquals("gizona", l1.getSexua());
		assertEquals("111222333", l1.getTelefonoa());
		assertEquals("1234", l1.getPasahitza());
		assertEquals("zuzendaria", l1.getLanpostu());
		
		assertEquals("1",l1.getSukurtsalak().get(0).getIdSukurtsala());
		assertEquals("0111", l1.getSukurtsalak().get(0).getKodSukurtsala());
		assertEquals("Santutxu, Santutxu Kalea, 27",l1.getSukurtsalak().get(0).getKokalekua());
		
		assertEquals("BBK", l1.getSukurtsalak().get(0).getEntitateBankario().getIzena());
		assertEquals("1",l1.getSukurtsalak().get(0).getEntitateBankario().getIdEntitatea());
		assertEquals("2345", l1.getSukurtsalak().get(0).getEntitateBankario().getEntitateZbk());
		assertEquals("218/253/128/45", l1.getSukurtsalak().get(0).getEntitateBankario().getBounds());
		assertEquals("src/res/bbk_logo.png", l1.getSukurtsalak().get(0).getEntitateBankario().getUrl());
		
		assertEquals("ES9723450111545932515164", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getIban());
		assertEquals(1500, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getSaldoa(), 0.01);
		assertEquals(1000, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHilekoLimitea(), 0.01);
		assertEquals("2022-01-01", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getSorreraData());
		assertEquals("aktiboa", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getEgoera());
		
		assertEquals(5000, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("2022-12-07", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getSarreraData());
		assertEquals("Ordainketa 1", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES2598760153921924586673", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIgortzailea());
		
		assertEquals(800, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("2020-08-01", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getTransferentziaData());
		assertEquals("Ordainketa 4", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES3467890003915285942937", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getJasotzailea());
		assertEquals(2, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKomisioa(), 0.01);
		
		assertEquals(60000, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getKantitatea(), 0.01);
		assertEquals(5000, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getOrdaindutakoa(), 0.01);
		assertEquals(2, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getKomisioa(), 0.01);
		assertEquals("2023-08-01", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getHasieraData());
		assertEquals(null, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getAmaieraData());
		assertEquals("errefusatuta", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getEgoera());
		
		assertEquals("5678", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getSegurtasunKodea());
		assertEquals("debito", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getMota());
		assertEquals("12345678A",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getNan());
		assertEquals("Aingeru",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getIzena());
		assertEquals("Siranaula",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getAbizena());
		assertEquals("10-21-2002",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getJaiotzeData());
		assertEquals("gizona",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getSexua());
		assertEquals("111222333",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getTelefonoa());
		assertEquals("12345678",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getPasahitza());
	}
	
	@Test
	public void testLangilearenEntitateak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("12345678B", "zuzendaria");
		String[] entitateak = metodoak.langilearenEntitateak(l1);
		
		assertEquals("BBK", entitateak[0]);
	}
	
	@Test
	public void testLangilearenSukurtsalak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("12345678B", "zuzendaria");
		String[] sukurtsalak = metodoak.langilearenSukurtsalak(l1, "BBK");
		
		assertEquals("Santutxu, Santutxu Kalea, 27", sukurtsalak[0]);
	}
	
	@Test
	public void testUrteakBete() {
		String[] urteak = Metodoak.urteakBete();
		
		assertEquals("2003", urteak[0]);
	}
	
	@Test
	public void testhilakBete() {
		String[] hilak = Metodoak.hilakBete();
		
		assertEquals("1", hilak[0]);
	}
	
	@Test
	public void testEgunakBete() {
		String[] egunak1 = Metodoak.egunakBete(1);
		String[] egunak2 = Metodoak.egunakBete(2);
		String[] egunak3 = Metodoak.egunakBete(11);
		
		assertEquals("1", egunak1[0]);
		assertEquals("1", egunak2[0]);
		assertEquals("1", egunak3[0]);
	}
	
	@Test
	public void testLangilearenSukurtsalarenKontuak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("12345678B", "zuzendaria");
		String[][] kontuak = metodoak.langilearenSukurtsalarenKontuak(l1, "Santutxu, Santutxu Kalea, 27");
		
		assertEquals("ES9723450111545932515164", kontuak[0][0]);
		assertEquals("1500,00 €", kontuak[0][1]);
		assertEquals("aktiboa", kontuak[0][2]);
	}
	
	@Test
	public void testLangileKontuInfo() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("12345678B", "zuzendaria");
		String[] kontua = metodoak.langileKontuInfo(l1, "ES9723450111545932515164", "Santutxu, Santutxu Kalea, 27");
		
		assertEquals("ES97 2345 0111  5459  3251  5164", kontua[0]);
		assertEquals("Aingeru Siranaula", kontua[1]);
		assertEquals("1500,00 €", kontua[2]);
		assertEquals("aktiboa", kontua[3]);
		assertEquals("1000,00", kontua[4]);
	}
	
	@Test
	public void testLangileKontuAldaketak() {
		Metodoak metodoak = new Metodoak();
		
		assertTrue(metodoak.langileKontuAldaketak("aktiboa", "1000", "ES9723450111545932515164"));
	}
	
	@Test
	public void testLangileKontuTransfer() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("12345678B", "zuzendaria");
		String[][] kontu = metodoak.langileKontuTransfer(l1, "ES9723450111545932515164");
		
		assertEquals("800,00 €", kontu[0][0]);
		assertEquals("2020-08-01", kontu[0][1]);
		assertEquals("ES34 6789 0003  9152  8594  2937", kontu[0][2]);
		assertEquals("Ordainketa 4", kontu[0][3]);
		assertEquals("2,00 %", kontu[0][4]);
	}
	
	@Test
	public void testLangileKontuSarrerak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("12345678B", "zuzendaria");
		String[][] kontu = metodoak.langileKontuSarrerak(l1, "ES9723450111545932515164");
		
		assertEquals("5000,00 €", kontu[0][0]);
		assertEquals("2022-12-07", kontu[0][1]);
		assertEquals("ES25 9876 0153  9219  2458  6673", kontu[0][2]);
		assertEquals("Ordainketa 1", kontu[0][3]);
	}
	
	@Test
	public void testBezeroSortu() {
		Metodoak metodoak = new Metodoak();
		
		String nan = "";
		String izena = "";
		String abizena = "";
		String jaiotzeData = "";
		String sexua = "";
		String telefonoa = "";
		String pasahitza = "";
		
		assertTrue(metodoak.bezeroSortu("Juan", "Perez", "12345678Z", "1234", "1999","06", "12", "gizona", "111222333"));
		Bezeroa b1 = metodoak.bezeroaKargatu("12345678Z");
		
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
	public void testIxtekoKontuak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("12345678C", "zuzendaria");
		String[][] kontuak = metodoak.ixtekoKontuak(l1, "Autonomia Kalea, 18");
		
		assertEquals("ES25 9876 0153  9219  2458  6673", kontuak[0][0]);
		assertEquals("9000,00 €", kontuak[0][1]);
		assertEquals("ixteko", kontuak[0][2]);
	}
	
	@Test
	public void testEskatutakoHipotekak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("12345678C", "zuzendaria");
		String[][] hipotekak = metodoak.eskatutakoHipotekak(l1, "Autonomia Kalea, 18");
		
		assertEquals("ES25 9876 0153  9219  2458  6673", hipotekak[0][0]);
		assertEquals("80000,00 €", hipotekak[0][1]);
		assertEquals("3,00 %", hipotekak[0][2]);
		assertEquals("10 urte", hipotekak[0][3]);
		assertEquals("eskatuta", hipotekak[0][4]);
	}
	
	@Test
	public void testOnartutakoHipotekak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("12345678A", "zuzendaria");
		String[][] hipotekak = metodoak.onartutakoHipotekak(l1, "Alameda de Recalde, 44");
		
		assertEquals("ES06 5432 0001  4187  5345  0238", hipotekak[0][0]);
		assertEquals("150000,00 €", hipotekak[0][1]);
		assertEquals("12000,00 €", hipotekak[0][2]);
		assertEquals("8,00 %", hipotekak[0][3]);
		assertEquals("2023-07-01", hipotekak[0][4]);
		assertEquals("5 urte", hipotekak[0][5]);
		assertEquals("onartuta", hipotekak[0][6]);
	}
	
	@Test
	public void testErrefusatutakoHipotekak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("12345678B", "zuzendaria");
		String[][] hipotekak = metodoak.errefusatutakoHipotekak(l1, "Santutxu, Santutxu Kalea, 27");
		
		assertEquals("ES97 2345 0111  5459  3251  5164", hipotekak[0][0]);
		assertEquals("60000,00 €", hipotekak[0][1]);
		assertEquals("2,00 %", hipotekak[0][2]);
		assertEquals("15 urte", hipotekak[0][3]);
		assertEquals("errefusatuta", hipotekak[0][4]);
	}
	
	@Test
	public void testItxitakoHipotekak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("12345678B", "zuzendaria");
		String[][] hipotekak = metodoak.itxitakoHipotekak(l1, "Urkixo Zumarkalea, 56");
		
		assertEquals("ES97 2345 0002  9857  8111  8223", hipotekak[0][0]);
		assertEquals("100000,00 €", hipotekak[0][1]);
		assertEquals("8000,00 €", hipotekak[0][2]);
		assertEquals("4,00 %", hipotekak[0][3]);
		assertEquals("2013-09-01", hipotekak[0][4]);
		assertEquals("2018-03-01", hipotekak[0][5]);
		assertEquals("5 urte", hipotekak[0][6]);
		assertEquals("itxita", hipotekak[0][7]);
	}
	
	@Test
	public void testBezeroarenKontuak() {
		Metodoak metodoak = new Metodoak();
		Bezeroa b1 = metodoak.bezeroaKargatu("12345678A");
		String[][] kontuak = metodoak.bezeroarenKontuak(b1, 1);
		
		assertEquals("ES97 2345 0002  9857  8111  8223", kontuak[0][0]);
		assertEquals("2000,00 €", kontuak[0][1]);
		assertEquals("aktiboa", kontuak[0][2]);
	}
	
	@Test
	public void testTransferentziakIkusi() {
		Metodoak metodoak = new Metodoak();
		Bezeroa b1 = metodoak.bezeroaKargatu("12345678A");
		String[][] transferentziak = metodoak.transferentziakIkusi(b1, 1, "ES9723450111545932515164");
		
		assertEquals("ES34 6789 0003  9152  8594  2937", transferentziak[0][0]);
		assertEquals("800.0 €", transferentziak[0][1]);
		assertEquals("Ordainketa 4", transferentziak[0][2]);
		assertEquals("2020-08-01", transferentziak[0][3]);
	}
	
	@Test
	public void testTransferentziaIbanBalidatu() {
		Metodoak metodoak = new Metodoak();
		
		assertTrue(metodoak.transferentziaIbanBalidatu("ES3467895948791937106722"));
	}
	
	@Test
	public void testTransferentziaSaldoaBalidatu() {
		Metodoak metodoak = new Metodoak();
		
		assertTrue(metodoak.transferentziaSaldoaBalidatu("100", "ES9723450111545932515164"));
	}
	
	@Test
	public void testSegurtasunKodeaBalidatu() {
		Metodoak metodoak = new Metodoak();
		
		assertTrue(metodoak.segurtasunKodeaBalidatu("5678", "ES9723450111545932515164"));
	}
	
	@Test
	public void testHipotekaDut() {
		Metodoak metodoak = new Metodoak();
		Bezeroa b1 = metodoak.bezeroaKargatu("12345678A");
		
		assertFalse(metodoak.hipotekaDut(b1, "ES9723450111545932515164"));
	}
}
