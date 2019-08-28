package view;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;

import model.Ordine;

public class ViewOrdine extends JPanel{
	
	//campi dati
	private List<Ordine> listaOrdini;
	
	private static Integer[] allID(List<Ordine> listaOrdini) {
		Integer[] allID;
		allID = new Integer[listaOrdini.size()];
		for(int i = 0; i < listaOrdini.size(); i++) {
			allID[i] = listaOrdini.get(i).getIdOrdine();
		}
		return allID;
	}
	
	private static Object[][] allOrders(List<Ordine> listaOrdini){
		int i = 0;
		Object[][] orders;
		orders = new Object[listaOrdini.size()][];
		for(Ordine ordine : listaOrdini){
			orders[i][0] = ordine.getData();
			orders[i][1] = ordine.getListaLibri();
			orders[i][2] = ordine.getCostoTotale();
			orders[i][3] = ordine.getPagamento();
			orders[i][4] = ordine.getSpedizione();
			orders[i][5] = ordine.getPuntiAccumulati();
			i++;
		}
		return orders;
	}
	
	//metodi get/set
	//EMAIL
	/*public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}*/
	
	public void setListaOrdini(List<Ordine> listaOrdini) {
		this.listaOrdini = listaOrdini;
	}
	
	
	//INIZIO GUI
	//contenitore card
	/*private static final JPanel card = new JPanel(new CardLayout());

	//bottoni cambia card ordine
	private static final JButton visualizzaOrdini =  new JButton("Visualizza ordine");
	private static final JButton inserisciOrdine = new JButton ("Inserisci ordine");*/
	
	//default card visualizza ordini
	//card VISUALIZZA ORDINE
	/*private final JComboBox boxOrdini = new JComboBox(allID(listaOrdini));
	private String[] columns = {"Data", "Lista Libri", "Costo Totale", "Pagamento", "Indirizzo spedizione", "Punti Accumulati"};
	private Object[][] data = ViewOrdine.allOrders(listaOrdini);
	private final JTable tabellaOrdini = new JTable(data, columns);
	*/
	private static JComboBox boxOrdini;
	private static final String[] columns = {"Data", "Lista Libri", "Costo Totale", "Pagamento", "Indirizzo spedizione", "Punti Accumulati"};
	private static Object[][] data;
	private static JTable tabellaOrdini ;
	
	public ViewOrdine(List<Ordine> listaOrdini) {
		boxOrdini = new JComboBox(allID(listaOrdini));
		data = ViewOrdine.allOrders(listaOrdini);
		tabellaOrdini = new JTable(data, columns);
		boxOrdini.setSelectedIndex(0);
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(boxOrdini);
		this.add(tabellaOrdini);
	}
	
	
	
	
	
	
	
	
	
	
	
}
