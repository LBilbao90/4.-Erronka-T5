package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.DiruSarrera;
import model.Hipoteka;
import model.KontuBankarioa;
import model.Transferentzia;
import model.KontuBankarioa.Egoera;

public class KontuBankarioaTest {

	@Test
	public void testKontuBankarioaConsGet() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 20);
		cal1.set(Calendar.MONTH, 0);
		cal1.set(Calendar.YEAR, 2003);
		Date sorreraData = cal1.getTime();
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.DAY_OF_MONTH, 23);
		cal2.set(Calendar.MONTH, 4);
		cal2.set(Calendar.YEAR, 2008);
		Date transferentziaData = cal2.getTime();
		
		Transferentzia t1 = new Transferentzia (1, 100, transferentziaData, "proba", "ES1245784512346751245124", 10);
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
		Calendar cal3 = Calendar.getInstance();
		cal3.set(Calendar.DAY_OF_MONTH, 19);
		cal3.set(Calendar.MONTH, 3);
		cal3.set(Calendar.YEAR, 2023);
		Date sarreraData = cal3.getTime();
		
		DiruSarrera ds1 = new DiruSarrera(1, 100, sarreraData, "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Calendar cal4 = Calendar.getInstance();
		cal4.set(Calendar.DAY_OF_MONTH, 12);
		cal4.set(Calendar.MONTH, 3);
		cal4.set(Calendar.YEAR, 2010);
		Date hasieraData = cal4.getTime();
		

		Calendar cal5 = Calendar.getInstance();
		cal5.set(Calendar.DAY_OF_MONTH, 10);
		cal5.set(Calendar.MONTH, 6);
		cal5.set(Calendar.YEAR, 2020);
		Date amaieraData = cal5.getTime();
		
		Hipoteka h1 = new Hipoteka (1, 100000, 50000, 10, hasieraData, amaieraData);
		
		ArrayList<Hipoteka> hipotekak = new ArrayList<Hipoteka> ();
		hipotekak.add(h1);
		
		KontuBankarioa kb1 = new KontuBankarioa("ES1576197348527531954865", 2000, 100, sorreraData, Egoera.aktiboa, diruSarrerak,transferentziak, hipotekak);
		
		// Getters
		assertEquals("ES1576197348527531954865", kb1.getIban());
		assertEquals(2000, kb1.getSaldoa());
		assertEquals(100, kb1.getHilekoLimitea());
		assertEquals(sorreraData, kb1.getSorreraData());
		assertEquals(Egoera.aktiboa, kb1.getEgoera());
		assertEquals(1, diruSarrerak.get(0).getIdSarrera());
		assertEquals(100, diruSarrerak.get(0).getKantitatea());
		assertEquals(sarreraData, diruSarrerak.get(0).getSarreraData());
		assertEquals("sarrera", diruSarrerak.get(0).getKontzeptua());
		assertEquals("ES1245784512346751245124", diruSarrerak.get(0).getIgortzailea());
		assertEquals(1, transferentziak.get(0).getIdTransferentzia());
		assertEquals(100, transferentziak.get(0).getKantitatea());
		assertEquals(transferentziaData, transferentziak.get(0).getTransferentziaData());
		assertEquals("proba", transferentziak.get(0).getKotzeptua());
		assertEquals("ES1245784512346751245124", transferentziak.get(0).getJasotzailea());
		assertEquals(10, transferentziak.get(0).getKomisioa());
		assertEquals(1, hipotekak.get(0).getIdHipoteka());
		assertEquals(100000, hipotekak.get(0).getKantitatea());
		assertEquals(50000, hipotekak.get(0).getOrdaindutakoa());
		assertEquals(10, hipotekak.get(0).getKomisioa());
		assertEquals(hasieraData, hipotekak.get(0).getHasieraData());
		assertEquals(amaieraData, hipotekak.get(0).getAmaieraData());
	}
	
	@Test
	public void testKontuBankarioaSet() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 20);
		cal1.set(Calendar.MONTH, 0);
		cal1.set(Calendar.YEAR, 2003);
		Date sorreraData = cal1.getTime();
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.DAY_OF_MONTH, 23);
		cal2.set(Calendar.MONTH, 4);
		cal2.set(Calendar.YEAR, 2008);
		Date transferentziaData = cal2.getTime();
		
		Transferentzia t1 = new Transferentzia (1, 100, transferentziaData, "proba", "ES1245784512346751245124", 10);
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
		Calendar cal3 = Calendar.getInstance();
		cal3.set(Calendar.DAY_OF_MONTH, 19);
		cal3.set(Calendar.MONTH, 3);
		cal3.set(Calendar.YEAR, 2023);
		Date sarreraData = cal3.getTime();
		
		DiruSarrera ds1 = new DiruSarrera(1, 100, sarreraData, "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Calendar cal4 = Calendar.getInstance();
		cal4.set(Calendar.DAY_OF_MONTH, 12);
		cal4.set(Calendar.MONTH, 3);
		cal4.set(Calendar.YEAR, 2010);
		Date hasieraData = cal4.getTime();
		

		Calendar cal5 = Calendar.getInstance();
		cal5.set(Calendar.DAY_OF_MONTH, 10);
		cal5.set(Calendar.MONTH, 6);
		cal5.set(Calendar.YEAR, 2020);
		Date amaieraData = cal5.getTime();
		
		Hipoteka h1 = new Hipoteka (1, 100000, 50000, 10, hasieraData, amaieraData);
		
		ArrayList<Hipoteka> hipotekak = new ArrayList<Hipoteka> ();
		hipotekak.add(h1);
		
		KontuBankarioa kb1 = new KontuBankarioa("ES1576197348527531954865", 2000, 100, sorreraData, Egoera.aktiboa, diruSarrerak,transferentziak, hipotekak);
		
		// Getters
		assertEquals("ES1576197348527531954865", kb1.getIban());
		assertEquals(2000, kb1.getSaldoa());
		assertEquals(100, kb1.getHilekoLimitea());
		assertEquals(sorreraData, kb1.getSorreraData());
		assertEquals(Egoera.aktiboa, kb1.getEgoera());
		assertEquals(1, diruSarrerak.get(0).getIdSarrera());
		assertEquals(100, diruSarrerak.get(0).getKantitatea());
		assertEquals(sarreraData, diruSarrerak.get(0).getSarreraData());
		assertEquals("sarrera", diruSarrerak.get(0).getKontzeptua());
		assertEquals("ES1245784512346751245124", diruSarrerak.get(0).getIgortzailea());
		assertEquals(1, transferentziak.get(0).getIdTransferentzia());
		assertEquals(100, transferentziak.get(0).getKantitatea());
		assertEquals(transferentziaData, transferentziak.get(0).getTransferentziaData());
		assertEquals("proba", transferentziak.get(0).getKotzeptua());
		assertEquals("ES1245784512346751245124", transferentziak.get(0).getJasotzailea());
		assertEquals(10, transferentziak.get(0).getKomisioa());
		assertEquals(1, hipotekak.get(0).getIdHipoteka());
		assertEquals(100000, hipotekak.get(0).getKantitatea());
		assertEquals(50000, hipotekak.get(0).getOrdaindutakoa());
		assertEquals(10, hipotekak.get(0).getKomisioa());
		assertEquals(hasieraData, hipotekak.get(0).getHasieraData());
		assertEquals(amaieraData, hipotekak.get(0).getAmaieraData());
	}
}
