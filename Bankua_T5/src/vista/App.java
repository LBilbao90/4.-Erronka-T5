package vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Metodoak;
import model.Bezeroa;
import model.EntitateBankario;
import model.Langilea;

import javax.swing.*;
import java.awt.event.*;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class App extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_bezero_erabiltzaile;
	private JPasswordField passBezero;
	private JTextField txt_langile_erabiltzaile;
	private JPasswordField passLangile;
	Metodoak metodoak = new Metodoak();
	final ImageIcon homeicon = new ImageIcon(new ImageIcon("src/res/casa.png").getImage().getScaledInstance(44, 34,Image.SCALE_DEFAULT));
	final ImageIcon logo_atzera = new ImageIcon(new ImageIcon("src/res/flecha_atras.png").getImage().getScaledInstance(44,30,Image.SCALE_DEFAULT));
	final ImageIcon logo_aurrera = new ImageIcon(new ImageIcon("src/res/flecha_alante.png").getImage().getScaledInstance(44,30,Image.SCALE_DEFAULT));
	final ImageIcon fondo_argazki = new ImageIcon(new ImageIcon("src/res/logo2.2.png").getImage().getScaledInstance(932,130,Image.SCALE_DEFAULT));
	ArrayList<EntitateBankario> entitateak = new ArrayList<>();
	Langilea langile = null;
	Bezeroa bezero = null;
	String nan_bezero = "";
	String nan_langile = "";
	String lanpostua = "";
	String sukurtsal_izen = "";
	String [] entitate_izen = null;
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
	String[] trasnferentziak_header = {"Jasotzailea","Kantitatea","Kontzeptua", "Transferentzia-data"};
	
	String[] langile_kontu_info = null;
	String[][] transferentzia_info = null;
	String[][] sarrera_info = null;
	String langile_aukeratu_iban="";
	
	int id_entitate = 0;
	String[][] kontuBankarioak = null;
	String[][] transferentziak = null;
	String kontua = null;
	
	//Combo Box arrayak
	String[] kontu_egoera = {"aktiboa","izoztuta","ixteko","itxita"};
	String[] sexu_array = {"gizona","emakumea"};
	String[] hilak_array = null;	
	String[] urteak_array= null;
	String[] egunak_array = null;
	String[] epemugak_array = {"3 urte", "5 urte", "10 urte", "15 urte"};
	
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
	private JPasswordField pass_segurtsaunkode_kontu;
	private JTextField txt_saldo_sortu;
	private JTextField txt_limite_sortu;
	private JTable hipotekak_table;
	private JTable itxi_table;
	JComboBox cb_sukurtsala = null;
	JComboBox cb_hila = null;
	JComboBox cb_eguna = null;
	JComboBox cb_egoera_kontua = null;
	JButton btn_kontu_itzi;
	JScrollPane kontuak_pane;
	JLabel lbl_iban_kontua;
	JLabel lbl_jabeak_kontua;
	JLabel lbl_saldo_kontua;
	JScrollPane itxi_pane;
	JButton btn_errefusatu;
	JButton btn_onartu;
	private JTextField txt_abizen_erabiltzaile;
	JScrollPane scrollPane_entitateKont;
	JScrollPane pane_hipotekak;
	
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

		urteak_array = Metodoak.urteakBete();
		hilak_array = Metodoak.hilakBete();
		egunak_array = Metodoak.egunakBete(1);
		
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
				if(metodoak.bezeroLogin(txt_bezero_erabiltzaile.getText(),String.valueOf(passBezero.getPassword()))) {
					nan_bezero= txt_bezero_erabiltzaile.getText();
					//Langilearen logina erregistratu
					metodoak.loginErregistratu(nan_bezero, "Bezeroa");
					
					bezero = metodoak.bezeroaKargatu(nan_bezero);
					entitateak = metodoak.botoiakSortu();

					//Entitateen botoiak sortzen dira
					for(int i=0;i<entitateak.size();i++) {		
						String[] limiteak = entitateak.get(i).getBounds().split("/");
						ImageIcon logo_banco = new ImageIcon(new ImageIcon(entitateak.get(i).getUrl()).getImage().getScaledInstance(Integer.parseInt(limiteak[2]),Integer.parseInt(limiteak[3]),Image.SCALE_DEFAULT));
						JButton btn_banco = new JButton(logo_banco);
						btn_banco.setToolTipText(String.valueOf(i+1));
						btn_banco.addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								id_entitate = Character.getNumericValue(btn_banco.getToolTipText().charAt(0));
								kontuBankarioak = metodoak.bezeroarenKontuak(bezero, id_entitate);
								
								scrollPane_entitateKont.setViewportView(table_entitateKont);
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
				}else {
					JOptionPane.showMessageDialog(null,"Login Okerra!","Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_bezero_sartu.setBounds(437, 423, 89, 23);
		loginBezero.add(btn_bezero_sartu);
		
		
		JButton btn_langile_sartu = new JButton("Sartu");
		btn_langile_sartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lanpostua = metodoak.langileLogin(txt_langile_erabiltzaile.getText(),String.valueOf(passLangile.getPassword()));
				if(lanpostua!=null) {					
					nan_langile = txt_langile_erabiltzaile.getText();
					//Langilearen logina erregistratu
					metodoak.loginErregistratu(nan_langile, "Bezeroa");
					
					langile = metodoak.langileaKargatu(nan_langile,lanpostua);
					//Langilearen entitate eta sukurtsalak kargatu
					entitate_izen = metodoak.langilearenEntitateak(langile);		
					sukurtsal_kok = metodoak.langilearenSukurtsalak(langile, langile.getSukurtsalak().get(0).getEntitateBankario().getIzena());
					
					cb_sukurtsala = new JComboBox(sukurtsal_kok);
					cb_sukurtsala.setBounds(412, 317, 230, 30);
					sukurtsalak.add(cb_sukurtsala);
					
					JComboBox cb_entitate = new JComboBox(entitate_izen);
					cb_entitate.addActionListener (new ActionListener () {
						public void actionPerformed(ActionEvent e) {
							sukurtsal_kok = metodoak.langilearenSukurtsalak(langile, cb_entitate.getSelectedItem().toString());
							cb_sukurtsala.removeAllItems();
							DefaultComboBoxModel sukurtsal_kokapena = new DefaultComboBoxModel(sukurtsal_kok);
							cb_sukurtsala.setModel(sukurtsal_kokapena);
						}
					});
					cb_entitate.setBounds(460, 278, 131, 30);
					sukurtsalak.add(cb_entitate);					
					
					loginLangile.setVisible(false);
					sukurtsalak.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(null,"Login Okerra!","Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btn_langile_sartu.setBounds(437, 423, 89, 23);
		loginLangile.add(btn_langile_sartu);

		//////////////////////////////////
		// 		  Etxea Botoiak 		//
		//////////////////////////////////
		JButton btn_etxea_1 = new JButton(homeicon);
		btn_etxea_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entitateKontuak.setVisible(true);
				bezeroEntitate.setVisible(false);
			}
		});
		btn_etxea_1.setBounds(29, 151, 44, 34);		
		btn_etxea_1.setOpaque(false);
		btn_etxea_1.setContentAreaFilled(false);
		btn_etxea_1.setBorderPainted(false);
		bezeroEntitate.add(btn_etxea_1);
		entitateKontuak.setLayout(null);
		
		JButton btn_etxea_2 = new JButton(homeicon);
		btn_etxea_2.setBounds(29, 151, 44, 34);
		btn_etxea_2.setOpaque(false);
		btn_etxea_2.setContentAreaFilled(false);
		btn_etxea_2.setBorderPainted(false);
		entitateKontuak.add(btn_etxea_2);		

		JButton btn_etxea_3 = new JButton(homeicon);
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
		btn_etxea_8.setOpaque(false);
		btn_etxea_8.setContentAreaFilled(false);
		btn_etxea_8.setBorderPainted(false);
		btn_etxea_8.setBounds(29, 151, 44, 34);
		hipotekaEskatu.add(btn_etxea_8);

		JButton btn_etxea_9 = new JButton(homeicon);
		btn_etxea_9.setOpaque(false);
		btn_etxea_9.setContentAreaFilled(false);
		btn_etxea_9.setBorderPainted(false);
		btn_etxea_9.setBounds(29, 151, 44, 34);
		hipotekaEstatus.add(btn_etxea_9);
		
		JButton btn_etxea_10 = new JButton(homeicon);
		btn_etxea_10.setOpaque(false);
		btn_etxea_10.setContentAreaFilled(false);
		btn_etxea_10.setBorderPainted(false);
		btn_etxea_10.setBounds(29, 151, 44, 34);
		hipotekaOrdaindu.add(btn_etxea_10);

		JButton btn_etxea_11 = new JButton(homeicon);
		btn_etxea_11.setOpaque(false);
		btn_etxea_11.setContentAreaFilled(false);
		btn_etxea_11.setBorderPainted(false);
		btn_etxea_11.setBounds(29, 151, 44, 34);
		mugimenduak.add(btn_etxea_11);

		JButton btn_etxea_12 = new JButton(homeicon);
		btn_etxea_12.setOpaque(false);
		btn_etxea_12.setContentAreaFilled(false);
		btn_etxea_12.setBorderPainted(false);
		btn_etxea_12.setBounds(29, 151, 44, 34);
		langileMenu.add(btn_etxea_12);

		JButton btn_etxea_13 = new JButton(homeicon);
		btn_etxea_13.setOpaque(false);
		btn_etxea_13.setContentAreaFilled(false);
		btn_etxea_13.setBorderPainted(false);
		btn_etxea_13.setBounds(29, 151, 44, 34);
		kontuakLista.add(btn_etxea_13);

		JButton btn_etxea_14 = new JButton(homeicon);
		btn_etxea_14.setOpaque(false);
		btn_etxea_14.setContentAreaFilled(false);
		btn_etxea_14.setBorderPainted(false);
		btn_etxea_14.setBounds(29, 151, 44, 34);
		infoKontua.add(btn_etxea_14);

		JButton btn_etxea_15 = new JButton(homeicon);
		btn_etxea_15.setOpaque(false);
		btn_etxea_15.setContentAreaFilled(false);
		btn_etxea_15.setBorderPainted(false);
		btn_etxea_15.setBounds(29, 151, 44, 34);
		erabiltzaileSortu.add(btn_etxea_15);

		JButton btn_etxea_16 = new JButton(homeicon);
		btn_etxea_16.setOpaque(false);
		btn_etxea_16.setContentAreaFilled(false);
		btn_etxea_16.setBorderPainted(false);
		btn_etxea_16.setBounds(29, 151, 44, 34);
		kontuSortu.add(btn_etxea_16);

		JButton btn_etxea_17 = new JButton(homeicon);
		btn_etxea_17.setOpaque(false);
		btn_etxea_17.setContentAreaFilled(false);
		btn_etxea_17.setBorderPainted(false);
		btn_etxea_17.setBounds(29, 151, 44, 34);
		kontuBankarioaSortu.add(btn_etxea_17);
		
		JButton btn_etxea_18 = new JButton(homeicon);
		btn_etxea_18.setOpaque(false);
		btn_etxea_18.setContentAreaFilled(false);
		btn_etxea_18.setBorderPainted(false);
		btn_etxea_18.setBounds(29, 151, 44, 34);
		hipotekak.add(btn_etxea_18);

		JButton btn_etxea_19 = new JButton(homeicon);
		btn_etxea_19.setOpaque(false);
		btn_etxea_19.setContentAreaFilled(false);
		btn_etxea_19.setBorderPainted(false);
		btn_etxea_19.setBounds(29, 151, 44, 34);
		hipotekaTaulak.add(btn_etxea_19);

		JButton btn_etxea_20 = new JButton(homeicon);
		btn_etxea_20.setOpaque(false);
		btn_etxea_20.setContentAreaFilled(false);
		btn_etxea_20.setBorderPainted(false);
		btn_etxea_20.setBounds(29, 151, 44, 34);
		ixtekoKontuak.add(btn_etxea_20);

		JButton btn_etxea_21 = new JButton(homeicon);
		btn_etxea_21.setOpaque(false);
		btn_etxea_21.setContentAreaFilled(false);
		btn_etxea_21.setBorderPainted(false);
		btn_etxea_21.setBounds(29, 151, 44, 34);
		sukurtsalak.add(btn_etxea_21);
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
					kontua = table_entitateKont.getModel().getValueAt(row, column).toString(); // Taulan aukeratutako kontuaren IBAN-a gordetzen du
					kontua = kontua.replace(" ", "");
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
		btn_atzera_5.setOpaque(false);
		btn_atzera_5.setContentAreaFilled(false);
		btn_atzera_5.setBorderPainted(false);
		btn_atzera_5.setBounds(15, 513, 44, 30);
		transferentziaEgin.add(btn_atzera_5);
		
		JButton btn_atzera_6 = new JButton(logo_atzera);
		btn_atzera_6.setOpaque(false);
		btn_atzera_6.setContentAreaFilled(false);
		btn_atzera_6.setBorderPainted(false);
		btn_atzera_6.setBounds(15, 513, 44, 30);
		sarrerak.add(btn_atzera_6);

		JButton btn_atzera_7 = new JButton(logo_atzera);
		btn_atzera_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		btn_atzera_9.setOpaque(false);
		btn_atzera_9.setContentAreaFilled(false);
		btn_atzera_9.setBorderPainted(false);
		btn_atzera_9.setBounds(15, 513, 44, 30);
		hipotekaOrdaindu.add(btn_atzera_9);
		
		JButton btn_atzera_10 = new JButton(logo_atzera);
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
					langile_kontu_info = metodoak.langileKontuInfo(langile, langile_aukeratu_iban, sukurtsal_izen);
					lbl_iban_kontua.setText(langile_kontu_info[0]);
					lbl_jabeak_kontua.setText(langile_kontu_info[1]);
					lbl_saldo_kontua.setText(langile_kontu_info[2]);

					cb_egoera_kontua = new JComboBox(kontu_egoera);
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
		btn_atzera_13.setOpaque(false);
		btn_atzera_13.setContentAreaFilled(false);
		btn_atzera_13.setBorderPainted(false);
		btn_atzera_13.setBounds(15, 513, 44, 30);
		infoKontua.add(btn_atzera_13);

		JButton btn_atzera_14 = new JButton(logo_atzera);
		btn_atzera_14.setOpaque(false);
		btn_atzera_14.setContentAreaFilled(false);
		btn_atzera_14.setBorderPainted(false);
		btn_atzera_14.setBounds(15, 513, 44, 30);
		erabiltzaileSortu.add(btn_atzera_14);

		JButton btn_atzera_15 = new JButton(logo_atzera);
		btn_atzera_15.setOpaque(false);
		btn_atzera_15.setContentAreaFilled(false);
		btn_atzera_15.setBorderPainted(false);
		btn_atzera_15.setBounds(15, 513, 44, 30);
		kontuSortu.add(btn_atzera_15);
		
		JButton btn_atzera_16 = new JButton(logo_atzera);
		btn_atzera_16.setOpaque(false);
		btn_atzera_16.setContentAreaFilled(false);
		btn_atzera_16.setBorderPainted(false);
		btn_atzera_16.setBounds(15, 513, 44, 30);
		kontuBankarioaSortu.add(btn_atzera_16);
		
		JButton btn_atzera_17 = new JButton(logo_atzera);
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
		btn_ordaindu.setBounds(392, 443, 153, 34);
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
		
		JButton btn_print_2 = new JButton("Imprimatu");
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
				
				if(!Character.isDigit(c) || txt_kantitate.getText().length() >= 10 ){
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
					if(txt_jasotzaile.getText().length() == 24 && metodoak.transferentziaIbanBalidatu(txt_jasotzaile.getText())) {
						if(!txt_jasotzaile.getText().equals(kontua)) {
							if(metodoak.transferentziaSaldoaBalidatu(txt_kantitate.getText(), txt_jasotzaile.getText())) {
								if(metodoak.segurtasunKodeaBalidatu(String.valueOf(transferentzia_segurtasun.getPassword()), kontua)) {
									
								}else {
									JOptionPane.showMessageDialog(null, "Sartutako segurtasun kodea ez da zuzena.","Error!", JOptionPane.INFORMATION_MESSAGE);
								}
							}else {
								JOptionPane.showMessageDialog(null, "Sartutako diru kopurua kontuan dagoena baino handiagoa da.","Error!", JOptionPane.INFORMATION_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(null, "Ezin duzu zure buruari transakziorik egin.","Error!", JOptionPane.INFORMATION_MESSAGE);
						}
					}else {
						JOptionPane.showMessageDialog(null, "Sartutako IBAN-a ez da zuzena.","Error!", JOptionPane.INFORMATION_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Datuak ez dira zuzenak.","Error!", JOptionPane.INFORMATION_MESSAGE);
				}
				//txt_kantitate
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
		btn_print_1.setBounds(410, 502, 107, 30);
		transferentziaIkusi.add(btn_print_1);
		
		JButton btn_transfer_ikusi = new JButton("Transferentziak Ikusi");
		btn_transfer_ikusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				transferentziak = metodoak.transferentziakIkusi(bezero, id_entitate, kontua);
				
				
				transfer_ikusi_table = new JTable(transferentziak, trasnferentziak_header) {
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
		
		JButton btn_transferentzia = new JButton("Tranferentzia");
		btn_transferentzia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bezeroKontua.setVisible(false);
				transferentzia.setVisible(true);
			}
		});
		btn_transferentzia.setBounds(276, 265, 115, 34);
		bezeroKontua.add(btn_transferentzia);
		
		JButton btn_sarrerak = new JButton("Diru Sarrerak");
		btn_sarrerak.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bezeroKontua.setVisible(false);
				sarrerak.setVisible(true);
			}
		});
		btn_sarrerak.setBounds(537, 265, 115, 34);
		bezeroKontua.add(btn_sarrerak);
		
		JButton btn_hipoteka = new JButton("Hipoteka");
		btn_hipoteka.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(metodoak.hipotekaDut(bezero, kontua)) {
					bezeroKontua.setVisible(false);
					hipotekaEstatus.setVisible(true);
				}else {
					bezeroKontua.setVisible(false);
					hipotekaEskatu.setVisible(true);
				}
			}
		});
		btn_hipoteka.setBounds(276, 354, 115, 34);
		bezeroKontua.add(btn_hipoteka);
		
		JButton btn_mugimendu = new JButton("Mugimenduak");
		btn_mugimendu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bezeroKontua.setVisible(false);
				mugimenduak.setVisible(true);
			}
		});
		btn_mugimendu.setBounds(537, 354, 115, 34);
		bezeroKontua.add(btn_mugimendu);
		
		JButton btn_kontuItxi = new JButton("Kontua Itxi");
		btn_kontuItxi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_kontuItxi.setBounds(409, 425, 115, 34);
		bezeroKontua.add(btn_kontuItxi);
		btn_kontuItxi.setBackground(Color.LIGHT_GRAY);
		
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
				
		JButton btn_eskatu = new JButton("Eskatu");
		btn_eskatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txt_kantitate_hipoteka.getText().equals("") && !String.valueOf(hipoteka_segurtasun.getPassword()).equals("")) {
					if(metodoak.segurtasunKodeaBalidatu(String.valueOf(hipoteka_segurtasun.getPassword()), kontua)) {
						hipotekaEstatus.setVisible(true);
						hipotekaEskatu.setVisible(false);
					}else {
						JOptionPane.showMessageDialog(null, "Sartutako segurtasun kodea ez da zuzena.","Error!", JOptionPane.INFORMATION_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "Datuak ez dira zuzenak.","Error!", JOptionPane.INFORMATION_MESSAGE);
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
				
				if(!Character.isDigit(c) || txt_kantitate_hipoteka.getText().length() >= 10 ){
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
		
		txt_hipoteka_komisio = new JTextField("");
		txt_hipoteka_komisio.setEditable(false);
		txt_hipoteka_komisio.setColumns(10);
		txt_hipoteka_komisio.setBounds(442, 335, 57, 34);
		hipotekaEskatu.add(txt_hipoteka_komisio);
		
		JComboBox hipoteka_combo = new JComboBox(epemugak_array);
		hipoteka_combo.setSelectedIndex(0);
		txt_hipoteka_komisio.setText("3 %");
		hipoteka_combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(hipoteka_combo.getSelectedIndex() == 0) {
					txt_hipoteka_komisio.setText("3 %");
				}else if(hipoteka_combo.getSelectedIndex() == 1) {
					txt_hipoteka_komisio.setText("3,5 %");
				}else if(hipoteka_combo.getSelectedIndex() == 2) {
					txt_hipoteka_komisio.setText("3,8 %");
				}else if(hipoteka_combo.getSelectedIndex() == 3) {
					txt_hipoteka_komisio.setText("4 %");
				}
			}
		});
		hipoteka_combo.setBounds(442, 290, 94, 34);
		hipotekaEskatu.add(hipoteka_combo);
		
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
		
		JButton btn_ordaindu_1 = new JButton("Ordaindu");
		btn_ordaindu_1.setBounds(392, 427, 153, 34);
		hipotekaOrdaindu.add(btn_ordaindu_1);
		
		JLabel lbl_hip_ordaindu = new JLabel("HIPOTEKA ORDAINDU");
		lbl_hip_ordaindu.setFont(new Font("Tahoma", Font.BOLD, 15));
		lbl_hip_ordaindu.setBounds(380, 184, 172, 48);
		hipotekaOrdaindu.add(lbl_hip_ordaindu);
		
		
		JScrollPane transfer_pane = new JScrollPane();
		transfer_pane.setBounds(109, 192, 757, 109);
		mugimenduak.add(transfer_pane);
		
		transfer_table = new JTable();
		transfer_pane.setViewportView(transfer_table);
		
		JScrollPane sarrera_pane = new JScrollPane();
		sarrera_pane.setBounds(109, 373, 757, 109);
		mugimenduak.add(sarrera_pane);
		
		sarrera_table = new JTable();
		sarrera_pane.setViewportView(sarrera_table);
		
		JButton btn_imprimatu_mugi = new JButton("Mugimenduak Imprimatu");
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
				sukurtsal_kontuak=metodoak.langilearenSukurtsalarenKontuak(langile, sukurtsal_izen);

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
				Metodoak.ibanKalkulatu(langile, sukurtsal_izen);
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

				cb_eguna = new JComboBox(egunak_array);
				cb_eguna.setBounds(645, 362, 82, 30);
				erabiltzaileSortu.add(cb_eguna);

				cb_hila = new JComboBox(hilak_array);
				cb_hila.addActionListener (new ActionListener () {
					public void actionPerformed(ActionEvent e) {
						egunak_array= metodoak.egunakBete(Integer.parseInt(cb_hila.getSelectedItem().toString()));
						cb_eguna.removeAllItems();
						DefaultComboBoxModel egunak_model = new DefaultComboBoxModel(egunak_array);
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
				String[][] ixteko_array = metodoak.ixtekoKontuak(langile, sukurtsal_izen);
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
				if(metodoak.langileKontuAldaketak(cb_egoera_kontua.getSelectedItem().toString(), txt_limite_kontua.getText(), langile_aukeratu_iban)) {
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
				transferentzia_info=metodoak.langileKontuTransfer(langile, langile_aukeratu_iban);
				
				transfer_ikusi_table = new JTable(transferentzia_info,transfer_header);
				scrollPane_transfer.setViewportView(transfer_ikusi_table);
				transfer_ikusi_table.getColumnModel().getColumn(2).setPreferredWidth(200);
				
				infoKontua.setVisible(false);
				transferentziaIkusi.setVisible(true);
			}
		});
		btn_transferentziak_ikusi.setBounds(731, 424, 180, 34);
		infoKontua.add(btn_transferentziak_ikusi);
		
		JButton btn_dirusarrerak_ikusi = new JButton("Diru Sarrerak Ikusi");
		btn_dirusarrerak_ikusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sarrera_info=metodoak.langileKontuSarrerak(langile, langile_aukeratu_iban);

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
		
		JTextField txt_izen_erabiltzaile = new JTextField();
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
		
		JComboBox cb_sexua = new JComboBox(sexu_array);
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
	            if (txt_tel_sortu.getText().length() >= 9) {
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
		
		JComboBox cb_urtea = new JComboBox(urteak_array);
		cb_urtea.setBounds(425, 362, 82, 30);
		erabiltzaileSortu.add(cb_urtea);
				
		
		JLabel lbl_erabiltzaile_pass_1_1 = new JLabel("/");
		lbl_erabiltzaile_pass_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_erabiltzaile_pass_1_1.setBounds(517, 362, 21, 30);
		erabiltzaileSortu.add(lbl_erabiltzaile_pass_1_1);
		
		JLabel lbl_erabiltzaile_pass_1_1_1 = new JLabel("/");
		lbl_erabiltzaile_pass_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_erabiltzaile_pass_1_1_1.setBounds(627, 362, 21, 30);
		erabiltzaileSortu.add(lbl_erabiltzaile_pass_1_1_1);
		
		JButton btn_sortu_kontu = new JButton("Sortu Kontua");
		btn_sortu_kontu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//registro
				if(metodoak.bezeroSortu(txt_izen_erabiltzaile.getText(),txt_abizen_erabiltzaile	.getText(),txt_nan_sortu.getText(),String.valueOf(pass_bezero_sortu.getPassword()),cb_urtea.getSelectedItem().toString(),cb_hila.getSelectedItem().toString(),cb_eguna.getSelectedItem().toString(),cb_sexua.getSelectedItem().toString(),txt_tel_sortu.getText())) {
					JOptionPane.showMessageDialog(null, "Erabiltzailea sortu da.","Informazio", JOptionPane.INFORMATION_MESSAGE);
					erabiltzaileSortu.setVisible(false);
					langileMenu.setVisible(true);
				}else {
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
		txt_kontu_sortu_nan.setColumns(10);
		txt_kontu_sortu_nan.setBounds(385, 246, 124, 30);
		kontuSortu.add(txt_kontu_sortu_nan);
		
		JLabel lbl_erabiltzaile_pass_1_2 = new JLabel("Segurtasun Kodea:");
		lbl_erabiltzaile_pass_1_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_erabiltzaile_pass_1_2.setBounds(337, 329, 124, 30);
		kontuSortu.add(lbl_erabiltzaile_pass_1_2);
		
		pass_segurtsaunkode_kontu = new JPasswordField();
		pass_segurtsaunkode_kontu.setBounds(471, 331, 114, 30);
		kontuSortu.add(pass_segurtsaunkode_kontu);
		
		JLabel lbl_txartel_id = new JLabel("Txartel Zenbakia:");
		lbl_txartel_id.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_txartel_id.setBounds(337, 287, 114, 30);
		kontuSortu.add(lbl_txartel_id);
		
		JLabel lbl_txartelid = new JLabel("0000 0000 0000 0000");
		lbl_txartelid.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_txartelid.setBounds(461, 288, 156, 30);
		kontuSortu.add(lbl_txartelid);
		
		JButton btn_jarraitu = new JButton("Jarraitu");
		btn_jarraitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kontuSortu.setVisible(false);
				kontuBankarioaSortu.setVisible(true);
			}
		});
		btn_jarraitu.setBounds(430, 445, 82, 34);
		kontuSortu.add(btn_jarraitu);
		
		JButton btn_gehitu_erabiltzaile = new JButton("Gehitu Beste Erabiltzaile Bat");
		btn_gehitu_erabiltzaile.setBounds(367, 381, 210, 34);
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
		
		JLabel lbl_kontusortu_iban = new JLabel("ES00 0000 0000 00 0000000000");
		lbl_kontusortu_iban.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_kontusortu_iban.setBounds(383, 242, 232, 30);
		kontuBankarioaSortu.add(lbl_kontusortu_iban);
		
		txt_limite_sortu = new JTextField();
		txt_limite_sortu.setColumns(10);
		txt_limite_sortu.setBounds(420, 324, 145, 30);
		kontuBankarioaSortu.add(txt_limite_sortu);
		
		JLabel lbl_egoera_sortu = new JLabel("Egoera:");
		lbl_egoera_sortu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbl_egoera_sortu.setBounds(335, 365, 72, 30);
		kontuBankarioaSortu.add(lbl_egoera_sortu);
		
		JComboBox cb_egoera_sortu = new JComboBox();
		cb_egoera_sortu.setBounds(407, 365, 131, 30);
		kontuBankarioaSortu.add(cb_egoera_sortu);
		
		JButton btn_sortu_kontu_1_1 = new JButton("Sortu Kontua");
		btn_sortu_kontu_1_1.setBounds(407, 430, 120, 34);
		kontuBankarioaSortu.add(btn_sortu_kontu_1_1);
				
		JButton btn_hipo_eskatu = new JButton("Eskatutako Hipotekak");
		btn_hipo_eskatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[][] eskatutako_hipotekak = metodoak.eskatutakoHipotekak(langile, sukurtsal_izen);
				
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
				String[][] onartutako_hipotekak = metodoak.onartutakoHipotekak(langile, sukurtsal_izen);
				
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
				String[][] itxitako_hipotekak = metodoak.itxitakoHipotekak(langile, sukurtsal_izen);
				
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
				String[][] errefusatutako_hipotekak = metodoak.errefusatutakoHipotekak(langile, sukurtsal_izen);
				
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
		
		JScrollPane pane_hipotekak = new JScrollPane();
		pane_hipotekak.setBounds(127, 261, 686, 187);
		hipotekaTaulak.add(pane_hipotekak);
		
		hipotekak_table = new JTable();
		pane_hipotekak.setViewportView(hipotekak_table);
		
		JLabel lbl_hipoteka_mota = new JLabel("ESKATUTAKO HIPOTEKAK");
		lbl_hipoteka_mota.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbl_hipoteka_mota.setBounds(337, 205, 249, 45);
		hipotekaTaulak.add(lbl_hipoteka_mota);
		
		JButton btn_errefusatu = new JButton("Errefusatu");
		btn_errefusatu.setBounds(318, 478, 100, 34);
		hipotekaTaulak.add(btn_errefusatu);
		
		JButton btn_onartu = new JButton("Onartu");
		btn_onartu.setBounds(514, 478, 100, 34);
		hipotekaTaulak.add(btn_onartu);
		
		itxi_pane = new JScrollPane();
		itxi_pane.setBounds(102, 227, 712, 192);
		ixtekoKontuak.add(itxi_pane);
		
		JButton btn_itxi = new JButton("Itxi");
		btn_itxi.setBounds(390, 450, 100, 34);
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
	}
}
