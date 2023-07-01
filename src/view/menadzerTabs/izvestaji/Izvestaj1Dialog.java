package view.menadzerTabs.izvestaji;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
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

import entity.Kozmeticar;
import manage.Controler;
import net.miginfocom.swing.MigLayout;

public class Izvestaj1Dialog extends JDialog{

	private static final long serialVersionUID = -1338222493881821409L;
	private Controler controler;
	
	public Izvestaj1Dialog(Controler controler, JFrame frame) {
		super(frame);

		this.controler = controler;
		setTitle("Izvestaj broj tretmana i prihodi kozmetičara");			
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
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][][]10[][]20[]");
		setLayout(layout);

		JComboBox<String> cbKozmeticar = new JComboBox<String>();
		DefaultComboBoxModel<String> cbKozmeticariModel = (DefaultComboBoxModel<String>) cbKozmeticar.getModel();
		cbKozmeticariModel.addElement(null);
		for (Kozmeticar kz : controler.sviKozmeticari().values()) {
			cbKozmeticariModel.addElement(kz.getKorisnickoIme());
		}				
		cbKozmeticar.setModel(cbKozmeticariModel);	
		
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
		
		JLabel lblBrojTretmana = new JLabel("");
		JLabel lblPrihod = new JLabel("");
		
		JButton btnOk = new JButton("Generiši");
		JButton btnCancel = new JButton("Odustani");			


		getRootPane().setDefaultButton(btnOk);

		add(new JLabel("Molimo da popunite formu."), "span 2");

		add(new JLabel("Kozmetičar: "), "al right");
		add(cbKozmeticar);

		add(new JLabel("Od: "), "al right");
		add(dpPocetak);

		add(new JLabel("Do: "), "al right");
		add(dpKraj);

		add(new JLabel("Broj tretmana: "), "al right");
		add(lblBrojTretmana);

		add(new JLabel("Prihodi: "), "al right");
		add(lblPrihod);
		
		add(btnOk);
		add(btnCancel);

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String kozmeticar =(String) cbKozmeticar.getSelectedItem();
				Date pocetak = (Date) dpPocetak.getModel().getValue();
				Date kraj = (Date) dpKraj.getModel().getValue();
				
				
				
				if (kozmeticar == null || pocetak == null || kraj == null) {
					JOptionPane.showMessageDialog(Izvestaj1Dialog.this, "Niste uneli sve podatke.", "Greška", JOptionPane.ERROR_MESSAGE);				
				} else if (dateFormat(kraj).isBefore(dateFormat(pocetak))){
					JOptionPane.showMessageDialog(Izvestaj1Dialog.this, "Datumi nisu validni.", "Greška", JOptionPane.ERROR_MESSAGE);		
				} else {
					double[] izvestaj = controler.izvestajBrojPrihodKozmeticari(controler.pronadjiKozmeticara(kozmeticar).getId(), dateFormat(pocetak), dateFormat(kraj));
					lblBrojTretmana.setText(String.format("%d", (int) izvestaj[0]));
					lblPrihod.setText(String.format("%.2f", izvestaj[1]));
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
