package test;

import static org.junit.Assert.*;

import org.junit.Test;

import controlador.PertsonakKargatu;
import model.Bezeroa;
import model.Langilea;

public class PertsonakKargatuTest {

	@Test
	public void testBezeroaKargatu() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		
		assertEquals("78950146R", b1.getNan());
		assertEquals("Aingeru", b1.getIzena());
		assertEquals("Siranaula", b1.getAbizena());
		assertEquals("10-21-2002", b1.getJaiotzeData());
		assertEquals("gizona", b1.getSexua());
		assertEquals("111222333", b1.getTelefonoa());
		assertEquals("1234", b1.getPasahitza());
	}
	
	@Test
	public void testLangileaKargatu() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		
		Langilea l1 = pertsonakKargatu.langileaKargatu("79003399D", "zuzendaria", "1234");
		
		assertEquals("79003399D", l1.getNan());
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
		assertEquals("2345", l1.getSukurtsalak().get(0).getEntitateBankario().getEntitateZbk());
		assertEquals("218/253/128/45", l1.getSukurtsalak().get(0).getEntitateBankario().getBounds());
		assertEquals("src/res/bbk_logo.png", l1.getSukurtsalak().get(0).getEntitateBankario().getUrl());
		
		assertEquals("ES9323450111313252003900", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getIban());
		assertEquals(20046.09, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getSaldoa(), 0.01);
		assertEquals(2000, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHilekoLimitea(), 0.01);
		assertEquals("2003-09-15", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getSorreraData());
		assertEquals("aktiboa", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getEgoera());
		
		assertEquals(100, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKantitatea(), 0.01);
		assertEquals("2023-04-04", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getSarreraData());
		assertEquals("dirua", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getKontzeptua());
		assertEquals("ES2967890003394765827453", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getDiruSarrerak().get(0).getIgortzailea());
		
		assertEquals(100, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKantitatea(), 0.01);
		assertEquals("2023-04-04", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getTransferentziaData());
		assertEquals("paga", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKotzeptua());
		assertEquals("ES2267890050608253351724", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getJasotzailea());
		assertEquals("1.5", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTransferentziak().get(0).getKomisioa());
		
		assertEquals(80000, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getKantitatea(), 0.01);
		assertEquals(0, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getOrdaindutakoa(), 0.01);
		assertEquals("3.8", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getKomisioa());
		assertEquals("2023-04-04", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getHasieraData());
		assertEquals(null, l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getAmaieraData());
		assertEquals("eskatuta", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getHipoteka().getEgoera());
		
		assertEquals("5678", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getSegurtasunKodea());
		assertEquals("kredito", l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getMota());
		assertEquals("78950146R",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getNan());
		assertEquals("Aingeru",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getIzena());
		assertEquals("Siranaula",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getAbizena());
		assertEquals("10-21-2002",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getJaiotzeData());
		assertEquals("gizona",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getSexua());
		assertEquals("111222333",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getTelefonoa());
		assertEquals("1234",l1.getSukurtsalak().get(0).getKontuBankarioak().get(0).getTxartelak().get(0).getBezeroa().getPasahitza());
	}

}
