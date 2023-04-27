package model;

import java.util.ArrayList;

public class Zuzendaria extends Langilea{
	// Constructors
	public Zuzendaria(String nan, String izena, String abizena, String jaiotzeData, String sexua, String telefonoa, String pasahitza, String lanpostu, ArrayList<Sukurtsala> sukurtsalak) {
		super(nan, izena, abizena, jaiotzeData, sexua, telefonoa, pasahitza, lanpostu, sukurtsalak);
	}
	public Zuzendaria() {
	}
	
	// Methods
	public void hipotekaSortu() {}
	
	public void kontuaItxi() {}
	
	public void kontuaIzoztu() {}
}
