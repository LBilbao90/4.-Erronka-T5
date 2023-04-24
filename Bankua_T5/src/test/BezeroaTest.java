package test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import model.Bezeroa;
import model.Pertsona;
import model.Pertsona.Generoa;

public class BezeroaTest {

	@Test
	public void testBezeroaConsGet() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
		Pertsona b1 = new Bezeroa ("12345678A", "Juan", "Perez", jaiotzeData, Generoa.gizona, "111222333", "12345678");
		
		// Getters
		assertEquals("12345678A", b1.getNan());
		assertEquals("Juan", b1.getIzena());
		assertEquals("Perez", b1.getAbizena());
		assertEquals(jaiotzeData, b1.getJaiotzeData());
		assertEquals(Generoa.gizona, b1.getSexua());
		assertEquals("111222333", b1.getTelefonoa());
		assertEquals("12345678", b1.getPasahitza());
	}
	
	@Test
	public void testBezeroaSet() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
		Pertsona b1 = new Bezeroa ("12345678A", "Juan", "Perez", jaiotzeData, Generoa.gizona, "111222333", "12345678");

		cal.set(Calendar.DAY_OF_MONTH, 15);
		cal.set(Calendar.MONTH, 6);
		cal.set(Calendar.YEAR, 2001);
		jaiotzeData = cal.getTime();
		
		// Setters
		b1.setNan("12345678B");
		b1.setIzena("Laura");
		b1.setAbizena("Sanchez");
		b1.setJaiotzeData(jaiotzeData);
		b1.setSexua(Generoa.emakumea);
		b1.setTelefonoa("444555666");
		b1.setPasahitza("87654321");
		
		assertEquals("12345678B", b1.getNan());
		assertEquals("Laura", b1.getIzena());
		assertEquals("Sanchez", b1.getAbizena());
		assertEquals(jaiotzeData, b1.getJaiotzeData());
		assertEquals(Generoa.emakumea, b1.getSexua());
		assertEquals("444555666", b1.getTelefonoa());
		assertEquals("87654321", b1.getPasahitza());
	}

}
