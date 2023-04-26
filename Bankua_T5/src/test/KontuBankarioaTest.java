package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.DiruSarrera;
import model.Hipoteka;
import model.KontuBankario;
import model.Transferentzia;

public class KontuBankarioaTest {

	@Test
	public void testKontuBankarioaConsGet() {		
		DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Transferentzia t1 = new Transferentzia (100, "04-23-2008", "proba", "ES1245784512346751245124");
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
		Hipoteka h1 = new Hipoteka (100000, 50000, 10, "03-12-2010", "06-10-2020", "eskatuta");
		
		ArrayList<Hipoteka> hipotekak = new ArrayList<Hipoteka> ();
		hipotekak.add(h1);
		
		KontuBankario kb1 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "aktiboa", diruSarrerak,transferentziak, hipotekak);
		
		// Getters
		assertEquals("ES1576197348527531954865", kb1.getIban());
		assertEquals(2000, kb1.getSaldoa(), 0.01);
		assertEquals(100, kb1.getHilekoLimitea(), 0.01);
		assertEquals("01-20-2003", kb1.getSorreraData());
		assertEquals("aktiboa", kb1.getEgoera());
		assertEquals(100, kb1.getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("03-19-2023", kb1.getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera", kb1.getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1245784512346751245124", kb1.getDiruSarrerak().get(0).getIgortzailea());
		assertEquals(100, kb1.getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("04-23-2008", kb1.getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba", kb1.getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245784512346751245124", kb1.getTransferentziak().get(0).getJasotzailea());
		assertEquals(2, kb1.getTransferentziak().get(0).getKomisioa(), 0.01);
		assertEquals(100000, kb1.getHipotekak().get(0).getKantitatea(), 0.01);
		assertEquals(50000, kb1.getHipotekak().get(0).getOrdaindutakoa(), 0.01);
		assertEquals(10, kb1.getHipotekak().get(0).getKomisioa(), 0.01);
		assertEquals("03-12-2010", kb1.getHipotekak().get(0).getHasieraData());
		assertEquals("06-10-2020", kb1.getHipotekak().get(0).getAmaieraData());
		assertEquals("eskatuta", kb1.getHipotekak().get(0).getEgoera());
	}
	
	@Test
	public void testKontuBankarioaSet() {		
		DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Transferentzia t1 = new Transferentzia (100, "04-23-2008", "proba", "ES1245784512346751245124");
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
		Hipoteka h1 = new Hipoteka (100000, 50000, 10, "03-12-2010", "06-10-2020", "eskatuta");
		
		ArrayList<Hipoteka> hipotekak = new ArrayList<Hipoteka> ();
		hipotekak.add(h1);
		
		KontuBankario kb1 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "aktiboa", diruSarrerak,transferentziak, hipotekak);
		
		
		DiruSarrera ds2 = new DiruSarrera(200, "09-10-2003", "sarrera2", "ES1297514512626751245124");
		
		ArrayList<DiruSarrera> diruSarrerak2 = new ArrayList<DiruSarrera> ();
		diruSarrerak2.add(ds2);
		
		Transferentzia t2 = new Transferentzia (300, "08-17-2023", "proba2", "ES1245709142346751245124");
		
		ArrayList<Transferentzia> transferentziak2 = new ArrayList<Transferentzia>();
		transferentziak2.add(t2);
		
		Hipoteka h2 = new Hipoteka (200000, 70000, 10, "06-15-2012", "07-11-2022", "onartuta");
		
		ArrayList<Hipoteka> hipotekak2 = new ArrayList<Hipoteka> ();
		hipotekak2.add(h2);
		
		// Setters
		kb1.setIban("ES1576197348527531958261");
		kb1.setSaldoa(3000);
		kb1.setHilekoLimitea(200);
		kb1.setSorreraData("08-22-2004");
		kb1.setEgoera("izoztuta");
		kb1.setDiruSarrerak(diruSarrerak2);
		kb1.setTransferentziak(transferentziak2);
		kb1.setHipotekak(hipotekak2);
		
		assertEquals("ES1576197348527531958261", kb1.getIban());
		assertEquals(3000, kb1.getSaldoa(), 0.01);
		assertEquals(200, kb1.getHilekoLimitea(), 0.01);
		assertEquals("08-22-2004", kb1.getSorreraData());
		assertEquals("izoztuta", kb1.getEgoera());
		assertEquals(200, diruSarrerak2.get(0).getKantitatea(), 0.01);
		assertEquals("09-10-2003", diruSarrerak2.get(0).getSarreraData());
		assertEquals("sarrera2", diruSarrerak2.get(0).getKontzeptua());
		assertEquals("ES1297514512626751245124", diruSarrerak2.get(0).getIgortzailea());
		assertEquals(300, transferentziak2.get(0).getKantitatea(), 0.01);
		assertEquals("08-17-2023", transferentziak2.get(0).getTransferentziaData());
		assertEquals("proba2", transferentziak2.get(0).getKotzeptua());
		assertEquals("ES1245709142346751245124", transferentziak2.get(0).getJasotzailea());
		assertEquals(2, transferentziak2.get(0).getKomisioa(), 0.01);
		assertEquals(200000, hipotekak2.get(0).getKantitatea(), 0.01);
		assertEquals(70000, hipotekak2.get(0).getOrdaindutakoa(), 0.01);
		assertEquals(10, hipotekak2.get(0).getKomisioa(), 0.01);
		assertEquals("06-15-2012", hipotekak2.get(0).getHasieraData());
		assertEquals("07-11-2022", hipotekak2.get(0).getAmaieraData());
		assertEquals("onartuta", kb1.getHipotekak().get(0).getEgoera());
	}
	
	@Test
	public void testKontuBankarioaToString() {
		DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Transferentzia t1 = new Transferentzia (100, "04-23-2008", "proba", "ES1245784512346751245124");
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
		Hipoteka h1 = new Hipoteka (100000, 50000, 10, "03-12-2010", "06-10-2020", "eskatuta");
		
		ArrayList<Hipoteka> hipotekak = new ArrayList<Hipoteka> ();
		hipotekak.add(h1);
		
		KontuBankario kb1 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "Aktiboa", diruSarrerak,transferentziak, hipotekak);
		
		// ToString
		assertEquals("KontuBankarioa iban=" + kb1.getIban() + ", saldoa=" + kb1.getSaldoa() + ", hilekoLimitea=" + kb1.getHilekoLimitea()
				+ ", sorreraData=" + kb1.getSorreraData() + ", egoera=" + kb1.getEgoera() + ", diruSarrerak=" + kb1.getDiruSarrerak()
				+ ", transferentziak=" + kb1.getTransferentziak() + ", hipotekak=" + kb1.getHipotekak(), kb1.toString());
	}
	
	@Test
	public void testKontuBankarioaEquals() {
		DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Transferentzia t1 = new Transferentzia (100, "04-23-2008", "proba", "ES1245784512346751245124");
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
		Hipoteka h1 = new Hipoteka (100000, 50000, 10, "03-12-2010", "06-10-2020", "eskatuta");
		
		ArrayList<Hipoteka> hipotekak = new ArrayList<Hipoteka> ();
		hipotekak.add(h1);
		
		KontuBankario kb1 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "Aktiboa", diruSarrerak,transferentziak, hipotekak);
		KontuBankario kb2 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "Aktiboa", diruSarrerak,transferentziak, hipotekak);
		KontuBankario kb3 = new KontuBankario();
		
		// ToString
		assertTrue(kb1.equals(kb2));
		assertFalse(kb3.equals(null));
	}

}
