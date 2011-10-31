/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ABMDieta;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Conexion.Conexion;

/*
 * To change this template, choose Tools | Template
 * and open the template in the editor.
 */

/**
 *
 * @author Giuliodori Eugenia
 * @author Sung Pei Jung
 * @author Torello Elina
 */
public class ControladorDieta {

    @SuppressWarnings("unused")
	private int codigo;

    
    /*Dado un codigo de dieta, y nombre de desayuno, almuerzo, merienda y cena, inserta dichos valores en la tabla "tabledieta",
     mientras estas comidas se encuentren en la tabla "comida"*/
    public void altaDieta (int codigo, String desayuno, String almuerzo, String merienda, String cena) throws SQLException{
       

        Connection connection = Conexion.getInstancia();
        String c1 = "SELECT * FROM comida where nombre='" + desayuno + "'";
        String c2 = "SELECT * FROM comida where nombre='" + almuerzo + "'";
        String c3 = "SELECT * FROM comida where nombre='" + merienda + "'";
        String c4 = "SELECT * FROM comida where nombre='" + cena + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSetDesayuno = statement.executeQuery(c1);
        boolean esIgualDesayuno = (resultSetDesayuno.next());   
        ResultSet resultSetAlmuerzo = statement.executeQuery(c2);
        boolean esIgualAlmuerzo = (resultSetAlmuerzo.next());
        ResultSet resultSetMerienda = statement.executeQuery(c3);
        boolean esIgualMerienda = (resultSetMerienda.next());
        ResultSet resultSetCena = statement.executeQuery(c4);
        boolean esIgualCena = (resultSetCena.next());      
        if((esIgualDesayuno)&&(esIgualAlmuerzo)&&(esIgualMerienda)&&(esIgualCena)){
            String query = "insert into tabledieta values ('"+codigo+"','"+desayuno+"','"+almuerzo+"','"+merienda+"','"+cena+"')";
            statement.executeUpdate(query);
        }
    } //fin Altadieta

     /*Dado un codigo de dieta, si existe en la tabla "tabledieta", elimina la dieta correspondiente a dicho codigo*/
    public void bajaDieta (int codigo) throws SQLException{
        Connection connection = Conexion.getInstancia();
        String query = "DELETE FROM tabledieta WHERE codigo='"+codigo+"'";
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
   
    }// fin bajaDieta


    /*Dado un codigo de dieta, y nombre de desayuno, almuerzo, merienda y cena, si existe en la tabla "tabledieta" modifica dichos valores en la tabla "tabledieta",
     mientras estas comidas se encuentren en la tabla "comida"*/
    public void modificarDieta (int codigo, String desayuno, String almuerzo, String merienda, String cena) throws SQLException{
            Connection connection = Conexion.getInstancia();
            String c1 = "SELECT * FROM tabledieta where codigo='" + codigo + "'";
            String c2 = "SELECT * FROM comida where nombre='" + desayuno + "'";
            String c3 = "SELECT * FROM comida where nombre='" + almuerzo + "'";
            String c4 = "SELECT * FROM comida where nombre='" + merienda + "'";
            String c5 = "SELECT * FROM comida where nombre='" + cena + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSetCodigo = statement.executeQuery(c1);
            boolean existeCodigo = (resultSetCodigo.next());
            ResultSet resultSetDesayuno = statement.executeQuery(c2);
            boolean existeDesayuno = (resultSetDesayuno.next());
            ResultSet resultSetAlmuerzo = statement.executeQuery(c3);
            boolean existeAlmuerzo = (resultSetAlmuerzo.next());
            ResultSet resultSetMerienda = statement.executeQuery(c4);
            boolean existeMerienda = (resultSetMerienda.next());
            ResultSet resultSetCena = statement.executeQuery(c5);
            boolean existeCena = (resultSetCena.next());
            if((existeCodigo)&&(existeDesayuno)&&(existeAlmuerzo)&&(existeMerienda)&&(existeCena)){
                String query = "UPDATE tabledieta SET desayuno='"+desayuno+"',almuerzo='"+almuerzo+"' ,merienda='"+merienda+"' ,cena='"+cena+"' WHERE codigo='"+codigo+"'";
                statement.executeUpdate(query);
            }
        
    }// fin modificarDieta
    }
