package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import controller.OrdiniResponsabileListener;
import model.Ordine;
import model.OrdineDao;
import model.OrdineDaoImpl;

public class OrdiniResponsabile extends JPanel {

	private JPanel nordPnl = new JPanel();
	private JComboBox<String> addEmail;
	private JPanel centroPnl = new JPanel(new CardLayout());

	public JPanel getCard() {
		return centroPnl;
	}

	public OrdiniResponsabile() {
		OrdineDao ordineDao = new OrdineDaoImpl();
		Set<String> setEmail = ordineDao.getOrdersStatus().keySet();
		String[] arrayEmail = Arrays.copyOf(setEmail.toArray(), setEmail.size(), String[].class);
		addEmail = new JComboBox<String>(arrayEmail);
		
		nordPnl.setLayout(new FlowLayout());
		nordPnl.add(addEmail);

		this.setLayout(new BorderLayout());
		this.add(nordPnl, BorderLayout.NORTH);
		this.add(centroPnl, BorderLayout.CENTER);
		
		//visualizzo la card relativa alla prima email
		String emailSelezionata = (String)addEmail.getSelectedItem();
		List<Ordine> listaOrdini = ordineDao.getOrdersStatus().get(emailSelezionata);
		VisualizzaOrdini visualizzaOrdini = new VisualizzaOrdini(listaOrdini);
		visualizzaOrdini.getEseguiOrdine().setVisible(false);
		centroPnl.add(visualizzaOrdini, emailSelezionata);
		CardLayout clC = (CardLayout)centroPnl.getLayout();
		clC.show(centroPnl, emailSelezionata);

		addEmail.addActionListener(new OrdiniResponsabileListener(this));
	}
	
}
