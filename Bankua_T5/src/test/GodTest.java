package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.God;

public class GodTest {

	@Test
	public void testGodConsGet() {
    	God g1 = new God("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
    	
    	// Getters
    	assertEquals("12345678A", g1.getNan());
		assertEquals("Juan", g1.getIzena());
		assertEquals("Perez", g1.getAbizena());
		assertEquals("10-21-2002", g1.getJaiotzeData());
		assertEquals("gizona", g1.getSexua());
		assertEquals("111222333", g1.getTelefonoa());
		assertEquals("12345678", g1.getPasahitza());
	}
	
	@Test
	public void testGodConsSet() {
    	God g1 = new God("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
    	
    	// Setters
    	g1.setNan("12345678B");
    	g1.setIzena("Laura");
    	g1.setAbizena("Sanchez");
    	g1.setJaiotzeData("08-19-2002");
    	g1.setSexua("emakumea");
    	g1.setTelefonoa("444555666");
    	g1.setPasahitza("87654321");
    	
    	assertEquals("12345678B", g1.getNan());
		assertEquals("Laura", g1.getIzena());
		assertEquals("Sanchez", g1.getAbizena());
		assertEquals("08-19-2002", g1.getJaiotzeData());
		assertEquals("emakumea", g1.getSexua());
		assertEquals("444555666", g1.getTelefonoa());
		assertEquals("87654321", g1.getPasahitza());
	}
	
}
