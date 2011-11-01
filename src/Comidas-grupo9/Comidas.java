/**
 * @author grupo 9
 */
 
public class Comidas{
	
	private String nombre;
	private String descripcion;
	
	public Comidas(String comida, String desc){
		nombre=comida;
		descripcion=desc;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
	public void setNombre(String x){
		nombre=x;
	}
	
	public void setDescripcion(String x){
		descripcion=x;
	}
}
