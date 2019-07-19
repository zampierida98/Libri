package model;

import java.util.List;

/**
 * Definisce le operazioni che si possono fare sul database con l'oggetto LibroCard (rispetta il design pattern DAO).
 */
public interface LibroCardDao {
	public boolean insertLibroCard(LibroCard lc);
	public LibroCard getLibroCard(String email);
	public List<LibroCard> getAllLibroCard();
}
