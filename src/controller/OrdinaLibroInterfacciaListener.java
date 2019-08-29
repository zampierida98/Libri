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
import view.NuovoOrdine;
import view.View;
import view.VisualizzaOrdine;

public class OrdinaLibroInterfacciaListener implements ActionListener{
	private View frame;
	private final OrdineDao ordineDao = new OrdineDaoImpl();
	
	public OrdinaLibroInterfacciaListener(View frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//card CENTER
				JButton button = (JButton)e.getSource();
				JPanel card = frame.getCard();
				NuovoOrdine ordinaLibro = new NuovoOrdine();
				card.add(ordinaLibro, "Esegui un ordine");
				CardLayout clC = (CardLayout)(card.getLayout());
				clC.show(card, button.getText());
				//bottoni NORD
				JPanel bottoni = frame.getBottoni();
				CardLayout clN = (CardLayout)(bottoni.getLayout());
				clN.show(bottoni, button.getText());
				frame.pack();
	}
	
	
}
