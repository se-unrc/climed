package Mediadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Comun.ControlConvenio;
import Comun.TablePanel;
import Dominio.Convenio;
import Interfaces.GuiABMConvenio;


public class MediadorABMConvenio implements ActionListener,KeyListener {

	//GUI
	private GuiABMConvenio guiABMConvenios = null;

	private Connection conexion;

	private Statement st;

	/**
	 * Constructor de la clase
	 * @param p mediador principal
	 * @throws Exception 
	 */
	public MediadorABMConvenio(JFrame guiPrincipal,Connection conexion) throws Exception{
		guiABMConvenios = new GuiABMConvenio(guiPrincipal);
		guiABMConvenios.setActionListeners(this);
		guiABMConvenios.setKeyListener(this);
		st = conexion.createStatement();
		ControlConvenio controlConvenio = new ControlConvenio(conexion,st);
		controlConvenio.cargarTabla(guiABMConvenios.getTabla());
		this.conexion = conexion;
		guiABMConvenios.hacerVisible();
	}

	public void CargarTabla(){
	}

	/** 
	 * Invoca acciones dependiendo del boton que ha sido accionado
	 * @param e representa un evento, que en este caso seria accionar un boton
	 */
	public void actionPerformed(ActionEvent e) { 

		Object source = e.getSource();

		if (source == guiABMConvenios.getbotonSalir()){
			this.presionBotonSalir();
		}
		if (source == guiABMConvenios.getBotonAlta()){
			this.seleccionAltaConvenio();
		}
		if (source == guiABMConvenios.getBotonBaja()){
			this.seleccionBajaConvenio();
		}
		if (source == guiABMConvenios.getBotonModificar()){
			this.seleccionModificarConvenio();
		}

	}

	private void presionBotonSalir(){
		guiABMConvenios.dispose();
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void keyReleased(KeyEvent arg0) {
		String prefijo = guiABMConvenios.getJTBuscar().getText();
		guiABMConvenios.getTabla().claveComienzaCon(2, prefijo);
		TablePanel a = guiABMConvenios.getTabla();
		a.setearTamañoColumna("Nro.", 80);
		a.setearTamañoColumna("% Cobertura", 85);
	}

	/** 
	 * Hace que la GUI sea visible en pantalla
	 */
	public void show() {
		guiABMConvenios.show();
	}

	/**
	 * Retorna la gui de la gestion de articulos
	 * @return la gui de la gestion de articulos
	 */
	public GuiABMConvenio getGuiABMConvenio() {
		return guiABMConvenios;
	}

	private void seleccionAltaConvenio() {
		try {
			MediadorAltaConvenio mAC = new MediadorAltaConvenio(this,conexion);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void seleccionBajaConvenio() {
		int i =  guiABMConvenios.obtenerFilaSeleccionada() ;
		if (i!=-1 && guiABMConvenios.obtenerDeFila(i)[0] != null) {
			try {
				st = conexion.createStatement();

				Object m = guiABMConvenios.obtenerDeFila(i)[1];
				Object oS = guiABMConvenios.obtenerDeFila(i)[2];
				Object n = guiABMConvenios.obtenerDeFila(i)[0];
				String medico = m.toString();
				String obraSocial = oS.toString();
				int nro = Integer.parseInt(n.toString());
				float cobertura = 0;
				Convenio convenio = new Convenio(nro,medico,obraSocial,cobertura);
				ControlConvenio controlConvenio = new ControlConvenio(conexion,st);
				controlConvenio.eliminar(convenio);
				guiABMConvenios.getTabla().borrarTodo();
				controlConvenio.cargarTabla(guiABMConvenios.getTabla());
				st.execute("END");
				st.close();
				//conexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			JOptionPane.showMessageDialog(guiABMConvenios,"Debe seleccionar un convenio de la tabla.\nPuede buscarlo por Obra Social utilizando la herramienta \"Buscar\"\nen la parte superior derecha de la ventana","Advertencia",
					JOptionPane.WARNING_MESSAGE);
		}
	}	

	private void seleccionModificarConvenio() {
		int i =   guiABMConvenios.obtenerFilaSeleccionada() ;
		if (i!=-1 && guiABMConvenios.obtenerDeFila(i)[0] != null){

			try {
				MediadorModificarConvenio mM = new MediadorModificarConvenio(this,conexion);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{
			JOptionPane.showMessageDialog(guiABMConvenios,"Debe seleccionar un convenio de la tabla.\nPuede buscarlo por Obra Social utilizando la herramienta \"Buscar\"\nen la parte superior derecha de la ventana","Advertencia",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Agrega una fila a la tabla
	 * @param row fila a agregar
	 */
	public void AgregarAGrilla(String [] row){
		guiABMConvenios.agregarFilaALaGrilla(row);
	}

	/**
	 * Borra una fila de la tabla
	 * @param i numero de fila a borrar
	 */
	public void borrarDeGrilla(int i){
		guiABMConvenios.borrarFilaDeGrilla(i);
	}

	/**
	 * Busca el indice de la fila cuyo identificadaro coincide con el dado como parametro
	 * @param s clave para buscar la fila
	 * @return un entero representando el numero de fila buscado
	 */
	public int buscarIndiceDe(String s){
		return guiABMConvenios.buscarFilaEnGrilla(s);
	}

	/**
	 * Selecciona una fila de la tabla
	 * @param i numero de fila a seleccionar
	 */
	public void seleccionarEnGrilla(int i){
		guiABMConvenios.seleccionarEnGrilla(i);
	}

	/**
	 * Modifica una fila en la tabla
	 * @param pos numero de fila a modificar
	 * @param id nuevo id del Cliente
	 * @param nombre nuevo nombre del Cliente
	 * @throws Exception
	 */
	public void SetearEnGrilla(int pos,String nro ,String obrasocial, String medico, String cobertura) throws Exception{
		guiABMConvenios.setearFila(pos,nro,obrasocial,medico,cobertura);
	}

	/**
	 * Retorna un arreglo representado la fila requerida
	 * @param i numero de fila requerida
	 * @return un arreglo representando ls fila requerida
	 */
	public Object [] obtenerDeGrilla(int i){
		return guiABMConvenios.obtenerDeFila(i);
	}

	/**
	 * Retorna el numero de fila que esta seleccionada en la tabla
	 * @return un entero representando el numero de fila seleccionada
	 */
	public int obtenerIndiceSeleccionado(){
		return guiABMConvenios.obtenerFilaSeleccionada();
	}

	/**
	 * Dice si se puede o no agregar una fila en la tabla, dependiendo de si esta existe o no
	 * @param id identificador por el que se busca la fila, si este existe no se puede agregar,
	 * si no existe si se puede
	 * @return true si se puede agregar, false en caso contrario
	 */
	public boolean puedoAgregar(String id){
		return guiABMConvenios.puedoAgregar(id);

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}