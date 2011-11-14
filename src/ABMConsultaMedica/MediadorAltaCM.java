package ABMConsultaMedica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Dominio.ConsultaMedica;

public class MediadorAltaCM implements ActionListener {

	private MediadorBMB mCM;
	private FormAltaModifCM guiAMCM;
	private ConsultaMedica cM;

	public MediadorAltaCM(GuiAbmCM guiP) throws SQLException {
		this.guiAMCM = new FormAltaModifCM(guiP,true);
		this.guiAMCM.setActionListeners(this);
		this.guiAMCM.setVisible(true);
	}

	public void show() {
		guiAMCM.show();
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == guiAMCM.getJBGrabar()) {         
			try{
				String idPaciente = this.guiAMCM.getJTFIdPaciente().getText();
				String dniMedico = this.guiAMCM.getJTFIdMedico().getText();
				System.out.println(dniMedico);
				if (!datosCorrectos(dniMedico,"dni","persona")) { //Se cambio donde decia tabla medico que no existia por la tabla persona y tambien el idMedico por dni que es el que utiliza
					JOptionPane.showMessageDialog(new JFrame(),"No existe medico con ese DNI.","",JOptionPane.ERROR_MESSAGE);
				} 
				else {               
					if (!datosCorrectos(idPaciente,"dni","persona")){
						JOptionPane.showMessageDialog(new JFrame(),"No existe paciente con ese DNI.","",JOptionPane.ERROR_MESSAGE);
					} 
					else {    	

						String fecha = this.guiAMCM.getJTFFecha().getText();
						String diagnostico = this.guiAMCM.getJTFDiagnostico().getText();
						int auxIdPaciente = Integer.parseInt(idPaciente);
						int auxIdMedico = Integer.parseInt(dniMedico);
						System.out.println(diagnostico+auxIdPaciente+fecha+auxIdMedico);
						ConsultaMedica alta= new ConsultaMedica();
						alta.setIdMedico(auxIdMedico);
						alta.setIdPaciente(auxIdPaciente);
						alta.setFecha(fecha);
						alta.setDiagnostico(diagnostico);
						alta.getCCM().insertarConsulta();
						JOptionPane.showMessageDialog(new JFrame(),"Nueva Consulta Aceptada","",JOptionPane.ERROR_MESSAGE);
						guiAMCM.dispose();
					}
				}
			}
			catch (Exception p) {
				p.printStackTrace();
			}      
		} 
		else { // boton "cancelar"
			guiAMCM.dispose();
		}
	}

	public boolean datosCorrectos(String id, String nomCol, String nomTabla) throws SQLException {
		// @ToDo
		// Comentado hasta que se pueda verificar si el médico existe
		//return true;
		try {
			ConsultaMedica as = new ConsultaMedica();
			ControlConsultaMedica asd = new ControlConsultaMedica("persona",1,as);
			boolean resultado = asd.existe(id, nomCol, nomTabla);
			if (!resultado) {
				return(false);
			}
			else {
				return(true);
			}
		}
		catch (Exception e) {
			System.out.println("Error al verificar existencia.\n"+ e);
			return(false);
		}
	}

}
