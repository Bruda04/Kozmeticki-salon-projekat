package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import manage.Controler;

public class KozmeticarView extends JFrame {
	private Controler controler;
	
	public KozmeticarView(Controler controler) {
		this.controler = controler;

		mainFrame();
	}
	
	private void mainFrame() {
		this.setTitle("Kozmeticki salon - " + controler.pronadjiKozmetickiSalon().getNaziv());
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setIconImage(new ImageIcon("img/icon.png").getImage());

		initMainGUI();
		this.setVisible(true);

	}
	
	private void initMainGUI() {		
		add(new JLabel("  Ovde na centralnom mestu glavnog prozora staviti ono sto je najbitnije za KOZMETICARA!"), BorderLayout.CENTER);
	}
}
