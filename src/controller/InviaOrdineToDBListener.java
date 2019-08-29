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
import view.NuovoOrdine;

public class InviaOrdineToDBListener implements ActionListener{

	private NuovoOrdine ordineGui = new NuovoOrdine();
	private OrdineDao ordineDao = new OrdineDaoImpl();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		List<Libro> listaLibri = new ArrayList<Libro>();
		HashMap<Libro, Integer> bookMap;
		JButton ordinaB = (JButton)e.getSource();
		if(ordinaB.getText().equals("Ordina")) {
			ordineGui.setBookMap();
			bookMap = ordineGui.getBookMap();
			int id = (int) Instant.now().getEpochSecond();
			Date data = Date.valueOf(LocalDate.now());
			int costoTotale = 0;
			for(Libro libro : ordineGui.getListaLibri()){
				for(int numeroCopie = 0; numeroCopie < bookMap.get(libro); numeroCopie++) {
					listaLibri.add(libro);
					costoTotale += libro.getPrezzo();
				}
			}
			
		}
		
	}
	
}
