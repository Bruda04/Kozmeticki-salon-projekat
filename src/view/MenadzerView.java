package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import manage.Controler;
import net.miginfocom.swing.MigLayout;
import view.menadzerTabs.KozmetickiSalonPnl;

public class MenadzerView extends JFrame {
	private static final long serialVersionUID = -585150090946624553L;
	
	private final Controler controler;
	
	public MenadzerView(Controler controler) {
		this.controler = controler;

		frameSetup();
	}
	
	private void frameSetup() {
		this.setTitle("Kozmeticki salon");
		this.setSize(800, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setIconImage(new ImageIcon("img/icon.png").getImage());
		setupGUI();
		this.setVisible(true);

	}
	
	private void setupGUI() {		
		JTabbedPane tp = new JTabbedPane();
		
		JPanel pnlLogout = new JPanel(new MigLayout("al center", "[]", "[]"));
		JButton btnLogout = new JButton("Odjavi se");
		btnLogout.addActionListener( actionListener -> {
			this.setVisible(false);
			this.dispose();
			
			new MainFrame(controler);
		});
		
		pnlLogout.add(btnLogout);
		
		
		JPanel pnlKSSettings = new KozmetickiSalonPnl(controler,this);
		JPanel pnlMenadzeri = new JPanel();
		JPanel pnlKozmeticari = new JPanel();
		JPanel pnlRecepcioneri = new JPanel();
		JPanel pnlKlijenti = new JPanel();
				
		
		tp.add("Kozmeticki salon", pnlKSSettings);
		tp.add("Menadzeri", pnlMenadzeri);
		tp.add("Kozmeticari", pnlKozmeticari);
		tp.add("Recepcioneri", pnlRecepcioneri);
		tp.add("Klijenti", pnlKlijenti);

		
		
		add(tp, BorderLayout.CENTER);
		add(pnlLogout, BorderLayout.SOUTH);
	}
	
	
}
