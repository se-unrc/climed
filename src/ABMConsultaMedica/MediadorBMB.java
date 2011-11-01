package ABMConsultaMedica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Dominio.ConsultaMedica;

public class MediadorBMB implements ActionListener, ListSelectionListener {

	private GuiAbmCM guiConsulta;
	private GuiBMB guiBsq;
	private Vector views=new Vector();
	private boolean flag=false;
	private ConsultaMedica cM;
	private MediadorAltaCM mACM;
	private MediadorModifCM mMCM;
	private String idUltimaConsulta; 

	public MediadorBMB(GuiAbmCM guiP) throws SQLException {   
		this.cM = new ConsultaMedica();
		this.guiBsq = new GuiBMB((JFrame) guiP, true);
		this.guiBsq.setActionListeners(this);
		this.guiBsq.setListSelectionListener(this);
		this.guiBsq.setVisible(true);
		this.flag = true;
		guiConsulta = guiP;
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj instanceof JButton) {
			ListenerEvent(((JButton) obj).getName());
		}
	}

	private void ListenerEvent(String event) {
		if (event.compareTo("Buscar")==0) {
			buscarDatos();
		} else if (event.compareTo("Ver Dx")==0) {
			mostrarDiagnostico();
		} else if (event.compareTo("Modificar")==0) {
			try{
				modificar();
			}
			catch(SQLException e) {
				System.out.println("Ha ocurrido una excepción");
				e.printStackTrace();
			}
		} else if (event.compareTo("Eliminar")==0) {
			eliminar();
		} else if (event.compareTo("Salir")==0) {
			guiBsq.dispose();
		}
	}

	public void modificar()throws SQLException {
		try {
			if (guiBsq.tabla.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(new JFrame(),"Para poder modificar una consulta debe seleccionar la fila previamente.","",JOptionPane.ERROR_MESSAGE);
			} 
			else {
				String idConsulta = ((String)guiBsq.datos[guiBsq.tabla.getSelectedRow()][0]);
				String idMedico = ((String)guiBsq.datos[guiBsq.tabla.getSelectedRow()][1]);
				String idPaciente = ((String)guiBsq.datos[guiBsq.tabla.getSelectedRow()][2]);
				String fecha = ((String)guiBsq.datos[guiBsq.tabla.getSelectedRow()][3]);
				String diagnostico = ((String)guiBsq.datos[guiBsq.tabla.getSelectedRow()][4]);
				int auxIdConsulta = Integer.parseInt(idConsulta);
				int auxIdMedico = Integer.parseInt(idMedico);
				int auxIdPaciente = Integer.parseInt(idPaciente);
				this.cM.setIdConsulta(auxIdConsulta);
				this.cM.setIdMedico(auxIdMedico);
				this.cM.setIdPaciente(auxIdPaciente);
				this.cM.setFecha(fecha);
				this.cM.setDiagnostico(diagnostico);
				this.guiBsq.dispose();
				mMCM = new MediadorModifCM(guiConsulta, this);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private void mostrarDiagnostico(){
		if (guiBsq.tabla.getSelectedRow() == -1){
			JOptionPane.showMessageDialog(new JFrame(),"Para poder ver el diagnóstico debe seleccionar la fila previamente.","",JOptionPane.ERROR_MESSAGE);
		}
		else{
			String id = (String)guiBsq.datos[guiBsq.tabla.getSelectedRow()][0];
			int auxId = Integer.parseInt(id);
			this.cM.setIdConsulta(auxId);
			String diagnostico = ((String)guiBsq.datos[guiBsq.tabla.getSelectedRow()][4]);    
			JOptionPane.showMessageDialog(new JFrame(),diagnostico,"",JOptionPane.INFORMATION_MESSAGE); ;

		}
	}
	private void eliminar() {
		try{
			if (guiBsq.tabla.getSelectedRow() == -1){
				JOptionPane.showMessageDialog(new JFrame(),"Para poder eliminar una consulta debe seleccionar la fila previamente.","",
						JOptionPane.ERROR_MESSAGE);
			} else {
				String id = (String)guiBsq.datos[guiBsq.tabla.getSelectedRow()][0];
				int auxId = Integer.parseInt(id);
				this.cM.setIdConsulta(auxId);
				this.cM.getCCM().eliminarConsulta();
				guiBsq.dispose();
				JOptionPane.showMessageDialog(new JFrame(),"CONSULTA ELIMINADA","", JOptionPane.ERROR_MESSAGE);
				guiBsq.dispose();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void buscarDatos() {
		try {
			String idPaciente = this.guiBsq.getJTFDni().getText();		
			if (idPaciente.length()==0) {
				JOptionPane.showMessageDialog(new JFrame(),"Ingrese el DNI del paciente.","",
						JOptionPane.ERROR_MESSAGE);
			} 
			else {
				int auxIdPaciente = new Integer(Integer.parseInt(idPaciente));
				this.cM.setIdPaciente(auxIdPaciente);
				Vector cm = this.cM.getCCM().buscarConsulta(auxIdPaciente);
				if (cm.size()==0) {
					JOptionPane.showMessageDialog(new JFrame(),"No se encontró ningún paciente con ese DNI.","",JOptionPane.ERROR_MESSAGE);
				} 
				else {
					guiBsq.datos = new String[cm.size()][guiBsq.titulos.length];
					int i = 0;
					for (int j = 0; j < cm.size(); j++) {
						guiBsq.datos[i] = (String[]) cm.get(i);
						i++;
					}
				}
			}
		}
		catch(Exception e) {
			System.out.println("Ha ocurrido una excepción.");
			e.printStackTrace();
		}
		guiBsq.tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		guiBsq.actualizarTabla();
		guiBsq.show();
	}

	public GuiBMB getGUI() {
		return guiBsq;
	}

	public void show() {
		guiBsq.show();
	}

	public void keyTyped(KeyEvent e) { }
	public void keyPressed(KeyEvent e) { }
	public void valueChanged(ListSelectionEvent arg0) { }

	public GuiBMB getGUIConsultaMedica(){
		return(this.guiBsq);
	}

	public ConsultaMedica getConsultaMedica(){
		return(this.cM);
	}

}
