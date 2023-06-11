package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Recepcioner;
import manage.Controler;

public class RecepcionerTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 5700995641324967796L;
	private List<Recepcioner> data;
	private Controler controler;
    private String[] columnNames = { "Id", "Korisničko ime", "Ime", "Prezime","Pol", "Telefon", "Adresa", "Lozinka", "Stručna sprema", "Godine staža", "Bonus", "Plata"};

	public RecepcionerTableModel(Controler controler) {
		this.controler = controler;
		this.data = new ArrayList<Recepcioner>(controler.sviRecepcioneri().values());
	}
	
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Recepcioner recepcioner = data.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return recepcioner.getId();
		case 1:
			return recepcioner.getKorisnickoIme();
		case 2:
			return recepcioner.getIme();
		case 3:
			return recepcioner.getPrezime();
		case 4:
			return recepcioner.getPol();
		case 5:
			return recepcioner.getTelefon();
		case 6:
			return recepcioner.getAdresa();
		case 7:
			return recepcioner.getLozinka();
		case 8:
			return recepcioner.getNivoStrucneSpreme();
		case 9:
			return recepcioner.getGodineStaza();
		case 10:
			return recepcioner.getBonus();
		case 11:
			return recepcioner.getPlata();
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
		this.data = new ArrayList<Recepcioner>(controler.sviRecepcioneri().values());
		this.fireTableDataChanged();
	}
}
