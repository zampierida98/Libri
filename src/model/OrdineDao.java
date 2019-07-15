package model;

import java.util.HashMap;
import java.util.List;

/**
 * Definisce le operazioni che si possono fare sul database con l'oggetto Ordine (rispetta il design pattern DAO).
 */
public interface OrdineDao {
	public boolean insertOrder(Ordine o);
	public List<Ordine> getAllOrders(String email);
	public Ordine getOrder(int idOrdine);
	public HashMap<String, List<Ordine>> getOrdersStatus();
}
