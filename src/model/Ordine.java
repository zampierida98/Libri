package model;

import java.sql.Date;
import java.util.List;

/**
 * Per ogni ordine si memorizzano un codice univoco, la data, i libri che lo compongono, il costo totale, il tipo di pagamento, l'utente che lo ha effettuato ed eventualmente il saldo punti della sua LibroCard.
 */
public class Ordine {

	private int idOrdine;
	private Date data;
	private List<Libro> listaLibri;//ATTENZIONE da tabella n..n
	private double costoTotale;
	private Pagamento pagamento;
	private String email;
	private int puntiAccumulati;//ATTENZIONE nel nuovo ordine si parte dal saldo e si aggiungono quelli della lista

	public Ordine(int idOrdine, Date data, List<Libro> listaLibri, double costoTotale, Pagamento pagamento, String email, int puntiAccumulati) {
		this.idOrdine = idOrdine;
		this.data = data;
		this.listaLibri = listaLibri;
		this.costoTotale = costoTotale;
		this.pagamento = pagamento;
		this.email = email;
		this.puntiAccumulati = puntiAccumulati;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public List<Libro> getListaLibri() {
		return listaLibri;
	}

	public void setListaLibri(List<Libro> listaLibri) {
		this.listaLibri = listaLibri;
	}

	public double getCostoTotale() {
		return costoTotale;
	}

	public void setCostoTotale(double costoTotale) {
		this.costoTotale = costoTotale;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPuntiAccumulati() {
		return puntiAccumulati;
	}

	public void setPuntiAccumulati(int puntiAccumulati) {
		this.puntiAccumulati = puntiAccumulati;
	}

}
