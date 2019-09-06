package view;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.PagamentoIndirizzoListener;
import model.Libro;
import model.LibroDao;
import model.LibroDaoImpl;

public class NuovoOrdine extends JPanel {
	
	private LibroDao libroDao = new LibroDaoImpl();
	private JLabel campoEmail = new JLabel("Inserisci email: ");
	private JTextField email = new JTextField();
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

	
	public JLabel getCampoEmail() {
		return campoEmail;
	}
	
	public JTextField getEmail() {
		return email;
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
		this.add(campoEmail);
		this.add(email);
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
