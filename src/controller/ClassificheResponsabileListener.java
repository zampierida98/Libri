package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;

import model.Classifica;
import model.ClassificaDao;
import model.ClassificaDaoImpl;
import model.Libro;
import model.LibroDao;
import model.LibroDaoImpl;
import view.ClassificheResponsabile;
import view.View;

public class ClassificheResponsabileListener implements ActionListener {

	private ClassificheResponsabile classificheResponsabile;

	public ClassificheResponsabileListener(ClassificheResponsabile classificheResponsabile) {
		this.classificheResponsabile = classificheResponsabile;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton)e.getSource();
		LibroDao libroDao = new LibroDaoImpl();
		ClassificaDao classificaDao = new ClassificaDaoImpl();

		if(button.getText().equals("Aggiorna")) {
			JComboBox<String> titoli = classificheResponsabile.getTitoli();
			String titoloSelezionato = (String) titoli.getSelectedItem();
			String posizione = classificheResponsabile.getPosizione().getText();

			//recupero il libro dal titolo
			Libro libro = null;
			List<Libro> listaLibri = libroDao.getAllBooks();
			for(Libro l : listaLibri) {
				if(l.getTitolo().equals(titoloSelezionato)) {
					libro = l;
					break;
				}
			}
			if(libro == null)
				return;
			
			//recupero la classifica
			boolean presente = true;
			HashMap<String, List<Classifica>> mapClassifiche = classificaDao.getClassifiche();
			if(mapClassifiche.isEmpty())
				presente = false;
			List<Classifica> libriInClassifica = mapClassifiche.get(libro.getGenere());
			if(libriInClassifica == null)
				presente = false;
			
			if(presente == true) {
				//aggiorno la posizione in classifica
				for(Classifica c : libriInClassifica) {
					if(libro.getISBN().equals(c.getISBN())) {
						c.setPosizione(Integer.valueOf(posizione));
						classificaDao.updateClassifica(libro.getISBN(), c);
					}
				}
			} else {
				//creo una nuova posizione in classifica
				Classifica c = new Classifica(libro.getISBN(), Integer.valueOf(posizione), 0);
				classificaDao.insertClassifica(c);
			}
			
			View.getInstance().pack();
			
			//DEBUG
			mapClassifiche = classificaDao.getClassifiche();
			for(String g : mapClassifiche.keySet())
				for(Classifica c : mapClassifiche.get(g))
					System.out.println(c.getISBN() + " " + c.getPosizione());
		}
	}

}
