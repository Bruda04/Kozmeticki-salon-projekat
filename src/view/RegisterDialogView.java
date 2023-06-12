package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import manage.Controler;
import net.miginfocom.swing.MigLayout;

public class RegisterDialogView extends JDialog{
	private static final long serialVersionUID = -8820690680167901709L;
	private Controler controler;

	public RegisterDialogView(Controler controler,JFrame frame) {
		super(frame);

		this.controler = controler;
		setTitle("Registracija");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/icon.png").getImage());
		setResizable(false);
		setModal(true);
		registerGUI(frame);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void registerGUI(JFrame frame) {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][][][][][][]20[]");
		setLayout(layout);

		JTextField tfKorisnickoIme = new JTextField(20);
		JPasswordField pfLozinka = new JPasswordField(20);

		JTextField tfIme = new JTextField(20);
		JTextField tfPrezime = new JTextField(20);
		JTextField tfTelefon = new JTextField(20);
		JTextField tfAdresa = new JTextField(20);

		String[] choices = {null, "M", "F"};
		JComboBox<String> cbPol = new JComboBox<String>(choices);

		JButton btnOk = new JButton("Registruj se");
		JButton btnCancel = new JButton("Odustani");


		getRootPane().setDefaultButton(btnOk);

		add(new JLabel("Molimo da se registrujete."), "span 2");

		add(new JLabel("Korisničko ime: "), "al right");
		add(tfKorisnickoIme);

		add(new JLabel("Lozinka: "), "al right");
		add(pfLozinka);

		add(new JLabel("Ime: "), "al right");
		add(tfIme);

		add(new JLabel("Prezime: "), "al right");
		add(tfPrezime);

		add(new JLabel("Telefon: "), "al right");
		add(tfTelefon);

		add(new JLabel("Adresa: "), "al right");
		add(tfAdresa);

		add(new JLabel("Pol: "), "al right");
		add(cbPol);

		add(btnOk);
		add(btnCancel);

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnickoIme = tfKorisnickoIme.getText().trim();
				String lozinka = new String(pfLozinka.getPassword()).trim();
				String ime = tfIme.getText().trim();
				String prezime = tfPrezime.getText().trim();
				String telefon = tfTelefon.getText().trim();
				String adresa = tfAdresa.getText().trim();
				String pol = (String) cbPol.getSelectedItem();
				
				
				if (korisnickoIme == null || lozinka == null || ime == null || prezime == null || telefon == null || adresa == null || pol == null) {
					JOptionPane.showMessageDialog(RegisterDialogView.this, "Niste uneli sve podatke.", "Greška", JOptionPane.ERROR_MESSAGE);				
				} else {
					controler.registrujKlijenta(korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka);
					
					new KlijentView(controler, controler.pronadjiKlijenta(korisnickoIme).getId());
					
					frame.setVisible(false);
					frame.dispose();
					
					setVisible(false);
					dispose();
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
	}

}
