/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Busqueda.java
 *
 * Created on 23/10/2011, 11:35:01
 */
package consultaMedica;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;


/**
 *
 * @author Grupo 6
 */
public class GuiBMB extends javax.swing.JDialog {

	public final String[] titulos = {"Nro. Consulta", "DNI Médico", "DNI Paciente", "Fecha"};
	public String[][] datos;
	public JTable tabla;
	private JTextField jtfNombre = null;
	private JTextField jtfApellido = null;
	public JTextField jtfDni = null;
	private JTextField jtfDireccion = null;
	private JTextField jtfTelefono = null;
	private JTextField jtfEspecialidad = null;

	private JLabel dni, nombre, apellido, direccion, telefono, especialidad;

	public JPanel jpPpal = null;
	private JPanel jpDatos = null;
	private JPanel jpGestion = null;
	private JPanel jpBuscador = null;

	private ModeloTabla modTabla = null;
	public JScrollPane jspDatos = null;

	private JButton jbVer = null;
	private JButton jbModif = null;

	private JButton jbSalir = null;
	private JButton jbEliminar = null;
	private JButton jbBuscar = null;

	Color fondoPaneles = new Color(168,168,168); // red-green-blue

	/** Creates new form Busqueda */
	public GuiBMB(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
		inicializar();
	}

