package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Bezeroa;

public class BezeroaTest {

	@Test
	public void testBezeroaConsGet() {
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
		// Getters
		assertEquals("12345678A", b1.getNan());
		assertEquals("Juan", b1.getIzena());
		assertEquals("Perez", b1.getAbizena());
		assertEquals("10-21-2002", b1.getJaiotzeData());
		assertEquals("gizona", b1.getSexua());
		assertEquals("111222333", b1.getTelefonoa());
		assertEquals("12345678", b1.getPasahitza());
	}
	
	@Test
	public void testBezeroaSet() {
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
		// Setters
		b1.setNan("12345678B");
		b1.setIzena("Laura");
		b1.setAbizena("Sanchez");
		b1.setJaiotzeData("08-19-2002");
		b1.setSexua("emakumea");
		b1.setTelefonoa("444555666");
		b1.setPasahitza("87654321");
		
		assertEquals("12345678B", b1.getNan());
		assertEquals("Laura", b1.getIzena());
		assertEquals("Sanchez", b1.getAbizena());
		assertEquals("08-19-2002", b1.getJaiotzeData());
		assertEquals("emakumea", b1.getSexua());
		assertEquals("444555666", b1.getTelefonoa());
		assertEquals("87654321", b1.getPasahitza());
	}
	
	@Test
	public void testBezeroaAdinaKalkulatu() {
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
		// AdinaKalkulatu
		assertEquals(20, b1.adinaKalkulatu());
	}

}
