package controller;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
		// TODO Auto-generated method stub
		JButton eseguiOrdine = (JButton)e.getSource();
		PagamentoIndirizzo pagamentoIndirizzo = new PagamentoIndirizzo(nuovoOrdine);
		
		JPanel card = View.getInstance().getCard();
		card.add(pagamentoIndirizzo, "Ordina");
		CardLayout clC = (CardLayout)(card.getLayout());
		clC.show(card, eseguiOrdine.getText());
		
		Dimension d = View.getInstance().getDefaultDim();
		View.getInstance().setSize(d);
		//View.getFrame().pack();
	}

}
