package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;

import model.Libro;
import model.LibroCard;
import model.LibroCardDao;
import model.LibroCardDaoImpl;
import model.Ordine;
import model.OrdineDao;
import model.OrdineDaoImpl;
import model.Pagamento;
import view.NuovoOrdine;
import view.VisualizzaProfilo;

public class InviaOrdineListener implements ActionListener{

	private NuovoOrdine nuovoOrdine;
	private OrdineDao ordineDao = new OrdineDaoImpl();
	private LibroCardDao libroCardDao = new LibroCardDaoImpl();
	private HashMap<Libro, Integer> bookMap = new HashMap<Libro, Integer>();
	
	public void setBookMap() {
		for(int indiceLibro = 0; indiceLibro < nuovoOrdine.getArrayBookButton().size(); indiceLibro++) {
			ArrayList<Libro> arrayBook = nuovoOrdine.getArrayBookButton();
			bookMap.put(arrayBook.get(indiceLibro), (Integer)((nuovoOrdine.getNumeroLibri()).get(indiceLibro)).getSelectedItem());
		}
	}
	
	public InviaOrdineListener(NuovoOrdine nuovoOrdine) {
		this.nuovoOrdine = nuovoOrdine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setBookMap(); // IMPORTANTE
		
		HashMap<Libro, Integer> listaLibri = new HashMap<Libro, Integer>();
		JButton ordinaB = (JButton)e.getSource();
		
		if(ordinaB.getText().equals("Ordina")) {
			String email = VisualizzaProfilo.getEmail();
			int id = (int) Instant.now().getEpochSecond();
			Date data = Date.valueOf(LocalDate.now());
			
			int costoTotale = 0;
			for(Libro libro : bookMap.keySet()) {
				int copie = bookMap.get(libro);
				if(copie > 0) {
					costoTotale += (libro.getPrezzo() * copie);
					listaLibri.put(libro, copie);
				}
			}
			
			//provvisorio:
			Pagamento pagamento = Pagamento.PAYPAL;
			String spedizione = "Amazon";
			
			LibroCard libroCard = libroCardDao.getLibroCard(email);
			int puntiAccumulati = libroCard.getSaldoPunti();
			for(Libro libro : bookMap.keySet()) {
				puntiAccumulati += (libro.getPunti() * bookMap.get(libro));
			}
			
			Ordine ordine = new Ordine(id, data, listaLibri, costoTotale, pagamento, email, spedizione, puntiAccumulati);
			if(ordineDao.insertOrder(ordine) == true)
				System.out.println("Ordine effettuato");			
		}
	}
	
}
