package model;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

/**
 * Per ogni posizione della classifica di vendita si indica da quante settimane il libro è in quella posizione.
 */
public class Classifica {

	private String ISBN;
	private int posizione;
	private Date data;

	public Classifica(String ISBN, int posizione, Date data) {
		this.ISBN = ISBN;
		this.posizione = posizione;
		this.data = data;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public int getPosizione() {
		return posizione;
	}

	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public long settimaneInClassifica(Date d1, Date d2) {
		long diff = d2.getTime() - d1.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) / 7;
	}

}
