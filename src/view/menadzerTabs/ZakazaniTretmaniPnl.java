package view.menadzerTabs;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import manage.Controler;
import model.ZakazanTretmanTableModel;
import net.miginfocom.swing.MigLayout;

public class ZakazaniTretmaniPnl extends JPanel{
	
	private static final long serialVersionUID = -4088744473089428422L;

	public ZakazaniTretmaniPnl(Controler controler, JFrame frame) {
		setLayout(new BorderLayout());

		JPanel pnlCrud = new JPanel(new MigLayout("al center", "[]20[]20[]", "15[]25"));
//		JButton btnAdd = new JButton("DODAJ");
//		JButton btnUpd = new JButton("IZMENI");
		JButton btnDel = new JButton("OBRIŠI");
		
//		ImageIcon addIcon = new ImageIcon("img/add.gif");		
//		ImageIcon addScaled = new ImageIcon(addIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
//		addIcon = addScaled;
//		btnAdd.setIcon(addIcon);
		
//		ImageIcon updIcon = new ImageIcon("img/edit.gif");		
//		ImageIcon updScaled = new ImageIcon(updIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
//		updIcon = updScaled;
//		btnUpd.setIcon(updIcon);
		
		ImageIcon delIcon = new ImageIcon("img/remove.gif");		
		ImageIcon delScaled = new ImageIcon(delIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		delIcon = delScaled;
		btnDel.setIcon(delIcon);

//		pnlCrud.add(btnAdd);
//		pnlCrud.add(btnUpd);
		pnlCrud.add(btnDel);

		add(pnlCrud, BorderLayout.NORTH);

		ZakazanTretmanTableModel tblmdZakazanTretman = new ZakazanTretmanTableModel(controler);
		JTable tblZakazanTretman = new JTable(tblmdZakazanTretman);
		tblZakazanTretman.setAutoCreateRowSorter(true);
		tblZakazanTretman.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scpTblZakazaniTretmani = new JScrollPane(tblZakazanTretman);


		add(scpTblZakazaniTretmani, BorderLayout.CENTER);

		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblZakazanTretman.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniPnl.this, "Niste označili Zakazan Tretman.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}

				int idZakazanTretman = (int) tblZakazanTretman.getValueAt(row, 0);
				int yesNo = JOptionPane.showConfirmDialog(ZakazaniTretmaniPnl.this, "Da li ste sigurni da želite da obrišete Zakazan Tretman?", "Brisanje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (yesNo == JOptionPane.YES_OPTION) {
					controler.obrisiZakazanTretman(idZakazanTretman);
					tblmdZakazanTretman.refresh();
				}

			}
		});
		
//		btnAdd.addActionListener(actionListener -> {
//				new CUZakazanTretmanDialog(controler, frame, -1);
//				tblmdZakazanTretman.refresh();
//		});
//		
//		btnUpd.addActionListener(actionListener -> {
//			int row = tblZakazanTretman.getSelectedRow();
//			if (row == -1) {
//				JOptionPane.showMessageDialog(ZakazaniTretmaniPnl.this, "Niste označili Tip Tretmana.", "Greška", JOptionPane.ERROR_MESSAGE);				
//				return;
//			}
//			int idZakazanTretman = (int) tblZakazanTretman.getValueAt(row, 0);
//			new CUZakazanTretmanDialog(controler, frame, idZakazanTretman);
//			tblmdZakazanTretman.refresh();
//		});

	}


}
