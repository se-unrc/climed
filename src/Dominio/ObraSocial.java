
public class ObraSocial {
	private int numero;
	private String nombre, direccion, telefono, cuit;
	
	public ObraSocial(int num, String nom, String dir, String tel, String cuit){
		numero = num;
		nombre = nom;
		direccion = dir;
		telefono = tel;
		this.cuit = cuit;
	}
	
	public int getNumero(){
		return numero;
	}
	
	public void setNumero(int num){
		numero = num;
	}
	
	public String getNombre(){
		return nombre;
	}
	
	public void setNombre(String nom){
		nombre = nom;
	}
	
	public String getDireccion(){
		return direccion;
	}
	
	public void setDireccion(String dir){
		direccion = dir;
	}
	
	public String getTelefono(){
		return telefono;
	}
	
	public void setTelefono(String tel){
		telefono = tel;
	}
	
	public String getCuit(){
		return cuit;
	}
	
	public void setCuit(String cuit){
		this.cuit = cuit;
	}
	
	public String toString(){
		return numero+" - "+nombre;
	}
}
