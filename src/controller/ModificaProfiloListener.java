package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Utente;
import model.UtenteDao;
import model.UtenteDaoImpl;
import view.ModificaProfilo;
import view.View;
import view.VisualizzaProfilo;

public class ModificaProfiloListener implements ActionListener {

	private ModificaProfilo modificaProfilo;
	
	public ModificaProfiloListener(ModificaProfilo modificaProfilo) {
		this.modificaProfilo = modificaProfilo;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		
		if(button.getText().equals("Modifica Dati")) {
			JTextField[] tfArray = modificaProfilo.getTfArray();
			
			String nome = tfArray[0].getText();
			String cognome = tfArray[1].getText();
			String indirizzo = tfArray[2].getText();
			String telefono = tfArray[3].getText();
			String email = modificaProfilo.getEmail();
			String password = tfArray[4].getText();
			
			//controlli
			for(int i = 0; i < tfArray.length; i++) {
				if(tfArray[i].getText().isEmpty()) {
					modificaProfilo.getResponse().setText("Modifica NON effettuata");
					return;
				}
			}
			
			UtenteDao utenteDao = new UtenteDaoImpl();
			Utente modificaUtente = new Utente(nome, cognome, indirizzo, telefono, email, password);
			if(utenteDao.updateUser(modificaProfilo.getEmail(), modificaUtente) == true) {
				modificaProfilo.getResponse().setText("Modifica effettuata");
				
				//sostituisco la card visualizza profilo
				JPanel card = View.getInstance().getCard();				
				VisualizzaProfilo visualizzaProfilo = new VisualizzaProfilo(utenteDao.getUser(email));
				card.remove(visualizzaProfilo);
				card.add(visualizzaProfilo, "Visualizza profilo");
			}
		}
	}
	
}
