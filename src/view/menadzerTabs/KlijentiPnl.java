package view.menadzerTabs;

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

import manage.Controler;
import model.KlijentTableModel;
import net.miginfocom.swing.MigLayout;

public class KlijentiPnl extends JPanel{
	private static final long serialVersionUID = 6588939234023333061L;

	public KlijentiPnl(Controler controler, JFrame frame) {
		setLayout(new BorderLayout());

		JPanel pnlCrud = new JPanel(new MigLayout("al center", "[]20[]20[]", "15[]25"));
		JButton btnAdd = new JButton("DODAJ");
		JButton btnUpd = new JButton("IZMENI");
		JButton btnDel = new JButton("OBRIŠI");
		
		ImageIcon addIcon = new ImageIcon("img/add.gif");		
		ImageIcon addScaled = new ImageIcon(addIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		addIcon = addScaled;
		btnAdd.setIcon(addIcon);
		
		ImageIcon updIcon = new ImageIcon("img/edit.gif");		
		ImageIcon updScaled = new ImageIcon(updIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		updIcon = updScaled;
		btnUpd.setIcon(updIcon);
		
		ImageIcon delIcon = new ImageIcon("img/remove.gif");		
		ImageIcon delScaled = new ImageIcon(delIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		delIcon = delScaled;
		btnDel.setIcon(delIcon);

		pnlCrud.add(btnAdd);
		pnlCrud.add(btnUpd);
		pnlCrud.add(btnDel);

		add(pnlCrud, BorderLayout.NORTH);

		KlijentTableModel tblmdKlijenti = new KlijentTableModel(controler);
		JTable tblKlijenti = new JTable(tblmdKlijenti);
		tblKlijenti.setAutoCreateRowSorter(true);
		tblKlijenti.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblKlijenti.getTableHeader().setReorderingAllowed(false);

		JScrollPane scpTblKlijenti = new JScrollPane(tblKlijenti);


		add(scpTblKlijenti, BorderLayout.CENTER);
		
		TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
		tableSorter.setModel((AbstractTableModel) tblKlijenti.getModel());
		tblKlijenti.setRowSorter(tableSorter);
		
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
		

		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblKlijenti.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(KlijentiPnl.this, "Niste označili Klijenta.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}

				int idKlijenta = (int) tblKlijenti.getValueAt(row, 0);
				int yesNo = JOptionPane.showConfirmDialog(KlijentiPnl.this, "Da li ste sigurni da želite da obrišete Klijenta?", "Brisanje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (yesNo == JOptionPane.YES_OPTION) {
					controler.obrisiKlijenta(idKlijenta);
					tblmdKlijenti.refresh();
				}

			}
		});
		
		btnAdd.addActionListener(actionListener -> {
				new CUKlijentiDialog(controler, frame, -1);
				tblmdKlijenti.refresh();
		});
		
		btnUpd.addActionListener(actionListener -> {
			int row = tblKlijenti.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(KlijentiPnl.this, "Niste označili Klijenta.", "Greška", JOptionPane.ERROR_MESSAGE);				
				return;
			}
			int idKlijenta = (int) tblKlijenti.getValueAt(row, 0);
			new CUKlijentiDialog(controler, frame, idKlijenta);
			tblmdKlijenti.refresh();
		});

	}


}
