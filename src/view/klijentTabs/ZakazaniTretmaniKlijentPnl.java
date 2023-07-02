package view.klijentTabs;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
		
		JPanel pnlInfo = new JPanel(new MigLayout("al center, wrap 2", "[][]", "15[]10[]15"));
		JTextField tfSearch = new JTextField();	
		String kartica;
		if (controler.pronadjiKlijenta(idUlogovan).isKarticaLojalnosti()) {
			kartica = "DA";
		} else {
			kartica = "NE";
		}
		
		JLabel lblInfo = new JLabel(String.format("Ima karticu lojalnosti: %s   Potrošeno: %.2f", kartica, controler.pronadjiKlijenta(idUlogovan).getPotroseno()));

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
		pnlInfo.add(lblInfo, "span 2, al center");
		add(pnlInfo, BorderLayout.SOUTH);

		btnOtkKlj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblZakazanTretman.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniKlijentPnl.this, "Niste označili Zakazan Tretman.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}
				int idZakazanTretman = controler.pronadjiZakazanTretman(controler.pronadjiUslugu((String) tblZakazanTretman.getValueAt(row, 1)).getId(),
						controler.pronadjiKozmeticara((String) tblZakazanTretman.getValueAt(row, 0)).getId(),
						(LocalDate) tblZakazanTretman.getValueAt(row, 2),
						LocalTime.parse((String) tblZakazanTretman.getValueAt(row, 3), DateTimeFormatter.ofPattern("HH:mm"))).getId();			

				if (controler.pronadjiZakazanTretman(idZakazanTretman).getStanje() != StanjeZakazanogTretmana.ZAKAZAN) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniKlijentPnl.this, "Niste označili Zakazan Tretman koji može biti otkazan.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}

				int yesNo = JOptionPane.showConfirmDialog(ZakazaniTretmaniKlijentPnl.this, "Da li ste sigurni da želite da otkažete Zakazan Tretman?", "Otkazivanje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (yesNo == JOptionPane.YES_OPTION) {
					controler.otkaziTretmanKlijent(idZakazanTretman);
					tblmdZakazanTretman.refresh();
					String karticaOtk;
					if (controler.pronadjiKlijenta(idUlogovan).isKarticaLojalnosti()) {
						karticaOtk = "DA";
					} else {
						karticaOtk = "NE";
					}
					lblInfo.setText(String.format("Ima karticu lojalnosti: %s   Potrošeno: %.2f", karticaOtk, controler.pronadjiKlijenta(idUlogovan).getPotroseno()));
				}

			}
		});

		btnZak.addActionListener(actionListener -> {
			new CZakazanTretmanKlijentDialog(controler, frame, idUlogovan);
			tblmdZakazanTretman.refresh();
			String karticaZak;
			if (controler.pronadjiKlijenta(idUlogovan).isKarticaLojalnosti()) {
				karticaZak = "DA";
			} else {
				karticaZak = "NE";
			}
			lblInfo.setText(String.format("Ima karticu lojalnosti: %s   Potrošeno: %.2f", karticaZak, controler.pronadjiKlijenta(idUlogovan).getPotroseno()));
		});


	}


}
