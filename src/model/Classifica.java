package model;

/**
 * Per ogni posizione della classifica di vendita si indica da quante settimane il libro è in quella posizione.
 */
public class Classifica {

	private String ISBN;
	private int posizione;
	private int settimane;

	public Classifica(String ISBN, int posizione, int settimane) {
		this.ISBN = ISBN;
		this.posizione = posizione;
		this.settimane = settimane;
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

	public int getSettimane() {
		return settimane;
	}

	public void setSettimane(int settimane) {
		this.settimane = settimane;
	}

}
