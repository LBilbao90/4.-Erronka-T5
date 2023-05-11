package test;

import static org.junit.Assert.*;

import org.junit.Test;

import controlador.PertsonakKargatu;
import model.Hipoteka;
import model.Langilea;

public class HipotekaTest {

	@Test
	public void testHipotekaConsGet() {		
		Hipoteka h1 = new Hipoteka (100000, 50000, "10", "03-12-2010", "06-10-2020", "eskatuta", "10 urte");
		
		// Getters
		assertEquals(100000, h1.getKantitatea(), 0.01);
		assertEquals(50000, h1.getOrdaindutakoa(), 0.01);
		assertEquals("03-12-2010", h1.getHasieraData());
		assertEquals("06-10-2020", h1.getAmaieraData());
		assertEquals("eskatuta", h1.getEgoera());
		assertEquals("10 urte", h1.getEpeMuga());
	}
	
	@Test
	public void testHipotekaSet() {		
		Hipoteka h1 = new Hipoteka (100000, 50000, "10", "03-12-2010", "06-10-2020", "eskatuta", "10 urte");
		
		// Setters
		h1.setKantitatea(2000000);
		h1.setOrdaindutakoa(1000000);
		h1.setHasieraData("04-10-2011");
		h1.setAmaieraData("03-18-2022");
		h1.setEgoera("errefusatuta");
		h1.setEpeMuga("11 urte");
		
		assertEquals(2000000, h1.getKantitatea(), 0.01);
		assertEquals(1000000, h1.getOrdaindutakoa(), 0.01);
		assertEquals("04-10-2011", h1.getHasieraData());
		assertEquals("03-18-2022", h1.getAmaieraData());
		assertEquals("errefusatuta", h1.getEgoera());
		assertEquals("11 urte", h1.getEpeMuga());
	}
	
	@Test
	public void testHipotekaToString() {
		Hipoteka h1 = new Hipoteka (100000, 50000, "10", "03-12-2010", "06-10-2020", "eskatuta", "10 urte");
		
		// ToString
		assertEquals("Hipoteka kantitatea=" + h1.getKantitatea() + ", ordaindutakoa=" + h1.getOrdaindutakoa()
				+ ", komisioa=" + h1.getKomisioa() + ", hasieraData=" + h1.getHasieraData() + ", amaieraData=" + h1.getAmaieraData()
				+ ", egoera=" + h1.getEgoera() + ", epeMuga="+ h1.getEpeMuga(), h1.toString());
	}
	
	@Test
	public void testHipotekaEquals() {
		Hipoteka h1 = new Hipoteka (100000, 50000, "10", "03-12-2010", "06-10-2020", "eskatuta", "10 urte");
		Hipoteka h2 = new Hipoteka (100000, 50000, "10", "03-12-2010", "06-10-2020", "eskatuta", "10 urte");
		Hipoteka h3 = new Hipoteka ();
		
		// Equals
		assertTrue(h1.equals(h2));
		assertFalse(h3.equals(null));
	}
	
	@Test
	public void testHipotekaErrefusatu() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		Langilea l1 = pertsonakKargatu.langileaKargatu("12345678Z", "god", "1234");
		Hipoteka h1 = new Hipoteka (100000, 50000, "10", "03-12-2010", "06-10-2020", "eskatuta", "10 urte");
		h1.hipotekaErrefusatu(l1, "Santutxu, Santutxu Kalea, 27", "ES9323450111313252003900");
		
		for(int i=0;i<l1.getSukurtsalak().size();i++) {
			if(l1.getSukurtsalak().get(i).getKokalekua().equals("Santutxu, Santutxu Kalea, 27")) {
				for(int j=0;j<l1.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
					if(l1.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban().equals("ES9323450111313252003900")) {
						assertEquals("errefusatuta", l1.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEgoera());
					}
				}
			}
		}
	}
	
	@Test
	public void testHipotekaOnartu() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		Langilea l1 = pertsonakKargatu.langileaKargatu("12345678Z", "god", "1234");
		Hipoteka h1 = new Hipoteka (100000, 50000, "10", "03-12-2010", "06-10-2020", "eskatuta", "10 urte");
		h1.hipotekaOnartu(l1, "Santutxu, Santutxu Kalea, 27", "ES9323450111313252003900");
		
		for(int i=0;i<l1.getSukurtsalak().size();i++) {
			if(l1.getSukurtsalak().get(i).getKokalekua().equals("Santutxu, Santutxu Kalea, 27")) {
				for(int j=0;j<l1.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
					if(l1.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban().equals("ES9323450111313252003900")) {
						assertEquals("onartuta", l1.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().getEgoera());
					}
				}
			}
		}
	}

}
