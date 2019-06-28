package model;

public class Utente {
	
	private int idUtente;
	private String nome;
	private String cognome;
	private String indirizzo;
	private String telefono;
	private String email;
	
	public Utente(int idUtente, String nome, String cognome, String indirizzo, String telefono, String email) {
		this.idUtente = idUtente;
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Utente [idUtente=" + idUtente + ", nome=" + nome + ", cognome=" + cognome + ", indirizzo=" + indirizzo
				+ ", telefono=" + telefono + ", email=" + email + "]";
	}
	
}
