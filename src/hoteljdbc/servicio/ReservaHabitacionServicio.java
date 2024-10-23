
package hoteljdbc.servicio;

import hoteljdbc.dto.CategoriaHabitacionDTO;
import hoteljdbc.entidad.CategoriaHabitacion;
import hoteljdbc.entidad.ReservaHabitacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservaHabitacionServicio {
    PreparedStatement statement;
   
   public ReservaHabitacion mostarReservaHabitacion(long idReserva, int idHabitacion , Connection conexion){
        String sql = "SELECT * FROM RESERVA_HABITACION WHERE ID_RESERVA = ? AND ID_HABITACION = ?";
        try {
           statement = conexion.prepareStatement(sql);
           statement.setLong(1,idReserva);
           statement.setInt(2,idHabitacion);
           ResultSet resultado = statement.executeQuery();
           statement.close();
           if(resultado.next()){
                return new ReservaHabitacion(
                        resultado.getLong("id_reserva"),
                        resultado.getInt("id_habitacion"),
                        resultado.getShort("cantidad")
                );
            }
           
        } catch (SQLException ex) {
           Logger.getLogger(categoriaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
   }
   
   public ReservaHabitacion crearCategoriaHabitacion(Connection conexion, ReservaHabitacion data){
       String sql = "INSERT INTO RESERVA_HABITACION(ID_RESERVA, ID_HABITACION, CANTIDAD) VALUES(?,?,?)";
       try {
           statement = conexion.prepareStatement(sql);
           statement.setLong(1, (long) data.getIdReserva());
           statement.setInt(2,data.getIdHabitacion());
           statement.setShort(3,data.getCantidad());
           int filas = statement.executeUpdate();
            if(filas > 0){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    statement.close();
                    return mostarReservaHabitacion(resultado.getLong(1), resultado.getInt(2),conexion);
                }
            }
       } catch (SQLException ex) {
           Logger.getLogger(categoriaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
   }

   
   public ReservaHabitacion modificarCategoriaHabitacion(Connection conexion, ReservaHabitacion data,int idReserva, int idHabitacion){
       String sql = "UPDATE RESERVA_HABITACION "
               + "SET ID_RESERVA = ? , ID_HABITACION = ? , CANTIDAD = ? WHERE ID_RESERVA = ? AND ID_HABITACION = ?";
       
       try {
           statement = conexion.prepareStatement(sql);
           statement.setLong(1, (long) data.getIdReserva());
           statement.setInt(2,data.getIdHabitacion());
           statement.setShort(3,data.getCantidad());
           statement.setLong(4, idReserva);
           statement.setInt(5, idHabitacion);
           int filas = statement.executeUpdate();
            if(filas > 0){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    statement.close();
                    return mostarReservaHabitacion(resultado.getLong(1), resultado.getInt(2),conexion);
                }
            }
       } catch (SQLException ex) {
           Logger.getLogger(categoriaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
   }
   public boolean borrarCategoriaHabitacion(Connection conexion, long idReserva, int idHabitacion) {
        String sql = "DELETE FROM RESERVA_HABITACION WHERE ID_RESERVA = ? AND ID_HABITACION = ?";
       try {
           statement = conexion.prepareStatement(sql);
           statement.setLong(1,idReserva);
           statement.setInt(2,idHabitacion);
           statement.execute(sql);
           statement.close();
           return true;
       } catch (SQLException ex) {
           Logger.getLogger(categoriaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;        
    }
}
