package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JTextField;

import model.LibroCard;
import model.LibroCardDao;
import model.LibroCardDaoImpl;
import model.Utente;
import model.UtenteDao;
import model.UtenteDaoImpl;
import view.View;


public class RegistrazioneListener implements ActionListener	{
	
	private View frame;
	
	public RegistrazioneListener(View frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField[] tfArray = frame.getTfArrayR();
		String nome = tfArray[0].getText();
		String cognome = tfArray[1].getText();
		String indirizzo = String.format("%s %s %s %s", tfArray[2].getText(), tfArray[3].getText(), tfArray[4].getText(), tfArray[5].getText());
		String telefono = tfArray[6].getText();
		String email = tfArray[7].getText();
		String password = tfArray[8].getText();
		UtenteDao utenteDao = new UtenteDaoImpl();
		JButton button = (JButton)e.getSource();
		if(button.getText().equals("Registrami")) {
			Utente utente = new Utente(nome, cognome, indirizzo, telefono, email, password);
			System.out.println(utente);
			utenteDao.insertUtente(utente);
			LibroCard libroCardUtente = new LibroCard(Date.valueOf(LocalDate.now()), 0, email);
			LibroCardDao libroCardDao = new LibroCardDaoImpl();
			libroCardDao.insertLibroCard(libroCardUtente);
		}
		/*else if(button.getText().equals("Ordina")) {
			
		}*/
	}

}
