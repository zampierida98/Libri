package view;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;


import model.Libro;
import model.LibroDao;
import model.LibroDaoImpl;

public class NuovoOrdine extends JPanel {
	
	private LibroDao libroDao = new LibroDaoImpl();
	private List<Libro> listaLibri = libroDao.getAllBooks();
	private Integer[] quantitaOrdinabile = {0,1,2,3,4,5,6,7,8,9};

	public NuovoOrdine() {
		this.setLayout(new GridLayout(listaLibri.size(), 3));
		for(Libro libro : listaLibri) {
			JCheckBox bookButton = new JCheckBox(libro.getTitolo());
			bookButton.setSelected(false);
	        
			JComboBox<Integer> numeroLibri = new JComboBox<Integer>(quantitaOrdinabile);
			
			this.add(bookButton);
			this.add(numeroLibri);
		}
		
	}

}
