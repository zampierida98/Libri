package view;

import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.InviaOrdineListener;

public class PagamentoIndirizzo extends JPanel{
	
	private String indirizzoProfilo = VisualizzaProfilo.getIndirizzo();
	NuovoOrdine nuovoOrdine;
	
	private JRadioButton carta = new JRadioButton("CARTA");
	private JRadioButton paypal = new JRadioButton("PAYPAL");
	private JRadioButton contrassegno = new JRadioButton("CONTRASSEGNO");
	
	private JRadioButton[] arrayPagamento = {carta, paypal, contrassegno};
	
	private ButtonGroup pagamento = new ButtonGroup();
	
	private JLabel labelIndirizzo = new JLabel("Vuoi consegnare ad un altro indirizzo?");
	private JTextField campoModifica = new JTextField();
	
	private JButton inviaDati = new JButton("Invia dati");

	public JRadioButton[] getArrayPagamento() {
		return arrayPagamento;
	}
	

	public JTextField getCampoModifica() {
		return campoModifica;
	}


	public PagamentoIndirizzo(NuovoOrdine nuovoOrdine) {
		this.nuovoOrdine = nuovoOrdine;
		this.setLayout(new GridLayout(2, 4));
		this.add(new JLabel("Pagamento: "));
		this.add(carta);
		this.add(paypal);
		this.add(contrassegno);
		this.add(labelIndirizzo);
		this.add(campoModifica);
		this.add(inviaDati);
		pagamento.add(carta);
		pagamento.add(paypal);
		pagamento.add(contrassegno);
		inviaDati.addActionListener(new InviaOrdineListener(nuovoOrdine, this));
	}
	
	/*public class campoIndirizzo implements ActionListener{

		private PagamentoIndirizzo pagamentoIndirizzo;
		public campoIndirizzo(PagamentoIndirizzo pagamentoIndirizzo) {
			this.pagamentoIndirizzo = pagamentoIndirizzo;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton modificaIndirizzo = (JButton)e.getSource();
			if(modificaIndirizzo.getText().equals("Modifica indirizzo")) {
				pagamentoIndirizzo.getCampoModifica().setVisible(true);
			}
		}
	}
	*/
	
}
