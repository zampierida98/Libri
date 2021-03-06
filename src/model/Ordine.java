package model;

import java.sql.Date;
import java.util.HashMap;

/**
 * Per ogni ordine si memorizzano un codice univoco, la data, i libri che lo compongono, il costo totale, il tipo di pagamento, l'utente che lo ha effettuato ed eventualmente il saldo punti della sua LibroCard.
 * Gli utenti registrati possono specificare ad ogni ordine un indirizzo di spedizione diverso da quello di residenza.
 */
public class Ordine {

	private int idOrdine;
	private Date data;
	private HashMap<Libro, Integer> listaLibri;
	private double costoTotale;
	private Pagamento pagamento;
	private String email;
	private String spedizione;
	private int puntiAccumulati;

	public Ordine(int idOrdine, Date data, HashMap<Libro, Integer> listaLibri, double costoTotale, Pagamento pagamento, String email, String spedizione, int puntiAccumulati) {
		this.idOrdine = idOrdine;
		this.data = data;
		this.listaLibri = listaLibri;
		this.costoTotale = costoTotale;
		this.pagamento = pagamento;
		this.email = email;
		this.spedizione = spedizione;
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

	public HashMap<Libro, Integer> getListaLibri() {
		return listaLibri;
	}

	public void setListaLibri(HashMap<Libro, Integer> listaLibri) {
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

	public String getSpedizione() {
		return spedizione;
	}

	public void setSpedizione(String spedizione) {
		this.spedizione = spedizione;
	}

	public int getPuntiAccumulati() {
		return puntiAccumulati;
	}

	public void setPuntiAccumulati(int puntiAccumulati) {
		this.puntiAccumulati = puntiAccumulati;
	}

}
