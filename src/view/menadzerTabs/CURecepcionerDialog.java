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

import entity.Recepcioner;
import manage.Controler;
import net.miginfocom.swing.MigLayout;

public class CURecepcionerDialog extends JDialog{	
	private static final long serialVersionUID = 2319534088594994210L;
	private Controler controler;
	
	
	public CURecepcionerDialog(Controler controler, JFrame frame, int idRecepcionera) {
		super(frame);

		this.controler = controler;
		if (idRecepcionera == -1) {
			setTitle("Kreiranje recepcionera");			
		} else {
			setTitle("Izmena recepcionera");			
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/icon.png").getImage());
		setResizable(false);
		setModal(true);
		cuGui(frame, idRecepcionera);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void cuGui(JFrame frame, int idRecepcionera) {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][][][][][][][][][][]20[]");
		setLayout(layout);

		JTextField tfKorisnickoIme = new JTextField(20);
		JPasswordField pfLozinka = new JPasswordField(20);

		JTextField tfIme = new JTextField(20);
		JTextField tfPrezime = new JTextField(20);
		JTextField tfTelefon = new JTextField(20);
		JTextField tfAdresa = new JTextField(20);

		String[] choices = {null, "M", "F"};
		JComboBox<String> cbPol = new JComboBox<String>(choices);
		
		JSpinner spnNivoStrucneSpreme = new JSpinner(new SpinnerNumberModel(1, 1, 4, 1));
		JSpinner spnStaz = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
		JCheckBox chbBonus = new JCheckBox("Bonus");
		JSpinner spnPlata = new JSpinner(new SpinnerNumberModel(0.0, 0.00, 99999.00, 1.00));


		String okTekst;
		
		Recepcioner r = controler.pronadjiRecepcionera(idRecepcionera);
		
		if (idRecepcionera == -1) {
			okTekst = "Kreiraj";
		} else {
			okTekst = "Izmeni";
			tfKorisnickoIme.setText(r.getKorisnickoIme());
			pfLozinka.setText(r.getLozinka());
			tfIme.setText(r.getIme());
			tfPrezime.setText(r.getPrezime());
			tfTelefon.setText(r.getTelefon());
			tfAdresa.setText(r.getAdresa());
			cbPol.setSelectedItem(r.getPol());
			spnNivoStrucneSpreme.setValue(r.getNivoStrucneSpreme());
			spnStaz.setValue(r.getGodineStaza());
			chbBonus.setSelected(r.getBonus());
			spnPlata.setValue(r.getPlata());
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
		
		add(new JLabel("Nivo Stručne spreme: "), "al right");
		add(spnNivoStrucneSpreme);	
		
		add(new JLabel("Godine staža: "), "al right");
		add(spnStaz);	
		
		if (idRecepcionera != -1) {
			add(chbBonus, "span 2, al center");
			
			add(new JLabel("Plata: "), "al right");
			add(spnPlata);			
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
				Integer nivoStrucneSpreme = (int) spnNivoStrucneSpreme.getValue();
				Integer staz = (int) spnStaz.getValue();
				Boolean bonus = chbBonus.isSelected();
				Double plata = (double) spnPlata.getValue();
				
				
				if (korisnickoIme == null || lozinka == null || ime == null || prezime == null || telefon == null || adresa == null || pol == null || nivoStrucneSpreme == null || staz == null ||
						(idRecepcionera != -1 && (bonus == null || plata == null))) {
					JOptionPane.showMessageDialog(CURecepcionerDialog.this, "Niste uneli sve podatke.", "Greška", JOptionPane.ERROR_MESSAGE);				
				} else {
					if (idRecepcionera == -1) {
						controler.registrujRecepcionera(korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, staz);						
					} else {
						controler.izmeniRecepcionera(idRecepcionera, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, staz, bonus, plata);
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
