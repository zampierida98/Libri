package model;

public class LibroCard {
	
	private int idLibroCard;
	private int dataEmissione;//ATTENZIONE bisogna gestire tutti i casi
	private int saldoPunti;
	
	public LibroCard(int idLibroCard, int dataEmissione, int saldoPunti) {
		super();
		this.idLibroCard = idLibroCard;
		this.dataEmissione = dataEmissione;
		this.saldoPunti = saldoPunti;
	}
	
}
