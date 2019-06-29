package model;

public class LibroCard {
	
	private int idLibroCard;
	private int idUtente;
	private int dataEmissione;//ATTENZIONE bisogna gestire tutti i casi
	private int saldoPunti;
	
	public LibroCard(int idLibroCard, int idUtente, int dataEmissione, int saldoPunti) {
		this.idLibroCard = idLibroCard;
		this.idUtente = idUtente;
		this.dataEmissione = dataEmissione;
		this.saldoPunti = saldoPunti;
	}
	
}
