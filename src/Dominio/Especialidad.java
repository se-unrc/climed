package Dominio;

public class Especialidad {
	private String nombre;
	private String id;
	
	public Especialidad(String nombre, String id){
		this.nombre=nombre;
		this.id=id;		
	}
	
	public void setNombre (String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public void setId (String id) {
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
}
