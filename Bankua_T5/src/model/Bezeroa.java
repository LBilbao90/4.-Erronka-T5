package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Bezeroa extends Pertsona{
	private ArrayList<Txartela> txartelak;
	
	// Constructors
	public Bezeroa(String nan, String izena, String abizena, String jaiotzeData, String sexua, String telefonoa, String pasahitza, ArrayList<Txartela> txartelak) {
		super(nan, izena, abizena, jaiotzeData, sexua, telefonoa, pasahitza);
		this.txartelak=txartelak;
	}
	
	//Getters 
	public ArrayList<Txartela> getTxartelak() {
		return txartelak;
	}
	//Setters
	public void setTxartelak(ArrayList<Txartela> txartelak) {
		this.txartelak = txartelak;
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
