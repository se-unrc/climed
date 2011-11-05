package ABMPersona;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;


public class AltaPersonaGUI extends JDialog {
	
	private JLabel etiquetaId;
	private JLabel etiquetaNombre;
	private JLabel etiquetaApellido;
	private JLabel etiquetaDni;
	private JLabel etiquetaDireccion;
	private JLabel etiquetaTelefono;
	private JLabel etiquetaClase;
	private JLabel etiquetaCategoria;
	private JLabel etiquetaEspecialidad;
	private JLabel etiquetaHorasExtras;
	protected JTextField campoId;
	private JTextField campoNombre;
	private JTextField campoApellido;
	private JTextField campoDni;
	private JTextField campoDireccion;
	private JTextField campoTelefono;
	private JTextField campoCategoria;
	private JTextField campoEspecialidad;
	private JTextField campoHorasExtras;
	private JComboBox comboBoxClase;
	private JButton botonAceptar;
	private JButton botonCancelar;

	public AltaPersonaGUI() {
		
		campoId = new JTextField("");
		campoNombre = new JTextField("");
		campoApellido = new JTextField("");
		campoDni = new JTextField("");
		campoDireccion = new JTextField("");
		campoTelefono = new JTextField("");
		campoCategoria = new JTextField("");
		campoEspecialidad = new JTextField("");
		campoHorasExtras = new JTextField("");
				
		setName("jDialogAltaPersona");
        setLayout(null);
		setSize(300, 400);
		setPreferredSize(new Dimension(300, 400));
		setAlwaysOnTop(true);
        initComponents();
        
        setResizable(false);
		pack();
		setLocationRelativeTo(null);
		
        setVisible(true);
	}
	
	public void initComponents() {		
		crearEtiqueta(etiquetaId, "Id", 15);
		crearEtiqueta(etiquetaNombre, "Nombre", 45);
		crearEtiqueta(etiquetaApellido, "Apellido", 75);
		crearEtiqueta(etiquetaDni, "Dni", 105);
		crearEtiqueta(etiquetaDireccion, "Direccion", 135);
		crearEtiqueta(etiquetaTelefono, "Telefono", 165);
		crearEtiqueta(etiquetaClase, "Clase", 195);
		crearEtiqueta(etiquetaCategoria, "Categoria", 225);
		crearEtiqueta(etiquetaEspecialidad, "Especialidad", 255);
		crearEtiqueta(etiquetaHorasExtras, "HorasExtras", 285);
		
		crearCampo(campoId, "Id", 15, false);
		crearCampo(campoNombre, "Nombre", 45, true);
		crearCampo(campoApellido, "Apellido", 75, true);
		crearCampo(campoDni, "Dni", 105, true);
		crearCampo(campoDireccion, "Direccion", 135, true);
		crearCampo(campoTelefono, "Telefono", 165, true);
		crearCampo(campoCategoria, "Categoria", 225, false);
		crearCampo(campoEspecialidad, "Especialidad", 255, false);
		crearCampo(campoHorasExtras, "HorasExtras", 285, false);
		
		String items[] = {"Paciente", "Administrativo", "Enfermera", "Medico"};
		comboBoxClase = new JComboBox(items);
		comboBoxClase.setName("comboBoxClase");
		comboBoxClase.setBounds(100, 195, 125, 25);
        add(comboBoxClase);
		
		botonAceptar = new JButton();
        botonAceptar.setText("Aceptar");
        botonAceptar.setName("botonAceptar");
        botonAceptar.setBounds(40, 330, 100, 25);
        add(botonAceptar);
        
		botonCancelar = new JButton();
        botonCancelar.setText("Cancelar");
        botonCancelar.setName("botonCancelar");
        botonCancelar.setBounds(160, 330, 100, 25);
        add(botonCancelar);
	}
	
	protected void crearEtiqueta(JLabel etiqueta, String nombre, int y) {
		etiqueta = new JLabel();
		etiqueta.setText(nombre);
        etiqueta.setName("etiqueta" + nombre);
        etiqueta.setBounds(25, y, 75, 25);
        add(etiqueta);
	}

	protected void crearCampo(JTextField campo, String nombre, int y, boolean editable) {
		campo.setText("");
        campo.setName("campo" + nombre);
        campo.setEditable(editable);
        campo.setBounds(100, y, 175, 25);
        add(campo);
	}
	
	public void addActionListener(ActionListener lis){
		botonAceptar.addActionListener(lis);
		botonCancelar.addActionListener(lis);
		comboBoxClase.addActionListener(lis);
	}
	
	public void cerrar() {
        setVisible(false);
        dispose();
    }
	
	public String getId() {
		return campoId.getText();
	}
	

	public String getNombre() {
		return campoNombre.getText(); 
	}
	
	public String getApellido() {
		return campoApellido.getText();
	}

	public String getDni() {
		return campoDni.getText();
	}

	public String getDireccion() {
		return campoDireccion.getText();
	}

	public String getTelefono() {
		return campoTelefono.getText();
	}

	public String getCategoria() {
		return campoCategoria.getText();
	}

	public String getEspecialidad() {
		return campoEspecialidad.getText();
	}

	public String getHorasExtras() {
		return campoHorasExtras.getText();
	}
	
	public int getClase() {
		return comboBoxClase.getSelectedIndex();
	}
		
	public String armarInsertQuerry(String tabla) {
		String res = "INSERT INTO " + tabla + "(nombre, apellido, dni, direccion, telefono, ocupacion";
		switch (comboBoxClase.getSelectedIndex()) {
		case 0:
			res += ") VALUES ('" + getNombre() + "', '" + getApellido() + "', '" + getDni() + "', '" + getDireccion() + "','" + getTelefono() + "', 'paciente');";
			break;
		case 1:
			res += ", horasextras) VALUES ('" + getNombre() + "', '" + getApellido() + "', '" + getDni() + "', '" + getDireccion() + "','" + getTelefono() + "', 'administrativo', " + getHorasExtras() + ");";
			break;
		case 2:
			res += ", categoria) VALUES ('" + getNombre() + "', '" + getApellido() + "', '" + getDni() + "', '" + getDireccion() + "','" + getTelefono() + "', 'enfermera', " + getCategoria() + ");";
			break;
		case 3:
			res += ", especialidad) VALUES ('" + getNombre() + "', '" + getApellido() + "', '" + getDni() + "', '" + getDireccion() + "','" + getTelefono() + "', 'medico', '" + getEspecialidad() + "');";
			break;
		}
		return res;
	}
	
	public void activarCategoria(boolean editable) {
		campoCategoria.setEditable(editable);
	}

	public void activarEspecialidad(boolean editable) {
		campoEspecialidad.setEditable(editable);
	}

	public void activarHoras(boolean editable) {
		campoHorasExtras.setEditable(editable);
	}
	
		
}
