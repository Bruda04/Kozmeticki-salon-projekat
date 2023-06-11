package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import manage.Controler;
import net.miginfocom.swing.MigLayout;
import view.recepcionerTabs.ZakazaniTretmaniRecepcionerPnl;

public class RecepcionerView extends JFrame {
	private static final long serialVersionUID = 8226569777300808762L;

	private Controler controler;
	private int idUlogovan;

	public RecepcionerView(Controler controler, int idUlogovanog) {
		this.controler = controler;
		this.idUlogovan = idUlogovanog;
		frameSetup();
	}

	private void frameSetup() {
		this.setTitle("Kozmetički salon - RECEPCIONER");
		this.setSize(1000, 1000);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setIconImage(new ImageIcon("img/icon.png").getImage());
		setupGUI();
		this.setVisible(true);

	}

	private void setupGUI() {		
		JPanel pnlLogout = new JPanel(new MigLayout("al center", "[]", "[]"));
		JButton btnLogout = new JButton("Odjavi se");
		btnLogout.addActionListener( actionListener -> {
			this.setVisible(false);
			this.dispose();

			new MainFrame(controler);
		});

		pnlLogout.add(btnLogout);
		
		JPanel pnlZakazaniTretmani = new ZakazaniTretmaniRecepcionerPnl(controler, this, idUlogovan);

		add(pnlZakazaniTretmani, BorderLayout.CENTER);
		add(pnlLogout, BorderLayout.SOUTH);
	}
}
