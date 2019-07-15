package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.DaoManager;

public class LibroDaoImpl implements LibroDao {

	/**
	 * Inserisce nel database i dati relativi ad un libro.
	 * 
	 * @param l il libro da inserire nel database
	 * @return true se la query va a buon fine, false altrimenti
	 */
	@Override
	public boolean insertBook(Libro l) {
		final String query = "INSERT INTO Libro VALUES(?,?,?,?,?,?,?,?,?)";
		
        Connection con = DaoManager.getConnection();
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, l.getISBN());
            pst.setString(2, l.getTitolo());
            pst.setString(3, l.getAutori());
            pst.setString(4, l.getCasaEditrice());
            pst.setInt(5, l.getAnnoPubblicazione());
            pst.setString(6, l.getGenere());
            pst.setDouble(7, l.getPrezzo());
            pst.setString(8, l.getDescrizione());
            pst.setInt(9, l.getPunti());
            
            pst.execute();
            con.close();
            
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
	}

	/**
	 * Restituisce i dati relativi a tutti i Libri presenti nel database.
	 * 
	 * @return una lista di istanze di Libro
	 */
	@Override
	public List<Libro> getAllBooks() {
		List<Libro> result = new ArrayList<>();
		final String query = "SELECT * FROM Libro";

		Connection con = DaoManager.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				result.add(mapRowToLibro(rs));
			}

			con.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return result;
	}
	
	/**
	 * Mappa il risultato di una query in un oggetto di tipo Libro.
	 * 
	 * @param rs il ResultSet che rappresenta una riga della query
	 * @return un'istanza di Libro
	 */
	private Libro mapRowToLibro(ResultSet rs) throws SQLException {
		return new Libro(rs.getString("ISBN"), rs.getString("titolo"), rs.getString("autori"), rs.getString("casaEditrice"), rs.getInt("annoPubblicazione"), rs.getString("genere"), rs.getDouble("prezzo"), rs.getString("descrizione"), rs.getInt("punti"));
	}

}
