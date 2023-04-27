package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Bezeroa;
import model.DiruSarrera;
import model.EntitateBankario;
import model.Hipoteka;
import model.KontuBankario;
import model.Sukurtsala;
import model.Transferentzia;
import model.Txartela;

public class KontuBankarioaTest {

	@Test
	public void testKontuBankarioaConsGetLangilea() {
		// Langilerako Sortzailea
		DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Transferentzia t1 = new Transferentzia (100, "04-23-2008", "proba", "ES1245784512346751245124");
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
		Hipoteka h1 = new Hipoteka (100000, 50000, 10, "03-12-2010", "06-10-2020", "eskatuta");
		
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
		Txartela txartela = new Txartela("455", "debito", b1);
		
		ArrayList<Txartela> txartelak = new ArrayList<Txartela>();
		txartelak.add(txartela);
		
		KontuBankario kb1 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "aktiboa", diruSarrerak, transferentziak, h1, txartelak);
		
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
		
		assertEquals(100000, kb1.getHipoteka().getKantitatea(), 0.01);
		assertEquals(50000, kb1.getHipoteka().getOrdaindutakoa(), 0.01);
		assertEquals(10, kb1.getHipoteka().getKomisioa(), 0.01);
		assertEquals("03-12-2010", kb1.getHipoteka().getHasieraData());
		assertEquals("06-10-2020", kb1.getHipoteka().getAmaieraData());
		assertEquals("eskatuta", kb1.getHipoteka().getEgoera());
		
