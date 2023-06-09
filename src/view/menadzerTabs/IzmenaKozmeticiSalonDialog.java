package view.menadzerTabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
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
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModal(true);
		izmenaGUI(controler, kozmetickiSalon);

		pack();
		setVisible(true);
	}

	private void izmenaGUI(Controler controler, KozmetickiSalon ks) {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][][][][][][]20[]");
		setLayout(layout);

		JTextField tfNaziv= new JTextField(20);
		tfNaziv.setText(ks.getNaziv());
		JTextField tfVremeOtvaranja = new JTextField(20);
		tfVremeOtvaranja.setText(ks.getVremeOtvaranjaFormatStr());
		JTextField tfVremeZatvaranja = new JTextField(20);
		tfVremeZatvaranja.setText(ks.getVremeZatvaranjaFormatStr());
		JSpinner spnPragBonus = new JSpinner(new SpinnerNumberModel(ks.getPragBonus(), 0.0, 999999.00, 1.00));
		JSpinner spnIznosBonus = new JSpinner(new SpinnerNumberModel(ks.getBonusIznos(), 0.0, 999999.00, 1.00));
		
		JButton btnOk = new JButton("Sačuvaj izmene");
		JButton btnCancel = new JButton("Odustani");

		getRootPane().setDefaultButton(btnOk);

		add(new JLabel("Molimo da unesete izmene."), "span 2");

		add(new JLabel("Naziv: "), "al right");
		add(tfNaziv);

		add(new JLabel("Vreme otvaranja: "), "al right");
		add(tfVremeOtvaranja);

		add(new JLabel("Vreme zatvaranja: "), "al right");
		add(tfVremeZatvaranja);

		add(new JLabel("Prag bonusa: "), "al right");
		add(spnPragBonus);

		add(new JLabel("Iznos bonusa: "), "al right");
		add(spnIznosBonus);


		add(btnOk);
		add(btnCancel);
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String naziv = tfNaziv.getText().trim();
				String vremeOtvaranja = tfVremeOtvaranja.getText().trim();
				String vremeZatvranja = tfVremeZatvaranja.getText().trim();
				Double pragBonus = (Double) (spnPragBonus.getValue());
				Double iznosBonus = (Double) (spnIznosBonus.getValue());
				
				
				if (naziv == null || vremeOtvaranja == null || vremeZatvranja == null || pragBonus == null || iznosBonus == null) {
					JOptionPane.showMessageDialog(null, "Niste uneli sve podatke.");				
				} else {
					controler.izmeniNazivKozmetickogSalona(naziv);
					controler.izmeniVremeOtvaranjaSalona(LocalTime.parse(vremeOtvaranja, DateTimeFormatter.ofPattern("HH:mm")));
					controler.izmeniVremeZatvaranjaSalona(LocalTime.parse(vremeZatvranja, DateTimeFormatter.ofPattern("HH:mm")));
					controler.izmeniPragBonusa(pragBonus, iznosBonus);
					
					
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
