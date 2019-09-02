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
import model.OrdineDao;
import model.OrdineDaoImpl;
import model.Pagamento;
import view.NuovoOrdine;

public class InviaOrdineListener implements ActionListener{

	
	private NuovoOrdine nuovoOrdine = new NuovoOrdine();
	private OrdineDao ordineDao = new OrdineDaoImpl();
	private HashMap<Libro, Integer> bookMap = new HashMap<Libro, Integer>();
	
	public void setBookMap() {
		for(int indiceLibro = 0; indiceLibro < nuovoOrdine.getArrayBookButton().size(); indiceLibro++) {
			this.bookMap.put(nuovoOrdine.getArrayBookButton().get(indiceLibro), (Integer)nuovoOrdine.getNumeroLibri().get(indiceLibro).getSelectedItem());
		}
	}
	
	public InviaOrdineListener(NuovoOrdine nuovoOrdine) {
		this.nuovoOrdine = nuovoOrdine;
		this.setBookMap();
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
			
		}
		//ordineDao.insertOrder(ordine);
	}
	
}
