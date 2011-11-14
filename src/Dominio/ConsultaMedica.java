package Dominio;

import java.sql.SQLException;

import ABMConsultaMedica.ControlConsultaMedica;

public class ConsultaMedica {
	private static int cantAtributos = 5;
	private ControlConsultaMedica cCM;
	private int idConsulta;
	private int idMedico;
	private int idPaciente;
	private String fecha;
	private String diagnostico;

	public ConsultaMedica() throws SQLException {
		this.cCM = new ControlConsultaMedica("consulta",cantAtributos,this);
	}

	public ConsultaMedica (int idc, int idm, int idp, String f, String dx) {
		idConsulta = idc;
		idMedico = idm;
		idPaciente = idp;
		fecha = f;
		diagnostico = dx;
	}

	public int getIdConsulta() {
		return(idConsulta);
	}

	public int getIdMedico() {
		// ToDo: HardCode idMedico until get Medico ABM working
		// return(idMedico);
		return (idMedico);//Ahora almacena el dni del Medico que realiza la consulta
	}

	public int getIdPaciente() {
		return(idPaciente);
	}

	public String getFecha() {
		// @ToDo: Dejar la fecha en el formate que puse debajo
		// return(fecha.replace('/', '-'));
		return "2011-12-12";
	}

	public String getDiagnostico() {
		return(diagnostico);
	}

	public ControlConsultaMedica getCCM() {
		return(cCM);
	}

	public void setIdConsulta(int idConsulta) {
		this.idConsulta = idConsulta;
	}

	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public void setCCM(ControlConsultaMedica cCM) {
		this.cCM = cCM;
	}

}
