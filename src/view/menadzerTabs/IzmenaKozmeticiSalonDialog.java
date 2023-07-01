package view.menadzerTabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import entity.KozmetickiSalon;
import manage.Controler;
import net.miginfocom.swing.MigLayout;

public class IzmenaKozmeticiSalonDialog extends JDialog{
	private static final long serialVersionUID = 572600692990057762L;
	
	public IzmenaKozmeticiSalonDialog(Controler controler, JFrame frame, KozmetickiSalon kozmetickiSalon) {
		super(frame);
		
		setTitle("Izmena Kozmetičkog salona");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModal(true);
		izmenaGUI(controler, kozmetickiSalon);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void izmenaGUI(Controler controler, KozmetickiSalon ks) {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][][][][][][]20[]");
		setLayout(layout);

		JTextField tfNaziv= new JTextField(20);
		tfNaziv.setText(ks.getNaziv());
		
		LocalTime[] choVreme = new LocalTime[24];	
		for (int i = 0; i < 24; i++) {
			choVreme[i] = LocalTime.MIDNIGHT.plusHours(i);
		}

		JComboBox<LocalTime> cbVremeOtvaranja = new JComboBox<>(choVreme);
		cbVremeOtvaranja.setSelectedItem(controler.pronadjiKozmetickiSalon().getVremeOtvaranja());
		JComboBox<LocalTime> cbVremeZatvaranja = new JComboBox<>(choVreme);
		cbVremeZatvaranja.setSelectedItem(controler.pronadjiKozmetickiSalon().getVremeZatvaranja());
		JSpinner spnPragBonus = new JSpinner(new SpinnerNumberModel(ks.getPragBonus(), 0.0, 999999.00, 1.00));
		JSpinner spnIznosBonus = new JSpinner(new SpinnerNumberModel(ks.getBonusIznos(), 0.0, 999999.00, 1.00));
		JCheckBox chbIzmenaLojalnosti = new JCheckBox("Refaktoriši kartice lojalnosti");
		JSpinner spnPragKarticaLojalnosti = new JSpinner(new SpinnerNumberModel(0.0, 0.0, 999999.00, 1.00));
		

		
		JButton btnOk = new JButton("Sačuvaj izmene");
		JButton btnCancel = new JButton("Odustani");

		getRootPane().setDefaultButton(btnOk);

		add(new JLabel("Molimo da unesete izmene."), "span 2");

		add(new JLabel("Naziv: "), "al right");
		add(tfNaziv);

		add(new JLabel("Vreme otvaranja: "), "al right");
		add(cbVremeOtvaranja);

		add(new JLabel("Vreme zatvaranja: "), "al right");
		add(cbVremeZatvaranja);

		add(new JLabel("Prag bonusa: "), "al right");
		add(spnPragBonus);

		add(new JLabel("Iznos bonusa: "), "al right");
		add(spnIznosBonus);

		add(chbIzmenaLojalnosti, "al left, span 2");
		
		add(new JLabel("Prag za karticu lojalnosti: "), "al right");
		add(spnPragKarticaLojalnosti);
		spnPragKarticaLojalnosti.setEnabled(false);

		add(btnOk);
		add(btnCancel);
		
		chbIzmenaLojalnosti.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!chbIzmenaLojalnosti.isSelected()) {
					spnPragKarticaLojalnosti.setEnabled(false);
				} else {
					spnPragKarticaLojalnosti.setEnabled(true);
				}
			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String naziv = tfNaziv.getText().trim();
				LocalTime vremeOtvaranja = (LocalTime) cbVremeOtvaranja.getSelectedItem();
				LocalTime vremeZatvranja = (LocalTime) cbVremeZatvaranja.getSelectedItem();
				Double pragBonus = (Double) (spnPragBonus.getValue());
				Double iznosBonus = (Double) (spnIznosBonus.getValue());
				Double pragKarticaLojalnosti = (Double) (spnPragKarticaLojalnosti.getValue());
				Boolean refaktorisiKarticeLojalnosti = chbIzmenaLojalnosti.isSelected();
				
				if (naziv == null || vremeOtvaranja == null || vremeZatvranja == null || pragBonus == null || iznosBonus == null || pragKarticaLojalnosti == null) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke.");				
				} else if (vremeOtvaranja.equals(vremeZatvranja)){
					JOptionPane.showMessageDialog(IzmenaKozmeticiSalonDialog.this, "Vreme otvaranja i zatvaranja ne mogu biti isti.", "Greška", JOptionPane.ERROR_MESSAGE);		
				} else {
					controler.izmeniNazivKozmetickogSalona(naziv);
					controler.izmeniVremeOtvaranjaSalona(vremeOtvaranja);
					controler.izmeniVremeZatvaranjaSalona(vremeZatvranja);
					controler.izmeniPragBonusa(pragBonus, iznosBonus);
					
					if (refaktorisiKarticeLojalnosti == true) {
						controler.postaviPragZaKarticuLojalnosti(pragKarticaLojalnosti);						
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
