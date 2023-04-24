package vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Metodoak;
import model.EntitateBankarioa;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
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
	EntitateBankarioa[] bankuak = new EntitateBankarioa[0];
	final ImageIcon homeicon = new ImageIcon(new ImageIcon("src/res/casa.png").getImage().getScaledInstance(44, 34,Image.SCALE_DEFAULT));
	final ImageIcon logo_atzera = new ImageIcon(new ImageIcon("src/res/flecha_atras.png").getImage().getScaledInstance(44,30,Image.SCALE_DEFAULT));
	final ImageIcon logo_aurrera = new ImageIcon(new ImageIcon("src/res/flecha_alante.png").getImage().getScaledInstance(44,30,Image.SCALE_DEFAULT));
	final ImageIcon fondo_argazki = new ImageIcon(new ImageIcon("src/res/logo2.2.png").getImage().getScaledInstance(932,130,Image.SCALE_DEFAULT));
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
		setIconImage(Toolkit.getDefaultToolkit().getImage(App.class.getResource("/res/banku_ikono.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 958, 603);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
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
				loginBezero.setVisible(false);
				bezeroEntitate.setVisible(true);
			}
		});
		btn_bezero_sartu.setBounds(437, 423, 89, 23);
		loginBezero.add(btn_bezero_sartu);
		
		bankuak = metodoak.botoiakSortu();
		for(int j=0;j<bankuak.length;j++) {		
			String[] limiteak = bankuak[j].getBounds().split("/");
			ImageIcon logo_banco = new ImageIcon(new ImageIcon(bankuak[j].getUrl()).getImage().getScaledInstance(Integer.parseInt(limiteak[2]),Integer.parseInt(limiteak[3]),Image.SCALE_DEFAULT));
			JButton btn_banco = new JButton(logo_banco);
			btn_banco.setToolTipText(String.valueOf(j+1));


			btn_banco.setBounds(Integer.parseInt(limiteak[0]), Integer.parseInt(limiteak[1]), Integer.parseInt(limiteak[2]), Integer.parseInt(limiteak[3]));
			bezeroEntitate.add(btn_banco);
			btn_banco.setOpaque(false);
			btn_banco.setContentAreaFilled(false);
			btn_banco.setBorderPainted(false);
		}
		
		JButton btn_langile_sartu = new JButton("Sartu");
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
		//////////////////////////////////
		// 			Aurrera Atzera 		//
		//////////////////////////////////
		JButton btn_atzera_1 = new JButton(logo_atzera);
		btn_atzera_1.setBounds(15, 513, 44, 30);
		btn_atzera_1.setOpaque(false);
		btn_atzera_1.setContentAreaFilled(false);
		btn_atzera_1.setBorderPainted(false);
		entitateKontuak.add(btn_atzera_1);
		
		JButton btn_aurrera_1 = new JButton(logo_aurrera);
		btn_aurrera_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bezeroKontua.setVisible(true);
				entitateKontuak.setVisible(false);
			}
		});
		btn_aurrera_1.setBounds(873, 513, 44, 30);
		btn_aurrera_1.setOpaque(false);
		btn_aurrera_1.setContentAreaFilled(false);
		btn_aurrera_1.setBorderPainted(false);
		entitateKontuak.add(btn_aurrera_1);		

		JButton btn_atzera_2 = new JButton(logo_atzera);
		btn_atzera_2.setOpaque(false);
		btn_atzera_2.setContentAreaFilled(false);
		btn_atzera_2.setBorderPainted(false);
		btn_atzera_2.setBounds(15, 513, 44, 30);
		bezeroKontua.add(btn_atzera_2);
		
		JButton btn_atzera_3 = new JButton(logo_atzera);
		btn_atzera_3.setOpaque(false);
		btn_atzera_3.setContentAreaFilled(false);
		btn_atzera_3.setBorderPainted(false);
		btn_atzera_3.setBounds(15, 513, 44, 30);
		transferentzia.add(btn_atzera_3);
		
		JButton btn_atzera_4 = new JButton(logo_atzera);
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
		btn_atzera_7.setOpaque(false);
		btn_atzera_7.setContentAreaFilled(false);
		btn_atzera_7.setBorderPainted(false);
		btn_atzera_7.setBounds(15, 513, 44, 30);
		hipotekaEskatu.add(btn_atzera_7);
		
		JButton btn_atzera_8 = new JButton(logo_atzera);
		btn_atzera_8.setOpaque(false);
		btn_atzera_8.setContentAreaFilled(false);
		btn_atzera_8.setBorderPainted(false);
		btn_atzera_8.setBounds(15, 513, 44, 30);
		hipotekaEstatus.add(btn_atzera_8);
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
		
		JLabel lbl_hipo_total = new JLabel("Hipoteka Totala:");
		lbl_hipo_total.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hipo_total.setBounds(369, 198, 112, 30);
		hipotekaEstatus.add(lbl_hipo_total);
		
		JLabel lbl_hasiera = new JLabel("Hasiera Data:");
		lbl_hasiera.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hasiera.setBounds(369, 243, 112, 30);
		hipotekaEstatus.add(lbl_hasiera);
		
		JLabel lbl_hipo_amaiera = new JLabel("Amaiera Data:");
		lbl_hipo_amaiera.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hipo_amaiera.setBounds(369, 284, 112, 30);
		hipotekaEstatus.add(lbl_hipo_amaiera);
		
		JLabel lbl_komisioa_2 = new JLabel("Komisioa: ");
		lbl_komisioa_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_komisioa_2.setBounds(369, 329, 112, 30);
		hipotekaEstatus.add(lbl_komisioa_2);
		
		JButton btn_ordaindu = new JButton("Ordaindu");
		btn_ordaindu.setBounds(392, 427, 153, 34);
		hipotekaEstatus.add(btn_ordaindu);
		
		JLabel lbl_ordainduta = new JLabel("Ordainduta:");
		lbl_ordainduta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_ordainduta.setBounds(369, 370, 112, 30);
		hipotekaEstatus.add(lbl_ordainduta);
		
		JLabel lbl_hipoteka_total = new JLabel("totala");
		lbl_hipoteka_total.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hipoteka_total.setBounds(469, 198, 112, 30);
		hipotekaEstatus.add(lbl_hipoteka_total);
		
		JLabel lbl_hipoteka_hasiera = new JLabel("hasiera data");
		lbl_hipoteka_hasiera.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hipoteka_hasiera.setBounds(469, 243, 112, 30);
		hipotekaEstatus.add(lbl_hipoteka_hasiera);
		
		JLabel lbl_hipoteka_amaiera = new JLabel("amaiera data");
		lbl_hipoteka_amaiera.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hipoteka_amaiera.setBounds(469, 284, 112, 30);
		hipotekaEstatus.add(lbl_hipoteka_amaiera);
		
		JLabel lbl_hipoteka_komisio_ikusi = new JLabel("komisioa");
		lbl_hipoteka_komisio_ikusi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hipoteka_komisio_ikusi.setBounds(469, 329, 112, 30);
		hipotekaEstatus.add(lbl_hipoteka_komisio_ikusi);
		
		JLabel lbl_hipoteka_ordainduta = new JLabel("ordainduta");
		lbl_hipoteka_ordainduta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_hipoteka_ordainduta.setBounds(469, 370, 112, 30);
		hipotekaEstatus.add(lbl_hipoteka_ordainduta);
		
		JButton btn_print_2 = new JButton("Imprimatu");
		btn_print_2.setBounds(410, 502, 107, 30);
		sarrerak.add(btn_print_2);
		
		JScrollPane sarrerak_panel = new JScrollPane();
		sarrerak_panel.setBounds(114, 242, 712, 232);
		sarrerak.add(sarrerak_panel);
		
		sarrerak_table = new JTable();
		sarrerak_panel.setViewportView(sarrerak_table);
		
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
		btn_transferitu.setBounds(393, 446, 153, 34);
		transferentziaEgin.add(btn_transferitu);
		
		JLabel lbl_segurtasun = new JLabel("Segurtasun Kodea:");
		lbl_segurtasun.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_segurtasun.setBounds(290, 397, 112, 30);
		transferentziaEgin.add(lbl_segurtasun);
		
		transferentzia_segurtasun = new JPasswordField();
		transferentzia_segurtasun.setBounds(412, 393, 112, 34);
		transferentziaEgin.add(transferentzia_segurtasun);
		
		JScrollPane transfer_panel = new JScrollPane();
		transfer_panel.setBounds(114, 242, 712, 232);
		transferentziaIkusi.add(transfer_panel);
		
		transfer_ikusi_table = new JTable();
		transfer_panel.setViewportView(transfer_ikusi_table);
		
		JButton btn_print_1 = new JButton("Imprimatu");
		btn_print_1.setBounds(410, 502, 107, 30);
		transferentziaIkusi.add(btn_print_1);
		
		JButton btn_transfer_ikusi = new JButton("Transferentziak Ikusi");
		btn_transfer_ikusi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
				bezeroKontua.setVisible(false);
				hipotekaEskatu.setVisible(true);
			}
		});
		btn_hipoteka.setBounds(276, 354, 115, 34);
		bezeroKontua.add(btn_hipoteka);
		
		JButton btn_mugimendu = new JButton("Mugimenduak");
		btn_mugimendu.setBounds(537, 354, 115, 34);
		bezeroKontua.add(btn_mugimendu);
		
		JButton btn_kontuItxi = new JButton("Kontua Itxi");
		btn_kontuItxi.setBounds(409, 425, 115, 34);
		bezeroKontua.add(btn_kontuItxi);
		btn_kontuItxi.setBackground(Color.LIGHT_GRAY);
		
		JScrollPane scrollPane_entitateKont = new JScrollPane();
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
				hipotekaEstatus.setVisible(true);
				hipotekaEskatu.setVisible(false);
			}
		});
		btn_eskatu.setBounds(408, 435, 107, 30);
		hipotekaEskatu.add(btn_eskatu);
		
		JLabel lbl_kantitate_hipoteka = new JLabel("Kantitatea:");
		lbl_kantitate_hipoteka.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_kantitate_hipoteka.setBounds(297, 246, 112, 30);
		hipotekaEskatu.add(lbl_kantitate_hipoteka);
		
		txt_kantitate_hipoteka = new JTextField();
		txt_kantitate_hipoteka.setColumns(10);
		txt_kantitate_hipoteka.setBounds(379, 245, 202, 34);
		hipotekaEskatu.add(txt_kantitate_hipoteka);
		
		JLabel lbl_urteak = new JLabel("Amortizazio Epea:");
		lbl_urteak.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_urteak.setBounds(297, 291, 112, 30);
		hipotekaEskatu.add(lbl_urteak);
		
		JLabel lbl_komisio_1 = new JLabel("Komisioa: ");
		lbl_komisio_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_komisio_1.setBounds(297, 336, 112, 30);
		hipotekaEskatu.add(lbl_komisio_1);
		
		txt_hipoteka_komisio = new JTextField("");
		txt_hipoteka_komisio.setEditable(false);
		txt_hipoteka_komisio.setColumns(10);
		txt_hipoteka_komisio.setBounds(419, 335, 57, 34);
		hipotekaEskatu.add(txt_hipoteka_komisio);
		
		JComboBox hipoteka_combo = new JComboBox();
		hipoteka_combo.setBounds(419, 290, 94, 34);
		hipotekaEskatu.add(hipoteka_combo);
		
		JLabel lbl_segurtasun_2 = new JLabel("Segurtasun Kodea:");
		lbl_segurtasun_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lbl_segurtasun_2.setBounds(297, 381, 112, 30);
		hipotekaEskatu.add(lbl_segurtasun_2);
		
		hipoteka_segurtasun = new JPasswordField();
		hipoteka_segurtasun.setBounds(419, 377, 112, 34);
		hipotekaEskatu.add(hipoteka_segurtasun);	
	}
}
