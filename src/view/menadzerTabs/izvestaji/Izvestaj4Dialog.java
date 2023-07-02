package view.menadzerTabs.izvestaji;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import manage.Controler;
import model.KlijentKarticaLojalnostiTableModel;
import net.miginfocom.swing.MigLayout;

public class Izvestaj4Dialog extends JDialog{

	private static final long serialVersionUID = -3313243649018376260L;
	private Controler controler;

	public Izvestaj4Dialog(Controler controler, JFrame frame) {
		super(frame);

		this.controler = controler;
		setTitle("Izvestaj potencijal za karticu lojalnosti");			
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/icon.png").getImage());
		setResizable(false);
		setModal(true);
		cuGui(frame);
		setSize(700, 700);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void cuGui(JFrame frame) {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[]20[]20[]");
		setLayout(layout);

		JSpinner spnPrag = new JSpinner(new SpinnerNumberModel(0.00, 0.00, 9999999.00, 1.00));

		KlijentKarticaLojalnostiTableModel tblmdKlijenti = new KlijentKarticaLojalnostiTableModel(controler, null);
		JTable tblKlijenti = new JTable(tblmdKlijenti);
		tblKlijenti.setAutoCreateRowSorter(true);
		tblKlijenti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scpTblKlijenti = new JScrollPane(tblKlijenti);

		JButton btnOk = new JButton("Generiši");
		JButton btnCancel = new JButton("Odustani");			


		getRootPane().setDefaultButton(btnOk);

		add(new JLabel("Prag za karticu lojalnosti: "), "al right");
		add(spnPrag);

		add(scpTblKlijenti, "span 2, growy, pushy, growx, pushx, al center");

		add(btnOk);
		add(btnCancel);

		spnPrag.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				Double prag = (Double) spnPrag.getValue();		
				if (prag == null) {
					JOptionPane.showMessageDialog(Izvestaj4Dialog.this, "Niste uneli sve podatke.", "Greška", JOptionPane.ERROR_MESSAGE);				
				} else {
					tblmdKlijenti.refresh(prag);
				}
			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Double prag = (Double) spnPrag.getValue();		
				if (prag == null) {
					JOptionPane.showMessageDialog(Izvestaj4Dialog.this, "Niste uneli sve podatke.", "Greška", JOptionPane.ERROR_MESSAGE);				
				} else {
					tblmdKlijenti.refresh(prag);
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
