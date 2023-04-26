package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Bezeroa extends Pertsona{
	// Constructors
	public Bezeroa(String nan, String izena, String abizena, String jaiotzeData, String sexua, String telefonoa, String pasahitza) {
		super(nan, izena, abizena, jaiotzeData, sexua, telefonoa, pasahitza);
	}
	
	// Methods
	public void hipotekaOrdaindu() {}
	
	public void itxiKontua() {}
	
	public void transferentziaEgin(){}
	
	public void hipotekaEskatu() {}
	
	public int adinaKalkulatu() {
		SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
		
		//Urteak Kalkulatu
		Date data_jaio=null;
		Date curdate = new Date();
		try {
			data_jaio = (Date) df.parse(this.jaiotzeData);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    long diffInMillies = Math.abs(curdate.getTime() - data_jaio.getTime());
	    int urteak = (int) TimeUnit.DAYS.convert(diffInMillies/365, TimeUnit.MILLISECONDS);
	    
	    return urteak;
	}
}
