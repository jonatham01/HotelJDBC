package hoteljdbc.servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import hoteljdbc.dto.HabitacionDTO;
import hoteljdbc.entidad.Habitacion;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HabitacionServicio {
    PreparedStatement statement;
    String sql;
    
    private Habitacion retornarHabitacion(ResultSet resultado){
        try {
            if(resultado.next()){
                return new Habitacion(
                        resultado.getInt("idHabitacion"),
                        resultado.getInt("piso"),
                        resultado.getInt("telefono"),
                        resultado.getInt("idCategoria")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private Habitacion ejecutar(Connection conexion, String sql){
        try {
            statement = conexion.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            Habitacion habitacion = retornarHabitacion(resultado);
            statement.close();
            return habitacion;
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public Habitacion guardarHabitacion(Connection conexion, HabitacionDTO dto){
        sql ="INSERT INTO HABITACION(piso,telefono,idCategoria) "
                + "VALUES(HABITACION.SQL.NEXTVAL, ${dto.getPiso()}, ${dto.getTelefono()} , ${dto.getCategoria()} )";
        return ejecutar(conexion, sql);
    }
    public Habitacion mostrarHabitacion(Connection conexion, HabitacionDTO dto, int id){
        sql ="SELECT * FROM HABITACION WHERE IDHABITACION = " + id;
        return ejecutar(conexion, sql);
    }
    public Habitacion modificarHabitacion(Connection conexion, HabitacionDTO dto, int id){
        sql ="UPDATE HABITACION "
                + "SET PISO = " + dto.getPiso()
                + ",TELEFONO = " + dto.getTelefono()
                + ", IDCATEGORIA = " + dto.getIdCategoria()
                + " WHERE IDHABITACION = " + id;
        return ejecutar(conexion, sql);
    }
    public boolean borrarHabitacion(int id) throws SQLException{
        sql = "DELETE FROM HABITACION "
                + "WHERE IDHABITCION = " + id;
        try{
            return statement.execute(sql);
        }catch(SQLException e){
            throw e;
        }
    }
    
   
    
}
