package vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.border.EmptyBorder;

import controlador.ObjetuMetodoak;
import controlador.DatuBaseSelect;
import controlador.DatuBaseInsert;
import controlador.DatuBaseUpdate;
import controlador.DatuBaseDelete;
import controlador.TxtIdatzi;
import controlador.ArrayMetodoak;
import controlador.BooleanMetodoak;
import controlador.StringMetodoak;
import controlador.PertsonakKargatu;
import controlador.TaulaMetodoak;

import model.Bezeroa;
import model.EntitateBankario;
import model.Langilea;
import model.SalbuespenaErregistro;
import model.SalbuespenaLogin;
import model.SalbuespenaLoginBlokeo;
import model.SalbuespenaOrdainketa;
import model.SalbuespenaTransferentzia;
import model.Transferentzia;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.Font;

public class App extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_bezero_erabiltzaile;
	private JPasswordField passBezero;
	private JTextField txt_langile_erabiltzaile;
	private JPasswordField passLangile;
	//Kontroladore paketeko klaseak importatu
	ObjetuMetodoak metodoakObjetu = new ObjetuMetodoak();
	DatuBaseSelect metodoakSelect = new DatuBaseSelect();
	DatuBaseInsert metodoakInsert = new DatuBaseInsert();
	DatuBaseUpdate metodoakUpdate = new DatuBaseUpdate();
	DatuBaseDelete metodoakDelete = new DatuBaseDelete();
	ArrayMetodoak metodoakArray = new ArrayMetodoak();
	BooleanMetodoak metodoakBoolean = new BooleanMetodoak();
	StringMetodoak metodoakString = new StringMetodoak();
	PertsonakKargatu metodoakKargatu = new PertsonakKargatu();
	TaulaMetodoak metodoakTaulak = new TaulaMetodoak();
	TxtIdatzi metodoakTxt = new TxtIdatzi();
	
	final ImageIcon homeicon = new ImageIcon(new ImageIcon("src/res/casa.png").getImage().getScaledInstance(44, 34,Image.SCALE_DEFAULT));
	final ImageIcon logouticon = new ImageIcon(new ImageIcon("src/res/logout.png").getImage().getScaledInstance(44, 34,Image.SCALE_DEFAULT));
	final ImageIcon logo_atzera = new ImageIcon(new ImageIcon("src/res/flecha_atras.png").getImage().getScaledInstance(44,30,Image.SCALE_DEFAULT));
	final ImageIcon logo_aurrera = new ImageIcon(new ImageIcon("src/res/flecha_alante.png").getImage().getScaledInstance(44,30,Image.SCALE_DEFAULT));
	final ImageIcon fondo_argazki = new ImageIcon(new ImageIcon("src/res/logo2.2.png").getImage().getScaledInstance(932,130,Image.SCALE_DEFAULT));
	ArrayList<EntitateBankario> entitateak = new ArrayList<>();
	Transferentzia trans = new Transferentzia();
	
	//Calendar
	Calendar c = Calendar.getInstance();
	String egun = Integer.toString(c.get(Calendar.DATE));
	String hil = Integer.toString(c.get(Calendar.MONTH)+1);
	String urte = Integer.toString(c.get(Calendar.YEAR));
	String data_sortu = urte+"-"+hil+"-"+egun;
	
	Langilea langile = null;
	Bezeroa bezero = null;
	String nan_bezero = "";
	String pass_bezero = "";
	String nan_langile = "";
	String pass_langile = "";
	String lanpostua = "";
	String sukurtsal_izen = "";
	String txartel_id= "";
	String id_txartel_random="";
	String iban_sortu = "";
	String iban_hipoteka = "";
	String iban_itxi = "";
	String langile_aukeratu_nan = "";
	String ixteko_kontu_iban = "";
	String bezero_kontu_egoera = "";
	String [] entitate_izen = null;
	String[] bezero_entitate = null;
	String[] sukurtsal_kok = null;
	String[][] sukurtsal_kontuak = null;
	//Taulen header
	String[] kontuak_lista = {"IBAN","Saldoa","Egoera"};
	String[] transfer_header = {"Kantitatea","Transferentzia Data","Jasotzaile IBAN","Kontzeptua","Komisioa"};
	String[] sarrera_header = {"Kantitatea","Transferentzia Data","Jasotzaile IBAN","Kontzeptua"};
	String[] hipoteka_eskatu_header = {"IBAN","Kantitatea","Komisioa","Epe Muga","Egoera"};
	String[] hipoteka_onartu_header = {"IBAN","Kantitatea","Ordaindutakoa","Komisioa","Hasiera Data","Epe Muga","Egoera"};
	String[] hipoteka_itxita_header = {"IBAN","Kantitatea","Ordaindutakoa","Komisioa","Hasiera Data","Amaiera Data","Epe Muga","Egoera"};
	String[] kontuBankario_header = {"IBAN","Saldoa", "Egoera"};
	String[] transferentziak_header = {"Jasotzailea","Kantitatea","Kontzeptua", "Transferentzia-data"};
	String[] diruSarrerak_header = {"Igortzailea", "Kantitatea", "Kontzeptua", "Sarrera-Data"};
	String[] zerrenda_header_bez = {"NAN","Izena","Abizena","Jaiotze Data","Sexua","Telefonoa","Pasahitza","Egoera"};
	String[] zerrenda_header_lang = {"NAN","Izena","Abizena","Jaiotze Data","Sexua","Telefonoa","Pasahitza","Lanpostua","Sukurtsala ID","Egoera"};
	
	String[] langile_kontu_info = null;
	String[][] transferentzia_info = null;
	String[][] sarrera_info = null;
	String[] hipoteka_info  = null;
	String langile_aukeratu_iban="";
	
	int id_entitate = 0;
	double saldo_bezero = 0;
	String[][] kontuBankarioak = null;
	String[][] transferentziak = null;
	String[][] diruSarrerak = null;
	String[][] bezeroZerrendaArray = null;
	String[][] langileZerrendaArray = null;
	String[] bezeroInfo = null;
	String[] langileInfo= null;
	String bezero_iban_kontua = null;
	String[] aukerak = {"Bai","Ez"};
	
	//Combo Box arrayak
	String[] kontu_egoera = {"aktiboa","izoztuta","ixteko","itxita"};
	String[] sexu_array = {"gizona","emakumea"};
	String[] txartel_mota_array = {"kredito","debito"};
	String[] hilak_array = null;	
	String[] urteak_array= null;
	String[] egunak_array = null;
	String[] epemugak_array = {"3 urte", "5 urte", "10 urte", "15 urte"};
	String[] pertsona_egoera = {"aktiboa","blokeatuta"};
	String[] lanpostu_array = {"gerentea","zuzendaria","god"};
	
	//Kontu sortzeko array
	String[][] erabiltzaileak =new String[0][4];
	
	private JTable table_entitateKont;
	private JTable transfer_ikusi_table;
	private JTextField txt_jasotzaile;
	private JTextField txt_kantitate;
	private JTextField txt_kontzeptu;
	private JTextField txt_komisio_transfer;
	private JTable sarrerak_table;
	private JTextField txt_kantitate_hipoteka;
	private JTextField txt_hipoteka_komisio;
	private JPasswordField transferentzia_segurtasun;
	private JPasswordField hipoteka_segurtasun;
	private JTextField txt_kant_ordaindu;
	private JPasswordField segurtasun_ordaindu;
	private JTable transfer_table;
	private JTable sarrera_table;
	private JTable kontuak_table;
	private JTextField txt_limite_kontua;
	private JPasswordField pass_bezero_sortu;
	private JTextField txt_nan_sortu;
	private JTextField txt_tel_sortu;
	private JTextField txt_kontu_sortu_nan;
	private JPasswordField pass_segurtasunkode_kontu;
	private JTextField txt_saldo_sortu;
	private JTextField txt_limite_sortu;
	private JTable hipotekak_table;
	private JTable itxi_table;
	JComboBox<String> cb_sukurtsala = null;
	JComboBox<String> cb_hila = null;
	JComboBox<String> cb_eguna = null;
	JComboBox<String> cb_egoera_kontua = null;
	JComboBox<String> cb_egoera_langile;
	JComboBox<String> cb_txartel_mota =null;
	JComboBox<String> cb_urtea;
	JComboBox<String> cb_sexua;
	JComboBox<String> cb_urtea_lang;
	JComboBox<String> cb_eguna_lang;
	JComboBox<String> cb_hila_lang;
	JComboBox<String> cb_egoera_langile_registro;
	JComboBox<String> cb_sexua_registro_lang;
	JComboBox<String> cb_lanpostu_registro;
	JButton btn_kontu_itzi;
	JButton btn_hipoteka;
	JButton btn_kontuItxi;
	JButton btn_langile_erregistratu;
	JScrollPane kontuak_pane;
	JLabel lbl_iban_bez_erakutsi;
	JLabel lbl_saldo_bez_erakutsi;
	JLabel lbl_iban_kontua;
	JLabel lbl_jabeak_kontua;
	JLabel lbl_saldo_kontua;
	JLabel lbl_txartelid;
	JLabel lbl_kontusortu_iban;
	JLabel lbl_zerrendak;
	JLabel lbl_hipo_egoera;
	JTextField txt_izen_erabiltzaile;
	JScrollPane itxi_pane;
	JScrollPane transfer_pane;
	JButton btn_errefusatu;
	JButton btn_onartu;
	JButton btn_bezero_zerrenda;
	JButton btn_langile_zerrenda;
	private JTextField txt_abizen_erabiltzaile;
	JScrollPane scrollPane_entitateKont;
	JScrollPane pane_hipotekak;
	JScrollPane sarrera_pane;
	JComboBox<String> cb_egoera_sortu;
	JComboBox<String> hipoteka_combo;
	private JTable zerrenda_table;
	private JTextField txt_abizen_editatu;
	private JTextField txt_izen_editatu;
	private JTextField txt_telefono_editatu;
	private JPasswordField pass_bez_editatu;
	private JTextField txt_abizen_edizio_lang;
	private JTextField txt_izen_edizio_lang;
	private JTextField txt_tel_edizio_lang;
	private JPasswordField pass_edizio_lang;
	private JTextField txt_id_sukurtsal;
	private JTextField txt_lang_abizen_registro;
	private JTextField txt_lang_izen_registro;
	private JTextField txt_tel_lang_registro;
	private JPasswordField pass_langile_registro;
	private JTextField txt_sukurtsal_registro;
	private JTextField txt_lang_nan;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App frame = new App();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public App() {
		super("Modern Management Bank");
		setIconImage(Toolkit.getDefaultToolkit().getImage(App.class.getResource("../res/banku_ikono.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 958, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		//////////////////////////////////
		// 			Datuak Kargatu	    //
		//////////////////////////////////		

		urteak_array = ArrayMetodoak.urteakBete();
		hilak_array = ArrayMetodoak.hilakBete();
		egunak_array = ArrayMetodoak.egunakBete(1);
		
		//////////////////////////////////
		// 			 Panelak 		    //
		//////////////////////////////////
		JPanel hasiera = new JPanel();
		contentPane.add(hasiera, "name_181017165039600");
		hasiera.setLayout(null);

		JPanel loginBezero = new JPanel();
		contentPane.add(loginBezero, "name_181745480875400");
		loginBezero.setLayout(null);		

		JPanel loginLangile = new JPanel();
		contentPane.add(loginLangile, "name_83386149582800");
		loginLangile.setLayout(null);

		JPanel bezeroEntitate = new JPanel();
		contentPane.add(bezeroEntitate, "name_3341806070500");
		bezeroEntitate.setLayout(null);
		
		JPanel entitateKontuak = new JPanel();
		contentPane.add(entitateKontuak, "name_246194448959400");
		
		JPanel bezeroKontua = new JPanel();
		contentPane.add(bezeroKontua, "name_248283919860200");
		bezeroKontua.setLayout(null);	
		
		JPanel transferentzia = new JPanel();
		contentPane.add(transferentzia, "name_252021979099300");
		transferentzia.setLayout(null);	

		JPanel transferentziaIkusi = new JPanel();
		contentPane.add(transferentziaIkusi, "name_256080551293500");
		transferentziaIkusi.setLayout(null);
		
		JPanel transferentziaEgin = new JPanel();
		contentPane.add(transferentziaEgin, "name_256350436382600");
		transferentziaEgin.setLayout(null);

		JPanel sarrerak = new JPanel();
		contentPane.add(sarrerak, "name_258001781435100");
		sarrerak.setLayout(null);

		JPanel hipotekaEskatu = new JPanel();
		contentPane.add(hipotekaEskatu, "name_258434853315400");
		hipotekaEskatu.setLayout(null);
		
		JPanel hipotekaEstatus = new JPanel();
		contentPane.add(hipotekaEstatus, "name_259952626453900");
		hipotekaEstatus.setLayout(null);
		
		JPanel hipotekaOrdaindu = new JPanel();
		contentPane.add(hipotekaOrdaindu, "name_504175485620400");
		hipotekaOrdaindu.setLayout(null);

		JPanel mugimenduak = new JPanel();
		contentPane.add(mugimenduak, "name_506277995450400");
		mugimenduak.setLayout(null);

		JPanel langileMenu = new JPanel();
		contentPane.add(langileMenu, "name_506859589204000");
		langileMenu.setLayout(null);

		JPanel kontuakLista = new JPanel();
		contentPane.add(kontuakLista, "name_507539506876700");
		kontuakLista.setLayout(null);

		JPanel infoKontua = new JPanel();
		contentPane.add(infoKontua, "name_508196687186600");
		infoKontua.setLayout(null);

		JPanel erabiltzaileSortu = new JPanel();
		contentPane.add(erabiltzaileSortu, "name_511319668371000");
		erabiltzaileSortu.setLayout(null);

		JPanel kontuSortu = new JPanel();
		contentPane.add(kontuSortu, "name_354467799796100");
		kontuSortu.setLayout(null);

		JPanel kontuBankarioaSortu = new JPanel();
		contentPane.add(kontuBankarioaSortu, "name_356061147224200");
		kontuBankarioaSortu.setLayout(null);

		JPanel hipotekak = new JPanel();
		contentPane.add(hipotekak, "name_357549417063100");
		hipotekak.setLayout(null);
		
		JPanel hipotekaTaulak = new JPanel();
		contentPane.add(hipotekaTaulak, "name_358282949187800");
		hipotekaTaulak.setLayout(null);

		JPanel ixtekoKontuak = new JPanel();
		contentPane.add(ixtekoKontuak, "name_358762862106800");
		ixtekoKontuak.setLayout(null);

		JPanel sukurtsalak = new JPanel();
		contentPane.add(sukurtsalak, "name_359342127690500");
		sukurtsalak.setLayout(null);

		JPanel zerrendak = new JPanel();
		contentPane.add(zerrendak, "name_170100723218100");
		zerrendak.setLayout(null);

		JPanel bezeroEditatu = new JPanel();
		contentPane.add(bezeroEditatu, "name_171839961680600");
		bezeroEditatu.setLayout(null);
		
		JPanel langileEditatu = new JPanel();
		contentPane.add(langileEditatu, "name_1647884240543200");
		langileEditatu.setLayout(null);

		JPanel langileRegistro = new JPanel();
		contentPane.add(langileRegistro, "name_604141533552700");
		langileRegistro.setLayout(null);
		//////////////////////////////////
		// 			  Botoiak 		    //
		//////////////////////////////////
		JButton btn_hasiera = new JButton("");
		btn_hasiera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hasiera.setVisible(false);
				loginBezero.setVisible(true);
			}
		});
		btn_hasiera.setBounds(0, 0, 932, 554);
		ImageIcon logo_hasiera = new ImageIcon(new ImageIcon("src/res/logo2.png").getImage().getScaledInstance(btn_hasiera.getWidth(),btn_hasiera.getHeight(),Image.SCALE_DEFAULT));
		btn_hasiera.setIcon(logo_hasiera);
		btn_hasiera.setOpaque(false);
		btn_hasiera.setContentAreaFilled(false);
		btn_hasiera.setBorderPainted(false);
		hasiera.add(btn_hasiera);		

		JButton btn_bezero = new JButton("Bezero Login");
		btn_bezero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_langile_erabiltzaile.setText("");
				passLangile.setText("");
				loginLangile.setVisible(false);
				loginBezero.setVisible(true);
			}
		});
		btn_bezero.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_bezero.setBounds(746, 151, 176, 36);
		loginLangile.add(btn_bezero);
		
		JButton btn_langile = new JButton("Langile Login");
		btn_langile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_bezero_erabiltzaile.setText("");
				passBezero.setText("");
				loginBezero.setVisible(false);
				loginLangile.setVisible(true);
			}
		});
		btn_langile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btn_langile.setBounds(746, 151, 176, 36);
		loginBezero.add(btn_langile);
		
		JButton btn_bezero_sartu = new JButton("Sartu");
		btn_bezero_sartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if(metodoakSelect.bezeroLogin(txt_bezero_erabiltzaile.getText(),String.valueOf(passBezero.getPassword()))) {

						nan_bezero= txt_bezero_erabiltzaile.getText();
						pass_bezero = String.valueOf(passBezero.getPassword());
						//Langilearen logina erregistratu
						metodoakTxt.loginErregistratu(nan_bezero, "Bezeroa");
						
						bezero = metodoakKargatu.bezeroaKargatu(nan_bezero);
						bezero_entitate = metodoakArray.bezeroarenEntitateak(bezero);

						txt_bezero_erabiltzaile.setText("");
						passBezero.setText("");
						
						entitateak = metodoakSelect.botoiakSortu();

						//Entitateen botoiak sortzen dira
						for(int i=0;i<entitateak.size();i++) {		
							String[] limiteak = entitateak.get(i).getBounds().split("/");
							ImageIcon logo_banco = new ImageIcon(new ImageIcon(entitateak.get(i).getUrl()).getImage().getScaledInstance(Integer.parseInt(limiteak[2]),Integer.parseInt(limiteak[3]),Image.SCALE_DEFAULT));
							JButton btn_banco = new JButton(logo_banco);
							btn_banco.setToolTipText(String.valueOf(i+1));
							btn_banco.addActionListener(new ActionListener(){
								public void actionPerformed(ActionEvent e) {
									if(metodoakBoolean.entitateBalidatu(bezero_entitate, btn_banco.getToolTipText())) {
										id_entitate = Character.getNumericValue(btn_banco.getToolTipText().charAt(0));
										
										kontuBankarioak = metodoakTaulak.bezeroarenKontuak(bezero, id_entitate);
										
										table_entitateKont = new JTable(kontuBankarioak,kontuBankario_header) {
											private static final long serialVersionUID = 1L;

											public boolean editCellAt(int row, int column, java.util.EventObject e) {
									            return false;
									        }	
										};
										table_entitateKont.getColumnModel().getColumn(0).setPreferredWidth(200);
										table_entitateKont.getTableHeader().setReorderingAllowed(false);
										scrollPane_entitateKont.setViewportView(table_entitateKont);
										bezeroEntitate.setVisible(false);
										entitateKontuak.setVisible(true);
									}else {
										JOptionPane.showMessageDialog(null,"Ez duzu konturik entitate honetan!","Informazio", JOptionPane.INFORMATION_MESSAGE);
									}
								}
							});

							btn_banco.setBounds(Integer.parseInt(limiteak[0]), Integer.parseInt(limiteak[1]), Integer.parseInt(limiteak[2]), Integer.parseInt(limiteak[3]));
							bezeroEntitate.add(btn_banco);
							btn_banco.setOpaque(false);
							btn_banco.setContentAreaFilled(false);
							btn_banco.setBorderPainted(false);
						}
						
						loginBezero.setVisible(false);
						bezeroEntitate.setVisible(true);
					}
				}catch (SalbuespenaLogin s) {
					metodoakTxt.loginOkerraErregistratu(txt_bezero_erabiltzaile.getText(),"Bezeroa");
					JOptionPane.showMessageDialog(null,"Login Okerra!","Error!", JOptionPane.ERROR_MESSAGE);
				}catch (SalbuespenaLoginBlokeo s) {
					metodoakTxt.loginOkerraErregistratu(txt_bezero_erabiltzaile.getText(),"Bezeroa");
					JOptionPane.showMessageDialog(null,"Erabiltzaile hau blokeatuta dago!","Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_bezero_sartu.setBounds(437, 423, 89, 23);
		loginBezero.add(btn_bezero_sartu);
		
		
		JButton btn_langile_sartu = new JButton("Sartu");
		btn_langile_sartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					lanpostua = metodoakSelect.langileLogin(txt_langile_erabiltzaile.getText(),String.valueOf(passLangile.getPassword()));				
					if(lanpostua!=null) {	
						if(lanpostua.equals("god")) {
							btn_bezero_zerrenda.setVisible(true);
							btn_langile_zerrenda.setVisible(true);
							btn_langile_erregistratu.setVisible(true);
						}else {
							btn_bezero_zerrenda.setVisible(false);
							btn_langile_zerrenda.setVisible(false);		
							btn_langile_erregistratu.setVisible(false);
						}
						nan_langile = txt_langile_erabiltzaile.getText();
						pass_langile = String.valueOf(passLangile.getPassword());
						//Langilearen logina erregistratu
						metodoakTxt.loginErregistratu(nan_langile, "Bezeroa");
						
						langile = metodoakKargatu.langileaKargatu(nan_langile,lanpostua,pass_langile);
						//Langilearen entitate eta sukurtsalak kargatu
						entitate_izen = metodoakArray.langilearenEntitateak(langile);		
						sukurtsal_kok = metodoakArray.langilearenSukurtsalak(langile, langile.getSukurtsalak().get(0).getEntitateBankario().getIzena());
						
						cb_sukurtsala = new JComboBox<String>(sukurtsal_kok);
						cb_sukurtsala.setBounds(412, 317, 230, 30);
						sukurtsalak.add(cb_sukurtsala);
						
						JComboBox<String> cb_entitate = new JComboBox<String>(entitate_izen);
						cb_entitate.addActionListener (new ActionListener () {
							public void actionPerformed(ActionEvent e) {
								sukurtsal_kok = metodoakArray.langilearenSukurtsalak(langile, cb_entitate.getSelectedItem().toString());
								cb_sukurtsala.removeAllItems();
								DefaultComboBoxModel<String> sukurtsal_kokapena = new DefaultComboBoxModel<String>(sukurtsal_kok);
								cb_sukurtsala.setModel(sukurtsal_kokapena);
							}
						});
						cb_entitate.setBounds(460, 278, 131, 30);
						sukurtsalak.add(cb_entitate);					

						txt_langile_erabiltzaile.setText("");
						passLangile.setText("");
						loginLangile.setVisible(false);
						sukurtsalak.setVisible(true);
					}
				}catch (SalbuespenaLogin s) {
					metodoakTxt.loginOkerraErregistratu(txt_langile_erabiltzaile.getText(),"Langilea");
					JOptionPane.showMessageDialog(null,"Login Okerra!","Error!", JOptionPane.ERROR_MESSAGE);
				} catch (SalbuespenaLoginBlokeo sb) {
					metodoakTxt.loginOkerraErregistratu(txt_langile_erabiltzaile.getText(),"Langilea");
					JOptionPane.showMessageDialog(null,"Erabiltzaile hau blokeatuta dago!","Error!", JOptionPane.ERROR_MESSAGE);
				}			
			}
		});
		btn_langile_sartu.setBounds(437, 423, 89, 23);
		loginLangile.add(btn_langile_sartu);

		//////////////////////////////////
		// 		  Etxea Botoiak 		//
		//////////////////////////////////
		JButton btn_logout_1 = new JButton(logouticon);
		btn_logout_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bezero=null;
				loginBezero.setVisible(true);
				entitateKontuak.setVisible(false);
			}
		});
		btn_logout_1.setBounds(874, 151, 44, 34);		
		btn_logout_1.setOpaque(false);
		btn_logout_1.setContentAreaFilled(false);
		btn_logout_1.setBorderPainted(false);
		bezeroEntitate.add(btn_logout_1);
		entitateKontuak.setLayout(null);

		JButton btn_etxea_3 = new JButton(homeicon);
		btn_etxea_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_etxea_3.setOpaque(false);
		btn_etxea_3.setContentAreaFilled(false);
		btn_etxea_3.setBorderPainted(false);
		btn_etxea_3.setBounds(29, 151, 44, 34);
		bezeroKontua.add(btn_etxea_3);

		JButton btn_etxea_4 = new JButton(homeicon);
		btn_etxea_4.setOpaque(false);
		btn_etxea_4.setContentAreaFilled(false);
		btn_etxea_4.setBorderPainted(false);
		btn_etxea_4.setBounds(29, 151, 44, 34);
		transferentzia.add(btn_etxea_4);

		JButton btn_etxea_5 = new JButton(homeicon);
		btn_etxea_5.setOpaque(false);
		btn_etxea_5.setContentAreaFilled(false);
		btn_etxea_5.setBorderPainted(false);
		btn_etxea_5.setBounds(29, 151, 44, 34);
		transferentziaIkusi.add(btn_etxea_5);
		
		JButton btn_etxea_6 = new JButton(homeicon);
		btn_etxea_6.setOpaque(false);
		btn_etxea_6.setContentAreaFilled(false);
		btn_etxea_6.setBorderPainted(false);
		btn_etxea_6.setBounds(29, 151, 44, 34);
		transferentziaEgin.add(btn_etxea_6);
		
		JButton btn_etxea_7 = new JButton(homeicon);
		btn_etxea_7.setOpaque(false);
		btn_etxea_7.setContentAreaFilled(false);
		btn_etxea_7.setBorderPainted(false);
		btn_etxea_7.setBounds(29, 151, 44, 34);
		sarrerak.add(btn_etxea_7);

		JButton btn_etxea_8 = new JButton(homeicon);
		btn_etxea_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_kantitate_hipoteka.setText("");
				hipoteka_combo.setSelectedIndex(0);
				txt_hipoteka_komisio.setText("3%");
				hipoteka_segurtasun.setText("");
				hipotekaEskatu.setVisible(false);
				bezeroEntitate.setVisible(true);
			}
		});
		btn_etxea_8.setOpaque(false);
		btn_etxea_8.setContentAreaFilled(false);
		btn_etxea_8.setBorderPainted(false);
		btn_etxea_8.setBounds(29, 151, 44, 34);
		hipotekaEskatu.add(btn_etxea_8);

		JButton btn_etxea_9 = new JButton(homeicon);
		btn_etxea_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hipotekaEstatus.setVisible(false);
				bezeroEntitate.setVisible(true);
			}
		});
		btn_etxea_9.setOpaque(false);
		btn_etxea_9.setContentAreaFilled(false);
		btn_etxea_9.setBorderPainted(false);
		btn_etxea_9.setBounds(29, 151, 44, 34);
		hipotekaEstatus.add(btn_etxea_9);
		
		JButton btn_etxea_10 = new JButton(homeicon);
		btn_etxea_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_kant_ordaindu.setText("");
				segurtasun_ordaindu.setText("");
				hipotekaOrdaindu.setVisible(false);
				bezeroEntitate.setVisible(true);
				
			}
		});
		btn_etxea_10.setOpaque(false);
		btn_etxea_10.setContentAreaFilled(false);
		btn_etxea_10.setBorderPainted(false);
		btn_etxea_10.setBounds(29, 151, 44, 34);
		hipotekaOrdaindu.add(btn_etxea_10);

		JButton btn_etxea_11 = new JButton(homeicon);
		btn_etxea_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mugimenduak.setVisible(false);
				bezeroEntitate.setVisible(true);
			}
		});
		btn_etxea_11.setOpaque(false);
		btn_etxea_11.setContentAreaFilled(false);
		btn_etxea_11.setBorderPainted(false);
		btn_etxea_11.setBounds(29, 151, 44, 34);
		mugimenduak.add(btn_etxea_11);

		JButton btn_etxea_13 = new JButton(homeicon);
		btn_etxea_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kontuakLista.setVisible(false);
				sukurtsalak.setVisible(true);
			}
		});
		btn_etxea_13.setOpaque(false);
		btn_etxea_13.setContentAreaFilled(false);
		btn_etxea_13.setBorderPainted(false);
		btn_etxea_13.setBounds(29, 151, 44, 34);
		kontuakLista.add(btn_etxea_13);

		JButton btn_etxea_14 = new JButton(homeicon);
		btn_etxea_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_limite_kontua.setText("");	
				cb_egoera_kontua.setSelectedIndex(0);
				infoKontua.setVisible(true);
				sukurtsalak.setVisible(true);
			}
		});
		btn_etxea_14.setOpaque(false);
		btn_etxea_14.setContentAreaFilled(false);
		btn_etxea_14.setBorderPainted(false);
		btn_etxea_14.setBounds(29, 151, 44, 34);
		infoKontua.add(btn_etxea_14);

		JButton btn_etxea_15 = new JButton(homeicon);
		btn_etxea_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_izen_erabiltzaile.setText("");
				txt_abizen_erabiltzaile.setText("");
				txt_nan_sortu.setText("");
				pass_bezero_sortu.setText("");
				cb_urtea.setSelectedIndex(0);
				cb_hila.setSelectedIndex(0);
				cb_eguna.setSelectedIndex(0);
				cb_sexua.setSelectedIndex(0);
				txt_tel_sortu.setText("");
				erabiltzaileSortu.setVisible(false);
				sukurtsalak.setVisible(true);
			}
		});
		btn_etxea_15.setOpaque(false);
		btn_etxea_15.setContentAreaFilled(false);
		btn_etxea_15.setBorderPainted(false);
		btn_etxea_15.setBounds(29, 151, 44, 34);
		erabiltzaileSortu.add(btn_etxea_15);

		JButton btn_etxea_16 = new JButton(homeicon);
		btn_etxea_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_kontu_sortu_nan.setText("");
				pass_segurtasunkode_kontu.setText("");
				cb_txartel_mota.setSelectedIndex(0);
				erabiltzaileak= new String[0][4];
				kontuSortu.setVisible(false);
				sukurtsalak.setVisible(true);
			}
		});
		btn_etxea_16.setOpaque(false);
		btn_etxea_16.setContentAreaFilled(false);
		btn_etxea_16.setBorderPainted(false);
		btn_etxea_16.setBounds(29, 151, 44, 34);
		kontuSortu.add(btn_etxea_16);

		JButton btn_etxea_17 = new JButton(homeicon);
		btn_etxea_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				erabiltzaileak = new String [0][4];
				txt_saldo_sortu.setText("");
				txt_limite_sortu.setText("");
				cb_egoera_sortu.setSelectedIndex(0);
				kontuBankarioaSortu.setVisible(false);
				sukurtsalak.setVisible(true);
			}
		});
		btn_etxea_17.setOpaque(false);
		btn_etxea_17.setContentAreaFilled(false);
		btn_etxea_17.setBorderPainted(false);
		btn_etxea_17.setBounds(29, 151, 44, 34);
		kontuBankarioaSortu.add(btn_etxea_17);
		
		JButton btn_etxea_18 = new JButton(homeicon);
		btn_etxea_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hipotekak.setVisible(false);
				sukurtsalak.setVisible(true);
			}
		});
		btn_etxea_18.setOpaque(false);
		btn_etxea_18.setContentAreaFilled(false);
		btn_etxea_18.setBorderPainted(false);
		btn_etxea_18.setBounds(29, 151, 44, 34);
		hipotekak.add(btn_etxea_18);

		JButton btn_etxea_19 = new JButton(homeicon);
		btn_etxea_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hipotekaTaulak.setVisible(false);
				sukurtsalak.setVisible(true);
			}
		});
		btn_etxea_19.setOpaque(false);
		btn_etxea_19.setContentAreaFilled(false);
		btn_etxea_19.setBorderPainted(false);
		btn_etxea_19.setBounds(29, 151, 44, 34);
		hipotekaTaulak.add(btn_etxea_19);

		JButton btn_etxea_20 = new JButton(homeicon);
		btn_etxea_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ixtekoKontuak.setVisible(false);
				sukurtsalak.setVisible(true);
			}
		});
		btn_etxea_20.setOpaque(false);
		btn_etxea_20.setContentAreaFilled(false);
		btn_etxea_20.setBorderPainted(false);
		btn_etxea_20.setBounds(29, 151, 44, 34);
		ixtekoKontuak.add(btn_etxea_20);

		JButton btn_logout_2 = new JButton(logouticon);
		btn_logout_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				langile=null;
				sukurtsalak.setVisible(false);
				loginLangile.setVisible(true);
			}
		});
		btn_logout_2.setOpaque(false);
		btn_logout_2.setContentAreaFilled(false);
		btn_logout_2.setBorderPainted(false);
		btn_logout_2.setBounds(874, 151, 44, 34);
		sukurtsalak.add(btn_logout_2);
		
		JButton btn_etxea_22 = new JButton(homeicon);
		btn_etxea_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				langileEditatu.setVisible(false);
				sukurtsalak.setVisible(true);
			}
		});
		btn_etxea_22.setOpaque(false);
		btn_etxea_22.setContentAreaFilled(false);
		btn_etxea_22.setBorderPainted(false);
		btn_etxea_22.setBounds(29, 151, 44, 34);
		bezeroEditatu.add(btn_etxea_22);
		
		JButton btn_etxea_23 = new JButton(homeicon);
		btn_etxea_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				langileEditatu.setVisible(false);
				sukurtsalak.setVisible(true);
			}
		});
		btn_etxea_23.setOpaque(false);
		btn_etxea_23.setContentAreaFilled(false);
		btn_etxea_23.setBorderPainted(false);
		btn_etxea_23.setBounds(29, 151, 44, 34);
		langileEditatu.add(btn_etxea_23);
		//////////////////////////////////
		// 			Aurrera Atzera 		//
		//////////////////////////////////
		JButton btn_atzera_1 = new JButton(logo_atzera);
		btn_atzera_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bezeroEntitate.setVisible(true);
				sukurtsalak.setVisible(false);
			}
		});
		btn_atzera_1.setBounds(15, 513, 44, 30);
		btn_atzera_1.setOpaque(false);
		btn_atzera_1.setContentAreaFilled(false);
		btn_atzera_1.setBorderPainted(false);
		entitateKontuak.add(btn_atzera_1);
		
		JButton btn_aurrera_1 = new JButton(logo_aurrera);
		btn_aurrera_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int column = 0;
				int row = table_entitateKont.getSelectedRow();
				if(row >= 0 && column >= 0) {
					bezero_iban_kontua = table_entitateKont.getModel().getValueAt(row, column).toString(); // Taulan aukeratutako kontuaren IBAN-a gordetzen du
					bezero_iban_kontua = bezero_iban_kontua.replace(" ", "");
					bezero_kontu_egoera = table_entitateKont.getModel().getValueAt(row, 2).toString();
					if(bezero_kontu_egoera.equals("aktiboa")) {
						btn_hipoteka.setEnabled(true);
					}else if(bezero_kontu_egoera.equals("ixteko")){
						btn_hipoteka.setEnabled(false);
						btn_kontuItxi.setEnabled(false);
					}else {
						btn_hipoteka.setEnabled(false);
						btn_kontuItxi.setEnabled(true);						
					}
					lbl_iban_bez_erakutsi.setText(table_entitateKont.getModel().getValueAt(row, 0).toString());
					lbl_saldo_bez_erakutsi.setText(table_entitateKont.getModel().getValueAt(row, 1).toString());
					bezeroKontua.setVisible(true);
					entitateKontuak.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Kontu bankario bat aukeratu behar duzu.","Error!", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_aurrera_1.setBounds(873, 513, 44, 30);
		btn_aurrera_1.setOpaque(false);
		btn_aurrera_1.setContentAreaFilled(false);
		btn_aurrera_1.setBorderPainted(false);
		entitateKontuak.add(btn_aurrera_1);		

		JButton btn_atzera_2 = new JButton(logo_atzera);
		btn_atzera_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entitateKontuak.setVisible(true);
				bezeroKontua.setVisible(false);
			}
		});
		btn_atzera_2.setOpaque(false);
		btn_atzera_2.setContentAreaFilled(false);
		btn_atzera_2.setBorderPainted(false);
		btn_atzera_2.setBounds(15, 513, 44, 30);
		bezeroKontua.add(btn_atzera_2);
		
		JButton btn_atzera_3 = new JButton(logo_atzera);
		btn_atzera_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bezeroKontua.setVisible(true);
				transferentzia.setVisible(false);
			}
		});
		btn_atzera_3.setOpaque(false);
		btn_atzera_3.setContentAreaFilled(false);
		btn_atzera_3.setBorderPainted(false);
		btn_atzera_3.setBounds(15, 513, 44, 30);
		transferentzia.add(btn_atzera_3);
		
		JButton btn_atzera_4 = new JButton(logo_atzera);
		btn_atzera_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bezero==null) {
					infoKontua.setVisible(true);
				}else if(langile==null) {
					transferentzia.setVisible(true);
				}
				transferentziaIkusi.setVisible(false);
			}
		});
		btn_atzera_4.setOpaque(false);
		btn_atzera_4.setContentAreaFilled(false);
		btn_atzera_4.setBorderPainted(false);
		btn_atzera_4.setBounds(15, 513, 44, 30);
		transferentziaIkusi.add(btn_atzera_4);
		
		JButton btn_atzera_5 = new JButton(logo_atzera);
		btn_atzera_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_jasotzaile.setText("");
				txt_kantitate.setText("");
				txt_kontzeptu.setText("");
				transferentzia_segurtasun.setText("");
				transferentzia.setVisible(true);
				transferentziaEgin.setVisible(false);
			}
		});
		btn_atzera_5.setOpaque(false);
		btn_atzera_5.setContentAreaFilled(false);
		btn_atzera_5.setBorderPainted(false);
		btn_atzera_5.setBounds(15, 513, 44, 30);
		transferentziaEgin.add(btn_atzera_5);
		
		JButton btn_atzera_6 = new JButton(logo_atzera);
		btn_atzera_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(langile == null) {
					bezeroKontua.setVisible(true);
					sarrerak.setVisible(false);
				}else if(bezero==null){
					infoKontua.setVisible(true);
					sarrerak.setVisible(false);
				}
			}
		});
		btn_atzera_6.setOpaque(false);
		btn_atzera_6.setContentAreaFilled(false);
		btn_atzera_6.setBorderPainted(false);
		btn_atzera_6.setBounds(15, 513, 44, 30);
		sarrerak.add(btn_atzera_6);

		JButton btn_atzera_7 = new JButton(logo_atzera);
		btn_atzera_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_kantitate_hipoteka.setText("");
				hipoteka_combo.setSelectedIndex(0);
				txt_hipoteka_komisio.setText("3%");
				hipoteka_segurtasun.setText("");
				
				hipotekaEskatu.setVisible(false);
				bezeroKontua.setVisible(true);
			}
		});
		btn_atzera_7.setOpaque(false);
		btn_atzera_7.setContentAreaFilled(false);
		btn_atzera_7.setBorderPainted(false);
		btn_atzera_7.setBounds(15, 513, 44, 30);
		hipotekaEskatu.add(btn_atzera_7);
		
		JButton btn_atzera_8 = new JButton(logo_atzera);
		btn_atzera_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hipotekaEstatus.setVisible(false);
				bezeroKontua.setVisible(true);
			}
		});
		btn_atzera_8.setOpaque(false);
		btn_atzera_8.setContentAreaFilled(false);
		btn_atzera_8.setBorderPainted(false);
		btn_atzera_8.setBounds(15, 513, 44, 30);
		hipotekaEstatus.add(btn_atzera_8);

		JButton btn_atzera_9 = new JButton(logo_atzera);
		btn_atzera_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_kant_ordaindu.setText("");
				segurtasun_ordaindu.setText("");
				hipotekaOrdaindu.setVisible(false);
				hipotekaEstatus.setVisible(true);
			}
		});
		btn_atzera_9.setOpaque(false);
		btn_atzera_9.setContentAreaFilled(false);
		btn_atzera_9.setBorderPainted(false);
		btn_atzera_9.setBounds(15, 513, 44, 30);
		hipotekaOrdaindu.add(btn_atzera_9);
		
		JButton btn_atzera_10 = new JButton(logo_atzera);
		btn_atzera_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bezeroKontua.setVisible(true);
				mugimenduak.setVisible(false);
			}
		});
		btn_atzera_10.setOpaque(false);
		btn_atzera_10.setContentAreaFilled(false);
		btn_atzera_10.setBorderPainted(false);
		btn_atzera_10.setBounds(15, 513, 44, 30);
		mugimenduak.add(btn_atzera_10);	
		
		JButton btn_atzera_11 = new JButton(logo_atzera);
		btn_atzera_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				langileMenu.setVisible(false);
				sukurtsalak.setVisible(true);
			}
		});
		btn_atzera_11.setOpaque(false);
		btn_atzera_11.setContentAreaFilled(false);
		btn_atzera_11.setBorderPainted(false);
		btn_atzera_11.setBounds(15, 513, 44, 30);
		langileMenu.add(btn_atzera_11);

		JButton btn_aurrera_2 = new JButton(logo_aurrera);
		btn_aurrera_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = kontuak_table.getSelectedRow();
				if(row>=0) {
					langile_aukeratu_iban=kontuak_table.getModel().getValueAt(row, 0).toString(); 
					langile_kontu_info = metodoakArray.langileKontuInfo(langile, langile_aukeratu_iban, sukurtsal_izen);
					lbl_iban_kontua.setText(langile_kontu_info[0]);
					lbl_jabeak_kontua.setText(langile_kontu_info[1]);
					lbl_saldo_kontua.setText(langile_kontu_info[2]);

					cb_egoera_kontua = new JComboBox<String>(kontu_egoera);
					cb_egoera_kontua.setBounds(415, 362, 126, 30);
					infoKontua.add(cb_egoera_kontua);
					cb_egoera_kontua.setSelectedItem(langile_kontu_info[3]);
					
					txt_limite_kontua.setText(langile_kontu_info[4]);
					kontuakLista.setVisible(false);
					infoKontua.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Kontu Bankario bat aukeratu behar duzu.","Alerta", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_aurrera_2.setOpaque(false);
		btn_aurrera_2.setContentAreaFilled(false);
		btn_aurrera_2.setBorderPainted(false);
		btn_aurrera_2.setBounds(878, 524, 44, 30);
		kontuakLista.add(btn_aurrera_2);
		
		JButton btn_atzera_12 = new JButton(logo_atzera);
		btn_atzera_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kontuakLista.setVisible(false);
				langileMenu.setVisible(true);
			}
		});
		btn_atzera_12.setOpaque(false);
		btn_atzera_12.setContentAreaFilled(false);
		btn_atzera_12.setBorderPainted(false);
		btn_atzera_12.setBounds(15, 513, 44, 30);
		kontuakLista.add(btn_atzera_12);

		JButton btn_atzera_13 = new JButton(logo_atzera);
		btn_atzera_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_limite_kontua.setText("");
				cb_egoera_kontua.setSelectedIndex(0);
				infoKontua.setVisible(false);
				kontuakLista.setVisible(true);
			}
		});
		btn_atzera_13.setOpaque(false);
		btn_atzera_13.setContentAreaFilled(false);
		btn_atzera_13.setBorderPainted(false);
		btn_atzera_13.setBounds(15, 513, 44, 30);
		infoKontua.add(btn_atzera_13);

		JButton btn_atzera_14 = new JButton(logo_atzera);
		btn_atzera_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_izen_erabiltzaile.setText("");
				txt_abizen_erabiltzaile.setText("");
				txt_nan_sortu.setText("");
				pass_bezero_sortu.setText("");
				cb_urtea.setSelectedIndex(0);
				cb_hila.setSelectedIndex(0);
				cb_eguna.setSelectedIndex(0);
				cb_sexua.setSelectedIndex(0);
				txt_tel_sortu.setText("");
				erabiltzaileSortu.setVisible(false);
				langileMenu.setVisible(true);
			}
		});
		btn_atzera_14.setOpaque(false);
		btn_atzera_14.setContentAreaFilled(false);
		btn_atzera_14.setBorderPainted(false);
		btn_atzera_14.setBounds(15, 513, 44, 30);
		erabiltzaileSortu.add(btn_atzera_14);

		JButton btn_atzera_15 = new JButton(logo_atzera);
		btn_atzera_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_kontu_sortu_nan.setText("");
				pass_segurtasunkode_kontu.setText("");
				cb_txartel_mota.setSelectedIndex(0);
				erabiltzaileak= new String[0][4];
				kontuSortu.setVisible(false);
				langileMenu.setVisible(true);
			}
		});
		btn_atzera_15.setOpaque(false);
		btn_atzera_15.setContentAreaFilled(false);
		btn_atzera_15.setBorderPainted(false);
		btn_atzera_15.setBounds(15, 513, 44, 30);
		kontuSortu.add(btn_atzera_15);
		
		JButton btn_atzera_16 = new JButton(logo_atzera);
		btn_atzera_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_saldo_sortu.setText("");
				txt_limite_sortu.setText("");
				cb_egoera_sortu.setSelectedIndex(0);
				kontuBankarioaSortu.setVisible(false);
				kontuSortu.setVisible(true);
			}
		});
		btn_atzera_16.setOpaque(false);
		btn_atzera_16.setContentAreaFilled(false);
		btn_atzera_16.setBorderPainted(false);
		btn_atzera_16.setBounds(15, 513, 44, 30);
		kontuBankarioaSortu.add(btn_atzera_16);
		
		JButton btn_atzera_17 = new JButton(logo_atzera);
		btn_atzera_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hipotekak.setVisible(false);
				langileMenu.setVisible(true);
			}
		});
		btn_atzera_17.setOpaque(false);
		btn_atzera_17.setContentAreaFilled(false);
		btn_atzera_17.setBorderPainted(false);
		btn_atzera_17.setBounds(15, 513, 44, 30);
		hipotekak.add(btn_atzera_17);
		
		JButton btn_atzera_18 = new JButton(logo_atzera);
		btn_atzera_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hipotekaTaulak.setVisible(false);
				hipotekak.setVisible(true);
			}
		});
		btn_atzera_18.setOpaque(false);
		btn_atzera_18.setContentAreaFilled(false);
		btn_atzera_18.setBorderPainted(false);
		btn_atzera_18.setBounds(15, 513, 44, 30);
		hipotekaTaulak.add(btn_atzera_18);
		
		JButton btn_atzera_19 = new JButton(logo_atzera);
		btn_atzera_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ixtekoKontuak.setVisible(false);
				langileMenu.setVisible(true);
			}
		});
		btn_atzera_19.setOpaque(false);
		btn_atzera_19.setContentAreaFilled(false);
		btn_atzera_19.setBorderPainted(false);
		btn_atzera_19.setBounds(15, 513, 44, 30);
		ixtekoKontuak.add(btn_atzera_19);

		JButton btn_aurrera_3 = new JButton(logo_aurrera);
		btn_aurrera_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sukurtsal_izen = cb_sukurtsala.getSelectedItem().toString();
				if(sukurtsal_izen != null) {
					if(lanpostua.equals("zuzendaria") || lanpostua.equals("god")) {
						btn_kontu_itzi.setVisible(true);
					}else {
						btn_kontu_itzi.setVisible(false);
					}
					sukurtsalak.setVisible(false);
					langileMenu.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null,"Sukurtsala aukeratu!","Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_aurrera_3.setOpaque(false);
		btn_aurrera_3.setContentAreaFilled(false);
		btn_aurrera_3.setBorderPainted(false);
		btn_aurrera_3.setBounds(873, 513, 44, 30);
		sukurtsalak.add(btn_aurrera_3);		

		JButton btn_atzera_20 = new JButton(logo_atzera);
		btn_atzera_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zerrendak.setVisible(false);
				sukurtsalak.setVisible(true);
			}
		});
		btn_atzera_20.setOpaque(false);
		btn_atzera_20.setContentAreaFilled(false);
		btn_atzera_20.setBorderPainted(false);
		btn_atzera_20.setBounds(15, 513, 44, 30);
		zerrendak.add(btn_atzera_20);
		
		JButton btn_atzera_21 = new JButton(logo_atzera);
		btn_atzera_21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bezeroEditatu.setVisible(false);
				zerrendak.setVisible(true);
			}
		});
		btn_atzera_21.setOpaque(false);
		btn_atzera_21.setContentAreaFilled(false);
		btn_atzera_21.setBorderPainted(false);
		btn_atzera_21.setBounds(15, 513, 44, 30);
		bezeroEditatu.add(btn_atzera_21);
		
		JButton btn_atzera_22 = new JButton(logo_atzera);
		btn_atzera_22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				langileEditatu.setVisible(false);
				zerrendak.setVisible(true);
			}
		});
		btn_atzera_22.setOpaque(false);
		btn_atzera_22.setContentAreaFilled(false);
		btn_atzera_22.setBorderPainted(false);
		btn_atzera_22.setBounds(15, 513, 44, 30);
		langileEditatu.add(btn_atzera_22);
		
		JButton btn_atzera_23 = new JButton(logo_atzera);
		btn_atzera_23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_lang_nan.setText("");
				txt_lang_izen_registro.setText("");
				txt_lang_abizen_registro.setText("");
				cb_urtea_lang.setSelectedIndex(0);
				cb_hila_lang.setSelectedIndex(0);
				cb_eguna_lang.setSelectedIndex(0);
				cb_egoera_langile_registro.setSelectedIndex(0);
				cb_sexua_registro_lang.setSelectedIndex(0);
				txt_tel_lang_registro.setText("");
				pass_langile_registro.setText("");
				cb_lanpostu_registro.setSelectedIndex(0);
				txt_sukurtsal_registro.setText("");
				sukurtsalak.setVisible(true);
				langileRegistro.setVisible(false);
			}
		});
		btn_atzera_23.setOpaque(false);
		btn_atzera_23.setContentAreaFilled(false);
		btn_atzera_23.setBorderPainted(false);
		btn_atzera_23.setBounds(15, 513, 44, 30);
		langileRegistro.add(btn_atzera_23);
		//////////////////////////////////
		// 			Label       		//
		//////////////////////////////////
		JLabel lblNewLabel = new JLabel("LOGIN BEZERO");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(407, 206, 140, 45);
		loginBezero.add(lblNewLabel);		
		
		JLabel lbl_erabiltzaile = new JLabel("Erabiltzailea:");
		lbl_erabiltzaile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_erabiltzaile.setBounds(315, 281, 82, 30);
		loginBezero.add(lbl_erabiltzaile);
		
		txt_bezero_erabiltzaile = new JTextField();
		txt_bezero_erabiltzaile.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {				
				if(txt_bezero_erabiltzaile.getText().length() >= 9 ){
					e.consume();
				}
			}
		});
		txt_bezero_erabiltzaile.setBounds(407, 281, 171, 30);
		loginBezero.add(txt_bezero_erabiltzaile);
		txt_bezero_erabiltzaile.setColumns(10);
		
		JLabel lbl_erabiltzaile_pass = new JLabel("Pasahitza:");
		lbl_erabiltzaile_pass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_erabiltzaile_pass.setBounds(315, 345, 82, 30);
		loginBezero.add(lbl_erabiltzaile_pass);
		
		passBezero = new JPasswordField();
		passBezero.setBounds(407, 345, 171, 30);
		loginBezero.add(passBezero);				
		
		//////////////////////////////////
		// 			Label fondo 		//
		//////////////////////////////////
		JLabel lbl_fondo_bezero = new JLabel(fondo_argazki);
		lbl_fondo_bezero.setBounds(0, 0, 932, 130);
		loginBezero.add(lbl_fondo_bezero);
		lbl_fondo_bezero.setIcon(fondo_argazki);	

		JLabel lbl_fondo_langile = new JLabel(fondo_argazki);
		lbl_fondo_langile.setBounds(0, 0, 932, 130);
		loginLangile.add(lbl_fondo_langile);		

		JLabel lbl_fondo_entitate = new JLabel(fondo_argazki);
		lbl_fondo_entitate.setBounds(0, 0, 932, 130);
		bezeroEntitate.add(lbl_fondo_entitate);
		
		JLabel lbl_ent_bank = new JLabel("ENTITATE BANKARIOAK");
		lbl_ent_bank.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_ent_bank.setBounds(375, 154, 210, 48);
		bezeroEntitate.add(lbl_ent_bank);
		
		JButton btn_erabiltziale_ezabatu = new JButton("Erabiltzailea Ezabatu");
		btn_erabiltziale_ezabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				int erantzuna = JOptionPane.showOptionDialog(null, "Ziur zaude erabiltzailea EZABATU nahi duzula?","Berrespena", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, aukerak,aukerak[1]);
				if(erantzuna == JOptionPane.YES_OPTION) {
					try {
							metodoakDelete.erabiltzaileEzabatu(nan_bezero);
							JOptionPane.showMessageDialog(null, "Erabiltzailea ezabatu da, eskerrik asko gure zerbitzuak erabiltzeagaitik!","Informazio", JOptionPane.INFORMATION_MESSAGE);
							bezeroEntitate.setVisible(false);
							loginBezero.setVisible(true);	
					}catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(null, "Erabiltzailea ezin izan da ezabatu!","Informazio", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		btn_erabiltziale_ezabatu.setBackground(Color.LIGHT_GRAY);
		btn_erabiltziale_ezabatu.setBounds(396, 484, 154, 34);
		bezeroEntitate.add(btn_erabiltziale_ezabatu);

		JLabel lbl_fondo_entitatekontuak = new JLabel(fondo_argazki);
		lbl_fondo_entitatekontuak.setBounds(0, 0, 932, 130);
		entitateKontuak.add(lbl_fondo_entitatekontuak);

		JLabel lbl_fondo_bezeroKontua = new JLabel(fondo_argazki);
		lbl_fondo_bezeroKontua.setBounds(0, 0, 932, 130);
		bezeroKontua.add(lbl_fondo_bezeroKontua);
		
		JLabel lbl_fondo_transfer = new JLabel(fondo_argazki);
		lbl_fondo_transfer.setBounds(0, 0, 932, 130);
		transferentzia.add(lbl_fondo_transfer);
		
		JLabel lbl_fondo_trasnferIkusi = new JLabel(fondo_argazki);
		lbl_fondo_trasnferIkusi.setBounds(0, 0, 932, 130);
		transferentziaIkusi.add(lbl_fondo_trasnferIkusi);
		
		JLabel lbl_fondo_trasnferEgin = new JLabel(fondo_argazki);
		lbl_fondo_trasnferEgin.setBounds(0, 0, 932, 130);
		transferentziaEgin.add(lbl_fondo_trasnferEgin);
		
		JLabel lbl_fondo_sarrerak = new JLabel(fondo_argazki);
		lbl_fondo_sarrerak.setBounds(0, 0, 932, 130);
		sarrerak.add(lbl_fondo_sarrerak);

		JLabel lbl_fondo_hipoteka = new JLabel(fondo_argazki);
		lbl_fondo_hipoteka.setBounds(0, 0, 932, 130);
		hipotekaEskatu.add(lbl_fondo_hipoteka);
		
		JLabel lbl_fondo_hipoteka_2 = new JLabel(fondo_argazki);
		lbl_fondo_hipoteka_2.setBounds(0, 0, 932, 130);
		hipotekaEstatus.add(lbl_fondo_hipoteka_2);

		JLabel lbl_fondo_hipoteka_3 = new JLabel(fondo_argazki);
		lbl_fondo_hipoteka_3.setBounds(0, 0, 932, 130);
		hipotekaOrdaindu.add(lbl_fondo_hipoteka_3);

		JLabel lbl_fondo_mugimenduak = new JLabel(fondo_argazki);
		lbl_fondo_mugimenduak.setBounds(0, 0, 932, 130);
		mugimenduak.add(lbl_fondo_mugimenduak);

		JLabel lbl_fondo_gerentea = new JLabel(fondo_argazki);
		lbl_fondo_gerentea.setBounds(0, 0, 932, 130);
		langileMenu.add(lbl_fondo_gerentea);
		
		JLabel lbl_fondo_kontuak_info = new JLabel(fondo_argazki);
		lbl_fondo_kontuak_info.setBounds(0, 0, 932, 130);
		infoKontua.add(lbl_fondo_kontuak_info);
		
		JLabel lbl_fondo_kontuak_list = new JLabel(fondo_argazki);
		lbl_fondo_kontuak_list.setBounds(0, 0, 932, 130);
		kontuakLista.add(lbl_fondo_kontuak_list);		

		JLabel lbl_fondo_erabiltzaile_sortu = new JLabel(fondo_argazki);
		lbl_fondo_erabiltzaile_sortu.setBounds(0, 0, 932, 130);
		erabiltzaileSortu.add(lbl_fondo_erabiltzaile_sortu);

		JLabel lbl_fondo_kontuasortu = new JLabel(fondo_argazki);
		lbl_fondo_kontuasortu.setBounds(0, 0, 932, 130);
		kontuSortu.add(lbl_fondo_kontuasortu);

		JLabel lbl_fondo_kontubanksortu = new JLabel(fondo_argazki);
		lbl_fondo_kontubanksortu.setBounds(0, 0, 932, 130);
		kontuBankarioaSortu.add(lbl_fondo_kontubanksortu);
		
		JLabel lbl_fondo_hipotekak = new JLabel(fondo_argazki);
		lbl_fondo_hipotekak.setBounds(0, 0, 932, 130);
		hipotekak.add(lbl_fondo_hipotekak);

		JLabel lbl_fondo_hipoteka_taulak = new JLabel(fondo_argazki);
		lbl_fondo_hipoteka_taulak.setBounds(0, 0, 932, 130);
		hipotekaTaulak.add(lbl_fondo_hipoteka_taulak);
		
		JLabel lbl_fondo_itxi = new JLabel(fondo_argazki);
		lbl_fondo_itxi.setBounds(0, 0, 932, 130);
		ixtekoKontuak.add(lbl_fondo_itxi);		
		
		JLabel lbl_fondo_sukurtsal = new JLabel(fondo_argazki);
		lbl_fondo_sukurtsal.setBounds(0, 0, 932, 130);
		sukurtsalak.add(lbl_fondo_sukurtsal);

		JLabel lbl_fondo_zerrendak = new JLabel(fondo_argazki);
		lbl_fondo_zerrendak.setBounds(0, 0, 932, 130);
		zerrendak.add(lbl_fondo_zerrendak);	
		
		JLabel lbl_fondo_editatu = new JLabel(fondo_argazki);
		lbl_fondo_editatu.setBounds(0, 0, 932, 130);
		bezeroEditatu.add(lbl_fondo_editatu);
		
		JLabel lbl_fondo_editatu_lang = new JLabel(fondo_argazki);
		lbl_fondo_editatu_lang.setBounds(0, 0, 932, 130);
		langileEditatu.add(lbl_fondo_editatu_lang);
		
		JLabel lbl_fondo_lang_registro = new JLabel(fondo_argazki);
		lbl_fondo_lang_registro.setBounds(0, 0, 932, 130);
		langileRegistro.add(lbl_fondo_lang_registro);
		
		JScrollPane pane_zerrenda = new JScrollPane();
		pane_zerrenda.setBounds(117, 253, 700, 205);
		zerrendak.add(pane_zerrenda);
		
		lbl_zerrendak = new JLabel("PERTSONA ZERRENDAK");
		lbl_zerrendak.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_zerrendak.setBounds(369, 181, 280, 45);
		zerrendak.add(lbl_zerrendak);
						
		
		JLabel lbl_hipo_total = new JLabel("Hipoteka Totala:");
		lbl_hipo_total.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hipo_total.setBounds(369, 214, 112, 30);
		hipotekaEstatus.add(lbl_hipo_total);
		
		JLabel lbl_hasiera = new JLabel("Hasiera Data:");
		lbl_hasiera.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hasiera.setBounds(369, 259, 112, 30);
		hipotekaEstatus.add(lbl_hasiera);
		
		JLabel lbl_hipo_amaiera = new JLabel("Amaiera Data:");
		lbl_hipo_amaiera.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hipo_amaiera.setBounds(369, 300, 112, 30);
		hipotekaEstatus.add(lbl_hipo_amaiera);
		
		JLabel lbl_komisioa_2 = new JLabel("Komisioa: ");
		lbl_komisioa_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_komisioa_2.setBounds(369, 345, 112, 30);
		hipotekaEstatus.add(lbl_komisioa_2);
		
		JButton btn_ordaindu = new JButton("Ordaindu");
		btn_ordaindu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				hipotekaEstatus.setVisible(false);
				hipotekaOrdaindu.setVisible(true);
			}
		});
		btn_ordaindu.setBounds(388, 485, 153, 34);
		hipotekaEstatus.add(btn_ordaindu);
		
		JLabel lbl_ordainduta = new JLabel("Ordainduta:");
		lbl_ordainduta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_ordainduta.setBounds(369, 386, 112, 30);
		hipotekaEstatus.add(lbl_ordainduta);
		
		JLabel lbl_hipoteka_total = new JLabel("totala");
		lbl_hipoteka_total.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hipoteka_total.setBounds(469, 214, 112, 30);
		hipotekaEstatus.add(lbl_hipoteka_total);
		
		JLabel lbl_hipoteka_hasiera = new JLabel("hasiera data");
		lbl_hipoteka_hasiera.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hipoteka_hasiera.setBounds(469, 259, 112, 30);
		hipotekaEstatus.add(lbl_hipoteka_hasiera);
		
		JLabel lbl_hipoteka_amaiera = new JLabel("amaiera data");
		lbl_hipoteka_amaiera.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hipoteka_amaiera.setBounds(469, 300, 112, 30);
		hipotekaEstatus.add(lbl_hipoteka_amaiera);
		
		JLabel lbl_hipoteka_komisio_ikusi = new JLabel("komisioa");
		lbl_hipoteka_komisio_ikusi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hipoteka_komisio_ikusi.setBounds(469, 345, 112, 30);
		hipotekaEstatus.add(lbl_hipoteka_komisio_ikusi);
		
		JLabel lbl_hipoteka_ordainduta = new JLabel("ordainduta");
		lbl_hipoteka_ordainduta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hipoteka_ordainduta.setBounds(469, 386, 112, 30);
		hipotekaEstatus.add(lbl_hipoteka_ordainduta);
		
		JLabel lblNewLabel_2 = new JLabel("HIPOTEKA EGOERA");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(388, 151, 153, 48);
		hipotekaEstatus.add(lblNewLabel_2);
		
		JLabel lbl_egoera_hipo = new JLabel("Egoera:");
		lbl_egoera_hipo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_egoera_hipo.setBounds(369, 427, 112, 30);
		hipotekaEstatus.add(lbl_egoera_hipo);
		
		lbl_hipo_egoera = new JLabel("egoera");
		lbl_hipo_egoera.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hipo_egoera.setBounds(469, 427, 112, 30);
		hipotekaEstatus.add(lbl_hipo_egoera);
		
		JButton btn_print_2 = new JButton("Imprimatu");
		btn_print_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodoakTxt.diruSarrerakImprimatu(diruSarrerak);
				JOptionPane.showMessageDialog(null,"Diru sarrerak inprimatu dira!","Informazio", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btn_print_2.setBounds(410, 502, 107, 30);
		sarrerak.add(btn_print_2);
		
		JScrollPane sarrerak_panel = new JScrollPane();
		sarrerak_panel.setBounds(114, 242, 712, 232);
		sarrerak.add(sarrerak_panel);
		
		
		JLabel lbl_jasotzaileIban = new JLabel("IBAN Jasotzaile:");
		lbl_jasotzaileIban.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_jasotzaileIban.setBounds(290, 221, 112, 30);
		transferentziaEgin.add(lbl_jasotzaileIban);
		
		txt_jasotzaile = new JTextField();
		txt_jasotzaile.setBounds(390, 220, 202, 34);
		transferentziaEgin.add(txt_jasotzaile);
		txt_jasotzaile.setColumns(10);
		
		JLabel lbl_kant = new JLabel("Kantitate:");
		lbl_kant.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_kant.setBounds(290, 266, 112, 30);
		transferentziaEgin.add(lbl_kant);
		
		txt_kantitate = new JTextField();
		txt_kantitate.setColumns(10);
		txt_kantitate.setBounds(390, 262, 202, 34);
		transferentziaEgin.add(txt_kantitate);
		txt_kantitate.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if (!Character.isDigit(c) && c!=',') {
	                e.consume();
	            }
			}
		});
		
		JLabel lbl_kontzeptu = new JLabel("Kontzeptua:");
		lbl_kontzeptu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_kontzeptu.setBounds(290, 307, 112, 30);
		transferentziaEgin.add(lbl_kontzeptu);
		
		txt_kontzeptu = new JTextField();
		txt_kontzeptu.setColumns(10);
		txt_kontzeptu.setBounds(390, 303, 202, 34);
		transferentziaEgin.add(txt_kontzeptu);
		
		JLabel lbl_komisio = new JLabel("Komisioa: ");
		lbl_komisio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_komisio.setBounds(290, 352, 112, 30);
		transferentziaEgin.add(lbl_komisio);
		
		txt_komisio_transfer = new JTextField("1,5%");
		txt_komisio_transfer.setColumns(10);
		txt_komisio_transfer.setBounds(390, 348, 44, 34);
		txt_komisio_transfer.setEditable(false);
		transferentziaEgin.add(txt_komisio_transfer);
		
		JButton btn_transferitu = new JButton("Egin Transferentzia");
		btn_transferitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txt_jasotzaile.getText().equals("") && !txt_kantitate.getText().equals("") && !txt_kontzeptu.getText().equals("") && !String.valueOf(transferentzia_segurtasun.getPassword()).equals("")) {
					if(txt_jasotzaile.getText().length() == 24 && metodoakSelect.transferentziaIbanBalidatu(txt_jasotzaile.getText())) {
						if(!txt_jasotzaile.getText().equals(bezero_iban_kontua)) {
							if(metodoakSelect.transferentziaSaldoaBalidatu(txt_kantitate.getText(),txt_jasotzaile.getText(),nan_bezero,pass_bezero)) {
								if(metodoakSelect.segurtasunKodeaBalidatu(String.valueOf(transferentzia_segurtasun.getPassword()), bezero_iban_kontua,nan_bezero,pass_bezero)) {
									try {
										if(metodoakInsert.transferentziaEgin(bezero_iban_kontua, txt_jasotzaile.getText(), txt_kantitate.getText(), txt_kontzeptu.getText(), txt_komisio_transfer.getText(), String.valueOf(transferentzia_segurtasun.getPassword()), transferentziak)) {
											bezero = metodoakObjetu.diruaKendu(bezero, bezero_iban_kontua, txt_kantitate.getText().toString(), txt_komisio_transfer.getText().toString(), txt_jasotzaile.getText().toString());
											for(int i = 0; i < bezero.getTxartelak().size(); i ++) {
												if(bezero.getTxartelak().get(i).getKontuBankario().getIban().equals(bezero_iban_kontua)) {
													trans.setJasotzailea(txt_jasotzaile.getText());
													String kant_tans = txt_kantitate.getText().replace(",", ".");
													trans.setKantitatea(Double.parseDouble(kant_tans));
													trans.setTransferentziaData(data_sortu);
													trans.setKontzeptua(txt_kontzeptu.getText());
													bezero.getTxartelak().get(i).getKontuBankario().getTransferentziak().add(trans);
													
													saldo_bezero = bezero.getTxartelak().get(i).getKontuBankario().getSaldoa();
													lbl_saldo_bez_erakutsi.setText(saldo_bezero+" €");
												}
											}
											txt_jasotzaile.setText("");
											txt_kantitate.setText("");
											txt_kontzeptu.setText("");
											transferentzia_segurtasun.setText("");
											JOptionPane.showMessageDialog(null,"Transferentzia zuzen burutu da!","Informazio", JOptionPane.INFORMATION_MESSAGE);
											transferentziaEgin.setVisible(false);
											transferentzia.setVisible(true);
										}									
									} catch (SalbuespenaTransferentzia e1) {
										e1.printStackTrace();
										JOptionPane.showMessageDialog(null,"Jasotzailearen kontua ezin ditu transferentziak jaso!","Error", JOptionPane.ERROR_MESSAGE);
									}
								}else {
									JOptionPane.showMessageDialog(null, "Sartutako segurtasun kodea ez da zuzena.","Error!", JOptionPane.ERROR_MESSAGE);
								}
							}else {
								JOptionPane.showMessageDialog(null, "Sartutako diru kopurua kontuan dagoena baino handiagoa da.","Error!", JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "Ezin duzu zure buruari transakziorik egin.","Error!", JOptionPane.ERROR_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Sartutako IBAN-a ez da zuzena.","Error!", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Datuak ez dira zuzenak.","Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_transferitu.setBounds(393, 446, 153, 34);
		transferentziaEgin.add(btn_transferitu);
		
		JLabel lbl_segurtasun = new JLabel("Segurtasun Kodea:");
		lbl_segurtasun.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_segurtasun.setBounds(290, 397, 112, 30);
		transferentziaEgin.add(lbl_segurtasun);
		
		transferentzia_segurtasun = new JPasswordField();
		transferentzia_segurtasun.setBounds(412, 393, 112, 34);
		transferentziaEgin.add(transferentzia_segurtasun);
		transferentzia_segurtasun.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(!Character.isDigit(c) || String.valueOf(transferentzia_segurtasun.getPassword()).length() >= 4 ){
					e.consume();
				}
			}
		});
		
		JScrollPane scrollPane_transfer = new JScrollPane();
		scrollPane_transfer.setBounds(114, 242, 712, 232);
		transferentziaIkusi.add(scrollPane_transfer);		
		
		JButton btn_print_1 = new JButton("Imprimatu");
		btn_print_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodoakTxt.transferentziakImprimatu(transferentziak);
				JOptionPane.showMessageDialog(null,"Transferentziak inprimatu dira!","Informazio", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btn_print_1.setBounds(410, 502, 107, 30);
		transferentziaIkusi.add(btn_print_1);
		
		JButton btn_transfer_ikusi = new JButton("Transferentziak Ikusi");
		btn_transfer_ikusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transferentziak = metodoakTaulak.transferentziakIkusi(bezero, bezero_iban_kontua);			
				
				transfer_ikusi_table = new JTable(transferentziak, transferentziak_header) {
					private static final long serialVersionUID = 1L;

					public boolean editCellAt(int row, int column, java.util.EventObject e) {
			            return false;
			        }	
				};
				transfer_ikusi_table.getColumnModel().getColumn(0).setPreferredWidth(200); 
				transfer_ikusi_table.getTableHeader().setReorderingAllowed(false);
				scrollPane_transfer.setViewportView(transfer_ikusi_table);
				
				transferentziaIkusi.setVisible(true);
				transferentzia.setVisible(false);
			}
		});
		btn_transfer_ikusi.setBounds(272, 301, 157, 34);
		transferentzia.add(btn_transfer_ikusi);
		
		JButton btn_transfer_egin = new JButton("Transferentzia Egin");
		btn_transfer_egin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transferentzia.setVisible(false);
				transferentziaEgin.setVisible(true);
			}
		});
		btn_transfer_egin.setBounds(514, 301, 157, 34);
		transferentzia.add(btn_transfer_egin);
		
		JLabel lblNewLabel_1 = new JLabel("TRANSFERENTZIAK");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(383, 191, 173, 44);
		transferentzia.add(lblNewLabel_1);
		
		JButton btn_transferentzia = new JButton("Transferentzia");
		btn_transferentzia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(bezero_kontu_egoera.equals("aktiboa")) {
					btn_transfer_egin.setEnabled(true);
				}else {
					btn_transfer_egin.setEnabled(false);
				}
				bezeroKontua.setVisible(false);
				transferentzia.setVisible(true);
			}
		});
		btn_transferentzia.setBounds(263, 265, 128, 34);
		bezeroKontua.add(btn_transferentzia);
		
		JButton btn_sarrerak = new JButton("Diru Sarrerak");
		btn_sarrerak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diruSarrerak = metodoakTaulak.diruSarrerakIkusi(bezero, bezero_iban_kontua);
				
				sarrerak_table = new JTable(diruSarrerak, diruSarrerak_header) {
					private static final long serialVersionUID = 1L;

					public boolean editCellAt(int row, int column, java.util.EventObject e) {
			            return false;
			        }	
				};
				sarrerak_table.getColumnModel().getColumn(0).setPreferredWidth(200); 
				sarrerak_table.getTableHeader().setReorderingAllowed(false);
				sarrerak_panel.setViewportView(sarrerak_table);
				
				bezeroKontua.setVisible(false);
				sarrerak.setVisible(true);
			}
		});
		btn_sarrerak.setBounds(524, 265, 128, 34);
		bezeroKontua.add(btn_sarrerak);
		
		btn_hipoteka = new JButton("Hipoteka");
		btn_hipoteka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!metodoakString.hipotekaDut(bezero, bezero_iban_kontua).equals("")) {
					hipoteka_info = metodoakArray.hipotekaEstatus(bezero,bezero_iban_kontua);
					lbl_hipoteka_total.setText(hipoteka_info[0] + " €");
					lbl_hipoteka_hasiera.setText(hipoteka_info[1]);
					lbl_hipoteka_amaiera.setText(hipoteka_info[2]);
					lbl_hipoteka_komisio_ikusi.setText(hipoteka_info[3]+" %");
					lbl_hipoteka_ordainduta.setText(hipoteka_info[4] + " €");
					lbl_hipo_egoera.setText(hipoteka_info[5]);
					if(!metodoakString.hipotekaDut(bezero, bezero_iban_kontua).equals("onartuta")){
						btn_ordaindu.setVisible(false);
					}else {
						btn_ordaindu.setVisible(true);
					}
					bezeroKontua.setVisible(false);
					hipotekaEstatus.setVisible(true);
				}else {
					bezeroKontua.setVisible(false);
					hipotekaEskatu.setVisible(true);
				}
			}
		});
		btn_hipoteka.setBounds(263, 354, 128, 34);
		bezeroKontua.add(btn_hipoteka);
		
		JButton btn_mugimendu = new JButton("Mugimenduak");
		btn_mugimendu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transferentziak = metodoakTaulak.transferentziakIkusi(bezero, bezero_iban_kontua);
				
				transfer_table = new JTable(transferentziak, transferentziak_header) {
					private static final long serialVersionUID = 1L;

					public boolean editCellAt(int row, int column, java.util.EventObject e) {
			            return false;
			        }	
				};
				transfer_table.getColumnModel().getColumn(0).setPreferredWidth(200); 
				transfer_table.getTableHeader().setReorderingAllowed(false);
				transfer_pane.setViewportView(transfer_table);
				
				diruSarrerak = metodoakTaulak.diruSarrerakIkusi(bezero, bezero_iban_kontua);
				
				sarrera_table = new JTable(diruSarrerak, diruSarrerak_header) {
					private static final long serialVersionUID = 1L;

					public boolean editCellAt(int row, int column, java.util.EventObject e) {
			            return false;
			        }	
				};
				sarrera_table.getColumnModel().getColumn(0).setPreferredWidth(200); 
				sarrera_table.getTableHeader().setReorderingAllowed(false);
				sarrera_pane.setViewportView(sarrera_table);
				
				bezeroKontua.setVisible(false);
				mugimenduak.setVisible(true);
			}
		});
		btn_mugimendu.setBounds(524, 354, 128, 34);
		bezeroKontua.add(btn_mugimendu);
		
		btn_kontuItxi = new JButton("Kontua Itxi");
		btn_kontuItxi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] options = new String[] {"Eskudirua Jaso", "Txeke jaso", "Ez itxi"};
			    int erantzuna = JOptionPane.showOptionDialog(null,"Ziur zaude kontua itxi nahi duzula?","Berrespena",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,null, options, options[0]);
				if(erantzuna == 0) {
					bezero = metodoakObjetu.ixtekoKontuAldatu(bezero, bezero_iban_kontua);
					if(metodoakUpdate.ixtekoKontuUpdate(bezero_iban_kontua)) {
						JOptionPane.showMessageDialog(null, "Kontua ixteko eskatu da! Dirua prestatzen dago.","Informazio", JOptionPane.INFORMATION_MESSAGE);
					}
				}else if(erantzuna == 1) {
					bezero = metodoakObjetu.ixtekoKontuAldatu(bezero, bezero_iban_kontua);
					if(metodoakUpdate.ixtekoKontuUpdate(bezero_iban_kontua)) {
						JOptionPane.showMessageDialog(null, "Kontua ixteko eskatu da! Txekea egiten gaude.","Informazio", JOptionPane.INFORMATION_MESSAGE);
					}
				}else if(erantzuna == 2) {
					JOptionPane.showMessageDialog(null, "Kontua ez da itxi.","Informazio", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btn_kontuItxi.setBounds(409, 425, 115, 34);
		bezeroKontua.add(btn_kontuItxi);
		btn_kontuItxi.setBackground(Color.LIGHT_GRAY);
		
		JLabel lbl_iban_bez = new JLabel("IBAN:");
		lbl_iban_bez.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_iban_bez.setBounds(154, 175, 54, 30);
		bezeroKontua.add(lbl_iban_bez);
		
		lbl_iban_bez_erakutsi = new JLabel("ibana");
		lbl_iban_bez_erakutsi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_iban_bez_erakutsi.setBounds(205, 175, 231, 30);
		bezeroKontua.add(lbl_iban_bez_erakutsi);
		
		JLabel lbl_saldo_bez = new JLabel("Saldoa:");
		lbl_saldo_bez.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_saldo_bez.setBounds(524, 175, 73, 30);
		bezeroKontua.add(lbl_saldo_bez);
		
		lbl_saldo_bez_erakutsi = new JLabel("saldoa");
		lbl_saldo_bez_erakutsi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_saldo_bez_erakutsi.setBounds(624, 175, 214, 30);
		bezeroKontua.add(lbl_saldo_bez_erakutsi);
		
		scrollPane_entitateKont = new JScrollPane();
		scrollPane_entitateKont.setBounds(190, 212, 550, 210);
		entitateKontuak.add(scrollPane_entitateKont);
		
		table_entitateKont = new JTable();
		scrollPane_entitateKont.setViewportView(table_entitateKont);
		
		JLabel lbl_langile_login = new JLabel("LOGIN LANGILE");
		lbl_langile_login.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_langile_login.setBounds(407, 206, 140, 45);
		loginLangile.add(lbl_langile_login);
		
		JLabel lbl_langile = new JLabel("Erabiltzailea:");
		lbl_langile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_langile.setBounds(315, 281, 82, 30);
		loginLangile.add(lbl_langile);
		
		txt_langile_erabiltzaile = new JTextField();
		txt_langile_erabiltzaile.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {				
				if(txt_langile_erabiltzaile.getText().length() >= 9){ 
					e.consume();
				}
			}
		});
		txt_langile_erabiltzaile.setColumns(10);
		txt_langile_erabiltzaile.setBounds(407, 281, 171, 30);
		loginLangile.add(txt_langile_erabiltzaile);
		
		JLabel lbl_langile_pass = new JLabel("Pasahitza:");
		lbl_langile_pass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_langile_pass.setBounds(315, 345, 82, 30);
		loginLangile.add(lbl_langile_pass);
		
		passLangile = new JPasswordField();
		passLangile.setBounds(407, 345, 171, 30);
		loginLangile.add(passLangile);
		
		txt_hipoteka_komisio = new JTextField("");
		txt_hipoteka_komisio.setEditable(false);
		txt_hipoteka_komisio.setColumns(10);
		txt_hipoteka_komisio.setBounds(442, 335, 57, 34);
		hipotekaEskatu.add(txt_hipoteka_komisio);
		
		hipoteka_combo = new JComboBox<String>(epemugak_array);
		hipoteka_combo.setSelectedIndex(0);
		txt_hipoteka_komisio.setText("3 %");
		hipoteka_combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hipoteka_combo.getSelectedIndex() == 0) {
					txt_hipoteka_komisio.setText("3%");
				}else if(hipoteka_combo.getSelectedIndex() == 1) {
					txt_hipoteka_komisio.setText("3,5%");
				}else if(hipoteka_combo.getSelectedIndex() == 2) {
					txt_hipoteka_komisio.setText("3,8%");
				}else if(hipoteka_combo.getSelectedIndex() == 3) {
					txt_hipoteka_komisio.setText("4%");
				}
			}
		});
		hipoteka_combo.setBounds(442, 290, 94, 34);
		hipotekaEskatu.add(hipoteka_combo);
				
		JButton btn_eskatu = new JButton("Eskatu");
		btn_eskatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txt_kantitate_hipoteka.getText().equals("") && String.valueOf(hipoteka_segurtasun.getPassword()).length()==4) {
					if(metodoakSelect.segurtasunKodeaBalidatu(String.valueOf(hipoteka_segurtasun.getPassword()), bezero_iban_kontua,nan_bezero,pass_bezero)) {
						if(txt_kantitate_hipoteka.getText().split(",").length<=2) {
							int erantzuna = JOptionPane.showOptionDialog(null, "Ziur zaude "+txt_kantitate_hipoteka.getText()+" € ko hipoteka eskatu nahi duzula "+txt_hipoteka_komisio.getText()+" ko komisioarekin?","Berrespena", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, aukerak,aukerak[1]);
							if(erantzuna == JOptionPane.YES_OPTION) {
								bezero = metodoakObjetu.hipotekaSortu(bezero, bezero_iban_kontua, txt_kantitate_hipoteka.getText(), txt_hipoteka_komisio.getText(), String.valueOf(hipoteka_combo.getSelectedItem()));
								metodoakInsert.hipotekaEskatu(txt_kantitate_hipoteka.getText(), txt_hipoteka_komisio.getText(), bezero_iban_kontua, String.valueOf(hipoteka_combo.getSelectedItem()),nan_bezero,pass_bezero);
								JOptionPane.showMessageDialog(null, "Hipoteka eskatu da!.","Informazio", JOptionPane.INFORMATION_MESSAGE);
								txt_kantitate_hipoteka.setText("");
								hipoteka_combo.setSelectedIndex(0);
								txt_hipoteka_komisio.setText("3%");
								hipoteka_segurtasun.setText("");
								bezeroKontua.setVisible(true);
								hipotekaEskatu.setVisible(false);
							}
						}else {
							JOptionPane.showMessageDialog(null, "Sartutako kantitatea ez da zuzena.","Error!", JOptionPane.ERROR_MESSAGE);							
						}
					}else {
						JOptionPane.showMessageDialog(null, "Sartutako segurtasun kodea ez da zuzena.","Error!", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Datuak ez dira zuzenak.","Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_eskatu.setBounds(431, 435, 107, 30);
		hipotekaEskatu.add(btn_eskatu);
		
		JLabel lbl_kantitate_hipoteka = new JLabel("Kantitatea:");
		lbl_kantitate_hipoteka.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_kantitate_hipoteka.setBounds(320, 246, 112, 30);
		hipotekaEskatu.add(lbl_kantitate_hipoteka);
		
		txt_kantitate_hipoteka = new JTextField();
		txt_kantitate_hipoteka.setColumns(10);
		txt_kantitate_hipoteka.setBounds(402, 245, 202, 34);
		txt_kantitate_hipoteka.addKeyListener(new KeyAdapter() {
			@Override
			 public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	            if (!Character.isDigit(c) && c!=',') {
	                e.consume();
	            }
	        }
		});
		hipotekaEskatu.add(txt_kantitate_hipoteka);
		
		JLabel lbl_urteak = new JLabel("Amortizazio Epea:");
		lbl_urteak.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_urteak.setBounds(320, 291, 112, 30);
		hipotekaEskatu.add(lbl_urteak);
		
		JLabel lbl_komisio_1 = new JLabel("Komisioa: ");
		lbl_komisio_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_komisio_1.setBounds(320, 336, 112, 30);
		hipotekaEskatu.add(lbl_komisio_1);
		
		JLabel lbl_segurtasun_2 = new JLabel("Segurtasun Kodea:");
		lbl_segurtasun_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_segurtasun_2.setBounds(320, 381, 112, 30);
		hipotekaEskatu.add(lbl_segurtasun_2);
		
		hipoteka_segurtasun = new JPasswordField();
		hipoteka_segurtasun.setBounds(442, 377, 112, 34);
		hipoteka_segurtasun.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(!Character.isDigit(c) || String.valueOf(hipoteka_segurtasun.getPassword()).length() >= 4 ){ 
					e.consume();
				}
			}
		});
		hipotekaEskatu.add(hipoteka_segurtasun);	
		
		JLabel lblNewLabel_2_1 = new JLabel("HIPOTEKA ESKATU");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(398, 173, 153, 48);
		hipotekaEskatu.add(lblNewLabel_2_1);

		JLabel lbl_kantitate_hipoteka_1 = new JLabel("Kantitatea:");
		lbl_kantitate_hipoteka_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_kantitate_hipoteka_1.setBounds(297, 296, 112, 30);
		hipotekaOrdaindu.add(lbl_kantitate_hipoteka_1);
		
		txt_kant_ordaindu = new JTextField();
		txt_kant_ordaindu.setColumns(10);
		txt_kant_ordaindu.setBounds(379, 295, 202, 34);
		hipotekaOrdaindu.add(txt_kant_ordaindu);
		
		JLabel lbl_segurtasun_2_1 = new JLabel("Segurtasun Kodea:");
		lbl_segurtasun_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_segurtasun_2_1.setBounds(297, 344, 112, 30);
		hipotekaOrdaindu.add(lbl_segurtasun_2_1);
		
		segurtasun_ordaindu = new JPasswordField();
		segurtasun_ordaindu.setBounds(434, 340, 112, 34);
		hipotekaOrdaindu.add(segurtasun_ordaindu);
		
		JButton btn_ordaindu_hipo = new JButton("Ordaindu");
		btn_ordaindu_hipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_kant_ordaindu.getText().split(",").length<=2) {
					try {
						bezero = metodoakObjetu.hipotekaOrdaindu(bezero, bezero_iban_kontua, txt_kant_ordaindu.getText());
						metodoakUpdate.hipotekaUpdate(bezero, bezero_iban_kontua,pass_bezero);
						hipoteka_info = metodoakArray.hipotekaEstatus(bezero,bezero_iban_kontua);
						lbl_hipoteka_total.setText(hipoteka_info[0] + " €");
						lbl_hipoteka_hasiera.setText(hipoteka_info[1]);
						lbl_hipoteka_amaiera.setText(hipoteka_info[2]);
						lbl_hipoteka_komisio_ikusi.setText(hipoteka_info[3]+" %");
						lbl_hipoteka_ordainduta.setText(hipoteka_info[4] + " €");
						lbl_hipo_egoera.setText(hipoteka_info[5]);
						for(int i=0;i<bezero.getTxartelak().size();i++){
							if(bezero.getTxartelak().get(i).getKontuBankario().getIban().equals(bezero_iban_kontua)){
								saldo_bezero = bezero.getTxartelak().get(i).getKontuBankario().getSaldoa();
							}
						}
						lbl_saldo_bez_erakutsi.setText(saldo_bezero+" €");
						txt_kant_ordaindu.setText("");
						segurtasun_ordaindu.setText("");
						hipotekaOrdaindu.setVisible(false);
						hipotekaEstatus.setVisible(true);
					} catch (SalbuespenaOrdainketa s) {
						JOptionPane.showMessageDialog(null,"Ordainketa okerra!","Error", JOptionPane.ERROR_MESSAGE);
						s.printStackTrace();
					}
				}else {
					JOptionPane.showMessageDialog(null,"Diru kantitatea okerra da!","Error", JOptionPane.ERROR_MESSAGE);					
				}				
			}
		});
		btn_ordaindu_hipo.setBounds(392, 427, 153, 34);
		hipotekaOrdaindu.add(btn_ordaindu_hipo);
		
		JLabel lbl_hip_ordaindu = new JLabel("HIPOTEKA ORDAINDU");
		lbl_hip_ordaindu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_hip_ordaindu.setBounds(380, 184, 172, 48);
		hipotekaOrdaindu.add(lbl_hip_ordaindu);
		
		
		transfer_pane = new JScrollPane();
		transfer_pane.setBounds(109, 192, 757, 109);
		mugimenduak.add(transfer_pane);
		
		sarrera_pane = new JScrollPane();
		sarrera_pane.setBounds(109, 373, 757, 109);
		mugimenduak.add(sarrera_pane);
		
		sarrera_table = new JTable();
		sarrera_pane.setViewportView(sarrera_table);
		
		JButton btn_imprimatu_mugi = new JButton("Mugimenduak Imprimatu");
		btn_imprimatu_mugi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				metodoakTxt.mugimentuakImprimatu(diruSarrerak, transferentziak);
				JOptionPane.showMessageDialog(null,"Mugimenduak guztiak inprimatu dira!","Informazio", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btn_imprimatu_mugi.setBounds(378, 493, 180, 34);
		mugimenduak.add(btn_imprimatu_mugi);
		
		JLabel lblNewLabel_2_2 = new JLabel("DIRU SARRERAK");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_2.setBounds(405, 312, 132, 48);
		mugimenduak.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("TRANSFERENTZIAK");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_3.setBounds(389, 137, 153, 48);
		mugimenduak.add(lblNewLabel_2_3);
		
		JButton btn_kontu_ikusi = new JButton("Kontuak Ikusi");
		btn_kontu_ikusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				sukurtsal_kontuak=metodoakTaulak.langilearenSukurtsalarenKontuak(langile, sukurtsal_izen);

				kontuak_table = new JTable(sukurtsal_kontuak,kontuak_lista){
					private static final long serialVersionUID = 1L;
			        public boolean isCellEditable(int row, int column) {                
			                return false;               
			        };
			    };
				kontuak_pane.setViewportView(kontuak_table);
				
				langileMenu.setVisible(false);
				kontuakLista.setVisible(true);
			}
		});
		btn_kontu_ikusi.setBounds(263, 262, 153, 34);
		langileMenu.add(btn_kontu_ikusi);
		
		JButton btn_kontu_sortu = new JButton("Kontua Sortu");
		btn_kontu_sortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do {
					id_txartel_random = StringMetodoak.sortuTxartelId();
					txartel_id = id_txartel_random.substring(0,4)+" "+id_txartel_random.substring(4,8)+" "+id_txartel_random.substring(8,12)+" "+id_txartel_random.substring(12,16);
					lbl_txartelid.setText(txartel_id);
				}while(!metodoakSelect.txartelIdBalidatu(id_txartel_random));
				langileMenu.setVisible(false);
				kontuSortu.setVisible(true);
			}
		});
		btn_kontu_sortu.setBounds(516, 355, 153, 34);
		langileMenu.add(btn_kontu_sortu);
		
		JButton btn_hipoteka_gerente = new JButton("Hipotekak");
		btn_hipoteka_gerente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				langileMenu.setVisible(false);
				hipotekak.setVisible(true);
			}
		});
		btn_hipoteka_gerente.setBounds(263, 355, 153, 34);
		langileMenu.add(btn_hipoteka_gerente);
		
		JButton btn_erabiltzaile_sortu = new JButton("Erabiltzaile Sortu");
		btn_erabiltzaile_sortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cb_eguna = new JComboBox<String>(egunak_array);
				cb_eguna.setBounds(645, 362, 82, 30);
				erabiltzaileSortu.add(cb_eguna);

				cb_hila = new JComboBox<String>(hilak_array);
				cb_hila.addActionListener (new ActionListener () {
					public void actionPerformed(ActionEvent e) {
						egunak_array= ArrayMetodoak.egunakBete(Integer.parseInt(cb_hila.getSelectedItem().toString()));
						cb_eguna.removeAllItems();
						DefaultComboBoxModel<String> egunak_model = new DefaultComboBoxModel<String>(egunak_array);
						cb_eguna.setModel(egunak_model);
					}
				});
				cb_hila.setBounds(535, 362, 82, 30);
				erabiltzaileSortu.add(cb_hila);
				
				langileMenu.setVisible(false);
				erabiltzaileSortu.setVisible(true);
			}
		});
		btn_erabiltzaile_sortu.setBounds(516, 262, 153, 34);
		langileMenu.add(btn_erabiltzaile_sortu);
		
		btn_kontu_itzi = new JButton("Ixteko Kontuak");
		btn_kontu_itzi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] ixteko_array = metodoakTaulak.ixtekoKontuak(langile, sukurtsal_izen);
				itxi_table = new JTable(ixteko_array,kontuak_lista) {
					private static final long serialVersionUID = 1L;
			        public boolean isCellEditable(int row, int column) {                
			                return false;               
			        };
			    };
				itxi_pane.setViewportView(itxi_table);
				itxi_table.getColumnModel().getColumn(0).setPreferredWidth(200);
				itxi_table.getTableHeader().setReorderingAllowed(false);
				
				langileMenu.setVisible(false);
				ixtekoKontuak.setVisible(true);
			}
		});
		btn_kontu_itzi.setBounds(394, 429, 153, 34);
		langileMenu.add(btn_kontu_itzi);
				
		kontuak_pane = new JScrollPane();
		kontuak_pane.setBounds(158, 246, 610, 173);
		kontuakLista.add(kontuak_pane);
		
		
		JLabel lblNewLabel_2_3_1 = new JLabel("KONTU ZERRENDA");
		lblNewLabel_2_3_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_3_1.setBounds(392, 180, 147, 48);
		kontuakLista.add(lblNewLabel_2_3_1);
				
		JLabel lbl_iban_kontu = new JLabel("IBAN:");
		lbl_iban_kontu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_iban_kontu.setBounds(361, 235, 44, 30);
		infoKontua.add(lbl_iban_kontu);
		
		JLabel lbl_jabe = new JLabel("Jabea(k):");
		lbl_jabe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_jabe.setBounds(361, 280, 64, 30);
		infoKontua.add(lbl_jabe);
		
		JLabel lbl_saldo_kontu = new JLabel("Saldoa:");
		lbl_saldo_kontu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_saldo_kontu.setBounds(361, 321, 64, 30);
		infoKontua.add(lbl_saldo_kontu);
		
		lbl_iban_kontua = new JLabel("ibana");
		lbl_iban_kontua.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_iban_kontua.setBounds(415, 235, 204, 30);
		infoKontua.add(lbl_iban_kontua);
		
		lbl_jabeak_kontua = new JLabel("jabe");
		lbl_jabeak_kontua.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_jabeak_kontua.setBounds(425, 280, 370, 30);
		infoKontua.add(lbl_jabeak_kontua);
		
		lbl_saldo_kontua = new JLabel("saldo");
		lbl_saldo_kontua.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_saldo_kontua.setBounds(435, 321, 167, 30);
		infoKontua.add(lbl_saldo_kontua);
		
		JLabel lblNewLabel_2_3_1_1 = new JLabel("KONTU INFORMAZIOA");
		lblNewLabel_2_3_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_3_1_1.setBounds(390, 167, 180, 48);
		infoKontua.add(lblNewLabel_2_3_1_1);
		
		JLabel lbl_egoera_kontu = new JLabel("Egoera:");
		lbl_egoera_kontu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_egoera_kontu.setBounds(361, 362, 64, 30);
		infoKontua.add(lbl_egoera_kontu);
				
		JLabel lbl_egoera_kontu_1 = new JLabel("Hileko Limitea:");
		lbl_egoera_kontu_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_egoera_kontu_1.setBounds(361, 403, 92, 30);
		infoKontua.add(lbl_egoera_kontu_1);
		
		txt_limite_kontua = new JTextField();
		txt_limite_kontua.setColumns(10);
		txt_limite_kontua.setBounds(461, 403, 141, 34);
		infoKontua.add(txt_limite_kontua);
		
		JButton btn_gorde_aldaketak = new JButton("Gorde Aldaketak");
		btn_gorde_aldaketak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(metodoakUpdate.langileKontuAldaketak(cb_egoera_kontua.getSelectedItem().toString(), txt_limite_kontua.getText(), langile_aukeratu_iban,nan_langile,pass_langile)) {
					JOptionPane.showMessageDialog(null, "Aldaketak gorde dira.","Informazio", JOptionPane.INFORMATION_MESSAGE);		
					infoKontua.setVisible(false);
					langileMenu.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Errorea aldaketak gordetzean.","Error", JOptionPane.ERROR_MESSAGE);		
				}
			}
		});
		btn_gorde_aldaketak.setBounds(392, 470, 153, 34);
		infoKontua.add(btn_gorde_aldaketak);
		
		JButton btn_transferentziak_ikusi = new JButton("Transferentziak Ikusi");
		btn_transferentziak_ikusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transferentzia_info= metodoakTaulak.langileKontuTransfer(langile, langile_aukeratu_iban);
				
				transfer_ikusi_table = new JTable(transferentzia_info,transfer_header){
					private static final long serialVersionUID = 1L;
			        public boolean isCellEditable(int row, int column) {                
			                return false;               
			        };
			    };
			    scrollPane_transfer.setViewportView(transfer_ikusi_table);
				transfer_ikusi_table.getColumnModel().getColumn(2).setPreferredWidth(200);
				transfer_ikusi_table.getTableHeader().setReorderingAllowed(false);
				
				infoKontua.setVisible(false);
				transferentziaIkusi.setVisible(true);
			}
		});
		btn_transferentziak_ikusi.setBounds(731, 424, 180, 34);
		infoKontua.add(btn_transferentziak_ikusi);
		
		JButton btn_dirusarrerak_ikusi = new JButton("Diru Sarrerak Ikusi");
		btn_dirusarrerak_ikusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sarrera_info= metodoakTaulak.langileKontuSarrerak(langile, langile_aukeratu_iban);

				sarrerak_table = new JTable(sarrera_info,sarrera_header){
					private static final long serialVersionUID = 1L;
			        public boolean isCellEditable(int row, int column) {                
			                return false;               
			        };
			    };
				sarrerak_panel.setViewportView(sarrerak_table);
				sarrerak_table.getColumnModel().getColumn(2).setPreferredWidth(200);
				hipotekak_table.getTableHeader().setReorderingAllowed(false);
				
				infoKontua.setVisible(false);
				sarrerak.setVisible(true);
			}
		});
		btn_dirusarrerak_ikusi.setBounds(731, 470, 180, 34);
		infoKontua.add(btn_dirusarrerak_ikusi);
		
		JLabel lbl_bezero_sortu = new JLabel("BEZERO SORTU");
		lbl_bezero_sortu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_bezero_sortu.setBounds(426, 141, 140, 45);
		erabiltzaileSortu.add(lbl_bezero_sortu);
		
		JLabel lbl_izena_bez = new JLabel("Izena:");
		lbl_izena_bez.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_izena_bez.setBounds(333, 197, 82, 30);
		erabiltzaileSortu.add(lbl_izena_bez);
		
		txt_izen_erabiltzaile = new JTextField();
		txt_izen_erabiltzaile.setColumns(10);
		txt_izen_erabiltzaile.setBounds(425, 197, 171, 30);
		erabiltzaileSortu.add(txt_izen_erabiltzaile);
		
		JLabel lbl_erabiltzaile_pass_1 = new JLabel("Pasahitza:");
		lbl_erabiltzaile_pass_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_erabiltzaile_pass_1.setBounds(333, 321, 82, 30);
		erabiltzaileSortu.add(lbl_erabiltzaile_pass_1);
		
		pass_bezero_sortu = new JPasswordField();
		pass_bezero_sortu.setBounds(425, 321, 171, 30);
		erabiltzaileSortu.add(pass_bezero_sortu);
		
		JLabel lbl_sexua = new JLabel("Sexua:");
		lbl_sexua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_sexua.setBounds(333, 403, 82, 30);
		erabiltzaileSortu.add(lbl_sexua);
		
		cb_sexua = new JComboBox<String>(sexu_array);
		cb_sexua.setBounds(425, 403, 129, 30);
		erabiltzaileSortu.add(cb_sexua);
		
		JLabel lbl_nan_sortu = new JLabel("NAN:");
		lbl_nan_sortu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_nan_sortu.setBounds(333, 280, 82, 30);
		erabiltzaileSortu.add(lbl_nan_sortu);
		
		txt_nan_sortu = new JTextField();
		txt_nan_sortu.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (txt_nan_sortu.getText().length() >= 9) {
	                e.consume();
	            }
	        }
	    });
		txt_nan_sortu.setColumns(10);
		txt_nan_sortu.setBounds(425, 280, 171, 30);
		erabiltzaileSortu.add(txt_nan_sortu);
		
		JLabel lbl_tel_sortu = new JLabel("Telefonoa:");
		lbl_tel_sortu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_tel_sortu.setBounds(333, 444, 82, 30);
		erabiltzaileSortu.add(lbl_tel_sortu);
		
		txt_tel_sortu = new JTextField();
		txt_tel_sortu.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	            if (txt_tel_sortu.getText().length() >= 9 || !Character.isDigit(c)) {
	                e.consume();
	            }
	        }
	    });
		txt_tel_sortu.setColumns(10);
		txt_tel_sortu.setBounds(425, 444, 171, 30);
		erabiltzaileSortu.add(txt_tel_sortu);
		
		JLabel lbl_jaiotze = new JLabel("Jaiotze Data:");
		lbl_jaiotze.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_jaiotze.setBounds(333, 362, 82, 30);
		erabiltzaileSortu.add(lbl_jaiotze);
		
		cb_urtea = new JComboBox<String>(urteak_array);
		cb_urtea.setBounds(425, 362, 82, 30);
		erabiltzaileSortu.add(cb_urtea);
				
		
		JLabel lbl_erabiltzaile_barra1 = new JLabel("/");
		lbl_erabiltzaile_barra1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_erabiltzaile_barra1.setBounds(517, 362, 21, 30);
		erabiltzaileSortu.add(lbl_erabiltzaile_barra1);
		
		JLabel lbl_erabiltzaile_barra2 = new JLabel("/");
		lbl_erabiltzaile_barra2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_erabiltzaile_barra2.setBounds(627, 362, 21, 30);
		erabiltzaileSortu.add(lbl_erabiltzaile_barra2);
		
		JButton btn_sortu_kontu = new JButton("Sortu Kontua");
		btn_sortu_kontu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!txt_izen_erabiltzaile.getText().equals("") && !txt_abizen_erabiltzaile.getText().equals("") && txt_nan_sortu.getText().length()==9 && !String.valueOf(pass_bezero_sortu.getPassword()).equals("") && txt_tel_sortu.getText().length()==9) {
						if(metodoakBoolean.nanBalidatu(txt_nan_sortu.getText())  && !metodoakSelect.bezeroExistitu(txt_nan_sortu.getText()) && metodoakInsert.bezeroSortu(txt_izen_erabiltzaile.getText(),txt_abizen_erabiltzaile.getText(),txt_nan_sortu.getText(),String.valueOf(pass_bezero_sortu.getPassword()),cb_urtea.getSelectedItem().toString(),cb_hila.getSelectedItem().toString(),cb_eguna.getSelectedItem().toString(),cb_sexua.getSelectedItem().toString(),txt_tel_sortu.getText(),nan_langile,pass_langile)) {
							if(metodoakInsert.bezeroErabiltzaieSortu(txt_nan_sortu.getText(), String.valueOf(pass_bezero_sortu.getPassword()))) {
								JOptionPane.showMessageDialog(null, "Erabiltzailea sortu da.","Informazio", JOptionPane.INFORMATION_MESSAGE);
								txt_izen_erabiltzaile.setText("");
								txt_abizen_erabiltzaile.setText("");
								txt_nan_sortu.setText("");
								pass_bezero_sortu.setText("");
								cb_urtea.setSelectedIndex(0);
								cb_hila.setSelectedIndex(0);
								cb_eguna.setSelectedIndex(0);
								cb_sexua.setSelectedIndex(0);
								txt_tel_sortu.setText("");
								erabiltzaileSortu.setVisible(false);
								langileMenu.setVisible(true);
							}
						}
					}else {
						JOptionPane.showMessageDialog(null, "Datuak okerrak dira.","Error", JOptionPane.ERROR_MESSAGE);						
					}
				} catch (SQLException s) {
					JOptionPane.showMessageDialog(null, "Erabiltzailea ezin izan da sortu.","Error", JOptionPane.ERROR_MESSAGE);
				}				
			}
		});
		btn_sortu_kontu.setBounds(395, 485, 153, 34);
		erabiltzaileSortu.add(btn_sortu_kontu);
		
		txt_abizen_erabiltzaile = new JTextField();
		txt_abizen_erabiltzaile.setColumns(10);
		txt_abizen_erabiltzaile.setBounds(425, 239, 171, 30);
		erabiltzaileSortu.add(txt_abizen_erabiltzaile);
		
		JLabel lbl_abizena_bez = new JLabel("Abizena:");
		lbl_abizena_bez.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_abizena_bez.setBounds(333, 239, 82, 30);
		erabiltzaileSortu.add(lbl_abizena_bez);
		
		JLabel lbl_kontusortu = new JLabel("ERABILTZAILEA GEHITU");
		lbl_kontusortu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_kontusortu.setBounds(360, 181, 217, 45);
		kontuSortu.add(lbl_kontusortu);
		
		JLabel lbl_nan_kontua = new JLabel("NAN:");
		lbl_nan_kontua.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_nan_kontua.setBounds(337, 246, 82, 30);
		kontuSortu.add(lbl_nan_kontua);
		
		txt_kontu_sortu_nan = new JTextField();
		txt_kontu_sortu_nan.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (txt_kontu_sortu_nan.getText().length() >= 9) {
	                e.consume();
	            }
	        }
	    });
		txt_kontu_sortu_nan.setColumns(10);
		txt_kontu_sortu_nan.setBounds(385, 246, 124, 30);
		kontuSortu.add(txt_kontu_sortu_nan);
		
		JLabel lbl_erabiltzaile_segur = new JLabel("Segurtasun Kodea:");
		lbl_erabiltzaile_segur.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_erabiltzaile_segur.setBounds(337, 329, 124, 30);
		kontuSortu.add(lbl_erabiltzaile_segur);
		
		pass_segurtasunkode_kontu = new JPasswordField();
		pass_segurtasunkode_kontu.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	            if (pass_segurtasunkode_kontu.getPassword().length >= 4 || !Character.isDigit(c)) {
	                e.consume();
	            }
	        }
	    });
		pass_segurtasunkode_kontu.setBounds(471, 331, 114, 30);
		kontuSortu.add(pass_segurtasunkode_kontu);
		
		JLabel lbl_txartel_mota = new JLabel("Txartel Mota:");
		lbl_txartel_mota.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_txartel_mota.setBounds(337, 370, 124, 30);
		kontuSortu.add(lbl_txartel_mota);
		
		cb_txartel_mota = new JComboBox<String>(txartel_mota_array);
		cb_txartel_mota.setBounds(456, 370, 129, 30);
		kontuSortu.add(cb_txartel_mota);
		
		JLabel lbl_txartel_id = new JLabel("Txartel Zenbakia:");
		lbl_txartel_id.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_txartel_id.setBounds(337, 287, 114, 30);
		kontuSortu.add(lbl_txartel_id);
		
		lbl_txartelid = new JLabel("0000 0000 0000 0000");
		lbl_txartelid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_txartelid.setBounds(461, 288, 156, 30);
		kontuSortu.add(lbl_txartelid);
		
		JButton btn_jarraitu = new JButton("Jarraitu");
		btn_jarraitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nan_kontu_sortu =  txt_kontu_sortu_nan.getText();
				if(metodoakBoolean.nanBalidatu(nan_kontu_sortu) && String.valueOf(pass_segurtasunkode_kontu.getPassword()).length()==4){
					if(metodoakSelect.bezeroExistitu(nan_kontu_sortu)) {	
						boolean errepikatuta = false;
						for(int i=0;i<erabiltzaileak.length && !errepikatuta;i++) {
							if(erabiltzaileak[i][0].equalsIgnoreCase(nan_kontu_sortu)) {
								errepikatuta=true;
							}
						}
						if(!errepikatuta) {
							erabiltzaileak = TaulaMetodoak.bezeroGordeArray(nan_kontu_sortu, id_txartel_random, String.valueOf(pass_segurtasunkode_kontu.getPassword()),cb_txartel_mota.getSelectedItem().toString() ,erabiltzaileak);
							do {					
								iban_sortu= StringMetodoak.ibanKalkulatu(langile, sukurtsal_izen);
							}while(!metodoakSelect.ibanBalidatu(iban_sortu));
							lbl_kontusortu_iban.setText(iban_sortu.substring(0,4)+" "+iban_sortu.substring(4,8)+" "+iban_sortu.substring(8,12)+" "+iban_sortu.substring(12,14)+" "+iban_sortu.substring(14));				
							txt_kontu_sortu_nan.setText("");
							pass_segurtasunkode_kontu.setText("");
							cb_txartel_mota.setSelectedIndex(0);
							kontuSortu.setVisible(false);
							kontuBankarioaSortu.setVisible(true);
						}else if(errepikatuta) {
							JOptionPane.showMessageDialog(null,"Ezin duzu bezero berdina sartu!","Error!", JOptionPane.ERROR_MESSAGE);						
						}
					}else {
						JOptionPane.showMessageDialog(null,"Bezeroa ez da existitzen!","Error!", JOptionPane.ERROR_MESSAGE);					
					}
				}else {			
					JOptionPane.showMessageDialog(null,"Sartutako datuak ez dira balidoak!","Error!", JOptionPane.ERROR_MESSAGE);		
				}
			}
		});
		btn_jarraitu.setBounds(423, 461, 82, 34);
		kontuSortu.add(btn_jarraitu);
		
		JButton btn_gehitu_erabiltzaile = new JButton("Gehitu Beste Erabiltzaile Bat");
		btn_gehitu_erabiltzaile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nan_kontu_sortu =  txt_kontu_sortu_nan.getText();
				if(metodoakBoolean.nanBalidatu(nan_kontu_sortu) && String.valueOf(pass_segurtasunkode_kontu.getPassword()).length()==4) { 
					if(metodoakSelect.bezeroExistitu(nan_kontu_sortu)) {
						erabiltzaileak = TaulaMetodoak.bezeroGordeArray(nan_kontu_sortu, id_txartel_random,String.valueOf(pass_segurtasunkode_kontu.getPassword()),cb_txartel_mota.getSelectedItem().toString(), erabiltzaileak);
						txt_kontu_sortu_nan.setText("");
						//Txartel id berri bat sortzen du
						do {
							id_txartel_random = StringMetodoak.sortuTxartelId();
						}while(!metodoakSelect.txartelIdBalidatu(id_txartel_random));	
						txartel_id = id_txartel_random.substring(0,4)+" "+id_txartel_random.substring(4,8)+" "+id_txartel_random.substring(8,12)+" "+id_txartel_random.substring(12,16);
						lbl_txartelid.setText(txartel_id);
						
						pass_segurtasunkode_kontu.setText("");
					}else {
						JOptionPane.showMessageDialog(null,"Bezeroa ez da existitzen!","Error!", JOptionPane.ERROR_MESSAGE);					
					}
				}else {		
					JOptionPane.showMessageDialog(null,"Sartutako datuak ez dira balidoak!","Error!", JOptionPane.ERROR_MESSAGE);			
				}
			}
		});
		btn_gehitu_erabiltzaile.setBounds(360, 416, 210, 34);
		kontuSortu.add(btn_gehitu_erabiltzaile);		
				
		JLabel lbl_kontusortu_1 = new JLabel("KONTU KORRONTEA  SORTU");
		lbl_kontusortu_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_kontusortu_1.setBounds(346, 179, 249, 45);
		kontuBankarioaSortu.add(lbl_kontusortu_1);
		
		JLabel lbl_saldo_kontusortu = new JLabel("Saldoa:");
		lbl_saldo_kontusortu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_saldo_kontusortu.setBounds(335, 283, 62, 30);
		kontuBankarioaSortu.add(lbl_saldo_kontusortu);
		
		txt_saldo_sortu = new JTextField();
		txt_saldo_sortu.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	            if (!Character.isDigit(c) && c!=',') {
	                e.consume();
	            }
	        }
	    });
		txt_saldo_sortu.setColumns(10);
		txt_saldo_sortu.setBounds(393, 283, 145, 30);
		kontuBankarioaSortu.add(txt_saldo_sortu);
		
		JLabel lbl_limite_sortu = new JLabel("Hileko Limite:");
		lbl_limite_sortu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_limite_sortu.setBounds(335, 324, 89, 30);
		kontuBankarioaSortu.add(lbl_limite_sortu);
		
		JLabel lbl_iban_kontusortu = new JLabel("IBAN:");
		lbl_iban_kontusortu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_iban_kontusortu.setBounds(335, 241, 51, 30);
		kontuBankarioaSortu.add(lbl_iban_kontusortu);
		
		lbl_kontusortu_iban = new JLabel("ES00 0000 0000 00 0000000000");
		lbl_kontusortu_iban.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_kontusortu_iban.setBounds(383, 242, 232, 30);
		kontuBankarioaSortu.add(lbl_kontusortu_iban);
		
		txt_limite_sortu = new JTextField();
		txt_limite_sortu.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	            if(!Character.isDigit(c) && c!=',') {
	                e.consume();
	            }
	        }
	    });
		txt_limite_sortu.setColumns(10);
		txt_limite_sortu.setBounds(420, 324, 145, 30);
		kontuBankarioaSortu.add(txt_limite_sortu);
		
		JLabel lbl_egoera_sortu = new JLabel("Egoera:");
		lbl_egoera_sortu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_egoera_sortu.setBounds(335, 365, 72, 30);
		kontuBankarioaSortu.add(lbl_egoera_sortu);
		
		cb_egoera_sortu = new JComboBox<String>(kontu_egoera);
		cb_egoera_sortu.setBounds(407, 365, 131, 30);
		kontuBankarioaSortu.add(cb_egoera_sortu);
		
		JButton btn_sortu_kontu_korronte = new JButton("Sortu Kontua");
		btn_sortu_kontu_korronte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(BooleanMetodoak.diruBalidatu(txt_saldo_sortu.getText()) && BooleanMetodoak.diruBalidatu(txt_limite_sortu.getText())) {
					if(metodoakInsert.kontuKorronteSortu(langile, iban_sortu, txt_saldo_sortu.getText(), txt_limite_sortu.getText(), cb_egoera_sortu.getSelectedItem().toString(), sukurtsal_izen, erabiltzaileak,nan_langile,pass_langile)) {
						JOptionPane.showMessageDialog(null,"Kontu korrontea sortu da!","Info!", JOptionPane.INFORMATION_MESSAGE);
						erabiltzaileak = new String [0][4];
						txt_saldo_sortu.setText("");
						txt_limite_sortu.setText("");
						cb_egoera_sortu.setSelectedIndex(0);
						kontuBankarioaSortu.setVisible(false);
						langileMenu.setVisible(true);
					}
				}
			}
		});
		btn_sortu_kontu_korronte.setBounds(407, 430, 120, 34);
		kontuBankarioaSortu.add(btn_sortu_kontu_korronte);
				
		JButton btn_hipo_eskatu = new JButton("Eskatutako Hipotekak");
		btn_hipo_eskatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] eskatutako_hipotekak = metodoakTaulak.eskatutakoHipotekak(langile, sukurtsal_izen);
				
				hipotekak_table = new JTable(eskatutako_hipotekak,hipoteka_eskatu_header){
					private static final long serialVersionUID = 1L;
			        public boolean isCellEditable(int row, int column) {                
			                return false;               
			        };
			    };
				pane_hipotekak.setViewportView(hipotekak_table);
				hipotekak_table.getColumnModel().getColumn(0).setPreferredWidth(200);
				hipotekak_table.getTableHeader().setReorderingAllowed(false);
				
				btn_errefusatu.setVisible(true);
				btn_onartu.setVisible(true);
				
				hipotekak.setVisible(false);
				hipotekaTaulak.setVisible(true);
			}
		});
		btn_hipo_eskatu.setBounds(273, 252, 180, 34);
		hipotekak.add(btn_hipo_eskatu);
		
		JButton btn_hipo_onartuta = new JButton("Onartutako Hipotekak");
		btn_hipo_onartuta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] onartutako_hipotekak = metodoakTaulak.onartutakoHipotekak(langile, sukurtsal_izen);
				
				hipotekak_table = new JTable(onartutako_hipotekak,hipoteka_onartu_header){
					private static final long serialVersionUID = 1L;
			        public boolean isCellEditable(int row, int column) {                
			                return false;               
			        };
			    };
				pane_hipotekak.setViewportView(hipotekak_table);
				hipotekak_table.getColumnModel().getColumn(0).setPreferredWidth(200);
				hipotekak_table.getTableHeader().setReorderingAllowed(false);

				btn_errefusatu.setVisible(false);
				btn_onartu.setVisible(false);
				
				hipotekak.setVisible(false);
				hipotekaTaulak.setVisible(true);
			}
		});
		btn_hipo_onartuta.setBounds(512, 252, 180, 34);
		hipotekak.add(btn_hipo_onartuta);
		
		JButton btn_hipo_itxita = new JButton("Itxitako Hipotekak");
		btn_hipo_itxita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] itxitako_hipotekak = metodoakTaulak.itxitakoHipotekak(langile, sukurtsal_izen);
				
				hipotekak_table = new JTable(itxitako_hipotekak,hipoteka_itxita_header){
					private static final long serialVersionUID = 1L;
			        public boolean isCellEditable(int row, int column) {                
			                return false;               
			        };
			    };
				pane_hipotekak.setViewportView(hipotekak_table);
				hipotekak_table.getColumnModel().getColumn(0).setPreferredWidth(200);
				hipotekak_table.getTableHeader().setReorderingAllowed(false);
				
				btn_errefusatu.setVisible(false);
				btn_onartu.setVisible(false);
				
				hipotekak.setVisible(false);
				hipotekaTaulak.setVisible(true);
			}
		});
		btn_hipo_itxita.setBounds(512, 341, 180, 34);
		hipotekak.add(btn_hipo_itxita);
		
		JButton btn_hipo_errefusatu = new JButton("Errefusatutako Hipoteka");
		btn_hipo_errefusatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] errefusatutako_hipotekak = metodoakTaulak.errefusatutakoHipotekak(langile, sukurtsal_izen);
				
				hipotekak_table = new JTable(errefusatutako_hipotekak,hipoteka_eskatu_header){
					private static final long serialVersionUID = 1L;
			        public boolean isCellEditable(int row, int column) {                
			                return false;               
			        };
			    };
				pane_hipotekak.setViewportView(hipotekak_table);
				hipotekak_table.getColumnModel().getColumn(0).setPreferredWidth(200);
				hipotekak_table.getTableHeader().setReorderingAllowed(false);
				
				btn_errefusatu.setVisible(false);
				btn_onartu.setVisible(false);
				
				hipotekak.setVisible(false);
				hipotekaTaulak.setVisible(true);
			}
		});
		btn_hipo_errefusatu.setBounds(273, 341, 180, 34);
		hipotekak.add(btn_hipo_errefusatu);
		
		JLabel lbl_hipotekak = new JLabel("HIPOTEKAK");
		lbl_hipotekak.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_hipotekak.setBounds(431, 192, 114, 45);
		hipotekak.add(lbl_hipotekak);
		
		pane_hipotekak = new JScrollPane();
		pane_hipotekak.setBounds(127, 261, 686, 187);
		hipotekaTaulak.add(pane_hipotekak);
		
		hipotekak_table = new JTable();
		pane_hipotekak.setViewportView(hipotekak_table);
		
		JLabel lbl_hipoteka_mota = new JLabel("ESKATUTAKO HIPOTEKAK");
		lbl_hipoteka_mota.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_hipoteka_mota.setBounds(337, 205, 249, 45);
		hipotekaTaulak.add(lbl_hipoteka_mota);
		
		btn_errefusatu = new JButton("Errefusatu");
		btn_errefusatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = hipotekak_table.getSelectedRow();
				if(row>=0) {
					iban_hipoteka=hipotekak_table.getModel().getValueAt(row, 0).toString(); 
					iban_hipoteka = iban_hipoteka.replace(" ", "");
					for(int i=0;i<langile.getSukurtsalak().size();i++) {
						if(langile.getSukurtsalak().get(i).getKokalekua().equals(sukurtsal_izen)) {
							for(int j=0;j<langile.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
								if(langile.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban().equals(iban_hipoteka)) {
									langile=langile.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().hipotekaErrefusatu(langile, sukurtsal_izen, iban_hipoteka);
								}
							}
						}
					}

					String[][] eskatutako_hipotekak = metodoakTaulak.eskatutakoHipotekak(langile, sukurtsal_izen);
					
					hipotekak_table = new JTable(eskatutako_hipotekak,hipoteka_eskatu_header){
						private static final long serialVersionUID = 1L;
				        public boolean isCellEditable(int row, int column) {                
				                return false;               
				        };
				    };
					pane_hipotekak.setViewportView(hipotekak_table);
					
				}else {
					JOptionPane.showMessageDialog(null, "Hipoteka bat aukeratu behar duzu.","Alerta", JOptionPane.INFORMATION_MESSAGE);
				}				
			}
		});
		btn_errefusatu.setBounds(318, 478, 100, 34);
		hipotekaTaulak.add(btn_errefusatu);
		
		btn_onartu = new JButton("Onartu");
		btn_onartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = hipotekak_table.getSelectedRow();
				if(row>=0) {
					iban_hipoteka=hipotekak_table.getModel().getValueAt(row, 0).toString(); 
					iban_hipoteka=iban_hipoteka.replace(" ", "");
					for(int i=0;i<langile.getSukurtsalak().size();i++) {
						if(langile.getSukurtsalak().get(i).getKokalekua().equals(sukurtsal_izen)) {
							for(int j=0;j<langile.getSukurtsalak().get(i).getKontuBankarioak().size();j++) {
								if(langile.getSukurtsalak().get(i).getKontuBankarioak().get(j).getIban().equals(iban_hipoteka)) {
									langile=langile.getSukurtsalak().get(i).getKontuBankarioak().get(j).getHipoteka().hipotekaOnartu(langile, sukurtsal_izen, iban_hipoteka);
								}
							}
						}
					}

					String[][] eskatutako_hipotekak = metodoakTaulak.eskatutakoHipotekak(langile, sukurtsal_izen);
					
					hipotekak_table = new JTable(eskatutako_hipotekak,hipoteka_eskatu_header){
						private static final long serialVersionUID = 1L;
				        public boolean isCellEditable(int row, int column) {                
				                return false;               
				        };
				    };
					pane_hipotekak.setViewportView(hipotekak_table);
					
				}else {
					JOptionPane.showMessageDialog(null, "Hipoteka bat aukeratu behar duzu.","Alerta", JOptionPane.INFORMATION_MESSAGE);
				}				
			}
		});
		btn_onartu.setBounds(514, 478, 100, 34);
		hipotekaTaulak.add(btn_onartu);
		
		itxi_pane = new JScrollPane();
		itxi_pane.setBounds(102, 227, 712, 192);
		ixtekoKontuak.add(itxi_pane);
		
		JButton btn_itxi = new JButton("Itxi");
		btn_itxi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = itxi_table.getSelectedRow();
				if(row>=0) {
					ixteko_kontu_iban=itxi_table.getModel().getValueAt(row, 0).toString();
					ixteko_kontu_iban = ixteko_kontu_iban.replace(" ","");
					langile = metodoakObjetu.kontuItxi(langile, ixteko_kontu_iban, sukurtsal_izen);
					if(metodoakDelete.kontuItxiDelete(ixteko_kontu_iban, nan_langile, pass_langile)) {
						JOptionPane.showMessageDialog(null, "Kontu Korrontea ezabatu da.","Alerta", JOptionPane.INFORMATION_MESSAGE);
						String[][] ixteko_array = metodoakTaulak.ixtekoKontuak(langile, sukurtsal_izen);
						itxi_table = new JTable(ixteko_array,kontuak_lista) {
							private static final long serialVersionUID = 1L;
					        public boolean isCellEditable(int row, int column) {                
					                return false;               
					        };
					    };
						itxi_pane.setViewportView(itxi_table);
						itxi_table.getColumnModel().getColumn(0).setPreferredWidth(200);
						itxi_table.getTableHeader().setReorderingAllowed(false);					
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Hipoteka bat aukeratu behar duzu.","Alerta", JOptionPane.INFORMATION_MESSAGE);
				}	
			}
		});
		btn_itxi.setBounds(427, 451, 100, 34);
		ixtekoKontuak.add(btn_itxi);
		
		JLabel lbl_hipoteka_mota_1 = new JLabel("IXTEKO KONTUAK");
		lbl_hipoteka_mota_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_hipoteka_mota_1.setBounds(381, 171, 170, 45);
		ixtekoKontuak.add(lbl_hipoteka_mota_1);
		
		JLabel lbl_sukurtsala = new JLabel("SUKURTSALA AUKERATU");
		lbl_sukurtsala.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_sukurtsala.setBounds(355, 198, 223, 45);
		sukurtsalak.add(lbl_sukurtsala);
		
		JLabel lbl_entitate = new JLabel("Entitate Bankarioa:");
		lbl_entitate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_entitate.setBounds(335, 276, 131, 30);
		sukurtsalak.add(lbl_entitate);
		
		JLabel lbl_sukurtsal = new JLabel("Sukurtsala:");
		lbl_sukurtsal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_sukurtsal.setBounds(335, 317, 81, 30);
		sukurtsalak.add(lbl_sukurtsal);		
		
		btn_bezero_zerrenda = new JButton(" Bezero Zerrenda");
		btn_bezero_zerrenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bezeroZerrendaArray = metodoakSelect.bezeroZerrendaKargatu();
				zerrenda_table = new JTable(bezeroZerrendaArray,zerrenda_header_bez);
				pane_zerrenda.setViewportView(zerrenda_table);
				zerrenda_table.getTableHeader().setReorderingAllowed(false);
				lbl_zerrendak.setText("BEZERO ZERRENDA");
				langileZerrendaArray = null;
				langileInfo=null;
				sukurtsalak.setVisible(false);
				zerrendak.setVisible(true);
			}
		});
		btn_bezero_zerrenda.setBounds(155, 461, 162, 34);
		sukurtsalak.add(btn_bezero_zerrenda);
		
		btn_langile_zerrenda = new JButton("Langile Zerrenda");
		btn_langile_zerrenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				langileZerrendaArray = metodoakSelect.langileZerrendaKargatu();
				zerrenda_table = new JTable(langileZerrendaArray,zerrenda_header_lang);
				pane_zerrenda.setViewportView(zerrenda_table);
				zerrenda_table.getTableHeader().setReorderingAllowed(false);
				lbl_zerrendak.setText("LANGILE ZERRENDA");
				bezeroZerrendaArray=null;
				bezeroInfo=null;
				sukurtsalak.setVisible(false);
				zerrendak.setVisible(true);
			}
		});
		btn_langile_zerrenda.setBounds(388, 461, 169, 34);
		sukurtsalak.add(btn_langile_zerrenda);		
		
		btn_langile_erregistratu = new JButton("Langile Erregistratu");
		btn_langile_erregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cb_eguna_lang = new JComboBox<String>(egunak_array);
				cb_eguna_lang.setBounds(413, 385, 82, 30);
				langileRegistro.add(cb_eguna_lang);

				cb_hila_lang = new JComboBox<String>(hilak_array);
				cb_hila_lang.addActionListener (new ActionListener () {
					public void actionPerformed(ActionEvent e) {
						egunak_array= ArrayMetodoak.egunakBete(Integer.parseInt(cb_hila_lang.getSelectedItem().toString()));
						cb_eguna_lang.removeAllItems();
						DefaultComboBoxModel<String> egunak_model = new DefaultComboBoxModel<String>(egunak_array);
						cb_eguna_lang.setModel(egunak_model);
					}
				});
				cb_hila_lang.setBounds(303, 385, 82, 30);
				langileRegistro.add(cb_hila_lang);
				
				sukurtsalak.setVisible(false);
				langileRegistro.setVisible(true);
			}
		});
		btn_langile_erregistratu.setBounds(644, 461, 162, 34);
		sukurtsalak.add(btn_langile_erregistratu);
				
		JLabel lbl_edizio = new JLabel("BEZERO EDITATU");
		lbl_edizio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_edizio.setBounds(385, 163, 193, 45);
		bezeroEditatu.add(lbl_edizio);
		
		JLabel lbl_nan = new JLabel("NAN:");
		lbl_nan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_nan.setBounds(90, 269, 57, 30);
		bezeroEditatu.add(lbl_nan);
		
		JLabel lbl_izen_edizio = new JLabel("Izena:");
		lbl_izen_edizio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_izen_edizio.setBounds(90, 314, 112, 30);
		bezeroEditatu.add(lbl_izen_edizio);
		
		JLabel lbl_abizen_bez_edizio = new JLabel("Abizena:");
		lbl_abizen_bez_edizio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_abizen_bez_edizio.setBounds(90, 355, 112, 30);
		bezeroEditatu.add(lbl_abizen_bez_edizio);
		
		txt_abizen_editatu = new JTextField();
		txt_abizen_editatu.setColumns(10);
		txt_abizen_editatu.setBounds(190, 351, 202, 34);
		bezeroEditatu.add(txt_abizen_editatu);
		
		txt_izen_editatu = new JTextField();
		txt_izen_editatu.setColumns(10);
		txt_izen_editatu.setBounds(190, 310, 202, 34);
		bezeroEditatu.add(txt_izen_editatu);
		
		JLabel lbl_jaiotze_data = new JLabel("Jaiotze Data:");
		lbl_jaiotze_data.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_jaiotze_data.setBounds(90, 397, 112, 30);
		bezeroEditatu.add(lbl_jaiotze_data);
		
		JLabel lbl_sexu_editatu = new JLabel("Sexua:");
		lbl_sexu_editatu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_sexu_editatu.setBounds(489, 270, 112, 30);
		bezeroEditatu.add(lbl_sexu_editatu);
		
		JLabel lbl_telefono_edizio = new JLabel("Telefonoa:");
		lbl_telefono_edizio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_telefono_edizio.setBounds(489, 315, 112, 30);
		bezeroEditatu.add(lbl_telefono_edizio);
		
		JLabel lbl_pasahitza_editatu = new JLabel("Pasahitza:");
		lbl_pasahitza_editatu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_pasahitza_editatu.setBounds(489, 356, 112, 30);
		bezeroEditatu.add(lbl_pasahitza_editatu);
		
		txt_telefono_editatu = new JTextField();
		txt_telefono_editatu.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	            if (txt_telefono_editatu.getText().length() >= 9 || !Character.isDigit(c)) {
	                e.consume();
	            }
	        }
	    });
		txt_telefono_editatu.setColumns(10);
		txt_telefono_editatu.setBounds(589, 311, 161, 34);
		bezeroEditatu.add(txt_telefono_editatu);
		
		JLabel lbl_egoera_bez = new JLabel("Egoera:");
		lbl_egoera_bez.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_egoera_bez.setBounds(489, 399, 112, 30);
		bezeroEditatu.add(lbl_egoera_bez);
		
		JLabel lbl_nan_edizio = new JLabel("NAN");
		lbl_nan_edizio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_nan_edizio.setBounds(190, 269, 161, 30);
		bezeroEditatu.add(lbl_nan_edizio);
		
		JLabel lbl_jaiotze_data_editatu = new JLabel("data");
		lbl_jaiotze_data_editatu.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_jaiotze_data_editatu.setBounds(190, 396, 161, 30);
		bezeroEditatu.add(lbl_jaiotze_data_editatu);
		
		JComboBox<String> cb_sexua_editatu = new JComboBox<String>(sexu_array);
		cb_sexua_editatu.setBounds(588, 274, 129, 30);
		bezeroEditatu.add(cb_sexua_editatu);
		
		pass_bez_editatu = new JPasswordField();
		pass_bez_editatu.setBounds(589, 354, 112, 34);
		bezeroEditatu.add(pass_bez_editatu);
		
		JComboBox<String> cb_egoera_bezero = new JComboBox<String>(pertsona_egoera);
		cb_egoera_bezero.setBounds(589, 402, 129, 30);
		bezeroEditatu.add(cb_egoera_bezero);		
		
		JButton btn_gorde_editatu = new JButton("Aldaketak Gorde");
		btn_gorde_editatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txt_telefono_editatu.getText().length()==9 &&  !txt_izen_editatu.getText().equals("") && !txt_abizen_editatu.getText().equals("") && !String.valueOf(pass_bez_editatu.getPassword()).equals("")) {
					if(metodoakUpdate.bezeroAldaketakUpdate(lbl_nan_edizio.getText(), txt_izen_editatu.getText(), txt_abizen_editatu.getText(), cb_sexua_editatu.getSelectedItem().toString(), txt_telefono_editatu.getText(), String.valueOf(pass_bez_editatu.getPassword()), cb_egoera_bezero.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(null, "Bezeroaren datuak aldatu dira.","Alerta", JOptionPane.INFORMATION_MESSAGE);
						bezeroZerrendaArray = metodoakSelect.bezeroZerrendaKargatu();
						zerrenda_table = new JTable(bezeroZerrendaArray,zerrenda_header_bez);
						pane_zerrenda.setViewportView(zerrenda_table);
						zerrenda_table.getTableHeader().setReorderingAllowed(false);
						bezeroEditatu.setVisible(false);
						zerrendak.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Errore bezeroaren datuak aldatzean.","Error", JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Sartutako datuak ez dira balidoak.","Error", JOptionPane.ERROR_MESSAGE);					
				}
			}
		});
		btn_gorde_editatu.setBounds(386, 483, 161, 34);
		bezeroEditatu.add(btn_gorde_editatu);

		
				
		JLabel lbl_edizio_lang = new JLabel("LANGILE EDITATU");
		lbl_edizio_lang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_edizio_lang.setBounds(379, 164, 193, 45);
		langileEditatu.add(lbl_edizio_lang);
		
		JLabel lbl_nan_lang = new JLabel("NAN:");
		lbl_nan_lang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_nan_lang.setBounds(116, 271, 57, 30);
		langileEditatu.add(lbl_nan_lang);
		
		JLabel lbl_izen_edizio_lang = new JLabel("Izena:");
		lbl_izen_edizio_lang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_izen_edizio_lang.setBounds(116, 316, 112, 30);
		langileEditatu.add(lbl_izen_edizio_lang);
		
		JLabel lbl_abizen_lang = new JLabel("Abizena:");
		lbl_abizen_lang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_abizen_lang.setBounds(116, 357, 112, 30);
		langileEditatu.add(lbl_abizen_lang);
		
		txt_abizen_edizio_lang = new JTextField();
		txt_abizen_edizio_lang.setColumns(10);
		txt_abizen_edizio_lang.setBounds(216, 353, 202, 34);
		langileEditatu.add(txt_abizen_edizio_lang);
		
		txt_izen_edizio_lang = new JTextField();
		txt_izen_edizio_lang.setColumns(10);
		txt_izen_edizio_lang.setBounds(216, 312, 202, 34);
		langileEditatu.add(txt_izen_edizio_lang);
		
		JLabel lbl_jaiotze_data_langile = new JLabel("Jaiotze Data:");
		lbl_jaiotze_data_langile.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_jaiotze_data_langile.setBounds(116, 399, 112, 30);
		langileEditatu.add(lbl_jaiotze_data_langile);
		
		JLabel lbl_sexu_editatu_lang = new JLabel("Sexua:");
		lbl_sexu_editatu_lang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_sexu_editatu_lang.setBounds(515, 272, 112, 30);
		langileEditatu.add(lbl_sexu_editatu_lang);
		
		JLabel lbl_telefono_edizio_lang = new JLabel("Telefonoa:");
		lbl_telefono_edizio_lang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_telefono_edizio_lang.setBounds(515, 317, 112, 30);
		langileEditatu.add(lbl_telefono_edizio_lang);
		
		JLabel lbl_pasahitza_editatu_lang = new JLabel("Pasahitza:");
		lbl_pasahitza_editatu_lang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_pasahitza_editatu_lang.setBounds(515, 358, 112, 30);
		langileEditatu.add(lbl_pasahitza_editatu_lang);
		
		txt_tel_edizio_lang = new JTextField();
		txt_tel_edizio_lang.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(!Character.isDigit(c) || txt_tel_edizio_lang.getText().length() >= 9){ 
					e.consume();
				}
			}
		});
		txt_tel_edizio_lang.setColumns(10);
		txt_tel_edizio_lang.setBounds(615, 313, 161, 34);
		langileEditatu.add(txt_tel_edizio_lang);
		
		JLabel lbl_lanpostu_lan = new JLabel("Lanpostua:");
		lbl_lanpostu_lan.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_lanpostu_lan.setBounds(515, 401, 112, 30);
		langileEditatu.add(lbl_lanpostu_lan);
		
		JLabel lbl_nan_edizio_lang = new JLabel("NAN");
		lbl_nan_edizio_lang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_nan_edizio_lang.setBounds(216, 271, 161, 30);
		langileEditatu.add(lbl_nan_edizio_lang);
		
		JLabel lbl_jaiotze_data_edizio_lang = new JLabel("data");
		lbl_jaiotze_data_edizio_lang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_jaiotze_data_edizio_lang.setBounds(216, 398, 161, 30);
		langileEditatu.add(lbl_jaiotze_data_edizio_lang);
		
		JComboBox<String> cb_sexua_edizio_lang = new JComboBox<String>(sexu_array);
		cb_sexua_edizio_lang.setBounds(614, 276, 129, 30);
		langileEditatu.add(cb_sexua_edizio_lang);
		
		pass_edizio_lang = new JPasswordField();
		pass_edizio_lang.setBounds(615, 356, 112, 34);
		langileEditatu.add(pass_edizio_lang);
		
		JComboBox<String> cb_lanpostu = new JComboBox<String>(lanpostu_array);
		cb_lanpostu.setBounds(615, 404, 129, 30);
		langileEditatu.add(cb_lanpostu);
		
		JButton btn_gorde_editatu_1 = new JButton("Aldaketak Gorde");
		btn_gorde_editatu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txt_izen_edizio_lang.getText().toString().equals("") && !txt_abizen_edizio_lang.getText().toString().equals("") && txt_tel_edizio_lang.getText().toString().length()==9 && String.valueOf(pass_edizio_lang.getPassword()).length()==4 && !txt_id_sukurtsal.getText().equals("")) {
					if(metodoakUpdate.langileAldaketakUpdate(lbl_nan_edizio_lang.getText(), txt_izen_edizio_lang.getText(), txt_abizen_edizio_lang.getText(), cb_sexua_edizio_lang.getSelectedItem().toString(), txt_tel_edizio_lang.getText(), String.valueOf(pass_edizio_lang.getPassword()), cb_lanpostu.getSelectedItem().toString(),txt_id_sukurtsal.getText(),cb_egoera_langile.getSelectedItem().toString())) {
						JOptionPane.showMessageDialog(null, "Langilearen datuak aldatu dira.","Alerta", JOptionPane.INFORMATION_MESSAGE);
						langileZerrendaArray = metodoakSelect.langileZerrendaKargatu();
						zerrenda_table = new JTable(langileZerrendaArray,zerrenda_header_lang);
						pane_zerrenda.setViewportView(zerrenda_table);
						zerrenda_table.getTableHeader().setReorderingAllowed(false);
						langileEditatu.setVisible(false);
						zerrendak.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Errore langilearen datuak aldatzean.","Error", JOptionPane.ERROR_MESSAGE);
					}	
				}else {
					JOptionPane.showMessageDialog(null, "Datuak okerrak dira.","Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_gorde_editatu_1.setBounds(588, 496, 161, 34);
		langileEditatu.add(btn_gorde_editatu_1);
		
		JLabel lbl_id_sukurtsal = new JLabel("ID Sukurtsal:");
		lbl_id_sukurtsal.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_id_sukurtsal.setBounds(515, 444, 112, 30);
		langileEditatu.add(lbl_id_sukurtsal);
		
		txt_id_sukurtsal = new JTextField();
		txt_id_sukurtsal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(!Character.isDigit(c)){
					e.consume();
				}
			}
		});
		txt_id_sukurtsal.setColumns(10);
		txt_id_sukurtsal.setBounds(615, 440, 96, 34);
		langileEditatu.add(txt_id_sukurtsal);
		
		JLabel lbl_egoera_lang = new JLabel("Egoera:");
		lbl_egoera_lang.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_egoera_lang.setBounds(116, 441, 76, 30);
		langileEditatu.add(lbl_egoera_lang);
		
		cb_egoera_langile = new JComboBox<String>(pertsona_egoera);
		cb_egoera_langile.setBounds(216, 440, 129, 30);
		langileEditatu.add(cb_egoera_langile);
		
		JButton btn_kaleratu = new JButton("Kaleratu");
		btn_kaleratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(metodoakDelete.langileKaleratu(lbl_nan_edizio_lang.getText())) {
					JOptionPane.showMessageDialog(null, "Langilea kaleratu da.","Informazio", JOptionPane.INFORMATION_MESSAGE);
					langileZerrendaArray = metodoakSelect.langileZerrendaKargatu();
					zerrenda_table = new JTable(langileZerrendaArray,zerrenda_header_lang);
					pane_zerrenda.setViewportView(zerrenda_table);
					zerrenda_table.getTableHeader().setReorderingAllowed(false);
					langileEditatu.setVisible(false);
					zerrendak.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null, "Errorea Langilea kaleratzean.","Error", JOptionPane.ERROR_MESSAGE);					
				}
				
			}
		});
		btn_kaleratu.setBounds(226, 496, 161, 34);
		langileEditatu.add(btn_kaleratu);
		
		JButton btn_editatu = new JButton("Editatu");
		btn_editatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = zerrenda_table.getSelectedRow();
				if(row>=0) {
					if(bezeroZerrendaArray!= null && langileZerrendaArray==null) {
						langile_aukeratu_nan=zerrenda_table.getModel().getValueAt(row, 0).toString();
						bezeroInfo = metodoakArray.bezeroInfo(bezeroZerrendaArray, langile_aukeratu_nan);
						lbl_nan_edizio.setText(bezeroInfo[0]);
						txt_izen_editatu.setText(bezeroInfo[1]);
						txt_abizen_editatu.setText(bezeroInfo[2]);
						lbl_jaiotze_data_editatu.setText(bezeroInfo[3]);
						cb_sexua_editatu.setSelectedItem(bezeroInfo[4]);
						txt_telefono_editatu.setText(bezeroInfo[5]);
						pass_bez_editatu.setText(bezeroInfo[6]);
						cb_egoera_bezero.setSelectedItem(bezeroInfo[7]);
						bezeroEditatu.setVisible(true);
					}else if(bezeroZerrendaArray==null && langileZerrendaArray!=null) {
						langile_aukeratu_nan=zerrenda_table.getModel().getValueAt(row, 0).toString();
						langileInfo = metodoakArray.langileInfo(langileZerrendaArray, langile_aukeratu_nan);	
						lbl_nan_edizio_lang.setText(langileInfo[0]);
						txt_izen_edizio_lang.setText(langileInfo[1]);
						txt_abizen_edizio_lang.setText(langileInfo[2]);
						lbl_jaiotze_data_edizio_lang.setText(langileInfo[3]);
						cb_sexua_edizio_lang.setSelectedItem(	langileInfo[4]);
						txt_tel_edizio_lang.setText(langileInfo[5]);
						pass_edizio_lang.setText(langileInfo[6]);
						cb_lanpostu.setSelectedItem(langileInfo[7]);
						txt_id_sukurtsal.setText(langileInfo[8]);
						cb_egoera_langile.setSelectedItem(langileInfo[9]);
						if(zerrenda_table.getModel().getValueAt(row, 7).toString().equals("god")) {
							btn_kaleratu.setEnabled(false);
						}else {
							btn_kaleratu.setEnabled(true);
						}
						langileEditatu.setVisible(true);
					}
					zerrendak.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(null, "Pertsona bat aukeratu behar duzu.","Alerta", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btn_editatu.setBounds(407, 481, 108, 34);
		zerrendak.add(btn_editatu);
				
		JLabel lbl_registro_lang = new JLabel("LANGILE ERREGISTRATU");
		lbl_registro_lang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_registro_lang.setBounds(339, 151, 229, 45);
		langileRegistro.add(lbl_registro_lang);
		
		JLabel lbl_nan_lang_1 = new JLabel("NAN:");
		lbl_nan_lang_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_nan_lang_1.setBounds(101, 258, 57, 30);
		langileRegistro.add(lbl_nan_lang_1);
		
		JLabel lbl_izen_edizio_lang_1 = new JLabel("Izena:");
		lbl_izen_edizio_lang_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_izen_edizio_lang_1.setBounds(101, 303, 112, 30);
		langileRegistro.add(lbl_izen_edizio_lang_1);
		
		JLabel lbl_abizen_lang_1 = new JLabel("Abizena:");
		lbl_abizen_lang_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_abizen_lang_1.setBounds(101, 344, 112, 30);
		langileRegistro.add(lbl_abizen_lang_1);
		
		txt_lang_abizen_registro = new JTextField();
		txt_lang_abizen_registro.setColumns(10);
		txt_lang_abizen_registro.setBounds(201, 340, 202, 34);
		langileRegistro.add(txt_lang_abizen_registro);
		
		txt_lang_izen_registro = new JTextField();
		txt_lang_izen_registro.setColumns(10);
		txt_lang_izen_registro.setBounds(201, 299, 202, 34);
		langileRegistro.add(txt_lang_izen_registro);
		
		JLabel lbl_jaiotze_data_langile_1 = new JLabel("Jaiotze Data:");
		lbl_jaiotze_data_langile_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_jaiotze_data_langile_1.setBounds(101, 386, 112, 30);
		langileRegistro.add(lbl_jaiotze_data_langile_1);
		
		JLabel lbl_sexu_editatu_lang_1 = new JLabel("Sexua:");
		lbl_sexu_editatu_lang_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_sexu_editatu_lang_1.setBounds(541, 258, 112, 30);
		langileRegistro.add(lbl_sexu_editatu_lang_1);
		
		JLabel lbl_telefono_edizio_lang_1 = new JLabel("Telefonoa:");
		lbl_telefono_edizio_lang_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_telefono_edizio_lang_1.setBounds(541, 303, 112, 30);
		langileRegistro.add(lbl_telefono_edizio_lang_1);
		
		JLabel lbl_pasahitza_editatu_lang_1 = new JLabel("Pasahitza:");
		lbl_pasahitza_editatu_lang_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_pasahitza_editatu_lang_1.setBounds(541, 344, 112, 30);
		langileRegistro.add(lbl_pasahitza_editatu_lang_1);
		
		txt_tel_lang_registro = new JTextField();
		txt_tel_lang_registro.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	        	char c = e.getKeyChar();
	            if (txt_tel_lang_registro.getText().length() >= 9 || !Character.isDigit(c)) {
	                e.consume();
	            }
	        }
	    });
		txt_tel_lang_registro.setColumns(10);
		txt_tel_lang_registro.setBounds(641, 299, 161, 34);
		langileRegistro.add(txt_tel_lang_registro);
		
		JLabel lbl_lanpostu_lan_1 = new JLabel("Lanpostua:");
		lbl_lanpostu_lan_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_lanpostu_lan_1.setBounds(541, 387, 112, 30);
		langileRegistro.add(lbl_lanpostu_lan_1);
		
		cb_sexua_registro_lang = new JComboBox<String>(sexu_array);
		cb_sexua_registro_lang.setBounds(640, 262, 129, 30);
		langileRegistro.add(cb_sexua_registro_lang);
		
		pass_langile_registro = new JPasswordField();
		pass_langile_registro.setBounds(641, 342, 112, 34);
		langileRegistro.add(pass_langile_registro);
		
		cb_lanpostu_registro = new JComboBox<String>(lanpostu_array);
		cb_lanpostu_registro.setBounds(641, 390, 129, 30);
		langileRegistro.add(cb_lanpostu_registro);
		
		JButton btn_erregistratu_lang = new JButton("Erregistratu Langile");
		btn_erregistratu_lang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(metodoakSelect.langileNanKant(txt_lang_nan.getText())==0) {
						if(metodoakBoolean.nanBalidatu(txt_lang_nan.getText()) && !txt_lang_izen_registro.getText().equals("") && !txt_lang_abizen_registro.getText().equals("") && txt_tel_lang_registro.getText().length()==9 && !String.valueOf(pass_langile_registro.getPassword()).equals("") && !txt_sukurtsal_registro.getText().equals("")) {
							if(metodoakInsert.langileErregistratu(txt_lang_nan.getText(),txt_lang_izen_registro.getText(), txt_lang_abizen_registro.getText(),cb_urtea_lang.getSelectedItem().toString(),cb_hila_lang.getSelectedItem().toString(),cb_eguna_lang.getSelectedItem().toString(),cb_egoera_langile_registro.getSelectedItem().toString(),cb_sexua_registro_lang.getSelectedItem().toString(), txt_tel_lang_registro.getText(),String.valueOf(pass_langile_registro.getPassword()),cb_lanpostu_registro.getSelectedItem().toString(),txt_sukurtsal_registro.getText())){
								if(metodoakInsert.langileErabiltzaieSortu(txt_lang_nan.getText(), String.valueOf(pass_langile_registro.getPassword()),cb_lanpostu_registro.getSelectedItem().toString())) {
									JOptionPane.showMessageDialog(null,"Langilea erregistratu da!","Informazio", JOptionPane.INFORMATION_MESSAGE);
									txt_lang_nan.setText("");
									txt_lang_izen_registro.setText("");
									txt_lang_abizen_registro.setText("");
									cb_urtea_lang.setSelectedIndex(0);
									cb_hila_lang.setSelectedIndex(0);
									cb_eguna_lang.setSelectedIndex(0);
									cb_egoera_langile_registro.setSelectedIndex(0);
									cb_sexua_registro_lang.setSelectedIndex(0);
									txt_tel_lang_registro.setText("");
									pass_langile_registro.setText("");
									cb_lanpostu_registro.setSelectedIndex(0);
									txt_sukurtsal_registro.setText("");
									sukurtsalak.setVisible(true);
									langileRegistro.setVisible(false);									
								}
							}else {
								JOptionPane.showMessageDialog(null,"Error erregistratzean!","Errore", JOptionPane.ERROR_MESSAGE);					
							}
						}else {
							JOptionPane.showMessageDialog(null,"Datu okerrak!","Errore", JOptionPane.ERROR_MESSAGE);					
						}
					}				
				} catch (SalbuespenaErregistro e1) {
					JOptionPane.showMessageDialog(null,"Langile hau badago erregistratuta!","Errore", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btn_erregistratu_lang.setBounds(364, 485, 161, 34);
		langileRegistro.add(btn_erregistratu_lang);
		
		JLabel lbl_id_sukurtsal_1 = new JLabel("ID Sukurtsal:");
		lbl_id_sukurtsal_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_id_sukurtsal_1.setBounds(541, 430, 112, 30);
		langileRegistro.add(lbl_id_sukurtsal_1);
		
		txt_sukurtsal_registro = new JTextField();
		txt_sukurtsal_registro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(!Character.isDigit(c)){
					e.consume();
				}
			}
		});
		txt_sukurtsal_registro.setColumns(10);
		txt_sukurtsal_registro.setBounds(641, 426, 96, 34);
		langileRegistro.add(txt_sukurtsal_registro);
		
		JLabel lbl_egoera_lang_1 = new JLabel("Egoera:");
		lbl_egoera_lang_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_egoera_lang_1.setBounds(101, 428, 76, 30);
		langileRegistro.add(lbl_egoera_lang_1);
		
		cb_egoera_langile_registro = new JComboBox<String>(pertsona_egoera);
		cb_egoera_langile_registro.setBounds(201, 427, 129, 30);
		langileRegistro.add(cb_egoera_langile_registro);
		
		txt_lang_nan = new JTextField();
		txt_lang_nan.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (txt_lang_nan.getText().length() >= 9) {
	                e.consume();
	            }
	        }
	    });
		txt_lang_nan.setColumns(10);
		txt_lang_nan.setBounds(201, 258, 202, 34);
		langileRegistro.add(txt_lang_nan);
		
		cb_urtea_lang = new JComboBox<String>(urteak_array);
		cb_urtea_lang.setBounds(201, 385, 82, 30);
		langileRegistro.add(cb_urtea_lang);
		
		JLabel lbl_erabiltzaile_barra1_1 = new JLabel("/");
		lbl_erabiltzaile_barra1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_erabiltzaile_barra1_1.setBounds(293, 385, 21, 30);
		langileRegistro.add(lbl_erabiltzaile_barra1_1);
		
		JLabel lbl_erabiltzaile_barra2_1 = new JLabel("/");
		lbl_erabiltzaile_barra2_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_erabiltzaile_barra2_1.setBounds(403, 385, 21, 30);
		langileRegistro.add(lbl_erabiltzaile_barra2_1);
	}
}
