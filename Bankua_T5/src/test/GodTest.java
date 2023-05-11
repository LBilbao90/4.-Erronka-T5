package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Bezeroa;
import model.DiruSarrera;
import model.EntitateBankario;
import model.God;
import model.Hipoteka;
import model.KontuBankario;
import model.Sukurtsala;
import model.Transferentzia;
import model.Txartela;

public class GodTest {

	@Test
	public void testGodConsGet() {
		DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Transferentzia t1 = new Transferentzia (100, "04-23-2008", "proba", "ES1245784512346751245124");
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
		Hipoteka h1 = new Hipoteka (100000, 50000, "10", "03-12-2010", "06-10-2020", "eskatuta", "10 urte");
		
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
		Txartela txartela = new Txartela("455", "debito", b1);
		
		ArrayList<Txartela> txartelak = new ArrayList<Txartela>();
		
		txartelak.add(txartela);
		
		KontuBankario kb1 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "aktiboa", diruSarrerak, transferentziak, h1, txartelak);
		
		ArrayList<KontuBankario> KontuBankarioak = new ArrayList<KontuBankario>();
		KontuBankarioak.add(kb1);
		
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", "0/0/932/130", "src-res-bbk_logo.png");
		
		Sukurtsala s1 = new Sukurtsala("1", "13","Gallarraga kalea", eb1, KontuBankarioak);
		
		ArrayList<Sukurtsala> sukurtsalak = new ArrayList<Sukurtsala>();
		sukurtsalak.add(s1);
		
    	God g1 = new God("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678", "Gerentea", sukurtsalak);
    	
    	// Getters
    	assertEquals("12345678A", g1.getNan());
		assertEquals("Juan", g1.getIzena());
		assertEquals("Perez", g1.getAbizena());
		assertEquals("10-21-2002", g1.getJaiotzeData());
		assertEquals("gizona", g1.getSexua());
		assertEquals("111222333", g1.getTelefonoa());
		assertEquals("12345678", g1.getPasahitza());
		
		assertEquals("1",g1.getSukurtsalak().get(0).getIdSukurtsala());
		assertEquals("13", g1.getSukurtsalak().get(0).getKodSukurtsala());
		assertEquals("Gallarraga kalea",g1.getSukurtsalak().get(0).getKokalekua());
		
		assertEquals("BBK", g1.getSukurtsalak().get(0).getEntitateBankario().getIzena());
		assertEquals("1",g1.getSukurtsalak().get(0).getEntitateBankario().getIdEntitatea());
		assertEquals("ES25", g1.getSukurtsalak().get(0).getEntitateBankario().getEntitateZbk());
		assertEquals("0/0/932/130", g1.getSukurtsalak().get(0).getEntitateBankario().getBounds());
		assertEquals("src-res-bbk_logo.png", g1.getSukurtsalak().get(0).getEntitateBankario().getUrl());
		
		assertEquals("ES1576197348527531954865", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getIban());
		assertEquals(2000, g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getSaldoa(), 0.01);
		assertEquals(100, g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHilekoLimitea(), 0.01);
		assertEquals("01-20-2003", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getSorreraData());
		assertEquals("aktiboa", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getEgoera());
		
