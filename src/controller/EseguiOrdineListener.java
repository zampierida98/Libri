package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.NuovoOrdine;
import view.View;
import view.VisualizzaOrdini;
import view.VisualizzaProfilo;

public class EseguiOrdineListener implements ActionListener{
	
	private View frame;
	
	public EseguiOrdineListener(View frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton eseguiOrdine = (JButton)e.getSource();
		if(eseguiOrdine.getText().equals("Fai un ordine") || eseguiOrdine.getText().equals("Esegui ordine")) {
			
			NuovoOrdine nuovoOrdine = new NuovoOrdine();
			if(eseguiOrdine.getText().equals("Esegui ordine")) {
				nuovoOrdine.getCampoEmail().setVisible(false);
				nuovoOrdine.getEmail().setVisible(false);
				nuovoOrdine.getEmail().setEnabled(false);
			}
			else {
				nuovoOrdine.getEmail().setEnabled(true);
			}
			JPanel card = frame.getCard();
			CardLayout clC = (CardLayout)(card.getLayout());
			
			card.add(nuovoOrdine, eseguiOrdine.getText());
			clC.show(card, eseguiOrdine.getText());
			
			frame.pack();
		}
		
	}

	
	
	
}
