package util_conxionBDPostgres;

/**
 *
 * @author Ezequiel Guerra, Octavio Lanzone
 */
import java.sql.*;
import java.util.Properties;
import javax.swing.table.DefaultTableModel;

public class conexionBD {

    private static conexionBD instancia = null;
    private static Connection conn;
    static{
        try {
            //Aqui lleva el nombre del driver
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * CONSTRUCTOR PRIVADO (porque es sigleton) del conector con la base de datos
     * @param host: nombre del servidor (localhost si es local)
     * @param baseDeDatos (nombre de la base de datos)
     * @param usuario (nombre de usuario)
     * @param contrasena (contraseña del usuario)
     */
    private conexionBD(String host, String baseDeDatos, String usuario, String contrasena){
        String url = "jdbc:postgresql://"+host+"/"+baseDeDatos;
        Properties props = new Properties();
        props.setProperty("user",usuario);
        props.setProperty("password",contrasena);
        //props.setProperty("ssl","false");
        try {
            conn = DriverManager.getConnection(url, props);
        } catch (Exception ex){
            System.err.println("NO SE HA REALIZADO LA CONEXION CON LA BASE DE DATOS.");
            ex.printStackTrace();
        }
    }

    /**
     * Retorna la instancia del SINGLETON para la conexion
     * @param host: nombre del servidor (localhost si es local)
     * @param baseDeDatos (nombre de la base de datos)
     * @param usuario (nombre de usuario)
     * @param contrasena (contraseña del usuario)
     * @return conexion
     */
    public static conexionBD getInstance(String host, String baseDeDatos, String usuario, String contrasena){
        if (instancia == null) {
            crearInstancia(host, baseDeDatos, usuario, contrasena);
        }
        return instancia;
    }

    /**
     * METODO PRIVADO PARA CREAR LA INSTANCIA SI NO ESTA CREADA
     * @param host: nombre del servidor (localhost si es local)
     * @param baseDeDatos (nombre de la base de datos)
     * @param usuario (nombre de usuario)
     * @param contrasena (contraseña del usuario)
     */
    private synchronized static void crearInstancia(String host, String baseDeDatos, String usuario, String contrasena){
        if (instancia == null) {
            instancia = new conexionBD(host, baseDeDatos, usuario, contrasena);
        }
    }

    /**
     * Ejecuta una sentencia directamente en la base de datos.
     * @param sentencia
     * @return ResultSet con la tabla obtenida
     *
     * EJ.:
     * OJO al poner el nombre de la tabla poner al inicio el nombre de la base de datos
     * Base de datos: climed
     * Tabla: medicamento
     * Select * from climed.medicamento
     *
            ResultSet res = conexion.ejecutarSentencia("Select * from climed.medicamento order by nombre");
            while (res.next()){ //Mientas existan elementos
                int idMedicamento = res.getInt("id"); //res.getInt("id"); obtiene el valor almacenado en la columna id de la tabla
                String nombre = res.getString("nombre");
                System.out.println(idMedicamento+": "+nombre);
            }
     */
    public ResultSet ejecutarSentencia(String sentencia){
        ResultSet res = null;
        try {
            if (conn != null){
                Statement sent = conn.createStatement();
                res = sent.executeQuery(sentencia);
            } else {
                System.err.println("CONEXION NULA, NO SE PUEDE EJECUTAR LA SENTENCIA.");
            }

        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * Retorna una tabla completa con los campos de la tabla solicitada, util para setear directamente a una JTable JTable.setModel(DefaultTableModel tabla)
     * @param sentencia Sentencia pura SQL (ej. Select * from medicamentos)
     * @param nombres lista de nombre de los campos de la tabla
     * @return DefaultTableModel, con la tabla obtenida
     *
     * Ej.:
     * Base de datos: climed
     * Tabla: medicamento
     * JTable: tabla
     *
            String[] titulos = {"id", "nombre"};
            Object[][] datos = {};
            DefaultTableModel medicamentos = new DefaultTableModel(datos, titulos);
            medicamentos = conexion.ejecutarSentencia("SELECT * from climed.medicamento", titulos);
            tabla.setModel(medicamentos); //Setea el modelo de la tabla la tabla cargada
     *
     *      //LO QUE SIGUE ES SOLAMENTE PARA MOSTRAR LOS DATOS DE LA TABLA
            for (int i = 0; i < medicamentos.getRowCount(); i++){
                for (int j = 0; j < medicamentos.getColumnCount(); j++){
                    System.out.print(tabla.getColumnName(j)+": ");
                    System.out.print(medicamentos.getValueAt(i, j)+((j+1== medicamentos.getColumnCount())?"":" - "));
                }
                System.out.println("");
            }
     */
    public DefaultTableModel ejecutarSentencia(String sentencia, String[] nombres){
        DefaultTableModel modelo = null;
        try {
            if (conn != null) {
                Statement sent = conn.createStatement();
                ResultSet res = sent.executeQuery(sentencia);
                String[] titulos = nombres;
                Object[][] datos = {};
                modelo = new DefaultTableModel(datos, titulos);
                while (res.next()){
                    Object[] fila = new Object[nombres.length];
                    for (int i = 0; i < nombres.length; i++){
                        fila[i] = res.getObject(nombres[i]);
                    }
                    modelo.addRow(fila);
                }
            } else {
                System.out.println("No se pudo realizar la consulta, conexion nula");
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return modelo;
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
            Statement stm = conn.createStatement();
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
            stmt = conn.prepareStatement(comando);
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
            Statement stmt = conn.createStatement();
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
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sentencia);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // EJEMPLOS DE USO
        //                                                                                         (SERVER, Base de datos, Nombre Usuario, pass del usuario)
        util_conxionBDPostgres.conexionBD conexion = util_conxionBDPostgres.conexionBD.getInstance("localhost", "climed",     "postgres", "root");
        try {
            String[] columnas2 = {"codigo", "nombre"};
            PreparedStatement insertar2 = conexion.prepararParaInsertar("climed.medicamento", columnas2);
            insertar2.setInt(1, 4);
            insertar2.setString(2, "ATOMO");
            conexion.finalizarInsercion(insertar2);
            /**
             * INSERTAR UN ELEMENTO
             */
            String[] columnas = {"codigo", "nombre"};
            PreparedStatement insertar = conexion.prepararParaInsertar("climed.medicamento", columnas);
            insertar.setInt(1, 3);
            insertar.setString(2, "JARABE PARA LA TOS");
            conexion.finalizarInsercion(insertar);
            /**
             * FORMA DE HACER UNA CONSULTA
             */
            ResultSet res = conexion.ejecutarSentencia("Select * from climed.medicamento order by nombre");
            while (res.next()){ //Mientas existan elementos
                int idMedicamento = res.getInt("codigo"); //res.getInt("id"); obtiene el valor almacenado en la columna id de la tabla
                String nombre = res.getString("nombre");
                System.out.println(idMedicamento+": "+nombre);
            }
            /**
             *  ELIMINAR UN VALOR QUE TIENE UNA UNICA CLAVE
             */
            //conexion.eliminarValor("climed.medicamento", "codigo", "3");
            /**
             * ELIMINAR UN VALOR CON MAS DE UNA CLAVE
             * las claves son id y nombre
             */
            //conexion.eliminar("DELETE FROM climed.medicamento where codigo = 4 and nombre = 'ATOMO'");
            /**
             * ACTUALIZAR UN VALOR
             */
            conexion.editar("UPDATE climed.medicamento set nombre = 'MATRIX GRIP x 20 Comprimidos' where codigo = 10");
            /**
             * PASAR UNA CONSULTA A UN JTable
             */
            javax.swing.JTable tabla = new javax.swing.JTable();
            String[] titulos = {"codigo", "nombre"};
            Object[][] datos = {};
            DefaultTableModel medicamentos = new DefaultTableModel(datos, titulos);
            medicamentos = conexion.ejecutarSentencia("SELECT * from climed.medicamento", titulos);
            tabla.setModel(medicamentos);
            //MOSTRAMOS LOS VALORES OBTENIDOS
            for (int i = 0; i < medicamentos.getRowCount(); i++){
                for (int j = 0; j < medicamentos.getColumnCount(); j++){
                    System.out.print(tabla.getColumnName(j)+": ");
                    System.out.print(medicamentos.getValueAt(i, j)+((j+1== medicamentos.getColumnCount())?"":" - "));
                }
                System.out.println("");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}