package test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import model.God;
import model.Pertsona.Generoa;

public class GodTest {

	@Test
	public void testGodConsGet() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
    	God g1 = new God("12345678A", "Juan", "Perez", jaiotzeData, Generoa.gizona, "111222333", "12345678");
    	
    	// Getters
    	assertEquals("12345678A", g1.getNan());
		assertEquals("Juan", g1.getIzena());
		assertEquals("Perez", g1.getAbizena());
		assertEquals(jaiotzeData, g1.getJaiotzeData());
		assertEquals(Generoa.gizona, g1.getSexua());
		assertEquals("111222333", g1.getTelefonoa());
		assertEquals("12345678", g1.getPasahitza());
	}
	
	@Test
	public void testGodConsSet() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
    	God g1 = new God("12345678A", "Juan", "Perez", jaiotzeData, Generoa.gizona, "111222333", "12345678");
    	
    	// Setters
    	// Setters
    	g1.setNan("12345678B");
    	g1.setIzena("Laura");
    	g1.setAbizena("Sanchez");
    	g1.setJaiotzeData(jaiotzeData);
    	g1.setSexua(Generoa.emakumea);
    	g1.setTelefonoa("444555666");
    	g1.setPasahitza("87654321");
    	
    	assertEquals("12345678B", g1.getNan());
		assertEquals("Laura", g1.getIzena());
		assertEquals("Sanchez", g1.getAbizena());
		assertEquals(jaiotzeData, g1.getJaiotzeData());
		assertEquals(Generoa.emakumea, g1.getSexua());
		assertEquals("444555666", g1.getTelefonoa());
		assertEquals("87654321", g1.getPasahitza());
	}
	
}
