package view.kozmeticarTabs;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

import manage.Controler;
import model.ZakazanTretmanKozmeticaraTableModel;
import net.miginfocom.swing.MigLayout;

public class ZakazaniTretmaniKozmeticaraPnl extends JPanel{
	
	private static final long serialVersionUID = -4088744473089428422L;

	public ZakazaniTretmaniKozmeticaraPnl(Controler controler, JFrame frame, int idUlogovanog) {
		setLayout(new BorderLayout());
		
		JPanel pnlTitle = new JPanel(new MigLayout("al center", "[]", "15[]25"));
		pnlTitle.add(new JLabel("Raspored zakazanih tretmana"));

		ZakazanTretmanKozmeticaraTableModel tblmdZakazanTretman = new ZakazanTretmanKozmeticaraTableModel(controler, idUlogovanog);
		JTable tblZakazanTretman = new JTable(tblmdZakazanTretman);
		tblZakazanTretman.setAutoCreateRowSorter(true);
		tblZakazanTretman.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblZakazanTretman.getTableHeader().setReorderingAllowed(false);

		JScrollPane scpTblZakazaniTretmani = new JScrollPane(tblZakazanTretman);

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
		
		
		add(pnlTitle, BorderLayout.NORTH);
		add(scpTblZakazaniTretmani, BorderLayout.CENTER);
	}


}
