package test;

import static org.junit.Assert.*;

import org.junit.Test;

import controlador.ArrayMetodoak;
import controlador.DatuBaseSelect;
import controlador.PertsonakKargatu;
import model.Bezeroa;
import model.Langilea;

public class ArrayMetodoakTest {

	@Test
	public void testBezeroarenEntitateak() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		ArrayMetodoak arrayMetodoak = new ArrayMetodoak();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		String[] entitateak = arrayMetodoak.bezeroarenEntitateak(b1);
		
		assertEquals("3", entitateak[0]);
	}
	
	@Test
	public void testLangilearenEntitateak() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		ArrayMetodoak arrayMetodoak = new ArrayMetodoak();
		
		Langilea l1 = pertsonakKargatu.langileaKargatu("79003399D", "zuzendaria", "1234");
		String[] entitateak = arrayMetodoak.langilearenEntitateak(l1);
		
		assertEquals("BBK", entitateak[0]);
	}
	
	@Test
	public void testLangilearenSukurtsalak() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		ArrayMetodoak arrayMetodoak = new ArrayMetodoak();
		
		Langilea l1 = pertsonakKargatu.langileaKargatu("79003399D", "zuzendaria", "1234");
		String[] sukurtsalak = arrayMetodoak.langilearenSukurtsalak(l1, "BBK");
		
		assertEquals("Santutxu, Santutxu Kalea, 27", sukurtsalak[0]);
	}
	
	@Test
	public void testLangileKontuInfo() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		ArrayMetodoak arrayMetodoak = new ArrayMetodoak();
		
		Langilea l1 = pertsonakKargatu.langileaKargatu("79003399D", "zuzendaria", "1234");
		String[] kontua = arrayMetodoak.langileKontuInfo(l1, "ES9323450111313252003900", "Santutxu, Santutxu Kalea, 27");
		
		assertEquals("ES93 2345 0111  31  3252003900", kontua[0]);
		assertEquals("Aingeru Siranaula", kontua[1]);
		assertEquals("20046,09 €", kontua[2]);
		assertEquals("aktiboa", kontua[3]);
		assertEquals("2000,00", kontua[4]);
	}
	
	@Test
	public void testUrteakBete() {
		String[] urteak = ArrayMetodoak.urteakBete();
		
		assertEquals("2003", urteak[0]);
	}
	
	@Test
	public void testhilakBete() {
		String[] hilak = ArrayMetodoak.hilakBete();
		
		assertEquals("1", hilak[0]);
	}
	
	@Test
	public void testEgunakBete() {
		String[] egunak1 = ArrayMetodoak.egunakBete(1);
		String[] egunak2 = ArrayMetodoak.egunakBete(2);
		String[] egunak3 = ArrayMetodoak.egunakBete(11);
		
		assertEquals("1", egunak1[0]);
		assertEquals("1", egunak2[0]);
		assertEquals("1", egunak3[0]);
	}
	
	@Test
	public void testHipotekaEstatus() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		ArrayMetodoak arrayMetodoak = new ArrayMetodoak();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		String[] hipoteka = arrayMetodoak.hipotekaEstatus(b1,"ES9323450111313252003900");
		
		assertEquals("80000.0", hipoteka[0]);
		assertEquals("2023-04-04", hipoteka[1]);
		assertEquals("- - -", hipoteka[2]);
		assertEquals("3.8", hipoteka[3]);
		assertEquals("0.0", hipoteka[4]);
		assertEquals("eskatuta", hipoteka[5]);

	}
	
	@Test
	public void testBezeroInfo() {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		ArrayMetodoak arrayMetodoak = new ArrayMetodoak();
		
		String[][] bezero_zerrenda = datuBaseSelect.bezeroZerrendaKargatu();
		String[] bezeroInfo = arrayMetodoak.bezeroInfo(bezero_zerrenda, "54821599H");
		
		assertEquals("54821599H", bezeroInfo[0]);
		assertEquals("Iker", bezeroInfo[1]);
		assertEquals("Zuluaga", bezeroInfo[2]);
		assertEquals("1994-08-04", bezeroInfo[3]);
		assertEquals("gizona", bezeroInfo[4]);
		assertEquals("111222333", bezeroInfo[5]);
		assertEquals("1234", bezeroInfo[6]);
		assertEquals("blokeatuta", bezeroInfo[7]);
	}
	
	@Test
	public void testLangileInfo() {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		ArrayMetodoak arrayMetodoak = new ArrayMetodoak();
		
		String[][] langile_zerrenda = datuBaseSelect.langileZerrendaKargatu();
		String[] langileInfo = arrayMetodoak.langileInfo(langile_zerrenda, "79003399D");
		
		assertEquals("79003399D", langileInfo[0]);
		assertEquals("Ibai", langileInfo[1]);
		assertEquals("Alvarez", langileInfo[2]);
		assertEquals("2000-10-21", langileInfo[3]);
		assertEquals("gizona", langileInfo[4]);
		assertEquals("111222333", langileInfo[5]);
		assertEquals("1234", langileInfo[6]);
		assertEquals("zuzendaria", langileInfo[7]);
	}
	
}
