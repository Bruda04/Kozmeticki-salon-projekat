package view.menadzerTabs.dijagrami;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.markers.SeriesMarkers;

import entity.TipTretmana;
import manage.Controler;
import net.miginfocom.swing.MigLayout;

public class Dijagram1Dialog extends JDialog{

	private static final long serialVersionUID = 8516899564484509633L;
	private Controler controler;

	public Dijagram1Dialog(Controler controler, JFrame frame) {
		super(frame);

		this.controler = controler;
		setTitle("Angažovanje kozmetičara");			
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(new ImageIcon("img/icon.png").getImage());
		setResizable(true);
		setModal(true);
		cuGui(frame);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void cuGui(JFrame frame) {
		MigLayout layout = new MigLayout("al center, wrap", "[]", "[][]");
		setLayout(layout);
		

		XYChart chart = new XYChartBuilder().width(1500).height(800).title("Godišnji prihodi izveštaj").xAxisTitle("Mesec").yAxisTitle("Prihodi").build();	    
		
		for (TipTretmana tt: controler.sviTipoviTretmana().values()) {
			chart.addSeries(tt.getNaziv(), new ArrayList<Date>(controler.dijagramPrihodiGodina(tt.getId()).keySet()),new ArrayList<Double>(controler.dijagramPrihodiGodina(tt.getId()).values())).setMarker(SeriesMarkers.NONE);
		}
		chart.addSeries("Ukupno", new ArrayList<Date>(controler.dijagramPrihodiGodina(0).keySet()),new ArrayList<Double>(controler.dijagramPrihodiGodina(0).values())).setMarker(SeriesMarkers.NONE);

		
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
