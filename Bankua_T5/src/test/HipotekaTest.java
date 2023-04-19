package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.Hipoteka;
import model.Hipoteka.Egoera;

public class HipotekaTest {

	@Test
	public void testHipotekaConsGet() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 12);
		cal1.set(Calendar.MONTH, 3);
		cal1.set(Calendar.YEAR, 2010);
		Date hasieraData = cal1.getTime();
		

		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.DAY_OF_MONTH, 10);
		cal2.set(Calendar.MONTH, 6);
		cal2.set(Calendar.YEAR, 2020);
		Date amaieraData = cal2.getTime();
		
		Hipoteka h1 = new Hipoteka (1, 100000, 50000, 10, hasieraData, amaieraData);
		
		// Getters
		assertEquals(1, h1.getIdHipoteka());
		assertEquals(100000, h1.getKantitatea(), 0.01);
		assertEquals(50000, h1.getOrdaindutakoa(), 0.01);
		assertEquals(hasieraData, h1.getHasieraData());
		assertEquals(amaieraData, h1.getAmaieraData());
		assertEquals(null, h1.getEgoera());
	}
	
	@Test
	public void testHipotekaSet() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 12);
		cal1.set(Calendar.MONTH, 3);
		cal1.set(Calendar.YEAR, 2010);
		Date hasieraData = cal1.getTime();
		

		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.DAY_OF_MONTH, 10);
		cal2.set(Calendar.MONTH, 6);
		cal2.set(Calendar.YEAR, 2020);
		Date amaieraData = cal2.getTime();
		
		Hipoteka h1 = new Hipoteka (1, 100000, 50000, 10, hasieraData, amaieraData);
		
		Calendar cal3 = Calendar.getInstance();
		cal3.set(Calendar.DAY_OF_MONTH, 10);
		cal3.set(Calendar.MONTH, 4);
		cal3.set(Calendar.YEAR, 2011);
		hasieraData = cal3.getTime();
		

		Calendar cal4 = Calendar.getInstance();
		cal4.set(Calendar.DAY_OF_MONTH, 18);
		cal4.set(Calendar.MONTH, 3);
		cal4.set(Calendar.YEAR, 2022);
		amaieraData = cal4.getTime();
		
		// Setters
		h1.setIdHipoteka(2);
		h1.setKantitatea(2000000);
		h1.setOrdaindutakoa(1000000);
		h1.setHasieraData(hasieraData);
		h1.setAmaieraData(amaieraData);
		h1.setEgoera(Egoera.errefusatuta);
		
		assertEquals(2, h1.getIdHipoteka());
		assertEquals(2000000, h1.getKantitatea(), 0.01);
		assertEquals(1000000, h1.getOrdaindutakoa(), 0.01);
		assertEquals(hasieraData, h1.getHasieraData());
		assertEquals(amaieraData, h1.getAmaieraData());
		assertEquals(Egoera.errefusatuta, h1.getEgoera());
	}
	
	@Test
	public void testHipotekaToString() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 12);
		cal1.set(Calendar.MONTH, 3);
		cal1.set(Calendar.YEAR, 2010);
		Date hasieraData = cal1.getTime();
		

		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.DAY_OF_MONTH, 10);
		cal2.set(Calendar.MONTH, 6);
		cal2.set(Calendar.YEAR, 2020);
		Date amaieraData = cal2.getTime();
		
		Hipoteka h1 = new Hipoteka (1, 100000, 50000, 10, hasieraData, amaieraData);
		
		// ToString
		assertEquals("Hipoteka idHipoteka=" + h1.getIdHipoteka() + ", kantitatea=" + h1.getKantitatea() + ", ordaindutakoa=" + h1.getOrdaindutakoa()
				+ ", komisioa=" + h1.getKomisioa() + ", hasieraData=" + h1.getHasieraData() + ", amaieraData=" + h1.getAmaieraData()
				+ ", egoera=" + h1.getEgoera(), h1.toString());
	}
	
	@Test
	public void testHipotekaEquals() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 12);
		cal1.set(Calendar.MONTH, 3);
		cal1.set(Calendar.YEAR, 2010);
		Date hasieraData = cal1.getTime();
		

		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.DAY_OF_MONTH, 10);
		cal2.set(Calendar.MONTH, 6);
		cal2.set(Calendar.YEAR, 2020);
		Date amaieraData = cal2.getTime();
		
		Hipoteka h1 = new Hipoteka (1, 100000, 50000, 10, hasieraData, amaieraData);
		Hipoteka h2 = new Hipoteka (1, 100000, 50000, 10, hasieraData, amaieraData);
		
		// Equals
		assertTrue(h1.equals(h2));
	}

}
