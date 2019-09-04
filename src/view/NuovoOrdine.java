package view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import controller.PagamentoIndirizzoListener;
import model.Libro;
import model.LibroDao;
import model.LibroDaoImpl;

public class NuovoOrdine extends JPanel {
	
	/*private LibroDao libroDao = new LibroDaoImpl();
	private List<Libro> listaLibri = libroDao.getAllBooks();
	
	private ArrayList<JComboBox<Integer>> numeroLibri = new ArrayList<JComboBox<Integer>>();
	
	private Integer[] quantitaOrdinabile = {0,1,2,3,4,5,6,7,8,9};
	private ArrayList<Libro> arrayBookButton = new ArrayList<Libro>();
	
	private JButton ordinaB = new JButton("Ordina");
	
	public List<Libro> getListaLibri() {
		return this.listaLibri;
	}
	
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
		//ordinaB.addActionListener(new InviaOrdineListener(this));
		ordinaB.addActionListener(new PagamentoIndirizzoListener(this));
	}*/
	private LibroDao libroDao = new LibroDaoImpl();
	//CheckBox
	private ArrayList<JCheckBox> arrayCheckBox = new ArrayList<JCheckBox>();
	private List<Libro> arrayLibri = libroDao.getAllBooks();
	private HashMap<Libro, JCheckBox> mapLibro = new HashMap<Libro, JCheckBox>();
	//ComboBox
	private ArrayList<JComboBox<Integer>> numeroLibri = new ArrayList<JComboBox<Integer>>();
	private Integer[] quantitaOrdinabile = {0,1,2,3,4,5,6,7,8,9};
	//Map di map
	private HashMap<Libro, JComboBox> mapQuantita = new HashMap<Libro, JComboBox>();
	
	//bottone a cui associare Listener
	private JButton ordinaB = new JButton("Ordina");
	
	public LibroDao getLibroDao() {
		return libroDao;
	}

	public ArrayList<JCheckBox> getArrayCheckBox() {
		return arrayCheckBox;
	}

	public List<Libro> getArrayLibri() {
		return arrayLibri;
	}

	public HashMap<Libro, JCheckBox> getMapLibro() {
		return mapLibro;
	}

	public ArrayList<JComboBox<Integer>> getNumeroLibri() {
		return numeroLibri;
	}

	public Integer[] getQuantitaOrdinabile() {
		return quantitaOrdinabile;
	}

	public HashMap<Libro, JComboBox> getMapQuantita() {
		return mapQuantita;
	}

	public NuovoOrdine() {
		this.setLayout(new GridLayout(arrayLibri.size() + 2, 2));
		for(int indiceCheckBox = 0; indiceCheckBox < arrayLibri.size(); indiceCheckBox++) {
			//Configurazione CheckBox
			Libro libro = arrayLibri.get(indiceCheckBox);
			
			JCheckBox checkBox = new JCheckBox(libro.getTitolo());
			//arrayCheckBox.add(checkBox);
			mapLibro.put(libro, checkBox);
			this.add(checkBox);
			//Configurazione ComboBox
			JComboBox<Integer> addNumeroLibri = new JComboBox<Integer>(quantitaOrdinabile);
			mapQuantita.put(libro, addNumeroLibri);
			this.add(addNumeroLibri);
		}
		
		this.add(ordinaB);
		ordinaB.addActionListener(new PagamentoIndirizzoListener(this));
	}
	
	
	
	
}
