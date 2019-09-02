package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

import model.Utente;
import model.UtenteDao;
import model.UtenteDaoImpl;
import view.ModificaProfilo;

public class ModificaProfiloListener implements ActionListener{
	private UtenteDao utenteDao = new UtenteDaoImpl();
	private ModificaProfilo modificaProfilo;
	
	public ModificaProfiloListener(ModificaProfilo modificaProfilo) {
		this.modificaProfilo = modificaProfilo;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton modifica = (JButton)e.getSource();
		if(modifica.getText().equals("Modifica Dati")) {
			String nome = modificaProfilo.getNome().getText();
			String cognome = modificaProfilo.getCognome().getText();
			String indirizzo = modificaProfilo.getIndirizzo().getText();
			String telefono = modificaProfilo.getTelefono().getText();
			String password = modificaProfilo.getPassword().getText();
			Utente modificaUtente = new Utente (
					nome,
					cognome,
					indirizzo,
					telefono,
					modificaProfilo.getEmail(),
					password
			);
			utenteDao.updateUser(modificaProfilo.getEmail(), modificaUtente);
			modificaProfilo.getResponse().setText("Modifica Effettuata");
		}
	}
}
