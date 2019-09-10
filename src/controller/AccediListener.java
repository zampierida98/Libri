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
import view.ModificaProfilo;
import view.View;
import view.VisualizzaOrdini;
import view.VisualizzaProfilo;

public class AccediListener implements ActionListener {
	
	private View frame;
	
	public AccediListener(View frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		UtenteDao utenteDao = new UtenteDaoImpl();
		OrdineDao ordineDao = new OrdineDaoImpl();
		
		if(button.getText().equals("Login") || button.getText().equals("Registrami")) {
			//accesso dalla schermata di autenticazione o dopo la registrazione
			
			JTextField[] tfArray = frame.getTfArrayA();
			String email = tfArray[0].getText();
			String password = tfArray[1].getText();
			boolean login = utenteDao.login(email, password);
			
			if(login) {
				VisualizzaProfilo visualizzaProfilo = new VisualizzaProfilo(utenteDao.getUser(email));
				VisualizzaProfilo visualizzaProfilo2 = new VisualizzaProfilo(utenteDao.getUser(email));
				ModificaProfilo modificaProfilo = new ModificaProfilo(utenteDao.getUser(email));
				VisualizzaOrdini visualizzaOrdini = new VisualizzaOrdini(ordineDao.getAllOrders(email));

				JPanel card = frame.getCard();
				CardLayout clC = (CardLayout)(card.getLayout());
				JPanel bottoni = frame.getBottoni();
				CardLayout clN = (CardLayout)(bottoni.getLayout());
				
				//mostro le operazioni per l'utente registrato
				clN.show(bottoni, frame.getRegUserPanel());
				
				//aggiungo le card di tutte le operazioni
				card.add(visualizzaProfilo, "Visualizza profilo");
				card.add(modificaProfilo, "Modifica profilo");
				card.add(visualizzaOrdini, "Visualizza ordini");
				
				//mostro la card di default
				card.add(visualizzaProfilo2, button.getText());
				clC.show(card, button.getText());
	
				frame.pack();
			} else {
				tfArray[1].setText(null);
			}
		}
	}
	
}
