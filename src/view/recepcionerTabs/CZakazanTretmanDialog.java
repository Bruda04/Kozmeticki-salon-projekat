package view.recepcionerTabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import entity.Klijent;
import entity.Kozmeticar;
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
		//		pack();
		setSize(400, 400);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private LocalDate dateFormat(Date datum) {
		if (datum != null) {
			return datum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();					
		} else {
			return null;
		}
	}

	private void cuGui(JFrame frame) {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][][][][][][][][][]20[]");
		setLayout(layout);

		ArrayList<String> choKlijenti = new ArrayList<>();
		choKlijenti.add(null);
		for (Klijent k : controler.sviKlijenti().values()) {
			choKlijenti.add(k.getKorisnickoIme());
		}
		String[] choKlijentiArray = choKlijenti.toArray(new String[choKlijenti.size()]); 
		JComboBox<String> cbKlijent = new JComboBox<String>(choKlijentiArray);

		ArrayList<String> choTipTretmana= new ArrayList<>();
		choTipTretmana.add(null);
		for (TipTretmana tt : controler.sviTipoviTretmana().values()) {
			choTipTretmana.add(tt.getNaziv());
		}
		String[] choTipTremtanaArray = choTipTretmana.toArray(new String[choTipTretmana.size()]); 
		JComboBox<String> cbTipTretmana = new JComboBox<String>(choTipTremtanaArray);

		ArrayList<String> choUsluga= new ArrayList<>();
		choUsluga.add(null);

		String[] choUslugaArray = choUsluga.toArray(new String[choUsluga.size()]); 
		JComboBox<String> cbUsluga = new JComboBox<String>(choUslugaArray);

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		JDatePickerImpl dpDatum = new JDatePickerImpl(datePanel, new DateComponentFormatter());

		JComboBox<String> cbVreme = new JComboBox<String>();

		ArrayList<String> choKozmeticari= new ArrayList<>();
		choKozmeticari.add(null);
		String[] choKozmeticariArray = choKozmeticari.toArray(new String[choKozmeticari.size()]); 
		JComboBox<String> cbKozmeticar = new JComboBox<String>(choKozmeticariArray);

		JButton btnOk = new JButton("Zakaži");
		JButton btnCancel = new JButton("Odustani");			


		cbTipTretmana.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel<String> cbUslugeModel = (DefaultComboBoxModel<String>) cbUsluga.getModel();
				cbUslugeModel.removeAllElements();

				cbUslugeModel.addElement(null);
				if (cbTipTretmana.getSelectedItem() != null) {
					for (int idUSluge : controler.pronadjiTipTretmana((String) cbTipTretmana.getSelectedItem()).getSkupTipovaUsluga()) {
						cbUslugeModel.addElement(controler.pronadjiUslugu(idUSluge).getNazivUsluge());
					}				
				}
				cbUsluga.setModel(cbUslugeModel);
			}
		});

		cbUsluga.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel<String> cbKozmeticariModel = (DefaultComboBoxModel<String>) cbKozmeticar.getModel();
				cbKozmeticariModel.removeAllElements();

				if (cbUsluga.getSelectedItem() != null) {
					for (Kozmeticar kz : controler.kozmeticariKojiZnajuUslugu(controler.pronadjiUslugu((String) cbUsluga.getSelectedItem()).getId())) {
						cbKozmeticariModel.addElement(kz.getKorisnickoIme());
					}				
				}
				cbKozmeticar.setModel(cbKozmeticariModel);				
			}
		});

		cbKozmeticar.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel<String> cbVremeModel = (DefaultComboBoxModel<String>) cbVreme.getModel();
				cbVremeModel.removeAllElements();

				if (dpDatum.getJDateInstantPanel().getModel().getValue() != null && cbKozmeticar.getSelectedItem() != null) {
					for (LocalTime v : controler.kozmeticarSlobodan(controler.pronadjiKozmeticara((String) cbKozmeticar.getSelectedItem()).getId(), dateFormat((Date)dpDatum.getModel().getValue()))) {
						cbVremeModel.addElement(v.format(DateTimeFormatter.ofPattern("HH:mm")));
					}				
				}
				cbVreme.setModel(cbVremeModel);				
			}
		});

		dpDatum.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultComboBoxModel<String> cbVremeModel = (DefaultComboBoxModel<String>) cbVreme.getModel();
				cbVremeModel.removeAllElements();

				if (cbKozmeticar.getSelectedItem() != null && cbKozmeticar.getSelectedItem() != null && dpDatum.getModel().getValue() != null) {
					for (LocalTime v : controler.kozmeticarSlobodan(controler.pronadjiKozmeticara((String) cbKozmeticar.getSelectedItem()).getId(), dateFormat((Date)dpDatum.getModel().getValue()))) {
						cbVremeModel.addElement(v.format(DateTimeFormatter.ofPattern("HH:mm")));
					}				
				}
				cbVreme.setModel(cbVremeModel);		
			}
		});

		JLabel lblCena = new JLabel("");
		JLabel lblTrajanje = new JLabel("");

		cbUsluga.addActionListener (new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if (cbUsluga.getSelectedItem() != null) {
					lblCena.setText(String.format("%.2f",controler.nadjiCenuUsluge(controler.pronadjiUslugu((String)cbUsluga.getSelectedItem()).getId())));		
					lblTrajanje.setText(String.format("%d",controler.pronadjiUslugu(controler.pronadjiUslugu((String)cbUsluga.getSelectedItem()).getId()).getTrajanjeUsluge()));					

				} else {
					lblCena.setText("");
					lblTrajanje.setText("");
				}
			}
		});		



		getRootPane().setDefaultButton(btnOk);

		add(new JLabel("Molimo da popunite formu."), "span 2");

		add(new JLabel("Klijent: "), "al right");
		add(cbKlijent);

		add(new JLabel("Tip tretmana: "), "al right");
		add(cbTipTretmana);

		add(new JLabel("Usluga: "), "al right");
		add(cbUsluga);

		add(new JLabel("Kozmetičar: "));
		add(cbKozmeticar);

		add(new JLabel("Datum"));
		add(dpDatum);

		add(new JLabel("Vreme: "), "al right");
		add(cbVreme);

		add(new JLabel("Cena: "));
		add(lblCena);

		add(new JLabel("Trajanje(minuta): "));
		add(lblTrajanje);

		add(btnOk);
		add(btnCancel);

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String klijentKI = (String) cbKlijent.getSelectedItem();
				String usluga = (String) cbUsluga.getSelectedItem();
				String kozmeticar = (String) cbKozmeticar.getSelectedItem();
				Date datum = (Date) dpDatum.getModel().getValue();
				String vreme = (String) cbVreme.getSelectedItem();

				if (klijentKI == null || usluga == null || kozmeticar == null || datum == null || vreme == null) {
					JOptionPane.showMessageDialog(CZakazanTretmanDialog.this, "Niste uneli sve podatke.", "Greška", JOptionPane.ERROR_MESSAGE);				
				} else {
					controler.zakaziTretman(dateFormat(datum), LocalTime.parse(vreme, DateTimeFormatter.ofPattern("HH:mm")),
					 controler.pronadjiKlijenta(klijentKI).getId(), controler.pronadjiKozmeticara(kozmeticar).getId(), controler.pronadjiUslugu(usluga).getId(), idUlogovan);
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
