package model;

/**
 * Ogni LibroCard ha un numero identificativo, una data di emissione e il totale dei punti raccolti.
 */
public class LibroCard {

	private int idLibroCard;
	private int dataEmissione;//ATTENZIONE bisogna gestire tutti i casi
	private int saldoPunti;
	private String email;
	
	public LibroCard(int idLibroCard, int dataEmissione, int saldoPunti, String email) {
		this.idLibroCard = idLibroCard;
		this.dataEmissione = dataEmissione;
		this.saldoPunti = saldoPunti;
		this.email = email;
	}

	public int getIdLibroCard() {
		return idLibroCard;
	}

	public void setIdLibroCard(int idLibroCard) {
		this.idLibroCard = idLibroCard;
	}

	public int getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(int dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public int getSaldoPunti() {
		return saldoPunti;
	}

	public void setSaldoPunti(int saldoPunti) {
		this.saldoPunti = saldoPunti;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
