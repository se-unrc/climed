package principal;

import gui.*;
import java.awt.event.*;

import javax.swing.*;

import Conexion.ControlConexion;

/**
 * @author vientosdepoder
 *
 */
public class MediadorAltaPersonaGUI  implements ActionListener{

	private AltaPersonaGUI gui;
	private String tabla;
	private ControlConexion controlConexion;

	public MediadorAltaPersonaGUI(int cat){
		tabla = "persona";
		controlConexion = new ControlConexion();
		gui = new AltaPersonaGUI();
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
			String query = gui.armarInsertQuerry(tabla);
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
