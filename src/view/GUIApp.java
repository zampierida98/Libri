package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUIApp extends JFrame {
	
	/*autenticazione.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent arg0) {
			cl.show(card, autenticazione.getText());
		}
		
	});*/
	
	
	
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIApp frame = new GUIApp();
					frame.pack();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//contenitore card
	private static final JPanel card = new JPanel(new CardLayout());
	//bottoni cambia card
	private static final JPanel cambiaCard = new JPanel(new FlowLayout());
	private static final JButton areaRiservataB = new JButton("Area Riservata");
	private final static JButton nonRegistrati = new JButton("Non registrati");
	private final static JButton regUtente = new JButton("Registrazione");
	private final static JButton autenticazione = new JButton("Autenticazione");
	//card autenticazione
	private static final JPanel login = new JPanel(new GridLayout(3, 2));
	private static final JLabel email = new JLabel("E-mail: ");
	private static final JTextField compEmail = new JTextField();
	private static final JLabel pwd = new JLabel("Password: ");
	private static final JPasswordField compPwd = new JPasswordField();
	private static final JButton loginB = new JButton("Login");
	//card registrazione
	private static final JPanel registrazione = new JPanel(new GridLayout(7, 2));
	private static final JLabel nomeR = new JLabel("Nome:");
	private static final JTextField compNomeR = new JTextField();
	private static final JLabel cognomeR = new JLabel("Cognome:");
	private static final JTextField compCognomeR = new JTextField();
	private static final JLabel indirizzoR = new JLabel("Indirizzo:");
	private static final JTextField compIndirizzoR = new JTextField();
	private static final JLabel telefonoR = new JLabel("Telefono (+39):");
	private static final JTextField compTelefonoR = new JTextField();
	private static final JLabel emailR = new JLabel("E-mail:");
	private static final JTextField compEmailR = new JTextField();
	private static final JLabel pwdR = new JLabel("Password:");
	private static final JPasswordField compPwdR = new JPasswordField();
	private static final JButton registra = new JButton("Registrami");
	
	
	/**
	 * Create the frame.
	 */
	public GUIApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Container Principale
		cambiaCard.add(areaRiservataB);
		cambiaCard.add(nonRegistrati);
		cambiaCard.add(regUtente);
		cambiaCard.add(autenticazione);
		//Aggiunta Informazioni ai vari Panel
		//LOGIN
		login.add(email);
		login.add(compEmail);
		login.add(pwd);
		login.add(compPwd);
		login.add(loginB);
		
		//CARD LAYOUT
		registrazione.add(nomeR);
		registrazione.add(compNomeR);
		registrazione.add(cognomeR);
		registrazione.add(compCognomeR);
		registrazione.add(indirizzoR);
		registrazione.add(compIndirizzoR);
		registrazione.add(telefonoR);
		registrazione.add(compTelefonoR);
		registrazione.add(emailR);
		registrazione.add(compEmailR);
		registrazione.add(pwdR);
		registrazione.add(compPwdR);
		registrazione.add(registra);
		//aggiunta card registrazione e login
		card.add(registrazione, "Registrazione");
		card.add(login, "Autenticazione");
		
		this.getContentPane().add(cambiaCard, BorderLayout.NORTH);
		this.getContentPane().add(card, BorderLayout.CENTER);
		
		CardLayout cl = (CardLayout)(card.getLayout());
		cl.show(card, regUtente.getText());
		
		autenticazione.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(card, autenticazione.getText());
			}
			
		});
		
		regUtente.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				cl.show(card, regUtente.getText());
			}
			
		});
		
		
		
	}
	
}
