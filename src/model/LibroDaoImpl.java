package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import controller.DaoManager;

public class LibroDaoImpl implements LibroDao {

	@Override
	public boolean insertBook(Libro l) {
		boolean result = false;
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
            
            result = pst.execute();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return result;
	}

}
