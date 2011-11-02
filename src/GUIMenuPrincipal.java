import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import java.awt.CardLayout;

public class GUIMenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JButton botonAdminOS = null;
	private JButton botonConvenio = null;
	private JButton botonAdminConsulta = null;
	private JPanel panelSur = null;
	private JButton botonSalir = null;
	private JMenuBar jJMenuBar = null;
	private JMenu jMenu = null;
	private JMenuItem jMenuItem = null;
	private JPanel panelGestion = null;
	private JTabbedPane jTabbedPane = null;
	private JPanel panelAdministracion = null;
	private JMenuItem jMenuItem1 = null;
	
	/**
	 * This is the default constructor
	 */
	public GUIMenuPrincipal() {
		super();
		initialize();
		this.setResizable(false); 
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(340, 224);
		this.setJMenuBar(getJJMenuBar());
		this.setContentPane(getJContentPane());
		this.setTitle("Clinica Medica ''Salud Total''");
	}
	
	public void setActionListeners(ActionListener lis){
		botonAdminConsulta.addActionListener(lis);
		botonAdminOS.addActionListener(lis);
		botonConvenio.addActionListener(lis);
		botonSalir.addActionListener(lis);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelSur(), BorderLayout.SOUTH);
			jContentPane.add(getJTabbedPane(), BorderLayout.NORTH);
		}
		return jContentPane;
	}

	/**
	 * This method initializes botonAdminOS	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonAdminOS() {
		if (botonAdminOS == null) {
			botonAdminOS = new JButton();
			botonAdminOS.setText("Obra Social");
			botonAdminOS.setName("botonAdminOS");
		}
		return botonAdminOS;
	}

	/**
	 * This method initializes botonConvenio	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonConvenio() {
		if (botonConvenio == null) {
			botonConvenio = new JButton();
			botonConvenio.setText("Convenio");
		}
		return botonConvenio;
	}

	/**
	 * This method initializes botonAdminConsulta	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonAdminConsulta() {
		if (botonAdminConsulta == null) {
			botonAdminConsulta = new JButton();
			botonAdminConsulta.setText("Consulta");
		}
		return botonAdminConsulta;
	}

	/**
	 * This method initializes panelSur	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelSur() {
		if (panelSur == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(java.awt.FlowLayout.RIGHT);
			panelSur = new JPanel();
			panelSur.setBorder(BorderFactory.createTitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			panelSur.setLayout(flowLayout);
			panelSur.add(getBotonSalir(), null);
		}
		return panelSur;
	}

	/**
	 * This method initializes botonSalir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonSalir() {
		if (botonSalir == null) {
			botonSalir = new JButton();
			botonSalir.setText("Salir");
			botonSalir.setIcon(new ImageIcon("Iconos/salir.gif"));
		}
		return botonSalir;
	}
	
	/**
	 * This method initializes jJMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJJMenuBar() {
		if (jJMenuBar == null) {
			jJMenuBar = new JMenuBar();
			jJMenuBar.setPreferredSize(new Dimension(50, 17));
			jJMenuBar.add(getJMenu());
		}
		return jJMenuBar;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJMenu() {
		if (jMenu == null) {
			jMenu = new JMenu();
			jMenu.setText("Archivo");
			jMenu.add(getJMenuItem());
			jMenu.add(getJMenuItem1());
		}
		return jMenu;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem() {
		if (jMenuItem == null) {
			jMenuItem = new JMenuItem();
			jMenuItem.setText("Informacion");
		}
		return jMenuItem;
	}

	/**
	 * This method initializes panelGestion	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelGestion() {
		if (panelGestion == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints.gridy = 0;
			panelGestion = new JPanel();
			panelGestion.setLayout(new GridBagLayout());
			panelGestion.add(getBotonAdminOS(), gridBagConstraints);
		}
		return panelGestion;
	}

	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.addTab("Gestiones", null, getPanelGestion(), null);
			jTabbedPane.addTab("Administraciones", null, getPanelAdministracion(), null);
		}
		return jTabbedPane;
	}

	/**
	 * This method initializes panelAdministracion	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelAdministracion() {
		if (panelAdministracion == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints2.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints1.gridy = 0;
			panelAdministracion = new JPanel();
			panelAdministracion.setLayout(new GridBagLayout());
			panelAdministracion.add(getBotonAdminConsulta(), gridBagConstraints1);
			panelAdministracion.add(getBotonConvenio(), gridBagConstraints2);
		}
		return panelAdministracion;
	}

	/**
	 * This method initializes jMenuItem1	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMenuItem1() {
		if (jMenuItem1 == null) {
			jMenuItem1 = new JMenuItem();
			jMenuItem1.setText("Salir");
		}
		return jMenuItem1;
	}

	public static void main(String [] args){
		GUIMenuPrincipal guiPrincipal = new GUIMenuPrincipal();
		guiPrincipal.setVisible(true);
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
