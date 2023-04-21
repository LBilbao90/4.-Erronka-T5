package test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import model.DiruSarrera;

public class DiruSarreraTest {

	@Test
	public void testDiruSarreraConsGet() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 19);
		cal.set(Calendar.MONTH, 3);
		cal.set(Calendar.YEAR, 2023);
		Date sarreraData = cal.getTime();
		
		DiruSarrera ds1 = new DiruSarrera(1, 100, sarreraData, "sarrera", "ES1245784512346751245124");
		
		// Getters
		assertEquals(1, ds1.getIdSarrera());
		assertEquals(100, ds1.getKantitatea(), 0.01);
		assertEquals(sarreraData, ds1.getSarreraData());
		assertEquals("sarrera", ds1.getKontzeptua());
		assertEquals("ES1245784512346751245124", ds1.getIgortzailea());
	}
	
	@Test
	public void testDiruSarreraSet() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 19);
		cal1.set(Calendar.MONTH, 3);
		cal1.set(Calendar.YEAR, 2023);
		Date sarreraData = cal1.getTime();
		
		DiruSarrera ds1 = new DiruSarrera(1, 100, sarreraData, "sarrera", "ES1245784512346751245124");
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.DAY_OF_MONTH, 20);
		cal2.set(Calendar.MONTH, 3);
		cal2.set(Calendar.YEAR, 2023);
		sarreraData = cal2.getTime();
		
		// Setters
		ds1.setIdSarrera(2);
		ds1.setKantitatea(10);
		ds1.setSarreraData(sarreraData);
		ds1.setKontzeptua("proba");
		ds1.setIgortzailea("ES1245784764913671245124");
		
		assertEquals(2, ds1.getIdSarrera());
		assertEquals(10, ds1.getKantitatea(), 0.01);
		assertEquals(sarreraData, ds1.getSarreraData());
		assertEquals("proba", ds1.getKontzeptua());
		assertEquals("ES1245784764913671245124", ds1.getIgortzailea());
	}
	
	@Test
	public void testDiruSarreraToString() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 19);
		cal1.set(Calendar.MONTH, 3);
		cal1.set(Calendar.YEAR, 2023);
		Date sarreraData = cal1.getTime();
		
		DiruSarrera ds1 = new DiruSarrera(1, 100, sarreraData, "sarrera", "ES1245784512346751245124");
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.DAY_OF_MONTH, 20);
		cal2.set(Calendar.MONTH, 3);
		cal2.set(Calendar.YEAR, 2023);
		sarreraData = cal2.getTime();
		
		// ToString
		assertEquals("DiruSarrera idSarrera=" + ds1.getIdSarrera() + ", kantitatea=" + ds1.getKantitatea() + ", sarreraData=" + ds1.getSarreraData()
				+ ", kontzeptua=" + ds1.getKontzeptua() + ", igortzailea=" + ds1.getIgortzailea(), ds1.toString());
	}
	
	@Test
	public void testDiruSarreraEquals() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 19);
		cal1.set(Calendar.MONTH, 3);
		cal1.set(Calendar.YEAR, 2023);
		Date sarreraData = cal1.getTime();
		
		DiruSarrera ds1 = new DiruSarrera(1, 100, sarreraData, "sarrera", "ES1245784512346751245124");
		DiruSarrera ds2 = new DiruSarrera(1, 100, sarreraData, "sarrera", "ES1245784512346751245124");
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.DAY_OF_MONTH, 20);
		cal2.set(Calendar.MONTH, 3);
		cal2.set(Calendar.YEAR, 2023);
		sarreraData = cal2.getTime();
		
		// Equals
		assertTrue(ds1.equals(ds2));
	}

}
