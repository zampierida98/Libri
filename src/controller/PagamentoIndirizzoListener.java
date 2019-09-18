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

/**
 * Predispone l'interfaccia per l'inserimento di pagamento e indirizzo a seconda dell'utente che lo vuole effettuare (registrato o non).
 */
public class PagamentoIndirizzoListener implements ActionListener {

	private NuovoOrdine nuovoOrdine;
	
	public PagamentoIndirizzoListener(NuovoOrdine nuovoOrdine) {
		this.nuovoOrdine = nuovoOrdine;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		PagamentoIndirizzo pagamentoIndirizzo = new PagamentoIndirizzo(nuovoOrdine);
		
		if(nuovoOrdine.getEmail().isEnabled())
			pagamentoIndirizzo.getLblIndirizzo().setText("Inserisci l'indirizzo:");
		else
			pagamentoIndirizzo.getLblIndirizzo().setText("Vuoi cambiare indirizzo?");
		
		JPanel card = View.getInstance().getCard();
		card.add(pagamentoIndirizzo, "Ordina");
		CardLayout clC = (CardLayout)(card.getLayout());
		clC.show(card, button.getText());
		
		Dimension d = View.getInstance().getDefaultDim();
		View.getInstance().setSize(d);
	}

}
