package test;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import model.Pertsona;
import model.Pertsona.Generoa;

public class PertsonaTest {

	@Test
	public void testPertsonaConsGet() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = null;
		Pertsona p1 = new Pertsona ("12345678A", "Juan", "Perez", jaiotzeData,Generoa.gizona, "111222333", "12345678");
		
		// Getters
		assertEquals("12345678A",p1.getNan());
		assertEquals("Juan",p1.getIzena());
		assertEquals("Perez",p1.getAbizena());
		assertEquals("gizona",p1.getSexua());
		assertEquals("111222333",p1.getTelefonoa());
		assertEquals("12345678",p1.getPasahitza());
	}

}
