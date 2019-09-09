package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import javafx.scene.shape.Box;
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
					return;
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
					return;
				} else {
					String spedizione = "";
					if(pagamentoIndirizzo.getCampoModifica().getText().equals("")) {
						System.out.println("Ordine NON effettuato");
						return;
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
						pannello.setLayout(new BoxLayout(pannello, BoxLayout.Y_AXIS));
						JLabel titolo = new JLabel("Il codice del tuo ordine �:");
						JLabel rigaVuota1 = new JLabel("<html><div><br></div></html>");
						JLabel codiceRestituito = new JLabel(String.valueOf(id));
						JLabel rigaVuota2 = new JLabel("<html><div><br></div></html>");
						JLabel messaggio = new JLabel("Salvalo per accedere ai dati dell'ordine successivamente!");
						
						titolo.setAlignmentX(Component.CENTER_ALIGNMENT);
						codiceRestituito.setAlignmentX(Component.CENTER_ALIGNMENT);
						codiceRestituito.setForeground(Color.RED);
						messaggio.setAlignmentX(Component.CENTER_ALIGNMENT);
						pannello.add(titolo);
						pannello.add(rigaVuota1);
						pannello.add(codiceRestituito);
						pannello.add(rigaVuota2);
						pannello.add(messaggio);
						
						codice.add(pannello);
						codice.pack();
						codice.setResizable(false);
						codice.setLocationRelativeTo(null);
						codice.setVisible(true);
					}
				}
			}
		}
	}

}
