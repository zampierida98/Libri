package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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

	private List<Libro> arrayLibri;

	private JPanel nordPnl = new JPanel();
	private JLabel campoEmail = new JLabel("Inserisci email: ");
	private JTextField email = new JTextField();

	private JPanel centroPnl = new JPanel();
	private HashMap<Libro, JCheckBox> mapLibro = new HashMap<Libro, JCheckBox>();
	private static final Integer[] quantitaOrdinabile = {0,1,2,3,4,5,6,7,8,9};
	private HashMap<Libro, JComboBox> mapQuantita = new HashMap<Libro, JComboBox>();
	
	private JPanel sudPnl = new JPanel();
	private JButton ordinaB = new JButton("Ordina");


	public JLabel getCampoEmail() {
		return campoEmail;
	}

	public JTextField getEmail() {
		return email;
	}

	public List<Libro> getArrayLibri() {
		return arrayLibri;
	}

	public HashMap<Libro, JCheckBox> getMapLibro() {
		return mapLibro;
	}

	public HashMap<Libro, JComboBox> getMapQuantita() {
		return mapQuantita;
	}


	public NuovoOrdine() {
		LibroDao libroDao = new LibroDaoImpl();
		List<Libro> arrayLibri = libroDao.getAllBooks();
		this.arrayLibri = arrayLibri;

		//Pannello Nord
		nordPnl.setLayout(new GridLayout(1,2));
		nordPnl.add(campoEmail);
		nordPnl.add(email);

		//Pannello Centro
		centroPnl.setLayout(new GridLayout(arrayLibri.size(), 3));
		for(int indiceCheckBox = 0; indiceCheckBox < arrayLibri.size(); indiceCheckBox++) {
			//configurazione CheckBox e label prezzo
			Libro libro = arrayLibri.get(indiceCheckBox);
			JCheckBox checkBox = new JCheckBox(libro.getTitolo());
			mapLibro.put(libro, checkBox);
			centroPnl.add(checkBox);
			JLabel prezzo = new JLabel(String.format("%.2f €", libro.getPrezzo()));
			prezzo.setHorizontalAlignment(JLabel.CENTER);
			centroPnl.add(prezzo);

			//configurazione ComboBox
			JComboBox<Integer> addNumeroLibri = new JComboBox<Integer>(quantitaOrdinabile);
			mapQuantita.put(libro, addNumeroLibri);
			centroPnl.add(addNumeroLibri);
		}
		
		//Pannello Sud
		sudPnl.setLayout(new FlowLayout());
		sudPnl.add(ordinaB);
		ordinaB.addActionListener(new PagamentoIndirizzoListener(this));

		//Container Principale
		this.setLayout(new BorderLayout());
		this.add(nordPnl, BorderLayout.NORTH);
		this.add(centroPnl, BorderLayout.CENTER);
		this.add(sudPnl, BorderLayout.SOUTH);
	}

}
