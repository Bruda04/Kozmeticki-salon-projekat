package model; 

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import entity.Usluga; 
public class UslugeJListRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = -144673058754628096L;

	public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) { 
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);  
		Usluga u = (Usluga) value; 
		setText(u.getNazivUsluge());  
		return this;   
	} 
}