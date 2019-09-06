package controller;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.Ordine;
import model.OrdineDao;
import model.OrdineDaoImpl;
import view.OrdiniResponsabile;
import view.View;
import view.VisualizzaOrdini;

public class VisualizzaOrdiniUtentiListener implements ActionListener{

	private OrdiniResponsabile ordiniResponsabili;
	
	private OrdineDao ordineDao = new OrdineDaoImpl();
	private HashMap<String, List<Ordine>> allOrdersMap = ordineDao.getOrdersStatus();
	private HashMap<String, VisualizzaOrdini> mapOrdini = new HashMap<String, VisualizzaOrdini>();
	
	public VisualizzaOrdiniUtentiListener(OrdiniResponsabile ordiniResponsabili) {
		this.ordiniResponsabili = ordiniResponsabili;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JComboBox<String> email = (JComboBox<String>) e.getSource();
		String emailSelezionata = (String)email.getSelectedItem();
		List<Ordine> listaOrdini = allOrdersMap.get(emailSelezionata);
		VisualizzaOrdini visualizzaOrdini = new VisualizzaOrdini(listaOrdini);
		visualizzaOrdini.getEseguiOrdine().setVisible(false);
		JPanel card = ordiniResponsabili.getCard();
		card.add(visualizzaOrdini, emailSelezionata);
		CardLayout clC = (CardLayout)card.getLayout();
		clC.show(card, emailSelezionata);
		
		View.getInstance().pack();
	}

}
