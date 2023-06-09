package controlador;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import model.EntitateBankario;
import model.SalbuespenaErregistro;
import model.SalbuespenaLogin;
import model.SalbuespenaLoginBlokeo;

public class DatuBaseSelect {
	final String url = "jdbc:mysql://10.5.14.109:3306/bankua";
	
	// EntitateBankario
	final String entitatebankario = "entitatebankario";
	final String id_entitate= "id_entitate";
	final String entitate_izena = "izena";
	final String entitateZenbaki = "entitateZenbaki";
	final String urlImg = "url";
	final String bounds = "bounds";
	
	// Bezeroa
	final String bezeroa = "bezeroa";
	final String nan = "nan";
	final String izena = "izena";
	final String abizenak = "abizenak";
	final String jaiotzeData = "jaiotzeData";
	final String sexua = "sexua";
	final String telefonoa = "telefonoa";
	final String pasahitza = "pasahitza";
	
	// Langile
	final String langile = "langile";
	final String id_langile = "id_langile";
	final String lanpostua = "lanpostua";
	
	
	// Sukurtsala
	final String sukurtsala = "sukurtsala";
	final String kodSukurtsala = "kodSukurtsala";
	final String kokalekua = "kokalekua";
	final String id_sukurtsal = "id_sukurtsal";
	
	// KontuBankario
	final String kontuBankario = "kontubankario";
	final String iban = "iban";
	final String saldoa = "saldoa";
	final String hilekoLimitea = "hilekoLimitea";
	final String sorreraData = "sorreraData";
	final String egoera = "egoera";
	
	// DiruSarrera
	final String diruSarrera = "dirusarrera";
	final String idSarrera = "idSarrera";
	final String kantitatea = "kantitatea";
	final String sarreraData = "sarreraData";
	final String igortzaile = "igortzaile";
	final String kontzeptua = "kontzeptua";
	final String ibanJasotzaile = "ibanJasotzaile";
	
	// Transferentzia
	final String transferentzia = "transferentzia";
	final String transferentziaData = "transferentziaData";
	final String jasotzailea = "jasotzailea";
	final String komisioa = "komisioa";
	final String ibanIgortzaile = "ibanIgortzaile";
	
	// Hipoteka
	final String hipoteka = "hipoteka";
	final String idHipoteka = "idHipoteka";
	final String ordaindutakoa = "ordaindutakoa";
	final String hasieraData = "hasieraData";
	final String amaieraData = "amaieraData";
	final String epeMuga = "epeMuga";
	
	// Txartela
	final String txartela = "txartela";
	final String id_txartela = "id_txartela";
	final String nanBezeroa = "nanBezeroa";
	final String segurtasunKodea = "segurtasunKodea";
	final String mota = "mota";
	
	//Kudeatu
	final String kudeatu = "kudeatu";
	
	 /**
		 * Bezeroaren logina zuzena edo okerra den esaten du.
		 * @param nan_bez Bezeroaren NAN
		 * @param pas_bez Bezeroaren pasahitza
		 * @return <b>true</b> login zuzena bada eta <b>false</b> login okerra bada
		 */
		public boolean bezeroLogin(String nan_bez, String pass_bez) throws SalbuespenaLogin, SalbuespenaLoginBlokeo{
			boolean aurkituta = false;
			
			Connection conn;					
			try {
				//Datu baseari konexioa eta Bezeroa logeatzeko kontsulta egiten dugu
				conn = (Connection) DriverManager.getConnection (url,"L12345678Z","1234");
				Statement comand = (Statement) conn.createStatement();	
				ResultSet req = comand.executeQuery("select "+egoera+" from "+bezeroa+" where "+nan+"='"+nan_bez+"' and "+pasahitza+"='"+pass_bez+"';");
				
				if(req.next()) {
					if(req.getString(1).equals("aktiboa")) {
						aurkituta=true;
					}
					else if(req.getString(1).equals("blokeatuta")) {
						throw new SalbuespenaLoginBlokeo("Erabiltzaile hau blokeatuta dago.");
					}
				}else if(!req.next()) {
					throw new SalbuespenaLogin("Logina okerra da.");
				}

				conn.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
				System.out.println("SQLState: "+ ex.getSQLState());
				System.out.println("ErrorCode: "+ ex.getErrorCode());
			}
			
			return aurkituta;
		}

