package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import controlador.PertsonakKargatu;
import controlador.TaulaMetodoak;
import controlador.TxtIdatzi;
import model.Bezeroa;

public class TxtIdatziTest {
	
	@Test
	public void testLoginErregistratu() {
		TxtIdatzi txtIdatzi = new TxtIdatzi();

		assertTrue(txtIdatzi.loginErregistratu("78950146", "Bezeroa"));
		
		BufferedReader fichero;
		File file = new File("src/logs/loginLogs.txt");
		try {
		fichero = new BufferedReader(new FileReader(file));
		
		assertEquals("Bezeroa-> Nan: 12345678Z - Data: 2023/05/10 21:33:40", fichero.readLine());
		
		fichero.close();
		
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void testLoginOkerraErregistratu() {
		TxtIdatzi txtIdatzi = new TxtIdatzi();

		assertTrue(txtIdatzi.loginOkerraErregistratu("78950146", "Bezeroa"));
		
		BufferedReader fichero;
		File file = new File("src/logs/errorLoginLogs.txt");
		try {
		fichero = new BufferedReader(new FileReader(file));
		
		assertEquals("Bezeroa-> Nan: 78950146R - Data: 2023/05/10 22:49:03", fichero.readLine());
		
		fichero.close();
		
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Test
	public void testTransferentziakImprimatu() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		TaulaMetodoak taulaMetodoak = new TaulaMetodoak();
		TxtIdatzi txtIdatzi = new TxtIdatzi();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		String[][] transferentziak_array = taulaMetodoak.transferentziakIkusi(b1, "ES9323450111313252003900"); 

		assertTrue(txtIdatzi.transferentziakImprimatu(transferentziak_array));
		
		BufferedReader fichero;
		File file = new File("src/movimientos/Transferentziak/2023_4_4_9_4.txt");
		try {
		fichero = new BufferedReader(new FileReader(file));
		String linea;
		String [] transferentziak = new String [0];
		
		while(( linea = fichero.readLine()) != null) {
			String[] transferentziak_prob = new String[transferentziak.length+1];
			for(int i =0;i<transferentziak.length;i++)
			{
				transferentziak_prob[i]=transferentziak[i];
			}
			transferentziak_prob[transferentziak.length] = linea;
			transferentziak = transferentziak_prob;
		}
		
		assertEquals("IBAN: ES22 6789 0050  60  8253351724 ,Kantitatea: 100,00 € ,Kontzeptuapaga ,Transferetzia-data: 2023-04-04", transferentziak[1]);
		
		fichero.close();
		
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void testDiruSarrerakImprimatu() {	
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		TaulaMetodoak taulaMetodoak = new TaulaMetodoak();
		TxtIdatzi txtIdatzi = new TxtIdatzi();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		String[][] diruSarrerak_array = taulaMetodoak.diruSarrerakIkusi(b1, "ES9323450111313252003900");
		assertTrue(txtIdatzi.diruSarrerakImprimatu(diruSarrerak_array));
		
		BufferedReader fichero;
		File file = new File("src/movimientos/DiruSarrerak/2023_4_4_9_4.txt");
		try {
		fichero = new BufferedReader(new FileReader(file));
		String linea;
		String [] diruSarrerak = new String [0];
		
		while(( linea = fichero.readLine()) != null) {
			String[] diruSarrerak_prob = new String[diruSarrerak.length+1];
			for(int i =0;i<diruSarrerak.length;i++)
			{
				diruSarrerak_prob[i]=diruSarrerak[i];
			}
			diruSarrerak_prob[diruSarrerak.length] = linea;
			diruSarrerak = diruSarrerak_prob;
		}
		
		assertEquals("IBAN igortzailea: ES29 6789 0003  39  4765827453 ,Kantitatea: 100,00 € ,Kontzeptuadirua ,Sarrera-data: 2023-04-04", diruSarrerak[1]);
		
		fichero.close();
		
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	@Test
	public void testMugimentuakImprimatu() {
		PertsonakKargatu pertsonakKargatu = new PertsonakKargatu();
		TaulaMetodoak taulaMetodoak = new TaulaMetodoak();
		TxtIdatzi txtIdatzi = new TxtIdatzi();
		
		Bezeroa b1 = pertsonakKargatu.bezeroaKargatu("78950146R");
		String[][] diruSarrerak_array = taulaMetodoak.diruSarrerakIkusi(b1, "ES9323450111313252003900");
		String[][] transferentziak_array = taulaMetodoak.transferentziakIkusi(b1, "ES9323450111313252003900");
		assertTrue(txtIdatzi.mugimentuakImprimatu(diruSarrerak_array, transferentziak_array));
		
		BufferedReader fichero;
		File file = new File("src/movimientos/Mugimenduak/2023_4_4_9_4.txt");
		try {
		fichero = new BufferedReader(new FileReader(file));
		String linea;
		String [] mugimenduak = new String [0];
		
		while(( linea = fichero.readLine()) != null) {
			String[] mugimenduak_prob = new String[mugimenduak.length+1];
			for(int i =0;i<mugimenduak.length;i++)
			{
				mugimenduak_prob[i]=mugimenduak[i];
			}
			mugimenduak_prob[mugimenduak.length] = linea;
			mugimenduak = mugimenduak_prob;
		}
		
		assertEquals("IBAN: ES22 6789 0050  60  8253351724 ,Kantitatea: 100,00 € ,Kontzeptuapaga ,Transferetzia-data: 2023-04-04", mugimenduak[1]);
		assertEquals("IBAN igortzailea: ES29 6789 0003  39  4765827453 ,Kantitatea: 100,00 € ,Kontzeptuadirua ,Sarrera-data: 2023-04-04", mugimenduak[3]);
		
		fichero.close();
		
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
