package view;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controller.DaoManager;
import model.Classifica;
import model.ClassificaDao;
import model.ClassificaDaoImpl;
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
	
	private static void testUtente() {
		UtenteDao utenteDao = new UtenteDaoImpl();

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
	}
	
	private static void testLibroCard() {
		LibroCardDao libroCardDao = new LibroCardDaoImpl();

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
	}
	
	private static void testLibro() {
		LibroDao libroDao = new LibroDaoImpl();

        Libro l1 = new Libro("a","a","a","a",1,"a",1.1,"a",1);
        Libro l2 = new Libro("b","b","b","b",2,"b",2.2,"b",2);
        Libro l3 = new Libro("c","c","c","c",3,"c",3.3,"c",3);
        
        libroDao.insertBook(l1);
        libroDao.insertBook(l2);
        libroDao.insertBook(l3);
	}
	
	private static void testOrdine() throws InterruptedException {
		OrdineDao ordineDao = new OrdineDaoImpl();
		LibroCardDao libroCardDao = new LibroCardDaoImpl();
		UtenteDao utenteDao = new UtenteDaoImpl();
		LibroDao libroDao = new LibroDaoImpl();
		
		// utenti e libri
		Utente u1 = utenteDao.getUser("zampierida98@gmail.com");
		List<Libro> ll = libroDao.getAllBooks();
		Libro l1 = null, l2 = null, l3 = null;
		if(ll.size() == 3) {
			l1 = ll.get(0);
	        l2 = ll.get(1);
	        l3 = ll.get(2);
		} else {
			return;
		}
		/*
        int id1 = (int) Instant.now().getEpochSecond();
        List<Libro> ll1 = new ArrayList<>();
        ll1.add(l1);
        ll1.add(l2);
        double tot1 = l1.getPrezzo() + l2.getPrezzo();
        int p1 = libroCardDao.getLibroCard("zampierida98@gmail.com").getSaldoPunti() + l1.getPunti() + l2.getPunti();
        
        Ordine o1 = new Ordine(id1, Date.valueOf(LocalDate.now()), ll1, tot1, Pagamento.CARTA, "zampierida98@gmail.com", u1.getIndirizzo(), p1);
        ordineDao.insertOrder(o1);
        
        Thread.sleep(5000);
        int id2 = (int) Instant.now().getEpochSecond();
        List<Libro> ll2 = new ArrayList<>();
        ll2.add(l1);
        ll2.add(l2);
        ll2.add(l3);
        double tot2 = l1.getPrezzo() + l2.getPrezzo() + l3.getPrezzo();
        int p2 = libroCardDao.getLibroCard("zampierida98@gmail.com").getSaldoPunti() + l1.getPunti() + l2.getPunti() + l3.getPunti();

        Ordine o2 = new Ordine(id2, Date.valueOf(LocalDate.now()), ll2, tot2, Pagamento.PAYPAL, "zampierida98@gmail.com", "nuovo_indirizzo", p2);
        ordineDao.insertOrder(o2);
        
        List<Ordine> lo = ordineDao.getAllOrders("zampierida98@gmail.com");
        for(Ordine o : lo) {
        	System.out.println(String.format("%010d %s %.2f %d", o.getIdOrdine(), o.getEmail(), o.getCostoTotale(), o.getPuntiAccumulati()));
        	for(Libro l : o.getListaLibri())
        		System.out.println("\t" + l.getISBN() + " " + l.getPunti());
        }
        
        Thread.sleep(5000);
        int id3 = (int) Instant.now().getEpochSecond();
        List<Libro> ll3 = new ArrayList<>();
        ll3.add(l3);
        ll3.add(l2);
        double tot3 = l3.getPrezzo() + l2.getPrezzo();
        int p3 = libroCardDao.getLibroCard("raffa@gmail.com").getSaldoPunti() + l3.getPunti() + l2.getPunti();

        Ordine o3 = new Ordine(id3, Date.valueOf(LocalDate.now()), ll3, tot3, Pagamento.CONTRASSEGNO, "raffa@gmail.com", "prova", p3);
        ordineDao.insertOrder(o3);
        */
        HashMap<String, List<Ordine>> mo = ordineDao.getOrdersStatus();
        for(String email : mo.keySet()) {
        	List<Ordine> os = mo.get(email);
        	for(Ordine o : os) {
            	System.out.println(String.format("%010d %s %.2f %d", o.getIdOrdine(), o.getEmail(), o.getCostoTotale(), o.getPuntiAccumulati()));
            	for(Libro l : o.getListaLibri())
            		System.out.println("\t" + l.getISBN() + " " + l.getPunti());
            }
        }
	}
	
	private static void testClassifica() {
		ClassificaDao classificaDao = new ClassificaDaoImpl();
		
        Classifica c1 = new Classifica("a", 1, 10);
        Classifica c2 = new Classifica("b", 2, 20);
        
        if(classificaDao.insertClassifica(c1) == true) {
        	c1.setPosizione(2);
        	c1.setSettimane(1);
        	classificaDao.updateClassifica(c1.getISBN(), c1);
        }
        if(classificaDao.insertClassifica(c2) == true) {
        	c2.setPosizione(3);
        	c2.setSettimane(1);
        	classificaDao.updateClassifica(c2.getISBN(), c2);
        }

        HashMap<String, List<Classifica>> mc = classificaDao.getClassifiche();
        for(String genere : mc.keySet()) {
        	List<Classifica> pos = mc.get(genere);
        	for(Classifica p : pos)
        		System.out.println(String.format("genere=%s, ISBN=%s, posizione=%d, settimane=%d", genere, p.getISBN(), p.getPosizione(), p.getSettimane()));
        }
	}

	public static void main(String[] args) throws InterruptedException {
		DaoManager dao = DaoManager.getInstance();
		/*testUtente();
		testLibroCard();
		testLibro();*/
		testOrdine();
		//testClassifica();
	}
}
