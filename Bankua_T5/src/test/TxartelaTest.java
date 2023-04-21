package test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;

import model.DiruSarrera;
import model.Hipoteka;
import model.KontuBankarioa;
import model.KontuBankarioa.Egoera;
import model.Pertsona;
import model.Pertsona.Generoa;
import model.Transferentzia;
import model.Txartela;
import model.Txartela.Mota;

public class TxartelaTest {

	@Test
	void testTxartelaConsGet() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
		Pertsona p1 = new Pertsona ("12345678A", "Juan", "Perez", jaiotzeData,Generoa.gizona, "111222333", "12345678");
		
		ArrayList<Pertsona> pertsonak= new ArrayList<Pertsona>();
		pertsonak.add(p1);
		
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
		
		ArrayList<KontuBankarioa> kontuBankarioak = new ArrayList<KontuBankarioa>();
		kontuBankarioak.add(kb1);
		
		Txartela txartela = new Txartela("123145677894",455,Mota.debito, kontuBankarioak,pertsonak);
		
		// Getters
		assertEquals("123145677894", txartela.getIdTxartela());
		assertEquals(455, txartela.getSegurtasunKodea());
		assertEquals(Mota.debito, txartela.getMota());
		assertEquals("ES1576197348527531954865", txartela.getKontuBankarioak().get(0).getIban());
		assertEquals(2000, txartela.getKontuBankarioak().get(0).getSaldoa(), 0.01);
		assertEquals(100, txartela.getKontuBankarioak().get(0).getHilekoLimitea(), 0.01);
		assertEquals(sorreraData, txartela.getKontuBankarioak().get(0).getSorreraData());
		assertEquals(Egoera.aktiboa, txartela.getKontuBankarioak().get(0).getEgoera());
		assertEquals(1, txartela.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIdSarrera());
		assertEquals(100, txartela.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals(sarreraData, txartela.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera", txartela.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1245784512346751245124", txartela.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIgortzailea());
		assertEquals(1, txartela.getKontuBankarioak().get(0).getTransferentziak().get(0).getIdTransferentzia());
		assertEquals(100, txartela.getKontuBankarioak().get(0).getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals(transferentziaData, txartela.getKontuBankarioak().get(0).getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba", txartela.getKontuBankarioak().get(0).getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245784512346751245124", txartela.getKontuBankarioak().get(0).getTransferentziak().get(0).getJasotzailea());
		assertEquals(10, txartela.getKontuBankarioak().get(0).getTransferentziak().get(0).getKomisioa(), 0.01);
		assertEquals(1, txartela.getKontuBankarioak().get(0).getHipotekak().get(0).getIdHipoteka());
		assertEquals(100000, txartela.getKontuBankarioak().get(0).getHipotekak().get(0).getKantitatea(), 0.01);
		assertEquals(50000, txartela.getKontuBankarioak().get(0).getHipotekak().get(0).getOrdaindutakoa(), 0.01);
		assertEquals(10, txartela.getKontuBankarioak().get(0).getHipotekak().get(0).getKomisioa(), 0.01);
		assertEquals(hasieraData, txartela.getKontuBankarioak().get(0).getHipotekak().get(0).getHasieraData());
		assertEquals(amaieraData, txartela.getKontuBankarioak().get(0).getHipotekak().get(0).getAmaieraData());
		assertEquals("12345678A",txartela.getPertsonak().get(0).getNan());
		assertEquals("Juan",txartela.getPertsonak().get(0).getIzena());
		assertEquals("Perez",txartela.getPertsonak().get(0).getAbizena());
		assertEquals(jaiotzeData,txartela.getPertsonak().get(0).getJaiotzeData());
		assertEquals(Generoa.gizona,txartela.getPertsonak().get(0).getSexua());
		assertEquals("111222333",txartela.getPertsonak().get(0).getTelefonoa());
		assertEquals("12345678",txartela.getPertsonak().get(0).getPasahitza());
	}
	
	@Test
	void testTxartelaSet() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
		Pertsona p1 = new Pertsona ("12345678A", "Juan", "Perez", jaiotzeData,Generoa.gizona, "111222333", "12345678");
		
		ArrayList<Pertsona> pertsonak= new ArrayList<Pertsona>();
		pertsonak.add(p1);
		
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
		
		ArrayList<KontuBankarioa> kontuBankarioak = new ArrayList<KontuBankarioa>();
		kontuBankarioak.add(kb1);
		
		Txartela txartela = new Txartela("123145677894",455,Mota.debito, kontuBankarioak,pertsonak);
		
		cal.set(Calendar.DAY_OF_MONTH, 15);
		cal.set(Calendar.MONTH, 5);
		cal.set(Calendar.YEAR, 2001);
		jaiotzeData = cal.getTime();
		
		Pertsona p2 = new Pertsona ("12345678B", "Laura", "Sanchez", jaiotzeData,Generoa.emakumea, "444555666", "87654321");
		
		ArrayList<Pertsona> pertsonak2 = new ArrayList<Pertsona>();
		pertsonak2.add(p2);
		
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
		
		KontuBankarioa kb2 = new KontuBankarioa("ES1576197348527531958261", 3000, 200, sorreraData, Egoera.izoztuta, diruSarrerak2,transferentziak2, hipotekak2);
		
		ArrayList<KontuBankarioa> kontuBankarioak2 = new ArrayList<KontuBankarioa>();
		kontuBankarioak2.add(kb2);
		
		// Setters
		txartela.setIdTxartela("162835162738");
		txartela.setSegurtasunKodea(860);
		txartela.setMota(Mota.kredito);
		txartela.setKontuBankarioak(kontuBankarioak2);
		txartela.setPertsonak(pertsonak2);
		
		
		assertEquals("162835162738", txartela.getIdTxartela());
		assertEquals(860, txartela.getSegurtasunKodea());
		assertEquals(Mota.kredito, txartela.getMota());
		assertEquals("ES1576197348527531958261", txartela.getKontuBankarioak().get(0).getIban());
		assertEquals(3000, txartela.getKontuBankarioak().get(0).getSaldoa(), 0.01);
		assertEquals(200, txartela.getKontuBankarioak().get(0).getHilekoLimitea(), 0.01);
		assertEquals(sorreraData, txartela.getKontuBankarioak().get(0).getSorreraData());
		assertEquals(Egoera.izoztuta, txartela.getKontuBankarioak().get(0).getEgoera());
		assertEquals(2, txartela.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIdSarrera());
		assertEquals(200, txartela.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals(sarreraData, txartela.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera2", txartela.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1297514512626751245124", txartela.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIgortzailea());
		assertEquals(3, txartela.getKontuBankarioak().get(0).getTransferentziak().get(0).getIdTransferentzia());
		assertEquals(300, txartela.getKontuBankarioak().get(0).getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals(transferentziaData, txartela.getKontuBankarioak().get(0).getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba2", txartela.getKontuBankarioak().get(0).getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245709142346751245124", txartela.getKontuBankarioak().get(0).getTransferentziak().get(0).getJasotzailea());
		assertEquals(10, txartela.getKontuBankarioak().get(0).getTransferentziak().get(0).getKomisioa(), 0.01);
		assertEquals(2, txartela.getKontuBankarioak().get(0).getHipotekak().get(0).getIdHipoteka());
		assertEquals(200000, txartela.getKontuBankarioak().get(0).getHipotekak().get(0).getKantitatea(), 0.01);
		assertEquals(70000, txartela.getKontuBankarioak().get(0).getHipotekak().get(0).getOrdaindutakoa(), 0.01);
		assertEquals(10, txartela.getKontuBankarioak().get(0).getHipotekak().get(0).getKomisioa(), 0.01);
		assertEquals(hasieraData, txartela.getKontuBankarioak().get(0).getHipotekak().get(0).getHasieraData());
		assertEquals(amaieraData, txartela.getKontuBankarioak().get(0).getHipotekak().get(0).getAmaieraData());
		assertEquals("12345678B",txartela.getPertsonak().get(0).getNan());
		assertEquals("Laura",txartela.getPertsonak().get(0).getIzena());
		assertEquals("Sanchez",txartela.getPertsonak().get(0).getAbizena());
		assertEquals(jaiotzeData,txartela.getPertsonak().get(0).getJaiotzeData());
		assertEquals(Generoa.emakumea,txartela.getPertsonak().get(0).getSexua());
		assertEquals("444555666",txartela.getPertsonak().get(0).getTelefonoa());
		assertEquals("87654321",txartela.getPertsonak().get(0).getPasahitza());
	}
	
	@Test
	void testTxartelaToString() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
		Pertsona p1 = new Pertsona ("12345678A", "Juan", "Perez", jaiotzeData,Generoa.gizona, "111222333", "12345678");
		
		ArrayList<Pertsona> pertsonak= new ArrayList<Pertsona>();
		pertsonak.add(p1);
		
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
		
		ArrayList<KontuBankarioa> kontuBankarioak = new ArrayList<KontuBankarioa>();
		kontuBankarioak.add(kb1);
		
		Txartela txartela = new Txartela("123145677894",455,Mota.debito, kontuBankarioak,pertsonak);
		
		// ToString
		assertEquals("Txartela idTxartela=" + txartela.getIdTxartela() + ", segurtasunKodea=" + txartela.getSegurtasunKodea() + ", mota=" + txartela.getMota()
				+ ", kontuBankarioak=" + txartela.getKontuBankarioak() + ", pertsonak=" + txartela.getPertsonak(), txartela.toString());
	}
	
	@Test
	void testTxartelaEquals() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 20);
		cal.set(Calendar.MONTH, 0);
		cal.set(Calendar.YEAR, 1999);
		Date jaiotzeData = cal.getTime();
		Pertsona p1 = new Pertsona ("12345678A", "Juan", "Perez", jaiotzeData,Generoa.gizona, "111222333", "12345678");
		
		ArrayList<Pertsona> pertsonak= new ArrayList<Pertsona>();
		pertsonak.add(p1);
		
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
		
		ArrayList<KontuBankarioa> kontuBankarioak = new ArrayList<KontuBankarioa>();
		kontuBankarioak.add(kb1);
		
		Txartela txartela = new Txartela("123145677894",455,Mota.debito, kontuBankarioak,pertsonak);
		Txartela txartela2 = new Txartela("123145677894",455,Mota.debito, kontuBankarioak,pertsonak);
		
		// Equals
		assertTrue(txartela.equals(txartela2));
	}

}
