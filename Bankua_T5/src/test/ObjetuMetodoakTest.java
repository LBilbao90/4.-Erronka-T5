package test;

import static org.junit.Assert.*;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import controlador.ObjetuMetodoak;
import controlador.PertsonakKargatu;
import model.Bezeroa;
import model.Langilea;
import model.SalbuespenaOrdainketa;

public class ObjetuMetodoakTest {

	@Test
	public void testIxtekoKontuAldatu(){
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		ObjetuMetodoak objetuMetodoak = new ObjetuMetodoak();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("65293200H");
		b1 = objetuMetodoak.ixtekoKontuAldatu(b1, "ES8867890003395016874189");
		
		for(int i=0;i<b1.getTxartelak().size();i++) {
			if(b1.getTxartelak().get(i).getKontuBankario().getIban().equals("ES8867890003395016874189")) {
				assertEquals("ixteko", b1.getTxartelak().get(i).getKontuBankario().getEgoera());
			}
		}
	}
	
	@Test
	public void testDiruaKendu(){
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		ObjetuMetodoak objetuMetodoak = new ObjetuMetodoak();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		b1 = objetuMetodoak.diruaKendu(b1, "ES7023450002734016972210", "1000", "1.5", "ES7023450002734016972210");
		
		for(int i=0;i<b1.getTxartelak().size();i++) {
			if(b1.getTxartelak().get(i).getKontuBankario().getIban().equals("ES7023450002734016972210")) {
				assertEquals(2436.98, b1.getTxartelak().get(i).getKontuBankario().getSaldoa(), 0.01);
			}
		}
	}
	
	@Test
	public void testHipotekaSortu() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		ObjetuMetodoak objetuMetodoak = new ObjetuMetodoak();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		b1 = objetuMetodoak.hipotekaSortu(b1, "ES1998760011555340294968", "120000", "4", "15 urte");
		
		Format f = new SimpleDateFormat("M/d/yyyy");
		String data = f.format(new Date());
		String[] data_array = data.split("/");
		data = data_array[2]+"-"+data_array[0]+"-"+data_array[1];
		
		for(int i=0;i<b1.getTxartelak().size();i++) {
			if(b1.getTxartelak().get(i).getKontuBankario().getIban().equals("ES1998760011555340294968")) {
				 assertEquals(124800.0, b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getKantitatea(), 0.01);
				 assertEquals(0.0, b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getOrdaindutakoa(), 0.01);
				 assertEquals("4", b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getKomisioa());
				 assertEquals(data, b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getHasieraData());
				 assertEquals("eskatuta", b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getEgoera());
				 assertEquals("15 urte", b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getEpeMuga());
			}
		}
	}
	
	@Test
	public void testHipotekaOrdaindu() throws SalbuespenaOrdainketa {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		ObjetuMetodoak objetuMetodoak = new ObjetuMetodoak();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		b1 = objetuMetodoak.hipotekaSortu(b1, "ES1998760011555340294968", "120000", "4", "15 urte");
		b1 = objetuMetodoak.hipotekaOrdaindu(b1, "ES1998760011555340294968", "1000");
		
		Format f = new SimpleDateFormat("M/d/yyyy");
		String data = f.format(new Date());
		String[] data_array = data.split("/");
		data = data_array[2]+"-"+data_array[0]+"-"+data_array[1];
		
		for(int i=0;i<b1.getTxartelak().size();i++) {
			if(b1.getTxartelak().get(i).getKontuBankario().getIban().equals("ES1998760011555340294968")) {
				if(b1.getTxartelak().get(i).getKontuBankario().getSaldoa()>=Double.parseDouble("1000") && b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getKantitatea()-b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getOrdaindutakoa()>= Double.parseDouble("1000")) {
					assertEquals(124800.0, b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getKantitatea(), 0.01);
					 assertEquals(1000, b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getOrdaindutakoa(), 0.01);
					 assertEquals("4", b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getKomisioa());
					 assertEquals(data, b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getHasieraData());
					 assertEquals("eskatuta", b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getEgoera());
					 assertEquals("15 urte", b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getEpeMuga());
					 
					if(b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getOrdaindutakoa()==b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getKantitatea()) {
						
					}
				}else {
					throw new SalbuespenaOrdainketa("Ordainketa okerra.");
				}
			}
		}
	}
	
	@Test
	public void testHipotekaOrdainduAmaieraData() throws SalbuespenaOrdainketa {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		ObjetuMetodoak objetuMetodoak = new ObjetuMetodoak();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		b1 = objetuMetodoak.hipotekaSortu(b1, "ES1998760011555340294968", "1200", "4", "15 urte");
		b1 = objetuMetodoak.hipotekaOrdaindu(b1, "ES1998760011555340294968", "1248");
		
		Format f = new SimpleDateFormat("M/d/yyyy");
		String data = f.format(new Date());
		String[] data_array = data.split("/");
		data = data_array[2]+"-"+data_array[0]+"-"+data_array[1];
		
		for(int i=0;i<b1.getTxartelak().size();i++) {
			if(b1.getTxartelak().get(i).getKontuBankario().getIban().equals("ES1998760011555340294968")) {
				assertEquals(1248.0, b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getKantitatea(), 0.01);
				assertEquals(1248.0, b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getOrdaindutakoa(), 0.01);
				assertEquals("4", b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getKomisioa());
				assertEquals(data, b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getHasieraData());
				assertEquals(data, b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getAmaieraData());
				assertEquals("itxita", b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getEgoera());
				assertEquals("15 urte", b1.getTxartelak().get(i).getKontuBankario().getHipoteka().getEpeMuga());
			}
		}
	}
	
	@Test
	public void testHipotekaOrdainduException() throws SalbuespenaOrdainketa {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		ObjetuMetodoak objetuMetodoak = new ObjetuMetodoak();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		b1 = objetuMetodoak.hipotekaSortu(b1, "ES1998760011555340294968", "1200", "4", "15 urte");
		try {
			b1 = objetuMetodoak.hipotekaOrdaindu(b1, "ES1998760011555340294968", "1000000");
		}catch(SalbuespenaOrdainketa s){
			System.out.println(s);
		}
	}
	
	@Test
	public void testKontuItxi(){
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		ObjetuMetodoak objetuMetodoak = new ObjetuMetodoak();
		
		Langilea l1 = pertsonakKargatu.langileaKargatu("79003399D", "zuzendaria", "1234");
		l1 = objetuMetodoak.kontuItxi(l1, "ES8867890003395016874189", "Alameda de Recalde, 35");
		
		for(int i=0;i<l1.getSukurtsalak().size();i++) {
			if(l1.getSukurtsalak().get(i).getKokalekua().equals("Alameda de Recalde, 35")) {
				//Kontu Bankarioak arakatu
				for(int j=0;j<l1.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
					assertFalse(l1.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban().equals("ES8867890003395016874189"));
				}
			}
		}	
	}
}
