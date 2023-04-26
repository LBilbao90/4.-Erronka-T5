package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.DiruSarrera;
import model.Hipoteka;
import model.KontuBankario;
import model.Langilea;
import model.Sukurtsala;
import model.Transferentzia;
import model.Zuzendaria;

public class SukurtsalaTest {

	@Test
	public void testSukurtsalaConsGet() {
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
		
		ArrayList<KontuBankario> KontuBankarioak = new ArrayList<KontuBankario>();
		KontuBankarioak.add(kb1);
		
		Zuzendaria z1 = new Zuzendaria("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678", "Zuzendaria");
		
		ArrayList<Langilea> langileak = new ArrayList<Langilea>();
		langileak.add(z1);
		
		Sukurtsala s1 = new Sukurtsala(1, "13","Gallarraga kalea", KontuBankarioak, langileak);
		
		// Getters
		assertEquals(1,s1.getIdSukurtsala());
		assertEquals("13", s1.getKodSukurtsala());
		assertEquals("Gallarraga kalea",s1.getKokalekua());
		assertEquals("ES1576197348527531954865", s1.getKontuBankarioak().get(0).getIban());
		assertEquals(2000, s1.getKontuBankarioak().get(0).getSaldoa(), 0.01);
		assertEquals(100, s1.getKontuBankarioak().get(0).getHilekoLimitea(), 0.01);
		assertEquals("01-20-2003", s1.getKontuBankarioak().get(0).getSorreraData());
		assertEquals("aktiboa", s1.getKontuBankarioak().get(0).getEgoera());
		assertEquals(100, s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("03-19-2023", s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera", s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1245784512346751245124", s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIgortzailea());
		assertEquals(100, s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("04-23-2008", s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba", s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245784512346751245124", s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getJasotzailea());
		assertEquals(2, s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getKomisioa(), 0.01);
		assertEquals(100000, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getKantitatea(), 0.01);
		assertEquals(50000, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getOrdaindutakoa(), 0.01);
		assertEquals(10, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getKomisioa(), 0.01);
		assertEquals("03-12-2010", s1.getKontuBankarioak().get(0).getHipotekak().get(0).getHasieraData());
		assertEquals("06-10-2020", s1.getKontuBankarioak().get(0).getHipotekak().get(0).getAmaieraData());
		assertEquals("eskatuta", s1.getKontuBankarioak().get(0).getHipotekak().get(0).getEgoera());
		assertEquals("12345678A", s1.getLangileak().get(0).getNan());
		assertEquals("Juan", s1.getLangileak().get(0).getIzena());
		assertEquals("Perez", s1.getLangileak().get(0).getAbizena());
		assertEquals("10-21-2002", s1.getLangileak().get(0).getJaiotzeData());
		assertEquals("gizona", s1.getLangileak().get(0).getSexua());
		assertEquals("111222333", s1.getLangileak().get(0).getTelefonoa());
		assertEquals("12345678", s1.getLangileak().get(0).getPasahitza());
		assertEquals("Zuzendaria", s1.getLangileak().get(0).getLanpostu());
	}
	
	@Test
	public void testSukurtsalaSet() {
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
		
		ArrayList<KontuBankario> KontuBankarioak = new ArrayList<KontuBankario>();
		KontuBankarioak.add(kb1);
		
		Zuzendaria z1 = new Zuzendaria("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678", "Zuzendaria");
		
		ArrayList<Langilea> langileak = new ArrayList<Langilea>();
		langileak.add(z1);
		
		Sukurtsala s1 = new Sukurtsala(1, "13","Gallarraga kalea", KontuBankarioak, langileak);
		
		
		DiruSarrera ds2 = new DiruSarrera(200, "09-10-2003", "sarrera2", "ES1297514512626751245124");
		
		ArrayList<DiruSarrera> diruSarrerak2 = new ArrayList<DiruSarrera> ();
		diruSarrerak2.add(ds2);
		
		Transferentzia t2 = new Transferentzia (300, "08-17-2023", "proba2", "ES1245709142346751245124");
		
		ArrayList<Transferentzia> transferentziak2 = new ArrayList<Transferentzia>();
		transferentziak2.add(t2);
		
		Hipoteka h2 = new Hipoteka (200000, 70000, 10, "06-15-2012", "07-11-2022", "onartuta");
		
		ArrayList<Hipoteka> hipotekak2 = new ArrayList<Hipoteka> ();
		hipotekak2.add(h2);
		
		KontuBankario kb2 = new KontuBankario("ES1576197348527531958261", 3000, 200, "08-22-2004", "izoztuta", diruSarrerak2,transferentziak2, hipotekak2);
		
		ArrayList<KontuBankario> KontuBankarioak2 = new ArrayList<KontuBankario>();
		KontuBankarioak2.add(kb2);
		
		Zuzendaria z2 = new Zuzendaria("12345678B", "Laura", "Sanchez", "08-19-2002", "emakumea", "111222333", "87654321", "Gerentea");
		
		ArrayList<Langilea> langileak2 = new ArrayList<Langilea>();
		langileak2.add(z2);
		
		// Setters
		s1.setIdSukurtsala(2);
		s1.setKodSukurtsala("11");
		s1.setKokalekua("Autonomia kalea");
		s1.setKontuBankarioak(KontuBankarioak2);
		s1.setLangileak(langileak2);
		
		assertEquals(2,s1.getIdSukurtsala());
		assertEquals("11", s1.getKodSukurtsala());
		assertEquals("Autonomia kalea",s1.getKokalekua());
		assertEquals("ES1576197348527531958261", s1.getKontuBankarioak().get(0).getIban());
		assertEquals(3000, s1.getKontuBankarioak().get(0).getSaldoa(), 0.01);
		assertEquals(200, s1.getKontuBankarioak().get(0).getHilekoLimitea(), 0.01);
		assertEquals("08-22-2004", s1.getKontuBankarioak().get(0).getSorreraData());
		assertEquals("izoztuta", s1.getKontuBankarioak().get(0).getEgoera());
		assertEquals(200, s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("09-10-2003", s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera2", s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1297514512626751245124", s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIgortzailea());
		assertEquals(300, s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("08-17-2023", s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba2", s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245709142346751245124", s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getJasotzailea());
		assertEquals(2, s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getKomisioa(), 0.01);
		assertEquals(200000, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getKantitatea(), 0.01);
		assertEquals(70000, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getOrdaindutakoa(), 0.01);
		assertEquals(10, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getKomisioa(), 0.01);
		assertEquals("06-15-2012", s1.getKontuBankarioak().get(0).getHipotekak().get(0).getHasieraData());
		assertEquals("07-11-2022", s1.getKontuBankarioak().get(0).getHipotekak().get(0).getAmaieraData());
		assertEquals("onartuta", s1.getKontuBankarioak().get(0).getHipotekak().get(0).getEgoera());
		assertEquals("12345678B", s1.getLangileak().get(0).getNan());
		assertEquals("Laura", s1.getLangileak().get(0).getIzena());
		assertEquals("Sanchez", s1.getLangileak().get(0).getAbizena());
		assertEquals("08-19-2002", s1.getLangileak().get(0).getJaiotzeData());
		assertEquals("emakumea", s1.getLangileak().get(0).getSexua());
		assertEquals("111222333", s1.getLangileak().get(0).getTelefonoa());
		assertEquals("87654321", s1.getLangileak().get(0).getPasahitza());
		assertEquals("Gerentea", s1.getLangileak().get(0).getLanpostu());
	}
	
	@Test
	public void testSukurtsalaToString() {
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
		
		ArrayList<KontuBankario> KontuBankarioak = new ArrayList<KontuBankario>();
		KontuBankarioak.add(kb1);
		
		Zuzendaria z1 = new Zuzendaria("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678", "Zuzendaria");
		
		ArrayList<Langilea> langileak = new ArrayList<Langilea>();
		langileak.add(z1);
		
		Sukurtsala s1 = new Sukurtsala(1, "13","Gallarraga kalea", KontuBankarioak, langileak);
		
		// ToString
		assertEquals("Sukurtsala idSukurtsala=" + s1.getIdSukurtsala() + ", kodSukurtsala=" + s1.getKodSukurtsala() + ", kokalekua="
				+ s1.getKokalekua() + ", kontuBankarioak=" + s1.getKontuBankarioak(), s1.toString());
	}
	
	@Test
	public void testSukurtsalaEquals() {
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
		
		ArrayList<KontuBankario> KontuBankarioak = new ArrayList<KontuBankario>();
		KontuBankarioak.add(kb1);
		
		Zuzendaria z1 = new Zuzendaria("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678", "Zuzendaria");
		
		ArrayList<Langilea> langileak = new ArrayList<Langilea>();
		langileak.add(z1);
		
		Sukurtsala s1 = new Sukurtsala(1, "13","Gallarraga kalea", KontuBankarioak, langileak);
		Sukurtsala s2 = new Sukurtsala(1, "13","Gallarraga kalea", KontuBankarioak, langileak);
		Sukurtsala s3 = new Sukurtsala();
		
		// Equals
		assertTrue(s1.equals(s2));
		assertFalse(s3.equals(null));
	}

}
