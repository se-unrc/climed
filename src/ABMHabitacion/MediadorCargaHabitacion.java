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
class MediadorCargaHabitacion {
	
	public void mostrarVentanaCargaHabitacion(GUICargarHabitacion guiCargarHabitacion) {

		guiCargarHabitacion.setVisible(true);

	}

	/**
	 * 
	 * @param guiCargarHabitacion
	 * @throws SQLException
	 * 
	 * Este metodo aceptar(GUICargarHabitacion guiCargarHabitacion): que tiene como parametro
	 * la guiCargarHabitacion donde obtiene lo que se ingresa en los TextField para poder
	 * insertarlo en la base de datos.
	 */
	public void aceptar(GUICargarHabitacion guiCargarHabitacion) throws SQLException {

		ControlHabitacion conexion = new ControlHabitacion();
		String numHabitacion = (guiCargarHabitacion.getJTextField1()).getText();
		String fechaInternacion = (guiCargarHabitacion.getJTextField2()).getText();
		String nroPiso = (guiCargarHabitacion.getJTextField3()).getText();
		int nroHabit = Integer.parseInt(numHabitacion);
		int piso = Integer.parseInt(nroPiso);
		conexion.cargarHabitacion(nroHabit,piso,fechaInternacion);
	}
}
