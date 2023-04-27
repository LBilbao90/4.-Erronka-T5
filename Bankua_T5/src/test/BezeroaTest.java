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

public class BezeroaTest {

	@Test
	public void testBezeroaConsGetLangilea() {
		// Langilerako Sortzailea
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
		// Getters
		assertEquals("12345678A", b1.getNan());
		assertEquals("Juan", b1.getIzena());
		assertEquals("Perez", b1.getAbizena());
		assertEquals("10-21-2002", b1.getJaiotzeData());
		assertEquals("gizona", b1.getSexua());
		assertEquals("111222333", b1.getTelefonoa());
		assertEquals("12345678", b1.getPasahitza());
	}
	
	@Test
	public void testBezeroaConsGet() {
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
		
		ArrayList<Txartela> txartelak = new ArrayList<Txartela>();
		txartelak.add(txartela);
		
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678", txartelak);
		
		// Getters
		assertEquals("12345678A", b1.getNan());
		assertEquals("Juan", b1.getIzena());
		assertEquals("Perez", b1.getAbizena());
		assertEquals("10-21-2002", b1.getJaiotzeData());
		assertEquals("gizona", b1.getSexua());
		assertEquals("111222333", b1.getTelefonoa());
		assertEquals("12345678", b1.getPasahitza());
		
		assertEquals("455", b1.getTxartelak().get(0).getSegurtasunKodea());
		assertEquals("debito", b1.getTxartelak().get(0).getMota());
		
		assertEquals("ES1576197348527531954865", b1.getTxartelak().get(0).getKontuBankario().getIban());
		assertEquals(2000, b1.getTxartelak().get(0).getKontuBankario().getSaldoa(), 0.01);
		assertEquals(100, b1.getTxartelak().get(0).getKontuBankario().getHilekoLimitea(), 0.01);
		assertEquals("01-20-2003", b1.getTxartelak().get(0).getKontuBankario().getSorreraData());
		assertEquals("aktiboa", b1.getTxartelak().get(0).getKontuBankario().getEgoera());
		
		assertEquals(100, b1.getTxartelak().get(0).getKontuBankario().getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("03-19-2023", b1.getTxartelak().get(0).getKontuBankario().getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera", b1.getTxartelak().get(0).getKontuBankario().getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1245784512346751245124", b1.getTxartelak().get(0).getKontuBankario().getDiruSarrerak().get(0).getIgortzailea());
		
		assertEquals(100, b1.getTxartelak().get(0).getKontuBankario().getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("04-23-2008", b1.getTxartelak().get(0).getKontuBankario().getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba", b1.getTxartelak().get(0).getKontuBankario().getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245784512346751245124", b1.getTxartelak().get(0).getKontuBankario().getTransferentziak().get(0).getJasotzailea());
		assertEquals(2, b1.getTxartelak().get(0).getKontuBankario().getTransferentziak().get(0).getKomisioa(), 0.01);
		
		assertEquals(100000, b1.getTxartelak().get(0).getKontuBankario().getHipoteka().getKantitatea(), 0.01);
		assertEquals(50000, b1.getTxartelak().get(0).getKontuBankario().getHipoteka().getOrdaindutakoa(), 0.01);
		assertEquals(10, b1.getTxartelak().get(0).getKontuBankario().getHipoteka().getKomisioa(), 0.01);
		assertEquals("03-12-2010", b1.getTxartelak().get(0).getKontuBankario().getHipoteka().getHasieraData());
		assertEquals("06-10-2020", b1.getTxartelak().get(0).getKontuBankario().getHipoteka().getAmaieraData());
		assertEquals("eskatuta", b1.getTxartelak().get(0).getKontuBankario().getHipoteka().getEgoera());
		
		assertEquals("1",b1.getTxartelak().get(0).getKontuBankario().getSukurtsala().getIdSukurtsala());
		assertEquals("13", b1.getTxartelak().get(0).getKontuBankario().getSukurtsala().getKodSukurtsala());
		assertEquals("Gallarraga kalea",b1.getTxartelak().get(0).getKontuBankario().getSukurtsala().getKokalekua());
		
		assertEquals("BBK", b1.getTxartelak().get(0).getKontuBankario().getSukurtsala().getEntitateBankario().getIzena());
		assertEquals("1",b1.getTxartelak().get(0).getKontuBankario().getSukurtsala().getEntitateBankario().getIdEntitatea());
		assertEquals("ES25", b1.getTxartelak().get(0).getKontuBankario().getSukurtsala().getEntitateBankario().getEntitateZbk());
		assertEquals("0/0/932/130", b1.getTxartelak().get(0).getKontuBankario().getSukurtsala().getEntitateBankario().getBounds());
		assertEquals("src-res-bbk_logo.png", b1.getTxartelak().get(0).getKontuBankario().getSukurtsala().getEntitateBankario().getUrl());
	}
	
	@Test
	public void testBezeroaSetLangilea() {
		// Langilerako Sortzailea
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
		// Setters
		b1.setNan("12345678B");
		b1.setIzena("Laura");
		b1.setAbizena("Sanchez");
		b1.setJaiotzeData("08-19-2002");
		b1.setSexua("emakumea");
		b1.setTelefonoa("444555666");
		b1.setPasahitza("87654321");
		
		assertEquals("12345678B", b1.getNan());
		assertEquals("Laura", b1.getIzena());
		assertEquals("Sanchez", b1.getAbizena());
		assertEquals("08-19-2002", b1.getJaiotzeData());
		assertEquals("emakumea", b1.getSexua());
		assertEquals("444555666", b1.getTelefonoa());
		assertEquals("87654321", b1.getPasahitza());
	}
	
	@Test
	public void testBezeroaSetBezeroa() {
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
		
		ArrayList<Txartela> txartelak = new ArrayList<Txartela>();
		txartelak.add(txartela);
		
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678", txartelak);
		
		
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
		
		Txartela txartela2 = new Txartela("860", "kredito", kb2);
		
		ArrayList<Txartela> txartelak2 = new ArrayList<Txartela>();
		txartelak2.add(txartela2);
		
		// Setters
		b1.setNan("12345678B");
		b1.setIzena("Laura");
		b1.setAbizena("Sanchez");
		b1.setJaiotzeData("08-19-2002");
		b1.setSexua("emakumea");
		b1.setTelefonoa("444555666");
		b1.setPasahitza("87654321");
		b1.setTxartelak(txartelak2);
		
		assertEquals("12345678B", b1.getNan());
		assertEquals("Laura", b1.getIzena());
		assertEquals("Sanchez", b1.getAbizena());
		assertEquals("08-19-2002", b1.getJaiotzeData());
		assertEquals("emakumea", b1.getSexua());
		assertEquals("444555666", b1.getTelefonoa());
		assertEquals("87654321", b1.getPasahitza());
		
		assertEquals("860", b1.getTxartelak().get(0).getSegurtasunKodea());
		assertEquals("kredito", b1.getTxartelak().get(0).getMota());
		
		assertEquals("ES1576197348527531958261", b1.getTxartelak().get(0).getKontuBankario().getIban());
		assertEquals(3000, b1.getTxartelak().get(0).getKontuBankario().getSaldoa(), 0.01);
		assertEquals(200, b1.getTxartelak().get(0).getKontuBankario().getHilekoLimitea(), 0.01);
		assertEquals("08-22-2004", b1.getTxartelak().get(0).getKontuBankario().getSorreraData());
		assertEquals("izoztuta", b1.getTxartelak().get(0).getKontuBankario().getEgoera());
		
		assertEquals(200, b1.getTxartelak().get(0).getKontuBankario().getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("09-10-2003", b1.getTxartelak().get(0).getKontuBankario().getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera2", b1.getTxartelak().get(0).getKontuBankario().getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1297514512626751245124", b1.getTxartelak().get(0).getKontuBankario().getDiruSarrerak().get(0).getIgortzailea());
		
		assertEquals(300, b1.getTxartelak().get(0).getKontuBankario().getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("08-17-2023", b1.getTxartelak().get(0).getKontuBankario().getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba2", b1.getTxartelak().get(0).getKontuBankario().getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245709142346751245124", b1.getTxartelak().get(0).getKontuBankario().getTransferentziak().get(0).getJasotzailea());
		assertEquals(2, b1.getTxartelak().get(0).getKontuBankario().getTransferentziak().get(0).getKomisioa(), 0.01);
		
		assertEquals(200000, b1.getTxartelak().get(0).getKontuBankario().getHipoteka().getKantitatea(), 0.01);
		assertEquals(70000, b1.getTxartelak().get(0).getKontuBankario().getHipoteka().getOrdaindutakoa(), 0.01);
		assertEquals(10, b1.getTxartelak().get(0).getKontuBankario().getHipoteka().getKomisioa(), 0.01);
		assertEquals("06-15-2012", b1.getTxartelak().get(0).getKontuBankario().getHipoteka().getHasieraData());
		assertEquals("07-11-2022", b1.getTxartelak().get(0).getKontuBankario().getHipoteka().getAmaieraData());
		assertEquals("onartuta", b1.getTxartelak().get(0).getKontuBankario().getHipoteka().getEgoera());
		
		assertEquals("2",b1.getTxartelak().get(0).getKontuBankario().getSukurtsala().getIdSukurtsala());
		assertEquals("11", b1.getTxartelak().get(0).getKontuBankario().getSukurtsala().getKodSukurtsala());
		assertEquals("Autonomia kalea",b1.getTxartelak().get(0).getKontuBankario().getSukurtsala().getKokalekua());
		
		assertEquals("BBVA", b1.getTxartelak().get(0).getKontuBankario().getSukurtsala().getEntitateBankario().getIzena());
		assertEquals("2",b1.getTxartelak().get(0).getKontuBankario().getSukurtsala().getEntitateBankario().getIdEntitatea());
		assertEquals("ES36", b1.getTxartelak().get(0).getKontuBankario().getSukurtsala().getEntitateBankario().getEntitateZbk());
		assertEquals("092/567/900/810", b1.getTxartelak().get(0).getKontuBankario().getSukurtsala().getEntitateBankario().getBounds());
		assertEquals("bbva.png", b1.getTxartelak().get(0).getKontuBankario().getSukurtsala().getEntitateBankario().getUrl());
	}
	
	@Test
	public void testBezeroaAdinaKalkulatu() {
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
		// AdinaKalkulatu
		assertEquals(20, b1.adinaKalkulatu());
	}

}
