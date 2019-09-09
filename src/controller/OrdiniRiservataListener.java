package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.UtenteDao;
import model.UtenteDaoImpl;
import view.InserisciLibro;
import view.OrdiniResponsabile;
import view.View;

public class OrdiniRiservataListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		JButton button = (JButton)e.getSource();

		JTextField[] tfArray = View.getInstance().getTfArrayAA();
		String username = tfArray[0].getText();
		String password = tfArray[1].getText();
		UtenteDao utenteDao = new UtenteDaoImpl();
		boolean login = utenteDao.login(username, password);

		if (login) {
			//se il login va a buon fine, visualizzo gli ordini dell'utente
			OrdiniResponsabile ordineResponsabile = new OrdiniResponsabile();
			OrdiniResponsabile ordineResponsabile2 = new OrdiniResponsabile();
			InserisciLibro inserisciLibro = new InserisciLibro();
			
			//... INSTANZIO TUTTE I POSSIBILI PANEL (CARD) PER LE OPERAZIONI E NE FACCIO VEDERE UNO

			//riferimenti ai card layout
			JPanel card = View.getInstance().getCard();
			card.add(ordineResponsabile, "Login area riservata");
			card.add(ordineResponsabile2, "Ordini");
			card.add(inserisciLibro, "Aggiungi libro");
			
			CardLayout clC = (CardLayout)(card.getLayout());
			JPanel bottoni = View.getInstance().getBottoni();
			CardLayout clN = (CardLayout)(bottoni.getLayout());

			clN.show(bottoni, button.getText());
			clC.show(card, button.getText());

			View.getInstance().pack();
		}
		else {
			tfArray[1].setText(null);
		}
	}

}
