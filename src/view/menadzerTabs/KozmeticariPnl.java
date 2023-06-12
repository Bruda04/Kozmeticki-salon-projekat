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
import model.KozmeticarTableModel;
import net.miginfocom.swing.MigLayout;

public class KozmeticariPnl extends JPanel{

	private static final long serialVersionUID = 4250501464323148916L;

	public KozmeticariPnl(Controler controler, JFrame frame) {	
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
			
		KozmeticarTableModel tblmdKozmeticari = new KozmeticarTableModel(controler);
		JTable tblKozmeticari = new JTable(tblmdKozmeticari);
		tblKozmeticari.setAutoCreateRowSorter(true);
		tblKozmeticari.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


		JScrollPane scpTblKozmeticari = new JScrollPane(tblKozmeticari);


		add(scpTblKozmeticari, BorderLayout.CENTER);

		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblKozmeticari.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(KozmeticariPnl.this, "Niste označili Kozmetičara.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}

				int idKozmeticara = (int) tblKozmeticari.getValueAt(row, 0);
				int yesNo = JOptionPane.showConfirmDialog(KozmeticariPnl.this, "Da li ste sigurni da želite da obrišete Kozmetičara?", "Brisanje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (yesNo == JOptionPane.YES_OPTION) {
					controler.obrisiKozmeticara(idKozmeticara);
					tblmdKozmeticari.refresh();
				}

			}
		});
		
		btnAdd.addActionListener(actionListener -> {
			new CUKozmeticarDialog(controler, frame, -1);
			tblmdKozmeticari.refresh();
		});

		btnUpd.addActionListener(actionListener -> {
			int row = tblKozmeticari.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(KozmeticariPnl.this, "Niste označili Kozmetičara.", "Greška", JOptionPane.ERROR_MESSAGE);				
				return;
			}
			int idKozmeticara = (int) tblKozmeticari.getValueAt(row, 0);
			new CUKozmeticarDialog(controler, frame, idKozmeticara);
			tblmdKozmeticari.refresh();
		});


	}
}