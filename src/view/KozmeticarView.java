package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import manage.Controler;
import net.miginfocom.swing.MigLayout;
import view.kozmeticarTabs.ZakazaniTretmaniKozmeticaraPnl;

public class KozmeticarView extends JFrame {
	private static final long serialVersionUID = -7799305953296278565L;
	private Controler controler;
	private int idUlogovanog;
	
	public KozmeticarView(Controler controler, int idUlogovanog) {
		this.controler = controler;
		this.idUlogovanog = idUlogovanog;
		mainFrame();
	}
	
	private void mainFrame() {
		this.setTitle("Kozmetički salon - KOZMETIČAR");
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
		
		
		JPanel pnlZakazaniTretmaniKozmeticar = new ZakazaniTretmaniKozmeticaraPnl(controler, this, idUlogovanog);

		add(pnlZakazaniTretmaniKozmeticar, BorderLayout.CENTER);
		
		add(pnlLogout, BorderLayout.SOUTH);
	}
}
