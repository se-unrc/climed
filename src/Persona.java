public class Persona {
	private String nombre;
	private String apellido;
	private String DNI;
	private String direccion;
	private String telefono;
	
	public Persona() {
		nombre = "";
		apellido ="";
		DNI = "";
		direccion = "";
		telefono = "";
	}
	public Persona(String nombre, String apellido, String dni, String direccion, String telefono) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.DNI = dni;
		this.telefono = telefono;
	}
	
	public Persona(Persona pers){
		this.nombre = pers.getNombre();
		this.apellido = pers.getApellido();
		this.direccion = pers.getDireccion();
		this.DNI = pers.getDNI();
		this.telefono = pers.getTelefono();
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public String getDNI() {
		return DNI;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public void setDNI(String dNI) {
		DNI = dNI;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
