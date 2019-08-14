package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import model.UtenteDao;
import model.UtenteDaoImpl;
import view.View;

public class AccediListener implements ActionListener{
	private View frame;
	
	public AccediListener(View frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField[] tfArray = frame.getTfArrayA();
		String email = tfArray[0].getText();
		String password = tfArray[1].getText();
		UtenteDao utenteDao = new UtenteDaoImpl();
		boolean login = utenteDao.login(email, password);
		if (login) {
			System.out.println("Acceso perfetto, bravo Raffa");
		}
		else {
			tfArray[1].setText(null);
		}
		
	}
}
