package model;

/**
 * Per gli utenti si memorizzano nome, cognome, indirizzo-CAP-città, numero di telefono ed email.
 * Se l'utente è registrato si memorizzano anche password e LibroCard associata.
 */
public class Utente {

	private String nome;
	private String cognome;
	private String indirizzo;
	private String telefono;
	private String email;
	private String password;

	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", indirizzo=" + indirizzo + ", telefono=" + telefono
				+ ", email=" + email + ", password=" + password + "]";
	}

	public Utente(String nome, String cognome, String indirizzo, String telefono, String email, String password) {
		this.nome = nome;
		this.cognome = cognome;
		this.indirizzo = indirizzo;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
