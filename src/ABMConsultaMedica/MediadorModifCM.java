package ABMConsultaMedica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Dominio.ConsultaMedica;

public class MediadorModifCM implements ActionListener {

	private MediadorBMB mCM;
	private FormAltaModifCM guiAMCM;
	private ConsultaMedica cM;

	public MediadorModifCM(GuiAbmCM guiP, MediadorBMB mCM) throws SQLException{
		this.mCM = mCM;
		this.cM = this.mCM.getConsultaMedica();
		this.guiAMCM = new FormAltaModifCM(guiP,true,this.mCM.getConsultaMedica());
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
				String idMedico = this.guiAMCM.getJTFIdMedico().getText();
				System.out.println(idMedico);
				if (!datosCorrectos(idMedico,"idMedico","medico")) {
					JOptionPane.showMessageDialog(new JFrame(),"No existe médico con ese DNI.","",
							JOptionPane.ERROR_MESSAGE);
				}
				else {
					if (!datosCorrectos(idPaciente,"idPaciente","paciente")){
						JOptionPane.showMessageDialog(new JFrame(),"No existe paciente con ese DNI","",
								JOptionPane.ERROR_MESSAGE);
					}
					else {
						String fecha = this.guiAMCM.getJTFFecha().getText();
						String diagnostico = this.guiAMCM.getJTFDiagnostico().getText();
						int auxIdPaciente = Integer.parseInt(idPaciente);
						int auxIdMedico = Integer.parseInt(idMedico);
						this.cM.setIdMedico(auxIdMedico);
						this.cM.setIdPaciente(auxIdPaciente);
						this.cM.setFecha(fecha);
						this.cM.setDiagnostico(diagnostico);
						this.cM.getCCM().modificarConsulta();
						JOptionPane.showMessageDialog(new JFrame(),"Datos Modificados.","",
								JOptionPane.ERROR_MESSAGE);
						guiAMCM.dispose();
					}
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}      
		} else { // boton "cancelar"
			guiAMCM.dispose();
		}
	}

	public boolean datosCorrectos(String id,String nomCol,String nomTabla) throws SQLException{
		try {
			boolean resultado = this.cM.getCCM().existe(id,nomCol,nomTabla);
			if (!resultado) {
				return(false);
			} 
			else {
				return(true);
			}
		} 
		catch (Exception e) {
			System.out.println("Error al verificar existencia.\n"+ e);
			e.printStackTrace();
			return(false);}
	}

}
