package model;

/**
 * Per ogni libro si memorizza il codice ISBN, il titolo, gli autori, la casa editrice, l'anno di pubblicazione, il genere, il prezzo ed una breve descrizione.
 * Ogni libro ha anche associato il numero di punti che vengono caricati sulle LibroCard in caso di acquisto.
 */
public class Libro {

	private String ISBN;
	private String titolo;
	private String autori;
	private String casaEditrice;
	private int annoPubblicazione;
	private String genere;
	private double prezzo;
	private String descrizione;
	private int punti;

	public Libro(String ISBN, String titolo, String autori, String casaEditrice, int annoPubblicazione, String genere, double prezzo, String descrizione, int punti) {
		this.ISBN = ISBN;
		this.titolo = titolo;
		this.autori = autori;
		this.casaEditrice = casaEditrice;
		this.annoPubblicazione = annoPubblicazione;
		this.genere = genere;
		this.prezzo = prezzo;
		this.descrizione = descrizione;
		this.punti = punti;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getAutori() {
		return autori;
	}

	public void setAutori(String autori) {
		this.autori = autori;
	}

	public String getCasaEditrice() {
		return casaEditrice;
	}

	public void setCasaEditrice(String casaEditrice) {
		this.casaEditrice = casaEditrice;
	}

	public int getAnnoPubblicazione() {
		return annoPubblicazione;
	}

	public void setAnnoPubblicazione(int annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public int getPunti() {
		return punti;
	}

	public void setPunti(int punti) {
		this.punti = punti;
	}
	

	@Override
	public String toString() {
		return "[ISBN=" + ISBN + " titolo=" + titolo + "\n]";
	}

}
