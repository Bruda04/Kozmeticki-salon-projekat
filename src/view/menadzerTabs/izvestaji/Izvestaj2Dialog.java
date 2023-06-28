package view.menadzerTabs.izvestaji;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import manage.Controler;
import net.miginfocom.swing.MigLayout;

public class Izvestaj2Dialog extends JDialog{

	private static final long serialVersionUID = -1321802431199202843L;
	private Controler controler;
	
	public Izvestaj2Dialog(Controler controler, JFrame frame) {
		super(frame);

		this.controler = controler;
		setTitle("Izvestaj broj zakazanih i otkazanih termina");			
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
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]10[][][][][][]20[]");
		setLayout(layout);
		
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
		
		JLabel lblZakazani = new JLabel("");
		JLabel lblIzvrseni = new JLabel("");
		JLabel lblOtkazaniK = new JLabel("");
		JLabel lblOtkazaniS = new JLabel("");
		JLabel lblNiPo = new JLabel("");
		
		JButton btnOk = new JButton("Generiši");
		JButton btnCancel = new JButton("Odustani");			


		getRootPane().setDefaultButton(btnOk);

		add(new JLabel("Molimo da popunite formu."), "span 2");
		
		add(new JLabel("Od: "), "al right");
		add(dpPocetak);

		add(new JLabel("Do: "), "al right");
		add(dpKraj);

		add(new JLabel("Broj zakazanih: "), "al right");
		add(lblZakazani);

		add(new JLabel("Broj izvršenih: "), "al right");
		add(lblIzvrseni);
		
		add(new JLabel("Broj otkazanih od strane klijenta: "), "al right");
		add(lblOtkazaniK);
		
		add(new JLabel("Broj otkazanih od strane salona: "), "al right");
		add(lblOtkazaniS);
		
		add(new JLabel("Broj propuštenih: "), "al right");
		add(lblNiPo);
		
		add(btnOk);
		add(btnCancel);

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date pocetak = (Date) dpPocetak.getModel().getValue();
				Date kraj = (Date) dpKraj.getModel().getValue();
				
				
				
				if (pocetak == null || kraj == null) {
					JOptionPane.showMessageDialog(Izvestaj2Dialog.this, "Niste uneli sve podatke.", "Greška", JOptionPane.ERROR_MESSAGE);				
				} else if (dateFormat(kraj).isBefore(dateFormat(pocetak))){
					JOptionPane.showMessageDialog(Izvestaj2Dialog.this, "Datumi nisu validni.", "Greška", JOptionPane.ERROR_MESSAGE);		
				} else {
					int[] izvestaj = controler.izvestajPotvrdjenoOtkazano(dateFormat(pocetak), dateFormat(kraj));
					lblZakazani.setText(String.format("%d", izvestaj[0]));
					lblIzvrseni.setText(String.format("%d", izvestaj[1]));
					lblOtkazaniK.setText(String.format("%d", izvestaj[2]));
					lblOtkazaniS.setText(String.format("%d", izvestaj[3]));
					lblNiPo.setText(String.format("%d", izvestaj[4]));
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
