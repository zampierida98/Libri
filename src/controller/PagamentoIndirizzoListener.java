package controller;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.NuovoOrdine;
import view.PagamentoIndirizzo;
import view.View;

public class PagamentoIndirizzoListener implements ActionListener{

	private NuovoOrdine nuovoOrdine;

	public PagamentoIndirizzoListener(NuovoOrdine nuovoOrdine) {
		this.nuovoOrdine = nuovoOrdine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton eseguiOrdine = (JButton)e.getSource();
		PagamentoIndirizzo pagamentoIndirizzo = new PagamentoIndirizzo(nuovoOrdine);

		JPanel card = View.getCard();
		card.add(pagamentoIndirizzo, "Ordina");
		CardLayout clC = (CardLayout)(card.getLayout());
		clC.show(card, eseguiOrdine.getText());
		
	}

}
