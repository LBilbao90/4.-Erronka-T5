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

public class SukurtsalaTest {

	@Test
	public void testSukurtsalaConsGetLangilea() {		
		// Langilerako Sortzailea
		DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Transferentzia t1 = new Transferentzia (100, "04-23-2008", "proba", "ES1245784512346751245124");
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
		Hipoteka h1 = new Hipoteka (100000, 50000, 10, "03-12-2010", "06-10-2020", "eskatuta", "10 urte");
		
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
		Txartela txartela = new Txartela("455", "debito", b1);
		
		ArrayList<Txartela> txartelak = new ArrayList<Txartela>();
		
		txartelak.add(txartela);
		
		KontuBankario kb1 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "aktiboa", diruSarrerak, transferentziak, h1, txartelak);
		
		ArrayList<KontuBankario> KontuBankarioak = new ArrayList<KontuBankario>();
		KontuBankarioak.add(kb1);
		
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", "0/0/932/130", "src-res-bbk_logo.png");
		
		Sukurtsala s1 = new Sukurtsala("1", "13","Gallarraga kalea", eb1, KontuBankarioak);
		
		// Getters
		assertEquals("1",s1.getIdSukurtsala());
		assertEquals("13", s1.getKodSukurtsala());
		assertEquals("Gallarraga kalea",s1.getKokalekua());
		
		assertEquals("BBK", s1.getEntitateBankario().getIzena());
		assertEquals("1",s1.getEntitateBankario().getIdEntitatea());
		assertEquals("ES25", s1.getEntitateBankario().getEntitateZbk());
		assertEquals("0/0/932/130", s1.getEntitateBankario().getBounds());
		assertEquals("src-res-bbk_logo.png", s1.getEntitateBankario().getUrl());
		
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
		
		assertEquals(100000, s1.getKontuBankarioak().get(0).getHipoteka().getKantitatea(), 0.01);
		assertEquals(50000, s1.getKontuBankarioak().get(0).getHipoteka().getOrdaindutakoa(), 0.01);
		assertEquals(10, s1.getKontuBankarioak().get(0).getHipoteka().getKomisioa(), 0.01);
		assertEquals("03-12-2010", s1.getKontuBankarioak().get(0).getHipoteka().getHasieraData());
		assertEquals("06-10-2020", s1.getKontuBankarioak().get(0).getHipoteka().getAmaieraData());
		assertEquals("eskatuta", s1.getKontuBankarioak().get(0).getHipoteka().getEgoera());
		assertEquals("10 urte", s1.getKontuBankarioak().get(0).getHipoteka().getEpeMuga());
		
