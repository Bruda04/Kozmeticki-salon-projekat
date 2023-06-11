package model; 

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import entity.TipTretmana; 
public class TipTretmanaJListRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 6990909753519430093L;

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) { 
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);  
		TipTretmana tt = (TipTretmana) value; 
		setText(tt.getNaziv());  
		return this;   
	} 
}