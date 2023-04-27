package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import controlador.Metodoak;
import model.Bezeroa;
import model.EntitateBankario;

public class MetodoakTest {

	@Test
	public void testBezeroakKargatu() {
		Metodoak metodoak = new Metodoak();
		Bezeroa b1 = metodoak.bezeroaKargatu("12345678A");
		
		assertEquals("12345678A", b1.getNan());
		assertEquals("Aingeru", b1.getIzena());
		assertEquals("Siranaula", b1.getAbizena());
		//assertEquals("10-21-2002", b1.getJaiotzeData());
		assertEquals("gizona", b1.getSexua());
		assertEquals("111222333", b1.getTelefonoa());
		assertEquals("12345678", b1.getPasahitza());
	}
	
	@Test
	public void testBezeroaLogin() {
		Metodoak metodoak = new Metodoak();
		
		assertTrue(metodoak.bezeroLogin("12345678A", "12345678"));
	}
	
	@Test
	public void testLangileLogin() {
		Metodoak metodoak = new Metodoak();
		
		assertEquals("zuzendaria", metodoak.langileLogin("12345678B", "1234"));
	}
	
	@Test
	public void testbotoiakSortu() {
		Metodoak metodoak = new Metodoak();
		ArrayList<EntitateBankario> botoiakEntitate = metodoak.botoiakSortu();
		
		assertEquals("1", botoiakEntitate.get(0).getIdEntitatea());
		assertEquals("BBK", botoiakEntitate.get(0).getIzena());
		assertEquals("ES972345", botoiakEntitate.get(0).getEntitateZbk());
		assertEquals("218/253/128/45", botoiakEntitate.get(0).getBounds());
		assertEquals("src/res/bbk_logo.png", botoiakEntitate.get(0).getUrl());
	}

}
