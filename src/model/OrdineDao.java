package model;

import java.util.List;

/**
 * Definisce le operazioni che si possono fare sul database con l'oggetto Ordine (rispetta il design pattern DAO).
 */
public interface OrdineDao {
	public boolean insertOrder(Ordine o);			//nuovo ordine
	public List<Ordine> getAllOrders(String email);	//vedere tutti gli ordini che ha effettuato nel tempo con il totale dei punti accumulati per ogni ordine
	public Ordine getOrder(int idOrdine);			//accedere agli ordini tramite il codice dell'ordine
	public List<Ordine> getOrdersStatus();			//stato di tutti gli ordini
}
