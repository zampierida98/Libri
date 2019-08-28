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
import view.VisualizzaOrdine;

public class AccediListener implements ActionListener{
	private View frame;
	private final OrdineDao ordineDao = new OrdineDaoImpl();
	
	public AccediListener(View frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField[] tfArray = frame.getTfArrayA();
		String email = tfArray[0].getText();
		String password = tfArray[1].getText();
		UtenteDao utenteDao = new UtenteDaoImpl();
		boolean login = utenteDao.login(email, password);
		if (login) {
			//se il login va a buon fine, visualizzo gli ordini dell'utente
			VisualizzaOrdine visualizzaOrdini = new VisualizzaOrdine(ordineDao.getAllOrders(email));
			
			JButton button = (JButton)e.getSource();
			
			JPanel card = frame.getCard();
			card.add(visualizzaOrdini, "Login");
			CardLayout clC = (CardLayout)(card.getLayout());
			clC.show(card, button.getText());
			
			JPanel bottoni = frame.getBottoni();
			CardLayout clN = (CardLayout)(bottoni.getLayout());
			clN.show(bottoni, button.getText());
		}
		else {
			tfArray[1].setText(null);
		}
		
		
	}
}
