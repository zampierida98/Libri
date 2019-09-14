package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.View;

public class ChangeCardListener implements ActionListener {

	private View frame;

	public ChangeCardListener(View frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		
		//pulisco tutti i campi di testo
		JTextField[] tfArray = frame.getTfArrayAR();
		for(JTextField tf: tfArray) {
			tf.setText(null);
		}
		tfArray = frame.getTfArrayA();
		for(JTextField tf: tfArray) {
			tf.setText(null);
		}
		tfArray = frame.getTfArrayR();
		for(JTextField tf: tfArray) {
			tf.setText(null);
		}
		
		//riferimenti ai card layout
		JPanel card = frame.getCard();
		CardLayout clC = (CardLayout)(card.getLayout());
		JPanel bottoni = frame.getBottoni();
		CardLayout clN = (CardLayout)(bottoni.getLayout());
		
		if(button.getText().equals("Esci")) {
			//caso speciale bottone Esci (torna alle card di default)
			clN.show(bottoni, frame.getDefaultNorthPanel());
			clC.show(card, frame.getRegistrationPanel());

			frame.setSize(frame.getDefaultDim());
		} else if(button.getText().equals("Visualizza un ordine")) {
			//caso speciale bottone Visualizza un ordine (torna alla card di default per i non registrati)
			clC.show(card, frame.getNotRegUserPanel());
			
			frame.setSize(frame.getDefaultDim());
		} else if(button.getText().equals("Visualizza ordini")) {
			//caso speciale bottone Visualizza ordini (mostra la card e fa un pack)
			clC.show(card, button.getText());
			
			frame.pack();
		} else {
			//caso base (testo del bottone)
			clC.show(card, button.getText());
			clN.show(bottoni, button.getText());
		}
	}

}
