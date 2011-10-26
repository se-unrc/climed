package consultaMedica;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FormAltaModifCM extends JDialog {

	private JButton jbGrabar;   
	private JButton jbCancelar;
	private JLabel jlFecha;
	private JLabel jlIdMedico;
	private JLabel jlIdPaciente;
	private JTextArea jtaDiagnostico;
	private JTextField jtfFecha;
	private JTextField jtfIdPaciente;
	private JTextField jtfIdMedico;
	private ConsultaMedica cM;

	private JPanel jpPpal;
	private JPanel jpDatos;
	private JPanel jpDatosD;

	Color fondoPaneles = new Color(168,168,168);

	public FormAltaModifCM(Frame parent, boolean modal) {
		super(parent,modal);
		inicializar();
		Calendar f = new GregorianCalendar();
		String currentDay = Integer.toString(f.get(Calendar.DATE)) + "/" + Integer.toString(f.get(Calendar.MONTH)) + "/" + Integer.toString(f.get(Calendar.YEAR));
		this.jtfFecha.setText(currentDay);
		this.jtfFecha.setEditable(false);
		this.setTitle("Nueva Consulta Médica");
	}

	public FormAltaModifCM(Frame parent, boolean modal, ConsultaMedica cM) {
		super(parent,modal);
		this.cM = cM;
		inicializar();
		this.jtfIdMedico.setText(String.valueOf(cM.getIdMedico()));
		this.jtfIdPaciente.setText(String.valueOf(cM.getIdPaciente()));
		this.jtaDiagnostico.setText(cM.getDiagnostico());
		this.jtfFecha.setText(cM.getFecha());
		this.jtfFecha.setEditable(false);
		this.setTitle("Modificar Consulta Médica");
	}

	private void inicializar() {
		this.setLocation(150,150);
		this.setSize(500,500);
		this.setResizable(false);
		this.setContentPane(getJPPpal());
		this.getContentPane().setName("GUIGestionarMedico");
	}

	public JPanel getJPPpal() {
		if (jpPpal == null) {
			jpPpal = new JPanel();
			jpPpal.setLayout(null);
			jpPpal.add(getJBGrabar(), null);
			jpPpal.add(getJBCancelar(), null);
			jpPpal.add(getJPDatos());
			jpPpal.add(getJPDatosD());
		}
		return jpPpal;
	}

	private JPanel getJPDatos() {
		if (jpDatos == null) {
			jpDatos = new JPanel();
			jpDatos.setLayout(null);
			jpDatos.setBounds(new Rectangle(30,30,430,180));
			jpDatos.add(getLBFecha());
			jpDatos.add(getLBIdPaciente());
			jpDatos.add(getLBIdMedico());
			jpDatos.add(getJTFIdMedico());
			jpDatos.add(getJTFIdPaciente());
			jpDatos.add(getJTFFecha());
			jpDatos.setBackground(fondoPaneles);
			jpDatos.setBorder(javax.swing.BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1,1,1,1, new java.awt.Color(254, 107, 3)), "Datos de la Consulta Médica", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
		}
		return jpDatos;
	}

	private JPanel getJPDatosD() {
		if (jpDatosD == null) {
			jpDatosD = new JPanel();
			jpDatosD.setLayout(null);
			jpDatosD.setBounds(new Rectangle(30,250,430,150));
			jpDatosD.setBorder(javax.swing.BorderFactory.createTitledBorder(BorderFactory.createMatteBorder(1,1,1,1, new java.awt.Color(254, 107, 3)), "Diagnóstico", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
			jpDatosD.add(getJTFDiagnostico());
			jpDatosD.setBackground(fondoPaneles);
		}
		return jpDatosD;
	}

	public JTextArea getJTFDiagnostico() {
		if (jtaDiagnostico == null) {
			jtaDiagnostico = new JTextArea();
			jtaDiagnostico.setBounds(new Rectangle(30,30,370,100));
		}
		return jtaDiagnostico;
	}

	public JTextField getJTFFecha() {
		if (jtfFecha == null) {
			jtfFecha = new JTextField();
			jtfFecha.setBounds(new Rectangle(330,25,82,22));
		}
		return jtfFecha;
	}

	public JTextField getJTFIdMedico() {
		if (jtfIdMedico == null) {
			jtfIdMedico = new JTextField();
			jtfIdMedico.setBounds(new Rectangle(100,85,180,22));
		}
		return jtfIdMedico;
	}

	public JTextField getJTFIdPaciente() {
		if (jtfIdPaciente == null) {
			jtfIdPaciente = new JTextField();
			jtfIdPaciente.setBounds(new Rectangle(100,120,180,22));
		}
		return jtfIdPaciente;
	}

	public JLabel getLBIdPaciente() {
		if (jlIdPaciente == null) {
			jlIdPaciente = new JLabel("DNI Paciente:");
			jlIdPaciente.setBounds(new Rectangle(15,120,180,22));
		}
		return jlIdPaciente;
	}

	public JLabel getLBIdMedico() {
		if (jlIdMedico == null) {
			jlIdMedico = new JLabel("DNI Médico:");
			jlIdMedico.setBounds(new Rectangle(15,85,180,22));
		}
		return jlIdMedico;
	}

	public JLabel getLBFecha() {
		if (jlFecha == null) {
			jlFecha = new JLabel("Fecha:");
			jlFecha.setBounds(new Rectangle(280,25,180,22));
		}
		return jlFecha;
	}

	public JButton getJBGrabar() {
		if (jbGrabar == null) {
			jbGrabar = new JButton();
			jbGrabar.setName("Grabar");
			jbGrabar.setText("Grabar");
			jbGrabar.setBounds(new Rectangle(260,420,100,25));
		}
		return jbGrabar;
	}

	public JButton getJBCancelar() {
		if (jbCancelar == null) {
			jbCancelar = new JButton();
			jbCancelar.setName("Cancelar");
			jbCancelar.setText("Cancelar");
			jbCancelar.setBounds(new Rectangle(120,420,100,25));
		}
		return jbCancelar;
	}

	public void setActionListeners (ActionListener lis) {
		jbCancelar.addActionListener(lis);
		jbGrabar.addActionListener(lis);
	}

}
