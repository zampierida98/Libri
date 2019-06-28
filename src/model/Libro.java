package model;

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

	@Override
	public String toString() {
		return "Libro [ISBN=" + ISBN + ", titolo=" + titolo + ", autori=" + autori + ", casaEditrice=" + casaEditrice
				+ ", annoPubblicazione=" + annoPubblicazione + ", genere=" + genere + ", prezzo=" + prezzo
				+ ", descrizione=" + descrizione + ", punti=" + punti + "]";
	}
	
}
