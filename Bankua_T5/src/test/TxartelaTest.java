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

public class TxartelaTest {

	@Test
	public void testTxartelaConsGetLangilea() {
		// Langilerako Sortzailea
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
		Txartela txartela = new Txartela("455", "debito", b1);
		
		// Getters
		assertEquals("455", txartela.getSegurtasunKodea());
		assertEquals("debito", txartela.getMota());
		
		assertEquals("12345678A",txartela.getBezeroa().getNan());
		assertEquals("Juan",txartela.getBezeroa().getIzena());
		assertEquals("Perez",txartela.getBezeroa().getAbizena());
		assertEquals("10-21-2002",txartela.getBezeroa().getJaiotzeData());
		assertEquals("gizona",txartela.getBezeroa().getSexua());
		assertEquals("111222333",txartela.getBezeroa().getTelefonoa());
		assertEquals("12345678",txartela.getBezeroa().getPasahitza());
	}
	
	@Test
	public void testTxartelaConsGetBezeroa() {
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
		
		Txartela txartela = new Txartela("455", "debito", kb1);
		
		// Getters
		assertEquals("455", txartela.getSegurtasunKodea());
		assertEquals("debito", txartela.getMota());
		
		assertEquals("ES1576197348527531954865", txartela.getKontuBankario().getIban());
		assertEquals(2000, txartela.getKontuBankario().getSaldoa(), 0.01);
		assertEquals(100, txartela.getKontuBankario().getHilekoLimitea(), 0.01);
		assertEquals("01-20-2003", txartela.getKontuBankario().getSorreraData());
		assertEquals("aktiboa", txartela.getKontuBankario().getEgoera());
		
		assertEquals(100, txartela.getKontuBankario().getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("03-19-2023", txartela.getKontuBankario().getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera", txartela.getKontuBankario().getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1245784512346751245124", txartela.getKontuBankario().getDiruSarrerak().get(0).getIgortzailea());
		
		assertEquals(100, txartela.getKontuBankario().getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("04-23-2008", txartela.getKontuBankario().getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba", txartela.getKontuBankario().getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245784512346751245124", txartela.getKontuBankario().getTransferentziak().get(0).getJasotzailea());
		assertEquals(2, txartela.getKontuBankario().getTransferentziak().get(0).getKomisioa(), 0.01);
		
		assertEquals(100000, txartela.getKontuBankario().getHipoteka().getKantitatea(), 0.01);
		assertEquals(50000, txartela.getKontuBankario().getHipoteka().getOrdaindutakoa(), 0.01);
		assertEquals(10, txartela.getKontuBankario().getHipoteka().getKomisioa(), 0.01);
		assertEquals("03-12-2010", txartela.getKontuBankario().getHipoteka().getHasieraData());
		assertEquals("06-10-2020", txartela.getKontuBankario().getHipoteka().getAmaieraData());
		assertEquals("eskatuta", txartela.getKontuBankario().getHipoteka().getEgoera());
		
		assertEquals("1",txartela.getKontuBankario().getSukurtsala().getIdSukurtsala());
		assertEquals("13", txartela.getKontuBankario().getSukurtsala().getKodSukurtsala());
		assertEquals("Gallarraga kalea",txartela.getKontuBankario().getSukurtsala().getKokalekua());
		
		assertEquals("BBK", txartela.getKontuBankario().getSukurtsala().getEntitateBankario().getIzena());
		assertEquals("1",txartela.getKontuBankario().getSukurtsala().getEntitateBankario().getIdEntitatea());
		assertEquals("ES25", txartela.getKontuBankario().getSukurtsala().getEntitateBankario().getEntitateZbk());
		assertEquals("0/0/932/130", txartela.getKontuBankario().getSukurtsala().getEntitateBankario().getBounds());
		assertEquals("src-res-bbk_logo.png", txartela.getKontuBankario().getSukurtsala().getEntitateBankario().getUrl());
	}
	
	@Test
	public void testTxartelaSetLangilea() {
		// Langilerako Sortzailea
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
		Txartela txartela = new Txartela("455", "debito", b1);
		
		
		Bezeroa b2 = new Bezeroa("12345678B", "Laura", "Sanchez", "08-19-2002", "emakumea", "444555666", "87654321");
		
		// Setters
		txartela.setSegurtasunKodea("860");
		txartela.setMota("kredito");
		txartela.setBezeroa(b2);
		
		assertEquals("860", txartela.getSegurtasunKodea());
		assertEquals("kredito", txartela.getMota());
		
		assertEquals("12345678B",txartela.getBezeroa().getNan());
		assertEquals("Laura",txartela.getBezeroa().getIzena());
		assertEquals("Sanchez",txartela.getBezeroa().getAbizena());
		assertEquals("08-19-2002",txartela.getBezeroa().getJaiotzeData());
		assertEquals("emakumea",txartela.getBezeroa().getSexua());
		assertEquals("444555666",txartela.getBezeroa().getTelefonoa());
		assertEquals("87654321",txartela.getBezeroa().getPasahitza());
	}
	
	@Test
	public void testTxartelaSetBezeroa() {
		// Langilerako Sortzailea
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
		
		Txartela txartela = new Txartela("455", "debito", kb1);
		
		
		DiruSarrera ds2 = new DiruSarrera(200, "09-10-2003", "sarrera2", "ES1297514512626751245124");
		
		ArrayList<DiruSarrera> diruSarrerak2 = new ArrayList<DiruSarrera> ();
		diruSarrerak2.add(ds2);
		
		Transferentzia t2 = new Transferentzia (300, "08-17-2023", "proba2", "ES1245709142346751245124");
		
		ArrayList<Transferentzia> transferentziak2 = new ArrayList<Transferentzia>();
		transferentziak2.add(t2);
		
		Hipoteka h2 = new Hipoteka (200000, 70000, 10, "06-15-2012", "07-11-2022", "onartuta");
		
		EntitateBankario eb2 = new EntitateBankario ("BBVA", "2", "ES36", "092/567/900/810", "bbva.png");
		
		Sukurtsala s2 = new Sukurtsala("2", "11","Autonomia kalea", eb2);
		
		KontuBankario kb2 = new KontuBankario("ES1576197348527531958261", 3000, 200, "08-22-2004", "izoztuta", diruSarrerak2, transferentziak2, h2, s2);
		
		// Setters
		txartela.setSegurtasunKodea("860");
		txartela.setMota("kredito");
		txartela.setKontuBankario(kb2);
		
		assertEquals("860", txartela.getSegurtasunKodea());
		assertEquals("kredito", txartela.getMota());
		
		assertEquals("ES1576197348527531958261", txartela.getKontuBankario().getIban());
		assertEquals(3000, txartela.getKontuBankario().getSaldoa(), 0.01);
		assertEquals(200, txartela.getKontuBankario().getHilekoLimitea(), 0.01);
		assertEquals("08-22-2004", txartela.getKontuBankario().getSorreraData());
		assertEquals("izoztuta", txartela.getKontuBankario().getEgoera());
		
		assertEquals(200, txartela.getKontuBankario().getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("09-10-2003", txartela.getKontuBankario().getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera2", txartela.getKontuBankario().getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1297514512626751245124", txartela.getKontuBankario().getDiruSarrerak().get(0).getIgortzailea());
		
		assertEquals(300, txartela.getKontuBankario().getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("08-17-2023", txartela.getKontuBankario().getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba2", txartela.getKontuBankario().getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245709142346751245124", txartela.getKontuBankario().getTransferentziak().get(0).getJasotzailea());
		assertEquals(2, txartela.getKontuBankario().getTransferentziak().get(0).getKomisioa(), 0.01);
		
		assertEquals(200000, txartela.getKontuBankario().getHipoteka().getKantitatea(), 0.01);
		assertEquals(70000, txartela.getKontuBankario().getHipoteka().getOrdaindutakoa(), 0.01);
		assertEquals(10, txartela.getKontuBankario().getHipoteka().getKomisioa(), 0.01);
		assertEquals("06-15-2012", txartela.getKontuBankario().getHipoteka().getHasieraData());
		assertEquals("07-11-2022", txartela.getKontuBankario().getHipoteka().getAmaieraData());
		assertEquals("onartuta", txartela.getKontuBankario().getHipoteka().getEgoera());
		
		assertEquals("2",txartela.getKontuBankario().getSukurtsala().getIdSukurtsala());
		assertEquals("11", txartela.getKontuBankario().getSukurtsala().getKodSukurtsala());
		assertEquals("Autonomia kalea",txartela.getKontuBankario().getSukurtsala().getKokalekua());
		
		assertEquals("BBVA", txartela.getKontuBankario().getSukurtsala().getEntitateBankario().getIzena());
		assertEquals("2",txartela.getKontuBankario().getSukurtsala().getEntitateBankario().getIdEntitatea());
		assertEquals("ES36", txartela.getKontuBankario().getSukurtsala().getEntitateBankario().getEntitateZbk());
		assertEquals("092/567/900/810", txartela.getKontuBankario().getSukurtsala().getEntitateBankario().getBounds());
		assertEquals("bbva.png", txartela.getKontuBankario().getSukurtsala().getEntitateBankario().getUrl());
	}
	
	@Test
	public void testTxartelaToString() {
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
		Txartela txartela = new Txartela("455", "debito", b1);
		
		// ToString
		assertEquals("Txartela segurtasunKodea=" + txartela.getSegurtasunKodea() + ", mota=" + txartela.getMota(), txartela.toString());
	}
	
	@Test
	public void testTxartelaEquals() {
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
				
		Txartela txartela = new Txartela("455", "debito", b1);
		Txartela txartela2 = new Txartela("455", "debito", b1);
		Txartela txartela3 = new Txartela();
		
		// Equals
		assertTrue(txartela.equals(txartela2));
		assertFalse(txartela3.equals(null));
	}

}
