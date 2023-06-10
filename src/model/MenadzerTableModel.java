package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Menadzer;
import manage.Controler;

public class MenadzerTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 5700995641324967796L;
	private List<Menadzer> data;
	private Controler controler;
    private String[] columnNames = { "Id", "Korisničko ime", "Ime", "Prezime","Pol", "Telefon", "Adresa", "Lozinka", "Stručna sprema", "Godine staža", "Bonus", "Plata", "Obrisan"};

	public MenadzerTableModel(Controler controler) {
		this.controler = controler;
		this.data = new ArrayList<Menadzer>(controler.sviMenadzeri().values());
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
		Menadzer menadzer = data.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return menadzer.getId();
		case 1:
			return menadzer.getKorisnickoIme();
		case 2:
			return menadzer.getIme();
		case 3:
			return menadzer.getPrezime();
		case 4:
			return menadzer.getPol();
		case 5:
			return menadzer.getTelefon();
		case 6:
			return menadzer.getAdresa();
		case 7:
			return menadzer.getLozinka();
		case 8:
			return menadzer.getNivoStrucneSpreme();
		case 9:
			return menadzer.getGodineStaza();
		case 10:
			return menadzer.getBonus();
		case 11:
			return menadzer.getPlata();
		case 12:
			return menadzer.isObrisan();
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
		this.data = new ArrayList<Menadzer>(controler.sviMenadzeri().values());
		this.fireTableDataChanged();
	}
}
