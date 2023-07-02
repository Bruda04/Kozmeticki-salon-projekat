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
import model.TipTretmanaKozmeticarTableModel;
import net.miginfocom.swing.MigLayout;

public class TipoviTretmanaKozmeticaraPnl extends JPanel{
	
	private static final long serialVersionUID = -4396612449728911864L;

	public TipoviTretmanaKozmeticaraPnl(Controler controler, JFrame frame, int idUlogovanog) {
		setLayout(new BorderLayout());

		TipTretmanaKozmeticarTableModel tblmdTipTretmana = new TipTretmanaKozmeticarTableModel(controler, idUlogovanog);
		JTable tblTipTretmana = new JTable(tblmdTipTretmana);
		tblTipTretmana.setAutoCreateRowSorter(true);
		tblTipTretmana.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblTipTretmana.getTableHeader().setReorderingAllowed(false);

		JScrollPane scpTblTipoviTretmana = new JScrollPane(tblTipTretmana);

		TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
		tableSorter.setModel((AbstractTableModel) tblTipTretmana.getModel());
		tblTipTretmana.setRowSorter(tableSorter);
		
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

		add(scpTblTipoviTretmana, BorderLayout.CENTER);
	}


}
