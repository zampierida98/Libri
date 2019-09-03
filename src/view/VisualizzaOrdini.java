package view;

import java.awt.Component;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import controller.ChangeCardListener;
import controller.EseguiOrdineListener;
import model.Libro;
import model.Ordine;

public class VisualizzaOrdini extends JPanel {

	private static final String[] columns = {"Data", "Lista Libri", "Costo Totale", "Pagamento", "Indirizzo spedizione", "Punti Accumulati"};
	
	private View frame; 
	private List<Ordine> listaOrdini;
	private JTable tabellaOrdini;
	private JButton eseguiOrdine = new JButton("Esegui ordine");
	
	public VisualizzaOrdini(View frame, List<Ordine> listaOrdini) {
		this.frame = frame;
		this.listaOrdini = listaOrdini;
		Object[][] data = allOrders(listaOrdini);
		
		//creo una JTable non modificabile
		tabellaOrdini = new JTable();
		DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		tabellaOrdini.setModel(tableModel);
		
		resizeRowHeight();
		resizeColumnWidth(tabellaOrdini);
		tabellaOrdini.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.add(tabellaOrdini);
		this.add(new JLabel("<html><div><br><br></div></html>"));
		eseguiOrdine.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(eseguiOrdine);
		eseguiOrdine.addActionListener(new EseguiOrdineListener(frame, this));
	}

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
			for(Libro libro : ordine.getListaLibri().keySet()) { //	MODIFICA
				listaLibri += (libro.getTitolo() + " x" + ordine.getListaLibri().get(libro) + "<br/>");
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
			int width = 15;
			for (int row = 0; row < table.getRowCount(); row++) {
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				width = Math.max(comp.getPreferredSize().width +1 , width);
			}
			//if(width > 300)
				//width=300;
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

	private void resizeRowHeight() {
		int riga = 1;
		for(Ordine ordine : this.listaOrdini){
			this.tabellaOrdini.setRowHeight(riga, ordine.getListaLibri().size() * 16);
			riga++;
		}
	}

}
