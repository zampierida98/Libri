package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controller.DaoManager;

public class ClassificaDaoImpl implements ClassificaDao {

	/**
	 * Inserisce nel database la posizione nella classifica di un libro.
	 * 
	 * @param c la posizione nella classifica da inserire nel database
	 * @return true se la query va a buon fine, false altrimenti
	 */
	@Override
	public boolean insertClassifica(Classifica c) {
		final String query = "INSERT INTO Classifica VALUES(?,?,?)";

		Connection con = DaoManager.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, c.getISBN());
			pst.setInt(2, c.getPosizione());
			pst.setInt(3, c.getSettimane());

			pst.execute();
			con.close();
			
			return true;
		} catch (SQLException ex) {
			System.out.println(ex);
			return false;
		}
	}

	/**
	 * Aggiorna i dati relativi alla posizione nella classifica di un libro presente nel database.
	 * 
	 * @param c la posizione nella classifica da aggiornare
	 * @return true se la query va a buon fine, false altrimenti
	 */
	@Override
	public boolean updateClassifica(String ISBN, Classifica c) {
		final String query = "UPDATE Classifica SET posizione=?, settimane=? WHERE ISBN=?";

		Connection con = DaoManager.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, c.getPosizione());
			pst.setInt(2, c.getSettimane());
			pst.setString(3, ISBN);

			pst.execute();
			con.close();
			
			return true;
		} catch (SQLException ex) {
			System.out.println(ex);
			return false;
		}
	}

	/**
	 * Restituisce i dati relativi a tutte le posizioni nelle classifiche (organizzate per genere) presenti nel database.
	 * 
	 * @return una lista di istanze di Classifica
	 */
	@Override
	public HashMap<String, List<Classifica>> getClassifiche() {
		HashMap<String, List<Classifica>> result = new HashMap<>();
		final String query = "SELECT * FROM Classifica C INNER JOIN Libro L ON(C.ISBN=L.ISBN) ORDER BY genere";

		Connection con = DaoManager.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				String key = rs.getString("genere");
				if(result.containsKey(key)) {
					result.get(key).add(mapRowToClassifica(rs));
				} else {
					List<Classifica> value = new ArrayList<>();
					value.add(mapRowToClassifica(rs));
					result.put(key, value);
				}
			}

			con.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return result;
	}
	
	/**
	 * Mappa il risultato di una query in un oggetto di tipo Classifica.
	 * 
	 * @param rs il ResultSet che rappresenta una riga della query
	 * @return un'istanza di Classifica
	 */
	private Classifica mapRowToClassifica(ResultSet rs) throws SQLException {
		return new Classifica(rs.getString("ISBN"), rs.getInt("posizione"), rs.getInt("settimane"));
	}

}
