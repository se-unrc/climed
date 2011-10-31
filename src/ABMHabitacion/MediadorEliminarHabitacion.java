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
class MediadorEliminarHabitacion {

	// private GUIEliminarHabitacion guiEliminarHabitacion = new GUIEliminarHabitacion(new javax.swing.JFrame(), true);

	public void mostrarVentanaEliminarHabitacion(GUIEliminarHabitacion guiEliminarHabitacion) {

		guiEliminarHabitacion.setVisible(true);

	}

	public void eliminar(GUIEliminarHabitacion guiEliminarHabitacion) throws SQLException {
		ControlHabitacion conexion = new ControlHabitacion();
		String nroHabitacion = (guiEliminarHabitacion.getJTextField1()).getText();
		int nroHabit = Integer.parseInt(nroHabitacion);

		conexion.eliminarHabitacion(nroHabit);
	} 
}
