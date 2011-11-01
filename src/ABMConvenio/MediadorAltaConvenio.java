package ABMConvenio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Dominio.Convenio;


public class MediadorAltaConvenio implements ActionListener{

	//gui
	private GuiAltaConvenio guiAltaConvenio = null;

	// mediador de la gestion de talones de devolucion
	private MediadorABMConvenio mediadorABMConvenios = null;

	private Connection conexion;

	public Statement st;

	/**
	 * Constructor de la clase
	 * @param mABMC mediador de la gestion de talones
	 */
	public MediadorAltaConvenio(MediadorABMConvenio mABMC,Connection conexion) throws Exception{
		guiAltaConvenio = new GuiAltaConvenio(mABMC.getGuiABMConvenio());
		guiAltaConvenio.setActionListeners(this);
		guiAltaConvenio.getJTextFieldNro().enable(false);
		this.st = conexion.createStatement();
		ControlConvenio controlConvenio = new ControlConvenio(conexion,st);
		int num = controlConvenio.obtenerUltimoConvenio() + 1;
		guiAltaConvenio.getJTextFieldNro().setText(num+"");
		this.conexion = conexion;
		mediadorABMConvenios = mABMC;
		guiAltaConvenio.hacerVisible();
	}

	/**
	 * Permite que la gui sea visible
	 */
	public void show(){
		guiAltaConvenio.show();
	}

	/** 
	 * Invoca acciones dependiendo del boton que ha sido accionado
	 * @param e representa un evento, que en este caso seria accionar un boton
	 */
	public void actionPerformed(ActionEvent e) { 
		//Obtenemos el codigo del evento que ocurrio
		Object source = e.getSource();

		if (source == guiAltaConvenio.getBotonCancelar()) { // si presionamos el boton ALTA
			this.presionBotonCancelar();
		}
		if (source == guiAltaConvenio.getBotonAceptar()) {  // si presionamos el boton Aceptar
			this.presionBotonAceptar();
		}	
	}

	private void presionBotonCancelar(){
		guiAltaConvenio.dispose();
	}

	private void presionBotonAceptar(){
		try{
			st = conexion.createStatement();
			String medico = (String) guiAltaConvenio.getComboBoxMedico().getSelectedItem();
			String obraSocial = (String) guiAltaConvenio.getComboBoxObraSocial().getSelectedItem();
			float cobertura = Float.parseFloat(guiAltaConvenio.getTextFieldCobertura().getText());
			int nro = Integer.parseInt(guiAltaConvenio.getJTextFieldNro().getText());
			ControlConvenio controlConvenio = new ControlConvenio(conexion,st);
			Convenio convenio = new Convenio(nro,medico,obraSocial,cobertura);
			if(!(controlConvenio.existeConvenio(convenio))){
				controlConvenio.insertar(convenio);
				mediadorABMConvenios.getGuiABMConvenio().getTabla().borrarTodo();
				controlConvenio.cargarTabla(mediadorABMConvenios.getGuiABMConvenio().getTabla());
				JOptionPane.showMessageDialog(new JFrame(),"El convenio se dio de alta con Exito.",
						"Informacion",JOptionPane.INFORMATION_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(new JFrame(),"El convenio que desea dar de alta ya existe.",
						"Informacion",JOptionPane.INFORMATION_MESSAGE);
			}
			st.execute("END");
			st.close();
			//conexion.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		guiAltaConvenio.dispose();
	}

	/**
	 * Retorna la gui de alta de talones
	 * @return la gui de alta de talones
	 */
	public GuiAltaConvenio getGui() {
		return guiAltaConvenio;
	}
}