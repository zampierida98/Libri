package view;

import java.awt.GridLayout;
import java.util.HashMap;
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
	JComboBox<Integer> numeroLibri = new JComboBox<Integer>(quantitaOrdinabile);
	private Libro[] arrayBookButton = new Libro[listaLibri.size()];
	private HashMap<Libro, Integer> bookMap = new HashMap<Libro, Integer>();

	public List<Libro> getListaLibri() {
		return this.listaLibri;
	}
	
	public void setBookMap() {
		for(Libro box : arrayBookButton) {
			this.bookMap.put(box, (Integer)numeroLibri.getSelectedItem());
		}
	}
	
	public HashMap<Libro, Integer> getBookMap(){
		return this.bookMap;
	}
	
	
	public NuovoOrdine() {
		int i = 0;
		this.setLayout(new GridLayout(listaLibri.size(), 3));
		for(Libro libro : listaLibri) {
			JCheckBox bookButton = new JCheckBox(libro.getTitolo());
			bookButton.setSelected(false);
	        arrayBookButton[i] = libro;
	        i++;			
			this.add(bookButton);
			this.add(numeroLibri);
		}
		
	}

}
