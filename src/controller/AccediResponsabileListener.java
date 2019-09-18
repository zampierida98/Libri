package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.UtenteDao;
import model.UtenteDaoImpl;
import view.ClassificheResponsabile;
import view.InserisciLibro;
import view.LibroCardResponsabile;
import view.OrdiniResponsabile;
import view.View;

/**
 * Esegue l'accesso controllando che username e password corrispondano a quelli di un responsabile della libreria.
 */
public class AccediResponsabileListener implements ActionListener {
	
	private View frame;
	
	public AccediResponsabileListener(View frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {	
		JButton button = (JButton)e.getSource();
		UtenteDao utenteDao = new UtenteDaoImpl();

		if(button.getText().equals("Login area riservata")) {
			JTextField[] tfArray = View.getInstance().getTfArrayAR();
			String username = tfArray[0].getText();
			String password = tfArray[1].getText();
			
			//controllo
			if(username.contains("@") || username.contains(".")) {
				tfArray[0].setText(null);
				tfArray[1].setText(null);
				return;
			}
			
			boolean login = utenteDao.login(username, password);
			if(login) {
				OrdiniResponsabile ordineResponsabile = new OrdiniResponsabile();
				OrdiniResponsabile ordineResponsabile2 = new OrdiniResponsabile();
				LibroCardResponsabile libroCardRiservata = new LibroCardResponsabile();
				InserisciLibro inserisciLibro = new InserisciLibro();
				ClassificheResponsabile classificheResponsabile = new ClassificheResponsabile();
				
				JPanel card = frame.getCard();
				CardLayout clC = (CardLayout)(card.getLayout());
				JPanel bottoni = frame.getBottoni();
				CardLayout clN = (CardLayout)(bottoni.getLayout());
				
				//mostro le operazioni per il responsabile
				clN.show(bottoni, button.getText());
				
				//aggiungo le card di tutte le operazioni
				card.add(ordineResponsabile, "Ordini");
				card.add(libroCardRiservata, "Libro Card");
				card.add(inserisciLibro, "Aggiungi libro");
				card.add(classificheResponsabile, "Aggiorna classifiche");
				
				//mostro la card di default
				card.add(ordineResponsabile2, button.getText());
				clC.show(card, button.getText());
	
				frame.pack();
			} else {
				tfArray[1].setText(null);
			}
		}
	}

}
