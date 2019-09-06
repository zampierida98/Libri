package view;

import java.awt.CardLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.InviaLibroListener;

public class InserisciLibro extends JPanel{

	JLabel ISBN = new JLabel("Inserisci ISBN: ");
	JLabel titolo = new JLabel("Inserisci titolo: ");
	JLabel autori = new JLabel("Inserisci autori: ");
	JLabel casaEditrice = new JLabel("Inserisci casa editrice: ");
	JLabel annoPubblicazione = new JLabel("Inserisci anno pubblicazione: ");
	JLabel genere = new JLabel("Inserisci genere: ");
	JLabel prezzo = new JLabel("Inserisci prezzo: ");
	JLabel descrizione = new JLabel("Inserisci descrizione: ");
	JLabel punti = new JLabel("Inserisci punti associati: ");
	JTextField campoISBN = new JTextField();
	JTextField campoTitolo = new JTextField();
	JTextField campoAutori = new JTextField();
	JTextField campoCasaEditrice = new JTextField();
	JTextField campoAnnoPubblicazione = new JTextField();
	JTextField campoGenere = new JTextField();
	JTextField campoPrezzo = new JTextField();
	JTextField campoDescrizione = new JTextField();
	JTextField campoPunti = new JTextField();
	JButton inviaLibro = new JButton("Invia libro");





	public JTextField getCampoAutori() {
		return campoAutori;
	}





	public void setCampoAutori(JTextField campoAutori) {
		this.campoAutori = campoAutori;
	}





	public JTextField getCampoAnnoPubblicazione() {
		return campoAnnoPubblicazione;
	}





	public void setCampoAnnoPubblicazione(JTextField campoAnnoPubblicazione) {
		this.campoAnnoPubblicazione = campoAnnoPubblicazione;
	}





	public JTextField getCampoISBN() {
		return campoISBN;
	}





	public JTextField getCampoTitolo() {
		return campoTitolo;
	}





	public JTextField getCampoCasaEditrice() {
		return campoCasaEditrice;
	}





	public JTextField getCampoGenere() {
		return campoGenere;
	}





	public JTextField getCampoPrezzo() {
		return campoPrezzo;
	}





	public JTextField getCampoDescrizione() {
		return campoDescrizione;
	}





	public JTextField getCampoPunti() {
		return campoPunti;
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

		inviaLibro.addActionListener(new InviaLibroListener(this));

	}
}
