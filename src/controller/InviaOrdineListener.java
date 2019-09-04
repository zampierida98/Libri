package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import model.Libro;
import model.LibroCard;
import model.LibroCardDao;
import model.LibroCardDaoImpl;
import model.Ordine;
import model.OrdineDao;
import model.OrdineDaoImpl;
import model.Pagamento;
import view.NuovoOrdine;
import view.PagamentoIndirizzo;
import view.VisualizzaProfilo;

public class InviaOrdineListener implements ActionListener{

	private NuovoOrdine nuovoOrdine;
	private PagamentoIndirizzo pagamentoIndirizzo;
	private OrdineDao ordineDao = new OrdineDaoImpl();
	private LibroCardDao libroCardDao = new LibroCardDaoImpl();
	private HashMap<Libro, Integer> bookMap = new HashMap<Libro, Integer>();

	private Pagamento pagamento;

	public void setBookMap() {
		/*for(int indiceLibro = 0; indiceLibro < nuovoOrdine.getArrayBookButton().size(); indiceLibro++) {
			ArrayList<Libro> arrayBook = nuovoOrdine.getArrayBookButton();
			bookMap.put(arrayBook.get(indiceLibro), (Integer)((nuovoOrdine.getNumeroLibri()).get(indiceLibro)).getSelectedItem());
		}*/
		for(Libro libro : nuovoOrdine.getArrayLibri()) {
			JCheckBox checkBox = nuovoOrdine.getMapLibro().get(libro);
			if(checkBox.isSelected()) {
				JComboBox<Integer> quantita = nuovoOrdine.getMapQuantita().get(libro);
				bookMap.put(libro, (Integer)quantita.getSelectedItem());
			}
		}
	}

	public InviaOrdineListener(NuovoOrdine nuovoOrdine, PagamentoIndirizzo pagamentoIndirizzo) {
		this.nuovoOrdine = nuovoOrdine;
		this.pagamentoIndirizzo = pagamentoIndirizzo;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.setBookMap(); // IMPORTANTE

		JButton ordinaB = (JButton)e.getSource();

		if(ordinaB.getText().equals("Invia dati")) {
			String email = VisualizzaProfilo.getEmail();
			int id = (int) Instant.now().getEpochSecond();
			Date data = Date.valueOf(LocalDate.now());

			int costoTotale = 0;
			for(Libro libro : bookMap.keySet()) {
				int copie = bookMap.get(libro);
				if(copie > 0) {
					costoTotale += (libro.getPrezzo() * copie);
					bookMap.put(libro, copie);
				}
			}

			//Boolean pagamentoSelezionato = false;
			//provvisorio
			for(JRadioButton tipoPagamento : pagamentoIndirizzo.getArrayPagamento()) {
				//pagamentoSelezionato = tipoPagamento.isSelected();
				if(tipoPagamento.isSelected()) {
					switch(tipoPagamento.getText()) {
					case "CARTA":
						pagamento = Pagamento.CARTA;
						break;
					case "PAYPAL":
						pagamento = Pagamento.PAYPAL;
						break;
					case "CONTRASSEGNO":
						pagamento = Pagamento.CONTRASSEGNO;
						break;
					default:
						System.out.println("Nessun pagamento selezionato");
						break;
					}
					break;
				}
			}
			if(pagamento == null) {
				System.out.println("Ordine NON effettuato");
			}
			else {
				String spedizione = VisualizzaProfilo.getIndirizzo();
				if(!pagamentoIndirizzo.getCampoModifica().getText().equals("")) {
					spedizione = pagamentoIndirizzo.getCampoModifica().getText();
				}
				LibroCard libroCard = libroCardDao.getLibroCard(email);
				int puntiAccumulati = libroCard.getSaldoPunti();
				if(!bookMap.keySet().isEmpty()) {
					for(Libro libro : bookMap.keySet()) {
						puntiAccumulati += (libro.getPunti() * bookMap.get(libro));
					}

					Ordine ordine = new Ordine(id, data, bookMap, costoTotale, pagamento, email, spedizione, puntiAccumulati);
					if(ordineDao.insertOrder(ordine) == true)
						System.out.println("Ordine effettuato");
				}
				else{
					System.out.println("Ordine NON effettuato");
				}

			}

		}
	}

}
