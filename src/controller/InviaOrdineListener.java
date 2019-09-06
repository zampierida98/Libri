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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	private HashMap<Libro, Integer> bookMap = new HashMap<Libro, Integer>();


	public InviaOrdineListener(NuovoOrdine nuovoOrdine, PagamentoIndirizzo pagamentoIndirizzo) {
		this.nuovoOrdine = nuovoOrdine;
		this.pagamentoIndirizzo = pagamentoIndirizzo;
	}


	public void setBookMap() {
		for(Libro libro : nuovoOrdine.getArrayLibri()) {
			JCheckBox checkBox = nuovoOrdine.getMapLibro().get(libro);
			if(checkBox.isSelected()) {
				JComboBox<Integer> quantita = nuovoOrdine.getMapQuantita().get(libro);
				bookMap.put(libro, (Integer)quantita.getSelectedItem());
			}
		}
	}

	/*
	private int idOrdine;
	private Date data;
	private HashMap<Libro, Integer> listaLibri;
	private double costoTotale;
	private Pagamento pagamento;
	private String email;
	private String spedizione;
	private int puntiAccumulati;

	int id1 = (int) Instant.now().getEpochSecond();
    HashMap<Libro, Integer> ll1 = new HashMap<Libro, Integer>();
    ll1.put(l1, 1);
    ll1.put(l2, 3);
    double tot1 = l1.getPrezzo() + (l2.getPrezzo() * 3);
    int p1 = libroCardDao.getLibroCard("zampierida98@gmail.com").getSaldoPunti() + l1.getPunti() + (l2.getPunti() * 3);

    Ordine o1 = new Ordine(id1, Date.valueOf(LocalDate.now()), ll1, tot1, Pagamento.CARTA, "zampierida98@gmail.com", u1.getIndirizzo(), p1);
    ordineDao.insertOrder(o1);
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		//associo la quantita ai libri selezionati
		this.setBookMap();

		JButton ordinaB = (JButton)e.getSource();
		if(ordinaB.getText().equals("Invia dati")) {
			OrdineDao ordineDao = new OrdineDaoImpl();
			LibroCardDao libroCardDao = new LibroCardDaoImpl();

			if(!nuovoOrdine.getEmail().isEnabled()) {
				//costruzione di un ordine per l'utente registrato

				int id = (int) Instant.now().getEpochSecond();
				Date data = Date.valueOf(LocalDate.now());
				String email = VisualizzaProfilo.getEmail();
				System.out.println(email);

				HashMap<Libro, Integer> listaLibri = new HashMap<Libro, Integer>();
				int costoTotale = 0;
				for(Libro libro : bookMap.keySet()) {
					int copie = bookMap.get(libro);
					if(copie > 0) {
						costoTotale += (libro.getPrezzo() * copie);
						listaLibri.put(libro, copie);
					}
				}

				Pagamento pagamento = null;
				for(JRadioButton tipoPagamento : pagamentoIndirizzo.getArrayPagamento()) {
					if(tipoPagamento.isSelected()) {
						pagamento = Pagamento.valueOf(tipoPagamento.getText());
						break;
					}
				}

				if(pagamento == null) {
					System.out.println("Ordine NON effettuato");
				} else {
					String spedizione = VisualizzaProfilo.getIndirizzo();
					if(!pagamentoIndirizzo.getCampoModifica().getText().equals("")) {
						spedizione = pagamentoIndirizzo.getCampoModifica().getText();
					}

					LibroCard libroCard = libroCardDao.getLibroCard(email);
					int puntiAccumulati = libroCard.getSaldoPunti();
					for(Libro libro : listaLibri.keySet())
						puntiAccumulati += (libro.getPunti() * listaLibri.get(libro));

					Ordine ordine = new Ordine(id, data, listaLibri, costoTotale, pagamento, email, spedizione, puntiAccumulati);
					if(ordineDao.insertOrder(ordine) == true)
						System.out.println("Ordine effettuato");
				}
			} else {
				//costruzione di un ordine per l'utente non registrato

				int id = (int) Instant.now().getEpochSecond();
				Date data = Date.valueOf(LocalDate.now());
				String email = nuovoOrdine.getEmail().getText();
				System.out.println(email);

				HashMap<Libro, Integer> listaLibri = new HashMap<Libro, Integer>();
				int costoTotale = 0;
				for(Libro libro : bookMap.keySet()) {
					int copie = bookMap.get(libro);
					if(copie > 0) {
						costoTotale += (libro.getPrezzo() * copie);
						listaLibri.put(libro, copie);
					}
				}

				Pagamento pagamento = null;
				for(JRadioButton tipoPagamento : pagamentoIndirizzo.getArrayPagamento()) {
					if(tipoPagamento.isSelected()) {
						pagamento = Pagamento.valueOf(tipoPagamento.getText());
						break;
					}
				}

				if(pagamento == null) {
					System.out.println("Ordine NON effettuato");
				} else {
					String spedizione = "";
					if(pagamentoIndirizzo.getCampoModifica().getText().equals("")) {
						System.out.println("Ordine NON effettuato");
					} else {
						spedizione = pagamentoIndirizzo.getCampoModifica().getText();
					}

					int puntiAccumulati = 0;
					for(Libro libro : listaLibri.keySet())
						puntiAccumulati += (libro.getPunti() * listaLibri.get(libro));

					Ordine ordine = new Ordine(id, data, listaLibri, costoTotale, pagamento, email, spedizione, puntiAccumulati);
					if(ordineDao.insertOrder(ordine) == true) {
						System.out.println("Ordine effettuato");
						
						//restituzione codice ordine
						JFrame codice = new JFrame();
						JPanel pannello = new JPanel();
						JLabel codiceRestituito = new JLabel(String.valueOf(id));
						pannello.add(codiceRestituito);
						codice.add(pannello);
						codice.pack();
						codice.setVisible(true);
						codice.setResizable(false);
					}
				}
			}
		}
	}

}
