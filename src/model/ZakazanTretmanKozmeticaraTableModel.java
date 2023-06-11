package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.ZakazanTretman;
import manage.Controler;

public class ZakazanTretmanKozmeticaraTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 6822441585456504715L;
	
	private List<ZakazanTretman> data;
	private Controler controler;
	private int idUlogovanog;
    private String[] columnNames = { "Id", "Klijent", "Usluga", "Datum", "Vreme", "Cena", "Zakazao"};

	public ZakazanTretmanKozmeticaraTableModel(Controler controler, int idUlogovanog) {
		this.controler = controler;
		this.idUlogovanog = idUlogovanog;
		this.data = controler.zakazaniTretmaniKozmeticara(idUlogovanog);
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
		ZakazanTretman zakazanTretman = data.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return zakazanTretman.getId();
		case 1:
			return controler.pronadjiKlijenta(zakazanTretman.getIdKlijenta()).getKorisnickoIme();
		case 2:
			return controler.pronadjiUslugu(zakazanTretman.getIdTipaUsluge()).getNazivUsluge();
		case 3:
			return zakazanTretman.getDatum();
		case 4:
			return zakazanTretman.getVremeFormatStr();
		case 5:
			return zakazanTretman.getCena();
		case 6:
			if (zakazanTretman.getIdZakazivaca() == 0) {
				return "Online";
			} else {
				return controler.pronadjiRecepcionera(zakazanTretman.getIdZakazivaca()).getKorisnickoIme();
			}
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
		this.data = controler.zakazaniTretmaniKozmeticara(idUlogovanog);
		this.fireTableDataChanged();
	}
}
