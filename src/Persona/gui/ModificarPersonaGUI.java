package gui;

public class ModificarPersonaGUI extends AltaPersonaGUI{
	
	public ModificarPersonaGUI() {
		super();
		campoId.setEditable(true);
		campoId.setToolTipText("Ingrese el ID de la persona a modificar.");
	}
	
	public String armarModificarQuerry(String tabla) {
		String res = "UPDATE " + tabla + " SET nombre = '" + getNombre() + "', apellido = '" + getApellido() + "', dni = '" + getDni() + "', direccion = '" + getDireccion() + "', telefono = '" + getTelefono() + "', ";
		switch (getClase()) {
		case 0:
			res += "ocupacion = 'paciente', categoria = default, especialidad = '" + getEspecialidad() + "', horasextras = default";
			break;
		case 1:
			res += "ocupacion = 'administrativo', categoria = default, especialidad = '" + getEspecialidad() + "', horasextras = " + getHorasExtras();
			break;
		case 2:
			res += "ocupacion = 'enfermera', categoria = " + getCategoria() + ", especialidad = '" + getEspecialidad() + "', horasextras = default";
			break;
		case 3:
			res += "ocupacion = 'medico', categoria = default , especialidad = '" + getEspecialidad() + "', horasextras = default";
			break;
		}
		res += " WHERE identificador = " + getId() + " AND borrado = false;";
		return res;
	}
	
}
