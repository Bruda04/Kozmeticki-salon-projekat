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

import entity.StanjeZakazanogTretmana;
import manage.Controler;
import model.ZakazanTretmanTableModel;
import net.miginfocom.swing.MigLayout;
import view.recepcionerTabs.CZakazanTretmanDialog;

public class ZakazaniTretmaniPnl extends JPanel{
	
	private static final long serialVersionUID = -4088744473089428422L;

	public ZakazaniTretmaniPnl(Controler controler, JFrame frame) {
		setLayout(new BorderLayout());

		JPanel pnlCrud = new JPanel(new MigLayout("al center", "[]20[]20[]20[]20[]20[]", "15[]25"));
		JButton btnZak = new JButton("ZAKAŽI");
		JButton btnIzv = new JButton("IZVRŠI");
		JButton btnOtkKlj = new JButton("OTKAZAO KLIJENT");
		JButton btnOtkSln = new JButton("OTKAZAO SALON");
		JButton btnNiPo = new JButton("NIJE SE POJAVIO");
		JButton btnDel = new JButton("OBRIŠI");

		ImageIcon zakIcon = new ImageIcon("img/add.gif");		
		ImageIcon zakScaled = new ImageIcon(zakIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		zakIcon = zakScaled;
		btnZak.setIcon(zakIcon);
		
		ImageIcon izvIcon = new ImageIcon("img/done.gif");		
		ImageIcon izvScaled = new ImageIcon(izvIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		izvIcon = izvScaled;
		btnIzv.setIcon(izvIcon);

		ImageIcon otkIcon = new ImageIcon("img/remove.gif");		
		ImageIcon otkScaled = new ImageIcon(otkIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		otkIcon = otkScaled;
		btnOtkKlj.setIcon(otkIcon);

		ImageIcon otsIcon = new ImageIcon("img/remove.gif");		
		ImageIcon otsScaled = new ImageIcon(otsIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		otsIcon = otsScaled;
		btnOtkSln.setIcon(otsIcon);

		ImageIcon nipIcon = new ImageIcon("img/remove.gif");		
		ImageIcon nipScaled = new ImageIcon(nipIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		nipIcon = nipScaled;
		btnNiPo.setIcon(nipIcon);
		
		ImageIcon delIcon = new ImageIcon("img/remove.gif");		
		ImageIcon delScaled = new ImageIcon(delIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		delIcon = delScaled;
		btnDel.setIcon(delIcon);

		pnlCrud.add(btnZak);
		pnlCrud.add(btnIzv);
		pnlCrud.add(btnOtkKlj);
		pnlCrud.add(btnOtkSln);
		pnlCrud.add(btnNiPo);
		pnlCrud.add(btnDel);

		add(pnlCrud, BorderLayout.NORTH);

		ZakazanTretmanTableModel tblmdZakazanTretman = new ZakazanTretmanTableModel(controler);
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


		add(scpTblZakazaniTretmani, BorderLayout.CENTER);
		
		btnOtkSln.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblZakazanTretman.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniPnl.this, "Niste označili Zakazan Tretman.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}
				int idZakazanTretman = (int) tblZakazanTretman.getValueAt(row, 0);		

				if (controler.pronadjiZakazanTretman(idZakazanTretman).getStanje() != StanjeZakazanogTretmana.ZAKAZAN) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniPnl.this, "Niste označili Zakazan Tretman koji može biti otkazan.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}

				int yesNo = JOptionPane.showConfirmDialog(ZakazaniTretmaniPnl.this, "Da li ste sigurni da želite da otkažete Zakazan Tretman u ime salona?", "Otkazivanje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (yesNo == JOptionPane.YES_OPTION) {
					controler.otkaziTretmanSalon(idZakazanTretman);
					tblmdZakazanTretman.refresh();
				}

			}
		});

		btnOtkKlj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblZakazanTretman.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniPnl.this, "Niste označili Zakazan Tretman.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}
				int idZakazanTretman = (int) tblZakazanTretman.getValueAt(row, 0);		

				if (controler.pronadjiZakazanTretman(idZakazanTretman).getStanje() != StanjeZakazanogTretmana.ZAKAZAN) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniPnl.this, "Niste označili Zakazan Tretman koji može biti otkazan.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}

				int yesNo = JOptionPane.showConfirmDialog(ZakazaniTretmaniPnl.this, "Da li ste sigurni da želite da otkažete Zakazan Tretman u ime klijenta?", "Otkazivanje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (yesNo == JOptionPane.YES_OPTION) {
					controler.otkaziTretmanKlijent(idZakazanTretman);
					tblmdZakazanTretman.refresh();
				}

			}
		});
		
		btnNiPo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblZakazanTretman.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniPnl.this, "Niste označili Zakazan Tretman.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}
				int idZakazanTretman = (int) tblZakazanTretman.getValueAt(row, 0);		

				if (controler.pronadjiZakazanTretman(idZakazanTretman).getStanje() != StanjeZakazanogTretmana.ZAKAZAN) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniPnl.this, "Niste označili Zakazan Tretman koji može biti otkazan.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}

				int yesNo = JOptionPane.showConfirmDialog(ZakazaniTretmaniPnl.this, "Da li ste sigurni da želite da označite da se klijent nije pojavio?", "Otkazivanje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (yesNo == JOptionPane.YES_OPTION) {
					controler.propustenTretmanKlijent(idZakazanTretman);
					tblmdZakazanTretman.refresh();
				}

			}
		});
		
		btnIzv.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblZakazanTretman.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniPnl.this, "Niste označili Zakazan Tretman.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}
				int idZakazanTretman = (int) tblZakazanTretman.getValueAt(row, 0);		

				if (controler.pronadjiZakazanTretman(idZakazanTretman).getStanje() != StanjeZakazanogTretmana.ZAKAZAN) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniPnl.this, "Niste označili Zakazan Tretman koji može biti izvršen.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}

				int yesNo = JOptionPane.showConfirmDialog(ZakazaniTretmaniPnl.this, "Da li ste sigurni da želite da označite da je tretman izvršen?", "Izvršen", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (yesNo == JOptionPane.YES_OPTION) {
					controler.izvrsiTretman(idZakazanTretman);
					tblmdZakazanTretman.refresh();
				}
			}
		});

		btnZak.addActionListener(actionListener -> {
			new CZakazanTretmanDialog(controler, frame, 0);
			tblmdZakazanTretman.refresh();
		});

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

	}


}
