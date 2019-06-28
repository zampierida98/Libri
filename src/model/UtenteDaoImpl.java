package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import controller.DaoManager;

public class UtenteDaoImpl implements UtenteDao {

	@Override
	public List<Utente> getAllUsers() {
		final String QUERY = "SELECT * FROM Utente";
        Connection con = DaoManager.getConnection();
        ArrayList<Utente> ls = new ArrayList<>();
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(QUERY);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Utente s = mapRowToUtente(rs);
                ls.add(s);
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return ls;
	}

	private Utente mapRowToUtente(ResultSet rs) throws SQLException {
		return new Utente(Integer.valueOf(rs.getString("idUtente")), rs.getString("nome"), rs.getString("cognome"), rs.getString("indirizzo"), rs.getString("telefono"), rs.getString("email"));
	}

	@Override
	public Utente getUser(int idUtente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(Utente user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteUser(Utente user) {
		// TODO Auto-generated method stub

	}

}
