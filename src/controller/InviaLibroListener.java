package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Libro;
import model.LibroDao;
import model.LibroDaoImpl;
import view.InserisciLibro;

public class InviaLibroListener implements ActionListener{
	private InserisciLibro inserisciLibro;
	
	public InviaLibroListener(InserisciLibro inserisiciLibro) {
		this.inserisciLibro = inserisiciLibro;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton inviaLibro = (JButton)e.getSource();
		if(inviaLibro.getText().equals("Invia libro")) {
			LibroDao libroDao = new LibroDaoImpl();
			String ISBN = inserisciLibro.getCampoISBN().getText();
			String titolo = inserisciLibro.getCampoTitolo().getText();
			String autori = inserisciLibro.getCampoAutori().getText();
			String casaEditrice = inserisciLibro.getCampoCasaEditrice().getText();
			int annoPubblicazione = Integer.valueOf(inserisciLibro.getCampoAnnoPubblicazione().getText());
			String genere = inserisciLibro.getCampoGenere().getText();
			double prezzo = Double.valueOf(inserisciLibro.getCampoPrezzo().getText());
			String descrizione = inserisciLibro.getCampoDescrizione().getText();
			int punti = Integer.valueOf(inserisciLibro.getCampoPunti().getText());
			
			Libro libro = new Libro(ISBN, titolo, autori, casaEditrice, annoPubblicazione, genere, prezzo, descrizione, punti);
			if(libroDao.insertBook(libro))
				System.out.println("Ordine inserito");
			else
				System.out.println("Ordine NON inserito");
		}
	}

}
