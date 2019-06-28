package model;

public class Ordine {
	
	private int idOrdine;
	private int data;//ATTENZIONE bisogna gestire tutti i casi
	private int idUtente;
	private double costoTotale;
	private int pagamento;//ATTENZIONE solo alcuni valori
	private int saldoPunti;
	//lista libri (da tabella n..n)
	
	public Ordine(int idOrdine, int data, int idUtente, double costoTotale, int pagamento, int saldoPunti) {
		this.idOrdine = idOrdine;
		this.data = data;
		this.idUtente = idUtente;
		this.costoTotale = costoTotale;
		this.pagamento = pagamento;
		this.saldoPunti = saldoPunti;
	}
	
}
