package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.View;

public class ChangeCardListener implements ActionListener {

	// riferimento alla finestra
	private View frame;

	public ChangeCardListener(View frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e){
		JTextField[] tfArray = frame.getTfArrayA();
		for(JTextField tf: tfArray) {
			tf.setText(null);
		}
		tfArray = frame.getTfArrayR();
		for(JTextField tf: tfArray) {
			tf.setText(null);
		}
		
		JButton button = (JButton)e.getSource();
		JPanel card = frame.getCard();
		CardLayout clC = (CardLayout)(card.getLayout());
		JPanel bottoni = frame.getBottoni();
		CardLayout clN = (CardLayout)(bottoni.getLayout());
		
		if(button.getText().equals("Home")) {
			//caso speciale bottone Home
			clN.show(bottoni, "default");
			clC.show(card, "Registrazione");
		} else {
			//caso base (testo del bottone)
			clC.show(card, button.getText());
		}
	}

}
