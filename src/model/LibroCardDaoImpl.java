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

		return null;
	}

	/**
	 * Mappa il risultato di una query in un oggetto di tipo LibroCard.
	 * 
	 * @param rs il ResultSet che rappresenta una riga della query
	 * @return un'istanza di LibroCard
	 */
	private LibroCard mapRowToLibroCard(ResultSet rs) throws SQLException {
		return new LibroCard(rs.getInt("idLibroCard"), rs.getInt("dataEmissione"), rs.getInt("saldoPunti"), rs.getString("email"));
	}

}
