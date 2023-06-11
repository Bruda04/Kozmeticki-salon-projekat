package view.menadzerTabs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entity.TipTretmana;
import manage.Controler;
import net.miginfocom.swing.MigLayout;

public class CUTipTretmanaDialog extends JDialog{
	private static final long serialVersionUID = -6805628090399925566L;

	private Controler controler;

	public CUTipTretmanaDialog(Controler controler, JFrame frame, int idTipaTretmana) {
		super(frame);

		this.controler = controler;
		if (idTipaTretmana == -1) {
			setTitle("Kreiranje tipa tretmana");			
		} else {
			setTitle("Izmena tipa tretmana");			
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/icon.png").getImage());
		setResizable(false);
		setModal(true);
		cuGui(frame, idTipaTretmana);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void cuGui(JFrame frame, int idTipaTretmana) {
		MigLayout layout = new MigLayout("wrap 3", "[][][]", "[]20[][][][]20[]");
		setLayout(layout);

		JTextField tfNaziv = new JTextField(20);

//		HashMap<Integer, Usluga> sveUsluge = controler.sveUsluge();

//		JButton btnDodajUslugu = new JButton("DODAJ >>>");
//		JButton btnOduzmiUslugu = new JButton("<<< OBRIŠI");
//
//		DefaultListModel<Usluga> lmSveUsluge = new DefaultListModel<>(); 
//		DefaultListModel<Usluga> lmDodeljeneUsluge = new DefaultListModel<>(); 
//
//		for (Usluga u : sveUsluge.values()) {
//			lmSveUsluge.addElement(u);
//		}
//
//		JList<Usluga> lstSveUsluge = new JList<>(lmSveUsluge);
//		JList<Usluga> lstDodeljeneUsluge = new JList<>(lmDodeljeneUsluge);
//
//		UslugeJListRenderer cellRenderer = new UslugeJListRenderer();
//
//		lstSveUsluge.setCellRenderer(cellRenderer);
//		lstDodeljeneUsluge.setCellRenderer(cellRenderer);
//
//		JScrollPane splstSveUsluge = new JScrollPane(lstSveUsluge);
//		JScrollPane splstDodeljeneUsluge = new JScrollPane(lstDodeljeneUsluge);

		String okTekst;

		TipTretmana tt = controler.pronadjiTipTretmana(idTipaTretmana);

		if (idTipaTretmana == -1) {
			okTekst = "Kreiraj";
		} else {
			okTekst = "Izmeni";
			tfNaziv.setText(tt.getNaziv());

//			for (int idU : tt.getSkupTipovaUsluga()) {
//				lmDodeljeneUsluge.addElement(controler.pronadjiUslugu(idU));
//				lmSveUsluge.removeElement(controler.pronadjiUslugu(idU));
//			}
		}

//		btnDodajUslugu.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				for (Usluga u : lstSveUsluge.getSelectedValuesList()) {
//					lmDodeljeneUsluge.addElement(u);
//					lmSveUsluge.removeElement(u);					
//				}
//			}
//		});
//
//		btnOduzmiUslugu.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				for (Usluga u : lstDodeljeneUsluge.getSelectedValuesList()) {
//					lmDodeljeneUsluge.removeElement(u);
//					lmSveUsluge.addElement(u);					
//				}
//			}
//		});

		JButton btnOk = new JButton(okTekst);
		JButton btnCancel = new JButton("Odustani");			


		getRootPane().setDefaultButton(btnOk);

		add(new JLabel("Molimo da popunite formu."), "span 3");

		add(new JLabel("Naziv: "), "al right, span 2");
		add(tfNaziv);

//		add(new JLabel("Usluge koje pripadaju tipu tretmana"), "span 3");

//		add(splstSveUsluge, "spany 2,w 30%, growx");
//		add(btnDodajUslugu, "w 20%");
//		add(splstDodeljeneUsluge, "spany 2,w 30%, growx");
//		add(btnOduzmiUslugu, "w 20%");


		add(btnOk);
		add(btnCancel);

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String naziv = tfNaziv.getText().trim();
				ArrayList<Integer> listaUsluga = new ArrayList<>();
//				
//				for(int i = 0; i< lstDodeljeneUsluge.getModel().getSize(); i++){
//					listaUsluga.add(lstDodeljeneUsluge.getModel().getElementAt(i).getId());
//				}

				if (naziv == null ) {
					JOptionPane.showMessageDialog(CUTipTretmanaDialog.this, "Niste uneli sve podatke.", "Greška", JOptionPane.ERROR_MESSAGE);				
				} else {
					if (idTipaTretmana == -1) {
						controler.dodajTipTretmana(naziv, listaUsluga);						
					} else {
						controler.izmeniTipTretmana(idTipaTretmana, naziv, tt.getSkupTipovaUsluga());
					}

					setVisible(false);
					dispose();
				}

			}
		});

		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
	}


}
