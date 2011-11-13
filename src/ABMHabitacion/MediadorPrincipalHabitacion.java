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
public class MediadorPrincipalHabitacion {
	private GUIPrincipal guiMenuPrincipal = new GUIPrincipal();

	public void mostrarGuiMenuPrincipal() {
		guiMenuPrincipal.setVisible(true);
	}

	public void insertarHabitacion() {
		GUICargarHabitacion guiCargarHabitacion = new GUICargarHabitacion(new javax.swing.JFrame(), true);
		MediadorCargaHabitacion mediadorCargaHabitacion= new MediadorCargaHabitacion();
		mediadorCargaHabitacion.mostrarVentanaCargaHabitacion(guiCargarHabitacion);
		try {
			mediadorCargaHabitacion.aceptar(guiCargarHabitacion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modificarHabitacion() {
		GUIModificarHabitacion guiModificarHabitacion = new GUIModificarHabitacion(new javax.swing.JFrame(), true);
		MediadorModificarHabitacion mediadorModificarHabitacion = new MediadorModificarHabitacion();
		mediadorModificarHabitacion.mostrarVentanaModificarHabitacion(guiModificarHabitacion);
		try {
			mediadorModificarHabitacion.modificar(guiModificarHabitacion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void eliminarHabitacion() {
		GUIEliminarHabitacion guiEliminarHabitacion = new GUIEliminarHabitacion(new javax.swing.JFrame(), true);
		MediadorEliminarHabitacion mediadorEliminarHabitacion = new MediadorEliminarHabitacion();
		mediadorEliminarHabitacion.mostrarVentanaEliminarHabitacion(guiEliminarHabitacion );
		try {
			mediadorEliminarHabitacion.eliminar(guiEliminarHabitacion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}




	public void cerrarGuiMenuPrincipal() {
		guiMenuPrincipal.dispose();
	}

}
