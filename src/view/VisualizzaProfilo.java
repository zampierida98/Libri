package view;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Utente;

public class VisualizzaProfilo extends JPanel {
	
	
	/*private JLabel nome = new JLabel("Nome: ");
	private JLabel cognome = new JLabel("Cognome: ");
	private JLabel indirizzo = new JLabel("Indirizzo: ");
	private JLabel telefono = new JLabel("Telefono: ");
	private JLabel email = new JLabel("Email: ");*/
	
	private static String email;
	
	private String[] campiProfilo = {"Nome: ", "Cognome: ", "Indirizzo: ", "Telefono: "};
	
	public static String getEmail() {
		return email;
	}
	
	
	public VisualizzaProfilo(Utente utente) {
		this.email = utente.getEmail();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		String[] campiUtente = {utente.getNome(), utente.getCognome(), utente.getIndirizzo(), utente.getTelefono(), utente.getEmail()};
		for(int i = 0; i < campiProfilo.length; i++) {
			JLabel visualizzazioneProfilo = new JLabel(campiProfilo[i] + campiUtente[i]);
			this.add(visualizzazioneProfilo);
		}
	}
	
	
	
	
}
