package view.menadzerTabs.dijagrami;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.XChartPanel;

import entity.Kozmeticar;
import manage.Controler;
import net.miginfocom.swing.MigLayout;

public class Dijagram2Dialog extends JDialog{

	private static final long serialVersionUID = 8516899564484509633L;
	private Controler controler;
	
	public Dijagram2Dialog(Controler controler, JFrame frame) {
		super(frame);

		this.controler = controler;
		setTitle("Angažovanje kozmetičara");			
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/icon.png").getImage());
		setResizable(false);
		setModal(true);
		cuGui(frame);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void cuGui(JFrame frame) {
		MigLayout layout = new MigLayout("al center, wrap", "[]", "[][]");
		setLayout(layout);
		
		PieChart chart = new PieChartBuilder().width(800).height(600).title("").build();
	    
	    
		for (Map.Entry<Kozmeticar, Double> entry : controler.dijagramUdeoKozmeticara().entrySet()) {
            chart.addSeries(entry.getKey().getKorisnickoIme(), entry.getValue());
        }
	    

		JButton btnOk = new JButton("Zatvori");

		getRootPane().setDefaultButton(btnOk);
		
		add(new XChartPanel<>(chart), "");
		
		add(btnOk, "al center");

		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
	}
	
}
