package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DaoManager;
import model.Utente;
import model.UtenteDao;
import model.UtenteDaoImpl;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomeFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeFrame frame = new WelcomeFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WelcomeFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblWelcome = new JLabel("Welcome");	//poi andranno private static final
		contentPane.add(lblWelcome, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		JLabel lblNewLabel2 = new JLabel("");
		panel.add(lblNewLabel2);
		
		JButton btnProva = new JButton("Prova");
		//NON CONSIGLIATO
		btnProva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DaoManager dao = new DaoManager();
		        UtenteDao utenteDao = new UtenteDaoImpl();
		        Utente u1 = utenteDao.getUser(1001);
		        lblNewLabel.setText(u1.getEmail());
		        
		        u1.setEmail("nuova_mail");
		        utenteDao.updateUser(u1);
		        lblNewLabel2.setText(u1.getEmail());
			}
		});
		panel.add(btnProva);
	}

}
