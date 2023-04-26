package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.DiruSarrera;
import model.EntitateBankario;
import model.Hipoteka;
import model.KontuBankario;
import model.Langilea;
import model.Sukurtsala;
import model.Transferentzia;
import model.Zuzendaria;

public class EntitateBankarioaTest {

	@Test
	public void testEntitaBankarioaConsGet() {
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
		
		ArrayList<Sukurtsala> sukurtsalak = new ArrayList<Sukurtsala>();
		sukurtsalak.add(s1);
		
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", sukurtsalak, "0, 0, 932, 130", "src-res-bbk_logo.png");
		
		// Getters
		assertEquals("BBK", eb1.getIzena());
		assertEquals("1",eb1.getIdEntitatea());
		assertEquals("ES25", eb1.getEntitateZbk());
		assertEquals("0, 0, 932, 130", eb1.getBounds());
		assertEquals("src-res-bbk_logo.png", eb1.getUrl());
		assertEquals(1, eb1.getSukurtsalak().get(0).getIdSukurtsala());
		assertEquals("13", eb1.getSukurtsalak().get(0).getKodSukurtsala());
		assertEquals("Gallarraga kalea", eb1.getSukurtsalak().get(0).getKokalekua());
		assertEquals("ES1576197348527531954865", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getIban());
		assertEquals(2000, eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getSaldoa(), 0.01);
		assertEquals(100, eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHilekoLimitea(), 0.01);
		assertEquals("01-20-2003", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getSorreraData());
		assertEquals("aktiboa", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getEgoera());
		assertEquals(100, eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("03-19-2023", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1245784512346751245124", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIgortzailea());
		assertEquals(100, eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("04-23-2008", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245784512346751245124", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getJasotzailea());
		assertEquals(2, eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKomisioa(), 0.01);
		assertEquals(100000, eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipotekak().get(0).getKantitatea(), 0.01);
		assertEquals(50000, eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipotekak().get(0).getOrdaindutakoa(), 0.01);
		assertEquals(10, eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipotekak().get(0).getKomisioa(), 0.01);
		assertEquals("03-12-2010", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipotekak().get(0).getHasieraData());
		assertEquals("06-10-2020", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipotekak().get(0).getAmaieraData());
		assertEquals("eskatuta", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipotekak().get(0).getEgoera());
	}
	
	@Test
	public void testEntitaBankarioaSet() {
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
		
		ArrayList<Sukurtsala> sukurtsalak = new ArrayList<Sukurtsala>();
		sukurtsalak.add(s1);
		
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", sukurtsalak, "0, 0, 932, 130", "src-res-bbk_logo.png");
		
		
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
		
		Sukurtsala s2 = new Sukurtsala(2, "11","Autonomia kalea", KontuBankarioak2, langileak2);
		
		ArrayList<Sukurtsala> sukurtzalak2 = new ArrayList<Sukurtsala>();
		sukurtzalak2.add(s2);
		
		// Setters
		eb1.setIzena("BBVA");
		eb1.setIdEntitatea("2");
		eb1.setEntitateZbk("ES36");
		eb1.setSukurtsalak(sukurtzalak2);
		eb1.setBounds("0, 0, 932, 130");
		eb1.setUrl("src-res-bbk_logo.png");
		
		assertEquals("BBVA", eb1.getIzena());
		assertEquals("2",eb1.getIdEntitatea());
		assertEquals("ES36", eb1.getEntitateZbk());
		assertEquals("0, 0, 932, 130", eb1.getBounds());
		assertEquals("src-res-bbk_logo.png", eb1.getUrl());
		assertEquals(2, eb1.getSukurtsalak().get(0).getIdSukurtsala());
		assertEquals("11", eb1.getSukurtsalak().get(0).getKodSukurtsala());
		assertEquals("Autonomia kalea", eb1.getSukurtsalak().get(0).getKokalekua());
		assertEquals("ES1576197348527531958261", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getIban());
		assertEquals(3000, eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getSaldoa(), 0.01);
		assertEquals(200, eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHilekoLimitea(), 0.01);
		assertEquals("08-22-2004", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getSorreraData());
		assertEquals("izoztuta", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getEgoera());
		assertEquals(200, eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("09-10-2003", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera2", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1297514512626751245124", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIgortzailea());
		assertEquals(300, eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("08-17-2023", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba2", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245709142346751245124", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getJasotzailea());
		assertEquals(2, eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKomisioa(), 0.01);
		assertEquals(200000, eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipotekak().get(0).getKantitatea(), 0.01);
		assertEquals(70000, eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipotekak().get(0).getOrdaindutakoa(), 0.01);
		assertEquals(10, eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipotekak().get(0).getKomisioa(), 0.01);
		assertEquals("06-15-2012", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipotekak().get(0).getHasieraData());
		assertEquals("07-11-2022", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipotekak().get(0).getAmaieraData());
		assertEquals("onartuta", eb1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipotekak().get(0).getEgoera());
	}
	
	@Test
	public void testEntitaBankarioaToString() {
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
		
		ArrayList<Sukurtsala> sukurtsalak = new ArrayList<Sukurtsala>();
		sukurtsalak.add(s1);
		
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", sukurtsalak, "0, 0, 932, 130", "src-res-bbk_logo.png");
		
		// ToString
		assertEquals("EntitateBankarioa izena=" + eb1.getIzena() + ", idEntitatea=" + eb1.getIdEntitatea() + ", entitateZbk=" + eb1.getEntitateZbk()
				+ ", sukurtsalak=" + eb1.getSukurtsalak(), eb1.toString());
	}
	
	@Test
	public void testEntitaBankarioaEquals() {
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
		
		ArrayList<Sukurtsala> sukurtsalak = new ArrayList<Sukurtsala>();
		sukurtsalak.add(s1);
		
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", sukurtsalak, "0, 0, 932, 130", "src-res-bbk_logo.png");
		EntitateBankario eb2 = new EntitateBankario ("BBK", "1", "ES25", sukurtsalak, "0, 0, 932, 130", "src-res-bbk_logo.png");
		EntitateBankario eb3 = new EntitateBankario ();
		
		// Equals
		assertTrue(eb1.equals(eb2));
		assertFalse(eb3.equals(null));
	}

}
