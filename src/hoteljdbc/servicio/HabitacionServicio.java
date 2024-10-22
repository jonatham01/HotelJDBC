package hoteljdbc.servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import hoteljdbc.dto.HabitacionDTO;
import hoteljdbc.entidad.Habitacion;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HabitacionServicio {
    PreparedStatement statement;
    String sql;
    
    private Habitacion retornarHabitacion(ResultSet resultado){
        try {
            if(resultado.next()){
                return new Habitacion(
                        resultado.getInt("id_habitacion"),
                        resultado.getInt("piso"),
                        resultado.getInt("telefono"),
                        resultado.getInt("id_categoria")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(HabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Habitacion mostrarHabitacion(Connection conexion, int id){
        sql ="SELECT * FROM HABITACION WHERE ID_HABITACION = ?";
        try {
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultado = statement.executeQuery();
            return retornarHabitacion(resultado);
        } catch (SQLException ex) {
            Logger.getLogger(FacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Habitacion guardarHabitacion(Connection conexion, HabitacionDTO dto){
        sql ="INSERT INTO HABITACION(piso,telefono,id_categoria) "
                + "VALUES(?, ? , ? )";
        try {
            statement = conexion.prepareStatement(sql);
            if(dto != null){
                statement.setInt(1, dto.getPiso());
                statement.setInt(2, dto.getTelefono());
                statement.setInt(3, dto.getIdCategoria());
            }
            int filas = statement.executeUpdate();
            if ( filas > 0 ){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    if (resultado.next()){
                        int id = resultado.getInt(1);
                        statement.close();
                        return mostrarHabitacion(conexion,id);
                    }                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;   
    }
    
    public Habitacion modificarHabitacion(Connection conexion, HabitacionDTO dto, int idHabitacion){
        sql ="UPDATE HABITACION "
                + "SET PISO = ? " 
                + ",TELEFONO = ?" 
                + ", ID_CATEGORIA = ?" 
                + " WHERE ID_HABITACION = ?" ;
        try {
            statement = conexion.prepareStatement(sql);
            if(dto != null){
                statement.setInt(1, dto.getPiso());
                statement.setInt(2, dto.getTelefono());
                statement.setInt(3, dto.getIdCategoria());
                statement.setInt(4, idHabitacion);
            }
            int filas = statement.executeUpdate();
            if ( filas > 0 ){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    if (resultado.next()){
                        int id = resultado.getInt(1);
                        statement.close();
                        return mostrarHabitacion(conexion,id);
                    }                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;   
    }
    public boolean borrarHabitacion(int id,Connection conexion){
        sql = "DELETE FROM HABITACION "
                + "WHERE ID_HABITCION = ?";
        try{
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute(sql);
            statement.close();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(ClienteServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
   
    
}
