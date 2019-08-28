package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.AccediListener;
import controller.ChangeCardListener;
import controller.RegistrazioneListener;

public class View extends JFrame {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
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
	private static final JTextField[] tfArrayA = {compEmail, compPwd};
	
	//card registrazione
	private static final JPanel registrazione = new JPanel(new GridLayout(5, 4));
	private static final JLabel nomeR = new JLabel("Nome:");
	private static final JTextField compNomeR = new JTextField();
	private static final JLabel cognomeR = new JLabel("Cognome:");
	private static final JTextField compCognomeR = new JTextField();
	private static final JLabel indirizzoR = new JLabel("Indirizzo:");
	private static final JTextField compIndirizzoR = new JTextField();
	private static final JLabel capR = new JLabel("CAP:");
	private static final JTextField compCapR = new JTextField();
	private static final JLabel cittaR = new JLabel("Città:");
	private static final JTextField compCittaR = new JTextField();
	private static final JLabel provR = new JLabel("Provincia:");
	private static final JTextField compProvR = new JTextField();
	private static final JLabel telefonoR = new JLabel("Telefono:");
	private static final JTextField compTelefonoR = new JTextField();
	private static final JLabel emailR = new JLabel("E-mail:");
	private static final JTextField compEmailR = new JTextField();
	private static final JLabel pwdR = new JLabel("Password:");
	private static final JPasswordField compPwdR = new JPasswordField();
	private static final JButton registra = new JButton("Registrami");
	private static final JTextField[] tfArrayR = {compNomeR, compCognomeR, compIndirizzoR, compCapR, compCittaR, compProvR, compTelefonoR, compEmailR, compPwdR};
	
	//card non registrati
	private static final JPanel utentiNonRegistrati = new JPanel(new GridLayout(2, 2));
	private static final JLabel visualizzaOrdine = new JLabel("Visualizza ordine: ");
	private static final JTextField inserisciVisualizzaOrdine = new JTextField();
	private static final JButton ordina = new JButton("Esegui un ordine");
	
	
	public JPanel getCard() {
		return card;
	}

	public JTextField[] getTfArrayA() {
		return tfArrayA;
	}

	public JTextField[] getTfArrayR() {
		return tfArrayR;
	}
	

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//aggiunta informazioni ai vari panel:
		// - north
		cambiaCard.add(areaRiservataB);
		cambiaCard.add(nonRegistrati);
		cambiaCard.add(regUtente);
		cambiaCard.add(autenticazione);
		// - center (login)
		login.add(email);
		login.add(compEmail);
		login.add(pwd);
		login.add(compPwd);
		login.add(loginB);
		// - center (registrazione)
		registrazione.add(nomeR);
		registrazione.add(compNomeR);
		registrazione.add(cognomeR);
		registrazione.add(compCognomeR);
		registrazione.add(indirizzoR);
		registrazione.add(compIndirizzoR);
		registrazione.add(capR);
		registrazione.add(compCapR);
		registrazione.add(cittaR);
		registrazione.add(compCittaR);
		registrazione.add(provR);
		registrazione.add(compProvR);
		registrazione.add(telefonoR);
		registrazione.add(compTelefonoR);
		registrazione.add(emailR);
		registrazione.add(compEmailR);
		registrazione.add(pwdR);
		registrazione.add(compPwdR);
		registrazione.add(registra);
		// - center (non registrati)
		utentiNonRegistrati.add(visualizzaOrdine);
		utentiNonRegistrati.add(inserisciVisualizzaOrdine);
		utentiNonRegistrati.add(ordina);
		// - center (visualizza ordini)
		
		
		
		
		card.add(registrazione, "Registrazione");
		card.add(login, "Autenticazione");
		card.add(utentiNonRegistrati, "Non registrati");
		
		
		this.getContentPane().add(cambiaCard, BorderLayout.NORTH);
		this.getContentPane().add(card, BorderLayout.CENTER);
		
		CardLayout cl = (CardLayout)(card.getLayout());
		cl.show(card, regUtente.getText());
		
		//listener cambia card
		autenticazione.addActionListener(new ChangeCardListener(this));
		regUtente.addActionListener(new ChangeCardListener(this));
		nonRegistrati.addActionListener(new ChangeCardListener(this));
		
		//listener azioni bottoni
		registra.addActionListener(new RegistrazioneListener(this));
		loginB.addActionListener(new AccediListener(this));
		
	}
	
}
