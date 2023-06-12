package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import entity.Klijent;
import entity.Korisnik;
import entity.Kozmeticar;
import entity.Menadzer;
import entity.Recepcioner;
import manage.Controler;
import net.miginfocom.swing.MigLayout;

public class LoginFrameView extends JFrame{
	private static final long serialVersionUID = -2079771946898365427L;
	
	public LoginFrameView(Controler controler) {		
		setTitle("Prijava");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/icon.png").getImage());
		setResizable(false);
		setupGUI(controler);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}

	private void setupGUI(Controler controler) {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]20[]10[]");
		setLayout(layout);

		JTextField tfKorisnickoIme = new JTextField(20);
		JPasswordField pfLozinka = new JPasswordField(20);
		JButton btnOk = new JButton("Prijavi se");
		JButton btnCancel = new JButton("Izlaz");
		JButton btnRegister = new JButton("Registruj se");

		
		getRootPane().setDefaultButton(btnOk);

		add(new JLabel("Dobrodošli. Molimo da se prijavite."), "span 2");
		add(new JLabel("Korisničko ime: "));
		add(tfKorisnickoIme);
		add(new JLabel("Lozinka: "), "al right");
		add(pfLozinka);
		add(btnOk);
		add(btnCancel);
		add(new JLabel("Nemate nalog?"));
		add(btnRegister);

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = tfKorisnickoIme.getText().trim();
				String lozinka = new String(pfLozinka.getPassword()).trim();
				if (korisnickoIme == null || lozinka == null) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke.");				
				} else {
					Korisnik kredencijali = controler.prijava(korisnickoIme, lozinka);
					if (kredencijali == null) {
						JOptionPane.showMessageDialog(null, "Korisnik sa unetim kredencijalima ne postoji.");				
					} else {
						
						if (kredencijali instanceof Klijent) {
							new KlijentView(controler, kredencijali.getId());
						} else if(kredencijali instanceof Menadzer) {
							new MenadzerView(controler);
						} else if (kredencijali instanceof Recepcioner) {
							new RecepcionerView(controler, kredencijali.getId());
						} else if (kredencijali instanceof Kozmeticar) {
							new KozmeticarView(controler, kredencijali.getId());
						}
						
						setVisible(false);
						dispose();
						
					}			
				}
				
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		btnRegister.addActionListener(actionListener -> {
			new RegisterDialogView(controler ,this);
		});

	}
	
}