		assertEquals("455", s1.getKontuBankarioak().get(0).getTxartelak().get(0).getSegurtasunKodea());
		assertEquals("debito", s1.getKontuBankarioak().get(0).getTxartelak().get(0).getMota());
		assertEquals("12345678A",s1.getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getNan());
		assertEquals("Juan",s1.getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getIzena());
		assertEquals("Perez",s1.getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getAbizena());
		assertEquals("10-21-2002",s1.getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getJaiotzeData());
		assertEquals("gizona",s1.getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getSexua());
		assertEquals("111222333",s1.getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getTelefonoa());
		assertEquals("12345678",s1.getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getPasahitza());
	}
	
	@Test
	public void testSukurtsalaConsGetBezeroa() {
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", "0/0/932/130", "src-res-bbk_logo.png");
		
		Sukurtsala s1 = new Sukurtsala("1", "13","Gallarraga kalea", eb1);
		
		// Getters
		assertEquals("1",s1.getIdSukurtsala());
		assertEquals("13", s1.getKodSukurtsala());
		assertEquals("Gallarraga kalea",s1.getKokalekua());
		
		assertEquals("BBK", s1.getEntitateBankario().getIzena());
		assertEquals("1",s1.getEntitateBankario().getIdEntitatea());
		assertEquals("ES25", s1.getEntitateBankario().getEntitateZbk());
		assertEquals("0/0/932/130", s1.getEntitateBankario().getBounds());
		assertEquals("src-res-bbk_logo.png", s1.getEntitateBankario().getUrl());
	}
	
	@Test
	public void testSukurtsalaSetLangilea() {
		// Langilerako Sortzailea
		DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Transferentzia t1 = new Transferentzia (100, "04-23-2008", "proba", "ES1245784512346751245124");
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
		Hipoteka h1 = new Hipoteka (100000, 50000, 10, "03-12-2010", "06-10-2020", "eskatuta", "10 urte");
		
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
		Txartela txartela = new Txartela("455", "debito", b1);
		
		ArrayList<Txartela> txartelak = new ArrayList<Txartela>();
		
		txartelak.add(txartela);
		
		KontuBankario kb1 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "aktiboa", diruSarrerak, transferentziak, h1, txartelak);
		
		ArrayList<KontuBankario> KontuBankarioak = new ArrayList<KontuBankario>();
		KontuBankarioak.add(kb1);
		
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", "0/0/932/130", "src-res-bbk_logo.png");
		
		Sukurtsala s1 = new Sukurtsala("1", "13","Gallarraga kalea", eb1, KontuBankarioak);
		
		
		DiruSarrera ds2 = new DiruSarrera(200, "09-10-2003", "sarrera2", "ES1297514512626751245124");
		
		ArrayList<DiruSarrera> diruSarrerak2 = new ArrayList<DiruSarrera> ();
		diruSarrerak2.add(ds2);
		
		Transferentzia t2 = new Transferentzia (300, "08-17-2023", "proba2", "ES1245709142346751245124");
		
		ArrayList<Transferentzia> transferentziak2 = new ArrayList<Transferentzia>();
		transferentziak2.add(t2);
		
		Hipoteka h2 = new Hipoteka (200000, 70000, 10, "06-15-2011", "07-11-2022", "onartuta", "11 urte");
		
		Bezeroa b2 = new Bezeroa("12345678B", "Laura", "Sanchez", "08-19-2002", "emakumea", "444555666", "87654321");
		
		Txartela txartela2 = new Txartela("860", "kredito", b2);
		
		ArrayList<Txartela> txartelak2 = new ArrayList<Txartela>();
		txartelak2.add(txartela2);
		
		KontuBankario kb2 = new KontuBankario("ES1576197348527531958261", 3000, 200, "08-22-2004", "izoztuta", diruSarrerak2,transferentziak2, h2, txartelak2);
		
		ArrayList<KontuBankario> KontuBankarioak2 = new ArrayList<KontuBankario>();
		KontuBankarioak2.add(kb2);
		
		EntitateBankario eb2 = new EntitateBankario ("BBVA", "2", "ES36", "092/567/900/810", "bbva.png");
		
		// Setters
		s1.setIdSukurtsala("2");
		s1.setKodSukurtsala("11");
		s1.setKokalekua("Autonomia kalea");
		s1.setEntitateBankario(eb2);
		s1.setKontuBankarioak(KontuBankarioak2);
		
		assertEquals("2",s1.getIdSukurtsala());
		assertEquals("11", s1.getKodSukurtsala());
		assertEquals("Autonomia kalea",s1.getKokalekua());
		
		assertEquals("BBVA", s1.getEntitateBankario().getIzena());
		assertEquals("2",s1.getEntitateBankario().getIdEntitatea());
		assertEquals("ES36", s1.getEntitateBankario().getEntitateZbk());
		assertEquals("092/567/900/810", s1.getEntitateBankario().getBounds());
		assertEquals("bbva.png", s1.getEntitateBankario().getUrl());
		
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
		
		assertEquals(200000, s1.getKontuBankarioak().get(0).getHipoteka().getKantitatea(), 0.01);
		assertEquals(70000, s1.getKontuBankarioak().get(0).getHipoteka().getOrdaindutakoa(), 0.01);
		assertEquals(10, s1.getKontuBankarioak().get(0).getHipoteka().getKomisioa(), 0.01);
		assertEquals("06-15-2011", s1.getKontuBankarioak().get(0).getHipoteka().getHasieraData());
		assertEquals("07-11-2022", s1.getKontuBankarioak().get(0).getHipoteka().getAmaieraData());
		assertEquals("onartuta", s1.getKontuBankarioak().get(0).getHipoteka().getEgoera());
		assertEquals("11 urte", s1.getKontuBankarioak().get(0).getHipoteka().getEpeMuga());
		
		assertEquals("860", s1.getKontuBankarioak().get(0).getTxartelak().get(0).getSegurtasunKodea());
		assertEquals("kredito", s1.getKontuBankarioak().get(0).getTxartelak().get(0).getMota());
		assertEquals("12345678B",s1.getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getNan());
		assertEquals("Laura",s1.getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getIzena());
		assertEquals("Sanchez",s1.getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getAbizena());
		assertEquals("08-19-2002",s1.getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getJaiotzeData());
		assertEquals("emakumea",s1.getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getSexua());
		assertEquals("444555666",s1.getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getTelefonoa());
		assertEquals("87654321",s1.getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getPasahitza());
	}
	
	@Test
	public void testSukurtsalaSetBezeroa() {
		// Bezerorako Sortzailea	
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", "0/0/932/130", "src-res-bbk_logo.png");
		
		Sukurtsala s1 = new Sukurtsala("1", "13","Gallarraga kalea", eb1);
		
		EntitateBankario eb2 = new EntitateBankario ("BBVA", "2", "ES36", "092/567/900/810", "bbva.png");
		
		// Setters
		s1.setIdSukurtsala("2");
		s1.setKodSukurtsala("11");
		s1.setKokalekua("Autonomia kalea");
		s1.setEntitateBankario(eb2);
		
		assertEquals("2",s1.getIdSukurtsala());
		assertEquals("11", s1.getKodSukurtsala());
		assertEquals("Autonomia kalea",s1.getKokalekua());
		
		assertEquals("BBVA", s1.getEntitateBankario().getIzena());
		assertEquals("2",s1.getEntitateBankario().getIdEntitatea());
		assertEquals("ES36", s1.getEntitateBankario().getEntitateZbk());
		assertEquals("092/567/900/810", s1.getEntitateBankario().getBounds());
		assertEquals("bbva.png", s1.getEntitateBankario().getUrl());
	}
	
	@Test
	public void testSukurtsalaToString() {
		DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Transferentzia t1 = new Transferentzia (100, "04-23-2008", "proba", "ES1245784512346751245124");
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
		Hipoteka h1 = new Hipoteka (100000, 50000, 10, "03-12-2010", "06-10-2020", "eskatuta", "10 urte");
		
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
		Txartela txartela = new Txartela("455", "debito", b1);
		
		ArrayList<Txartela> txartelak = new ArrayList<Txartela>();
		
		txartelak.add(txartela);
		
		KontuBankario kb1 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "aktiboa", diruSarrerak, transferentziak, h1, txartelak);
		
		ArrayList<KontuBankario> KontuBankarioak = new ArrayList<KontuBankario>();
		KontuBankarioak.add(kb1);
		
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", "0/0/932/130", "src-res-bbk_logo.png");
		
		Sukurtsala s1 = new Sukurtsala("1", "13","Gallarraga kalea", eb1, KontuBankarioak);
		
		// ToString
		assertEquals("Sukurtsala idSukurtsala=" + s1.getIdSukurtsala() + ", kodSukurtsala=" + s1.getKodSukurtsala() + ", kokalekua="
				+ s1.getKokalekua() + ", entitateBankario=" + s1.getEntitateBankario(), s1.toString());
	}
	
	@Test
	public void testSukurtsalaEquals() {
		DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Transferentzia t1 = new Transferentzia (100, "04-23-2008", "proba", "ES1245784512346751245124");
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
		Hipoteka h1 = new Hipoteka (100000, 50000, 10, "03-12-2010", "06-10-2020", "eskatuta", "10 urte");
		
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
		Txartela txartela = new Txartela("455", "debito", b1);
		
		ArrayList<Txartela> txartelak = new ArrayList<Txartela>();
		
		txartelak.add(txartela);
		
		KontuBankario kb1 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "aktiboa", diruSarrerak, transferentziak, h1, txartelak);
		
		ArrayList<KontuBankario> KontuBankarioak = new ArrayList<KontuBankario>();
		KontuBankarioak.add(kb1);
		
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", "0/0/932/130", "src-res-bbk_logo.png");
		
		Sukurtsala s1 = new Sukurtsala("1", "13","Gallarraga kalea", eb1, KontuBankarioak);
		Sukurtsala s2 = new Sukurtsala("1", "13","Gallarraga kalea", eb1, KontuBankarioak);
		Sukurtsala s3 = new Sukurtsala();
		
		// Equals
		assertTrue(s1.equals(s2));
		assertFalse(s3.equals(null));
	}

}
