
package hoteljdbc.servicio;
import hoteljdbc.entidad.Huesped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HuespedServicio {
    PreparedStatement statement;
    
    public Huesped mostrarHuesped(Connection conexion, int cedula){
        String sql = "SELECT * FROM HUESPED WHERE CEDULA = ?";
        try {
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, cedula);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                Huesped huesped = new Huesped(
                        resultado.getInt("cedula"),
                        resultado.getString("nombre"),
                        resultado.getString("apellido"),
                        resultado.getString("tipo_identificacion")
                );
                statement.close();
                return huesped;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
       
    public Huesped crearHuesped(Connection conexion, Huesped huesped){
        String sql = "INSERT INTO HUESPED(CEDULA,NOMBRE,APELLIDO,TIPO_IDENTIFICACION) VALUES(?,?,?,?)";
        try {
            statement = conexion.prepareStatement(sql);
            if( huesped != null){
                statement.setInt(1, huesped.getCedula());
                statement.setString(2, huesped.getNombre());
                statement.setString(3, huesped.getApellido());
                statement.setString(4, huesped.getTipoIdentificacion());
            }
           int filas = statement.executeUpdate();
            if ( filas > 0 ){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    if (resultado.next()){
                        int id = resultado.getInt(1);
                        statement.close();
                        return mostrarHuesped(conexion,id);
                    }                    
                }
            }
  
        } catch (SQLException ex) {
            Logger.getLogger(HuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    
    public Huesped modificarHuesped(Connection conexion, Huesped huesped, int cedula){
        String sql = "UPDATE HUESPED SET CEDULA = ?,NOMBRE = ?,APELLIDO = ?,TIPO_IDENTIFICACION = ? WHERE CEDULA = ?";
         try {
            statement = conexion.prepareStatement(sql);
            if( huesped != null){
                statement.setInt(1, huesped.getCedula());
                statement.setString(2, huesped.getNombre());
                statement.setString(3, huesped.getApellido());
                statement.setString(4, huesped.getTipoIdentificacion());
                statement.setInt(5, cedula);
            }
           int filas = statement.executeUpdate();
            if ( filas > 0 ){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    if (resultado.next()){
                        int id = resultado.getInt(1);
                        statement.close();
                        return mostrarHuesped(conexion,id);
                    }                    
                }
            }
            
  
        } catch (SQLException ex) {
            Logger.getLogger(HuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
     public boolean borrarHuesped(Connection conexion, int cedula){
        String sql = "DELETE FROM HUESPED WHERE CEDULA =  ?" ;
        try{
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, cedula);
            statement.execute(sql);
            statement.close();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(ClienteServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    
}
