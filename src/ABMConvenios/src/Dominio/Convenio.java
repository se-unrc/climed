package Dominio;

public class Convenio {
	
	private int nro;
	
	private String medico;
	
	private String obraSocial;
	
	private float cobertura;
	
	
	public Convenio(int nro, String medico, String obraSocial, float cobertura){
		this.nro = nro;
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


	public int getNro() {
		return nro;
	}


	public void setNro(int nro) {
		this.nro = nro;
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
