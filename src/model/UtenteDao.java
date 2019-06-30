package model;

public interface UtenteDao {
	public boolean insertUtente(Utente u);	//registrazione
	public Utente getUser(int idUtente);	//accedere al loro profilo (visualizzare i dati anagrafici)
	public boolean updateUser(Utente u);	//modificare i dati anagrafici
	//verificare il saldo punti (query con libro card)
	//verificare lo stato dei loro ordini (query con ordine)
}
