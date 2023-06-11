package view.recepcionerTabs;

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

import entity.StanjeZakazanogTretmana;
import manage.Controler;
import model.ZakazanTretmanTableModel;
import net.miginfocom.swing.MigLayout;

public class ZakazaniTretmaniRecepcionerPnl extends JPanel{

	private static final long serialVersionUID = -9216009020838943626L;

	public ZakazaniTretmaniRecepcionerPnl(Controler controler, JFrame frame, int idUlogovan) {
		setLayout(new BorderLayout());

		JPanel pnlCrud = new JPanel(new MigLayout("al center", "[]20[]20[]20[]20[]", "15[]25"));
		JButton btnZak = new JButton("ZAKAŽI");
		JButton btnIzv = new JButton("IZVRŠI");
		JButton btnOtkKlj = new JButton("OTKAZAO KLIJENT");
		JButton btnOtkSln = new JButton("OTKAZAO SALON");
		JButton btnNiPo = new JButton("NIJE SE POJAVIO");

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

		pnlCrud.add(btnZak);
		pnlCrud.add(btnIzv);
		pnlCrud.add(btnOtkKlj);
		pnlCrud.add(btnOtkSln);
		pnlCrud.add(btnNiPo);

		add(pnlCrud, BorderLayout.NORTH);

		ZakazanTretmanTableModel tblmdZakazanTretman = new ZakazanTretmanTableModel(controler);
		JTable tblZakazanTretman = new JTable(tblmdZakazanTretman);
		tblZakazanTretman.setAutoCreateRowSorter(true);
		tblZakazanTretman.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scpTblZakazaniTretmani = new JScrollPane(tblZakazanTretman);

		add(scpTblZakazaniTretmani, BorderLayout.CENTER);

		btnOtkSln.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblZakazanTretman.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniRecepcionerPnl.this, "Niste označili Zakazan Tretman.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}
				int idZakazanTretman = (int) tblZakazanTretman.getValueAt(row, 0);		

				if (controler.pronadjiZakazanTretman(idZakazanTretman).getStanje() != StanjeZakazanogTretmana.ZAKAZAN) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniRecepcionerPnl.this, "Niste označili Zakazan Tretman koji može biti otkazan.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}

				int yesNo = JOptionPane.showConfirmDialog(ZakazaniTretmaniRecepcionerPnl.this, "Da li ste sigurni da želite da otkažete Zakazan Tretman u ime salona?", "Otkazivanje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

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
					JOptionPane.showMessageDialog(ZakazaniTretmaniRecepcionerPnl.this, "Niste označili Zakazan Tretman.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}
				int idZakazanTretman = (int) tblZakazanTretman.getValueAt(row, 0);		

				if (controler.pronadjiZakazanTretman(idZakazanTretman).getStanje() != StanjeZakazanogTretmana.ZAKAZAN) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniRecepcionerPnl.this, "Niste označili Zakazan Tretman koji može biti otkazan.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}

				int yesNo = JOptionPane.showConfirmDialog(ZakazaniTretmaniRecepcionerPnl.this, "Da li ste sigurni da želite da otkažete Zakazan Tretman u ime klijenta?", "Otkazivanje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

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
					JOptionPane.showMessageDialog(ZakazaniTretmaniRecepcionerPnl.this, "Niste označili Zakazan Tretman.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}
				int idZakazanTretman = (int) tblZakazanTretman.getValueAt(row, 0);		

				if (controler.pronadjiZakazanTretman(idZakazanTretman).getStanje() != StanjeZakazanogTretmana.ZAKAZAN) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniRecepcionerPnl.this, "Niste označili Zakazan Tretman koji može biti otkazan.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}

				int yesNo = JOptionPane.showConfirmDialog(ZakazaniTretmaniRecepcionerPnl.this, "Da li ste sigurni da želite da označite da se klijent nije pojavio?", "Otkazivanje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

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
					JOptionPane.showMessageDialog(ZakazaniTretmaniRecepcionerPnl.this, "Niste označili Zakazan Tretman.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}
				int idZakazanTretman = (int) tblZakazanTretman.getValueAt(row, 0);		

				if (controler.pronadjiZakazanTretman(idZakazanTretman).getStanje() != StanjeZakazanogTretmana.ZAKAZAN) {
					JOptionPane.showMessageDialog(ZakazaniTretmaniRecepcionerPnl.this, "Niste označili Zakazan Tretman koji može biti izvršen.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}

				int yesNo = JOptionPane.showConfirmDialog(ZakazaniTretmaniRecepcionerPnl.this, "Da li ste sigurni da želite da označite da je tretman izvršen?", "Izvršen", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (yesNo == JOptionPane.YES_OPTION) {
					controler.izvrsiTretman(idZakazanTretman);
					tblmdZakazanTretman.refresh();
				}
			}
		});

		btnZak.addActionListener(actionListener -> {
			new CZakazanTretmanDialog(controler, frame, idUlogovan);
			tblmdZakazanTretman.refresh();
		});


	}


}
