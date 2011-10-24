
public class HorasExtras {
	private int horas;
	private String fecha;
	
	public HorasExtras() {
		horas = 0;
		fecha = "";
	}
	
	public HorasExtras(int hora, String fecha){
		this.horas = hora;
		this.fecha = fecha;
	}
	
	public void setHoras(int horas) {
		this.horas = horas;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getHoras() {
		return horas;
	}
	public String getFecha() {
		return fecha;
	}
}
