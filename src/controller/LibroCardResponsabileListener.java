package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import view.LibroCardResponsabile;
import view.View;

public class LibroCardResponsabileListener implements ActionListener {
	
	private View frame;
	
	public LibroCardResponsabileListener(View frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		
		if(button.getText().equals("Libro Card")) {
			LibroCardResponsabile libroCardRiservata = new LibroCardResponsabile();
			
			JPanel card = frame.getCard();
			card.add(libroCardRiservata, "Libro Card");
			
			CardLayout clC = (CardLayout)(card.getLayout());
			clC.show(card, button.getText());

			frame.pack();
		}
	}

}
