package view;

import java.awt.BorderLayout;
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
	
	//Pannello Nord:
	private static final JPanel nordPnl=new JPanel();
	private static final JLabel lblPagamento = new JLabel("Pagamento:");
	//Pannello Centrale:
	private static final JPanel centroPnl=new JPanel(new GridLayout(2,1));
	private static final JRadioButton carta = new JRadioButton("CARTA");
	private static final JRadioButton paypal = new JRadioButton("PAYPAL");
	private static final JRadioButton contrassegno = new JRadioButton("CONTRASSEGNO");
	private static final JRadioButton[] arrayPagamento = {carta, paypal, contrassegno};
	private static final ButtonGroup pagamento = new ButtonGroup();
	//Pannello Sud:
	private static final JPanel sudPnl=new JPanel();
	private static final JLabel lblIndirizzo = new JLabel("Vuoi cambiare indirizzo?");
	private static final JTextField campoModifica = new JTextField();
	private static final JButton inviaDati = new JButton("Invia dati");

	
	public JRadioButton[] getArrayPagamento() {
		return arrayPagamento;
	}
	
	public JTextField getCampoModifica() {
		return campoModifica;
	}


	public PagamentoIndirizzo(NuovoOrdine nuovoOrdine) {
		this.nuovoOrdine = nuovoOrdine;
		/*this.setLayout(new GridLayout(2, 4));
		this.add();
		this.add(carta);
		this.add(paypal);
		this.add(contrassegno);
		this.add(labelIndirizzo);
		this.add(campoModifica);
		this.add(inviaDati);*/
		
		//Pannello Nord
		nordPnl.setLayout(new GridLayout(1, 3));
		nordPnl.add(lblPagamento);
		//Pannello Centro
		centroPnl.setLayout(new GridLayout(3, 1));
		centroPnl.add(carta);
		centroPnl.add(paypal);
		centroPnl.add(contrassegno);
		pagamento.add(carta);
		pagamento.add(paypal);
		pagamento.add(contrassegno);
		//Pannello Sud
		sudPnl.setLayout(new GridLayout(1, 3));
		sudPnl.add(lblIndirizzo);
		sudPnl.add(campoModifica);
		sudPnl.add(inviaDati);
		inviaDati.addActionListener(new InviaOrdineListener(nuovoOrdine, this));
		
		//Container Principale
		this.setLayout(new BorderLayout());
		this.add(nordPnl, BorderLayout.NORTH);
		this.add(centroPnl, BorderLayout.CENTER);
		this.add(sudPnl, BorderLayout.SOUTH);
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
