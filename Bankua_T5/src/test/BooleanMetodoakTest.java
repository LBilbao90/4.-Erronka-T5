package test;

import static org.junit.Assert.*;

import org.junit.Test;

import controlador.ArrayMetodoak;
import controlador.BooleanMetodoak;
import controlador.PertsonakKargatu;
import model.Bezeroa;

public class BooleanMetodoakTest {

	@Test
	public void testEntitateBalidatu() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		ArrayMetodoak arrayMetodoak = new ArrayMetodoak();
		BooleanMetodoak booleanMetodoak = new BooleanMetodoak();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		String[] entitateak = arrayMetodoak.bezeroarenEntitateak(b1);
		
		
		assertTrue(booleanMetodoak.entitateBalidatu(entitateak, "3"));
	}
	
	@Test
	public void testNanbalidatu() {
		BooleanMetodoak booleanMetodoak = new BooleanMetodoak();
		
		assertTrue(booleanMetodoak.nanBalidatu("78950146R"));
	}
	
	@Test
	public void testDiruBalidatuTrue() {
		assertTrue(BooleanMetodoak.diruBalidatu("100,12"));
	}
	
	@Test
	public void testDiruBalidatuFalse() {
		assertFalse(BooleanMetodoak.diruBalidatu("a"));
	}
	
}