		/**
		 * Langilearen logina zuzena edo okerra den esaten du eta zer motatakoa
		 * @param nan_lang Langilearen NAN
		 * @param pas_lang Langilearen pasahitza
		 * @return <b>god</b> langilea GOD erabiltzailea erabiltzean, <b>zuzendaria</b> langilea Zuzendaria erabiltzailea erabiltzean, <b>gerentea</b> langilea Gerentea erabiltzailea erabiltzean eta <b>null</b> logina okerra bada.
		 * @throws SalbuespenaLogin Login okerra bada salbuespena botatzen du.
		 * @throws SalbuespenaLoginBlokeo Erabiltzailea blokeatuta badako salbuespena botatzen du.
		 */
		public String langileLogin(String nan_lang, String pass_lang) throws SalbuespenaLogin,SalbuespenaLoginBlokeo {
			String login= null;
			
			Connection conn;					
			try {
				//Datu baseari konexioa eta Langilea logeatzeko kontsulta egiten dugu
				conn = (Connection) DriverManager.getConnection (url,"L12345678Z","1234");
				Statement comand = (Statement) conn.createStatement();	
				ResultSet req = comand.executeQuery("select "+lanpostua+","+egoera+" from "+langile+" where "+nan+"='"+nan_lang+"' and "+pasahitza+"='"+pass_lang+"';");
				//Daturik aurkitzen badu
				if(req.next()) {
					if(req.getString(2).equals("blokeatuta")) {
						throw new SalbuespenaLoginBlokeo("Erabiltzaile hau blokeatuta dago.");
					}
					else if(req.getString(1).equals("gerentea")) {
						login="gerentea";
					}else if(req.getString(1).equals("zuzendaria")) {
						login="zuzendaria";
					}else if(req.getString(1).equals("god")) {
						login="god";
					}
				}else if(!req.next()) {
					throw new SalbuespenaLogin("Logina okerra da.");
				}
				conn.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
				System.out.println("SQLState: "+ ex.getSQLState());
				System.out.println("ErrorCode: "+ ex.getErrorCode());
			}
			if(login==null) {
				throw new SalbuespenaLogin("Logina okerra da.");
			}					
			return login;
		}
		
		/**
		 * IBAN egokia den ala ez balidatzen du
		 * @param ibanJasotzaile Zein iban balidatuko da
		 * @return True IBANa ondo badago, false txarto badago
		 */
		public boolean transferentziaIbanBalidatu(String ibanJasotzaile) {
			boolean aurkituta = false;
			
			Connection conn;					
			try {
				conn = (Connection) DriverManager.getConnection (url,"L12345678Z","1234");
				Statement comand = (Statement) conn.createStatement();	
				ResultSet req = comand.executeQuery("Select COUNT(*) as kant from kontubankario where "+iban+"= '"+ibanJasotzaile+"';");
				
				if(req.next()) {
					if(req.getInt("kant") == 1) {
						aurkituta = true;
					}
				}
				conn.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
				System.out.println("SQLState: "+ ex.getSQLState());
				System.out.println("ErrorCode: "+ ex.getErrorCode());
			}	
			
			return aurkituta;
		}

