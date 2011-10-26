import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class MediadorCartelAltas implements ActionListener{

	private CartelAltas gui = new CartelAltas();
	private ControlObraSocial control;


	public MediadorCartelAltas(){
		gui.addActionListener(this);
		gui.setVisible(true);
		control = new ControlObraSocial();
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton)
		  ListenerEvent(((JButton) obj).getName());
	}


	private void ListenerEvent(String event){
		if(event.compareTo("Agregar")==0){
			if (gui.getNumero().compareTo("") == 0){
				JOptionPane.showMessageDialog(gui, "Falta ingresar el numero de la obra social","ERROR",JOptionPane.WARNING_MESSAGE);
			} else if (gui.getNombre().compareTo("") == 0){
				JOptionPane.showMessageDialog(gui, "Falta ingresar el nombre de la obra social","ERROR",JOptionPane.WARNING_MESSAGE);
			} else if (gui.getDireccion().compareTo("") == 0){
				JOptionPane.showMessageDialog(gui, "Falta ingresar la direccion de la obra social","ERROR",JOptionPane.WARNING_MESSAGE);
			} else if (gui.getTelefono().compareTo("") == 0){
				JOptionPane.showMessageDialog(gui, "Falta ingresar el telefono de la obra social","ERROR",JOptionPane.WARNING_MESSAGE);
			} else if (gui.getCuit().compareTo("") == 0){
				JOptionPane.showMessageDialog(gui, "Falta ingresar el cuit de la obra social","ERROR",JOptionPane.WARNING_MESSAGE);
			} else{
				int numero;
				try{
					numero = Integer.parseInt(gui.getNumero());
					if (numero <= 0)
						JOptionPane.showMessageDialog(gui, "El numero de la obra social no puede ser menor a 1.","ERROR",JOptionPane.WARNING_MESSAGE);
					control.insertar(numero,gui.getNombre(),gui.getDireccion(),gui.getTelefono(),gui.getCuit());
					JOptionPane.showMessageDialog(gui, gui.getNombre()+" guardado correctamente.","GUARDADO EXITOSO", JOptionPane.INFORMATION_MESSAGE);
					gui.limpiarCampos();
				} catch (SQLException e){
					JOptionPane.showMessageDialog(gui, "Ya existe en la base de datos una obra social con el numero y/o el nombre ingresado.","ERROR",JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException e){
					JOptionPane.showMessageDialog(gui, "El numero de la obra social debe ser un numero (no letras)","ERROR",JOptionPane.ERROR_MESSAGE);
				}
			}			
		} else if(event.compareTo("Cancelar")==0){
			gui.dispose();
		}
	}	
}