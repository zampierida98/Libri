package model;

public interface UtenteDao {
	public Utente getUser(int idUtente);	//accedere al loro profilo (visualizzare i dati anagrafici)
	public void updateUser(Utente u);		//modificare i dati anagrafici
	//verificare il saldo punti e lo stato dei loro ordini
}
