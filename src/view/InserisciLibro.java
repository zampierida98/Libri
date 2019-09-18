package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.InviaLibroListener;

/**
 * Contiene l'interfaccia per permettere ai responsabili della libreria di inserire i dati relativi ai libri.
 */
public class InserisciLibro extends JPanel{

	private static final JLabel ISBN = new JLabel("Inserisci codice ISBN:");
	private static final JLabel titolo = new JLabel("Inserisci titolo:");
	private static final JLabel autori = new JLabel("Inserisci autori:");
	private static final JLabel casaEditrice = new JLabel("Inserisci casa editrice:");
	private static final JLabel annoPubblicazione = new JLabel("Inserisci anno di pubblicazione:");
	private static final JLabel genere = new JLabel("Inserisci genere:");
	private static final JLabel prezzo = new JLabel("Inserisci prezzo:");
	private static final JLabel descrizione = new JLabel("Inserisci descrizione:");
	private static final JLabel punti = new JLabel("Inserisci punti associati:");
	private static final JTextField campoISBN = new JTextField();
	private static final JTextField campoTitolo = new JTextField();
	private static final JTextField campoAutori = new JTextField();
	private static final JTextField campoCasaEditrice = new JTextField();
	private static final JTextField campoAnnoPubblicazione = new JTextField();
	private static final JTextField campoGenere = new JTextField();
	private static final JTextField campoPrezzo = new JTextField();
	private static final JTextField campoDescrizione = new JTextField();
	private static final JTextField campoPunti = new JTextField();
	private JButton inviaLibro = new JButton("Inserisci libro");
	private JLabel response = new JLabel("", SwingConstants.CENTER);
	private static final JTextField[] tfArrayLibro = {campoISBN, campoTitolo, campoAutori, campoCasaEditrice, campoAnnoPubblicazione, campoGenere, campoPrezzo, campoDescrizione, campoPunti};

	public JTextField[] getTfArrayLibro() {
		return tfArrayLibro;
	}
	
	public JLabel getResponse() {
		return response;
	}

	public InserisciLibro() {
		this.setLayout(new GridLayout(10, 2));
		this.add(ISBN); 
		this.add(campoISBN);
		this.add(titolo);
		this.add(campoTitolo);
		this.add(autori);
		this.add(campoAutori);
		this.add(casaEditrice);
		this.add(campoCasaEditrice);
		this.add(annoPubblicazione);
		this.add(campoAnnoPubblicazione);
		this.add(genere);
		this.add(campoGenere);
		this.add(prezzo);
		this.add(campoPrezzo);
		this.add(descrizione);
		this.add(campoDescrizione);
		this.add(punti);
		this.add(campoPunti);
		this.add(inviaLibro);
		this.add(response);

		inviaLibro.addActionListener(new InviaLibroListener(this));
	}
	
}
