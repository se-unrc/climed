import java.sql.*;
/**
 * @author grupo 9
 */
 
public class ABMComidas{

	private static ControlConexion gestor = new ControlConexion();

	public static void alta(Comidas x) throws SQLException{
		String query=	"insert into comidas (nombre, descripcion) values ('" + x.getNombre() +"', '" + x.getDescripcion() + "');";
		PreparedStatement statement = Conexion.getInstancia().prepareStatement(query);
		gestor.finalizarInsercion(statement);
	}

	public static void baja(Comidas x) throws SQLException{
		String query=	"delete from comidas where (nombre='" + x.getNombre()+"');";
		gestor.eliminar(query);
	}

	public  static void modificar(Comidas x, String campo, String nuevoValor) throws SQLException{
		String query=	"update comidas set "+ campo + " = '" + nuevoValor + "' where  nombre ='" + x.getNombre() +"';";
		gestor.editar(query);
	}
}
