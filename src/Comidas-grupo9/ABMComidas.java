import java.sql.*;

public class ABMComidas{

	private static GestionBD gestor = new GestionBD();

	public static void alta(Comidas x) throws SQLException{
			gestor.insertar(x.getNombre(),x.getDescipcion());
	}

	public static void baja(Comidas x) throws SQLException{
			gestor.borrar(x.getNombre());
	}

	public  static void modificar(Comidas x, String campo, String nuevoValor) throws SQLException{	
			gestor.modificar(x.getNombre(), campo, nuevoValor);
	}
}
