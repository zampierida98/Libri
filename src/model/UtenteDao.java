package model;

/**
 * Definisce le operazioni che si possono fare sul database con l'oggetto Utente (rispetta il design pattern DAO).
 */
public interface UtenteDao {
	public boolean insertUtente(Utente u);
	public Utente getUser(String email);
	public boolean updateUser(String email, Utente u);
	public boolean login(String email, String password);
}
