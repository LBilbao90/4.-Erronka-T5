package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.Langilea;
import model.Pertsona.Generoa;

class LangileaTest {

	@Test
	void testLangileaToString() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
		Langilea l1 = new Langilea ("12345678A", "Juan", "Perez", jaiotzeData,Generoa.gizona, "111222333", "12345678", "4455");
		
		
		assertEquals("Pertsona nan=" + l1.getNan() + ", izena=" + l1.getIzena() + ", abizena=" + l1.getAbizena() + ", jaiotzeData=" + jaiotzeData
				+ ", sexua=" + l1.getSexua() + ", telefonoa=" + l1.getTelefonoa() + ", pasahitza=" + l1.getPasahitza() + " Sukurtzala=" + l1.getSukurtzala(), l1.toString());
	}

}
