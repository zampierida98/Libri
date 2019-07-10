package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.DaoManager;

public class LibroCardDaoImpl implements LibroCardDao {
	
	/**
	 * Inserisce nel database i dati relativi ad una LibroCard.
	 * 
	 * @param lc la LibroCard da inserire nel database
	 * @return true se la query va a buon fine, false altrimenti
	 */	
	@Override
	public boolean insertLibroCard(LibroCard lc) {
		final String query = "INSERT INTO LibroCard VALUES(?,?,?,?)";

		Connection con = DaoManager.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, lc.getIdLibroCard());
			pst.setDate(2, lc.getDataEmissione());
			pst.setInt(3, lc.getSaldoPunti());
			pst.setString(4, lc.getEmail());

			pst.execute();
			con.close();
			
			return true;
		} catch (SQLException ex) {
			System.out.println(ex);
			return false;
		}
	}

	/**
	 * Restituisce i dati relativi ad una LibroCard presente nel database.
	 * 
	 * @param email l'email dell'utente che possiede la LibroCard
	 * @return null se la LibroCard non è presente nel database, un'istanza di LibroCard altrimenti
	 */
	@Override
	public LibroCard getLibroCard(String email) {
		LibroCard lc = null;
		final String query = "SELECT * FROM LibroCard WHERE email=?";

		Connection con = DaoManager.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				lc = mapRowToLibroCard(rs);
			}

			con.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return lc;
	}

	/**
	 * Restituisce i dati relativi a tutte le LibroCard presenti nel database.
	 * 
	 * @return una lista di istanze di LibroCard
	 */
	@Override
	public List<LibroCard> getAllLibroCard() {
		List<LibroCard> result = new ArrayList<>();
		final String query = "SELECT * FROM LibroCard";

		Connection con = DaoManager.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				result.add(mapRowToLibroCard(rs));
			}

			con.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return result;
	}

	/**
	 * Mappa il risultato di una query in un oggetto di tipo LibroCard.
	 * 
	 * @param rs il ResultSet che rappresenta una riga della query
	 * @return un'istanza di LibroCard
	 */
	private LibroCard mapRowToLibroCard(ResultSet rs) throws SQLException {
		return new LibroCard(rs.getDate("dataEmissione"), rs.getInt("saldoPunti"), rs.getString("email"));
	}

}
