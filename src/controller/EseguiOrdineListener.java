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
	private VisualizzaOrdini visualizzaOrdini;
	
	public EseguiOrdineListener(View frame, VisualizzaOrdini visualizzaOrdini) {
		this.frame = frame;
		this.visualizzaOrdini = visualizzaOrdini;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton eseguiOrdine = (JButton)e.getSource();
		if(eseguiOrdine.getText().equals("Esegui ordine")) {
				
			NuovoOrdine nuovoOrdine = new NuovoOrdine();
			
			JPanel card = frame.getCard();
			CardLayout clC = (CardLayout)(card.getLayout());
			
			card.add(nuovoOrdine, "Esegui ordine");
			clC.show(card, eseguiOrdine.getText());
			
			frame.pack();
		}
		
	}

	
	
	
}
