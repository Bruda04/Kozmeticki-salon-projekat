package view.recepcionerTabs;

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
import manage.Controler;
import net.miginfocom.swing.MigLayout;

public class CZakazanTretmanDialog extends JDialog{

	private static final long serialVersionUID = 7247432992768080272L;
	private Controler controler;
	private int idUlogovan;

	public CZakazanTretmanDialog(Controler controler, JFrame frame, int idUlogovan) {
		super(frame);

		this.controler = controler;
		this.idUlogovan = idUlogovan;
		setTitle("Zakazivanje Tretmana");			

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/icon.png").getImage());
		setResizable(false);
		setModal(true);
		cuGui(frame);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void cuGui(JFrame frame) {
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
		
		JButton btnOk = new JButton("Zakaži");
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
				Integer idTipaTretmana = controler.pronadjiTipTretmana((String) cbTipTretmana.getSelectedItem()).getId();
				Double cena = (Double) spnCena.getValue();

				if (naziv == null || trajanje == null || idTipaTretmana == null || cena == null ) {
					JOptionPane.showMessageDialog(CZakazanTretmanDialog.this, "Niste uneli sve podatke.", "Greška", JOptionPane.ERROR_MESSAGE);				
				} else {
					controler.zakaziTretman(null, null, idUlogovan, idUlogovan, idUlogovan, idUlogovan);
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
