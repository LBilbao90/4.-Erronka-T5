package controlador;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class TxtIdatzi {

	
	/**
	 * Pertsona bat logeatzean ligon horren informazioa gordetzen du .txt fitxategi batean.	
	 * @param nan Logeatu de pertsonaren NAN.
	 * @param mota Logeatu den erabiltzaile mota.
	 */
	public boolean loginErregistratu(String nan, String mota) {
		boolean zuzena = false;
		File file = new File("src/logs/loginLogs.txt");
		
			try {
				FileWriter fw = new FileWriter(file,true);
		    	BufferedWriter bw = new BufferedWriter(fw);
		    	PrintWriter pw = new PrintWriter(bw);

	    	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	    	   LocalDateTime eguna = LocalDateTime.now();
		    	
		    	pw.println(mota+"-> Nan: "+nan.toUpperCase()+" - Data: "+dtf.format(eguna));
				zuzena = true;
		    	pw.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		return zuzena;
	}
	
	/**
	 * Pertsona bat login oker bat egitean login horren informazioa gordetzen du .txt fitxategi batean.
	 * @param nan Login okerraren NAN.
	 * @param mota Login okerra egin duen erabiltzaile mota.
	 */
	public boolean loginOkerraErregistratu(String nan, String mota) {
		boolean zuzena = false;
		File file = new File("src/logs/errorLoginLogs.txt");
		
		try {
			FileWriter fw = new FileWriter(file,true);
	    	BufferedWriter bw = new BufferedWriter(fw);
	    	PrintWriter pw = new PrintWriter(bw);

    	   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
    	   LocalDateTime eguna = LocalDateTime.now();
	    	
	    	pw.println(mota+"-> Nan: "+nan.toUpperCase()+" - Data: "+dtf.format(eguna));
			zuzena = true;
	    	pw.close();
		
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return zuzena;
	}

	/**
	 * Transferentziak .txt batean imprimatzen ditu
	 * @param transferentziak Transferentzien arraya imprimatzeko
	 * @return Imprimaketa ondo egin bada true, bestela false
	 */
	public boolean transferentziakImprimatu(String[][] transferentziak){
		boolean zuzena = false;
    	Calendar cal = Calendar.getInstance();
		cal.set(Calendar.AM_PM, Calendar.AM);
    	String cal_s = cal.get(Calendar.YEAR)+"_"+cal.get(Calendar.MONTH)+"_"+cal.get(Calendar.DAY_OF_MONTH)+"_"+cal.get(Calendar.HOUR)+"_"+cal.get(Calendar.MINUTE);
		File file = new File("src/movimientos/Transferentziak/"+cal_s+".txt");
		BufferedWriter fichero;
		
		try {
	    	fichero = new BufferedWriter(new FileWriter(file));
	    	fichero.write("TRANSFERENTZIAK: \n");
	    	
	    	for(int i = 0; i < transferentziak.length; i ++) {
	    		fichero.write("IBAN: "+transferentziak[i][0]+" ,Kantitatea: "+transferentziak[i][1]+" ,Kontzeptua"+transferentziak[i][2]+" ,Transferetzia-data: "+ transferentziak[i][3] +"\n");
	    	}
	    	zuzena = true;
	    	fichero.close();
		
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return zuzena;
	}
	
	/**
	 * diru sarrerak .txt batean imprimatzen ditu
	 * @param diruSarrerak diru sarreren arraya imprimatzeko
	 * @return Imprimaketa ondo egin bada true, bestela false
	 */
	public boolean diruSarrerakImprimatu(String[][] diruSarrerak){
		boolean zuzena = false;
    	Calendar cal = Calendar.getInstance();
		cal.set(Calendar.AM_PM, Calendar.AM);
    	String cal_s = cal.get(Calendar.YEAR)+"_"+cal.get(Calendar.MONTH)+"_"+cal.get(Calendar.DAY_OF_MONTH)+"_"+cal.get(Calendar.HOUR)+"_"+cal.get(Calendar.MINUTE);
		File file = new File("src/movimientos/DiruSarrerak/"+cal_s+".txt");
		BufferedWriter fichero;
		
		try {
	    	fichero = new BufferedWriter(new FileWriter(file));
	    	fichero.write("DIRU SARRERAK: \n");
	    	
	    	for(int i = 0; i < diruSarrerak.length; i ++) {
	    		fichero.write("IBAN igortzailea: "+diruSarrerak[i][0]+" ,Kantitatea: "+diruSarrerak[i][1]+" ,Kontzeptua"+diruSarrerak[i][2]+" ,Sarrera-data: "+ diruSarrerak[i][3] +"\n");
	    	}
	    	zuzena = true;
	    	fichero.close();
		
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return zuzena;
	}

	/**
	 * Transferentziak eta diru sarrerak .txt berdinean imprimatzeko
	 * @param diruSarrerak diru sarreren arraya
	 * @param transferentziak transferentzien arraya
	 * @return imprimaketa ondo egin bada true, besetela false
	 */
	public boolean mugimentuakImprimatu(String[][] diruSarrerak, String[][] transferentziak){
		boolean zuzena = true;
    	Calendar cal = Calendar.getInstance();
		cal.set(Calendar.AM_PM, Calendar.AM);
    	String cal_s = cal.get(Calendar.YEAR)+"_"+cal.get(Calendar.MONTH)+"_"+cal.get(Calendar.DAY_OF_MONTH)+"_"+cal.get(Calendar.HOUR)+"_"+cal.get(Calendar.MINUTE);
		File file = new File("src/movimientos/Mugimenduak/"+cal_s+".txt");
		BufferedWriter fichero;
		
		try {
			fichero = new BufferedWriter(new FileWriter(file));
	    	fichero.write("TRANSFERENTZIAK: \n");
	    	
	    	for(int i = 0; i < transferentziak.length; i ++) {
	    		fichero.write("IBAN: "+transferentziak[i][0]+" ,Kantitatea: "+transferentziak[i][1]+" ,Kontzeptua"+transferentziak[i][2]+" ,Transferetzia-data: "+ transferentziak[i][3] +"\n");
	    	}
	    	
	    	fichero.write("DIRU SARRERAK: \n");
	    	
	    	for(int i = 0; i < diruSarrerak.length; i ++) {
	    		fichero.write("IBAN igortzailea: "+diruSarrerak[i][0]+" ,Kantitatea: "+diruSarrerak[i][1]+" ,Kontzeptua"+diruSarrerak[i][2]+" ,Sarrera-data: "+ diruSarrerak[i][3] +"\n");
	    	}
	    	
	    	fichero.close();
		
		} catch (IOException e1) {
			zuzena = false;
			e1.printStackTrace();
		}
		return zuzena;
	}
}
