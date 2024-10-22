
package hoteljdbc.servicio;

import hoteljdbc.dto.CamaHabitacionDTO;
import hoteljdbc.entidad.CamaHabitacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CamaHabitacionServicio {
    
    PreparedStatement statement;
    
    private CamaHabitacion retornarCamaHabitacion(ResultSet resultado) throws SQLException{
        if(resultado.next()){
          int idCama = resultado.getInt("idCama");
          int idHabitacion = resultado.getInt("idHabitacion");
          String estado = resultado.getString("estado");
          return new CamaHabitacion(idCama,idHabitacion,estado);
      }
      return null;
    }
    
    public CamaHabitacion mostrarCamaHabitacion (Connection conexion, int idHabitacion, int idCama){
        
        String sql = "SELECT * FROM CAMA_HABITACION "
                + "WHERE ID_CAMA = ? AND ID_HABITACION = ?" ;
        
        try {
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, idCama);
            statement.setInt(2, idHabitacion);
            ResultSet resultado = statement.executeQuery();
            CamaHabitacion camaHabitacion = retornarCamaHabitacion(resultado);
            statement.close();
            return camaHabitacion;
            
        } catch (SQLException ex) {
            Logger.getLogger(CamaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public CamaHabitacion crearCamaHabitacion(Connection conexion, CamaHabitacionDTO dto){
        String sql = "INSERT INTO CAMA_HABITACION(ID_CAMA,ID_HABITACION,ESTADO) VALUES(?,? ,?) ";
        
        try {
            statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, dto.getIdCama());
            statement.setInt(2, dto.getIdHabitacion());
            statement.setString(3, dto.getEstado());
            int filas = statement.executeUpdate();
            if(filas > 0){
                try (ResultSet resultado = statement.getGeneratedKeys()) {
                    if(resultado.next()){
                        int idCama = resultado.getInt(1);
                        int idHabitacion = resultado.getInt(2);
                        statement.close();
                        return mostrarCamaHabitacion(conexion ,idHabitacion,idCama);
                    }
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CamaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return null;
    }
    
    public CamaHabitacion modificarCamaHabitacion(Connection conexion, CamaHabitacionDTO dto, int idHabitacion, int idCama){
        String sql = "UPDATE CAMA_HABITACION SET "
                + " ID_HABITACION = ?" 
                + " , ID_CAMA = ?" 
                + " ,ESTADO = ? " 
                + "WHERE ID_HABITACION ?= " 
                + " AND ID_CAMA = ?" ;
        
        try {
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, dto.getIdCama());
            statement.setInt(2, dto.getIdHabitacion());
            statement.setString(3, dto.getEstado());
            statement.setInt(4, dto.getIdHabitacion());
            statement.setInt(5, dto.getIdCama());
            int filas = statement.executeUpdate();
            if(filas > 0){
                try (ResultSet resultado = statement.getGeneratedKeys()) {
                    CamaHabitacion camaHabitacion = retornarCamaHabitacion(resultado);
                    statement.close();
                    return camaHabitacion;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CamaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
 
    
    public boolean borrarCamaHabitacion(Connection conexion, int idHabitacion, int idCama) {
        String sql = "DELETE FROM CAMA_HABITACION "
                + "WHERE ID_CAMA = ? AND ID_HABITACION = ?" ;
       try {
           statement = conexion.prepareStatement(sql);
           statement.setInt(1, idCama);
           statement.setInt(2, idHabitacion);
           statement.execute(sql);
           statement.close();
           return true;
       } catch (SQLException ex) {
           Logger.getLogger(categoriaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;        
    }
    
}
