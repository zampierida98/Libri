package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.util.Arrays;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import controller.VisualizzaOrdiniUtentiListener;
import model.OrdineDao;
import model.OrdineDaoImpl;

public class OrdiniResponsabile extends JPanel{
		private OrdineDao ordineDao = new OrdineDaoImpl();
		
		private Set<String> setEmail = ordineDao.getOrdersStatus().keySet();
		String[] arrayEmail = Arrays.copyOf(setEmail.toArray(), setEmail.size(), String[].class);
		private JComboBox<String> addEmail = new JComboBox<String>(arrayEmail);
		private JPanel card = new JPanel(new CardLayout());
		private JPanel nordPnl = new JPanel();
		
		public JPanel getCard() {
			return card;
		}
		
		public OrdiniResponsabile() {
			System.out.println(setEmail.toString());
			this.setLayout(new BorderLayout());
			nordPnl.setLayout(new FlowLayout());
			nordPnl.add(addEmail);
			this.add(nordPnl, BorderLayout.NORTH);
			this.add(card, BorderLayout.CENTER);
			addEmail.addActionListener(new VisualizzaOrdiniUtentiListener(this));
			
		}
}
