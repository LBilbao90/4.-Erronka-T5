package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.Langilea;
import model.Pertsona.Generoa;

class LangileaTest {
	
	@Test
	void testLangileaConsGet() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
		Langilea l1 = new Langilea ("12345678A", "Juan", "Perez", jaiotzeData,Generoa.gizona, "111222333", "12345678", "4455");
		
		// Getters
		assertEquals("12345678A", l1.getNan());
		assertEquals("Juan", l1.getIzena());
		assertEquals("Perez", l1.getAbizena());
		assertEquals(jaiotzeData, l1.getJaiotzeData());
		assertEquals(Generoa.gizona, l1.getSexua());
		assertEquals("111222333", l1.getTelefonoa());
		assertEquals("12345678", l1.getPasahitza());
		assertEquals("4455", l1.getSukurtzala());
	}
	
	@Test
	void testLangileaSet() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
		Langilea l1 = new Langilea ("12345678A", "Juan", "Perez", jaiotzeData,Generoa.gizona, "111222333", "12345678", "4455");
		
		
		cal.set(Calendar.DAY_OF_MONTH, 16);
		cal.set(Calendar.MONTH, 3);
		cal.set(Calendar.YEAR, 2001);
		jaiotzeData = cal.getTime();
		
		//Setters
		l1.setNan("12345678B");
		l1.setIzena("Laura");
		l1.setAbizena("Sanchez");
		l1.setJaiotzeData(jaiotzeData);
		l1.setSexua(Generoa.emakumea);
		l1.setTelefonoa("444555666");
		l1.setPasahitza("87654321");
		l1.setSukurtzala("6677");
		
		assertEquals("12345678B", l1.getNan());
		assertEquals("Laura", l1.getIzena());
		assertEquals("Sanchez", l1.getAbizena());
		assertEquals(jaiotzeData, l1.getJaiotzeData());
		assertEquals(Generoa.emakumea, l1.getSexua());
		assertEquals("444555666", l1.getTelefonoa());
		assertEquals("87654321", l1.getPasahitza());
		assertEquals("6677", l1.getSukurtzala());
	}
	
	@Test
	void testLangileaToString() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
		Langilea l1 = new Langilea ("12345678A", "Juan", "Perez", jaiotzeData,Generoa.gizona, "111222333", "12345678", "4455");
		
		
		assertEquals("Pertsona nan=" + l1.getNan() + ", izena=" + l1.getIzena() + ", abizena=" + l1.getAbizena() + ", jaiotzeData=" + jaiotzeData
				+ ", sexua=" + l1.getSexua() + ", telefonoa=" + l1.getTelefonoa() + ", pasahitza=" + l1.getPasahitza() + " Sukurtzala=" + l1.getSukurtzala(), l1.toString());
	}

}
