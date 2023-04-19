package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.Pertsona;
import model.Txartela;
import model.Pertsona.Generoa;
import model.Txartela.Mota;

class TxartelaTest {

	@Test
	void testTxartelaConsGet() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
		Pertsona p1 = new Pertsona ("12345678A", "Juan", "Perez", jaiotzeData,Generoa.gizona, "111222333", "12345678");
		
		ArrayList<Pertsona> pertsonak= new ArrayList<Pertsona>();
		
		Txartela t1 = new Txartela(123145677894,455,Mota.debito, ,pertsonak);
	}

}
