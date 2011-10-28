package Interfaces;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GuiAltaConvenio extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelNorte = null;
	private JComboBox ComboBoxObraSocial = null;
	private JComboBox ComboBoxMedico = null;
	private JLabel EtiquetaObraSocial = null;
	private JLabel EtiquetaMedico = null;
	private JLabel EtiquetaCobertura = null;
	private JTextField TextFieldCobertura = null;
	private JPanel panelSur = null;
	private JButton botonAceptar = null;
	private JButton botonCancelar = null;
	private JLabel etiquetaNro = null;
	private JTextField jTextFieldNro = null;
	
	/**
	 * @param owner
	 */
	public GuiAltaConvenio(JDialog owner) {
		super(owner);
		initialize();
		this.pack();
		this.setResizable(false); 
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public GuiAltaConvenio(JDialog owner, String titulo) {
		super(owner);
		this.setSize(376, 240);
		this.setTitle(titulo);
		this.setContentPane(getJContentPane());
		this.pack();
		this.setResizable(false); 
		this.setLocationRelativeTo(null); 
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
	public void hacerVisible(){
		this.setVisible(true);
		this.setModal(true);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(376, 240);
		this.setTitle("Alta de Convenio");
		this.setContentPane(getJContentPane());
	}
	
	/** Agrega un ActionListener al boton salir
	 * @param lis representa un ActionListener
	 */
	public void setActionListeners(ActionListener lis) {
		botonCancelar.addActionListener(lis);
		botonAceptar.addActionListener(lis);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.fill = GridBagConstraints.NONE;
			gridBagConstraints9.anchor = GridBagConstraints.SOUTHEAST;
			gridBagConstraints9.gridy = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.fill = GridBagConstraints.BOTH;
			gridBagConstraints6.ipadx = 100;
			gridBagConstraints6.ipady = 20;
			gridBagConstraints6.gridy = 0;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getPanelNorte(), gridBagConstraints6);
			jContentPane.add(getPanelSur(), gridBagConstraints9);
		}
		return jContentPane;
	}

	/**
	 * This method initializes panelNorte	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelNorte() {
		if (panelNorte == null) {
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints21.gridy = 0;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.anchor = GridBagConstraints.WEST;
			gridBagConstraints21.insets = new Insets(5, 9, 5, 5);
			gridBagConstraints21.ipadx = 70;
			gridBagConstraints21.gridx = 2;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 1;
			gridBagConstraints11.anchor = GridBagConstraints.WEST;
			gridBagConstraints11.insets = new Insets(3, 6, 0, 0);
			gridBagConstraints11.gridy = 0;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = 3;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.ipadx = 80;
			gridBagConstraints5.insets = new Insets(5, 9, 5, 0);
			gridBagConstraints5.gridx = 2;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.insets = new Insets(3, 6, 3, 0);
			gridBagConstraints4.gridy = 3;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.insets = new Insets(3, 6, 0, 0);
			gridBagConstraints3.gridy = 2;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.insets = new Insets(3, 6, 0, 0);
			gridBagConstraints2.gridy = 1;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 2;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.insets = new Insets(5, 9, 5, 5);
			gridBagConstraints1.gridx = 2;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.insets = new Insets(5, 9, 5, 5);
			gridBagConstraints.gridx = 2;
			panelNorte = new JPanel();
			panelNorte.setLayout(new GridBagLayout());
			panelNorte.setBorder(BorderFactory.createTitledBorder(null, " Convenio ", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			panelNorte.add(getComboBoxObraSocial(), gridBagConstraints);
			panelNorte.add(getComboBoxMedico(), gridBagConstraints1);
			panelNorte.add(getEtiquetaObraSocial(), gridBagConstraints2);
			panelNorte.add(getEtiquetaMedico(), gridBagConstraints3);
			panelNorte.add(getEtiquetaCobertura(), gridBagConstraints4);
			panelNorte.add(getTextFieldCobertura(), gridBagConstraints5);
			panelNorte.add(getEtiquetaNro(), gridBagConstraints11);
			panelNorte.add(getJTextFieldNro(), gridBagConstraints21);
		}
		return panelNorte;
	}

	/**
	 * This method initializes ComboBoxObraSocial	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getComboBoxObraSocial() {
		if (ComboBoxObraSocial == null) {
			ComboBoxObraSocial = new JComboBox();
		}
		return ComboBoxObraSocial;
	}

	/**
	 * This method initializes ComboBoxMedico	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getComboBoxMedico() {
		if (ComboBoxMedico == null) {
			ComboBoxMedico = new JComboBox();
		}
		return ComboBoxMedico;
	}

	/**
	 * This method initializes EtiquetaObraSocial	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getEtiquetaObraSocial() {
		if (EtiquetaObraSocial == null) {
			EtiquetaObraSocial = new JLabel();
			EtiquetaObraSocial.setText("Obra Social");
		}
		return EtiquetaObraSocial;
	}

	/**
	 * This method initializes EtiquetaMedico	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getEtiquetaMedico() {
		if (EtiquetaMedico == null) {
			EtiquetaMedico = new JLabel();
			EtiquetaMedico.setText("Medico");
		}
		return EtiquetaMedico;
	}

	/**
	 * This method initializes EtiquetaCobertura	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getEtiquetaCobertura() {
		if (EtiquetaCobertura == null) {
			EtiquetaCobertura = new JLabel();
			EtiquetaCobertura.setText("% Cobertura");
		}
		return EtiquetaCobertura;
	}

	/**
	 * This method initializes TextFieldCobertura	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextFieldCobertura() {
		if (TextFieldCobertura == null) {
			TextFieldCobertura = new JTextField();
		}
		return TextFieldCobertura;
	}
	
	/**
	 * This method initializes panelSur	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelSur() {
		if (panelSur == null) {
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 1;
			gridBagConstraints8.insets = new Insets(6, 5, 5, 5);
			gridBagConstraints8.anchor = GridBagConstraints.EAST;
			gridBagConstraints8.gridy = 0;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.insets = new Insets(6, 0, 5, 5);
			gridBagConstraints7.anchor = GridBagConstraints.EAST;
			gridBagConstraints7.gridy = 0;
			panelSur = new JPanel();
			panelSur.setLayout(new GridBagLayout());
			panelSur.add(getBotonAceptar(), gridBagConstraints7);
			panelSur.add(getBotonCancelar(), gridBagConstraints8);
		}
		return panelSur;
	}

	/**
	 * This method initializes botonAceptar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonAceptar() {
		if (botonAceptar == null) {
			botonAceptar = new JButton();
			botonAceptar.setText("Aceptar");
			botonAceptar.setIcon(new ImageIcon("Iconos/Aceptar.png"));
		}
		return botonAceptar;
	}

	/**
	 * This method initializes botonSalir	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonCancelar() {
		if (botonCancelar == null) {
			botonCancelar = new JButton();
			botonCancelar.setText("Cancelar");
			botonCancelar.setIcon(new ImageIcon("Iconos/Cancelar.png"));
		}
		return botonCancelar;
	}

	/**
	 * This method initializes etiquetaNro	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getEtiquetaNro() {
		if (etiquetaNro == null) {
			etiquetaNro = new JLabel();
			etiquetaNro.setText("Nro");
		}
		return etiquetaNro;
	}

	/**
	 * This method initializes jTextFieldNro	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getJTextFieldNro() {
		if (jTextFieldNro == null) {
			jTextFieldNro = new JTextField();
		}
		return jTextFieldNro;
	}

	public void setjTextFieldNro(JTextField jTextFieldNro) {
		this.jTextFieldNro = jTextFieldNro;
	}

}  //  @jve:decl-index=0:visual-constraint="10,10"
