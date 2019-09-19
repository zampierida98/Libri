package model;

import java.util.HashMap;
import java.util.List;

/**
 * Definisce le operazioni che si possono fare sul database con l'oggetto Classifica (rispetta il design pattern DAO).
 */
public interface ClassificaDao {
	public boolean insertClassifica(Classifica c);
	public boolean updateClassifica(String ISBN, Classifica c);
	public HashMap<String, List<Classifica>> getClassifiche();
}
