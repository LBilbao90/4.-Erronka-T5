package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import model.DiruSarrera;
import model.Hipoteka;
import model.KontuBankarioa;
import model.KontuBankarioa.Egoera;
import model.Transferentzia;

public class KontuBankarioaTest {

	@Test
	public void testKontuBankarioaConsGet() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 20);
		cal1.set(Calendar.MONTH, 0);
		cal1.set(Calendar.YEAR, 2003);
		Date sorreraData = cal1.getTime();
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.DAY_OF_MONTH, 19);
		cal2.set(Calendar.MONTH, 3);
		cal2.set(Calendar.YEAR, 2023);
		Date sarreraData = cal2.getTime();
		
		DiruSarrera ds1 = new DiruSarrera(1, 100, sarreraData, "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Calendar cal3 = Calendar.getInstance();
		cal3.set(Calendar.DAY_OF_MONTH, 23);
		cal3.set(Calendar.MONTH, 4);
		cal3.set(Calendar.YEAR, 2008);
		Date transferentziaData = cal3.getTime();
		
		Transferentzia t1 = new Transferentzia (1, 100, transferentziaData, "proba", "ES1245784512346751245124", 10);
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
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
		assertEquals(2000, kb1.getSaldoa(), 0.01);
		assertEquals(100, kb1.getHilekoLimitea(), 0.01);
		assertEquals(sorreraData, kb1.getSorreraData());
		assertEquals(Egoera.aktiboa, kb1.getEgoera());
		assertEquals(1, kb1.getDiruSarrerak().get(0).getIdSarrera());
		assertEquals(100, kb1.getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals(sarreraData, kb1.getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera", kb1.getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1245784512346751245124", kb1.getDiruSarrerak().get(0).getIgortzailea());
		assertEquals(1, kb1.getTransferentziak().get(0).getIdTransferentzia());
		assertEquals(100, kb1.getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals(transferentziaData, kb1.getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba", kb1.getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245784512346751245124", kb1.getTransferentziak().get(0).getJasotzailea());
		assertEquals(10, kb1.getTransferentziak().get(0).getKomisioa(), 0.01);
		assertEquals(1, kb1.getHipotekak().get(0).getIdHipoteka());
		assertEquals(100000, kb1.getHipotekak().get(0).getKantitatea(), 0.01);
		assertEquals(50000, kb1.getHipotekak().get(0).getOrdaindutakoa(), 0.01);
		assertEquals(10, kb1.getHipotekak().get(0).getKomisioa(), 0.01);
		assertEquals(hasieraData, kb1.getHipotekak().get(0).getHasieraData());
		assertEquals(amaieraData, kb1.getHipotekak().get(0).getAmaieraData());
	}
	
	@Test
	public void testKontuBankarioaSet() {		
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 20);
		cal1.set(Calendar.MONTH, 0);
		cal1.set(Calendar.YEAR, 2003);
		Date sorreraData = cal1.getTime();
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.DAY_OF_MONTH, 19);
		cal2.set(Calendar.MONTH, 3);
		cal2.set(Calendar.YEAR, 2023);
		Date sarreraData = cal2.getTime();
		
		DiruSarrera ds1 = new DiruSarrera(1, 100, sarreraData, "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Calendar cal3 = Calendar.getInstance();
		cal3.set(Calendar.DAY_OF_MONTH, 23);
		cal3.set(Calendar.MONTH, 4);
		cal3.set(Calendar.YEAR, 2008);
		Date transferentziaData = cal3.getTime();
		
		Transferentzia t1 = new Transferentzia (1, 100, transferentziaData, "proba", "ES1245784512346751245124", 10);
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
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
		
		cal1.set(Calendar.DAY_OF_MONTH, 22);
		cal1.set(Calendar.MONTH, 8);
		cal1.set(Calendar.YEAR, 2004);
		sorreraData = cal1.getTime();
		
		cal2.set(Calendar.DAY_OF_MONTH, 10);
		cal2.set(Calendar.MONTH, 9);
		cal2.set(Calendar.YEAR, 2023);
		sarreraData = cal2.getTime();
		
		DiruSarrera ds2 = new DiruSarrera(2, 200, sarreraData, "sarrera2", "ES1297514512626751245124");
		
		ArrayList<DiruSarrera> diruSarrerak2 = new ArrayList<DiruSarrera> ();
		diruSarrerak2.add(ds2);
		
		cal3.set(Calendar.DAY_OF_MONTH, 17);
		cal3.set(Calendar.MONTH, 7);
		cal3.set(Calendar.YEAR, 2010);
		transferentziaData = cal3.getTime();
		
		Transferentzia t2 = new Transferentzia (3, 300, transferentziaData, "proba2", "ES1245709142346751245124", 10);
		
		ArrayList<Transferentzia> transferentziak2 = new ArrayList<Transferentzia>();
		transferentziak2.add(t2);
		
		cal4.set(Calendar.DAY_OF_MONTH, 15);
		cal4.set(Calendar.MONTH, 6);
		cal4.set(Calendar.YEAR, 2012);
		hasieraData = cal4.getTime();
		
		cal5.set(Calendar.DAY_OF_MONTH, 11);
		cal5.set(Calendar.MONTH, 7);
		cal5.set(Calendar.YEAR, 2022);
		amaieraData = cal5.getTime();
		
		Hipoteka h2 = new Hipoteka (2, 200000, 70000, 10, hasieraData, amaieraData);
		
		ArrayList<Hipoteka> hipotekak2 = new ArrayList<Hipoteka> ();
		hipotekak2.add(h2);
		
		// Setters
		kb1.setIban("ES1576197348527531958261");
		kb1.setSaldoa(3000);
		kb1.setHilekoLimitea(200);
		kb1.setSorreraData(sorreraData);
		kb1.setEgoera(Egoera.izoztuta);
		kb1.setDiruSarrerak(diruSarrerak2);
		kb1.setTransferentziak(transferentziak2);
		kb1.setHipotekak(hipotekak2);
		
		assertEquals("ES1576197348527531958261", kb1.getIban());
		assertEquals(3000, kb1.getSaldoa(), 0.01);
		assertEquals(200, kb1.getHilekoLimitea(), 0.01);
		assertEquals(sorreraData, kb1.getSorreraData());
		assertEquals(Egoera.izoztuta, kb1.getEgoera());
		assertEquals(2, diruSarrerak2.get(0).getIdSarrera());
		assertEquals(200, diruSarrerak2.get(0).getKantitatea(), 0.01);
		assertEquals(sarreraData, diruSarrerak2.get(0).getSarreraData());
		assertEquals("sarrera2", diruSarrerak2.get(0).getKontzeptua());
		assertEquals("ES1297514512626751245124", diruSarrerak2.get(0).getIgortzailea());
		assertEquals(3, transferentziak2.get(0).getIdTransferentzia());
		assertEquals(300, transferentziak2.get(0).getKantitatea(), 0.01);
		assertEquals(transferentziaData, transferentziak2.get(0).getTransferentziaData());
		assertEquals("proba2", transferentziak2.get(0).getKotzeptua());
		assertEquals("ES1245709142346751245124", transferentziak2.get(0).getJasotzailea());
		assertEquals(10, transferentziak2.get(0).getKomisioa(), 0.01);
		assertEquals(2, hipotekak2.get(0).getIdHipoteka());
		assertEquals(200000, hipotekak2.get(0).getKantitatea(), 0.01);
		assertEquals(70000, hipotekak2.get(0).getOrdaindutakoa(), 0.01);
		assertEquals(10, hipotekak2.get(0).getKomisioa(), 0.01);
		assertEquals(hasieraData, hipotekak2.get(0).getHasieraData());
		assertEquals(amaieraData, hipotekak2.get(0).getAmaieraData());
	}
	
	@Test
	public void testKontuBankarioaToString() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 20);
		cal1.set(Calendar.MONTH, 0);
		cal1.set(Calendar.YEAR, 2003);
		Date sorreraData = cal1.getTime();
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.DAY_OF_MONTH, 19);
		cal2.set(Calendar.MONTH, 3);
		cal2.set(Calendar.YEAR, 2023);
		Date sarreraData = cal2.getTime();
		
		DiruSarrera ds1 = new DiruSarrera(1, 100, sarreraData, "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Calendar cal3 = Calendar.getInstance();
		cal3.set(Calendar.DAY_OF_MONTH, 23);
		cal3.set(Calendar.MONTH, 4);
		cal3.set(Calendar.YEAR, 2008);
		Date transferentziaData = cal3.getTime();
		
		Transferentzia t1 = new Transferentzia (1, 100, transferentziaData, "proba", "ES1245784512346751245124", 10);
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
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
		
		// ToString
		assertEquals("KontuBankarioa iban=" + kb1.getIban() + ", saldoa=" + kb1.getSaldoa() + ", hilekoLimitea=" + kb1.getHilekoLimitea()
				+ ", sorreraData=" + kb1.getSorreraData() + ", egoera=" + kb1.getEgoera() + ", diruSarrerak=" + kb1.getDiruSarrerak()
				+ ", transferentziak=" + kb1.getTransferentziak() + ", hipotekak=" + kb1.getHipotekak(), kb1.toString());
	}
	
	@Test
	public void testKontuBankarioaEquals() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.DAY_OF_MONTH, 20);
		cal1.set(Calendar.MONTH, 0);
		cal1.set(Calendar.YEAR, 2003);
		Date sorreraData = cal1.getTime();
		
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.DAY_OF_MONTH, 19);
		cal2.set(Calendar.MONTH, 3);
		cal2.set(Calendar.YEAR, 2023);
		Date sarreraData = cal2.getTime();
		
		DiruSarrera ds1 = new DiruSarrera(1, 100, sarreraData, "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Calendar cal3 = Calendar.getInstance();
		cal3.set(Calendar.DAY_OF_MONTH, 23);
		cal3.set(Calendar.MONTH, 4);
		cal3.set(Calendar.YEAR, 2008);
		Date transferentziaData = cal3.getTime();
		
		Transferentzia t1 = new Transferentzia (1, 100, transferentziaData, "proba", "ES1245784512346751245124", 10);
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
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
		KontuBankarioa kb2 = new KontuBankarioa("ES1576197348527531954865", 2000, 100, sorreraData, Egoera.aktiboa, diruSarrerak,transferentziak, hipotekak);
		KontuBankarioa kb3 = new KontuBankarioa();
		
		// ToString
		assertTrue(kb1.equals(kb2));
		assertFalse(kb3.equals(null));
	}

}
