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
import controller.OrdinaLibroInterfacciaListener;
import controller.RegistrazioneListener;

public class View extends JFrame {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View frame = new View();
					
					frame.pack();
					frame.setResizable(false);
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	//card layout
	private static final JPanel card = new JPanel(new CardLayout());
	private static final JPanel bottoni = new JPanel(new CardLayout());
	private static final String DEFAULT_NORTH_PANEL = "Default";
	private static final String REG_USER_PANEL = "Registrati";
	private static final String REGISTRATION_PANEL = "Registrazione";
	
	
	//bottoni cambia card (pagina iniziale)
	private static final JPanel cambiaCard = new JPanel(new FlowLayout());
	private static final JButton areaRiservata = new JButton("Area Riservata");
	private static final JButton nonRegistrati = new JButton("Non registrati");
	private static final JButton regUtente = new JButton("Registrazione");
	private static final JButton autenticazione = new JButton("Autenticazione");
	
	//bottoni cambia card (utente registrato)
	private static final JPanel operazioniUtenteR = new JPanel(new FlowLayout());
	private static final JButton visualizzaProfiloB = new JButton("Visualizza profilo");
	private static final JButton modificaProfiloB = new JButton("Modifica profilo");
	private static final JButton visualizzaOrdiniB = new JButton("Visualizza ordini");
	private static final JButton esciUtenteR = new JButton("Esci");
	
	//bottoni cambia card (utente non registrato)
	private static final JPanel operazioniUtenteNonR = new JPanel(new FlowLayout());
	private static final JButton ordineB = new JButton("Fai un ordine");
	private static final JButton codiceOrdineB = new JButton("Visualizza un ordine");
	private static final JButton esciUtenteNonR = new JButton("Esci");
	
	
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
	private static final JButton registraB = new JButton("Registrami");
	private static final JTextField[] tfArrayR = {compNomeR, compCognomeR, compIndirizzoR, compCapR, compCittaR, compProvR, compTelefonoR, compEmailR, compPwdR};
	
	//card fai un ordine (utente non registrato)
	//... COPIA DEI CAMPI DELLA REGISTRAZIONE (POI PROSEGUE SU UN NUOVO PANEL PER I LIBRI)
	
	//card codice ordine
	private static final JPanel utentiNonRegistrati = new JPanel(new GridLayout(2, 2));
	private static final JLabel codiceOrdine = new JLabel("Inserisci codice ordine:");
	private static final JTextField compCodiceOrdine = new JTextField();
	private static final JButton accediOrdineB = new JButton("Accedi all'ordine");
	
	
	public JPanel getCard() {
		return card;
	}
	
	public JPanel getBottoni() {
		return bottoni;
	}
	
	public String getDefaultNorthPanel() {
		return DEFAULT_NORTH_PANEL;
	}
	
	public String getRegUserPanel() {
		return REG_USER_PANEL;
	}
	
	public String getRegistrationPanel() {
		return REGISTRATION_PANEL;
	}

	public JTextField[] getTfArrayA() {
		return tfArrayA;
	}

	public JTextField[] getTfArrayR() {
		return tfArrayR;
	}
	
	/*
	public void setPassword(String cambiaTesto) {
		this.pwdR.setText(cambiaTesto);
	}
	
	public void setPasswordField(char cambiaTesto) {
		this.compPwdR.setEchoChar(cambiaTesto);
	}
	
	public JLabel getPassword() {
		return this.pwdR;
	}
	
	public JPasswordField getPasswordField() {
		return this.compPwdR;
	}
	
	public JButton getButtonRegistrazione() {
		return registraB;
	}
	*/
	
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//aggiunta informazioni ai vari panel:
		// - north
		cambiaCard.add(areaRiservata);
		cambiaCard.add(nonRegistrati);
		cambiaCard.add(regUtente);
		cambiaCard.add(autenticazione);
		operazioniUtenteR.add(visualizzaProfiloB);
		operazioniUtenteR.add(modificaProfiloB);
		operazioniUtenteR.add(visualizzaOrdiniB);
		operazioniUtenteR.add(esciUtenteR);
		operazioniUtenteNonR.add(ordineB);
		operazioniUtenteNonR.add(codiceOrdineB);
		operazioniUtenteNonR.add(esciUtenteNonR);
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
		registrazione.add(registraB);
		// - center (codice ordine)
		utentiNonRegistrati.add(codiceOrdine);
		utentiNonRegistrati.add(compCodiceOrdine);
		utentiNonRegistrati.add(accediOrdineB);
		
		//aggiunta delle card al card layout:
		// - north
		bottoni.add(cambiaCard, DEFAULT_NORTH_PANEL);
		bottoni.add(operazioniUtenteR, REG_USER_PANEL);
		bottoni.add(operazioniUtenteNonR, "Non registrati");
		// - center
		card.add(registrazione, REGISTRATION_PANEL);
		card.add(login, "Autenticazione");
		card.add(utentiNonRegistrati, "Non registrati");
		
		//listener:
		// - pagina iniziale
		nonRegistrati.addActionListener(new ChangeCardListener(this));
		areaRiservata.addActionListener(new ChangeCardListener(this));
		regUtente.addActionListener(new ChangeCardListener(this));
		autenticazione.addActionListener(new ChangeCardListener(this));
		registraB.addActionListener(new RegistrazioneListener(this));
		loginB.addActionListener(new AccediListener(this));
		// - utente registrato
		visualizzaOrdiniB.addActionListener(new ChangeCardListener(this));
		esciUtenteR.addActionListener(new ChangeCardListener(this));
		// - utente non registrato
		ordineB.addActionListener(new ChangeCardListener(this));
		codiceOrdineB.addActionListener(new ChangeCardListener(this));
		esciUtenteNonR.addActionListener(new ChangeCardListener(this));
		
		
		//visualizzazione pannelli
		this.getContentPane().add(bottoni, BorderLayout.NORTH);
		this.getContentPane().add(card, BorderLayout.CENTER);
		CardLayout clC = (CardLayout)(card.getLayout());
		clC.show(card, regUtente.getText());
		CardLayout clN = (CardLayout)(bottoni.getLayout());
		clN.show(bottoni, "default");
	}
	
}
