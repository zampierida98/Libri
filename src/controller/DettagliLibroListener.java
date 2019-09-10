package controller;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Libro;
import view.NuovoOrdine;

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
		
		//creazione finestra per mostrare tutti i dati del libro
		JFrame dettagli = new JFrame();
		
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
		pannello.add(new JLabel(libro.getGenere()));
		pannello.add(lblPrezzo);
		pannello.add(new JLabel(String.format("%.2f �", libro.getPrezzo())));
		pannello.add(lblDescrizione);
		pannello.add(new JLabel("NON DISPONIBILE"));//DA IMPLEMENTARE
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
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