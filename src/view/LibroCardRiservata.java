package view;

import java.awt.Component;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import model.Libro;
import model.LibroCard;
import model.LibroCardDao;
import model.LibroCardDaoImpl;
import model.Ordine;

public class LibroCardRiservata extends JPanel{
	private LibroCardDao libroCardDao = new LibroCardDaoImpl();
	private List<LibroCard> listLibroCard = libroCardDao.getAllLibroCard();
	private String[] columns = {"Numero identificativo ","Data di emissione", "Saldo Punti", "Email"};
	
	private Object[][] allCards(List<LibroCard> listaCard){
		Object[][] cards = new Object[listaCard.size()+1][4];
		
		//intestazione colonne
		for(int c = 0; c < columns.length; c++)
			cards[0][c] = columns[c];
		
		//riempimento righe
		int i = 1;
		for(LibroCard libroCard : listaCard){
			cards[i][0] = libroCard.getIdLibroCard();
			cards[i][1] = libroCard.getDataEmissione();
			cards[i][2] = libroCard.getSaldoPunti();
			cards[i][3] = libroCard.getEmail();
			i++;
		}
		
		return cards;
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

	
	public LibroCardRiservata() {
		//creo una JTable non modificabile
		Object[][] data = allCards(listLibroCard);
		JTable tabellaCard = new JTable();
		DefaultTableModel tableModel = new DefaultTableModel(data, columns) {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		tabellaCard.setModel(tableModel);
		
		resizeColumnWidth(tabellaCard);
		tabellaCard.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		this.add(tabellaCard);
	}
}
