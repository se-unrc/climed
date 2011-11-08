package ABMObraSocial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

public class MediadorCartelBajas implements ActionListener{

	private CartelBajas gui = new CartelBajas();
	private ControlObraSocial control;


	public MediadorCartelBajas(){
		gui.addActionListener(this);
		gui.setVisible(true);
		control = new ControlObraSocial();
		LinkedList nombres = control.listar();
		if (nombres.size() == 0){
			JOptionPane.showMessageDialog(gui, "No hay obras sociales en la base de datos para borrar","INFORMACION",JOptionPane.INFORMATION_MESSAGE);
			gui.dispose();
		} else {
			gui.agregarDatos(nombres);
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton)
		  ListenerEvent(((JButton) obj).getName());
	}


	private void ListenerEvent(String event){
		if(event.compareTo("Borrar")==0){
			control.eliminar(gui.getComboBoxValueSelected());
			JOptionPane.showMessageDialog(gui, gui.getComboBoxValueSelected()+" borrado.");
			gui.dispose();
		} else if(event.compareTo("Cancelar")==0){
			gui.dispose();
		}
	}	
}
