package controller;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import model.Libro;
import view.DettagliLibro;
import view.NuovoOrdine;
import view.View;

/**
 * Visualizza tutti i dati relativi al libro selezionato dall'utente.
 */
public class DettagliLibroListener implements MouseListener {
	
	private NuovoOrdine nuovoOrdine;
	
	public DettagliLibroListener(NuovoOrdine nuovoOrdine) {
		this.nuovoOrdine = nuovoOrdine;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel libroPremuto = (JLabel)e.getSource();
		Libro libro = nuovoOrdine.getMapTitoli().get(libroPremuto);
		
		DettagliLibro dettagliFrame = new DettagliLibro(libro);
		dettagliFrame.setVisible(true);
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
