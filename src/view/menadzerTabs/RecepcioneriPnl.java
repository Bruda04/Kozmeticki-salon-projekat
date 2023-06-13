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
import model.RecepcionerTableModel;
import net.miginfocom.swing.MigLayout;

public class RecepcioneriPnl extends JPanel{

	private static final long serialVersionUID = 1424056375810707381L;

	public RecepcioneriPnl(Controler controler, JFrame frame) {	
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

		RecepcionerTableModel tblmdRecepcioneri = new RecepcionerTableModel(controler);
		JTable tblRecepcioneri = new JTable(tblmdRecepcioneri);
		tblRecepcioneri.setAutoCreateRowSorter(true);
		tblRecepcioneri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblRecepcioneri.getTableHeader().setReorderingAllowed(false);

		JScrollPane scpTblRecepcioneri = new JScrollPane(tblRecepcioneri);
		
		TableRowSorter<AbstractTableModel> tableSorter = new TableRowSorter<AbstractTableModel>();
		tableSorter.setModel((AbstractTableModel) tblRecepcioneri.getModel());
		tblRecepcioneri.setRowSorter(tableSorter);
		
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

		add(scpTblRecepcioneri, BorderLayout.CENTER);

		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblRecepcioneri.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(RecepcioneriPnl.this, "Niste označili Recepcionera.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}

				int idRecepcionera = (int) tblRecepcioneri.getValueAt(row, 0);
				int yesNo = JOptionPane.showConfirmDialog(RecepcioneriPnl.this, "Da li ste sigurni da želite da obrišete Recepciora?", "Brisanje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (yesNo == JOptionPane.YES_OPTION) {
					controler.obrisiRecepcionera(idRecepcionera);
					tblmdRecepcioneri.refresh();
				}

			}
		});
		
		btnAdd.addActionListener(actionListener -> {
			new CURecepcionerDialog(controler, frame, -1);
			tblmdRecepcioneri.refresh();
		});

		btnUpd.addActionListener(actionListener -> {
			int row = tblRecepcioneri.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(RecepcioneriPnl.this, "Niste označili Recepcionera.", "Greška", JOptionPane.ERROR_MESSAGE);				
				return;
			}
			int idRecepcionera = (int) tblRecepcioneri.getValueAt(row, 0);
			new CURecepcionerDialog(controler, frame, idRecepcionera);
			tblmdRecepcioneri.refresh();
		});
	}
}