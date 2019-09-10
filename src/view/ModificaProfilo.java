package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ModificaProfiloListener;
import model.Utente;

public class ModificaProfilo extends JPanel {
	
	private String email;
	private JTextField nome = new JTextField();
	private JTextField cognome = new JTextField();
	private JTextField indirizzo = new JTextField();
	private JTextField telefono = new JTextField();
	private JTextField password = new JTextField();
	private JTextField[] tfArray = {nome, cognome, indirizzo, telefono, password};
	private JLabel response = new JLabel("", SwingConstants.CENTER);
	private JButton modificaProfiloButton = new JButton("Modifica Dati");
	
	public String getEmail() {
		return email;
	}

	public JTextField[] getTfArray() {
		return tfArray;
	}

	public JLabel getResponse() {
		return response;
	}
	
	public ModificaProfilo(Utente utente) {
		this.email = utente.getEmail();
		this.setLayout(new GridLayout(6,2));

		this.add(new JLabel("Nome:"));
		nome.setText(utente.getNome());
		this.add(nome);
		
		this.add(new JLabel("Cognome:"));
		cognome.setText(utente.getCognome());
		this.add(cognome);
		
		this.add(new JLabel("Indirizzo:"));
		indirizzo.setText(utente.getIndirizzo());
		this.add(indirizzo);
		
		this.add(new JLabel("Telefono:"));
		telefono.setText(utente.getTelefono());
		this.add(telefono);
		
		this.add(new JLabel("Password:"));
		password.setText(utente.getPassword());
		this.add(password);
		
		this.add(modificaProfiloButton);
		this.add(response);
		modificaProfiloButton.addActionListener(new ModificaProfiloListener(this));
	}
	
}
