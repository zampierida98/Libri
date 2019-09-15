package controller;

import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Classifica;
import model.ClassificaDao;
import model.ClassificaDaoImpl;
import model.Libro;
import view.NuovoOrdine;
import view.View;

public class DettagliLibroListener implements MouseListener {
	
	private NuovoOrdine nuovoOrdine;
	private static final JLabel lblISBN = new JLabel("Codice ISBN:");
	private static final JLabel lblTitolo = new JLabel("Titolo:");
	private static final JLabel lblAutori = new JLabel("Autori:");
	private static final JLabel lblCasaEditrice = new JLabel("Casa editrice:");
	private static final JLabel lblAnnoPubblicazione = new JLabel("Anno di pubblicazione:");
	private static final JLabel lblGenere = new JLabel("Genere:");
	private static final JLabel lblPrezzo = new JLabel("Prezzo:");
	private static final JLabel lblDescrizione = new JLabel("Descrizione:");
	private static final JLabel lblPunti = new JLabel("Punti associati:");
	
	public DettagliLibroListener(NuovoOrdine nuovoOrdine) {
		this.nuovoOrdine = nuovoOrdine;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel libroPremuto = (JLabel)e.getSource();
		Libro libro = nuovoOrdine.getMapTitoli().get(libroPremuto);
		ClassificaDao classificaDao = new ClassificaDaoImpl();
		
		//creazione finestra per mostrare tutti i dati del libro
		JFrame dettagli = new JFrame(libro.getTitolo());
		
		JPanel pannello = new JPanel();
		pannello.setLayout(new GridLayout(9,2));
		
		pannello.add(lblISBN);
		pannello.add(new JLabel(libro.getISBN()));
		
		pannello.add(lblTitolo);
		pannello.add(new JLabel(libro.getTitolo()));
		
		pannello.add(lblAutori);
		pannello.add(new JLabel(libro.getAutori()));
		
		pannello.add(lblCasaEditrice);
		pannello.add(new JLabel(libro.getCasaEditrice()));
		
		pannello.add(lblAnnoPubblicazione);
		pannello.add(new JLabel(String.valueOf(libro.getAnnoPubblicazione())));
		
		pannello.add(lblGenere);
		String genere = libro.getGenere();
		HashMap<String, List<Classifica>> mapClassifiche = classificaDao.getClassifiche();
		List<Classifica> libriInClassifica = mapClassifiche.get(libro.getGenere());
		if(libriInClassifica != null) {
			for(Classifica c : libriInClassifica) {
				if(c.getISBN().equals(libro.getISBN())) {
					genere += " - n. " + c.getPosizione() + " in classifica";
					
					long settimane = c.settimaneInClassifica(c.getData(), Date.valueOf(LocalDate.now()));
					if(settimane < 1)
						genere += " da meno di 1 settimana";
					else if(settimane == 1)
						genere += " da 1 settimana";
					else
						genere += " da " + settimane + " settimane";
					
					break;
				}
			}
		}
		pannello.add(new JLabel(genere));
		
		pannello.add(lblPrezzo);
		pannello.add(new JLabel(String.format("%.2f €", libro.getPrezzo())));
		
		pannello.add(lblDescrizione);
		String[] parole = libro.getDescrizione().split(" ");
		String descrizione = "<html>";
		if(parole.length > 20) {
			for(int i = 0; i < 20; i++) {
				descrizione += parole[i] + " ";
				if(i == 9)
					descrizione += "<br/>";
			}
			descrizione += "...";
		} else {
			for(int i = 0; i < parole.length; i++)
				descrizione += parole[i] + " ";
		}
		descrizione += "</html>";
		pannello.add(new JLabel(descrizione));
		
		pannello.add(lblPunti);
		pannello.add(new JLabel(String.valueOf(libro.getPunti())));
		
		dettagli.add(pannello);
		dettagli.pack();
		dettagli.setResizable(false);
		dettagli.setLocationRelativeTo(null);
		dettagli.setVisible(true);		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        View.getInstance().setCursor(cursor);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Cursor cursor = new Cursor(Cursor.DEFAULT_CURSOR);
        View.getInstance().setCursor(cursor);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
