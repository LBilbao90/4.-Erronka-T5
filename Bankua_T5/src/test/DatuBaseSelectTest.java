package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import controlador.DatuBaseSelect;
import model.EntitateBankario;
import model.SalbuespenaLogin;
import model.SalbuespenaLoginBlokeo;

public class DatuBaseSelectTest {
	
	@Test
	public void testBezeroaLoginAktiboa() throws SalbuespenaLoginBlokeo {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		try {
			assertTrue(datuBaseSelect.bezeroLogin("78950146R", "1234"));
		} catch (SalbuespenaLogin e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testBezeroaLoginBloqueatuta() throws SalbuespenaLoginBlokeo, SalbuespenaLogin {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		try {
			datuBaseSelect.bezeroLogin("54821599H", "1234");
		} catch (SalbuespenaLoginBlokeo e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testBezeroaLoginError() throws SalbuespenaLoginBlokeo {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		try {
			assertFalse(datuBaseSelect.bezeroLogin("78950146J", "1234"));
		} catch (SalbuespenaLogin e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testLangileLoginZuzendaria() throws SalbuespenaLoginBlokeo {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		try {
			assertEquals("zuzendaria", datuBaseSelect.langileLogin("78950146R", "1234"));
		} catch (SalbuespenaLogin e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testLangileLoginGerentea() throws SalbuespenaLoginBlokeo {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		try {
			assertEquals("gerentea", datuBaseSelect.langileLogin("90138299B", "1234"));
		} catch (SalbuespenaLogin e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testLangileLoginGod() throws SalbuespenaLoginBlokeo {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		try {
			assertEquals("god", datuBaseSelect.langileLogin("12345678Z", "1234"));
		} catch (SalbuespenaLogin e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testLangileLoginFalse() throws SalbuespenaLoginBlokeo {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		try {
			assertEquals("god", datuBaseSelect.langileLogin("12345678E", "1234"));
		} catch (SalbuespenaLogin e) {
			System.out.println(e);
		}
	}
	
	@Test
	public void testTransferentziaIbanBalidatu() {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		assertTrue(datuBaseSelect.transferentziaIbanBalidatu("ES7023450002734016972210"));
	}
	
	@Test
	public void testTransferentziaSaldoaBalidatu() {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		assertTrue(datuBaseSelect.transferentziaSaldoaBalidatu("100", "ES7023450002734016972210", "78950146R", "1234"));
	}
	
	@Test
	public void testSegurtasunKodeaBalidatu() {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		assertTrue(datuBaseSelect.segurtasunKodeaBalidatu("5678", "ES7023450002734016972210", "78950146R", "1234"));
	}
	
	@Test
	public void testIbanBalidatuTrue() {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		assertTrue(datuBaseSelect.ibanBalidatu("ES1598760011533136761560"));
	}
	
	@Test
	public void testIbanBalidatuFalse() {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		assertFalse(datuBaseSelect.ibanBalidatu("ES1598760011533136761561"));
	}
	
	@Test
	public void testTxartelIdBalidatuTrue() {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		assertTrue(datuBaseSelect.txartelIdBalidatu("8949421198213169"));
	}
	
	@Test
	public void testTxartelIdBalidatuFalse() {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		assertFalse(datuBaseSelect.txartelIdBalidatu("8949467198213169"));
	}
	
	@Test
	public void testBezeroExistituTrue() {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		assertTrue(datuBaseSelect.bezeroExistitu("78950146R"));
	}
	
	@Test
	public void testBezeroExistituFalse() {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		assertFalse(datuBaseSelect.bezeroExistitu("78950146J"));
	}
	
	@Test
	public void testBezeroZerrendaKargatu() {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		String[][] bezero_zerrenda = datuBaseSelect.bezeroZerrendaKargatu();
		
		assertEquals("54821599H", bezero_zerrenda[0][0]);
		assertEquals("Iker", bezero_zerrenda[0][1]);
		assertEquals("Zuluaga", bezero_zerrenda[0][2]);
		assertEquals("1994-08-04", bezero_zerrenda[0][3]);
		assertEquals("gizona", bezero_zerrenda[0][4]);
		assertEquals("111222333", bezero_zerrenda[0][5]);
		assertEquals("1234", bezero_zerrenda[0][6]);
		assertEquals("blokeatuta", bezero_zerrenda[0][7]);
	}
	
	@Test
	public void testLangileZerrendaKargatu() {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		String[][] langile_zerrenda = datuBaseSelect.langileZerrendaKargatu();
		
		assertEquals("79003399D", langile_zerrenda[0][0]);
		assertEquals("Ibai", langile_zerrenda[0][1]);
		assertEquals("Alvarez", langile_zerrenda[0][2]);
		assertEquals("2000-10-21", langile_zerrenda[0][3]);
		assertEquals("gizona", langile_zerrenda[0][4]);
		assertEquals("111222333", langile_zerrenda[0][5]);
		assertEquals("1234", langile_zerrenda[0][6]);
		assertEquals("zuzendaria", langile_zerrenda[0][7]);
	}
	
	@Test
	public void testBotoiakSortu() {
		DatuBaseSelect datuBaseSelect = new DatuBaseSelect();
		
		ArrayList<EntitateBankario> botoiakEntitate = datuBaseSelect.botoiakSortu();
		
		assertEquals("1", botoiakEntitate.get(0).getIdEntitatea());
		assertEquals("BBK", botoiakEntitate.get(0).getIzena());
		assertEquals("2345", botoiakEntitate.get(0).getEntitateZbk());
		assertEquals("218/253/128/45", botoiakEntitate.get(0).getBounds());
		assertEquals("src/res/bbk_logo.png", botoiakEntitate.get(0).getUrl());
	}

}
