package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ClassificheResponsabileListener;
import model.Libro;
import model.LibroDao;
import model.LibroDaoImpl;

/**
 * Contiene l'interfaccia per permettere ai responsabili della libreria di aggiornare le classifiche di vendita.
 */
public class ClassificheResponsabile extends JPanel {

	private JPanel nordPnl = new JPanel();
	private JLabel lblTitoli = new JLabel("Seleziona un libro:");
	private JComboBox<String> titoli;
	private JPanel centroPnl = new JPanel();
	private JLabel lblPosizione = new JLabel("Inserisci la posizione:");
	private JTextField posizione = new JTextField();
	private JPanel sudPnl = new JPanel();
	private JButton aggiorna = new JButton("Aggiorna");
	
	public JComboBox<String> getTitoli() {
		return titoli;
	}
	
	public JTextField getPosizione() {
		return posizione;
	}

	public ClassificheResponsabile() {
		LibroDao libroDao = new LibroDaoImpl();
		List<Libro> listaLibri = libroDao.getAllBooks();
		String[] arrayTitoli = new String[listaLibri.size()];
		for(int i = 0; i < listaLibri.size(); i++)
			arrayTitoli[i] = listaLibri.get(i).getTitolo();
		titoli = new JComboBox<String>(arrayTitoli);
		
		//Pannello Nord
		nordPnl.setLayout(new FlowLayout());
		nordPnl.add(lblTitoli);
		nordPnl.add(titoli);
		//Pannello Centro
		posizione.setHorizontalAlignment(JTextField.CENTER);
		centroPnl.setLayout(new GridLayout(1, 2));
		centroPnl.add(lblPosizione);
		centroPnl.add(posizione);
		//Pannello Sud
		sudPnl.setLayout(new FlowLayout());
		sudPnl.add(aggiorna);
		aggiorna.addActionListener(new ClassificheResponsabileListener(this));

		//Container Principale
		this.setLayout(new BorderLayout());
		this.add(nordPnl, BorderLayout.NORTH);
		this.add(centroPnl, BorderLayout.CENTER);
		this.add(sudPnl, BorderLayout.SOUTH);
	}

}