		/**
		 * balidatzen du kontu batek beharrezko saldoa duela
		 * @param saldo Balidatuko den saldoa
		 * @param kontua Zein kontuan balidatuko da
		 * @param nan_bezero bezeroaren NAN
		 * @param pass_bezero bezeroaren pasahitza
		 * @return Beharrezko saldoa badago true, bestela false
		 */
		public boolean transferentziaSaldoaBalidatu(String diru_kantitate, String kontua, String nan_bezero, String pass_bezero) {
			boolean zuzena = false;
			if(diru_kantitate.split(",").length<=2) {
				diru_kantitate = diru_kantitate.replace(',','.');
				Connection conn;					
				try {
					conn = (Connection) DriverManager.getConnection (url,"B"+nan_bezero,pass_bezero);
					Statement comand = (Statement) conn.createStatement();	
					ResultSet req = comand.executeQuery("Select "+saldoa+" from kontubankario where "+iban+"='"+kontua+"';");
					
					if(req.next()) {
						if(req.getDouble(saldoa) >= ((Double.parseDouble(diru_kantitate)*1.5)/100)+Double.parseDouble(diru_kantitate)) {
							zuzena = true;
						}
					}
					conn.close();
				}catch(SQLException ex) {
					System.out.println("SQLException: "+ ex.getMessage());
					System.out.println("SQLState: "+ ex.getSQLState());
					System.out.println("ErrorCode: "+ ex.getErrorCode());
				}
			}
			return zuzena;
		}

		/**
		 * balidatzen du emandako segurtasun kodea zuzena dela
		 * @param kodea balidatuko den kodea
		 * @param kontua zein kontutan bilatuko den
		 * @param nan_bezero bezeroaren NAN
		 * @param pass_bezero bezeroaren pasahitza
		 * @return Kodea zuzena bada true, bestela false
		 */
		public boolean segurtasunKodeaBalidatu(String kodea, String kontua, String nan_bezero, String pass_bezero) {
			boolean zuzena = false;
			Connection conn;					
			try {
				conn = (Connection) DriverManager.getConnection (url,"B"+nan_bezero,pass_bezero);
				Statement comand = (Statement) conn.createStatement();	
				ResultSet req = comand.executeQuery("Select COUNT(*) as kant from kontubankario kon join kudeatu ku on kon."+ iban +"= ku."+ iban +" join txartela t on ku."+ id_txartela+"= t."+ id_txartela +" where ku."+iban+"='"+kontua+"' and "+ segurtasunKodea +"='"+ kodea +"';");
				
				if(req.next()) {
					if(req.getInt("kant") == 1) {
						zuzena = true;
					}
				}
				conn.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
				System.out.println("SQLState: "+ ex.getSQLState());
				System.out.println("ErrorCode: "+ ex.getErrorCode());
			}			
			return zuzena;
		}

		/**
		 * Sartutako IBANa existitzen dela ala ez balidatzen du.
		 * @param iban_sortu Sartutako iban kontua.
		 * @return <b>True</b> ez bada existitzen eta <b>False</b> existitzen bada.
		 */
		public boolean ibanBalidatu(String iban_sortu) {
			boolean libre = false;
			
			Connection conn;
			try {
				conn = (Connection) DriverManager.getConnection (url,"L12345678Z","1234");
				Statement comand = (Statement) conn.createStatement();
				ResultSet req = comand.executeQuery("select "+iban+" from "+kontuBankario+" where "+iban+"='"+iban_sortu+"';");
				if(!req.next()) {
					libre= true;
				}				
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
				System.out.println("SQLState: "+ ex.getSQLState());
				System.out.println("ErrorCode: "+ ex.getErrorCode());
			}	
			
			return libre;
		}
		
		/**
		 * Balidatzen du txartela existitzen dela
		 * @param txartelId Balidatuko den txartela
		 * @return txartela ez bada existitzen true, bestela false
		 */
		public boolean txartelIdBalidatu(String txartelId) {
			boolean libre=true;
			
			Connection conn;					
			try {
				//Datu baseari konexioa eta Langilea logeatzeko kontsulta egiten dugu
				conn = (Connection) DriverManager.getConnection (url,"L12345678Z","1234");
				Statement comand = (Statement) conn.createStatement();	
				ResultSet req = comand.executeQuery("select "+id_txartela+" from "+txartela+" where "+id_txartela+"='"+txartelId+"';");
				//Daturik aurkitzen badu
				if(req.next()) {
					libre = false;	
				}
				conn.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
				System.out.println("SQLState: "+ ex.getSQLState());
				System.out.println("ErrorCode: "+ ex.getErrorCode());
			}		
			return libre;
		}

