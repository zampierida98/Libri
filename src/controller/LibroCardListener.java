package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.LibroCardRiservata;
import view.View;

public class LibroCardListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton libroCard = (JButton)e.getSource();
		if(libroCard.getText().equals("Libro Card")) {
			LibroCardRiservata libroCardRiservata = new LibroCardRiservata();
			
			JPanel card = View.getInstance().getCard();
			card.add(libroCardRiservata, "Libro Card");
			CardLayout clC = (CardLayout)(card.getLayout());
			
			clC.show(card, libroCard.getText());

			View.getInstance().pack();
			
		}
		
	}

}