		assertEquals("455", kb1.getTxartelak().get(0).getSegurtasunKodea());
		assertEquals("debito", kb1.getTxartelak().get(0).getMota());
		assertEquals("12345678A",kb1.getTxartelak().get(0).getBezeroa().getNan());
		assertEquals("Juan",kb1.getTxartelak().get(0).getBezeroa().getIzena());
		assertEquals("Perez",kb1.getTxartelak().get(0).getBezeroa().getAbizena());
		assertEquals("10-21-2002",kb1.getTxartelak().get(0).getBezeroa().getJaiotzeData());
		assertEquals("gizona",kb1.getTxartelak().get(0).getBezeroa().getSexua());
		assertEquals("111222333",kb1.getTxartelak().get(0).getBezeroa().getTelefonoa());
		assertEquals("12345678",kb1.getTxartelak().get(0).getBezeroa().getPasahitza());
	}
	
	@Test
	public void testKontuBankarioaConsGetBezeroa() {
		// Bezerorako Sortzailea
		DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Transferentzia t1 = new Transferentzia (100, "04-23-2008", "proba", "ES1245784512346751245124");
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
		Hipoteka h1 = new Hipoteka (100000, 50000, 10, "03-12-2010", "06-10-2020", "eskatuta");
		
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", "0/0/932/130", "src-res-bbk_logo.png");
		
		Sukurtsala s1 = new Sukurtsala("1", "13","Gallarraga kalea", eb1);
		
		KontuBankario kb1 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "aktiboa", diruSarrerak, transferentziak, h1, s1);
		
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
		
		assertEquals(100000, kb1.getHipoteka().getKantitatea(), 0.01);
		assertEquals(50000, kb1.getHipoteka().getOrdaindutakoa(), 0.01);
		assertEquals(10, kb1.getHipoteka().getKomisioa(), 0.01);
		assertEquals("03-12-2010", kb1.getHipoteka().getHasieraData());
		assertEquals("06-10-2020", kb1.getHipoteka().getAmaieraData());
		assertEquals("eskatuta", kb1.getHipoteka().getEgoera());
		
		assertEquals("1",kb1.getSukurtsala().getIdSukurtsala());
		assertEquals("13", kb1.getSukurtsala().getKodSukurtsala());
		assertEquals("Gallarraga kalea",s1.getKokalekua());
		
		assertEquals("BBK", kb1.getSukurtsala().getEntitateBankario().getIzena());
		assertEquals("1",kb1.getSukurtsala().getEntitateBankario().getIdEntitatea());
		assertEquals("ES25", kb1.getSukurtsala().getEntitateBankario().getEntitateZbk());
		assertEquals("0/0/932/130", kb1.getSukurtsala().getEntitateBankario().getBounds());
		assertEquals("src-res-bbk_logo.png", kb1.getSukurtsala().getEntitateBankario().getUrl());
	}
	
	@Test
	public void testKontuBankarioaSetLangilea() {
		// Langilerako Sortzailea
		DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Transferentzia t1 = new Transferentzia (100, "04-23-2008", "proba", "ES1245784512346751245124");
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
		Hipoteka h1 = new Hipoteka (100000, 50000, 10, "03-12-2010", "06-10-2020", "eskatuta");
		
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
		Txartela txartela = new Txartela("455", "debito", b1);
		
		ArrayList<Txartela> txartelak = new ArrayList<Txartela>();
		txartelak.add(txartela);	
		
		KontuBankario kb1 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "aktiboa", diruSarrerak,transferentziak, h1, txartelak);
		
		
		DiruSarrera ds2 = new DiruSarrera(200, "09-10-2003", "sarrera2", "ES1297514512626751245124");
		
		ArrayList<DiruSarrera> diruSarrerak2 = new ArrayList<DiruSarrera> ();
		diruSarrerak2.add(ds2);
		
		Transferentzia t2 = new Transferentzia (300, "08-17-2023", "proba2", "ES1245709142346751245124");
		
		ArrayList<Transferentzia> transferentziak2 = new ArrayList<Transferentzia>();
		transferentziak2.add(t2);
		
		Hipoteka h2 = new Hipoteka (200000, 70000, 10, "06-15-2012", "07-11-2022", "onartuta");
		
		Bezeroa b2 = new Bezeroa("12345678B", "Laura", "Sanchez", "08-19-2002", "emakumea", "444555666", "87654321");
		
		Txartela txartela2 = new Txartela("860", "kredito", b2);
		
		ArrayList<Txartela> txartelak2 = new ArrayList<Txartela>();
		txartelak2.add(txartela2);	
		
		// Setters
		kb1.setIban("ES1576197348527531958261");
		kb1.setSaldoa(3000);
		kb1.setHilekoLimitea(200);
		kb1.setSorreraData("08-22-2004");
		kb1.setEgoera("izoztuta");
		kb1.setDiruSarrerak(diruSarrerak2);
		kb1.setTransferentziak(transferentziak2);
		kb1.setHipoteka(h2);
		kb1.setTxartelak(txartelak2);
		
		
		assertEquals("ES1576197348527531958261", kb1.getIban());
		assertEquals(3000, kb1.getSaldoa(), 0.01);
		assertEquals(200, kb1.getHilekoLimitea(), 0.01);
		assertEquals("08-22-2004", kb1.getSorreraData());
		assertEquals("izoztuta", kb1.getEgoera());
		
		assertEquals(200, kb1.getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("09-10-2003", kb1.getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera2", kb1.getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1297514512626751245124", kb1.getDiruSarrerak().get(0).getIgortzailea());
		
		assertEquals(300, kb1.getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("08-17-2023", kb1.getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba2", kb1.getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245709142346751245124", kb1.getTransferentziak().get(0).getJasotzailea());
		assertEquals(2, kb1.getTransferentziak().get(0).getKomisioa(), 0.01);
		
		assertEquals(200000, kb1.getHipoteka().getKantitatea(), 0.01);
		assertEquals(70000, kb1.getHipoteka().getOrdaindutakoa(), 0.01);
		assertEquals(10, kb1.getHipoteka().getKomisioa(), 0.01);
		assertEquals("06-15-2012", kb1.getHipoteka().getHasieraData());
		assertEquals("07-11-2022", kb1.getHipoteka().getAmaieraData());
		assertEquals("onartuta", kb1.getHipoteka().getEgoera());
		
		assertEquals("860", kb1.getTxartelak().get(0).getSegurtasunKodea());
		assertEquals("kredito", kb1.getTxartelak().get(0).getMota());
		assertEquals("12345678B",kb1.getTxartelak().get(0).getBezeroa().getNan());
		assertEquals("Laura",kb1.getTxartelak().get(0).getBezeroa().getIzena());
		assertEquals("Sanchez",kb1.getTxartelak().get(0).getBezeroa().getAbizena());
		assertEquals("08-19-2002",kb1.getTxartelak().get(0).getBezeroa().getJaiotzeData());
		assertEquals("emakumea",kb1.getTxartelak().get(0).getBezeroa().getSexua());
		assertEquals("444555666",kb1.getTxartelak().get(0).getBezeroa().getTelefonoa());
		assertEquals("87654321",kb1.getTxartelak().get(0).getBezeroa().getPasahitza());
	}
	
	@Test
	public void testKontuBankarioaSetBezeroa() {
		// Bezerorako Sortzailea
		DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Transferentzia t1 = new Transferentzia (100, "04-23-2008", "proba", "ES1245784512346751245124");
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
		Hipoteka h1 = new Hipoteka (100000, 50000, 10, "03-12-2010", "06-10-2020", "eskatuta");
		
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", "0/0/932/130", "src-res-bbk_logo.png");
		
		Sukurtsala s1 = new Sukurtsala("1", "13","Gallarraga kalea", eb1);
		
		KontuBankario kb1 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "aktiboa", diruSarrerak, transferentziak, h1, s1);
		
		
		DiruSarrera ds2 = new DiruSarrera(200, "09-10-2003", "sarrera2", "ES1297514512626751245124");
		
		ArrayList<DiruSarrera> diruSarrerak2 = new ArrayList<DiruSarrera> ();
		diruSarrerak2.add(ds2);
		
		Transferentzia t2 = new Transferentzia (300, "08-17-2023", "proba2", "ES1245709142346751245124");
		
		ArrayList<Transferentzia> transferentziak2 = new ArrayList<Transferentzia>();
		transferentziak2.add(t2);
		
		Hipoteka h2 = new Hipoteka (200000, 70000, 10, "06-15-2012", "07-11-2022", "onartuta");
		
		EntitateBankario eb2 = new EntitateBankario ("BBVA", "2", "ES36", "092/567/900/810", "bbva.png");
		
		Sukurtsala s2 = new Sukurtsala("2", "11","Autonomia kalea", eb2);
		
		// Setters
		kb1.setIban("ES1576197348527531958261");
		kb1.setSaldoa(3000);
		kb1.setHilekoLimitea(200);
		kb1.setSorreraData("08-22-2004");
		kb1.setEgoera("izoztuta");
		kb1.setDiruSarrerak(diruSarrerak2);
		kb1.setTransferentziak(transferentziak2);
		kb1.setHipoteka(h2);
		kb1.setSukurtsala(s2);
		
		
		assertEquals("ES1576197348527531958261", kb1.getIban());
		assertEquals(3000, kb1.getSaldoa(), 0.01);
		assertEquals(200, kb1.getHilekoLimitea(), 0.01);
		assertEquals("08-22-2004", kb1.getSorreraData());
		assertEquals("izoztuta", kb1.getEgoera());
		
		assertEquals(200, kb1.getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("09-10-2003", kb1.getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera2", kb1.getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1297514512626751245124", kb1.getDiruSarrerak().get(0).getIgortzailea());
		
		assertEquals(300, kb1.getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("08-17-2023", kb1.getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba2", kb1.getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245709142346751245124", kb1.getTransferentziak().get(0).getJasotzailea());
		assertEquals(2, kb1.getTransferentziak().get(0).getKomisioa(), 0.01);
		
		assertEquals(200000, kb1.getHipoteka().getKantitatea(), 0.01);
		assertEquals(70000, kb1.getHipoteka().getOrdaindutakoa(), 0.01);
		assertEquals(10, kb1.getHipoteka().getKomisioa(), 0.01);
		assertEquals("06-15-2012", kb1.getHipoteka().getHasieraData());
		assertEquals("07-11-2022", kb1.getHipoteka().getAmaieraData());
		assertEquals("onartuta", kb1.getHipoteka().getEgoera());
		
		assertEquals("2",kb1.getSukurtsala().getIdSukurtsala());
		assertEquals("11", kb1.getSukurtsala().getKodSukurtsala());
		assertEquals("Autonomia kalea",kb1.getSukurtsala().getKokalekua());
		
		assertEquals("BBVA", kb1.getSukurtsala().getEntitateBankario().getIzena());
		assertEquals("2",kb1.getSukurtsala().getEntitateBankario().getIdEntitatea());
		assertEquals("ES36", kb1.getSukurtsala().getEntitateBankario().getEntitateZbk());
		assertEquals("092/567/900/810", kb1.getSukurtsala().getEntitateBankario().getBounds());
		assertEquals("bbva.png", kb1.getSukurtsala().getEntitateBankario().getUrl());
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
				
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
				
		Txartela txartela = new Txartela("455", "debito", b1);
				
		ArrayList<Txartela> txartelak = new ArrayList<Txartela>();
		txartelak.add(txartela);
				
		KontuBankario kb1 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "aktiboa", diruSarrerak, transferentziak, h1, txartelak);
	
		// ToString
		assertEquals("KontuBankario iban=" + kb1.getIban() + ", saldoa=" + kb1.getSaldoa() + ", hilekoLimitea=" + kb1.getHilekoLimitea()
				+ ", sorreraData=" + kb1.getSorreraData() + ", egoera=" + kb1.getEgoera() + ", diruSarrerak=" + kb1.getDiruSarrerak()
				+ ", transferentziak=" + kb1.getTransferentziak() + ", hipoteka=" + kb1.getHipoteka(), kb1.toString());
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
				
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
				
		Txartela txartela = new Txartela("455", "debito", b1);
				
		ArrayList<Txartela> txartelak = new ArrayList<Txartela>();
		txartelak.add(txartela);
				
		KontuBankario kb1 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "aktiboa", diruSarrerak, transferentziak, h1, txartelak);
		KontuBankario kb2 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "aktiboa", diruSarrerak, transferentziak, h1, txartelak);
		KontuBankario kb3 = new KontuBankario();
		
		// ToString
		assertTrue(kb1.equals(kb2));
		assertFalse(kb3.equals(null));
	}

}
