package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.TipTretmana;
import entity.Usluga;
import manage.Controler;

public class UslugaTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = -1710007579518923937L;

	private List<Usluga> data;
	private Controler controler;
    private String[] columnNames = { "Id", "Naziv", "Trajanje(minuta)", "Tip Tretmana", "Cena"};

	public UslugaTableModel(Controler controler) {
		this.controler = controler;
		this.data = new ArrayList<Usluga>(controler.sveUsluge().values());
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
		Usluga usluga = data.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return usluga.getId();
		case 1:
			return usluga.getNazivUsluge();
		case 2:
			return usluga.getTrajanjeUsluge();
		case 3:
			return ((TipTretmana) controler.nadjiTipTretmanaUsluge(usluga.getId())).getNaziv();
		case 4:
			return controler.nadjiCenuUsluge(usluga.getId());
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
		this.data = new ArrayList<Usluga>(controler.sveUsluge().values());
		this.fireTableDataChanged();
	}
}
