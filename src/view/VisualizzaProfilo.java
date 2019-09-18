package view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.LibroCardDao;
import model.LibroCardDaoImpl;
import model.Utente;

/**
 * Contiene i dati del profilo e della LibroCard di un utente registrato.
 */
public class VisualizzaProfilo extends JPanel {

	private static String email;
	private static String indirizzo;
		
	public static String getEmail() {
		return email;
	}
	
	public static String getIndirizzo() {
		return indirizzo;
	}
	
	public VisualizzaProfilo(Utente utente) {
		this.setLayout(new GridLayout(6,2));
		
		this.add(new JLabel("Nome:"));
		this.add(new JLabel(utente.getNome()));
		
		this.add(new JLabel("Cognome:"));
		this.add(new JLabel(utente.getCognome()));
		
		this.add(new JLabel("Indirizzo:"));
		indirizzo = utente.getIndirizzo();
		this.add(new JLabel(indirizzo));
		
		this.add(new JLabel("Telefono:"));
		this.add(new JLabel(utente.getTelefono()));
		
		this.add(new JLabel("E-mail:"));
		email = utente.getEmail();
		this.add(new JLabel(email));
		
		this.add(new JLabel("Saldo punti:"));
		LibroCardDao libroCard = new LibroCardDaoImpl();
		String saldoPunti = String.valueOf(libroCard.getLibroCard(email).getSaldoPunti());
		this.add(new JLabel(saldoPunti));
	}

}
