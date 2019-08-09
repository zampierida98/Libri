package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import view.GUIApp;

public class ChangeCardListener implements ActionListener {

	// riferimento alla finestra
	private GUIApp frame;

	public ChangeCardListener(GUIApp frame) {
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
		
		JPanel card = frame.getCard();
		JButton button = (JButton)e.getSource();
		
		CardLayout cl = (CardLayout)(card.getLayout());
		cl.show(card, button.getText());
	}

}
