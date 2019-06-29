package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import controller.DaoManager;

public class UtenteDaoImpl implements UtenteDao {

	@Override
	public Utente getUser(int idUtente) {
		Utente u = null;
		final String query = String.format("SELECT * FROM Utente WHERE idUtente=%d", idUtente);
		
        Connection con = DaoManager.getConnection();
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                u = mapRowToUser(rs);
            }
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return u;
	}

	@Override
	public void updateUser(Utente u) {
		final String query = String.format("UPDATE Utente"
				+ " SET nome='%s', cognome='%s', indirizzo='%s', telefono='%s', email='%s'"
				+ " WHERE idUtente=%d",
				u.getNome(), u.getCognome(), u.getIndirizzo(), u.getTelefono(), u.getEmail(), u.getIdUtente());
		
        Connection con = DaoManager.getConnection();
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(query);
            pst.execute();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
	}

	private Utente mapRowToUser(ResultSet rs) throws SQLException {
		return new Utente(Integer.valueOf(rs.getString("idUtente")), rs.getString("nome"), rs.getString("cognome"), rs.getString("indirizzo"), rs.getString("telefono"), rs.getString("email"));
	}

}
