package view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.Libro;
import model.LibroDao;
import model.LibroDaoImpl;
import model.Utente;


public class NuovoOrdine extends JPanel {
	
	
	private LibroDao libroDao = new LibroDaoImpl();
	private List<Libro> listaLibri = libroDao.getAllBooks();
	
	private ArrayList<JComboBox<Integer>> numeroLibri = new ArrayList<JComboBox<Integer>>();
	
	
	private Integer[] quantitaOrdinabile = {0,1,2,3,4,5,6,7,8,9};
	private ArrayList<Libro> arrayBookButton = new ArrayList<Libro>();
	
	private JButton ordinaB = new JButton("Ordina");
	
	public List<Libro> getListaLibri() {
		return this.listaLibri;
	}
	
	
	
	/*public HashMap<Libro, Integer> getBookMap(){
		return this.bookMap;
	}*/
	
	
	public ArrayList<JComboBox<Integer>> getNumeroLibri() {
		return numeroLibri;
	}



	public ArrayList<Libro> getArrayBookButton() {
		return arrayBookButton;
	}



	public NuovoOrdine() {
		int i = 0;
		this.setLayout(new GridLayout(listaLibri.size() + 2, 2));
		for(Libro libro : listaLibri) {
			JCheckBox bookButton = new JCheckBox(libro.getTitolo());
			bookButton.setSelected(false);
	        arrayBookButton.add(i, libro);	
			this.add(bookButton);
			JComboBox<Integer> addNumeroLibri = new JComboBox<Integer>(quantitaOrdinabile);
			numeroLibri.add(i, addNumeroLibri);
			this.add(addNumeroLibri);
			i++;	
		}
		this.add(ordinaB);
	}

}
