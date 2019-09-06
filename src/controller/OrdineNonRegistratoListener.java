package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Ordine;
import model.OrdineDao;
import model.OrdineDaoImpl;
import view.View;
import view.VisualizzaOrdini;

public class OrdineNonRegistratoListener implements ActionListener{
	private OrdineDao ordineDao = new OrdineDaoImpl();

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton accediOrdine = (JButton)e.getSource();
		if(accediOrdine.getText().equals("Accedi all'ordine")) {
			Ordine o = ordineDao.getOrder(Integer.valueOf(View.getInstance().getCompCodiceOrdine().getText()));
			List<Ordine> listaOrdini = new ArrayList<Ordine>();
			listaOrdini.add(o);
			VisualizzaOrdini visualizzaOrdine = new VisualizzaOrdini(View.getInstance(), listaOrdini);
			visualizzaOrdine.getEseguiOrdine().setVisible(false);
			//riferimenti ai card layout
			JPanel card = View.getInstance().getCard();
			CardLayout clC = (CardLayout)(card.getLayout());
			card.add(visualizzaOrdine, "Accedi all'ordine");
			clC.show(card, accediOrdine.getText());
			
			View.getInstance().pack();
		}
		
	}
	

}
