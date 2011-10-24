public class Medico extends Personal {
	private int idMedico;
	
	public Medico() {
		super();
		idMedico = -1;
	}
	
	public Medico(Integer id, Persona pers, int idMedico){
		super(id, pers);
		this.idMedico=idMedico;
	}
	
	public int getIdMedico() {
		return idMedico;
	}
	
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
}
