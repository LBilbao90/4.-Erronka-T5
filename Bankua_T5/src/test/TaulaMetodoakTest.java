package test;

import static org.junit.Assert.*;

import org.junit.Test;

import controlador.PertsonakKargatu;
import controlador.TaulaMetodoak;
import model.Bezeroa;
import model.Langilea;

public class TaulaMetodoakTest {

	@Test
	public void testLangilearenSukurtsalarenKontuak() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		TaulaMetodoak taulaMetodoak = new TaulaMetodoak();
		
		Langilea l1 = pertsonakKargatu.langileaKargatu("79003399D", "zuzendaria", "1234");
		String[][] kontuak = taulaMetodoak.langilearenSukurtsalarenKontuak(l1, "Santutxu, Santutxu Kalea, 27");
		
		assertEquals("ES9323450111313252003900", kontuak[0][0]);
		assertEquals("20046,09 €", kontuak[0][1]);
		assertEquals("aktiboa", kontuak[0][2]);
	}
	
	@Test
	public void testLangileKontuTransfer() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		TaulaMetodoak taulaMetodoak = new TaulaMetodoak();
		
		Langilea l1 = pertsonakKargatu.langileaKargatu("79003399D", "zuzendaria", "1234");
		String[][] kontu = taulaMetodoak.langileKontuTransfer(l1, "ES9323450111313252003900");
		
		assertEquals("100,00 €", kontu[0][0]);
		assertEquals("2023-04-04", kontu[0][1]);
		assertEquals("ES22 6789 0050  60  8253351724", kontu[0][2]);
		assertEquals("paga", kontu[0][3]);
		assertEquals("1.5 %", kontu[0][4]);
	}
	
	@Test
	public void testLangileKontuSarrerak() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		TaulaMetodoak taulaMetodoak = new TaulaMetodoak();
		
		Langilea l1 = pertsonakKargatu.langileaKargatu("79003399D", "zuzendaria", "1234");
		String[][] kontu = taulaMetodoak.langileKontuSarrerak(l1, "ES9323450111313252003900");
		
		assertEquals("100,00 €", kontu[0][0]);
		assertEquals("2023-04-04", kontu[0][1]);
		assertEquals("ES29 6789 0003  3947  6582  7453", kontu[0][2]);
		assertEquals("dirua", kontu[0][3]);
	}
	
	@Test
	public void testIxtekoKontuak() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		TaulaMetodoak taulaMetodoak = new TaulaMetodoak();
		
		Langilea l1 = pertsonakKargatu.langileaKargatu("78950146R", "zuzendaria", "1234");
		String[][] kontuak = taulaMetodoak.ixtekoKontuak(l1, "C. de Bertendona, 4");
		
		assertEquals("ES22 6789 0050  60  8253351724", kontuak[0][0]);
		assertEquals("1143,96 €", kontuak[0][1]);
		assertEquals("ixteko", kontuak[0][2]);
	}
	
	@Test
	public void testEskatutakoHipotekak() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		TaulaMetodoak taulaMetodoak = new TaulaMetodoak();
		
		Langilea l1 = pertsonakKargatu.langileaKargatu("79003399D", "zuzendaria", "2");
		String[][] hipotekak = taulaMetodoak.eskatutakoHipotekak(l1, "Santutxu, Santutxu Kalea, 27");
		
		assertEquals("ES93 2345 0111  31  3252003900", hipotekak[0][0]);
		assertEquals("80000,00 €", hipotekak[0][1]);
		assertEquals("3.8 %", hipotekak[0][2]);
		assertEquals("10 urte", hipotekak[0][3]);
		assertEquals("eskatuta", hipotekak[0][4]);
	}
	
	@Test
	public void testOnartutakoHipotekak() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		TaulaMetodoak taulaMetodoak = new TaulaMetodoak();
		
		Langilea l1 = pertsonakKargatu.langileaKargatu("79003399D", "zuzendaria", "2");
		String[][] hipotekak = taulaMetodoak.onartutakoHipotekak(l1, "Urkixo Zumarkalea, 56");
		
		assertEquals("ES70 2345 0002  73  4016972210", hipotekak[0][0]);
		assertEquals("70000,00 €", hipotekak[0][1]);
		assertEquals("2000,00 €", hipotekak[0][2]);
		assertEquals("3.5 %", hipotekak[0][3]);
		assertEquals("2023-04-04", hipotekak[0][4]);
		assertEquals("5 urte", hipotekak[0][5]);
		assertEquals("onartuta", hipotekak[0][6]);
	}
	
	@Test
	public void testErrefusatutakoHipotekak() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		TaulaMetodoak taulaMetodoak = new TaulaMetodoak();
		
		Langilea l1 = pertsonakKargatu.langileaKargatu("12345678Z", "god", "2");
		String[][] hipotekak = taulaMetodoak.errefusatutakoHipotekak(l1, "Alameda de Recalde, 35");
		
		assertEquals("ES29 6789 0003  39  4765827453", hipotekak[0][0]);
		assertEquals("100000,00 €", hipotekak[0][1]);
		assertEquals("4 %", hipotekak[0][2]);
		assertEquals("15 urte", hipotekak[0][3]);
		assertEquals("errefusatuta", hipotekak[0][4]);
	}
	
	@Test
	public void testItxitakoHipotekak() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		TaulaMetodoak taulaMetodoak = new TaulaMetodoak();
		
		Langilea l1 = pertsonakKargatu.langileaKargatu("12345678Z", "god", "2");
		String[][] hipotekak = taulaMetodoak.itxitakoHipotekak(l1, "C. de Bertendona, 4");
		
		assertEquals("ES22 6789 0050  60  8253351724", hipotekak[0][0]);
		assertEquals("100000,00 €", hipotekak[0][1]);
		assertEquals("0,00 €", hipotekak[0][2]);
		assertEquals("4 %", hipotekak[0][3]);
		assertEquals("2008-04-04", hipotekak[0][4]);
		assertEquals("2023-05-04", hipotekak[0][5]);
		assertEquals("15 urte", hipotekak[0][6]);
		assertEquals("itxita", hipotekak[0][7]);
	}
	
	@Test
	public void testBezeroarenKontuak() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		TaulaMetodoak taulaMetodoak = new TaulaMetodoak();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		String[][] kontuak = taulaMetodoak.bezeroarenKontuak(b1, 1);
		
		assertEquals("ES70 2345 0002  73  4016972210", kontuak[0][0]);
		assertEquals("2451,98 €", kontuak[0][1]);
		assertEquals("aktiboa", kontuak[0][2]);
	}
	
	@Test
	public void testTransferentziakIkusi() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		TaulaMetodoak taulaMetodoak = new TaulaMetodoak();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		String[][] transferentziak = taulaMetodoak.transferentziakIkusi(b1,"ES7023450002734016972210");
		
		assertEquals("ES51 2345 0002  73  1634074159", transferentziak[0][0]);
		assertEquals("1230,00 €", transferentziak[0][1]);
		assertEquals("Zorionak", transferentziak[0][2]);
		assertEquals("2023-04-04", transferentziak[0][3]);
	}
	
	@Test
	public void testDiruSarrerakIkusi() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		TaulaMetodoak taulaMetodoak = new TaulaMetodoak();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		String[][] transferentziak = taulaMetodoak.diruSarrerakIkusi(b1,"ES7023450002734016972210");
		
		assertEquals("ES58 5432 0001  61  7546616053", transferentziak[0][0]);
		assertEquals("800,00 €", transferentziak[0][1]);
		assertEquals("oparia", transferentziak[0][2]);
		assertEquals("2023-04-04", transferentziak[0][3]);
	}
	
	@Test
	public void testBezeroGordeArray() {		
		String[][] erabiltzaileak = new String [0][4];
		String[][] bezero_array = TaulaMetodoak.bezeroGordeArray("13060345W", "7891578632487552", "1234", "kredito", erabiltzaileak);
		
		assertEquals("13060345W", bezero_array[0][0]);
		assertEquals("7891578632487552", bezero_array[0][1]);
		assertEquals("1234", bezero_array[0][2]);
		assertEquals("kredito", bezero_array[0][3]);
	}

}
