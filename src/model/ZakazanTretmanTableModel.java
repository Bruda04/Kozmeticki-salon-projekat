package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.ZakazanTretman;
import manage.Controler;

public class ZakazanTretmanTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = 6822441585456504715L;
	
	private List<ZakazanTretman> data;
	private Controler controler;
    private String[] columnNames = {"Id", "Klijent", "Kozmetičar", "Usluga", "Datum", "Vreme", "Cena", "Zakazao", "Stanje"};

	public ZakazanTretmanTableModel(Controler controler) {
		this.controler = controler;
		this.data = new ArrayList<ZakazanTretman>(controler.sviZakazaniTretmani().values());
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
		if (data.size() == 0) {
			return null;
		}
		ZakazanTretman zakazanTretman = data.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return zakazanTretman.getId();
		case 1:
			return controler.pronadjiKlijenta(zakazanTretman.getIdKlijenta()).getKorisnickoIme();
		case 2:
			return controler.pronadjiKozmeticara(zakazanTretman.getIdKozmeticara()).getKorisnickoIme();
		case 3:
			return controler.pronadjiUslugu(zakazanTretman.getIdTipaUsluge()).getNazivUsluge();
		case 4:
			return zakazanTretman.getDatum();
		case 5:
			return zakazanTretman.getVremeFormatStr();
		case 6:
			return zakazanTretman.getCena();
		case 7:
			if (zakazanTretman.getIdZakazivaca() == 0) {
				return "Online";
			} else {
				return controler.pronadjiRecepcionera(zakazanTretman.getIdZakazivaca()).getKorisnickoIme();
			}
		case 8: {
			return zakazanTretman.getStanje();
		}
		default:
			return null;
		}
	}
	
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Class<?> getColumnClass(int c) {
		if(this.getValueAt(0, c) == null) {
            return Object.class;
        }
        return this.getValueAt(0, c).getClass();
	}

	public boolean isCellEditable(int row, int col) {
		return false;
	}
	
	public void refresh() {
		this.data = new ArrayList<ZakazanTretman>(controler.sviZakazaniTretmani().values());
		this.fireTableDataChanged();
	}
}
