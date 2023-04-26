package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.DiruSarrera;

public class DiruSarreraTest {

	@Test
	public void testDiruSarreraConsGet() {
		DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		
		// Getters
		assertEquals(100, ds1.getKantitatea(), 0.01);
		assertEquals("03-19-2023", ds1.getSarreraData());
		assertEquals("sarrera", ds1.getKontzeptua());
		assertEquals("ES1245784512346751245124", ds1.getIgortzailea());
	}
	
	@Test
	public void testDiruSarreraSet() {
		DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		
		// Setters
		ds1.setKantitatea(10);
		ds1.setSarreraData("03-20-2023");
		ds1.setKontzeptua("proba");
		ds1.setIgortzailea("ES1245784764913671245124");
		
		assertEquals(10, ds1.getKantitatea(), 0.01);
		assertEquals("03-20-2023", ds1.getSarreraData());
		assertEquals("proba", ds1.getKontzeptua());
		assertEquals("ES1245784764913671245124", ds1.getIgortzailea());
	}
	
	@Test
	public void testDiruSarreraToString() {
		DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		
		// ToString
		assertEquals("DiruSarrera kantitatea=" + ds1.getKantitatea() + ", sarreraData=" + ds1.getSarreraData()
				+ ", kontzeptua=" + ds1.getKontzeptua() + ", igortzailea=" + ds1.getIgortzailea(), ds1.toString());
	}
	
	@Test
	public void testDiruSarreraEquals() {
		DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		DiruSarrera ds2 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		DiruSarrera ds3 = new DiruSarrera();
		
		// Equals
		assertTrue(ds1.equals(ds2));
		assertFalse(ds3.equals(null));
	}

}
