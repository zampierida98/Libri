package model;

import java.util.List;

/**
 * Per ogni ordine si memorizzano un codice univoco, la data, i libri che lo compongono, il costo totale, il tipo di pagamento, l'utente che lo ha effettuato ed eventualmente il saldo punti della sua LibroCard.
 */
public class Ordine {

	private int idOrdine;
	private int data;//ATTENZIONE bisogna gestire tutti i casi
	private List<Libro> listaLibri;//ATTENZIONE da tabella n..n
	private double costoTotale;
	private int pagamento;//ATTENZIONE solo alcuni valori (carta di credito, paypal o contrassegno)
	private String email;
	private int saldoPunti;//ATTENZIONE da tabella LibroCard
	
	public Ordine(int idOrdine, int data, List<Libro> listaLibri, double costoTotale, int pagamento, String email, int saldoPunti) {
		this.idOrdine = idOrdine;
		this.data = data;
		this.listaLibri = listaLibri;
		this.costoTotale = costoTotale;
		this.pagamento = pagamento;
		this.email = email;
		this.saldoPunti = saldoPunti;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
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

	public int getPagamento() {
		return pagamento;
	}

	public void setPagamento(int pagamento) {
		this.pagamento = pagamento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSaldoPunti() {
		return saldoPunti;
	}

	public void setSaldoPunti(int saldoPunti) {
		this.saldoPunti = saldoPunti;
	}

}
