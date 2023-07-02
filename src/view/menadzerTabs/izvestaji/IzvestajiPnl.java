package view.menadzerTabs.izvestaji;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import manage.Controler;
import net.miginfocom.swing.MigLayout;

public class IzvestajiPnl extends JPanel{

	private static final long serialVersionUID = 6800719089996475905L;

	public IzvestajiPnl(Controler controler, JFrame frame) {
		MigLayout mig = new MigLayout("wrap 2, al center", "[][][][][]", "[][]");
		setLayout(mig);

		JButton izvestaj1 = new JButton("Generiši");
		JButton izvestaj2 = new JButton("Generiši");
		JButton izvestaj3 = new JButton("Generiši");
		JButton izvestaj4 = new JButton("Generiši");
		JButton izvestaj5 = new JButton("Generiši");
		

		add(new JLabel("Broj tretmana i prihod kozmetičara - "), "al right");
		add(izvestaj1);

		add(new JLabel("Broj zakazivanja i otkazivanja tretmana - "), "al right");
		add(izvestaj2);

		add(new JLabel("Statistika usluge - "), "al right");
		add(izvestaj3);

		add(new JLabel("Klijenti sa uslovom za karticu lojalnosti - "), "al right");
		add(izvestaj4);
		
		add(new JLabel("Prihodi i rashodi - "), "al right");
		add(izvestaj5);


		izvestaj1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Izvestaj1Dialog(controler, frame);
			}
		});

		izvestaj2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Izvestaj2Dialog(controler, frame);
			}
		});

		izvestaj3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Izvestaj3Dialog(controler, frame);
			}
		});

		izvestaj4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Izvestaj4Dialog(controler, frame);
			}
		});

		izvestaj5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Izvestaj5Dialog(controler, frame);
			}
		});

	}


}
