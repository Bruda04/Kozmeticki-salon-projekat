package view.kozmeticarTabs;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

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
		
		JScrollPane scpTblZakazaniTretmani = new JScrollPane(tblZakazanTretman);

		add(pnlTitle, BorderLayout.NORTH);
		add(scpTblZakazaniTretmani, BorderLayout.CENTER);
	}


}
