package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import manage.Controler;
import net.miginfocom.swing.MigLayout;
import view.menadzerTabs.KlijentiPnl;
import view.menadzerTabs.KozmeticariPnl;
import view.menadzerTabs.KozmetickiSalonPnl;
import view.menadzerTabs.MenadzeriPnl;
import view.menadzerTabs.RecepcioneriPnl;
import view.menadzerTabs.TipoviTretmanaPnl;
import view.menadzerTabs.UslugePnl;

public class MenadzerView extends JFrame {
	private static final long serialVersionUID = -585150090946624553L;
	
	private final Controler controler;
	
	public MenadzerView(Controler controler) {
		this.controler = controler;

		frameSetup();
	}
	
	private void frameSetup() {
		this.setTitle("Kozmetički salon");
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
		JTabbedPane tp = new JTabbedPane();
		
		JPanel pnlLogout = new JPanel(new MigLayout("al center", "[]", "[]"));
		JButton btnLogout = new JButton("Odjavi se");
		btnLogout.addActionListener( actionListener -> {
			this.setVisible(false);
			this.dispose();
			
			new MainFrame(controler);
		});
		
		pnlLogout.add(btnLogout);
		
		
		JPanel pnlKSSettings = new KozmetickiSalonPnl(controler, this);
		JPanel pnlMenadzeri = new MenadzeriPnl(controler, this);
		JPanel pnlKozmeticari = new KozmeticariPnl(controler, this);
		JPanel pnlRecepcioneri = new RecepcioneriPnl(controler, this);
		JPanel pnlKlijenti = new KlijentiPnl(controler, this);
		JPanel pnlTipoviTretmana = new TipoviTretmanaPnl(controler, this);
		JPanel pnlUsluge = new UslugePnl(controler, this);

		
		tp.add("Kozmetički salon", pnlKSSettings);
		tp.add("Menadžeri", pnlMenadzeri);
		tp.add("Kozmetičari", pnlKozmeticari);
		tp.add("Recepcioneri", pnlRecepcioneri);
		tp.add("Klijenti", pnlKlijenti);
		tp.add("Tipovi tretmana", pnlTipoviTretmana);
		tp.add("Usluge", pnlUsluge);
		
		
		add(tp, BorderLayout.CENTER);
		add(pnlLogout, BorderLayout.SOUTH);
	}
	
	
}
