package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.LibroCard;
import model.LibroCardDao;
import model.LibroCardDaoImpl;
import model.OrdineDao;
import model.OrdineDaoImpl;
import model.Utente;
import model.UtenteDao;
import model.UtenteDaoImpl;
import view.View;
import view.VisualizzaOrdini;


public class RegistrazioneListener implements ActionListener	{
	
	private View frame;
	
	public RegistrazioneListener(View frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField[] tfArray = frame.getTfArrayR();
		String nome = tfArray[0].getText();
		String cognome = tfArray[1].getText();
		String indirizzo = String.format("%s %s %s %s", tfArray[2].getText(), tfArray[3].getText(), tfArray[4].getText(), tfArray[5].getText());
		String telefono = tfArray[6].getText();
		String email = tfArray[7].getText();
		String password = tfArray[8].getText();
		
		//controlli
		for(int i = 0; i < tfArray.length; i++) {
			if(tfArray[i].getText().isEmpty())
				return;
		}
		if(!email.contains("@") || !email.contains("."))
			return;
		
		//salvataggio dati utente
		UtenteDao utenteDao = new UtenteDaoImpl();
		Utente utente = new Utente(nome, cognome, indirizzo, telefono, email, password);
		if(utenteDao.insertUtente(utente) == false)
			return;
		
		//salvataggio dati libro card
		LibroCard libroCardUtente = new LibroCard(Date.valueOf(LocalDate.now()), 0, email);
		LibroCardDao libroCardDao = new LibroCardDaoImpl();
		if(libroCardDao.insertLibroCard(libroCardUtente) == false)
			return;
		/*
		//visualizzo gli ordini dell'utente
		OrdineDao ordineDao = new OrdineDaoImpl();
		VisualizzaOrdini visualizzaOrdini = new VisualizzaOrdini(ordineDao.getAllOrders(email));
		
		//riferimenti ai card layout
		JPanel card = frame.getCard();
		CardLayout clC = (CardLayout)(card.getLayout());
		JPanel bottoni = frame.getBottoni();
		CardLayout clN = (CardLayout)(bottoni.getLayout());
		
		clN.show(bottoni, frame.getRegUserPanel());
		card.add(visualizzaOrdini, button.getText());
		clC.show(card, button.getText());
		
		frame.pack();
		*/
	}

}
