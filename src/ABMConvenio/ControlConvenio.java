package ABMConvenio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import Dominio.Convenio;


public class ControlConvenio {

	private Connection conexion = null;
	private Statement st;

	public ControlConvenio(Connection conexion, Statement st){
		this.conexion = conexion;
		this.st = st;
	}

	public boolean existeConvenio(Convenio convenio) {
		boolean existe = false;
		Collection ConvenioCol;
		try {
			ConvenioCol = obtenerConvenio(convenio);
			if (ConvenioCol != null)
				if (ConvenioCol.size()>0) {
					existe=true;
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existe; 
	}

	public Collection obtenerConvenios(){
		Collection retorno = new HashSet();
		ResultSet temporal;
		String medico,obraSocial = "";
		Float cobertura;
		int nro;
		String query = "Select * From convenio";
		PreparedStatement state;
		try {
			state = conexion.prepareStatement(query);
			temporal = state.executeQuery();
			while(temporal.next()){
				nro = temporal.getInt(1);
				medico = temporal.getString(2);
				obraSocial = temporal.getString(3);
				cobertura = temporal.getFloat(4);
				Convenio conv = new Convenio(nro, medico, obraSocial, cobertura);
				retorno.add(conv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}
	public Collection obtenerConvenio(Convenio convenio) throws Exception{
		return buscarConvenio(convenio);
	}


	public void insertar(Convenio convenio){
		String query = "Insert into convenio (medico, obra_social, cobertura) values ('"+convenio.getMedico()+"','"+convenio.getObraSocial()+"',"+convenio.getCobertura()+")";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void modificar(Convenio convenioNuevo, int nro){
		String query = "Update convenio set Medico= '"+convenioNuevo.getMedico()+"', obra_social= '"
		+convenioNuevo.getObraSocial()+"', Cobertura="+convenioNuevo.getCobertura() +
		"where id = '"+nro+"'";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void eliminar(Convenio convenio){
		String query = "Delete from convenio where id ='"+convenio.getNro()+"'";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public Collection buscarConvenio(Convenio convenio){
		Collection retorno = new HashSet();
		ResultSet temporal;
		String medico,obraSocial;
		Float cobertura;
		int nro;
		String query = "Select * From convenio Where medico = '"+convenio.getMedico()+"' and obra_social= '"+convenio.getObraSocial()+"'";
		PreparedStatement state;
		try {
			state = conexion.prepareStatement(query);
			temporal = state.executeQuery();
			if (temporal != null){
				while(temporal.next()){
					nro = temporal.getInt(1);
					medico = temporal.getString(2);
					obraSocial = temporal.getString(3);
					cobertura = temporal.getFloat(4);
					Convenio conv = new Convenio(nro, medico, obraSocial, cobertura);
					retorno.add(conv);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}

	public void cargarTabla(TablePanel p) {
		try{
			Collection c = this.obtenerConvenios();
			Iterator iter = c.iterator();
			while (iter.hasNext()) {
				Convenio convenio =  (Convenio) iter.next();
				String[] newRow = {convenio.getNro()+"",convenio.getMedico(), convenio.getObraSocial(), convenio.getCobertura()+""};
				p.agregarFila(newRow);
			}
		}catch (Exception e){
			System.out.println("----Fallo la Carga de la Tabla---");
			System.out.println("Excepcion lanzada : " + e.getMessage());
		}
	}	
	
	public int obtenerUltimoConvenio(){
		ResultSet temporal;
		int resultado = -1;
		String query = "SELECT id FROM convenio order by id DESC";
		PreparedStatement state;
		try {
			state = conexion.prepareStatement(query);
			temporal = state.executeQuery();
			while(temporal.next()){
				resultado = temporal.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

}
