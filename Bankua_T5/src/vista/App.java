package vista;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlagailua.Metodoak;
import model.EntitateBankarioa;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.Icon;
import javax.swing.JScrollPane;

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
	ImageIcon homeicon = new ImageIcon(new ImageIcon("src/res/casa.png").getImage().getScaledInstance(44, 34,Image.SCALE_DEFAULT));
	ImageIcon logo_atzera = new ImageIcon(new ImageIcon("src/res/flecha_atras.png").getImage().getScaledInstance(44,30,Image.SCALE_DEFAULT));
	ImageIcon logo_aurrera = new ImageIcon(new ImageIcon("src/res/flecha_alante.png").getImage().getScaledInstance(44,30,Image.SCALE_DEFAULT));
	ImageIcon fondo_argazki = new ImageIcon(new ImageIcon("src/res/logo2.2.png").getImage().getScaledInstance(932,130,Image.SCALE_DEFAULT));
	private JTable table_entitateKont;
	
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
			ImageIcon logo_banco = new ImageIcon(new ImageIcon(bankuak[j].getUrl()).getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT));
			JButton btn_banco = new JButton(logo_banco);
			btn_banco.setToolTipText(String.valueOf(j+1));


			String[] limiteak = bankuak[j].getBounds().split("/");
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
		
		JButton btn_etxea_2 = new JButton((Icon) null);
		btn_etxea_2.setBounds(29, 151, 44, 34);
		btn_etxea_2.setOpaque(false);
		btn_etxea_2.setContentAreaFilled(false);
		btn_etxea_2.setBorderPainted(false);
		entitateKontuak.add(btn_etxea_2);		
		

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
		btn_aurrera_1.setBounds(873, 513, 44, 30);
		btn_aurrera_1.setOpaque(false);
		btn_aurrera_1.setContentAreaFilled(false);
		btn_aurrera_1.setBorderPainted(false);
		entitateKontuak.add(btn_aurrera_1);

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
		
		JPanel bezeroKontua = new JPanel();
		contentPane.add(bezeroKontua, "name_248283919860200");
		
		
	}
}
