package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import model.SalbuespenaErregistro;
import model.SalbuespenaLogin;
import model.SalbuespenaLoginBlokeo;

public class MetodoakTest {

	@Test
	public void testBezeroakKargatu() {
		Metodoak metodoak = new Metodoak();
		Bezeroa b1 = metodoak.bezeroaKargatu("78950146R");
		
		assertEquals("78950146R", b1.getNan());
		assertEquals("Aingeru", b1.getIzena());
		assertEquals("Siranaula", b1.getAbizena());
		assertEquals("10-21-2002", b1.getJaiotzeData());
		assertEquals("gizona", b1.getSexua());
		assertEquals("111222333", b1.getTelefonoa());
		assertEquals("1234", b1.getPasahitza());
	}
	
	@Test
	public void testBezeroaLoginAktiboa() throws SalbuespenaLoginBlokeo {
		Metodoak metodoak = new Metodoak();
		
		try {
			assertTrue(metodoak.bezeroLogin("78950146R", "1234"));
		} catch (SalbuespenaLogin e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testBezeroaLoginBloqueatuta() throws SalbuespenaLoginBlokeo {
		Metodoak metodoak = new Metodoak();
		
		try {
			metodoak.bezeroLogin("54821599H", "1234");
		} catch (SalbuespenaLogin e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testBezeroaLoginError() throws SalbuespenaLoginBlokeo {
		Metodoak metodoak = new Metodoak();
		
		try {
			assertFalse(metodoak.bezeroLogin("78950146J", "1234"));
		} catch (SalbuespenaLogin e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testLangileLoginZuzendaria() {
		Metodoak metodoak = new Metodoak();
		
		try {
			assertEquals("zuzendaria", metodoak.langileLogin("78950146R", "1234"));
		} catch (SalbuespenaLogin e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testLangileLoginGerentea() {
		Metodoak metodoak = new Metodoak();
		
		try {
			assertEquals("gerentea", metodoak.langileLogin("90138299B", "1234"));
		} catch (SalbuespenaLogin e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testLangileLoginGod() {
		Metodoak metodoak = new Metodoak();
		
		try {
			assertEquals("god", metodoak.langileLogin("12345678Z", "1234"));
		} catch (SalbuespenaLogin e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testLangileLoginFalse() {
		Metodoak metodoak = new Metodoak();
		
		try {
			assertEquals("god", metodoak.langileLogin("12345678E", "1234"));
		} catch (SalbuespenaLogin e) {
			System.out.println(e);
		}
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
		Langilea l1 = metodoak.langileaKargatu("79003399D", "zuzendaria");
		
		assertEquals("79003399D", l1.getNan());
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
		
		assertEquals("ES9323450111313252003900", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getIban());
		assertEquals(20046.09, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getSaldoa(), 0.01);
		assertEquals(2000, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHilekoLimitea(), 0.01);
		assertEquals("2003-09-15", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getSorreraData());
		assertEquals("aktiboa", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getEgoera());
		
		assertEquals(100, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("2023-04-04", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getSarreraData());
		assertEquals("dirua", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES2967890003394765827453", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIgortzailea());
		
		assertEquals(100, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("2023-04-04", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getTransferentziaData());
		assertEquals("paga", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES2267890050608253351724", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getJasotzailea());
		assertEquals(2, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKomisioa(), 0.01);
		
		assertEquals(80000, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getKantitatea(), 0.01);
		assertEquals(0, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getOrdaindutakoa(), 0.01);
		assertEquals(3.8, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getKomisioa(), 0.01);
		assertEquals("2023-04-04", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getHasieraData());
		assertEquals(null, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getAmaieraData());
		assertEquals("eskatuta", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getEgoera());
		
		assertEquals("5678", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getSegurtasunKodea());
		assertEquals("kredito", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getMota());
		assertEquals("78950146R",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getNan());
		assertEquals("Aingeru",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getIzena());
		assertEquals("Siranaula",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getAbizena());
		assertEquals("10-21-2002",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getJaiotzeData());
		assertEquals("gizona",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getSexua());
		assertEquals("111222333",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getTelefonoa());
		assertEquals("1234",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getPasahitza());
	}
	
	@Test
	public void testLangilearenEntitateak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("79003399D", "zuzendaria");
		String[] entitateak = metodoak.langilearenEntitateak(l1);
		
		assertEquals("BBK", entitateak[0]);
	}
	
	@Test
	public void testLangilearenSukurtsalak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("79003399D", "zuzendaria");
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
		Langilea l1 = metodoak.langileaKargatu("79003399D", "zuzendaria");
		String[][] kontuak = metodoak.langilearenSukurtsalarenKontuak(l1, "Santutxu, Santutxu Kalea, 27");
		
		assertEquals("ES9323450111313252003900", kontuak[0][0]);
		assertEquals("20046,09 €", kontuak[0][1]);
		assertEquals("aktiboa", kontuak[0][2]);
	}
	
	@Test
	public void testLangileKontuInfo() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("79003399D", "zuzendaria");
		String[] kontua = metodoak.langileKontuInfo(l1, "ES9323450111313252003900", "Santutxu, Santutxu Kalea, 27");
		
		assertEquals("ES93 2345 0111  31  3252003900", kontua[0]);
		assertEquals("Aingeru Siranaula", kontua[1]);
		assertEquals("20046,09 €", kontua[2]);
		assertEquals("aktiboa", kontua[3]);
		assertEquals("2000,00", kontua[4]);
	}
	
	@Test
	public void testLangileKontuAldaketak() {
		Metodoak metodoak = new Metodoak();
		
		assertTrue(metodoak.langileKontuAldaketak("aktiboa", "1000", "ES3454320001655285505955"));
	}
	
	@Test
	public void testLangileKontuTransfer() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("79003399D", "zuzendaria");
		String[][] kontu = metodoak.langileKontuTransfer(l1, "ES9323450111313252003900");
		
		assertEquals("100,00 €", kontu[0][0]);
		assertEquals("2023-04-04", kontu[0][1]);
		assertEquals("ES22 6789 0050  60  8253351724", kontu[0][2]);
		assertEquals("paga", kontu[0][3]);
		assertEquals("2,00 %", kontu[0][4]);
	}
	
	@Test
	public void testLangileKontuSarrerak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("79003399D", "zuzendaria");
		String[][] kontu = metodoak.langileKontuSarrerak(l1, "ES9323450111313252003900");
		
		assertEquals("100,00 €", kontu[0][0]);
		assertEquals("2023-04-04", kontu[0][1]);
		assertEquals("ES29 6789 0003  3947  6582  7453", kontu[0][2]);
		assertEquals("dirua", kontu[0][3]);
	}
	
	@Test
	public void testBezeroSortu() throws SalbuespenaErregistro {
		Metodoak metodoak = new Metodoak();
		
		String nan = "";
		String izena = "";
		String abizena = "";
		String jaiotzeData = "";
		String sexua = "";
		String telefonoa = "";
		String pasahitza = "";
		
		assertTrue(metodoak.bezeroSortu("Juan", "Perez", "86460678K", "1234", "1999","06", "12", "gizona", "111222333"));
		Bezeroa b1 = metodoak.bezeroaKargatu("86460678K");
		
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
		Langilea l1 = metodoak.langileaKargatu("78950146R", "zuzendaria");
		String[][] kontuak = metodoak.ixtekoKontuak(l1, "C. de Bertendona, 4");
		
		assertEquals("ES22 6789 0050  60  8253351724", kontuak[0][0]);
		assertEquals("1143,96 €", kontuak[0][1]);
		assertEquals("ixteko", kontuak[0][2]);
	}
	
	@Test
	public void testEskatutakoHipotekak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("79003399D", "zuzendaria");
		String[][] hipotekak = metodoak.eskatutakoHipotekak(l1, "Santutxu, Santutxu Kalea, 27");
		
		assertEquals("ES93 2345 0111  31  3252003900", hipotekak[0][0]);
		assertEquals("80000,00 €", hipotekak[0][1]);
		assertEquals("3,80 %", hipotekak[0][2]);
		assertEquals("10 urte", hipotekak[0][3]);
		assertEquals("eskatuta", hipotekak[0][4]);
	}
	
	@Test
	public void testOnartutakoHipotekak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("79003399D", "zuzendaria");
		String[][] hipotekak = metodoak.onartutakoHipotekak(l1, "Urkixo Zumarkalea, 56");
		
		assertEquals("ES70 2345 0002  73  4016972210", hipotekak[0][0]);
		assertEquals("70000,00 €", hipotekak[0][1]);
		assertEquals("1000,00 €", hipotekak[0][2]);
		assertEquals("3,50 %", hipotekak[0][3]);
		assertEquals("2023-04-04", hipotekak[0][4]);
		assertEquals("5 urte", hipotekak[0][5]);
		assertEquals("onartuta", hipotekak[0][6]);
	}
	
	@Test
	public void testErrefusatutakoHipotekak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("12345678Z", "god");
		String[][] hipotekak = metodoak.errefusatutakoHipotekak(l1, "Alameda de Recalde, 35");
		
		assertEquals("ES29 6789 0003  39  4765827453", hipotekak[0][0]);
		assertEquals("100000,00 €", hipotekak[0][1]);
		assertEquals("4,00 %", hipotekak[0][2]);
		assertEquals("15 urte", hipotekak[0][3]);
		assertEquals("errefusatuta", hipotekak[0][4]);
	}
	
	@Test
	public void testItxitakoHipotekak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("12345678Z", "god");
		String[][] hipotekak = metodoak.itxitakoHipotekak(l1, "C. de Bertendona, 4");
		
		assertEquals("ES22 6789 0050  60  8253351724", hipotekak[0][0]);
		assertEquals("100000,00 €", hipotekak[0][1]);
		assertEquals("0,00 €", hipotekak[0][2]);
		assertEquals("4,00 %", hipotekak[0][3]);
		assertEquals("2008-04-04", hipotekak[0][4]);
		assertEquals("2023-05-04", hipotekak[0][5]);
		assertEquals("15 urte", hipotekak[0][6]);
		assertEquals("itxita", hipotekak[0][7]);
	}
	
	@Test
	public void testBezeroarenKontuak() {
		Metodoak metodoak = new Metodoak();
		Bezeroa b1 = metodoak.bezeroaKargatu("78950146R");
		String[][] kontuak = metodoak.bezeroarenKontuak(b1, 1);
		
		assertEquals("ES70 2345 0002  73  4016972210", kontuak[0][0]);
		assertEquals("3451,98 €", kontuak[0][1]);
		assertEquals("aktiboa", kontuak[0][2]);
	}
	
	@Test
	public void testTransferentziakIkusi() {
		Metodoak metodoak = new Metodoak();
		Bezeroa b1 = metodoak.bezeroaKargatu("78950146R");
		String[][] transferentziak = metodoak.transferentziakIkusi(b1,"ES7023450002734016972210");
		
		assertEquals("ES51 2345 0002  73  1634074159", transferentziak[0][0]);
		assertEquals("1230,00 €", transferentziak[0][1]);
		assertEquals("Zorionak", transferentziak[0][2]);
		assertEquals("2023-04-04", transferentziak[0][3]);
	}
	
	@Test
	public void testTransferentziaIbanBalidatu() {
		Metodoak metodoak = new Metodoak();
		
		assertTrue(metodoak.transferentziaIbanBalidatu("ES7023450002734016972210"));
	}
	
	@Test
	public void testTransferentziaSaldoaBalidatu() {
		Metodoak metodoak = new Metodoak();
		
		assertTrue(metodoak.transferentziaSaldoaBalidatu("100", "ES7023450002734016972210"));
	}
	
	@Test
	public void testSegurtasunKodeaBalidatu() {
		Metodoak metodoak = new Metodoak();
		
		assertTrue(metodoak.segurtasunKodeaBalidatu("5678", "ES7023450002734016972210"));
	}
	
	@Test
	public void testHipotekaDut() {
		Metodoak metodoak = new Metodoak();
		Bezeroa b1 = metodoak.bezeroaKargatu("78950146R");
		Bezeroa b2 = metodoak.bezeroaKargatu("89167975B");
		
		assertTrue(metodoak.hipotekaDut(b1, "ES7023450002734016972210"));
		assertFalse(metodoak.hipotekaDut(b2, "ES5123450002731634074159"));
	}
	
	@Test
	public void testBezeroExistituTrue() {
		Metodoak metodoak = new Metodoak();
		
		assertTrue(metodoak.bezeroExistitu("78950146R"));
	}
	
	@Test
	public void testBezeroExistituFalse() {
		Metodoak metodoak = new Metodoak();
		
		assertFalse(metodoak.bezeroExistitu("78950146J"));
	}
	
	@Test
	public void testTransferentziakImprimatu() {
		Metodoak metodoak = new Metodoak();
		Bezeroa b1 = metodoak.bezeroaKargatu("78950146R");
		String[][] transferentziak_array = metodoak.transferentziakIkusi(b1, "ES9323450111313252003900"); 

		assertTrue(metodoak.transferentziakImprimatu(transferentziak_array));
		
		BufferedReader fichero;
		File file = new File("src/movimientos/Transferentziak/2023_4_4_9_4.txt");
		try {
		fichero = new BufferedReader(new FileReader(file));
		String linea;
		String [] transferentziak = new String [0];
		
		while(( linea = fichero.readLine()) != null) {
			String[] transferentziak_prob = new String[transferentziak.length+1];
			for(int i =0;i<transferentziak.length;i++)
			{
				transferentziak_prob[i]=transferentziak[i];
			}
			transferentziak_prob[transferentziak.length] = linea;
			transferentziak = transferentziak_prob;
		}
		
		assertEquals("IBAN: ES22 6789 0050  60  8253351724 ,Kantitatea: 100,00 € ,Kontzeptuapaga ,Transferetzia-data: 2023-04-04", transferentziak[1]);
		
		fichero.close();
		
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void testdiruSarrerakImprimatu() {	
		Metodoak metodoak = new Metodoak();
		Bezeroa b1 = metodoak.bezeroaKargatu("78950146R");
		String[][] diruSarrerak_array = metodoak.diruSarrerakIkusi(b1, "ES9323450111313252003900");
		assertTrue(metodoak.diruSarrerakImprimatu(diruSarrerak_array));
		
		BufferedReader fichero;
		File file = new File("src/movimientos/DiruSarrerak/2023_4_4_9_4.txt");
		try {
		fichero = new BufferedReader(new FileReader(file));
		String linea;
		String [] diruSarrerak = new String [0];
		
		while(( linea = fichero.readLine()) != null) {
			String[] diruSarrerak_prob = new String[diruSarrerak.length+1];
			for(int i =0;i<diruSarrerak.length;i++)
			{
				diruSarrerak_prob[i]=diruSarrerak[i];
			}
			diruSarrerak_prob[diruSarrerak.length] = linea;
			diruSarrerak = diruSarrerak_prob;
		}
		
		assertEquals("IBAN igortzailea: ES29 6789 0003  39  4765827453 ,Kantitatea: 100,00 € ,Kontzeptuadirua ,Sarrera-data: 2023-04-04", diruSarrerak[1]);
		
		fichero.close();
		
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void testMugimentuakImprimatu() {
		Metodoak metodoak = new Metodoak();
		Bezeroa b1 = metodoak.bezeroaKargatu("78950146R");
		String[][] diruSarrerak_array = metodoak.diruSarrerakIkusi(b1, "ES9323450111313252003900");
		String[][] transferentziak_array = metodoak.transferentziakIkusi(b1, "ES9323450111313252003900");
		assertTrue(metodoak.mugimentuakImprimatu(diruSarrerak_array, transferentziak_array));
		
		BufferedReader fichero;
		File file = new File("src/movimientos/Mugimenduak/2023_4_4_9_4.txt");
		try {
		fichero = new BufferedReader(new FileReader(file));
		String linea;
		String [] mugimenduak = new String [0];
		
		while(( linea = fichero.readLine()) != null) {
			String[] mugimenduak_prob = new String[mugimenduak.length+1];
			for(int i =0;i<mugimenduak.length;i++)
			{
				mugimenduak_prob[i]=mugimenduak[i];
			}
			mugimenduak_prob[mugimenduak.length] = linea;
			mugimenduak = mugimenduak_prob;
		}
		
		assertEquals("IBAN: ES22 6789 0050  60  8253351724 ,Kantitatea: 100,00 € ,Kontzeptuapaga ,Transferetzia-data: 2023-04-04", mugimenduak[1]);
		assertEquals("IBAN igortzailea: ES29 6789 0003  39  4765827453 ,Kantitatea: 100,00 € ,Kontzeptuadirua ,Sarrera-data: 2023-04-04", mugimenduak[3]);
		
		fichero.close();
		
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void testNanbalidatu() {
		Metodoak metodoak = new Metodoak();
		
		assertTrue(metodoak.nanBalidatu("78950146R"));
	}
	
	@Test
	public void testTxartelIdBalidatuTrue() {
		Metodoak metodoak = new Metodoak();
		
		assertTrue(metodoak.txartelIdBalidatu("8949421198213169"));
	}
	
	@Test
	public void testTxartelIdBalidatuFalse() {
		Metodoak metodoak = new Metodoak();
		
		assertFalse(metodoak.txartelIdBalidatu("8949467198213169"));
	}
	
	@Test
	public void testDiruBalidatuTrue() {
		assertTrue(Metodoak.diruBalidatu("100,12"));
	}
	
	@Test
	public void testDiruBalidatuFalse() {
		assertFalse(Metodoak.diruBalidatu("a"));
	}
	
	@Test
	public void testBezeroZerrendaKargatu() {
		Metodoak metodoak = new Metodoak();
		String[][] bezero_zerrenda = metodoak.bezeroZerrendaKargatu();
		
		assertEquals("54821599H", bezero_zerrenda[0][0]);
		assertEquals("Iker", bezero_zerrenda[0][1]);
		assertEquals("Zuluaga", bezero_zerrenda[0][2]);
		assertEquals("1994-08-04", bezero_zerrenda[0][3]);
		assertEquals("gizona", bezero_zerrenda[0][4]);
		assertEquals("111222333", bezero_zerrenda[0][5]);
		assertEquals("1234", bezero_zerrenda[0][6]);
		assertEquals("blokeatuta", bezero_zerrenda[0][7]);
	}
	
	@Test
	public void testBezeroInfo() {
		Metodoak metodoak = new Metodoak();
		String[][] bezero_zerrenda = metodoak.bezeroZerrendaKargatu();
		String[] bezeroInfo = metodoak.bezeroInfo(bezero_zerrenda, "54821599H");
		
		assertEquals("54821599H", bezeroInfo[0]);
		assertEquals("Iker", bezeroInfo[1]);
		assertEquals("Zuluaga", bezeroInfo[2]);
		assertEquals("1994-08-04", bezeroInfo[3]);
		assertEquals("gizona", bezeroInfo[4]);
		assertEquals("111222333", bezeroInfo[5]);
		assertEquals("1234", bezeroInfo[6]);
		assertEquals("blokeatuta", bezeroInfo[7]);
	}
	
	@Test
	public void testLangileZerrendaKargatu() {
		Metodoak metodoak = new Metodoak();
		String[][] langile_zerrenda = metodoak.langileZerrendaKargatu();
		
		assertEquals("79003399D", langile_zerrenda[0][0]);
		assertEquals("Ibai", langile_zerrenda[0][1]);
		assertEquals("Alvarez", langile_zerrenda[0][2]);
		assertEquals("2000-10-21", langile_zerrenda[0][3]);
		assertEquals("gizona", langile_zerrenda[0][4]);
		assertEquals("111222333", langile_zerrenda[0][5]);
		assertEquals("1234", langile_zerrenda[0][6]);
		assertEquals("zuzendaria", langile_zerrenda[0][7]);
	}
	
	@Test
	public void testLangileInfo() {
		Metodoak metodoak = new Metodoak();
		String[][] langile_zerrenda = metodoak.langileZerrendaKargatu();
		String[] langileInfo = metodoak.langileInfo(langile_zerrenda, "79003399D");
		
		assertEquals("79003399D", langileInfo[0]);
		assertEquals("Ibai", langileInfo[1]);
		assertEquals("Alvarez", langileInfo[2]);
		assertEquals("2000-10-21", langileInfo[3]);
		assertEquals("gizona", langileInfo[4]);
		assertEquals("111222333", langileInfo[5]);
		assertEquals("1234", langileInfo[6]);
		assertEquals("zuzendaria", langileInfo[7]);
	}
	
	@Test
	public void testHipotekaEstatus() {
		Metodoak metodoak = new Metodoak();
		String[] hipoteka = metodoak.hipotekaEstatus("ES9323450111313252003900");
		
		assertEquals("80000", hipoteka[0]);
		assertEquals("2023-04-04", hipoteka[1]);
		assertEquals("-----", hipoteka[2]);
		assertEquals("3.8", hipoteka[3]);
		assertEquals("0", hipoteka[4]);
	}
}
