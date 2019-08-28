package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.OrdineDao;
import model.OrdineDaoImpl;
import view.NuovoOrdine;
import view.View;

public class OrdinaLibroListener implements ActionListener{
	private View frame;
	private final OrdineDao ordineDao = new OrdineDaoImpl();
	
	public OrdinaLibroListener(View frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*JTextField[] tfArray = frame.getTfArrayA();
		String email = tfArray[0].getText();
		String password = tfArray[1].getText();
		UtenteDao utenteDao = new UtenteDaoImpl();
		boolean login = utenteDao.login(email, password);
		if (login) {
			VisualizzaOrdine visualizzaOrdini = new VisualizzaOrdine(ordineDao.getAllOrders(email));
			JPanel card = frame.getCard();
			card.add(visualizzaOrdini, "Login");
			JButton button = (JButton)e.getSource();
			
			CardLayout cl = (CardLayout)(card.getLayout());
			cl.show(card, button.getText());
		}
		else {
			tfArray[1].setText(null);
		}*/
		JPanel card = frame.getCard();
		NuovoOrdine ordinaLibro = new NuovoOrdine();
		card.add(ordinaLibro, "Esegui un ordine");
		JButton button = (JButton)e.getSource();
		
		CardLayout cl = (CardLayout)(card.getLayout());
		cl.show(card, button.getText());
		
		
	}
}