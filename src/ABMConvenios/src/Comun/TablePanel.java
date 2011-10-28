package Comun;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class TablePanel extends JPanel{

	//tabla
	private JTable table = null;  // Tabla

	//modelo de tabla
	private MiModelo tableModel = null;

	//JScrollPane
	private JScrollPane scrollPane = null;

	public void setTable(JTable table) {
		this.table = table;
	}

	//lista
	private ArrayList <String []> data;	//Estructura para guardar las


	private int columna= 0;

	/**
	 * Constructor de la clase
	 * @param c numero de fils de la tabla
	 */
	public TablePanel(int c){
		data = new ArrayList <String []> ();
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS)); // Seteamos el BoxLayout
		this.scrollPane = new JScrollPane();
		this.tableModel = new MiModelo();
		this.table = new JTable(this.tableModel);
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.setPreferredScrollableViewportSize(new Dimension (650,400));
		this.scrollPane.getViewport().add(this.table, null);
		this.add(this.scrollPane, null);	
		columna = c;
	}

	public TablePanel(int c,HashSet columnaEditable){
		data = new ArrayList <String []> ();
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS)); // Seteamos el BoxLayout
		this.scrollPane = new JScrollPane();
		this.tableModel = new MiModelo(columnaEditable);
		this.table = new JTable(this.tableModel);
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.setPreferredScrollableViewportSize(new Dimension (650,400));
		this.scrollPane.getViewport().add(this.table, null);
		this.add(this.scrollPane, null);	
		columna = c;
	}

	public void addListener(TableModelListener t){
		table.getModel().addTableModelListener( t);

	}
	public void addTableColumnModelListener(TableColumnModelListener  t){
		table.getColumnModel().addColumnModelListener(t);

	}

	public void addListSelectionListener(ListSelectionListener  t){
		table.getSelectionModel().addListSelectionListener(t);

	}


	/** 
	 * Establecemos limites a la tabla.
	 * @param x limite 1
	 * @param y limite 2
	 * @param w limite 3
	 * @param h limite 4
	 */
	public void setBoundsTable(int x, int y, int w, int h){
		this.setBounds(x, y, w, h);
		this.scrollPane.setBounds( x, y, w-10, h-10);
		this.table.setBounds(x, y, w-20, h-20);
	}

	/**
	 * Carga la tabla con la informacion y da nombre a las columnas
	 * @param data1 matriz que alberga los datos de cada celda de la tabla
	 * @param columnName arreglo que alberga los nombres de cada una de las 
	 * columnas de la tabla
	 */
	public void  setearDatos(Object[][] data1, String[] columnName){
		String[] row = new String[columna]; 
		for (int i=0;i<data.size();i++) {
			row = data.get(i);
			for (int j=0;j<row.length;j++) {
				data1[i][j] = row[j];
			}
		}
		this.tableModel.setDataVector(data1, columnName);
	}


	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(MiModelo tableModel) {
		this.tableModel = tableModel;
	}

	public void setearTamañoColumna(String columna, int tamaño){
		TableColumn col = table.getColumn(columna); //Obtienes la columna
		col.setMinWidth(tamaño);
		col.setMaxWidth(tamaño);
		col.setPreferredWidth(tamaño); //Y le das un tama�o
	}


	/**
	 * Proporciona informacion acerca del numero de fila que se 
	 * encuentra seleccionada en la tabla 
	 * @return entero representando la fila seleccionada en la tabla
	 */
	public int obtenerFilaSeleccionada(){
		return this.table.getSelectedRow();		


	}

	/**
	 * Permite recuperar la tabla con su contenido
	 * @return la tabla con su contenido
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * Proporciona la información que almacena la tabla en una matriz
	 * @return todas las celdas de la tabla en una matriz
	 */
	public String[][] obtenerDatos(){
		String[][] data1 = new String[data.size()][columna];
		String[] row = new String[table.getColumnCount()]; 
		for (int i=0;i<data.size();i++) {
			row = data.get(i);
			for (int j=0;j<row.length;j++) {
				data1[i][j] = row[j];
			}
		}
		return data1;
	}

	public String obtenerCelda(int i, int j){
		String [][] a = this.obtenerDatos();
		return a[i][j]; 
	}

	/**
	 * Proporciona la información que almacena la tabla en una matriz
	 * @return todas las celdas de la tabla en una matriz
	 */
	public Object[][] obtenerObject(){
		Object[][] data1 = new Object[data.size()][columna];
		String[] row = new String[3]; 
		for (int i=0;i<data.size();i++) {
			row = data.get(i);
			for (int j=0;j<row.length;j++) {
				data1[i][j] = row[j];
			}
		}
		return data1;
	}

	/**
	 * Dado un entero, proporciona la fila correspondiente
	 * @param row numero de fila a recuperar
	 * @return una fila
	 */
	public String [] obtenerFila(int row){
		int columnCoun = this.tableModel.getColumnCount();
		String[] auxrow = new String[columnCoun];
		for (int i = 0; i < columnCoun; i++) {
			auxrow[i] = (String)this.tableModel.getValueAt(row, i);
		}	     	

		return auxrow;
	}
	//TODO COMENTAR
	public Object[] [] obtenerDatosReales(){
		int columnas = this.tableModel.getColumnCount();
		int filas = this.tableModel.getRowCount();
		Object [] [] datos = new Object[filas] [columnas];
		for (int i = 0; i < filas; i++) {
			for (int j = 0;j<columnas;j++){
				datos [i][j] = this.table.getValueAt(i,j);
			}
		}	     	
		return datos;
	}


	public void claveComienzaCon (int numeroColumna,String comienzo) {
		ArrayList<String []> filasFiltradas = new ArrayList <String []>();
		String[] nombresColumnas = new String[this.table.getColumnCount()];
		String[] row = null;
		if (this.table.getRowCount()!=0){
			for (int i=0;i<data.size();i++) {
				row = data.get(i);

				for (int j=0;j<row.length;j++) {
					if (j==numeroColumna && row[numeroColumna].toUpperCase().startsWith(comienzo.toUpperCase())) {
						filasFiltradas.add(row);
					}
				}
			}
			for (int u=0;u<row.length;u++) {
				nombresColumnas[u] = table.getColumnName(u);
			}
			String[][] filasNuevas = new String[data.size()][row.length];
			for (int k=0;k<filasFiltradas.size();k++) {
				for (int h=0;h<row.length;h++) {
					filasNuevas[k][h] = (String) filasFiltradas.get(k)[h];
					//System.out.println(filasNuevas[k][h]);
				}
			}
			ArrayList<String[]> auxData = data;
			data = filasFiltradas;
			this.setearDatos(filasNuevas, nombresColumnas);
			data=auxData;
		}

	}


	public void sincronizar(String[] columnName){
		this.setearDatos(this.obtenerDatosReales(),columnName );
	}


	public void cambiarTamaño (int ancho, int alto) {
		table.setPreferredScrollableViewportSize(new Dimension(ancho, alto));
	}


	public void setearNombresColumnas (String [] nombres) {
		this.tableModel.setColumnIdentifiers(nombres);
	}


	/**
	 * Proporciona la cantidad de filas de la tabla
	 * @return un entero con la cantidad de filas de la tabla
	 */
	public int cantidadDeFilas () {
		return this.tableModel.getRowCount();
	}

	public int cantidadDeColumnas() {
		return this.tableModel.getColumnCount();
	}

	/**
	 * Agrega una fila al final de la tabla
	 * @param dataRow arreglo representante de la fila a agregar
	 */
	public void agregarFila(Object[] dataRow){
		this.tableModel.addRow(dataRow);
		data.add((String[]) dataRow);
	}

	/**
	 * Elimina una fila de la tabla dada su posicion
	 * @param pos representa la posición de la fila a eliminar
	 */
	public void borrarFila (int pos) {
		this.tableModel.removeRow(pos);
		data.remove(pos);
	}

	/**
	 * Busca una fila en la tabla dado su campo clave
	 * @param key representa la clave por cual buscar la fila en la tabla
	 * @return entero representando la posición de la fila hallada,
	 * si no se hallo retorna -1
	 */
	public int buscarFila (String key) {
		int res = -1;
		int i = 0;
		boolean found = false;
		if (this.tableModel.getRowCount()>0) {
			String currentValue=null;
			while (i<this.tableModel.getRowCount() && !found) {
				currentValue = (String) this.tableModel.getValueAt(i,0);
				currentValue = currentValue.trim();
				if (currentValue.equals(key)) {
					found = true;
				}
				i++;
			}
			if (found) {
				res = i-1;

			}else{
				res = -1;
			}
		}
		return res;
	}

	/**
	 * Busca una fila en la tabla dado su campo clave
	 * @param key representa la clave por cual buscar la fila en la tabla
	 * @return entero representando la posición de la fila hallada,
	 * si no se hallo retorna -1
	 */
	public int buscarFila (String key,int columna) {
		int res = -1;
		int i = 0;
		boolean found = false;
		if (this.tableModel.getRowCount()>0) {
			String currentValue=null;
			while (i<this.tableModel.getRowCount() && !found) {
				currentValue = (String) this.tableModel.getValueAt(i,columna);
				currentValue = currentValue.trim();
				if (currentValue.equals(key)) {
					found = true;
				}
				i++;
			}
			if (found) {
				res = i-1;

			}else{
				res = -1;
			}
		}
		return res;
	}

	/**
	 * Modifica una fila de la tabla dada su posición y los nuevos valores que llevarán los campos de
	 * la fila 
	 * @param pos posición de la fila a modificar
	 * @param a valor del primer campo de la fila
	 * @param b valor del segundo campo de la fila
	 * @param c valor del tercer campo de la fila
	 */
	public void setearFila(int pos, String []a) {
		for(int i = 0; i< a.length;i++){
			this.tableModel.setValueAt(a[i], pos, i);
		}
		data.set(pos,a);

	}

	/**
	 * Selecciona una fila en la tabla
	 * @param row numero de fila a seleccionar
	 */
	public void seleccionarFila(int row){
		this.table.changeSelection(row, 0, false, false);
	}

	/**
	 * Informa si la fila identificada con el id pasado como parametro esta en la tabla
	 * @param id clave de busqueda de una fila
	 * @return true si la fila esta en la tabla, si no false
	 */
	public boolean contieneFila(String id){
		return (-1 == buscarFila(id));
	}

	/**
	 * Borra todas las filas de la tabla
	 */
	public void borrarTodo(){
		data = new ArrayList<String []>();

		while (tableModel.getRowCount()>0) {
			tableModel.removeRow(0);
		}
	}
	public LinkedList aListaDeDescripcion(){
		LinkedList a = new LinkedList();
		String[] row = new String[columna]; 
		for (int i=0;i<data.size();i++) {
			row = data.get(i);
			a.add(row[0]);
		}
		return a;

	}

	public LinkedList aListaDeCodigos(){
		LinkedList a = new LinkedList();
		String[] row = new String[columna]; 
		for (int i=0;i<data.size();i++) {
			row = data.get(i);
			a.add(row[0]);
		}
		return a;
	}

	public int getCantidadDeColumnas(){
		return columna;
	}

}
