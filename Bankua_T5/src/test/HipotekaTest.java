package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Hipoteka;

public class HipotekaTest {

	@Test
	public void testHipotekaConsGet() {		
		Hipoteka h1 = new Hipoteka (100000, 50000, 10, "03-12-2010", "06-10-2020", "eskatuta");
		
		// Getters
		assertEquals(100000, h1.getKantitatea(), 0.01);
		assertEquals(50000, h1.getOrdaindutakoa(), 0.01);
		assertEquals("03-12-2010", h1.getHasieraData());
		assertEquals("06-10-2020", h1.getAmaieraData());
		assertEquals("eskatuta", h1.getEgoera());
	}
	
	@Test
	public void testHipotekaSet() {		
		Hipoteka h1 = new Hipoteka (100000, 50000, 10, "03-12-2010", "06-10-2020", "eskatuta");
		
		// Setters
		h1.setKantitatea(2000000);
		h1.setOrdaindutakoa(1000000);
		h1.setHasieraData("04-10-2011");
		h1.setAmaieraData("03-18-2022");
		h1.setEgoera("errefusatuta");
		
		assertEquals(2000000, h1.getKantitatea(), 0.01);
		assertEquals(1000000, h1.getOrdaindutakoa(), 0.01);
		assertEquals("04-10-2011", h1.getHasieraData());
		assertEquals("03-18-2022", h1.getAmaieraData());
		assertEquals("errefusatuta", h1.getEgoera());
	}
	
	@Test
	public void testHipotekaToString() {
		Hipoteka h1 = new Hipoteka (100000, 50000, 10, "03-12-2010", "06-10-2020", "eskatuta");
		
		// ToString
		assertEquals("Hipoteka kantitatea=" + h1.getKantitatea() + ", ordaindutakoa=" + h1.getOrdaindutakoa()
				+ ", komisioa=" + h1.getKomisioa() + ", hasieraData=" + h1.getHasieraData() + ", amaieraData=" + h1.getAmaieraData()
				+ ", egoera=" + h1.getEgoera(), h1.toString());
	}
	
	@Test
	public void testHipotekaEquals() {
		Hipoteka h1 = new Hipoteka (100000, 50000, 10, "03-12-2010", "06-10-2020", "eskatuta");
		Hipoteka h2 = new Hipoteka (100000, 50000, 10, "03-12-2010", "06-10-2020", "eskatuta");
		Hipoteka h3 = new Hipoteka ();
		
		// Equals
		assertTrue(h1.equals(h2));
		assertFalse(h3.equals(null));
	}

}
