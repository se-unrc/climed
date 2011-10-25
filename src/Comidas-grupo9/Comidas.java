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
	
	public String getDescipcion(){
		return descripcion;
	}
}
