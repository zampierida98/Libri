package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.OrdineDao;
import model.OrdineDaoImpl;
import view.NuovoOrdine;
import view.View;

public class OrdinaLibroListener implements ActionListener{
	private View frame;
	private final OrdineDao ordineDao = new OrdineDaoImpl();
	
	public OrdinaLibroListener(View frame) {
		this.frame = frame;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JPanel card = frame.getCard();
		NuovoOrdine ordinaLibro = new NuovoOrdine();
		card.add(ordinaLibro, "Esegui un ordine");
		JButton button = (JButton)e.getSource();
		
		CardLayout cl = (CardLayout)(card.getLayout());
		cl.show(card, button.getText());
		
		frame.pack();
	}
}
