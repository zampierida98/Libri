package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.GUIApp;

public class ChangeCardListener implements ActionListener {

	// riferimento alla finestra
	private GUIApp frame;

	public ChangeCardListener(GUIApp frame) {
		this.frame = frame;
	}

	public void actionPerformed(ActionEvent e){
		JPanel card = frame.getCard();
		JButton button = (JButton)e.getSource();
		
		CardLayout cl = (CardLayout)(card.getLayout());
		cl.show(card, button.getText());
	}

}
