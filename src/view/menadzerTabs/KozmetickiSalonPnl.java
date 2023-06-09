package view.menadzerTabs;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import entity.KozmetickiSalon;
import manage.Controler;
import net.miginfocom.swing.MigLayout;

public class KozmetickiSalonPnl extends JPanel{
	private static final long serialVersionUID = -229234417170898485L;
	
	public KozmetickiSalonPnl(Controler controler, JFrame frame) {		
		MigLayout mlks = new MigLayout("wrap", "[][]", "[][][][][][][][]");
		setLayout(mlks);
		
		JButton btnIzmena = new JButton("Izmeni");
		KozmetickiSalon ks = controler.pronadjiKozmetickiSalon();
				
		add(new JLabel("PODACI KOZMETICKOG SALONA"), "al center, span 2");
		
		JLabel lblNaziv = new JLabel(ks.getNaziv());
		JLabel lblVremeOtvarnja = new JLabel(ks.getVremeOtvaranjaFormatStr());
		JLabel lblVremeZatvaranja = new JLabel(ks.getVremeZatvaranjaFormatStr());
		JLabel lblPragBonus = new JLabel(String.format("%.2f", ks.getPragBonus()));
		JLabel lblIznosBonus = new JLabel(String.format("%.2f", ks.getBonusIznos()));
		JLabel lblStanjeRacuna = new JLabel(String.format("%.2f", ks.getStanje()));
		
		add(new JLabel("Naziv: "), "growx, pushx");
		add(lblNaziv, "growx, pushx");
		add(new JLabel("Vreme otvaranja: "), "growx, pushx");
		add(lblVremeOtvarnja, "growx, pushx");
		add(new JLabel("Vreme zatvaranja: "), "growx, pushx");
		add(lblVremeZatvaranja, "growx, pushx");
		add(new JLabel("Prag za bonus: "), "growx, pushx");
		add(lblPragBonus, "growx, pushx");
		add(new JLabel("Iznos bonusa: "), "growx, pushx");
		add(lblIznosBonus, "growx, pushx");
		add(new JLabel("Stanje računa: "), "growx, pushx");
		add(lblStanjeRacuna, "growx, pushx");
		
		add(btnIzmena, "span 2, al center");

		btnIzmena.addActionListener(actionListener -> {
			new IzmenaKozmeticiSalonDialog(controler, frame, ks);
			
			lblNaziv.setText(ks.getNaziv());
			lblVremeOtvarnja.setText(ks.getVremeOtvaranjaFormatStr());
			lblVremeZatvaranja.setText(ks.getVremeZatvaranjaFormatStr());
			lblPragBonus.setText(String.format("%.2f", ks.getPragBonus()));
			lblIznosBonus.setText(String.format("%.2f", ks.getBonusIznos()));
			lblStanjeRacuna.setText(String.format("%.2f", ks.getStanje()));
		});
		
		
		
	}
}
