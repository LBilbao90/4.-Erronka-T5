package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Bezeroa;
import model.DiruSarrera;
import model.Hipoteka;
import model.KontuBankario;
import model.Transferentzia;
import model.Txartela;

public class TxartelaTest {

	@Test
	public void testTxartelaConsGet() {
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
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

		
		Txartela txartela = new Txartela("123145677894",455, "debito", kb1, b1);
		
		// Getters
		assertEquals("123145677894", txartela.getIdTxartela());
		assertEquals(455, txartela.getSegurtasunKodea());
		assertEquals("debito", txartela.getMota());
		assertEquals("ES1576197348527531954865", txartela.getKontuBankarioa().getIban());
		assertEquals(2000, txartela.getKontuBankarioa().getSaldoa(), 0.01);
		assertEquals(100, txartela.getKontuBankarioa().getHilekoLimitea(), 0.01);
		assertEquals("01-20-2003", txartela.getKontuBankarioa().getSorreraData());
		assertEquals("aktiboa", txartela.getKontuBankarioa().getEgoera());
		assertEquals(100, txartela.getKontuBankarioa().getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("03-19-2023", txartela.getKontuBankarioa().getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera", txartela.getKontuBankarioa().getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1245784512346751245124", txartela.getKontuBankarioa().getDiruSarrerak().get(0).getIgortzailea());
		assertEquals(100, txartela.getKontuBankarioa().getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("04-23-2008", txartela.getKontuBankarioa().getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba", txartela.getKontuBankarioa().getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245784512346751245124", txartela.getKontuBankarioa().getTransferentziak().get(0).getJasotzailea());
		assertEquals(2, txartela.getKontuBankarioa().getTransferentziak().get(0).getKomisioa(), 0.01);
		assertEquals(100000, txartela.getKontuBankarioa().getHipotekak().get(0).getKantitatea(), 0.01);
		assertEquals(50000, txartela.getKontuBankarioa().getHipotekak().get(0).getOrdaindutakoa(), 0.01);
		assertEquals(10, txartela.getKontuBankarioa().getHipotekak().get(0).getKomisioa(), 0.01);
		assertEquals("03-12-2010", txartela.getKontuBankarioa().getHipotekak().get(0).getHasieraData());
		assertEquals("06-10-2020", txartela.getKontuBankarioa().getHipotekak().get(0).getAmaieraData());
		assertEquals("eskatuta", txartela.getKontuBankarioa().getHipotekak().get(0).getEgoera());
		assertEquals("12345678A",txartela.getBezeroa().getNan());
		assertEquals("Juan",txartela.getBezeroa().getIzena());
		assertEquals("Perez",txartela.getBezeroa().getAbizena());
		assertEquals("10-21-2002",txartela.getBezeroa().getJaiotzeData());
		assertEquals("gizona",txartela.getBezeroa().getSexua());
		assertEquals("111222333",txartela.getBezeroa().getTelefonoa());
		assertEquals("12345678",txartela.getBezeroa().getPasahitza());
	}
	
	@Test
	public void testTxartelaSet() {
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
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

		
		Txartela txartela = new Txartela("123145677894",455, "debito", kb1, b1);
		
		
		Bezeroa b2 = new Bezeroa("12345678B", "Laura", "Sanchez", "08-19-2002", "emakumea", "444555666", "87654321");
		
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
		
		// Setters
		txartela.setIdTxartela("162835162738");
		txartela.setSegurtasunKodea(860);
		txartela.setMota("kredito");
		txartela.setKontuBankarioa(kb2);
		txartela.setBezeroa(b2);
		
		
		assertEquals("162835162738", txartela.getIdTxartela());
		assertEquals(860, txartela.getSegurtasunKodea());
		assertEquals("kredito", txartela.getMota());
		assertEquals("ES1576197348527531958261", txartela.getKontuBankarioa().getIban());
		assertEquals(3000, txartela.getKontuBankarioa().getSaldoa(), 0.01);
		assertEquals(200, txartela.getKontuBankarioa().getHilekoLimitea(), 0.01);
		assertEquals("08-22-2004", txartela.getKontuBankarioa().getSorreraData());
		assertEquals("izoztuta", txartela.getKontuBankarioa().getEgoera());
		assertEquals(200, txartela.getKontuBankarioa().getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("09-10-2003", txartela.getKontuBankarioa().getDiruSarrerak().get(0).getSarreraData());
		assertEquals("sarrera2", txartela.getKontuBankarioa().getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES1297514512626751245124", txartela.getKontuBankarioa().getDiruSarrerak().get(0).getIgortzailea());
		assertEquals(300, txartela.getKontuBankarioa().getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("08-17-2023", txartela.getKontuBankarioa().getTransferentziak().get(0).getTransferentziaData());
		assertEquals("proba2", txartela.getKontuBankarioa().getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES1245709142346751245124", txartela.getKontuBankarioa().getTransferentziak().get(0).getJasotzailea());
		assertEquals(2, txartela.getKontuBankarioa().getTransferentziak().get(0).getKomisioa(), 0.01);
		assertEquals(200000, txartela.getKontuBankarioa().getHipotekak().get(0).getKantitatea(), 0.01);
		assertEquals(70000, txartela.getKontuBankarioa().getHipotekak().get(0).getOrdaindutakoa(), 0.01);
		assertEquals(10, txartela.getKontuBankarioa().getHipotekak().get(0).getKomisioa(), 0.01);
		assertEquals("06-15-2012", txartela.getKontuBankarioa().getHipotekak().get(0).getHasieraData());
		assertEquals("07-11-2022", txartela.getKontuBankarioa().getHipotekak().get(0).getAmaieraData());
		assertEquals("onartuta", txartela.getKontuBankarioa().getHipotekak().get(0).getEgoera());
		assertEquals("12345678B",txartela.getBezeroa().getNan());
		assertEquals("Laura",txartela.getBezeroa().getIzena());
		assertEquals("Sanchez",txartela.getBezeroa().getAbizena());
		assertEquals("08-19-2002",txartela.getBezeroa().getJaiotzeData());
		assertEquals("emakumea",txartela.getBezeroa().getSexua());
		assertEquals("444555666",txartela.getBezeroa().getTelefonoa());
		assertEquals("87654321",txartela.getBezeroa().getPasahitza());
	}
	
	@Test
	public void testTxartelaToString() {
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
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

		
		Txartela txartela = new Txartela("123145677894",455, "debito", kb1, b1);
		
		// ToString
		assertEquals("Txartela idTxartela=" + txartela.getIdTxartela() + ", segurtasunKodea=" + txartela.getSegurtasunKodea() + ", mota=" + txartela.getMota()
				+ ", kontuBankarioa=" + txartela.getKontuBankarioa() + ", bezeroa=" + txartela.getBezeroa(), txartela.toString());
	}
	
	@Test
	public void testTxartelaEquals() {
		Bezeroa b1 = new Bezeroa ("12345678A", "Juan", "Perez", "10-21-2002", "gizona", "111222333", "12345678");
		
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

		
		Txartela txartela = new Txartela("123145677894",455, "debito", kb1, b1);
		Txartela txartela2 = new Txartela("123145677894",455, "debito", kb1, b1);
		Txartela txartela3 = new Txartela();
		
		// Equals
		assertTrue(txartela.equals(txartela2));
		assertFalse(txartela3.equals(null));
	}

}
