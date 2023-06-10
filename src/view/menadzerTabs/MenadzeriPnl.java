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
import model.MenadzerTableModel;
import net.miginfocom.swing.MigLayout;

public class MenadzeriPnl extends JPanel{
	private static final long serialVersionUID = 1205905156719316731L;

	public MenadzeriPnl(Controler controler, JFrame frame) {	
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

		MenadzerTableModel tblmdlMenadzeri = new MenadzerTableModel(controler);
		JTable tblMenadzeri = new JTable(tblmdlMenadzeri);
		tblMenadzeri.setAutoCreateRowSorter(true);
		tblMenadzeri.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


		JScrollPane scpTblMenadzeri = new JScrollPane(tblMenadzeri);


		add(scpTblMenadzeri, BorderLayout.CENTER);

		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tblMenadzeri.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(MenadzeriPnl.this, "Niste označili Menadžera.", "Greška", JOptionPane.ERROR_MESSAGE);				
					return;
				}

				int idMenadzera = (int) tblMenadzeri.getValueAt(row, 0);
				int yesNo = JOptionPane.showConfirmDialog(MenadzeriPnl.this, "Da li ste sigurni da želite da obrišete Menadžera?", "Brisanje", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (yesNo == JOptionPane.YES_OPTION) {
					controler.obrisiMenadzera(idMenadzera);
					tblmdlMenadzeri.refresh();
				}

			}
		});

		btnAdd.addActionListener(actionListener -> {
			new CUMenadzerDialog(controler, frame, -1);
			tblmdlMenadzeri.refresh();
		});

		btnUpd.addActionListener(actionListener -> {
			int row = tblMenadzeri.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(MenadzeriPnl.this, "Niste označili Menadžera.", "Greška", JOptionPane.ERROR_MESSAGE);				
				return;
			}
			int idMenadzera = (int) tblMenadzeri.getValueAt(row, 0);
			new CUMenadzerDialog(controler, frame, idMenadzera);
			tblmdlMenadzeri.refresh();
		});


	}
}