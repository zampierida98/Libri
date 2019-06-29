import controller.DaoManager;
import model.Utente;
import model.UtenteDao;
import model.UtenteDaoImpl;

public class Main {

	public static void main(String[] args) {
		DaoManager dao = new DaoManager();
        UtenteDao utenteDao = new UtenteDaoImpl();
        System.out.println(utenteDao.getUser(1));
        System.out.println(utenteDao.getUser(2));
        
        Utente nuovo = utenteDao.getUser(2);
        nuovo.setEmail("verifica_update3");
        utenteDao.updateUser(nuovo);
        System.out.println(utenteDao.getUser(2));
	}

}
