package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.DaoManager;

public class LibroCardDaoImpl implements LibroCardDao {

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
	
	private LibroCard mapRowToLibroCard(ResultSet rs) throws SQLException {
		//ATTENZIONE metto tutto, ma poi sarà l'handler a decidere cosa visualizzare
		return new LibroCard(rs.getInt("idLibroCard"), rs.getInt("idUtente"), rs.getInt("dataEmissione"), rs.getInt("saldoPunti"));
	}

}
