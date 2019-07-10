package controller;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 * Gestore delle connessioni al database (rispetta il design pattern Singleton).
 */
public class DaoManager {

	private final static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
	private final static String URL = "jdbc:derby:LIBRI";
	private static BasicDataSource ds = null;
	private static DaoManager instance = new DaoManager();

	private DaoManager() {
		ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(URL);
		ds.setUsername("admin");
		ds.setPassword("admin");
	}

	public static DaoManager getInstance(){
		return instance;
	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return con;
	}

}
