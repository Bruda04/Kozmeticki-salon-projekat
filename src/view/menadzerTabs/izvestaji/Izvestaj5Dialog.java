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

public class Izvestaj5Dialog extends JDialog{

	private static final long serialVersionUID = -4886278069208990373L;
	private Controler controler;
	
	public Izvestaj5Dialog(Controler controler, JFrame frame) {
		super(frame);

		this.controler = controler;
		setTitle("Izvestaj prihodi i rashodi");			
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
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[][]10[][]20[]");
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
		
		JLabel lblPrihodi = new JLabel("");
		JLabel lblRashodi = new JLabel("");

		
		JButton btnOk = new JButton("Generiši");
		JButton btnCancel = new JButton("Odustani");			


		getRootPane().setDefaultButton(btnOk);

		add(new JLabel("Molimo da popunite formu."), "span 2");
		
		add(new JLabel("Od: "), "al right");
		add(dpPocetak);

		add(new JLabel("Do: "), "al right");
		add(dpKraj);

		add(new JLabel("Prihodi: "), "al right");
		add(lblPrihodi);

		add(new JLabel("Rashodi: "), "al right");
		add(lblRashodi);
		
		add(btnOk);
		add(btnCancel);

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Date pocetak = (Date) dpPocetak.getModel().getValue();
				Date kraj = (Date) dpKraj.getModel().getValue();
				
				
				
				if (pocetak == null || kraj == null) {
					JOptionPane.showMessageDialog(Izvestaj5Dialog.this, "Niste uneli sve podatke.", "Greška", JOptionPane.ERROR_MESSAGE);				
				} else if (dateFormat(kraj).isBefore(dateFormat(pocetak))){
					JOptionPane.showMessageDialog(Izvestaj5Dialog.this, "Datumi nisu validni.", "Greška", JOptionPane.ERROR_MESSAGE);		
				} else {
					double[] izvestaj = controler.izvestajPrihodiRashodi(dateFormat(pocetak), dateFormat(kraj));
					lblPrihodi.setText(String.format("%.2f", izvestaj[0]));
					lblRashodi.setText(String.format("%.2f", izvestaj[1]));
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
