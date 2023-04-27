package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.EntitateBankario;

public class EntitateBankarioaTest {

	@Test
	public void testEntitaBankarioaConsGet() {		
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", "0/0/932/130", "src-res-bbk_logo.png");
		
		// Getters
		assertEquals("BBK", eb1.getIzena());
		assertEquals("1",eb1.getIdEntitatea());
		assertEquals("ES25", eb1.getEntitateZbk());
		assertEquals("0/0/932/130", eb1.getBounds());
		assertEquals("src-res-bbk_logo.png", eb1.getUrl());
	}
	
	@Test
	public void testEntitaBankarioaSet() {
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", "src-res-bbk_logo.png", "0/0/932/130");
		
		// Setters
		eb1.setIzena("BBVA");
		eb1.setIdEntitatea("2");
		eb1.setEntitateZbk("ES36");
		eb1.setBounds("092/567/900/810");
		eb1.setUrl("bbva.png");
		
		assertEquals("BBVA", eb1.getIzena());
		assertEquals("2",eb1.getIdEntitatea());
		assertEquals("ES36", eb1.getEntitateZbk());
		assertEquals("092/567/900/810", eb1.getBounds());
		assertEquals("bbva.png", eb1.getUrl());
	}
	
	@Test
	public void testEntitaBankarioaToString() {
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", "src-res-bbk_logo.png", "0/0/932/130");
		
		// ToString
		assertEquals("EntitateBankarioa izena=" + eb1.getIzena() + ", idEntitatea=" + eb1.getIdEntitatea() + ", entitateZbk=" + eb1.getEntitateZbk(), eb1.toString());
	}
	
	@Test
	public void testEntitaBankarioaEquals() {
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", "src-res-bbk_logo.png", "0/0/932/130");
		EntitateBankario eb2 = new EntitateBankario ("BBK", "1", "ES25", "src-res-bbk_logo.png", "0/0/932/130");
		EntitateBankario eb3 = new EntitateBankario ();
		
		// Equals
		assertTrue(eb1.equals(eb2));
		assertFalse(eb3.equals(null));
	}

}
