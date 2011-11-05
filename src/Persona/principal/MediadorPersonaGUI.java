package principal;

import gui.*;
import java.awt.event.*;
import javax.swing.*;
import Conexion.*;

/**
 * @author vientosdepoder
 *
 */
public class MediadorPersonaGUI  implements ActionListener{

	private PrincipalGUI gui;
	private Conexion conn;
	private int categoria;

	public MediadorPersonaGUI(){
		gui = new PrincipalGUI();
		gui.addActionListener(this);
		gui.setVisible(true);
		categoria = 0;
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JComboBox) {
			categoria = ((JComboBox) obj).getSelectedIndex();
			gui.getPanel().cargarTabla(categoria);
		}
		else if(obj instanceof JButton){
			ListenerEvent(((JButton) obj).getName());
		}
	}
	
	private void ListenerEvent(String event){
		
		if(event.compareTo("botonAlta") == 0){
			MediadorAltaPersonaGUI med = new MediadorAltaPersonaGUI(categoria);
		}
		else if(event.compareTo("botonBaja") == 0){
			MediadorBajaGUI baja = new MediadorBajaGUI();
			//gui.getPanel().cargarTabla(categoria);
		}
		else if(event.compareTo("botonModificar") == 0){
			MediadorModificarPersonaGUI med = new MediadorModificarPersonaGUI(categoria);
			//gui.getPanel().cargarTabla(categoria);
		}
		else if(event.compareTo("botonActualizar") == 0){
			//gui.getPanel().cargarTabla(categoria);
		}
		gui.getPanel().cargarTabla(categoria);
	}

}
