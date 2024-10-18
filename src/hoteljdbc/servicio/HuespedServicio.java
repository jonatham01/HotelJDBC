
package hoteljdbc.servicio;
import hoteljdbc.entidad.Huesped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HuespedServicio {
    PreparedStatement statement;
    
    private boolean ejecutarSQL(Connection conexion, String sql, Huesped huesped){
        try {
            statement = conexion.prepareStatement(sql);
            if( huesped != null){
                statement.setInt(1, huesped.getCedula());
                statement.setString(2, huesped.getNombre());
                statement.setString(3, huesped.getApellido());
                statement.setString(4, huesped.getTipoIdentificacion());
            }
            boolean validacion = statement.execute();
            statement.close();
            return validacion;
  
        } catch (SQLException ex) {
            Logger.getLogger(HuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Huesped crearHuesped(Connection conexion, Huesped huesped){
        String sql = "INSERT INTO HUESPED(CEDULA,NOMBRE,APELLIDO,TIPOIDENTIFICACION) VALUES(?,?,?,?)";
        boolean validacion = ejecutarSQL(conexion,sql,huesped);
        if(validacion){
            return  huesped;
        }
        return null;
    }
    
    public Huesped mostrarHuesped(Connection conexion, int id){
        String sql = "SELECT * FROM HUESPED WHERE CEDULA =" +id;
        try {
            statement = conexion.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                Huesped huesped = new Huesped(
                        resultado.getInt("cedula"),
                        resultado.getString("nombre"),
                        resultado.getString("apellido"),
                        resultado.getString("TipoIdentificacion")
                );
                statement.close();
                return huesped;
            }
        } catch (SQLException ex) {
            Logger.getLogger(HuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Huesped modificarHuesped(Connection conexion, Huesped huesped, int id){
        String sql = "UPDATE HUESPED SET CEDULA = ?,NOMBRE = ?,APELLIDO = ?,TIPOIDENTIFICACION = ? WHERE CEDULA" + id;
        boolean validacion = ejecutarSQL(conexion,sql,huesped);
        if(validacion){
            return  huesped;
        }
        return null;
    }
     public boolean borrarHuesped(Connection conexion, int id){
        String sql = "DELETE FROM HUESPED WHERE CEDULA = " + id;
        return ejecutarSQL(conexion,sql,null);
        
    }
    
}