		assertEquals(100, g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("03-19-2023", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1245784512346751245124", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIgortzailea());
		
		assertEquals(100, g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("04-23-2008", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba", s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245784512346751245124", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getJasotzailea());
		assertEquals("1.5", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKomisioa());
		
		assertEquals(100000, g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getKantitatea(), 0.01);
		assertEquals(50000, g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getOrdaindutakoa(), 0.01);
		assertEquals("10", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getKomisioa());
		assertEquals("03-12-2010", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getHasieraData());
		assertEquals("06-10-2020", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getAmaieraData());
		assertEquals("eskatuta", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getEgoera());
		assertEquals("10 urte", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getEpeMuga());
		
		assertEquals("455", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getSegurtasunKodea());
		assertEquals("debito", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getMota());
		assertEquals("12345678A",g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getNan());
		assertEquals("Juan",g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getIzena());
		assertEquals("Perez",g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getAbizena());
		assertEquals("10-21-2002",g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getJaiotzeData());
		assertEquals("gizona",g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getSexua());
		assertEquals("111222333",g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getTelefonoa());
		assertEquals("12345678",g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getPasahitza());
	}
	
	@Test
	public void testGodConsSet() {
DiruSarrera ds1 = new DiruSarrera(100, "03-19-2023", "sarrera", "ES1245784512346751245124");
		
		ArrayList<DiruSarrera> diruSarrerak = new ArrayList<DiruSarrera> ();
		diruSarrerak.add(ds1);
		
		Transferentzia t1 = new Transferentzia (100, "04-23-2008", "proba", "ES1245784512346751245124");
		
		ArrayList<Transferentzia> transferentziak = new ArrayList<Transferentzia>();
		transferentziak.add(t1);
		
		Hipoteka h1 = new Hipoteka (100000, 50000, "10", "03-12-2010", "06-10-2020", "eskatuta", "10 urte");
		
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
		Txartela txartela = new Txartela("455", "debito", b1);
		
		ArrayList<Txartela> txartelak = new ArrayList<Txartela>();
		
		txartelak.add(txartela);
		
		KontuBankario kb1 = new KontuBankario("ES1576197348527531954865", 2000, 100, "01-20-2003", "aktiboa", diruSarrerak, transferentziak, h1, txartelak);
		
		ArrayList<KontuBankario> KontuBankarioak = new ArrayList<KontuBankario>();
		KontuBankarioak.add(kb1);
		
		EntitateBankario eb1 = new EntitateBankario ("BBK", "1", "ES25", "0/0/932/130", "src-res-bbk_logo.png");
		
		Sukurtsala s1 = new Sukurtsala("1", "13","Gallarraga kalea", eb1, KontuBankarioak);
		
		ArrayList<Sukurtsala> sukurtsalak = new ArrayList<Sukurtsala>();
		sukurtsalak.add(s1);
		
    	God g1 = new God("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678", "Gerentea", sukurtsalak);
    	
    	
    	DiruSarrera ds2 = new DiruSarrera(200, "09-10-2003", "sarrera2", "ES1297514512626751245124");
		
		ArrayList<DiruSarrera> diruSarrerak2 = new ArrayList<DiruSarrera> ();
		diruSarrerak2.add(ds2);
		
		Transferentzia t2 = new Transferentzia (300, "08-17-2023", "proba2", "ES1245709142346751245124");
		
		ArrayList<Transferentzia> transferentziak2 = new ArrayList<Transferentzia>();
		transferentziak2.add(t2);
		
		Hipoteka h2 = new Hipoteka (200000, 70000, "10", "06-15-2011", "07-11-2022", "onartuta", "11 urte");
		
		Bezeroa b2 = new Bezeroa("12345678B", "Laura", "Sanchez", "08-19-2002", "emakumea", "444555666", "87654321");
		
		Txartela txartela2 = new Txartela("860", "kredito", b2);
		
		ArrayList<Txartela> txartelak2 = new ArrayList<Txartela>();
		txartelak2.add(txartela2);
		
		KontuBankario kb2 = new KontuBankario("ES1576197348527531958261", 3000, 200, "08-22-2004", "izoztuta", diruSarrerak2,transferentziak2, h2, txartelak2);
		
		ArrayList<KontuBankario> KontuBankarioak2 = new ArrayList<KontuBankario>();
		KontuBankarioak2.add(kb2);
		
		EntitateBankario eb2 = new EntitateBankario ("BBVA", "2", "ES36", "092/567/900/810", "bbva.png");
		
		Sukurtsala s2 = new Sukurtsala("2", "11","Autonomia kalea", eb2, KontuBankarioak2);
		
		ArrayList<Sukurtsala> sukurtsalak2 = new ArrayList<Sukurtsala>();
		sukurtsalak2.add(s2);
		
		// Setters
		g1.setNan("12345678B");
		g1.setIzena("Laura");
		g1.setAbizena("Sanchez");
		g1.setJaiotzeData("08-19-2002");
		g1.setSexua("emakumea");
		g1.setTelefonoa("444555666");
		g1.setPasahitza("87654321");
		g1.setLanpostu("Zuzendaria");
		g1.setSukurtsalak(sukurtsalak2);
		
		assertEquals("12345678B", g1.getNan());
		assertEquals("Laura", g1.getIzena());
		assertEquals("Sanchez", g1.getAbizena());
		assertEquals("08-19-2002", g1.getJaiotzeData());
		assertEquals("emakumea", g1.getSexua());
		assertEquals("444555666", g1.getTelefonoa());
		assertEquals("87654321", g1.getPasahitza());
		assertEquals("Zuzendaria", g1.getLanpostu());
		
		assertEquals("2",g1.getSukurtsalak().get(0).getIdSukurtsala());
		assertEquals("11", g1.getSukurtsalak().get(0).getKodSukurtsala());
		assertEquals("Autonomia kalea",g1.getSukurtsalak().get(0).getKokalekua());
		
		assertEquals("BBVA", g1.getSukurtsalak().get(0).getEntitateBankario().getIzena());
		assertEquals("2",g1.getSukurtsalak().get(0).getEntitateBankario().getIdEntitatea());
		assertEquals("ES36", g1.getSukurtsalak().get(0).getEntitateBankario().getEntitateZbk());
		assertEquals("092/567/900/810", g1.getSukurtsalak().get(0).getEntitateBankario().getBounds());
		assertEquals("bbva.png", g1.getSukurtsalak().get(0).getEntitateBankario().getUrl());
		
		assertEquals("ES1576197348527531958261", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getIban());
		assertEquals(3000, g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getSaldoa(), 0.01);
		assertEquals(200, g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHilekoLimitea(), 0.01);
		assertEquals("08-22-2004", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getSorreraData());
		assertEquals("izoztuta", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getEgoera());
		
		assertEquals(200, g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("09-10-2003", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera2", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1297514512626751245124", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIgortzailea());
		
		assertEquals(300, g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("08-17-2023", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba2", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245709142346751245124", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getJasotzailea());
		assertEquals("1.5", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKomisioa());
		
		assertEquals(200000, g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getKantitatea(), 0.01);
		assertEquals(70000, g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getOrdaindutakoa(), 0.01);
		assertEquals("10", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getKomisioa());
		assertEquals("06-15-2011", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getHasieraData());
		assertEquals("07-11-2022", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getAmaieraData());
		assertEquals("onartuta", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getEgoera());
		assertEquals("11 urte", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getEpeMuga());
		
		assertEquals("860", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getSegurtasunKodea());
		assertEquals("kredito", g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getMota());
		assertEquals("12345678B",g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getNan());
		assertEquals("Laura",g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getIzena());
		assertEquals("Sanchez",g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getAbizena());
		assertEquals("08-19-2002",g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getJaiotzeData());
		assertEquals("emakumea",g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getSexua());
		assertEquals("444555666",g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getTelefonoa());
		assertEquals("87654321",g1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getPasahitza());
	}
	
}
