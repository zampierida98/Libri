package view;

import java.awt.Component;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import model.LibroCard;
import model.LibroCardDao;
import model.LibroCardDaoImpl;

public class LibroCardResponsabile extends JPanel {
	
	private static final String[] columns = {"Numero identificativo", "Data di emissione", "Saldo punti", "Email"};
 
	private List<LibroCard> listaLibroCard;
	
	public LibroCardResponsabile() {
		LibroCardDao libroCardDao = new LibroCardDaoImpl();
		this.listaLibroCard = libroCardDao.getAllLibroCard();
		
		//creo una JTable non modificabile
		Object[][] data = allCards();
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
	
	private Object[][] allCards(){
		Object[][] cards = new Object[listaLibroCard.size()+1][4];
		
		//intestazione colonne
		for(int c = 0; c < columns.length; c++)
			cards[0][c] = columns[c];
		
		//riempimento righe
		int i = 1;
		for(LibroCard libroCard : listaLibroCard){
			cards[i][0] = String.format("%010d", libroCard.getIdLibroCard());
			cards[i][1] = libroCard.getDataEmissione();
			cards[i][2] = libroCard.getSaldoPunti();
			cards[i][3] = libroCard.getEmail();
			i++;
		}
		
		return cards;
	}

	private void resizeColumnWidth(JTable table) {
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

}
