package view.menadzerTabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import entity.Klijent;
import manage.Controler;
import net.miginfocom.swing.MigLayout;

public class CUKlijentiDialog extends JDialog{
	private static final long serialVersionUID = 4322868593447156250L;
	private Controler controler;
	
	public CUKlijentiDialog(Controler controler, JFrame frame, int idKlijenta) {
		super(frame);

		this.controler = controler;
		if (idKlijenta == -1) {
			setTitle("Kreiranje klijenta");			
		} else {
			setTitle("Izmena klijenta");			
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/icon.png").getImage());
		setResizable(false);
		setModal(true);
		cuGui(frame, idKlijenta);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void cuGui(JFrame frame, int idKlijenta) {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][][][][][][][][]20[]");
		setLayout(layout);

		JTextField tfKorisnickoIme = new JTextField(20);
		JPasswordField pfLozinka = new JPasswordField(20);

		JTextField tfIme = new JTextField(20);
		JTextField tfPrezime = new JTextField(20);
		JTextField tfTelefon = new JTextField(20);
		JTextField tfAdresa = new JTextField(20);

		String[] choices = {null, "M", "F"};
		JComboBox<String> cbPol = new JComboBox<String>(choices);
		
		JCheckBox chbKarticaLojalnosti = new JCheckBox("Kartica lojalnosti");
		JSpinner spnPotroseno = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 999999.00, 1.00));

		String okTekst;
		
		Klijent k = controler.pronadjiKlijenta(idKlijenta);
		
		if (idKlijenta == -1) {
			okTekst = "Kreiraj";
		} else {
			okTekst = "Izmeni";
			tfKorisnickoIme.setText(k.getKorisnickoIme());
			pfLozinka.setText(k.getLozinka());
			tfIme.setText(k.getIme());
			tfPrezime.setText(k.getPrezime());
			tfTelefon.setText(k.getTelefon());
			tfAdresa.setText(k.getAdresa());
			cbPol.setSelectedItem(k.getPol());
			chbKarticaLojalnosti.setSelected(k.isKarticaLojalnosti());
			spnPotroseno.setValue(k.getPotroseno());
		}
		
		JButton btnOk = new JButton(okTekst);
		JButton btnCancel = new JButton("Odustani");			


		getRootPane().setDefaultButton(btnOk);

		add(new JLabel("Molimo da popunite formu."), "span 2");

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
		
		if (idKlijenta != -1) {
			add(chbKarticaLojalnosti, "span 2, al center");
			
			add(new JLabel("Potrošeno: "), "al right");
			add(spnPotroseno);			
		}

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
				Boolean karticaLojalnosti = chbKarticaLojalnosti.isSelected();
				Double potroseno = (double) spnPotroseno.getValue();
				
				
				if (korisnickoIme == null || lozinka == null || ime == null || prezime == null || telefon == null || adresa == null || pol == null || (idKlijenta != -1 && (karticaLojalnosti == null || potroseno == null))) {
					JOptionPane.showMessageDialog(CUKlijentiDialog.this, "Niste uneli sve podatke.", "Greška", JOptionPane.ERROR_MESSAGE);				
				} else {
					if (idKlijenta == -1) {
						controler.registrujKlijenta(korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka);						
					} else {
						controler.izmeniKlijenta(idKlijenta, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, karticaLojalnosti, potroseno);
					}
					
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
