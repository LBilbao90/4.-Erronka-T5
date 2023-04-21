package test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import model.Transferentzia;

public class TransferentziaTest {

	@Test
	public void testTransferentziaConsGet() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date transferentziaData = cal.getTime();
		Transferentzia t1 = new Transferentzia (1, 100, transferentziaData, "proba", "ES1245784512346751245124", 10);
		
		// Getters
		assertEquals(1, t1.getIdTransferentzia());
		assertEquals(100, t1.getKantitatea(), 0.01);
		assertEquals(transferentziaData, t1.getTransferentziaData());
		assertEquals("proba", t1.getKotzeptua());
		assertEquals("ES1245784512346751245124", t1.getJasotzailea());
		assertEquals(10, t1.getKomisioa(), 0.01);
	}
	
	@Test
	public void testTransferentziaSet() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 20);
		cal1.set(Calendar.MONTH, 0);
		cal1.set(Calendar.YEAR, 1999);
		Date transferentziaData = cal1.getTime();
		Transferentzia t1 = new Transferentzia (1, 100, transferentziaData, "proba", "ES1245784512346751245124", 10);
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.DAY_OF_MONTH, 17);
		cal2.set(Calendar.MONTH, 8);
		cal2.set(Calendar.YEAR, 2023);
		transferentziaData = cal2.getTime();
		
		// Setters
		t1.setIdTransferentzia(2);
		t1.setKantitatea(10);
		t1.setTransferentziaData(transferentziaData);
		t1.setKotzeptua("proba2");
		t1.setJasotzailea("ES1245784512346751247816");
		
		assertEquals(2, t1.getIdTransferentzia());
		assertEquals(10, t1.getKantitatea(), 0.01);
		assertEquals(transferentziaData, t1.getTransferentziaData());
		assertEquals("proba2", t1.getKotzeptua());
		assertEquals("ES1245784512346751247816", t1.getJasotzailea());
		assertEquals(10, t1.getKomisioa(), 0.01);
	}
	
	@Test
	public void testTransferentziaToString() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 20);
		cal1.set(Calendar.MONTH, 0);
		cal1.set(Calendar.YEAR, 1999);
		Date transferentziaData = cal1.getTime();
		Transferentzia t1 = new Transferentzia (1, 100, transferentziaData, "proba", "ES1245784512346751245124", 10);
		
		// ToString
		assertEquals("Transferentzia idTransferentzia=" + t1.getIdTransferentzia() + ", kantitatea=" + t1.getKantitatea()
				+ ", transferentziaData=" + t1.getTransferentziaData() + ", kotzeptua=" + t1.getKotzeptua() + ", jasotzailea="
				+ t1.getJasotzailea() + ", komisioa=" + t1.getKomisioa(), t1.toString());
	}
	
	@Test
	public void testTransferentziaEquals() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 20);
		cal1.set(Calendar.MONTH, 0);
		cal1.set(Calendar.YEAR, 1999);
		Date transferentziaData = cal1.getTime();
		Transferentzia t1 = new Transferentzia (1, 100, transferentziaData, "proba", "ES1245784512346751245124", 10);
		Transferentzia t2 = new Transferentzia (1, 100, transferentziaData, "proba", "ES1245784512346751245124", 10);
		
		// Equals
		assertTrue(t1.equals(t2));
	}

}
