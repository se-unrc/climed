package MenuPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Main.Principal;

import javax.swing.JButton;

import ABMConsultaMedica.MediadorConsulta;
import ABMConvenio.MediadorABMConvenio;
import ABMDieta.MediadorMenuPrincipal;
import ABMEspecialidad.MediadorEspecialidad;
import ABMHabitacion.MediadorPrincipalHabitacion;
import ABMHorario.MediadorPrincipal;
import ABMMedicamento.MediadorMedicamentos;
import ABMObraSocial.MediadorObraSocial;
import ABMPersona.MediadorPersonaGUI;


public class MediadorMenu  implements ActionListener {

	private Menu gui = new Menu();

	public MediadorMenu(Principal guiP){
		gui.addActionListener(this);
		gui.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton)
			try {
				ListenerEvent(((JButton) obj).getName());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

	private void ListenerEvent(String event) throws Exception{
		if(event.compareTo("ABMConvenio")==0){
			new MediadorABMConvenio(gui);
		} else if (event.compareTo("ABMConsulta")==0){
			new MediadorConsulta();
		} else if (event.compareTo("ABMDieta")== 0){
			new MediadorMenuPrincipal();
		} else if (event.compareTo("ABMEspecialidad")== 0){
			new MediadorEspecialidad();
		} else if (event.compareTo("ABMHabitacion")== 0){
			MediadorPrincipalHabitacion mediador = new MediadorPrincipalHabitacion();
			mediador.mostrarGuiMenuPrincipal();
		} else if (event.compareTo("ABMHorario")== 0){
			new MediadorPrincipal();
		} else if (event.compareTo("ABMMedicamento")== 0){
			new MediadorMedicamentos();
		} else if (event.compareTo("ABMObraSocial")==0){			
			new MediadorObraSocial();
		} else if (event.compareTo("ABMPersona")== 0){
			new MediadorPersonaGUI();
		} else if (event.compareTo("Salir")== 0){
			System.exit( 0 ); 
		}
	}
}
