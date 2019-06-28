package model;

public class UtenteRegistrato extends Utente {
	
	private String password;
	private int idLibroCard;
	private String spedizione;
	
	public UtenteRegistrato(int idUtente, String nome, String cognome, String indirizzo, String telefono, String email, String password, int idLibroCard, String spedizione) {
		super(idUtente, nome, cognome, indirizzo, telefono, email);
		this.password = password;
		this.idLibroCard = idLibroCard;
		this.spedizione = spedizione;
	}
	
}
