package model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Klijent;
import manage.Controler;

public class KlijentKarticaLojalnostiTableModel extends AbstractTableModel{
	private static final long serialVersionUID = -3294795822481902011L;
	
	private List<Klijent> data;
	private Controler controler;
    private String[] columnNames = { "Id", "Korisničko ime", "Ime", "Prezime","Pol", "Telefon", "Adresa", "Lozinka", "Kartica lojalnosti", "Potrošeno sredstava"};

	public KlijentKarticaLojalnostiTableModel(Controler controler, Double prag) {
		this.controler = controler;
		this.data = controler.izvestajPotencijalZaKarticuLojalnosti(prag);			

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
		
		Klijent klijent = data.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return klijent.getId();
		case 1:
			return klijent.getKorisnickoIme();
		case 2:
			return klijent.getIme();
		case 3:
			return klijent.getPrezime();
		case 4:
			return klijent.getPol();
		case 5:
			return klijent.getTelefon();
		case 6:
			return klijent.getAdresa();
		case 7:
			return klijent.getLozinka();
		case 8:
			return klijent.isKarticaLojalnosti();
		case 9:
			return klijent.getPotroseno();
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
	
	public void refresh(Double prag) {
		this.data = controler.izvestajPotencijalZaKarticuLojalnosti(prag);			
		this.fireTableDataChanged();
	}
}
