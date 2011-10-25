import java.sql.*;


public class GestionBD{
	
	private Conexion conexion;
	private String query;
	Connection connection = null;

	public GestionBD(){
			connection=conexion.getInstancia();
		
	}
	
	private void crearTabla(){
		try{
			Statement statement = connection.createStatement();
			statement.executeUpdate("Create table Comidas (id serial primary key, nombre varchar(45), descripcion varchar(200));");
		}catch(SQLException e){
			System.out.println("La tabla ya existe");
		}
	}

	public void insertar (String nombre, String desc) throws SQLException{
		try{	 		
			query=	"insert into comidas (nombre, descripcion)values ('" + nombre+"', '" + desc + "');";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.executeUpdate();
		}catch(SQLException e){
			crearTabla();
		}		
	}
	
	public void borrar (String nombre) throws SQLException{
		try{	 		
			query=	"delete from comidas where (nombre='" + nombre+"');";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.executeUpdate();
		}catch(SQLException e){
			crearTabla();
		}
	}
	
	public void modificar (String valor,String campo, String nuevoValor) throws SQLException{
		try{	 		
			query=	"update comidas set "+ campo + " = '" + nuevoValor + "' where " + campo + "='" + valor+"';";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.executeUpdate();
		}catch(SQLException e){
			crearTabla();
		}
	}

	public void desconectar(){
	
		conexion.cerrarConexion();	
		
	}

}
