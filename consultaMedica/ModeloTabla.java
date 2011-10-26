package consultaMedica;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;


public class ModeloTabla implements TableModel {

	private String[] titulos;
	private Object[][] datos;

	public ModeloTabla(String[] tit, Object data[][]) {
		titulos = tit;
		datos = data;
	}

	public int getRowCount() {
		return datos.length;
	}

	public int getColumnCount() {
		return titulos.length;
	}

	public String[] getColumnModel() {
		return titulos;
	}

	public Object[] getDatos() {
		return datos;
	}

	public String getColumnName(int columnIndex) {
		return titulos[columnIndex];
	}

	public Class getColumnClass(int c) {
		return (new String()).getClass();
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		return datos[rowIndex] [columnIndex];
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		datos[rowIndex][columnIndex] = aValue;
	}

	public void addTableModelListener(TableModelListener l) {
	}

	public void removeTableModelListener(TableModelListener l) {
	}

}
