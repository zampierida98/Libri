import controller.DaoManager;
import model.UtenteDao;
import model.UtenteDaoImpl;

public class Main {

	public static void main(String[] args) {
		DaoManager daoManager = new DaoManager();
        UtenteDao utenteDao = new UtenteDaoImpl();
        System.out.println(utenteDao.getAllUsers());
	}

}
