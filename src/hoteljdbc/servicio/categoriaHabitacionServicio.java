package hoteljdbc.servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import hoteljdbc.dto.CategoriaHabitacionDTO;
import hoteljdbc.entidad.CategoriaHabitacion;
import java.util.logging.Level;
import java.util.logging.Logger;

public class categoriaHabitacionServicio {
   
   PreparedStatement statement;
   
   public CategoriaHabitacion mostarCategoriaHabitacion(int id, Connection conexion){
        String sql = "SELECT * FROM CATEGORIA_HABITACION WHERE ID_CATEGORIA_HABITACION = ?";
        try {
           statement = conexion.prepareStatement(sql);
           statement.setInt(1,id);
           ResultSet resultado = statement.executeQuery();
           statement.close();
           if(resultado.next()){
                return new CategoriaHabitacion(
                        resultado.getInt("id_categoria_habitacion"),
                        resultado.getString("nombre"),
                        resultado.getDouble("precio_noche"),
                         resultado.getString("foto_url")
                );
            }
           
        } catch (SQLException ex) {
           Logger.getLogger(categoriaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
   }
   
   public CategoriaHabitacion crearCategoriaHabitacion(Connection conexion, CategoriaHabitacionDTO dto){
       String sql = "INSERT INTO CATEGORIA_HABITACION(nombre,precio_noche,foto_url) VALUES(?,?,?)";
       try {
           statement = conexion.prepareStatement(sql);
           statement.setString(1,dto.getNombre());
           statement.setDouble(2,dto.getPrecioNoche());
           statement.setString(3,dto.getFotoUrl());
           int filas = statement.executeUpdate();
            if(filas > 0){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    statement.close();
                    return mostarCategoriaHabitacion(resultado.getInt(1),conexion);
                }
            }
       } catch (SQLException ex) {
           Logger.getLogger(categoriaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
   }

   
   public CategoriaHabitacion modificarCategoriaHabitacion(Connection conexion, CategoriaHabitacionDTO dto, int id){
       String sql = "UPDATE CATEGORIA_HABITACION "
               + "SET "
               + "NOMBRE = ? ,PRECIO_NOCHE = ? ,FOTO_URL = ? WHERE ID_CATEGORIA_HABITACION = ?";
       
       try {
           statement = conexion.prepareStatement(sql);
           statement.setString(1,dto.getNombre());
           statement.setDouble(2,dto.getPrecioNoche());
           statement.setString(3,dto.getFotoUrl());
           statement.setInt(4,id);
           int filas = statement.executeUpdate();
            if(filas > 0){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    statement.close();
                    return mostarCategoriaHabitacion(resultado.getInt(1),conexion);
                }
            }
       } catch (SQLException ex) {
           Logger.getLogger(categoriaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
   }
   public boolean borrarCategoriaHabitacion(Connection conexion, int id) {
        String sql = "DELETE FROM CATEGORIA_HABITACION WHERE ID_CATEGORIA_HABITACION = ?";
       try {
           statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute(sql);
            statement.close();
            return true;
       } catch (SQLException ex) {
           Logger.getLogger(categoriaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;        
    }
    
}
