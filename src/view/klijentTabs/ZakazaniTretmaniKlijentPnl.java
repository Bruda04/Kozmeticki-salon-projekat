package view.klijentTabs;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;

import entity.StanjeZakazanogTretmana;
import manage.Controler;
import model.ZakazanTretmanKlijentaTableModel;
import net.miginfocom.swing.MigLayout;

public class ZakazaniTretmaniKlijentPnl extends JPanel{

	private static final long serialVersionUID = -3222106222926088223L;

	public ZakazaniTretmaniKlijentPnl(Controler controler, JFrame frame, int idUlogovan) {
		setLayout(new BorderLayout());

		JPanel pnlCrud = new JPanel(new MigLayout("al center", "[]20[]", "15[]25"));
		JButton btnZak = new JButton("ZAKAŽI");
		JButton btnOtkKlj = new JButton("OTKAŽI");

		ImageIcon zakIcon = new ImageIcon("img/add.gif");		
		ImageIcon zakScaled = new ImageIcon(zakIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		zakIcon = zakScaled;
		btnZak.setIcon(zakIcon);

		ImageIcon otkIcon = new ImageIcon("img/remove.gif");		
		ImageIcon otkScaled = new ImageIcon(otkIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		otkIcon = otkScaled;
		btnOtkKlj.setIcon(otkIcon);

		pnlCrud.add(btnZak);
		pnlCrud.add(btnOtkKlj);

		add(pnlCrud, BorderLayout.NORTH);

		ZakazanTretmanKlijentaTableModel tblmdZakazanTretman = new ZakazanTretmanKlijentaTableModel(controler, idUlogovan);
		JTable tblZakazanTretman = new JTable(tblmdZakazanTretman);
		tblZakazanTretman.setAutoCreateRowSorter(true);
		tblZakazanTretman.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblZakazanTretman.getTableHeader().setReorderingAllowed(false);


		JScrollPane scpTblZakazaniTretmani = new JScrollPane(tblZakazanTretman);

		add(scpTblZakazaniTretmani, BorderLayout.CENTER);

		TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
		tableSorter.setModel((AbstractTableModel) tblZakazanTretman.getModel());
		tblZakazanTretman.setRowSorter(tableSorter);
		
		JPanel pnlInfo = new JPanel(new MigLayout("al center", "[][]", "15[]25"));
		JTextField tfSearch = new JTextField();	

		tfSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				changedUpdate(e);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				//System.out.println("~ "+tfSearch.getText());
				if (tfSearch.getText().trim().length() == 0) {
					tableSorter.setRowFilter(null);
				} else {
					tableSorter.setRowFilter(RowFilter.regexFilter("(?i)" + tfSearch.getText().trim()));
				}
			}
		});

		pnlInfo.add(new JLabel("Pretraga: "), "al right");
		pnlInfo.add(tfSearch, "w 30%");
		add(pnlInfo, BorderLayout.SOUTH);

		btnOtkKlj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblZakazanTretman.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniKlijentPnl.this, "Niste označili Zakazan Tretman.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}
				int idZakazanTretman = (int) tblZakazanTretman.getValueAt(row, 0);		

				if (controler.pronadjiZakazanTretman(idZakazanTretman).getStanje() != StanjeZakazanogTretmana.ZAKAZAN) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniKlijentPnl.this, "Niste označili Zakazan Tretman koji može biti otkazan.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}

				int yesNo = JOptionPane.showConfirmDialog(ZakazaniTretmaniKlijentPnl.this, "Da li ste sigurni da želite da otkažete Zakazan Tretman?", "Otkazivanje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (yesNo == JOptionPane.YES_OPTION) {
					controler.otkaziTretmanKlijent(idZakazanTretman);
					tblmdZakazanTretman.refresh();
				}

			}
		});

		btnZak.addActionListener(actionListener -> {
			new CZakazanTretmanKlijentDialog(controler, frame, idUlogovan);
			tblmdZakazanTretman.refresh();
		});


	}


}
