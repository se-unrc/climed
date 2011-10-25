package Dominio;

public class Convenio {
	private String medico = "";
	private String obraSocial = "";
	private float cobertura = 0;
	
	
	public Convenio(String medico, String obraSocial, float cobertura){
		this.medico = medico;
		this.obraSocial = obraSocial;
		this.cobertura = cobertura;
	}


	public String getMedico() {
		return medico;
	}


	public void setMedico(String medico) {
		this.medico = medico;
	}


	public String getObraSocial() {
		return obraSocial;
	}


	public void setObraSocial(String obraSocial) {
		this.obraSocial = obraSocial;
	}


	public float getCobertura() {
		return cobertura;
	}


	public void setCobertura(float cobertura) {
		this.cobertura = cobertura;
	}
}
