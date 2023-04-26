package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Zuzendaria;

public class ZuzendariaTest {

	@Test
	 public void testZuzendariaConsGet() {
		Zuzendaria z1 = new Zuzendaria("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678", "Zuzendaria");
		
		// Getters
		assertEquals("12345678A", z1.getNan());
		assertEquals("Juan", z1.getIzena());
		assertEquals("Perez", z1.getAbizena());
		assertEquals("10-21-2002", z1.getJaiotzeData());
		assertEquals("gizona", z1.getSexua());
		assertEquals("111222333", z1.getTelefonoa());
		assertEquals("12345678", z1.getPasahitza());
		assertEquals("Zuzendaria", z1.getLanpostu());
	}
	
	@Test
	 public void testZuzendariaSet() {
		Zuzendaria z1 = new Zuzendaria("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678", "Zuzendaria");
		
		// Setters
		z1.setNan("12345678B");
		z1.setIzena("Laura");
		z1.setAbizena("Sanchez");
		z1.setJaiotzeData("08-19-2002");
		z1.setSexua("emakumea");
		z1.setTelefonoa("444555666");
		z1.setPasahitza("87654321");
		z1.setLanpostu("Gerentea");
		
		assertEquals("12345678B", z1.getNan());
		assertEquals("Laura", z1.getIzena());
		assertEquals("Sanchez", z1.getAbizena());
		assertEquals("08-19-2002", z1.getJaiotzeData());
		assertEquals("emakumea", z1.getSexua());
		assertEquals("444555666", z1.getTelefonoa());
		assertEquals("87654321", z1.getPasahitza());
		assertEquals("Gerentea", z1.getLanpostu());
	}
	
	@Test
	 public void testZuzendariaToString() {
		Zuzendaria z1 = new Zuzendaria("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678", "Zuzendaria");
		
		// ToString
		assertEquals("Pertsona nan=" + z1.getNan() + ", izena=" + z1.getIzena() + ", abizena=" + z1.getAbizena() + ", jaiotzeData=" + z1.getJaiotzeData()
				+ ", sexua=" + z1.getSexua() + ", telefonoa=" + z1.getTelefonoa() + ", pasahitza=" + z1.getPasahitza() + " Lanpostu=" + z1.getLanpostu(), z1.toString());
	}
	
	@Test
	public void testZuzendariaEquals() {
		Zuzendaria z1 = new Zuzendaria("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678", "Zuzendaria");
		Zuzendaria z2 = new Zuzendaria("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678", "Zuzendaria");
		Zuzendaria z3 = new Zuzendaria();
		
		// ToString
		assertTrue(z1.equals(z2));
		assertFalse(z3.equals(null));
	}
}
