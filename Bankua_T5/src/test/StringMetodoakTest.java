package test;

import static org.junit.Assert.*;

import org.junit.Test;

import controlador.PertsonakKargatu;
import controlador.StringMetodoak;
import model.Bezeroa;

public class StringMetodoakTest {

	@Test
	public void testKontrolDigitoak() {
		String kontrolZenbakiak = StringMetodoak.kontrolDigitoak("2345", "0111", "1238546781");
		
		assertEquals("37", kontrolZenbakiak);
	}

	@Test
	public void testHipotekaDut() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		StringMetodoak stringMetodoak = new StringMetodoak();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		Bezeroa b2 = pertsonakKargatu.bezeroaKargatu("79439437J");
		Bezeroa b3 = pertsonakKargatu.bezeroaKargatu("78950146R");
		Bezeroa b4 = pertsonakKargatu.bezeroaKargatu("55929848N");
		
		assertEquals("eskatuta", stringMetodoak.hipotekaDut(b1, "ES9323450111313252003900"));
		assertEquals("errefusatuta", stringMetodoak.hipotekaDut(b2, "ES2967890003394765827453"));
		assertEquals("onartuta", stringMetodoak.hipotekaDut(b3, "ES7023450002734016972210"));
		assertEquals("itxita", stringMetodoak.hipotekaDut(b4, "ES2267890050608253351724"));
	}
	
}
