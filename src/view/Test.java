package view;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.DaoManager;
import model.Libro;
import model.LibroCard;
import model.LibroCardDao;
import model.LibroCardDaoImpl;
import model.LibroDao;
import model.LibroDaoImpl;
import model.Ordine;
import model.OrdineDao;
import model.OrdineDaoImpl;
import model.Pagamento;
import model.Utente;
import model.UtenteDao;
import model.UtenteDaoImpl;

/**
 * Classe che effettua prove sul database e mostra come si trattano i dati.
 * 
 * @remove quando si comincia a disegnare l'interfaccia
 */
public class Test {

	public static void main(String[] args) {
		DaoManager dao = DaoManager.getInstance();
        UtenteDao utenteDao = new UtenteDaoImpl();
        LibroCardDao libroCardDao = new LibroCardDaoImpl();
        LibroDao libroDao = new LibroDaoImpl();
        OrdineDao ordineDao = new OrdineDaoImpl();
        /*
        // utente
        Utente u1 = utenteDao.getUser("zampierida98@gmail.com");
        System.out.println(u1.getPassword());
        
        u1.setPassword("nuovaPassword");
        if(utenteDao.updateUser("zampierida98@gmail.com", u1) == true) {
	        u1 = utenteDao.getUser("zampierida98@gmail.com");
	        System.out.println(u1.getPassword());
        }
        
        Utente u2 = new Utente("a","aa","aaa","1234","email@gmail.com","ciaoSuspa");
        if(utenteDao.insertUtente(u2) == true) {
	        u2 = utenteDao.getUser("email@gmail.com");
	        System.out.println(u2.getNome());
        }
        
        System.out.println(utenteDao.login("email@gmail.com", "admin"));
        System.out.println(utenteDao.login("email@gmail.com", "ciaoSuspa"));
        
        // libro card
        LibroCard lc1 = new LibroCard(Date.valueOf(LocalDate.now()), 0, "zampierida98@gmail.com");
        if(libroCardDao.insertLibroCard(lc1) == true) {
	    	lc1 = libroCardDao.getLibroCard("zampierida98@gmail.com");
	    	System.out.println(lc1.getIdLibroCard() + " " + lc1.getDataEmissione() + " " + lc1.getSaldoPunti());
        }
        
        LibroCard lc2 = new LibroCard(Date.valueOf(LocalDate.now()), 0, "raffa@gmail.com");
        if(libroCardDao.insertLibroCard(lc2) == true) {
        	lc2 = libroCardDao.getLibroCard("raffa@gmail.com");
	    	System.out.println(lc2.getIdLibroCard() + " " + lc2.getDataEmissione() + " " + lc2.getSaldoPunti());
        }
        
        LibroCard lc3 = new LibroCard(Date.valueOf(LocalDate.of(2019, 2, 28)), 23, "email@gmail.com");
        if(libroCardDao.insertLibroCard(lc3) == true) {
        	lc3 = libroCardDao.getLibroCard("email@gmail.com");
	    	System.out.println(lc3.getIdLibroCard() + " " + lc3.getDataEmissione() + " " + lc3.getSaldoPunti());
        }
        
        List<LibroCard> llc = libroCardDao.getAllLibroCard();
        for(LibroCard lc : llc)
        	System.out.println(String.format("%010d", lc.getIdLibroCard()) + " " + lc.getDataEmissione() + " " + lc.getSaldoPunti());
        */
        // libro
        Libro l1 = new Libro("a","a","a","a",1,"a",1.1,"a",1);
        Libro l2 = new Libro("b","b","b","b",2,"b",2.2,"b",2);
        Libro l3 = new Libro("c","c","c","c",3,"c",3.3,"c",3);
        /*
        libroDao.insertBook(l1);
        libroDao.insertBook(l2);
        libroDao.insertBook(l3);
        */
        // ordine
        /*
        int id1 = (int) Instant.now().getEpochSecond();
        List<Libro> ll1 = new ArrayList<>();
        ll1.add(l1);
        ll1.add(l2);
        double tot1 = l1.getPrezzo() + l2.getPrezzo();
        int p1 = libroCardDao.getLibroCard("zampierida98@gmail.com").getSaldoPunti() + l1.getPunti() + l2.getPunti();
        
        Ordine o1 = new Ordine(id1, Date.valueOf(LocalDate.now()), ll1, tot1, Pagamento.CARTA, "zampierida98@gmail.com", p1);
        ordineDao.insertOrder(o1);
        
        int id2 = (int) Instant.now().getEpochSecond();
        List<Libro> ll2 = new ArrayList<>();
        ll2.add(l1);
        ll2.add(l2);
        ll2.add(l3);
        double tot2 = l1.getPrezzo() + l2.getPrezzo() + l3.getPrezzo();
        int p2 = libroCardDao.getLibroCard("zampierida98@gmail.com").getSaldoPunti() + l1.getPunti() + l2.getPunti() + l3.getPunti();
        
        Ordine o2 = new Ordine(id2, Date.valueOf(LocalDate.now()), ll2, tot2, Pagamento.PAYPAL, "zampierida98@gmail.com", p2);
        ordineDao.insertOrder(o2);
        */
        List<Ordine> lo = ordineDao.getAllOrders("zampierida98@gmail.com");
        for(Ordine o : lo) {
        	System.out.println(String.format("%010d %s %.2f %d", o.getIdOrdine(), o.getEmail(), o.getCostoTotale(), o.getPuntiAccumulati()));
        	for(Libro l : o.getListaLibri())
        		System.out.println("\t" + l.getISBN() + " " + l.getPunti());
        }
	}
}
