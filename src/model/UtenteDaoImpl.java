package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import controller.DaoManager;

public class UtenteDaoImpl implements UtenteDao {

	/**
	 * Inserisce nel database i dati relativi ad un utente.
	 * 
	 * @param u l'utente da inserire nel database
	 * @return true se la query va a buon fine, false altrimenti
	 */
	@Override
	public boolean insertUtente(Utente u) {
		boolean result = false;
		final String query = "INSERT INTO Utente VALUES(?,?,?,?,?,?)";

		Connection con = DaoManager.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, u.getNome());
			pst.setString(2, u.getCognome());
			pst.setString(3, u.getIndirizzo());
			pst.setString(4, u.getTelefono());
			pst.setString(5, u.getEmail());
			pst.setString(6, u.getPassword());

			result = pst.execute();
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return result;
	}

	/**
	 * Restituisce i dati relativi ad un utente presente nel database.
	 * 
	 * @param email l'email dell'utente di cui si vogliono i dati
	 * @return null se l'utente non è presente nel database, un'istanza di Utente altrimenti
	 */
	@Override
	public Utente getUser(String email) {
		Utente u = null;
		final String query = "SELECT * FROM Utente WHERE email=?";

		Connection con = DaoManager.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				u = mapRowToUtente(rs);
			}

			con.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return u;
	}

	/**
	 * Aggiorna i dati relativi ad un utente presente nel database.
	 * 
	 * @param email l'email dell'utente di cui si vogliono aggiornare i dati
	 * @param u l'istanza di Utente che contiene i dati aggiornati
	 * @return true se la query va a buon fine, false altrimenti
	 */
	@Override
	public boolean updateUser(String email, Utente u) {
		boolean result = false;
		final String query = "UPDATE Utente SET nome=?, cognome=?, indirizzo=?, telefono=?, password=? WHERE email=?";

		Connection con = DaoManager.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, u.getNome());
			pst.setString(2, u.getCognome());
			pst.setString(3, u.getIndirizzo());
			pst.setString(4, u.getTelefono());
			pst.setString(5, u.getPassword());
			pst.setString(6, email);

			pst.execute();
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return result;
	}

	/**
	 * Mappa il risultato di una query in un oggetto di tipo Utente.
	 * 
	 * @param rs il ResultSet che rappresenta una riga della query
	 * @return un'istanza di Utente
	 */
	private Utente mapRowToUtente(ResultSet rs) throws SQLException {
		return new Utente(rs.getString("nome"), rs.getString("cognome"), rs.getString("indirizzo"), rs.getString("telefono"), rs.getString("email"), rs.getString("password"));
	}

}
