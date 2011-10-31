package ABMConvenio;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

public class GuiABMConvenio extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel panelNorte = null;
	private JButton botonAlta = null;
	private JButton botonBaja = null;
	private JButton botonModificar = null;
	private JPanel panelCentro = null;
	private TablePanel Tabla = null;
	private JPanel PanelSur = null;
	private JButton botonSalir = null;
	private JPanel jContentePane = null;
	private JPanel panelBuscar = null;
	private JLabel etiquetaBuscar = null;
	private JTextField jTBuscar = null;

	/**
	 * @param owner
	 */
	public GuiABMConvenio(JFrame owner) {
		this.dialogInit();
		initialize();
		this.pack();
		this.setResizable(false); 
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public void hacerVisible(){
		this.setVisible(true);
		this.setModal(true);
	}
	
	/** Agrega un ActionListener al boton salir
	 * @param lis representa un ActionListener
	 */
	public void setActionListeners(ActionListener lis) {
		botonSalir.addActionListener(lis);
		botonAlta.addActionListener(lis);
		botonBaja.addActionListener(lis);
		botonModificar.addActionListener(lis);
	}

	public void setKeyListener(KeyListener lis) {
		getJTBuscar().addKeyListener(lis);	  
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(567, 228);
		this.setContentPane(getJContentePane());
		this.setTitle("Gestion de Convenios");
	}

	/**
	 * This method initializes panelNorte	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(java.awt.FlowLayout.LEFT);
			flowLayout.setHgap(15);
			panelNorte = new JPanel();
			panelNorte.setLayout(flowLayout);
			panelNorte.setBorder(BorderFactory.createTitledBorder(null, " Opciones ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			panelNorte.add(getBotonAlta(), null);
			panelNorte.add(getBotonBaja(), null);
			panelNorte.add(getBotonModificar(), null);
			panelNorte.add(getPanelBuscar(), null);
		}
		return panelNorte;
	}

	/**
	 * This method initializes botonAlta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonAlta() {
		if (botonAlta == null) {
			botonAlta = new JButton();
			botonAlta.setText("Alta");
			botonAlta.setIcon(new ImageIcon("Iconos/Add.png"));
		}
		return botonAlta;
	}

	/**
	 * This method initializes botonBaja	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonBaja() {
		if (botonBaja == null) {
			botonBaja = new JButton();
			botonBaja.setText("Baja");
			botonBaja.setIcon(new ImageIcon("Iconos/Baja.png"));
		}
		return botonBaja;
	}

	/**
	 * This method initializes botonModificar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonModificar() {
		if (botonModificar == null) {
			botonModificar = new JButton();
			botonModificar.setText("Modificar");
			botonModificar.setIcon(new ImageIcon("Iconos/Modif.png"));
		}
		return botonModificar;
	}

	/**
	 * This method initializes panelCentro	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelCentro() {
		if (panelCentro == null) {
			panelCentro = new JPanel();
			panelCentro.setLayout(new FlowLayout());
			panelCentro.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			panelCentro.add(getTabla(), null);
		}
		return panelCentro;
	}

	/**
	 * This method initializes Tabla	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public TablePanel getTabla() {
		if (Tabla == null) {
			Tabla = new TablePanel(3);
			Tabla.setearDatos(Tabla.obtenerDatos(),obtenerNombreDeColumnas());
			Tabla.setPreferredSize(new Dimension(640, 220));
			Tabla.setearTamañoColumna("Nro.", 80);
			Tabla.setearTamañoColumna("% Cobertura", 85);
		}
		return Tabla;
	}
	
	/**
	 * Retorna un arreglo representando los nombres de las columnas de la tabla
	 * @return un arreglo con los nombres de las columnas de la tabla
	 */
	public String[] obtenerNombreDeColumnas() {
		String[] columnas = {"Nro.","Medico","Obra Social","% Cobertura"};
		return columnas;
	}
	
	/**
	 * Dado un numero retorna en un arreglo la fila correspondiente
	 * @param i numero que representa la fila requerida
	 * @return un arreglo de objetos representando la fila
	 */
	public Object [] obtenerDeFila (int i) {
		return Tabla.obtenerFila(i);
	}
	
	
	/**Agrega fila a la tabla
	 * @param arreglo cuyas posiciones representan las celdas 
	 * de la fila a agregar
	 */
	public void agregarFilaALaGrilla (String [] newData) {
		Tabla.agregarFila(newData);
	}

	/**Quita fila a la tabla
	 * @param entero que representa la posicion de la fila a borrar
	 */
	public void borrarFilaDeGrilla (int pos) {
		Tabla.borrarFila(pos);
	}

	/**Busca fila en la tabla dado su campo clave
	 * @param key campo clave por el cual se busca la fila
	 * @return entero representando la posicion de la fila hallada,
	 * si no se hallo retorna -1
	 */
	public int buscarFilaEnGrilla(String key) {
		return Tabla.buscarFila(key);
	}

	/**Modifica fila de la tabla
	 * @param pos posición de la fila a modificar
	 * @param a valor del primer campo de la fila
	 * @param b valor del segundo campo de la fila
	 */
	public void setearFila (int pos, String a, String b, String c, String d) {
		String [] aux = {a, b, c, d};
		Tabla.setearFila(pos,aux);
	}

	/**
	 * Retorna el entero correspondiente al numero de fila seleccionada
	 * @return un numero indicando el numero de fila seleccionado en la tabla
	 */
	public int obtenerFilaSeleccionada () {
		return Tabla.obtenerFilaSeleccionada();
	}

	/**
	 * Dado un numero selecciona la fila correspondiente en la tabla
	 * @param row numero de fila a seleccionar
	 */
	public void seleccionarEnGrilla(int row){
		Tabla.seleccionarFila(row);
	}

	/**
	 * Retorna si se puede o no agregar una fila
	 * @param id identificador de la fila a agregar
	 * @return True si puedo agregar la fila (no existe) False si no puedo agregarla (existe)
	 */
	public boolean puedoAgregar(String id){
		return Tabla.contieneFila(id);
	}
	
	/**
	 * This method initializes PanelSur	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelSur() {
		if (PanelSur == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(5, 0, 5, 5);
			PanelSur = new JPanel();
			PanelSur.setLayout(new GridBagLayout());
			PanelSur.add(getbotonSalir(), gridBagConstraints3);
		}
		return PanelSur;
	}

	/**
	 * This method initializes botonCancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getbotonSalir() {
		if (botonSalir == null) {
			botonSalir = new JButton();
			botonSalir.setText("Salir");
			botonSalir.setIcon(new ImageIcon("Iconos/salir.gif"));
		}
		return botonSalir;
	}

	/**
	 * This method initializes jContentePane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentePane() {
		if (jContentePane == null) {
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 0;
			gridBagConstraints31.gridy = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.anchor = GridBagConstraints.SOUTHEAST;
			gridBagConstraints2.gridy = 2;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.anchor = GridBagConstraints.CENTER;
			gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints.gridy = 0;
			jContentePane = new JPanel();
			jContentePane.setLayout(new GridBagLayout());
			jContentePane.add(getPanelNorte(), gridBagConstraints);
			jContentePane.add(getPanelSur(), gridBagConstraints2);
			jContentePane.add(getPanelCentro(), gridBagConstraints31);
		}
		return jContentePane;
	}

	/**
	 * This method initializes panelBuscar	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelBuscar() {
		if (panelBuscar == null) {
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.anchor = GridBagConstraints.WEST;
			gridBagConstraints21.insets = new Insets(5, 5, 0, 0);
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.gridy = 1;
			gridBagConstraints21.ipadx = 130;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.fill = GridBagConstraints.VERTICAL;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.insets = new Insets(5, 5, 0, 0);
			etiquetaBuscar = new JLabel();
			etiquetaBuscar.setText("Buscar (Obra Social)");
			panelBuscar = new JPanel();
			panelBuscar.setLayout(new GridBagLayout());
			panelBuscar.add(etiquetaBuscar, gridBagConstraints1);
			panelBuscar.add(getJTBuscar(), gridBagConstraints21);
		}
		return panelBuscar;
	}

	/**
	 * This method initializes jTBuscar	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJTBuscar() {
		if (jTBuscar == null) {
			jTBuscar = new JTextField();
		}
		return jTBuscar;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