		/**
		 * balidatzen du bezero bat ez dagoela erregistratuta datubasean
		 * @param nan_bez Bezeroaren NAN
		 * @return bezeroa ez badago datu basean false, bestela true
		 */
		public boolean bezeroExistitu(String nan_bez) {
			boolean existitu = false;
			Connection conn;					
			try {
				//Datu baseari konexioa eta Bezeroa nan kontsulta egiten da
				conn = (Connection) DriverManager.getConnection (url,"L12345678Z","1234");
				Statement comand = (Statement) conn.createStatement();	
				ResultSet req = comand.executeQuery("select "+nan+" from "+bezeroa+" where "+nan+"='"+nan_bez+"';");
				//Daturik aurkitzen badu
				if(req.next()) {
					existitu = true;	
				}
				conn.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
				System.out.println("SQLState: "+ ex.getSQLState());
				System.out.println("ErrorCode: "+ ex.getErrorCode());		
			}		
			return existitu;
		}

		/**
		 * Bezero guztiak datubasetik kargatzen ditu
		 * @return Bezeroen matrize bat, lerro bakoitza bezero bat
		 */
		public String[][] bezeroZerrendaKargatu(){
			String[][] bezeroak = new String[0][8];		
			
			Connection conn;					
			try {
				//Datu baseari konexioa eta Bezeroa logeatzeko kontsulta egiten dugu
				conn = (Connection) DriverManager.getConnection (url,"L12345678Z","1234");
				Statement comand = (Statement) conn.createStatement();	
				ResultSet req = comand.executeQuery("select "+nan+","+izena+","+abizenak+","+jaiotzeData+","+sexua+","+telefonoa+","+pasahitza+","+egoera+" from "+bezeroa);
				
				while(req.next()) {
					String[][] bezero_prob = new String[bezeroak.length+1][8];
					for(int i=0;i<bezeroak.length;i++) {
						for(int j=0;j<bezeroak[i].length;j++) {
							bezero_prob[i][j]=bezeroak[i][j];
						}
					}
					bezero_prob[bezeroak.length][0]=req.getString(1);
					bezero_prob[bezeroak.length][1]=req.getString(2);
					bezero_prob[bezeroak.length][2]=req.getString(3);
					bezero_prob[bezeroak.length][3]=req.getString(4);
					bezero_prob[bezeroak.length][4]=req.getString(5);
					bezero_prob[bezeroak.length][5]=req.getString(6);
					bezero_prob[bezeroak.length][6]=req.getString(7);
					bezero_prob[bezeroak.length][7]=req.getString(8);
					bezeroak=bezero_prob;
				}
				conn.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
				System.out.println("SQLState: "+ ex.getSQLState());
				System.out.println("ErrorCode: "+ ex.getErrorCode());
			}		
			return bezeroak;
		}

