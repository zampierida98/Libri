package model;

import java.util.List;

public interface OrdineDao {
	public List<Ordine> getAllOrders(Utente u);		//vedere tutti gli ordini che ha effettuato nel tempo con il totale dei punti accumulati per ogni ordine
	public Ordine getOrder(int idOrdine);			//accedere agli ordini tramite il codice dell'ordine
	public List<Ordine> getOrdersStatus();			//stato di tutti gli ordini
}
