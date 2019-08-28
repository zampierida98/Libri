package view;

import java.awt.Component;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import model.Libro;
import model.Ordine;

public class VisualizzaOrdine extends JPanel {

	private List<Ordine> listaOrdini;
	//private static JComboBox boxOrdini;
	private static final String[] columns = {"Data", "Lista Libri", "Costo Totale", "Pagamento", "Indirizzo spedizione", "Punti Accumulati"};
	private Object[][] data;
	private JTable tabellaOrdini;
	
	public VisualizzaOrdine(List<Ordine> listaOrdini) {
		this.listaOrdini = listaOrdini;
		//boxOrdini = new JComboBox(allID(listaOrdini));
		data = this.allOrders(listaOrdini);
		
		//creo una JTable non modificabile
		tabellaOrdini = new JTable();
		DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		tabellaOrdini.setModel(tableModel);
		
		this.aggiustaAltezzaRighe();
		resizeColumnWidth(tabellaOrdini);
		tabellaOrdini.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		//this.add(boxOrdini);
		this.add(tabellaOrdini);
	}

	/*private Integer[] allID(List<Ordine> listaOrdini) {
		Integer[] allID;
		allID = new Integer[listaOrdini.size()];
		for(int i = 0; i < listaOrdini.size(); i++) {
			allID[i] = listaOrdini.get(i).getIdOrdine();
		}
		return allID;
	}*/

	private Object[][] allOrders(List<Ordine> listaOrdini){
		Object[][] orders = new Object[listaOrdini.size()+1][6];
		
		//intestazione colonne
		for(int c = 0; c < columns.length; c++)
			orders[0][c] = columns[c];
		
		//riempimento righe
		int i = 1;
		for(Ordine ordine : listaOrdini){
			orders[i][0] = ordine.getData();
			
			String listaLibri = "<html>";
			for(Libro libro : ordine.getListaLibri()) {
				listaLibri += (libro.toString() + "<br/>");
			}
			orders[i][1] = listaLibri + "</html>";
			
			orders[i][2] = ordine.getCostoTotale();
			orders[i][3] = ordine.getPagamento();
			orders[i][4] = ordine.getSpedizione();
			orders[i][5] = ordine.getPuntiAccumulati();
			i++;
		}
		
		return orders;
	}
	
	public void resizeColumnWidth(JTable table) {
		final TableColumnModel columnModel = table.getColumnModel();
		for (int column = 0; column < table.getColumnCount(); column++) {
			int width = 15; // Min width
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width +1 , width);
			}
			if(width > 300)
				width=300;
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

	private void aggiustaAltezzaRighe() {
		int riga = 0;
		for(Ordine ordine : this.listaOrdini){
			this.tabellaOrdini.setRowHeight(riga , ordine.getListaLibri().size()*16);
			riga++;
		}
	}

}
