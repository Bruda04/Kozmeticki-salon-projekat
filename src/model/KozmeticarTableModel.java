package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Kozmeticar;
import entity.TipTretmana;
import manage.Controler;

public class KozmeticarTableModel extends AbstractTableModel{


	private static final long serialVersionUID = 5700995641324967796L;
	private List<Kozmeticar> data;
	private HashMap<Integer, TipTretmana> tipoviTretmana;
	private Controler controler;
	private String[] columnNames = { "Id", "Korisničko ime", "Ime", "Prezime","Pol", "Telefon", "Adresa", "Lozinka", "Stručna sprema", "Godine staža", "Bonus", "Plata", "Obucen za", "Obrisan"};

	public KozmeticarTableModel(Controler controler) {
		this.controler = controler;
		this.data = new ArrayList<Kozmeticar>(controler.sviKozmeticari().values());
		this.tipoviTretmana = controler.sviTipoviTretmana();
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Kozmeticar kozmeticar = data.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return kozmeticar.getId();
		case 1:
			return kozmeticar.getKorisnickoIme();
		case 2:
			return kozmeticar.getIme();
		case 3:
			return kozmeticar.getPrezime();
		case 4:
			return kozmeticar.getPol();
		case 5:
			return kozmeticar.getTelefon();
		case 6:
			return kozmeticar.getAdresa();
		case 7:
			return kozmeticar.getLozinka();
		case 8:
			return kozmeticar.getNivoStrucneSpreme();
		case 9:
			return kozmeticar.getGodineStaza();
		case 10:
			return kozmeticar.getBonus();
		case 11:
			return kozmeticar.getPlata();
		case 12:
			ArrayList<String> retTipTretmana = new ArrayList<>();
			for (int idTipaTretmana : kozmeticar.getSpisakTretmana()) {
				retTipTretmana.add(tipoviTretmana.get(idTipaTretmana).getNaziv());
			}
			return retTipTretmana;
		case 13:
			return kozmeticar.isObrisan();
		default:
			return null;
		}
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Class<?> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public void refresh() {
		this.data = new ArrayList<Kozmeticar>(controler.sviKozmeticari().values());
		this.tipoviTretmana = controler.sviTipoviTretmana();
		this.fireTableDataChanged();
	}
}