		/**
		 * Langile guztiak datubasetik kargatzen ditu
		 * @return Langileen matrize bat, lerro bakoitza langile bat
		 */
		public String[][] langileZerrendaKargatu(){
			String[][] langileak = new String[0][10];		
			
			Connection conn;					
			try {
				//Datu baseari konexioa eta Bezeroa logeatzeko kontsulta egiten dugu
				conn = (Connection) DriverManager.getConnection (url,"L12345678Z","1234");
				Statement comand = (Statement) conn.createStatement();	
				ResultSet req = comand.executeQuery("select "+nan+","+izena+","+abizenak+","+jaiotzeData+","+sexua+","+telefonoa+","+pasahitza+","+lanpostua+","+id_sukurtsal+","+egoera+" from "+langile);
				
				while(req.next()) {
					String[][] langile_prob = new String[langileak.length+1][10];
					for(int i=0;i<langileak.length;i++) {
						for(int j=0;j<langileak[i].length;j++) {
							langile_prob[i][j]=langileak[i][j];
						}
					}
					langile_prob[langileak.length][0]=req.getString(1);
					langile_prob[langileak.length][1]=req.getString(2);
					langile_prob[langileak.length][2]=req.getString(3);
					langile_prob[langileak.length][3]=req.getString(4);
					langile_prob[langileak.length][4]=req.getString(5);
					langile_prob[langileak.length][5]=req.getString(6);
					langile_prob[langileak.length][6]=req.getString(7);
					langile_prob[langileak.length][7]=req.getString(8);
					langile_prob[langileak.length][8]=req.getString(9);
					langile_prob[langileak.length][9]=req.getString(10);
					langileak=langile_prob;
				}
				conn.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
				System.out.println("SQLState: "+ ex.getSQLState());
				System.out.println("ErrorCode: "+ ex.getErrorCode());
			}		
			return langileak;
		}

		/**
		 * Datu basean dauden entitate bankarioak kargatzen du botoiak sortzeko
		 * @return Entitate Bankario ArrayList bat entitate guztiekin
		 */
		public ArrayList<EntitateBankario> botoiakSortu(){
			ArrayList<EntitateBankario> entitateak = new ArrayList<>();		
			
			Connection conn;					
			try {			
				//Datu baseari konexioa eta Entitateak kargatzeko kontsulta egiten dugu
				conn = (Connection) DriverManager.getConnection (url,"L12345678Z","1234");
				Statement comand = (Statement) conn.createStatement();	
				ResultSet req = comand.executeQuery("select "+id_entitate+","+izena+","+entitateZenbaki+","+urlImg+","+bounds+" from "+entitatebankario+";");
				//Emaitzik badaude
				while(req.next()) {
					String ent_id = req.getString(1);
					String ent_izen = req.getString(2);
					String ent_zbk = req.getString(3);
					String ent_url = req.getString(4);
					String ent_bound = req.getString(5);
					//Datuak Entitate Bankarioan gorde
					EntitateBankario entitate_kontu = new EntitateBankario(ent_izen,ent_id,ent_zbk,ent_bound,ent_url);
					entitateak.add(entitate_kontu);
				}
				conn.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
				System.out.println("SQLState: "+ ex.getSQLState());
				System.out.println("ErrorCode: "+ ex.getErrorCode());
			}		
			return entitateak;
		}

		/**
		 * Langilearen nan existitzen baden datubasean begiratzen du
		 * @param nan_lang Langilea erregistratu nahi den nan-a.
		 * @return Nan horrekin dauden erregistro kantitatea.
		 * @throws SalbuespenaErregistro Erregistro bat badago salbuespena botatzen du.
		 */
		public int langileNanKant(String nan_lang) throws SalbuespenaErregistro{
			int kant=0;
			
			Connection conn;					
			try {			
				//Datu baseari konexioa eta langile kantitatea nan horrekin ateratzen du
				conn = (Connection) DriverManager.getConnection (url,"L12345678Z","1234");
				Statement comand = (Statement) conn.createStatement();	
				ResultSet req = comand.executeQuery("select count(*) as kantitate from "+langile+" where "+nan+"='"+nan_lang+"';");
				if(req.next()) {
					kant = req.getInt("kantitate");
					if(kant!=0) {
						throw new SalbuespenaErregistro("Errore erregistratzean.");
					}
				}
				
				conn.close();
			}catch(SQLException ex) {
				System.out.println("SQLException: "+ ex.getMessage());
				System.out.println("SQLState: "+ ex.getSQLState());
				System.out.println("ErrorCode: "+ ex.getErrorCode());
			}			
			return kant;
		}		
}
