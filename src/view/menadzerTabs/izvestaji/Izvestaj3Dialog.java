package view.menadzerTabs.izvestaji;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
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

import entity.Usluga;
import manage.Controler;
import net.miginfocom.swing.MigLayout;

public class Izvestaj3Dialog extends JDialog{

	private static final long serialVersionUID = -282971551256139376L;
	private Controler controler;
	
	public Izvestaj3Dialog(Controler controler, JFrame frame) {
		super(frame);

		this.controler = controler;
		setTitle("Izvestaj statistike usluge");			
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/icon.png").getImage());
		setResizable(false);
		setModal(true);
		cuGui(frame);
		pack();
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
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][][]10[][][][][][][]15[]");
		setLayout(layout);
		
		JComboBox<String> cbUsluga = new JComboBox<String>();
		DefaultComboBoxModel<String> cbUslugaModel = (DefaultComboBoxModel<String>) cbUsluga.getModel();
		cbUslugaModel.addElement(null);
		for (Usluga u : controler.sveUsluge().values()) {
			cbUslugaModel.addElement(u.getNazivUsluge());
		}	
		
		UtilDateModel modelOd = new UtilDateModel();
		Properties pOd = new Properties();
		pOd.put("text.today", "Today");
		pOd.put("text.month", "Month");
		pOd.put("text.year", "Year");
		JDatePanelImpl datePanelOd = new JDatePanelImpl(modelOd, pOd);
		JDatePickerImpl dpPocetak = new JDatePickerImpl(datePanelOd, new DateComponentFormatter());
		
		UtilDateModel modelDo = new UtilDateModel();
		Properties pDo = new Properties();
		pDo.put("text.today", "Today");
		pDo.put("text.month", "Month");
		pDo.put("text.year", "Year");
		JDatePanelImpl datePanelDo = new JDatePanelImpl(modelDo, pDo);
		JDatePickerImpl dpKraj = new JDatePickerImpl(datePanelDo, new DateComponentFormatter());
		
		JLabel idUsluge = new JLabel("");
		JLabel nazivUsluge = new JLabel("");
		JLabel tipUsluge = new JLabel("");
		JLabel trajanjeUsluge = new JLabel("");
		JLabel cenaUsluge = new JLabel("");
		JLabel zakazivanjaUsluge = new JLabel("");
		JLabel prihodiUsluge = new JLabel("");
		
		JButton btnOk = new JButton("Generiši");
		JButton btnCancel = new JButton("Odustani");			


		getRootPane().setDefaultButton(btnOk);

		add(new JLabel("Molimo da popunite formu."), "span 2");
		
		add(new JLabel("Usluga: "), "al right");
		add(cbUsluga);
		
		add(new JLabel("Od: "), "al right");
		add(dpPocetak);

		add(new JLabel("Do: "), "al right");
		add(dpKraj);

		add(new JLabel("id: "), "al right");
		add(idUsluge);

		add(new JLabel("Naziv: "), "al right");
		add(nazivUsluge);
		
		add(new JLabel("Tip tretmana: "), "al right");
		add(tipUsluge);
		
		add(new JLabel("Trajanje: "), "al right");
		add(trajanjeUsluge);
		
		add(new JLabel("Cena: "), "al right");
		add(cenaUsluge);
		
		add(new JLabel("Broj zakazivanja: "), "al right");
		add(zakazivanjaUsluge);
		
		add(new JLabel("Prihodi: "), "al right");
		add(prihodiUsluge);
		
		add(btnOk);
		add(btnCancel);

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String usluga =(String) cbUsluga.getSelectedItem();
				Date pocetak = (Date) dpPocetak.getModel().getValue();
				Date kraj = (Date) dpKraj.getModel().getValue();
			
				
				if (usluga == null || pocetak == null || kraj == null) {
					JOptionPane.showMessageDialog(Izvestaj3Dialog.this, "Niste uneli sve podatke.", "Greška", JOptionPane.ERROR_MESSAGE);				
				} else {
					HashMap<String, Object> izvestaj = controler.izvestajUslugaStatistika(controler.pronadjiUslugu(usluga).getId(), dateFormat(pocetak), dateFormat(kraj));
					idUsluge.setText(String.format("%d", izvestaj.get("id")));
					nazivUsluge.setText(String.format("%s", izvestaj.get("naziv")));
					tipUsluge.setText(String.format("%s", izvestaj.get("tip")));
					trajanjeUsluge.setText(String.format("%d", izvestaj.get("trajanje")));
					cenaUsluge.setText(String.format("%.2f", izvestaj.get("cena")));
					zakazivanjaUsluge.setText(String.format("%d", izvestaj.get("zakazivanja")));
					prihodiUsluge.setText(String.format("%.2f", izvestaj.get("prihodi")));

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
