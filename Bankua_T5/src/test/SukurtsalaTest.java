package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import model.DiruSarrera;
import model.Hipoteka;
import model.KontuBankarioa;
import model.Sukurtsala;
import model.Transferentzia;
import model.KontuBankarioa.Egoera;

public class SukurtsalaTest {

	@Test
	public void testSukurtsalaConsGet() {
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
		
		ArrayList<KontuBankarioa> KontuBankarioak = new ArrayList<KontuBankarioa>();
		KontuBankarioak.add(kb1);
		
		Sukurtsala s1 = new Sukurtsala(1, "Gallarraga kalea", 48830, KontuBankarioak);
		
		// Getters
		assertEquals(1,s1.getIdSukurtsala());
		assertEquals("Gallarraga kalea",s1.getKokalekua());
		assertEquals(48830,s1.getPostaKodea());
		assertEquals("ES1576197348527531954865", s1.getKontuBankarioak().get(0).getIban());
		assertEquals(2000, s1.getKontuBankarioak().get(0).getSaldoa());
		assertEquals(100, s1.getKontuBankarioak().get(0).getHilekoLimitea());
		assertEquals(sorreraData, s1.getKontuBankarioak().get(0).getSorreraData());
		assertEquals(Egoera.aktiboa, s1.getKontuBankarioak().get(0).getEgoera());
		assertEquals(1, s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIdSarrera());
		assertEquals(100, s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKantitatea());
		assertEquals(sarreraData, s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera", s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1245784512346751245124", s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIgortzailea());
		assertEquals(1, s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getIdTransferentzia());
		assertEquals(100, s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getKantitatea());
		assertEquals(transferentziaData, s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba", s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245784512346751245124", s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getJasotzailea());
		assertEquals(10, s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getKomisioa());
		assertEquals(1, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getIdHipoteka());
		assertEquals(100000, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getKantitatea());
		assertEquals(50000, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getOrdaindutakoa());
		assertEquals(10, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getKomisioa());
		assertEquals(hasieraData, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getHasieraData());
		assertEquals(amaieraData, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getAmaieraData());
	}
	
	@Test
	public void testSukurtsalaSet() {
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
		
		ArrayList<KontuBankarioa> KontuBankarioak = new ArrayList<KontuBankarioa>();
		KontuBankarioak.add(kb1);
		
		Sukurtsala s1 = new Sukurtsala(1, "Gallarraga kalea", 48830, KontuBankarioak);
		
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
		
		ArrayList<KontuBankarioa> KontuBankarioak2 = new ArrayList<KontuBankarioa>();
		KontuBankarioak2.add(kb2);
		
		// Setters
		s1.setIdSukurtsala(2);
		s1.setKokalekua("Autonomia kalea");
		s1.setPostaKodea(48001);
		s1.setKontuBankarioak(KontuBankarioak2);
		
		assertEquals(2,s1.getIdSukurtsala());
		assertEquals("Autonomia kalea",s1.getKokalekua());
		assertEquals(48001,s1.getPostaKodea());
		assertEquals("ES1576197348527531958261", s1.getKontuBankarioak().get(0).getIban());
		assertEquals(3000, s1.getKontuBankarioak().get(0).getSaldoa());
		assertEquals(200, s1.getKontuBankarioak().get(0).getHilekoLimitea());
		assertEquals(sorreraData, s1.getKontuBankarioak().get(0).getSorreraData());
		assertEquals(Egoera.izoztuta, s1.getKontuBankarioak().get(0).getEgoera());
		assertEquals(2, s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIdSarrera());
		assertEquals(200, s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKantitatea());
		assertEquals(sarreraData, s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera2", s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1297514512626751245124", s1.getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIgortzailea());
		assertEquals(3, s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getIdTransferentzia());
		assertEquals(300, s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getKantitatea());
		assertEquals(transferentziaData, s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba2", s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245709142346751245124", s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getJasotzailea());
		assertEquals(10, s1.getKontuBankarioak().get(0).getTransferentziak().get(0).getKomisioa());
		assertEquals(2, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getIdHipoteka());
		assertEquals(200000, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getKantitatea());
		assertEquals(70000, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getOrdaindutakoa());
		assertEquals(10, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getKomisioa());
		assertEquals(hasieraData, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getHasieraData());
		assertEquals(amaieraData, s1.getKontuBankarioak().get(0).getHipotekak().get(0).getAmaieraData());
	}
	
	@Test
	public void testSukurtsalaToString() {
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
		
		ArrayList<KontuBankarioa> KontuBankarioak = new ArrayList<KontuBankarioa>();
		KontuBankarioak.add(kb1);
		
		Sukurtsala s1 = new Sukurtsala(1, "Gallarraga kalea", 48830, KontuBankarioak);
		
		// ToString
		assertEquals("Sukurtsala idSukurtsala=" + s1.getIdSukurtsala() + ", kokalekua=" + s1.getKokalekua() + ", postaKodea=" + s1.getPostaKodea()
				+ ", kontuBankarioak=" + s1.getKontuBankarioak(), s1.toString());
	}
	
	@Test
	public void testSukurtsalaEquals() {
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
		
		ArrayList<KontuBankarioa> KontuBankarioak = new ArrayList<KontuBankarioa>();
		KontuBankarioak.add(kb1);
		
		Sukurtsala s1 = new Sukurtsala(1, "Gallarraga kalea", 48830, KontuBankarioak);
		Sukurtsala s2 = new Sukurtsala(1, "Gallarraga kalea", 48830, KontuBankarioak);
		
		// Equals
		assertTrue(s1.equals(s2));
	}

}
