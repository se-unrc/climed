/**
 * TERMINAR
 */
package ABMPersona;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;

import Conexion.ControlConexion;

/**
 * @author vientosdepoder
 *
 */
public class MediadorModificarPersonaGUI  implements ActionListener{

	private ModificarPersonaGUI gui;
	private String tabla;
	private ControlConexion controlConexion;

	public MediadorModificarPersonaGUI(int cat){
		tabla = "persona";
		controlConexion = new ControlConexion();
		gui = new ModificarPersonaGUI();
		gui.addActionListener(this);
		gui.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton){
			ListenerEvent(((JButton) obj).getName());
		}
		else if(obj instanceof JComboBox){
			ListenerEvent(((JComboBox) obj).getName());
		}
	}
	
	private void ListenerEvent(String event){	
		if(event.compareTo("botonAceptar") == 0){
			String query = gui.armarModificarQuerry(tabla);
			System.out.println(query);
			controlConexion.ejecutarSentencia(query);
			gui.cerrar();
		}
		else if(event.compareTo("botonCancelar") == 0){
			gui.cerrar();
		}
		else if(event.compareTo("comboBoxClase") == 0){
			switch (gui.getClase()) {
			case 0:
				gui.activarCategoria(false);
				gui.activarEspecialidad(false);
				gui.activarHoras(false);
				break;
			case 1:
				gui.activarCategoria(false);
				gui.activarEspecialidad(false);
				gui.activarHoras(true);
				break;
			case 2:
				gui.activarCategoria(true);
				gui.activarEspecialidad(false);
				gui.activarHoras(false);
				break;
			case 3:
				gui.activarCategoria(false);
				gui.activarEspecialidad(true);
				gui.activarHoras(false);
				break;
			}
			gui.repaint();
		}
	}

}
