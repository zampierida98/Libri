package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import model.Libro;
import model.LibroDao;
import model.LibroDaoImpl;
import view.InserisciLibro;

public class InviaLibroListener implements ActionListener {
	
	private InserisciLibro inserisciLibro;
	
	public InviaLibroListener(InserisciLibro inserisiciLibro) {
		this.inserisciLibro = inserisiciLibro;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		
		if(button.getText().equals("Inserisci libro")) {
			JTextField[] tfArray = inserisciLibro.getTfArrayLibro();
			
			String ISBN = tfArray[0].getText();
			String titolo = tfArray[1].getText();
			String autori = tfArray[2].getText();
			String casaEditrice = tfArray[3].getText();
			String genere = tfArray[5].getText();
			String descrizione = tfArray[7].getText();
			
			//controlli sulle stringhe
			for(int i = 0; i < tfArray.length; i++) {
				if(tfArray[i].getText().isEmpty()) {
					inserisciLibro.getResponse().setText("Inserimento NON effettuato");
					return;
				}
			}
			if(!ISBN.contains("-")) {
				inserisciLibro.getResponse().setText("Inserimento NON effettuato");
				return;
			}
			
			int annoPubblicazione;
			double prezzo;
			int punti;
			try {
				annoPubblicazione = Integer.valueOf(tfArray[4].getText());
				prezzo = Double.valueOf(tfArray[6].getText());
				punti = Integer.valueOf(tfArray[8].getText());
			} catch(NumberFormatException nfe) {
				inserisciLibro.getResponse().setText("Inserimento NON effettuato");
				return;
			}
			
			//controlli sui numeri
			if(annoPubblicazione < 0) {
				inserisciLibro.getResponse().setText("Inserimento NON effettuato");
				return;
			}
			
			//inserimento del libro nel database
			LibroDao libroDao = new LibroDaoImpl();
			Libro libro = new Libro(ISBN, titolo, autori, casaEditrice, annoPubblicazione, genere, prezzo, descrizione, punti);
			if(libroDao.insertBook(libro) == true)
				inserisciLibro.getResponse().setText("Inserimento effettuato");
			else
				inserisciLibro.getResponse().setText("Inserimento NON effettuato");
		}
	}

}
