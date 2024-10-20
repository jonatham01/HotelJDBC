
package hoteljdbc.servicio;

import hoteljdbc.dto.CamaHabitacionDTO;
import hoteljdbc.entidad.CamaHabitacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

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
    
    public CamaHabitacion crearCamaHabitacion(Connection conexion, CamaHabitacionDTO dto){
        String sql = "INSERT INTO CAMA_HABITACION(ID_CAMA,ID_HABITACION,ESTADO) "
               + dto.getIdCama() + ","
               + dto.getIdHabitacion() + ","
               + dto.getEstado()
               + ")";
        
        try {
            statement = conexion.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            CamaHabitacion camaHabitacion = retornarCamaHabitacion(resultado);
            statement.close();
            return camaHabitacion;
        } catch (SQLException ex) {
            Logger.getLogger(CamaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return null;
    }
    
    public CamaHabitacion modificarCamaHabitacion(Connection conexion, CamaHabitacionDTO dto, int idHabitacion, int idCama){
        String sql = "UPDATE CAMA_HABITACION SET "
                + " ID_HABITACION = " + dto.getIdHabitacion()
                + " , ID_CAMA = " + dto.getIdCama()
                + " ,ESTADO = " + dto.getEstado()
                + "WHERE ID_HABITACION = " + idHabitacion
                + " AND ID_CAMA = " + idCama;
        
        try {
            statement = conexion.prepareStatement(sql);
            ResultSet resultado =  statement.executeQuery();
            CamaHabitacion camaHabitacion = retornarCamaHabitacion(resultado);
            statement.close();
            return camaHabitacion;
        } catch (SQLException ex) {
            Logger.getLogger(CamaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public CamaHabitacion mostrarCamaHabitacion (Connection conexion, int idHabitacion, int idCama){
        
        String sql = "SELECT * FROM CAMA_HABITACION "
                + "WHERE ID_CAMA = " + idCama + " AND ID_HABITACION = " + idHabitacion;
        
        try {
            statement = conexion.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            CamaHabitacion camaHabitacion = retornarCamaHabitacion(resultado);
            statement.close();
            return camaHabitacion;
            
        } catch (SQLException ex) {
            Logger.getLogger(CamaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean borrarCamaHabitacion( int idHabitacion, int idCama) {
        String sql = "DELETE FROM CAMA_HABITACION "
                 + "WHERE ID_CAMA = " + idCama + " AND ID_HABITACION = " + idHabitacion;
       try {
           boolean validacion = statement.execute(sql);
           statement.close();
           return validacion;
       } catch (SQLException ex) {
           Logger.getLogger(categoriaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;        
    }
    
}
