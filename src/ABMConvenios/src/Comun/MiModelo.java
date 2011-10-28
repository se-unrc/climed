package Comun;

import java.util.HashSet;
import java.util.Set;

import javax.swing.table.DefaultTableModel;


public class MiModelo extends DefaultTableModel{

	public int s=-1;
	public Set columnas;

	public MiModelo(){
		super();
	}
	public MiModelo(Set c){
		super();
		columnas= new HashSet();
		columnas = c;
	}

	public boolean isCellEditable (int row, int column)
	{
		if (!(columnas==null)){
			if (columnas.contains(column)){
				return true;
			}else{
				return false;
			}
		}			
		return false;
	}


}