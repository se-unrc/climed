package ABMConvenio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Dominio.Convenio;



public class MediadorModificarConvenio implements ActionListener{

	//gui
	private GuiAltaConvenio guiModificarConvenio = null;

	//mediador de la gestion de talones
	private MediadorABMConvenio mediadorABMConvenio = null;

	private Connection conexion;

	public Statement st;

	/**
	 * Constructor de la clase
	 * @param abmM mediador de la gestion de Convenio
	 */
	public MediadorModificarConvenio(MediadorABMConvenio abmM,Connection conexion) throws Exception{
		guiModificarConvenio = new GuiAltaConvenio(abmM.getGuiABMConvenio(),"Modificar Convenio");
		guiModificarConvenio.setActionListeners(this);
		mediadorABMConvenio = abmM;
		this.conexion = conexion;
		int i = abmM.obtenerIndiceSeleccionado();
		Object [] conv = abmM.obtenerDeGrilla(i);
		guiModificarConvenio.getComboBoxMedico().setSelectedItem(conv[1].toString());
		guiModificarConvenio.getComboBoxObraSocial().setSelectedItem(conv[2].toString());
		guiModificarConvenio.getTextFieldCobertura().setText(conv[3].toString());
		guiModificarConvenio.getJTextFieldNro().enable(false);
		this.st = conexion.createStatement();
		ControlConvenio controlConvenio = new ControlConvenio(conexion,st);
		int num = controlConvenio.obtenerUltimoConvenio() + 1;
		guiModificarConvenio.getJTextFieldNro().setText(num+"");
		guiModificarConvenio.hacerVisible();
	}

	/**
	 * Retorna el mediador de la gestion de talones
	 * @return el mediador de la gestion de talones
	 */
	public MediadorABMConvenio getMediadorABMConvenio() {
		return mediadorABMConvenio;
	}

	/**
	 * Setea el mediador de la gestion de talones con un nuevo mediador
	 * @param mediadorABMCliente mediador que reemplazara al actual
	 */
	public void setMediadorABMConvenio(MediadorABMConvenio mediadorABMConvenio) {
		this.mediadorABMConvenio = mediadorABMConvenio;
	}


	/**
	 * Retorna la gui de la modificacion de un convenio
	 * @return la gui de la modificacion de un convenio
	 */
	public GuiAltaConvenio getGuiModifConvenio() {
		return guiModificarConvenio;
	}

	/**
	 * hace que la gui sea visible
	 */
	public void show(){
		guiModificarConvenio.show();
	}

	/** 
	 * Invoca acciones dependiendo del boton que ha sido accionado
	 * @param e representa un evento, que en este caso seria accionar un boton
	 */
	public void actionPerformed(ActionEvent e) { 
		//Obtenemos el codigo del evento que ocurrio
		Object source = e.getSource();

		if (source == guiModificarConvenio.getBotonAceptar()) {  // si presionamos el boton GUARDAR
			int quitOption = JOptionPane.showConfirmDialog(new JFrame(),"Esta Seguro que desea modificar el Convenio ?","Modificar",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
			if(quitOption == 0){
				this.presionBotonAceptar();
			}
		}
		if (source == guiModificarConvenio.getBotonCancelar()) {  // si presionamos el boton GUARDAR
			this.presionBotonCancelar();
		}
	}


	private void presionBotonCancelar(){
		guiModificarConvenio.dispose();
	}

	private void presionBotonAceptar(){
		int i = mediadorABMConvenio.obtenerIndiceSeleccionado();
		Object [] fila = mediadorABMConvenio.getGuiABMConvenio().getTabla().obtenerFila(i);
		String medico = fila[1].toString(); 
		String obraSocial = fila[2].toString();
		float cobertura = Float.parseFloat(fila[3].toString());
		int nro = Integer.parseInt(fila[0].toString());
		Convenio convenio = new Convenio(nro,medico,obraSocial,cobertura);
		try {
			st = conexion.createStatement();
			ControlConvenio controlConvenio = new ControlConvenio(conexion,st);
			//*Cargamos el Convenio NUEVO*//
			String medicoNuevo = (String)guiModificarConvenio.getComboBoxMedico().getSelectedItem();
			String obraSocialNuevo = (String)guiModificarConvenio.getComboBoxObraSocial().getSelectedItem();
			float coberturaNuevo = Float.parseFloat((String) guiModificarConvenio.getTextFieldCobertura().getText());
			Convenio convenioNuevo = new Convenio(nro,medicoNuevo, obraSocialNuevo,coberturaNuevo);
			if(!(controlConvenio.existeConvenio(convenioNuevo))){
				if(!(cobertura == 0)){
					controlConvenio.modificar(convenioNuevo,nro);
					//Refrescamos la gui abm convenio
					mediadorABMConvenio.getGuiABMConvenio().getTabla().borrarTodo();
					controlConvenio.cargarTabla(mediadorABMConvenio.getGuiABMConvenio().getTabla());
					guiModificarConvenio.dispose();
					st.execute("END");
					st.close();
					//conexion.close();
				}else{
					JOptionPane.showMessageDialog(new JFrame(),"Formato de Codigo incorrecto. Solo se aceptan numeros","Error",JOptionPane.ERROR_MESSAGE);
					guiModificarConvenio.getTextFieldCobertura().setText("");
				}
			}else{
				JOptionPane.showMessageDialog(new JFrame(),"El convenio que desea dar de alta ya existe.","Error",JOptionPane.ERROR_MESSAGE);
				guiModificarConvenio.getComboBoxMedico().setSelectedIndex(0);
				guiModificarConvenio.getComboBoxObraSocial().setSelectedIndex(0);
				guiModificarConvenio.getTextFieldCobertura().setText("");
			}
		}catch (Exception error) {
			error.printStackTrace();				
		}
	}



}
