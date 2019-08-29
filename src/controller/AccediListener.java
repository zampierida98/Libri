package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.OrdineDao;
import model.OrdineDaoImpl;
import model.UtenteDao;
import model.UtenteDaoImpl;
import view.View;
import view.VisualizzaOrdini;

public class AccediListener implements ActionListener {
	
	private View frame;
	
	public AccediListener(View frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		
		JTextField[] tfArray = frame.getTfArrayA();
		String email = tfArray[0].getText();
		String password = tfArray[1].getText();
		UtenteDao utenteDao = new UtenteDaoImpl();
		boolean login = utenteDao.login(email, password);
		
		if (login) {
			//se il login va a buon fine, visualizzo gli ordini dell'utente
			OrdineDao ordineDao = new OrdineDaoImpl();
			VisualizzaOrdini visualizzaOrdini = new VisualizzaOrdini(ordineDao.getAllOrders(email));
			//... INSTANZIO TUTTE I POSSIBILI PANEL (CARD) PER LE OPERAZIONI E NE FACCIO VEDERE UNO
			
			//riferimenti ai card layout
			JPanel card = frame.getCard();
			CardLayout clC = (CardLayout)(card.getLayout());
			JPanel bottoni = frame.getBottoni();
			CardLayout clN = (CardLayout)(bottoni.getLayout());
			
			clN.show(bottoni, frame.getRegUserPanel());
			card.add(visualizzaOrdini, button.getText());
			clC.show(card, button.getText());
			
			frame.pack();
		}
		else {
			tfArray[1].setText(null);
		}
		
		
	}
}
