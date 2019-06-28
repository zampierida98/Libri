package model;

import java.util.List;

public interface UtenteDao {
	public List<Utente> getAllUsers();
	public Utente getUser(int idUtente);
	public void updateUser(Utente user);
	public void deleteUser(Utente user);
}
