package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import controller.DaoManager;

public class UtenteDaoImpl implements UtenteDao {
	
	@Override
	public boolean insertUtente(Utente u) {
		boolean result = false;
		final String query = "INSERT INTO Utente VALUES(?,?,?,NULL,?,?,NULL,default)";
		
        Connection con = DaoManager.getConnection();
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(query);
            pst.setString(1, u.getNome());
            pst.setString(2, u.getCognome());
            pst.setString(3, u.getIndirizzo());
            pst.setString(4, u.getTelefono());
            pst.setString(5, u.getEmail());
            
            result = pst.execute();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return result;
	}

	@Override
	public Utente getUser(int idUtente) {
		Utente u = null;
		final String query = "SELECT * FROM Utente WHERE idUtente=?";
		
        Connection con = DaoManager.getConnection();
        PreparedStatement pst;
        try {
            pst = con.prepareStatement(query);
            pst.setInt(1, idUtente);
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
	public boolean updateUser(Utente u) {
		boolean result = false;
		final String query = "UPDATE Utente SET nome=?, cognome=?, indirizzo=?, telefono=?, email=? WHERE idUtente=?";
		
        Connection con = DaoManager.getConnection();
        PreparedStatement pst;
        try {
        	pst = con.prepareStatement(query);
            pst.setString(1, u.getNome());
            pst.setString(2, u.getCognome());
            pst.setString(3, u.getIndirizzo());
            pst.setString(4, u.getTelefono());
            pst.setString(5, u.getEmail());
            pst.setInt(6, u.getIdUtente());
            
            pst.execute();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return result;
	}

	private Utente mapRowToUser(ResultSet rs) throws SQLException {
		return new Utente(Integer.valueOf(rs.getString("idUtente")), rs.getString("nome"), rs.getString("cognome"), rs.getString("indirizzo"), rs.getString("telefono"), rs.getString("email"));
	}

}
