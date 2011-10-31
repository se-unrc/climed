package Dominio;

public class Habitacion {

	private int nroHabitacion;
	private String fechaInternacion;
	private int nroPiso;


	public Habitacion(int numHab, String fechaInt, int nPiso ){
		nroHabitacion = numHab;
		fechaInternacion = fechaInt;
		nroPiso = nPiso;

	}

	public int getNroHabitacion(){
		return nroHabitacion;

	}

	public void setNroHabitacion(int numHab){
		nroHabitacion = numHab;
	}

	public String getFechaInternacio(){
		return fechaInternacion;
	}

	public void setFechaInterncion(String fechaInt){
		fechaInternacion = fechaInt;
	}

	public int getNroPiso (){
		return nroPiso;
	}

	public void setNroPiso(int numPiso){
		nroPiso = numPiso;
	}

}
