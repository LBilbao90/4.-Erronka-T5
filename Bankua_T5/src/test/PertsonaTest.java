package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.Pertsona;
import model.Pertsona.Generoa;

class PertsonaTest {

	@Test
	public void testPertsonaConsGet() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
		Pertsona p1 = new Pertsona ("12345678A", "Juan", "Perez", jaiotzeData,Generoa.gizona, "111222333", "12345678");
		
		// Getters
		assertEquals("12345678A",p1.getNan());
		assertEquals("Juan",p1.getIzena());
		assertEquals("Perez",p1.getAbizena());
		assertEquals(jaiotzeData,p1.getJaiotzeData());
		assertEquals(Generoa.gizona,p1.getSexua());
		assertEquals("111222333",p1.getTelefonoa());
		assertEquals("12345678",p1.getPasahitza());
	}
	
	@Test
	public void testPertsonaSet() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 20);
		cal1.set(Calendar.MONTH, 0);
		cal1.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal1.getTime();
		Pertsona p1 = new Pertsona ("12345678A", "Juan", "Perez", jaiotzeData,Generoa.gizona, "111222333", "12345678");
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.DAY_OF_MONTH, 15);
		cal2.set(Calendar.MONTH, 5);
		cal2.set(Calendar.YEAR, 2001);
		jaiotzeData = cal2.getTime();
		
		// Setters
		p1.setNan("12345678B");
		p1.setIzena("Laura");
		p1.setAbizena("Sanchez");
		p1.setJaiotzeData(jaiotzeData);
		p1.setSexua(Generoa.emakumea);
		p1.setTelefonoa("444555666");
		p1.setPasahitza("87654321");
		
		// Getters
		assertEquals("12345678B",p1.getNan());
		assertEquals("Laura",p1.getIzena());
		assertEquals("Sanchez",p1.getAbizena());
		assertEquals(jaiotzeData,p1.getJaiotzeData());
		assertEquals(Generoa.emakumea,p1.getSexua());
		assertEquals("444555666",p1.getTelefonoa());
		assertEquals("87654321",p1.getPasahitza());
	}
	
	@Test
	public void testPertsonatoString() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 20);
		cal1.set(Calendar.MONTH, 0);
		cal1.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal1.getTime();
		Pertsona p1 = new Pertsona ("12345678A", "Juan", "Perez", jaiotzeData,Generoa.gizona, "111222333", "12345678");
		
		// ToString
		assertEquals("Pertsona nan=" + p1.getNan() + ", izena=" + p1.getIzena() + ", abizena=" + p1.getAbizena() + ", jaiotzeData=" + jaiotzeData
				+ ", sexua=" + p1.getSexua() + ", telefonoa=" + p1.getTelefonoa() + ", pasahitza=" + p1.getPasahitza(), p1.toString());
	}
	
	@Test
	public void testPertsonaEquals() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 20);
		cal1.set(Calendar.MONTH, 0);
		cal1.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal1.getTime();
		Pertsona p1 = new Pertsona ("12345678A", "Juan", "Perez", jaiotzeData,Generoa.gizona, "111222333", "12345678");
		Pertsona p2 = new Pertsona ("12345678A", "Juan", "Perez", jaiotzeData,Generoa.gizona, "111222333", "12345678");
		
		// Equals
		assertTrue(p1.equals(p2));
	}
	
	// Methods
	
}
