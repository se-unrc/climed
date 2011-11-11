package Dominio;

public class Matricula {
	private String numero;
	private String idMedico;
	private String idEspecialidad;
	private String fechaObtencion;
	
	public Matricula(String numero, String idMedico, String idEspecialidad, String fechaObtencion){
		this.numero=numero;
		this.idMedico=idMedico;
		this.idEspecialidad=idEspecialidad;
		this.fechaObtencion=fechaObtencion;
		
	}
	
	public void setNumero (String numero){
		this.numero = numero;
	}
	
	public String getNumero () {
		return this.numero;
	}
	
	public void setIdMedico (String idMedico){
		this.idMedico = idMedico;
	}
	
	public String getIdMedico (){
		return this.idMedico;
	}
	
	public void setIdEspecialidad (String idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	
	public String getIdEspecialidad () {
		return this.idEspecialidad;
	}
	
	public void setFechaObtencion (String fechaObtencion) {
		this.fechaObtencion = fechaObtencion;
	}
	
	public String getFechaObtencion () {
		return this.fechaObtencion;
	}
}
