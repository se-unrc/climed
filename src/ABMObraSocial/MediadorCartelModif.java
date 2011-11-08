package ABMObraSocial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class MediadorCartelModif implements ActionListener{

	private CartelModif gui = new CartelModif();
	private ControlObraSocial control;


	public MediadorCartelModif(){
		gui.addActionListener(this);
		gui.setVisible(true);
		control = new ControlObraSocial();
		LinkedList nombres = control.listar();
		if (nombres.size() == 0){
			JOptionPane.showMessageDialog(gui, "No hay obras sociales en la base de datos para modificar","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
			gui.dispose();
		} else{
			gui.agregarDatos(nombres);
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton)
			ListenerEvent(((JButton) obj).getName());
		else if (obj instanceof JComboBox)
			ListenerEvent(((JComboBox) obj).getName());
	}


	private void ListenerEvent(String event){
		if(event.compareTo("Modificar")==0){
			if (gui.getDireccion().compareTo("") == 0){
				JOptionPane.showMessageDialog(gui, "Falta ingresar la direccion de la obra social","ERROR",JOptionPane.WARNING_MESSAGE);
			} else if (gui.getTelefono().compareTo("") == 0){
				JOptionPane.showMessageDialog(gui, "Falta ingresar el telefono de la obra social","ERROR",JOptionPane.WARNING_MESSAGE);
			} else if (gui.getCuit().compareTo("") == 0){
				JOptionPane.showMessageDialog(gui, "Falta ingresar el cuit de la obra social","ERROR",JOptionPane.WARNING_MESSAGE);
			} else{
				int numero;
				try{
					numero = Integer.parseInt(gui.getNumero());
					control.modificar(numero,gui.getNombre(),gui.getDireccion(),gui.getTelefono(),gui.getCuit());
					JOptionPane.showMessageDialog(gui, gui.getNombre()+" modificado correctamente.","MODIFICADO EXITOSO", JOptionPane.INFORMATION_MESSAGE);
					gui.dispose();
				} catch (SQLException e){
					JOptionPane.showMessageDialog(gui, "Se produjo un error al realizar el acceso a la base de datos.","ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if(event.compareTo("Cancelar")==0){
			gui.dispose();
		} else if(event.compareTo("comboBox") == 0){
			gui.llenarCampos(control.seleccionar(gui.getComboBoxValueSelected()));
		}
	}	
}