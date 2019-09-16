package model;

import java.util.List;

/**
 * Definisce le operazioni che si possono fare sul database con l'oggetto Libro (rispetta il design pattern DAO).
 */
public interface LibroDao {
	public boolean insertBook(Libro l);
	public List<Libro> getAllBooks();
}
