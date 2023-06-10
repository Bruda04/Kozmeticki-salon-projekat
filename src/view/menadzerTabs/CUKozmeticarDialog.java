package view.menadzerTabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

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

import entity.Kozmeticar;
import entity.TipTretmana;
import manage.Controler;
import net.miginfocom.swing.MigLayout;

public class CUKozmeticarDialog extends JDialog{
	private static final long serialVersionUID = -5741331909493399189L;
	
	private Controler controler;
	
	public CUKozmeticarDialog(Controler controler, JFrame frame, int idKozmeticara) {
		super(frame);

		this.controler = controler;
		if (idKozmeticara == -1) {
			setTitle("Kreiranje menadžera");			
		} else {
			setTitle("Izmena menadžera");			
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/icon.png").getImage());
		setResizable(false);
		setModal(true);
		cuGui(frame, idKozmeticara);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void cuGui(JFrame frame, int idKozmeticara) {
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
		
		HashMap<Integer, TipTretmana> sviTipoviTretmana = controler.sviTipoviTretmana();
		HashMap<Integer, TipTretmana> nauceniTretmani = new HashMap<>();


		String okTekst;
		
		Kozmeticar k = controler.pronadjiKozmeticara(idKozmeticara);
		
		if (idKozmeticara == -1) {
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
			spnNivoStrucneSpreme.setValue(k.getNivoStrucneSpreme());
			spnStaz.setValue(k.getGodineStaza());
			chbBonus.setSelected(k.getBonus());
			spnPlata.setValue(k.getPlata());
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
		
		if (idKozmeticara != -1) {
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
						(idKozmeticara != -1 && (bonus == null || plata == null))) {
					JOptionPane.showMessageDialog(CUKozmeticarDialog.this, "Niste uneli sve podatke.", "Greška", JOptionPane.ERROR_MESSAGE);				
				} else {
					if (idKozmeticara == -1) {
						controler.registrujKozmeticara(korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, staz, null);						
					} else {
						controler.izmeniKozmeticara(idKozmeticara, korisnickoIme, ime, prezime, pol, telefon, adresa, lozinka, nivoStrucneSpreme, staz, bonus, plata, null);
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
