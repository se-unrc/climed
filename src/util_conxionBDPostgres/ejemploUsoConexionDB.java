package util_conxionBDPostgres;

import java.sql.ResultSet;

public class ejemploUsoConexionDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        util_conxionBDPostgres.conexionBD conexion = util_conxionBDPostgres.conexionBD.getInstance("localhost", "climed", "root", "root");
        try {
            ResultSet res = conexion.ejecutarSentencia("Select * from climed.medicamento");
            while (res.next()){
                System.out.println(res.getInt("id"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}