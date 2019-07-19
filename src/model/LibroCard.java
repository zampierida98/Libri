package model;

import java.sql.Date;

/**
 * Ogni LibroCard ha un numero identificativo, una data di emissione e il totale dei punti raccolti.
 */
public class LibroCard {

	private int idLibroCard;
	private Date dataEmissione;
	private int saldoPunti;
	private String email;
	
	public LibroCard(Date dataEmissione, int saldoPunti, String email) {
		this.idLibroCard = Math.abs(email.hashCode());//ATTENZIONE scelta progettuale
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

	public Date getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(Date dataEmissione) {
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
