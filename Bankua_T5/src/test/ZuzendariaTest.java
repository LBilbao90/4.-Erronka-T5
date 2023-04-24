package test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import model.Pertsona.Generoa;
import model.Zuzendaria;

public class ZuzendariaTest {

	@Test
	 public void testZuzendariaConsGet() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
		Zuzendaria z1 = new Zuzendaria("12345678A", "Juan", "Perez", jaiotzeData, Generoa.gizona, "111222333", "12345678", "0111");
		
		// Getters
		assertEquals("12345678A", z1.getNan());
		assertEquals("Juan", z1.getIzena());
		assertEquals("Perez", z1.getAbizena());
		assertEquals(jaiotzeData, z1.getJaiotzeData());
		assertEquals(Generoa.gizona, z1.getSexua());
		assertEquals("111222333", z1.getTelefonoa());
		assertEquals("12345678", z1.getPasahitza());
		assertEquals("0111", z1.getSukurtsala());
	}
	
	@Test
	 public void testZuzendariaSet() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
		Zuzendaria z1 = new Zuzendaria("12345678A", "Juan", "Perez", jaiotzeData, Generoa.gizona, "111222333", "12345678", "0111");
		
		// Setters
		z1.setNan("12345678B");
		z1.setIzena("Laura");
		z1.setAbizena("Sanchez");
		z1.setJaiotzeData(jaiotzeData);
		z1.setSexua(Generoa.emakumea);
		z1.setTelefonoa("444555666");
		z1.setPasahitza("87654321");
		z1.setSukurtsala("0548");
		
		assertEquals("12345678B", z1.getNan());
		assertEquals("Laura", z1.getIzena());
		assertEquals("Sanchez", z1.getAbizena());
		assertEquals(jaiotzeData, z1.getJaiotzeData());
		assertEquals(Generoa.emakumea, z1.getSexua());
		assertEquals("444555666", z1.getTelefonoa());
		assertEquals("87654321", z1.getPasahitza());
		assertEquals("0548", z1.getSukurtsala());
	}
	
	@Test
	 public void testZuzendariaToString() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
		Zuzendaria z1 = new Zuzendaria("12345678A", "Juan", "Perez", jaiotzeData, Generoa.gizona, "111222333", "12345678", "0111");
		
		// ToString
		assertEquals("Pertsona nan=" + z1.getNan() + ", izena=" + z1.getIzena() + ", abizena=" + z1.getAbizena() + ", jaiotzeData=" + jaiotzeData
				+ ", sexua=" + z1.getSexua() + ", telefonoa=" + z1.getTelefonoa() + ", pasahitza=" + z1.getPasahitza() + " Sukurtsala=" + z1.getSukurtsala(), z1.toString());
	}
	
	@Test
	public void testZuzendariaEquals() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
		Zuzendaria z1 = new Zuzendaria("12345678A", "Juan", "Perez", jaiotzeData, Generoa.gizona, "111222333", "12345678", "0111");
		Zuzendaria z2 = new Zuzendaria("12345678A", "Juan", "Perez", jaiotzeData, Generoa.gizona, "111222333", "12345678", "0111");
		Zuzendaria z3 = new Zuzendaria(null, null, null, null, null, null, null, null);
		
		// ToString
		assertTrue(z1.equals(z2));
		assertFalse(z3.equals(null));
	}
}
