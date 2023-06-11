package model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.TipTretmana;
import manage.Controler;

public class TipTretmanaTableModel extends AbstractTableModel{
	
	private static final long serialVersionUID = -5136647995072966557L;
	private List<TipTretmana> data;
	private Controler controler;
    private String[] columnNames = { "Id", "Naziv", "Usluge"};

	public TipTretmanaTableModel(Controler controler) {
		this.controler = controler;
		this.data = new ArrayList<TipTretmana>(controler.sviTipoviTretmana().values());
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
		TipTretmana tipTretmana = data.get(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return tipTretmana.getId();
		case 1:
			return tipTretmana.getNaziv();
		case 2:
			StringBuilder sb = new StringBuilder();
	        for(int idUsluge : tipTretmana.getSkupTipovaUsluga()) {
	            sb.append(", ").append(controler.pronadjiUslugu(idUsluge).getNazivUsluge());
	        }
	        String retStr = sb.toString();
			return retStr.subSequence(2, retStr.length());
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
		this.data = new ArrayList<TipTretmana>(controler.sviTipoviTretmana().values());
		this.fireTableDataChanged();
	}
}
