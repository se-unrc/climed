/**
 * TERMINAR
 */
package principal;

import gui.*;
import java.awt.event.*;
import javax.swing.*;

import Conexion.ControlConexion;

/**
 * @author vientosdepoder
 *
 */
public class MediadorBajaGUI  implements ActionListener{

	private BajaGUI gui;
	private String tabla;
	private ControlConexion controlConexion;

	public MediadorBajaGUI(){
		tabla = "persona";
		controlConexion = new ControlConexion();
		gui = new BajaGUI();
		gui.addActionListener(this);
		gui.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton){
			ListenerEvent(((JButton) obj).getName());
		}
	}
	
	private void ListenerEvent(String event){
		if(event.compareTo("Borrar") == 0){
			String query = armarBajaQuerry(tabla);
			System.out.println(query);
			controlConexion.ejecutarSentencia(query);
			gui.cerrar();
		}
		else if(event.compareTo("Cancelar") == 0){
			gui.cerrar();
		}
	}
	
	public String armarBajaQuerry(String tabla) {
		String res = "DELETE FROM " + tabla + " WHERE identificador = " + gui.obtenerId() + ";";
		return res;
	}
}
