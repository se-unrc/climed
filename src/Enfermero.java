
public class Enfermero extends Personal {
	private Tcategoria categoria;
	
	public Enfermero(Tcategoria categoria) {
		super();
		this.categoria = categoria;
	}
	public Enfermero(Integer id, Persona pers, Tcategoria categoria){
		super(id, pers);
		this.categoria = categoria;
	}
	
	public Tcategoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Tcategoria categoria) {
		this.categoria = categoria;
	}
}
