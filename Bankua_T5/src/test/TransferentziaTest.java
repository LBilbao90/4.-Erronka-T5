package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Transferentzia;

public class TransferentziaTest {

	@Test
	public void testTransferentziaConsGet() {
		Transferentzia t1 = new Transferentzia (100, "01-20-1999", "proba", "ES1245784512346751245124");
		
		// Getters
		assertEquals(100, t1.getKantitatea(), 0.01);
		assertEquals("01-20-1999", t1.getTransferentziaData());
		assertEquals("proba", t1.getKotzeptua());
		assertEquals("ES1245784512346751245124", t1.getJasotzailea());
		assertEquals(2, t1.getKomisioa(), 0.01);
	}
	
	@Test
	public void testTransferentziaSet() {
		Transferentzia t1 = new Transferentzia (100, "01-20-1999", "proba", "ES1245784512346751245124");
		
		// Setters
		t1.setKantitatea(10);
		t1.setTransferentziaData("08-17-2023");
		t1.setKotzeptua("proba2");
		t1.setJasotzailea("ES1245784512346751247816");
		
		assertEquals(10, t1.getKantitatea(), 0.01);
		assertEquals("08-17-2023", t1.getTransferentziaData());
		assertEquals("proba2", t1.getKotzeptua());
		assertEquals("ES1245784512346751247816", t1.getJasotzailea());
		assertEquals(2, t1.getKomisioa(), 0.01);
	}
	
	@Test
	public void testTransferentziaToString() {
		Transferentzia t1 = new Transferentzia (100, "01-20-1999", "proba", "ES1245784512346751245124");
		
		// ToString
		assertEquals("Transferentzia kantitatea=" + t1.getKantitatea()
				+ ", transferentziaData=" + t1.getTransferentziaData() + ", kotzeptua=" + t1.getKotzeptua() + ", jasotzailea="
				+ t1.getJasotzailea() + ", komisioa=" + t1.getKomisioa(), t1.toString());
	}
	
	@Test
	public void testTransferentziaEquals() {
		Transferentzia t1 = new Transferentzia (100, "01-20-1999", "proba", "ES1245784512346751245124");
		Transferentzia t2 = new Transferentzia (100, "01-20-1999", "proba", "ES1245784512346751245124");
		Transferentzia t3 = new Transferentzia ();
		
		// Equals
		assertTrue(t1.equals(t2));
		assertFalse(t3.equals(null));
	}

}
