package model;

public interface UtenteDao {
	public boolean insertUtente(Utente u);
	public Utente getUser(String email);
	public boolean updateUser(String email, Utente u);
	//verificare il saldo punti (query con libro card)
	//verificare lo stato dei loro ordini (query con ordine)
	//accesso (select di email e password)
}
