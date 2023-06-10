package view.menadzerTabs;

import java.awt.Image;

import javax.swing.ImageIcon;
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
		MigLayout mlks = new MigLayout("al center, wrap 2", "[]15[]", "[]20[]20[]20[]20[]20[]20[]20[]");
		setLayout(mlks);
		
		JButton btnIzmena = new JButton("IZMENI");
		ImageIcon updIcon = new ImageIcon("img/edit.gif");		
		ImageIcon updScaled = new ImageIcon(updIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		updIcon = updScaled;
		btnIzmena.setIcon(updIcon);
		
		KozmetickiSalon ks = controler.pronadjiKozmetickiSalon();
				
		add(new JLabel("PODACI KOZMETICKOG SALONA"), "al center, span 2");
		
		JLabel lblNaziv = new JLabel(ks.getNaziv());
		JLabel lblVremeOtvarnja = new JLabel(ks.getVremeOtvaranjaFormatStr());
		JLabel lblVremeZatvaranja = new JLabel(ks.getVremeZatvaranjaFormatStr());
		JLabel lblPragBonus = new JLabel(String.format("%.2f", ks.getPragBonus()));
		JLabel lblIznosBonus = new JLabel(String.format("%.2f", ks.getBonusIznos()));
		JLabel lblStanjeRacuna = new JLabel(String.format("%.2f", ks.getStanje()));
		
		add(new JLabel("Naziv: "), "al right");
		add(lblNaziv, " al left");
		add(new JLabel("Vreme otvaranja: "), "al right");
		add(lblVremeOtvarnja, "");
		add(new JLabel("Vreme zatvaranja: "), "al right");
		add(lblVremeZatvaranja, "");
		add(new JLabel("Prag za bonus: "), "al right");
		add(lblPragBonus, "");
		add(new JLabel("Iznos bonusa: "), "al right");
		add(lblIznosBonus, "");
		add(new JLabel("Stanje računa: "), "al right");
		add(lblStanjeRacuna, "");
		
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
