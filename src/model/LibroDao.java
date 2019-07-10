package model;

/**
 * Definisce le operazioni che si possono fare sul database con l'oggetto Libro (rispetta il design pattern DAO).
 */
public interface LibroDao {
	public boolean insertBook(Libro l);
	//cancellazione e aggiornamento?
}
