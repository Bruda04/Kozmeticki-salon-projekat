package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.ZakazanTretman;
import manage.Controler;

public class ZakazanTretmanKlijentaTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = -5708001691966272324L;
	
	private List<ZakazanTretman> data;
	private Controler controler;
	private int idUlogovanog;
    private String[] columnNames = {"Kozmetičar", "Usluga", "Datum", "Vreme", "Cena", "Zakazao", "Stanje"};

	public ZakazanTretmanKlijentaTableModel(Controler controler, int idUlogovanog) {
		this.controler = controler;
		this.idUlogovanog = idUlogovanog;
		this.data = controler.tretmaniKlijenta(idUlogovanog);
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
			return controler.pronadjiKozmeticara(zakazanTretman.getIdKozmeticara()).getKorisnickoIme();
		case 1:
			return controler.pronadjiUslugu(zakazanTretman.getIdTipaUsluge()).getNazivUsluge();
		case 2:
			return zakazanTretman.getDatum();
		case 3:
			return zakazanTretman.getVremeFormatStr();
		case 4:
			return zakazanTretman.getCena();
		case 5:
			if (zakazanTretman.getIdZakazivaca() == 0) {
				return "Online";
			} else {
				return controler.pronadjiRecepcionera(zakazanTretman.getIdZakazivaca()).getKorisnickoIme();
			}
		case 6:
			return zakazanTretman.getStanje();
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
		this.data = controler.tretmaniKlijenta(idUlogovanog);
		this.fireTableDataChanged();
	}
}
