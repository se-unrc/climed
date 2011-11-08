/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ABMHabitacion;

import java.sql.SQLException;

/**
 *
 * @author jime
 */
class MediadorModificarHabitacion {

	//private GUIModificarHabitacion guiModificarHabitacion = new GUIModificarHabitacion(new javax.swing.JFrame(), true);

	public void mostrarVentanaModificarHabitacion(GUIModificarHabitacion guiModificarHabitacion) {
		guiModificarHabitacion.setVisible(true);
	}

	public void modificar(GUIModificarHabitacion guiModificarHabitacion) throws SQLException {
		ControlHabitacion conexion = new ControlHabitacion();
		String nroHabitacion = (guiModificarHabitacion.getJTextField1()).getText();
		String fechaInternacion = (guiModificarHabitacion.getJTextField2()).getText();
		String nroPiso = (guiModificarHabitacion.getJTextField3()).getText();
		int nroHabit = Integer.parseInt(nroHabitacion);
		int numPiso = Integer.parseInt(nroPiso);
		conexion.modificarHabitacion(nroHabit,numPiso,fechaInternacion);
	} 
}
