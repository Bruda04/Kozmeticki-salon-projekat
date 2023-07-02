package view.menadzerTabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import entity.TipTretmana;
import entity.Usluga;
import manage.Controler;
import net.miginfocom.swing.MigLayout;

public class CUUslugaDialog extends JDialog{
	private static final long serialVersionUID = 4044951033799305090L;

	private Controler controler;

	public CUUslugaDialog(Controler controler, JFrame frame, int idUsluge) {
		super(frame);

		this.controler = controler;
		if (idUsluge == -1) {
			setTitle("Kreiranje tipa tretmana");			
		} else {
			setTitle("Izmena tipa tretmana");			
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/icon.png").getImage());
		setResizable(false);
		setModal(true);
		cuGui(frame, idUsluge);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void cuGui(JFrame frame, int idUsluge) {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][][][][][]20[]");
		setLayout(layout);

		JTextField tfNaziv = new JTextField(20);
		JSpinner spnTrajanje = new JSpinner(new SpinnerNumberModel(1, 1, 1440, 1));
		
		ArrayList<String> choices = new ArrayList<>();
		choices.add(null);
		for (TipTretmana tt : controler.sviTipoviTretmana().values()) {
			choices.add(tt.getNaziv());
		}
		String[] choicesArray = choices.toArray(new String[choices.size()]); 
		JComboBox<String> cbTipTretmana = new JComboBox<String>(choicesArray);

		JSpinner spnCena = new JSpinner(new SpinnerNumberModel(0.00, 0.00, 99999.00, 1.00));
		
		String okTekst;

		Usluga u = controler.pronadjiUslugu(idUsluge);

		if (idUsluge == -1) {
			okTekst = "Kreiraj";
		} else {
			okTekst = "Izmeni";
			tfNaziv.setText(u.getNazivUsluge());
			spnTrajanje.setValue(u.getTrajanjeUsluge());
			cbTipTretmana.setSelectedItem(controler.nadjiTipTretmanaUsluge(idUsluge).getNaziv());
			spnCena.setValue(controler.nadjiCenuUsluge(idUsluge));
		}

		JButton btnOk = new JButton(okTekst);
		JButton btnCancel = new JButton("Odustani");			


		getRootPane().setDefaultButton(btnOk);

		add(new JLabel("Molimo da popunite formu."), "span 2");

		add(new JLabel("Naziv: "), "al right");
		add(tfNaziv);
		
		add(new JLabel("Trajanje usluge(minuta): "), "al right");
		add(spnTrajanje);
		
		add(new JLabel("Tip tretmana: "), "al right");
		add(cbTipTretmana);
		
		add(new JLabel("Cena: "), "al right");
		add(spnCena);

		add(btnOk);
		add(btnCancel);

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String naziv = tfNaziv.getText().trim();
				Integer trajanje = (Integer) spnTrajanje.getValue();
				Double cena = (Double) spnCena.getValue();
				String tipTretmana = (String) cbTipTretmana.getSelectedItem();
				
				if (naziv == null || trajanje == null || tipTretmana == null || cena == null ) {
					JOptionPane.showMessageDialog(CUUslugaDialog.this, "Niste uneli sve podatke.", "Greška", JOptionPane.ERROR_MESSAGE);				
				} else {
					try {
						Integer idTipaTretmana = controler.pronadjiTipTretmana(tipTretmana).getId();
						if (idUsluge == -1) {
							controler.dodajUslugu(naziv, trajanje, cena, idTipaTretmana);						
						} else {
							controler.izmeniUslugu(idUsluge, naziv, trajanje, cena);
							controler.premestiUslugu(idUsluge, controler.nadjiTipTretmanaUsluge(idUsluge).getId(), idTipaTretmana);
						}
						
						setVisible(false);
						dispose();						
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(CUUslugaDialog.this, "Usluga sa tim nazivom već postoji!", "Greška", JOptionPane.ERROR_MESSAGE);
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
	}


}
