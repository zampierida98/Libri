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
import javax.swing.SwingConstants;

import controller.DettagliLibroListener;
import controller.PagamentoIndirizzoListener;
import model.Libro;
import model.LibroDao;
import model.LibroDaoImpl;

/**
 * Contiene l'interfaccia per la memorizzazione dei libri che compongono l'ordine e dell’utente che lo sta effettuando.
 */
public class NuovoOrdine extends JPanel {

	private List<Libro> arrayLibri;

	private JPanel nordPnl = new JPanel();
	private JLabel campoEmail = new JLabel("Inserisci email: ", SwingConstants.RIGHT);
	private JTextField email = new JTextField();

	private JPanel centroPnl = new JPanel();
	private JLabel lblQuantita = new JLabel("Quantità:", SwingConstants.CENTER);
	private HashMap<Libro, JCheckBox> mapLibro = new HashMap<Libro, JCheckBox>();
	private HashMap<JLabel, Libro> mapTitoli = new HashMap<JLabel, Libro>();
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
	
	public HashMap<JLabel, Libro> getMapTitoli() {
		return mapTitoli;
	}

	public HashMap<Libro, JComboBox> getMapQuantita() {
		return mapQuantita;
	}


	public NuovoOrdine() {
		LibroDao libroDao = new LibroDaoImpl();
		List<Libro> arrayLibri = libroDao.getAllBooks();
		this.arrayLibri = arrayLibri;

		//Pannello Nord
		nordPnl.setLayout(new GridLayout(1,3));
		nordPnl.add(campoEmail);
		nordPnl.add(email);
		nordPnl.add(new JLabel("<html><div><br></div></html>"));

		//Pannello Centro
		centroPnl.setLayout(new GridLayout(arrayLibri.size()+1, 3));
		centroPnl.add(new JLabel("<html><div><br></div></html>"));
		centroPnl.add(new JLabel("<html><div><br></div></html>"));
		centroPnl.add(lblQuantita);
		for(int indiceCheckBox = 0; indiceCheckBox < arrayLibri.size(); indiceCheckBox++) {
			//configurazione CheckBox
			Libro libro = arrayLibri.get(indiceCheckBox);
			JCheckBox checkBox = new JCheckBox(libro.getTitolo());
			mapLibro.put(libro, checkBox);
			centroPnl.add(checkBox);
			
			//configurazione label prezzo
			JLabel prezzo = new JLabel("<html>" + String.format("%.2f € ", libro.getPrezzo()) + "<body style=\"color:blue;\"><u>+</u></body></html>");
			prezzo.setHorizontalAlignment(JLabel.CENTER);
			prezzo.addMouseListener(new DettagliLibroListener(this));
			mapTitoli.put(prezzo, libro);
			centroPnl.add(prezzo);

			//configurazione ComboBox
			JComboBox<Integer> addNumeroLibri = new JComboBox<Integer>(quantitaOrdinabile);
			((JLabel)addNumeroLibri.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
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
