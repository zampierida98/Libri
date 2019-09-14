package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.NuovoOrdine;
import view.View;

public class EseguiOrdineListener implements ActionListener {
	
	private View frame;
	
	public EseguiOrdineListener(View frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		if(button.getText().equals("Fai un ordine") || button.getText().equals("Esegui ordine")) {
			NuovoOrdine nuovoOrdine = new NuovoOrdine();
			
			if(button.getText().equals("Esegui ordine")) {
				nuovoOrdine.getCampoEmail().setVisible(false);
				nuovoOrdine.getEmail().setVisible(false);
				nuovoOrdine.getEmail().setEnabled(false);
			}
			else {
				nuovoOrdine.getEmail().setEnabled(true);
			}
			
			JPanel card = frame.getCard();
			CardLayout clC = (CardLayout)(card.getLayout());
			
			card.add(nuovoOrdine, button.getText());
			clC.show(card, button.getText());
			
			frame.pack();
		}
	}

}
