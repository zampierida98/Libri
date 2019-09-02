package view;

import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ModificaProfiloListener;
import model.Utente;

public class ModificaProfilo extends JPanel {
	
	private String[] arrayLabel = {"Nome: ", "Cognome: ", "Indirizzo: ", "Telefono: ", "Password: "};
	private String email;
	private JTextField nome = new JTextField();
	private JTextField cognome = new JTextField();
	private JTextField indirizzo = new JTextField();
	private JTextField telefono = new JTextField();
	private JTextField password = new JTextField();
	private JLabel response = new JLabel("", SwingConstants.CENTER);
	private JButton modificaProfiloButton = new JButton("Modifica Dati");
	
	public String getEmail() {
		return email;
	}
	
	public JTextField getNome() {
		return nome;
	}

	public JTextField getCognome() {
		return cognome;
	}

	public JTextField getIndirizzo() {
		return indirizzo;
	}

	public JTextField getTelefono() {
		return telefono;
	}

	public JTextField getPassword() {
		return password;
	}
	
	public JButton getModificaProfiloButton() {
		return modificaProfiloButton;
	}

	public JLabel getResponse() {
		return response;
	}
	
	public ModificaProfilo(Utente u) {
		this.setLayout(new GridLayout(7, 2));
		this.email = u.getEmail();
		String[] utente = {u.getNome(), u.getCognome(), u.getIndirizzo(), u.getTelefono(),  u.getPassword() };
		JTextField[] modificaCampi = {nome, cognome, indirizzo, telefono, password};
		for(int i = 0; i < arrayLabel.length; i++) {
			JLabel campo = new JLabel(arrayLabel[i]);
			this.add(campo);
			modificaCampi[i].setText(utente[i]);
			this.add(modificaCampi[i]);
		}
		this.add(modificaProfiloButton);
		this.add(response);
		modificaProfiloButton.addActionListener(new ModificaProfiloListener(this));
	}
}
