package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import controlador.Metodoak;
import model.Bezeroa;
import model.EntitateBankario;
import model.Langilea;

public class MetodoakTest {

	@Test // mirar
	public void testBezeroakKargatu() {
		Metodoak metodoak = new Metodoak();
		Bezeroa b1 = metodoak.bezeroaKargatu("12345678A");
		
		assertEquals("12345678A", b1.getNan());
		assertEquals("Aingeru", b1.getIzena());
		assertEquals("Siranaula", b1.getAbizena());
		assertEquals("10-21-2002", b1.getJaiotzeData());
		assertEquals("gizona", b1.getSexua());
		assertEquals("111222333", b1.getTelefonoa());
		assertEquals("12345678", b1.getPasahitza());
	}
	
	@Test
	public void testBezeroaLogin() {
		Metodoak metodoak = new Metodoak();
		
		assertTrue(metodoak.bezeroLogin("12345678A", "12345678"));
	}
	
	@Test
	public void testLangileLogin() {
		Metodoak metodoak = new Metodoak();
		
		assertEquals("zuzendaria", metodoak.langileLogin("12345678B", "1234"));
	}
	
	@Test
	public void testBotoiakSortu() {
		Metodoak metodoak = new Metodoak();
		ArrayList<EntitateBankario> botoiakEntitate = metodoak.botoiakSortu();
		
		assertEquals("1", botoiakEntitate.get(0).getIdEntitatea());
		assertEquals("BBK", botoiakEntitate.get(0).getIzena());
		assertEquals("ES972345", botoiakEntitate.get(0).getEntitateZbk());
		assertEquals("218/253/128/45", botoiakEntitate.get(0).getBounds());
		assertEquals("src/res/bbk_logo.png", botoiakEntitate.get(0).getUrl());
	}
	
	@Test
	public void testLangileaKargatu() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("12345678B", "zuzendaria");
		
		assertEquals("12345678B", l1.getNan());
		assertEquals("Ibai", l1.getIzena());
		assertEquals("Alvarez", l1.getAbizena());
		assertEquals("10-21-2000", l1.getJaiotzeData());
		assertEquals("gizona", l1.getSexua());
		assertEquals("111222333", l1.getTelefonoa());
		assertEquals("1234", l1.getPasahitza());
		assertEquals("zuzendaria", l1.getLanpostu());
		
		assertEquals("1",l1.getSukurtsalak().get(0).getIdSukurtsala());
		assertEquals("0111", l1.getSukurtsalak().get(0).getKodSukurtsala());
		assertEquals("Santutxu, Santutxu Kalea, 27",l1.getSukurtsalak().get(0).getKokalekua());
		
		assertEquals("BBK", l1.getSukurtsalak().get(0).getEntitateBankario().getIzena());
		assertEquals("1",l1.getSukurtsalak().get(0).getEntitateBankario().getIdEntitatea());
		assertEquals("ES972345", l1.getSukurtsalak().get(0).getEntitateBankario().getEntitateZbk());
		assertEquals("218/253/128/45", l1.getSukurtsalak().get(0).getEntitateBankario().getBounds());
		assertEquals("src/res/bbk_logo.png", l1.getSukurtsalak().get(0).getEntitateBankario().getUrl());
		
		assertEquals("ES9723450111545932515164", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getIban());
		assertEquals(1500, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getSaldoa(), 0.01);
		assertEquals(1000, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHilekoLimitea(), 0.01);
		assertEquals("2022-01-01", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getSorreraData());
		assertEquals("aktiboa", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getEgoera());
		
		assertEquals(5000, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("2022-12-07", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getSarreraData());
		assertEquals("Ordainketa 1", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES2598760153921924586673", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIgortzailea());
		
		assertEquals(800, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("2020-08-01", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getTransferentziaData());
		assertEquals("Ordainketa 4", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES3467890003915285942937", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getJasotzailea());
		assertEquals(2, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKomisioa(), 0.01);
		
		assertEquals(60000, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getKantitatea(), 0.01);
		assertEquals(5000, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getOrdaindutakoa(), 0.01);
		assertEquals(9, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getKomisioa(), 0.01);
		assertEquals("2023-08-01", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getHasieraData());
		assertEquals(null, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getAmaieraData());
		assertEquals("errefusatuta", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getEgoera());
		
		assertEquals("5678", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getSegurtasunKodea());
		assertEquals("debito", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getMota());
		assertEquals("12345678A",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getNan());
		assertEquals("Aingeru",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getIzena());
		assertEquals("Siranaula",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getAbizena());
		assertEquals("10-21-2002",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getJaiotzeData());
		assertEquals("gizona",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getSexua());
		assertEquals("111222333",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getTelefonoa());
		assertEquals("12345678",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getPasahitza());
	}
	
	@Test
	public void testLangilearenEntitateak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("12345678B", "zuzendaria");
		String[] entitateak = metodoak.langilearenEntitateak(l1);
		
		assertEquals("BBK", entitateak[0]);
	}
	
	@Test
	public void testLangilearenSukurtsalak() {
		Metodoak metodoak = new Metodoak();
		Langilea l1 = metodoak.langileaKargatu("12345678B", "zuzendaria");
		String[] sukurtsalak = metodoak.langilearenSukurtsalak(l1, "BBK");
		
		assertEquals("Santutxu, Santutxu Kalea, 27", sukurtsalak[0]);
	}
}
