import java.util.LinkedList;


public class PersonalAdministrativo extends Personal {
	private LinkedList<HorasExtras> horas; 
	
	public PersonalAdministrativo() {
		super();
		horas = new LinkedList<HorasExtras>();
	}
	
	public LinkedList<HorasExtras> getHoras() {
		return horas;
	}
	
	public void setHoras(LinkedList<HorasExtras> horas) {
		this.horas = horas;
	}
	
	public void addHoras(HorasExtras hora){
		horas.add(hora);
	}
	
	public HorasExtras popHoras(){
		HorasExtras ret = horas.getFirst();
		horas.removeFirst();
		return ret;
	}
}
