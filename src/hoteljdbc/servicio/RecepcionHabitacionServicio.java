
package hoteljdbc.servicio;

import hoteljdbc.entidad.RecepcionHabitacion;
import hoteljdbc.dto.RecepcionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecepcionHabitacionServicio {
    
    private PreparedStatement statement;
    
    public RecepcionHabitacion crearRecepcionHabitacion(Connection conexion, RecepcionDTO dto){
        String sql = "INSERT INTO RECEPCIONHABITACION(FECHA,IDHABITACION,CATEGORIA) VALUES(CAMA.SQL.NEXTVAL,?,?,?)";
        try {
            statement = conexion.prepareStatement(sql);
            statement.setDate(1, (Date) dto.getFecha());
            statement.setInt(2, dto.getIdHabitacion());
            statement.setString(3, dto.getCategoria());
            ResultSet resultado = statement.executeQuery();
            statement.close();
            if(resultado.next()){
                return new RecepcionHabitacion(
                        resultado.getInt("codigoRecepcionHabitacion"),
                        resultado.getDate("fecha"),
                        resultado.getInt("idHabitacion"),
                        resultado.getString("categoria")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(RecepcionHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public RecepcionHabitacion mostrarRecepcionHabitacion(Connection conexion, int id){
        String sql = "SELECT * FROM RECEPCIONHABITACION WHERE CODIGORECEPCIONHABITACION = ${id}";
        try{
            statement = conexion.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            statement.close();
            if(resultado.next()){
                return new RecepcionHabitacion(
                        resultado.getInt("codigoRecepcionHabitacion"),
                        resultado.getDate("fecha"),
                        resultado.getInt("idHabitacion"),
                        resultado.getString("categoria")
                );
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(RecepcionHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public RecepcionHabitacion modificarRecepcionHabitacion(Connection conexion, int id, RecepcionDTO dto ){
        String sql = "UPDATE RECEPCIONHABITACION SET FECHA = ?, IDHABITACION = ?, CATEGORIA = ? "
                + "WHERE CODIGORECEPCIONHABITACION = ${id}";
        try{
            statement = conexion.prepareStatement(sql);
            statement.setDate(1, (Date) dto.getFecha());
            statement.setInt(2, dto.getIdHabitacion());
            statement.setString(3, dto.getCategoria());
            ResultSet resultado = statement.executeQuery();
            statement.close();
            if(resultado.next()){
                return new RecepcionHabitacion(
                        resultado.getInt("codigoRecepcionHabitacion"),
                        resultado.getDate("fecha"),
                        resultado.getInt("idHabitacion"),
                        resultado.getString("categoria")
                );
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(RecepcionHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public boolean BorrarRecepcionHabitacion(Connection conexion, int id){
        String sql = "DELETE FROM RECEPCIONHABITACION WHERE CODIGORECEPCIONHABITACION = " +id;
        
        try {
            statement = conexion.prepareStatement(sql);
            boolean validacion = statement.execute();
            statement.close();
            return validacion;
        } catch (SQLException ex) {
            Logger.getLogger(RecepcionHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
