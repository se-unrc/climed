package ABMMedicamento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import Dominio.Medicamento;
import Conexion.*;

/**
 * @author grupo1
 */
public class ControlMedicamentos {

    private ControlConexion conexion = new ControlConexion();

    public Vector<Medicamento> getAll(){
        Vector<Medicamento> medicamentos = new Vector();
        ResultSet res = conexion.ejecutarSentencia("Select * from medicamento");
        try {
            while (res.next()){
                Medicamento a = new Medicamento (res.getInt("codigo"),res.getString("nombre"),res.getDouble("stock"),res.getDouble("stock_min"), res.getString("unidad"),res.getDate("vencimiento"));
                medicamentos.add(a);
            }
        }catch (Exception ex){
            System.err.println("Error al cargar Medicamentos");
            ex.printStackTrace();
        }
        return medicamentos;
    }

    public boolean insertar(Medicamento elem){
        boolean isOk = false;
        try{
            String [] columnas = {"codigo","nombre","stock","stock_min","unidad","vencimiento"};
            PreparedStatement insertStmt = conexion.prepararParaInsertar("medicamento",columnas);
            insertStmt.setInt(1,elem.getCodigo());
            insertStmt.setString(2, elem.getNombre());
            insertStmt.setDouble(3, elem.getStock());
            insertStmt.setDouble(4, elem.getStock_min());
            insertStmt.setString(5, elem.getUnidad());
            java.sql.Date d = java.sql.Date.valueOf((elem.getVencimiento().getYear()+1900)+"-"+(elem.getVencimiento().getMonth()+1)+"-"+elem.getVencimiento().getDate());;
            insertStmt.setDate(6, d);
            conexion.finalizarInsercion(insertStmt);
            isOk = true;
        }catch(Exception ex){
            System.err.println("Error al Insertar Medicamento");
            ex.printStackTrace();
        }finally{
            return isOk;
        }
    }

    public boolean exist(Medicamento a){
        boolean exist = false;
        Vector<Medicamento> medicamentos = getAll();
        for (int i = 0; i < medicamentos.size(); i++){
            if (medicamentos.get(i).getCodigo() == a.getCodigo()) {
                exist = true;
                break;
            }
        }
        return exist;
    }

    public Medicamento getByCode(int cod){
        Medicamento elem = new Medicamento();
        Vector<Medicamento> medicamentos = getAll();
        for (int i = 0; i < medicamentos.size(); i++){
            if (medicamentos.get(i).getCodigo() == cod) {
                elem = medicamentos.get(i);
                break;
            }
        }
        return elem;
    }

    public boolean actualizar(Medicamento elem){
        boolean isOk = false;
        try{
            int ano = elem.getVencimiento().getYear()+1900;
            int mes = elem.getVencimiento().getMonth()+1;
            int dia = elem.getVencimiento().getDate();
            String fecha = ano+"-"+mes+"-"+dia;
            conexion.editar("UPDATE medicamento SET nombre = '"+ elem.getNombre()+
                    "', stock = "+ elem.getStock()+
                    ", stock_min = "+ elem.getStock_min()+
                    ", unidad = '"+ elem.getUnidad()+
                    "', vencimiento = '"+ fecha+
                    "' WHERE codigo = " + elem.getCodigo() + ";");
            isOk = true;
        }catch(Exception ex){
            System.err.println("Error al Actualizar Medicamento");
            ex.printStackTrace();
        }finally{
            return isOk;
        }
    }

    public boolean deleteItem(Medicamento a){
        boolean isOk = false;
        try{
            conexion.eliminarValor("medicamento", "codigo", a.getCodigo()+"");
            isOk = true;
        }catch(Exception ex){
            System.err.println("Error al eliminar Medicamento");
            ex.printStackTrace();
        }finally{
            return isOk;
        }
    }
}
