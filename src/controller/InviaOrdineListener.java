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

	
	private NuovoOrdine nuovoOrdine = new NuovoOrdine();
	private OrdineDao ordineDao = new OrdineDaoImpl();
	private LibroCardDao libroCardDao = new LibroCardDaoImpl();
	private HashMap<Libro, Integer> bookMap = new HashMap<Libro, Integer>();
	
	public void setBookMap() {
		for(int indiceLibro = 0; indiceLibro < nuovoOrdine.getArrayBookButton().size(); indiceLibro++) {
			//System.out.println("Indice Libro: " + indiceLibro);
			ArrayList<Libro> arrayBook = nuovoOrdine.getArrayBookButton();
			bookMap.put(arrayBook.get(indiceLibro), (Integer)((nuovoOrdine.getNumeroLibri()).get(indiceLibro)).getSelectedItem());
		}
	}
	
	public InviaOrdineListener(NuovoOrdine nuovoOrdine) {
		System.out.println("Costruttore invia Ordine");
		this.nuovoOrdine = nuovoOrdine;
		//this.setBookMap();
	}
	
	
	
	/*
	 * 
	 * private int idOrdine;
		private Date data;
		private List<Libro> listaLibri;//ATTENZIONE da tabella n..n
		private double costoTotale;
		private Pagamento pagamento;
		private String email;
		private String spedizione;
		private int puntiAccumulati;//ATTENZIONE nel nuovo ordine si parte dal saldo e si aggiungono quelli della lista
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		List<Libro> listaLibri = new ArrayList<Libro>();
		JButton ordinaB = (JButton)e.getSource();
		if(ordinaB.getText().equals("Ordina")) {
			int id = (int) Instant.now().getEpochSecond();
			Date data = Date.valueOf(LocalDate.now());
			int costoTotale = 0;
			for(Libro libro : nuovoOrdine.getListaLibri()){
				for(int numeroCopie = 0; numeroCopie < bookMap.get(libro); numeroCopie++) {
					listaLibri.add(libro);
					costoTotale += libro.getPrezzo();
				}
			}
			Pagamento pagamento = Pagamento.PAYPAL;
			String email = VisualizzaProfilo.getEmail();
			String spedizione = "Amazon";
			LibroCard libroCard = libroCardDao.getLibroCard(email);
			int puntiAccumulati = libroCard.getSaldoPunti();
			for(Libro libro : listaLibri) {
				puntiAccumulati += libro.getPunti();
			}
			Ordine ordine = new Ordine(id, data, listaLibri, costoTotale, pagamento, email, spedizione, puntiAccumulati);
			ordineDao.insertOrder(ordine);
		}
	}
	
}
