package Conexion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author grupo1
 */
public class ControlConexion {
    
    private Conexion conn;
    
    public ControlConexion(){
    }
    
    public ResultSet ejecutarSentencia(String sentencia){
        ResultSet res = null;
        try {
            Statement sent = Conexion.getInstancia().createStatement();
            res = sent.executeQuery(sentencia);
        }
        catch (Exception ex){
            ex.printStackTrace();
        } 
        return res;
    }

    /**
     * Metodo para editar contenido
     * @param update Sentencia SQL pura (Ej. "UPDATE medidamentos set nombre = 'Paracetamol' where id = 10")
     *
     * Ej.:
     *
     * conexion.editar("UPDATE climed.medicamento set nombre = 'MATRIX GRIP x 20 Comprimidos' where id = 10");
     */
    public void editar(String update){
        try {
            Statement stm = Conexion.getInstancia().createStatement();
            stm.executeUpdate(update);
        }
        catch  (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Metodo para insertar valores en la tabla
     * @param tabla nombre de la tabla en la cual se va a insertar
     * @param columnas nombre de las columnas en las cuales se va a insertar
     * @return PreparedStatement, luego con este objeto se deben hacer los set's:
     *
     * Ej:
     * Base de datos: climed
     * Tabla: medicamento
     * Columnas de la tabla: id (integer) y nombre (text)
     *
        String[] columnas = {"id", "nombre"};
        PreparedStatement insertar = conexion.prepararParaInsertar("climed.medicamento", columnas);
        insertar.setInt(1, 3); //Se setea el valor 3 en id
        insertar.setString(2, "JARABE PARA LA TOS"); //se setea JARABE PARA LA TOS en nombre
        conexion.finalizarInsercion(insertar); //Ejecuta la insersion
     */
    public PreparedStatement prepararParaInsertar (String tabla, String[] columnas){
        PreparedStatement stmt = null;
        try {
            String valores = "";
            String nombreColumas = "";
            int indice = 0;
            while (indice != columnas.length){ //si esto no funiona ver otro conectDB y restaurar este metodo
                valores += "?,";
                nombreColumas = (nombreColumas.equals(""))? columnas[indice] : nombreColumas+", "+columnas[indice];
                indice++;
            }
            valores = (String)valores.subSequence(0, valores.length()-1);

            String comando = "INSERT INTO "+tabla+" (" + nombreColumas +") VALUES ("+valores+")";
            stmt = Conexion.getInstancia().prepareStatement(comando);
        }
        catch (Exception ex){
            System.err.println("ERROR EN prepararParaInsertar");
            ex.printStackTrace();
        }
        return stmt;
    }

    /**
     * Finaliza la insercion, antes debe utilizarce prepararParaInsertar
     * @param stm objeto que tiene seteados los parametros
     */
    public void finalizarInsercion(PreparedStatement stm){
        try {
            stm.executeUpdate();
        }
        catch  (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Elimina un registro de una tabla (debe tener un unico campo clave)
     * @param tabla nombre de la tabla
     * @param columnaID nombre del campo que es clave
     * @param datoABorrar valor que se va a eliminar
     *
     * EJ.:
     * conexion.eliminarValor("climed.medicamento", "id", "3");
     */
    public void eliminarValor(String tabla, String columnaID, String datoABorrar){
        try {
            Statement stmt = Conexion.getInstancia().createStatement();
            stmt.executeUpdate("DELETE From "+tabla+" Where "+columnaID+" = "+datoABorrar+";");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    /**
     * Metodo para eliminar pasando como parametro una sentencia
     * @param sentencia Sentencia pura SQL (Ej. DELETE FROM Medicamentos where id = 1)
     *
     * Ej.:
     *
     * conexion.eliminar("DELETE FROM climed.medicamento where id = 4 and nombre = 'ATOMO'");
     */
    public void eliminar(String sentencia){
        try {
            Statement stmt = Conexion.getInstancia().createStatement();
            stmt.executeUpdate(sentencia);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
    
}
