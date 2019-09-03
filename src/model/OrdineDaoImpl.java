package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import controller.DaoManager;

public class OrdineDaoImpl implements OrdineDao {

	/**
	 * Inserisce nel database i dati relativi ad un ordine.
	 * 
	 * @param o l'ordine da inserire nel database
	 * @return true se la query va a buon fine, false altrimenti
	 */
	@Override
	public boolean insertOrder(Ordine o) {
		Connection con = DaoManager.getConnection();
		PreparedStatement pst;

		// inserimento dati generali
		final String queryDati = "INSERT INTO Ordine VALUES(?,?,?,?,?,?,?)";
		try {
			pst = con.prepareStatement(queryDati);
			pst.setInt(1, o.getIdOrdine());
			pst.setDate(2, o.getData());
			pst.setDouble(3, o.getCostoTotale());
			pst.setInt(4, o.getPagamento().ordinal());
			pst.setString(5, o.getEmail());
			pst.setString(6, o.getSpedizione());
			pst.setInt(7, o.getPuntiAccumulati());
			pst.execute();
		} catch (SQLException ex) {
			System.out.println(ex);
			return false;
		}

		// inserimento libri
		final String queryLibri = "INSERT INTO ListaLibri VALUES(?,?,?)";
		for(Libro l : o.getListaLibri().keySet()) {
			try {
				pst = con.prepareStatement(queryLibri);
				pst.setInt(1, o.getIdOrdine());
				pst.setString(2, l.getISBN());
				pst.setInt(3, o.getListaLibri().get(l));
				pst.execute();
			} catch (SQLException ex) {
				System.out.println(ex);
				return false;
			}
		}

		// aggiornamento punti
		final String queryPunti = "UPDATE LibroCard SET saldoPunti=? WHERE email=?";
		try {
			pst = con.prepareStatement(queryPunti);
			pst.setInt(1, o.getPuntiAccumulati());
			pst.setString(2, o.getEmail());
			pst.execute();
			con.close();
		} catch (SQLException ex) {
			System.out.println(ex);
			return false;
		}

		return true;
	}

	/**
	 * Restituisce i dati relativi a tutti gli ordini effettuati da un utente.
	 * 
	 * @param email l'email dell'utente di cui si vogliono i dati di tutti gli ordini
	 * @return una lista di istanze di Ordine
	 */
	@Override
	public List<Ordine> getAllOrders(String email) {
		List<Ordine> result = new ArrayList<>();
		final String query = "SELECT * FROM Ordine WHERE email=?";

		Connection con = DaoManager.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(query);
			pst.setString(1, email);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				result.add(mapRowToOrdine(rs));
			}

			con.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return result;
	}

	/**
	 * Restituisce i dati relativi ad un ordine presente nel database.
	 * 
	 * @param idOrdine il codice dell'ordine di cui si vogliono i dati
	 * @return null se l'ordine non è presente nel database, un'istanza di Ordine altrimenti
	 */
	@Override
	public Ordine getOrder(int idOrdine) {
		Ordine o = null;
		final String query = "SELECT * FROM Ordine WHERE idOrdine=?";

		Connection con = DaoManager.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(query);
			pst.setInt(1, idOrdine);
			ResultSet rs = pst.executeQuery();

			while(rs.next()) {
				o = mapRowToOrdine(rs);
			}

			con.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return o;
	}

	/**
	 * Restituisce i dati relativi agli ordini effettuati da tutti gli utenti.
	 * 
	 * @return una lista di istanze di Ordine
	 */
	@Override
	public HashMap<String, List<Ordine>> getOrdersStatus() {
		HashMap<String, List<Ordine>> result = new HashMap<>();
		final String query = "SELECT * FROM Ordine ORDER BY email";

		Connection con = DaoManager.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				String key = rs.getString("email");
				if(result.containsKey(key)) {
					result.get(key).add(mapRowToOrdine(rs));
				} else {
					List<Ordine> value = new ArrayList<>();
					value.add(mapRowToOrdine(rs));
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
	 * Mappa il risultato di una query in un oggetto di tipo Ordine.
	 * 
	 * @param rs il ResultSet che rappresenta una riga della query
	 * @return un'istanza di Ordine
	 */
	private Ordine mapRowToOrdine(ResultSet rs) throws SQLException {
		// seleziono tutti i libri
		HashMap<Libro, Integer> listaLibri = new HashMap<Libro, Integer>();
		final String queryLibri = "SELECT * FROM ListaLibri LL INNER JOIN Libro L ON(LL.ISBN=L.ISBN) WHERE idOrdine=?";
		Connection con = DaoManager.getConnection();
		PreparedStatement pst;
		try {
			pst = con.prepareStatement(queryLibri);
			pst.setInt(1, rs.getInt("idOrdine"));
			ResultSet rsLibri = pst.executeQuery();

			while(rsLibri.next()) {
				listaLibri.put(mapRowToLibro(rsLibri), rsLibri.getInt("copie"));
			}

			con.close();
		} catch (SQLException ex) {
			System.out.println(ex);
		}

		return new Ordine(rs.getInt("idOrdine"), rs.getDate("data"), listaLibri, rs.getDouble("costoTotale"), Pagamento.values()[rs.getInt("pagamento")], rs.getString("email"), rs.getString("spedizione"), rs.getInt("puntiAccumulati"));
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