	private void inicializar() {
		datos = new String[0][titulos.length];
		this.setLocation(50,100);
		this.setSize(800,500);
		this.setResizable(false);
		this.setTitle("Gestionar Consultas Médicas");
		this.setContentPane(getJPPpal());
		this.getContentPane().setName("GUIGestionarMedico");
		jbSalir.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbSalirActionPerformed(evt);
			}
		});
	}

	public JPanel getJPPpal() {
		if (jpPpal == null) {
			jpPpal = new JPanel();
			jpPpal.setLayout(null);
			jpPpal.add(getJPGestion(), null);
			jpPpal.add(getJPDatos(), null);
			jpPpal.add(getJPBuscador(), null);
			jpPpal.add(getJBSalir(), null);
		}
		return jpPpal;
	}

	private JPanel getJPBuscador() {
		if (jpBuscador == null) {
			jpBuscador = new JPanel();
			jpBuscador .setBackground(fondoPaneles);
			jpBuscador.setLayout(null);
			jpBuscador.setBorder(javax.swing.BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1,1,1,1, new java.awt.Color(254, 107, 3)), "Buscar Consulta",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,javax.swing.border.TitledBorder.DEFAULT_POSITION,new java.awt.Font("Dialog", java.awt.Font.BOLD, 12), java.awt.Color.black));
			jpBuscador.setBounds(new Rectangle(15,35,358,80));
			jpBuscador.add(getJLDni(), null);
			jpBuscador.add(getJTFDni(), null);
			jpBuscador.add(getJBBuscar(),null);
			getJTFDni();
		}
		return jpBuscador;
	}

	private JPanel getJPGestion() {
		if (jpGestion == null) {
			jpGestion = new JPanel();
			jpGestion.setLayout(null);
			jpGestion.setBounds(new Rectangle(400,35,355,80));
			jpGestion.setBorder(javax.swing.BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1,1,1,1, new java.awt.Color(254, 107, 3)), "Consulta Médica", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
			jpGestion.add(getJBVer(), null);
			jpGestion.add(getJBModificar(), null);
			jpGestion.add(getJBEliminar(), null);
			jpGestion.setBackground(fondoPaneles);
		}
		return jpGestion;
	}

	private JPanel getJPDatos() {
		if (jpDatos == null) {
			jpDatos = new JPanel();
			jpDatos.setLayout(null);
			jpDatos.setBounds(new Rectangle(15,140,775,250));
			jpDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1,1,1,1, new java.awt.Color(254, 107, 3)), "Listado de Consultas Médicas",
					javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
					javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
			jpDatos.add(getJSPDatos(), null);
			jpDatos.setBackground(fondoPaneles);
		}
		return jpDatos;
	}

	private JScrollPane getJSPDatos() {
		if (jspDatos == null) {
			jspDatos = new JScrollPane();
			jspDatos.setBounds(new Rectangle(5,15,765,210));
			jspDatos.setViewportView(getJTDatos());
		}
		return jspDatos;
	}

	public JTable getJTDatos() {
		if (tabla == null) {
			modTabla = new ModeloTabla(titulos, datos);
			tabla = new JTable(modTabla);
		}
		return tabla;
	}

	public JTextField getJTFNombre() {
		if (jtfNombre == null) {
			jtfNombre = new JTextField();
			jtfNombre.setBounds(new Rectangle(160,15,180,22));
		}
		return jtfNombre;
	}

	public JTextField getJTFApellido() {
		if (jtfApellido == null) {
			jtfApellido = new JTextField();
			jtfApellido.setBounds(new Rectangle(160,45,180,22));
		}
		return jtfApellido;
	}

	public JTextField getJTFDireccion() {
		if (jtfDireccion == null) {
			jtfDireccion = new JTextField();
			jtfDireccion.setBounds(new Rectangle(160,75,180,22));
		}
		return jtfDireccion;
	}

	public JTextField getJTFTelefono() {
		if (jtfTelefono == null) {
			jtfTelefono = new JTextField();
			jtfTelefono.setBounds(new Rectangle(160,105,180,22));
		}
		return jtfTelefono;
	}

	public JTextField getJTFEspecialidad() {
		if (jtfEspecialidad == null) {
			jtfEspecialidad = new JTextField();
			jtfEspecialidad.setBounds(new Rectangle(160,135,180,22));
		}
		return jtfEspecialidad;
	}    

	public JTextField getJTFDni() {
		if (jtfDni == null) {
			jtfDni = new JTextField();
			jtfDni.setBounds(new Rectangle(260,20,80,22));
		}
		return jtfDni;
	}

	private JLabel getJLNombre() {
		if (nombre == null) {
			nombre = new JLabel("Nombre:");
			nombre.setHorizontalAlignment(JLabel.RIGHT);
			nombre.setBounds(new Rectangle(90,20,60,15));
		}
		return nombre;
	}

	private JLabel getJLApellido() {
		if (apellido == null) {
			apellido = new JLabel("Apellido:");
			apellido.setHorizontalAlignment(JLabel.RIGHT);
			apellido.setBounds(new Rectangle(90,50,60,15));
		}
		return apellido;
	}

	private JLabel getJLDireccion() {
		if (direccion == null) {
			direccion = new JLabel("Dirección:");
			direccion.setHorizontalAlignment(JLabel.RIGHT);
			direccion.setBounds(new Rectangle(90,80,60,15));
		}
		return direccion;
	}

	private JLabel getJLTelefono() {
		if (telefono == null) {
			telefono = new JLabel("Teléfono:");
			telefono.setHorizontalAlignment(JLabel.RIGHT);
			telefono.setBounds(new Rectangle(90,110,60,15));
		}
		return telefono;
	}

	private JLabel getJLEspecialidad() {
		if (especialidad == null) {
			especialidad = new JLabel("Especialidad:");
			especialidad.setHorizontalAlignment(JLabel.RIGHT);
			especialidad.setBounds(new Rectangle(90,130,60,15));
		}
		return especialidad;
	}


	private JLabel getJLDni() {
		if (dni == null) {
			dni = new JLabel("Ingrese DNI del paciente:");
			dni.setHorizontalAlignment(JLabel.RIGHT);
			dni.setBounds(new Rectangle(75,22,180,15));
		}
		return dni;
	}

	public JButton getJBVer() {
		if (jbVer == null) {
			jbVer = new JButton();
			jbVer.setName("Ver Dx");
			jbVer.setText("Ver Dx");
			jbVer.setBounds(new Rectangle(15,25,100,25));
		}
		return jbVer;
	}

	public JButton getJBModificar() {
		if (jbModif == null) {
			jbModif = new JButton();
			jbModif.setName("Modificar");
			jbModif.setText("Modificar");
			jbModif.setBounds(new Rectangle(130,25,100,25));
		}
		return jbModif;
	}

	public JButton getJBEliminar() {
		if (jbEliminar == null) {
			jbEliminar = new JButton();
			jbEliminar.setName("Eliminar");
			jbEliminar.setText("Eliminar");
			jbEliminar.setBounds(new Rectangle(245,25,100,25));
		}
		return jbEliminar;
	}


	public JButton getJBSalir() {
		if (jbSalir == null) {
			jbSalir = new JButton();
			jbSalir.setText("Salir");
			jbSalir.setName("Salir");
			jbSalir.setBounds(new Rectangle(685,420,100,30));
		}
		return jbSalir;
	}

	public JButton getJBBuscar() {
		if (jbBuscar == null) {
			jbBuscar = new JButton();
			jbBuscar.setText("Buscar");
			jbBuscar.setName("Buscar");
			jbBuscar.setBounds(new Rectangle(260,50,80,22));
		}
		return jbBuscar;
	}

	public void setKeyListener(KeyListener lis) {
		jtfDni.addKeyListener(lis);
	}

	public void setListSelectionListener(ListSelectionListener lis) {
		tabla.getSelectionModel().addListSelectionListener(lis);
	}

	public void setActionListeners (ActionListener lis) {
		jbVer.addActionListener(lis);
		jbModif.addActionListener(lis);
		jbEliminar.addActionListener(lis);
		jbSalir.addActionListener(lis);
		jbBuscar.addActionListener(lis);
	}

	private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		this.dispose();
	}

	public void repaint() {
		super.repaint();
	}

	void mostrarJTFNombre() {
		this.getJPBuscador().remove(getJTFDni());
		this.getJPBuscador().remove(getJLDni());

		this.getJPBuscador().add(getJTFNombre(),null);
		this.getJPBuscador().add(getJTFApellido(),null);
		this.getJPBuscador().add(getJLNombre(),null);
		this.getJPBuscador().add(getJLApellido(),null);

		this.repaint();
		this.show();
	}

	void mostrarJTFDni() {
		this.getJPBuscador().remove(getJTFNombre());
		this.getJPBuscador().remove(getJTFApellido());
		this.getJPBuscador().remove(getJLNombre());
		this.getJPBuscador().remove(getJLApellido());

		this.getJPBuscador().add(getJTFDni(),null);
		this.getJPBuscador().add(getJLDni(),null);

		this.repaint();
		this.show();
	}

	public void actualizarTabla(){
		jpPpal.remove(getJPDatos());
		jpDatos = null;
		tabla = null;
		modTabla = null;
		jspDatos = null;
		jpPpal.add(getJPDatos(), null);
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 400, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 300, Short.MAX_VALUE)
		);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	// End of variables declaration//GEN-END:variables

}
