package view.menadzerTabs.dijagrami;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import manage.Controler;
import net.miginfocom.swing.MigLayout;

public class DijagramiPnl extends JPanel{


	private static final long serialVersionUID = 1645817542643202313L;

	public DijagramiPnl(Controler controler, JFrame frame) {
		MigLayout mig = new MigLayout("wrap 2, al center", "[][][][]", "[][]");
		setLayout(mig);

		JButton dijagram1 = new JButton("Generiši");
		JButton dijagram2 = new JButton("Generiši");
		JButton dijagram3 = new JButton("Generiši");

		add(new JLabel("Prihodi po tipu tretmana u proteklih godinu dana - "), "al right");
		add(dijagram1);

		add(new JLabel("Broj realizovanih tretmana u proteklih 30 dana po kozmetičarima - "), "al right");
		add(dijagram2);

		add(new JLabel("Odnos statusa tretmana u proteklih 30 dana - "), "al right");
		add(dijagram3);


		dijagram1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Dijagram1Dialog(controler, frame);
			}
		});

		dijagram2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new	Dijagram2Dialog(controler, frame);
			}
		});

		dijagram3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Dijagram3Dialog(controler, frame);
			}
		});


	}


}
