package Dominio;

public class Horario {
	
	public String horario = "";
	
	public int id_enfermera;
	
	public int piso;
	
	
	public Horario(String horario, int id_enfermera, int piso){
		this.horario = horario;
		this.id_enfermera = id_enfermera;
		this.piso = piso;
	}


	public String getHorario() {
		return horario;
	}


	public void setHorario(String horario) {
		this.horario = horario;
	}


	public int getId_enfermera() {
		return id_enfermera;
	}


	public void setId_enfermera(int id_enfermera) {
		this.id_enfermera = id_enfermera;
	}


	public int getPiso() {
		return piso;
	}


	public void setPiso(int piso) {
		this.piso = piso;
	}
	
	@Deprecated
	public String toString(){
		return ("Horario:"+this.horario+" - Piso:"+this.piso+" - Id_Enfermera:"+this.id_enfermera);
	}
}

