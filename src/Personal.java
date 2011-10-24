public class Personal extends Persona {
	private Integer id;
	
	public Personal() {
		super();
		id = -1;
	}
	
	public Personal(Integer id, Persona pers){
		super(pers);
		this.id = id;
	}
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
