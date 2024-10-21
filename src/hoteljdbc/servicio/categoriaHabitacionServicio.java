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
   
   private CategoriaHabitacion retornarCategoriaHabitacion(ResultSet resultado) throws SQLException{
      if(resultado.next()){
          int id = resultado.getInt("id_categoria_habitacion");
          String nombre = resultado.getString("nombre");
          Double precioNoche = resultado.getDouble("precio_noche");
          String fotoUrl = resultado.getString("foto_url");
          return new CategoriaHabitacion(id,nombre,precioNoche,fotoUrl);
      }
      return null;
   }
   
   public CategoriaHabitacion crearCategoriaHabitacion(Connection conexion, CategoriaHabitacionDTO categoriaHabitacionDTO){
       String sql = "INSERT INTO CATEGORIA_HABITACION(id_categoria_habitacion,nombre,precio_noche,foto_url) "
               + "VALUES(CAMA.SQL.NEXTVAL,"
               + categoriaHabitacionDTO.getNombre() + ","
               + categoriaHabitacionDTO.getPrecioNoche() + ","
               + categoriaHabitacionDTO.getFotoUrl() + ","
               + ")";
       try {
           statement = conexion.prepareStatement(sql);
           ResultSet resultado = statement.executeQuery();
           CategoriaHabitacion categoria = retornarCategoriaHabitacion(resultado);
           statement.close();
           return categoria;
       } catch (SQLException ex) {
           Logger.getLogger(categoriaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
   }
   
   public CategoriaHabitacion mostarCategoriaHabitacion(int id, Connection conexion){
        String sql = "SELECT * FROM CATEGORIA_HABITACION WHERE ID_CATEGORIA_HABITACION = " + id;
       try {
           statement = conexion.prepareStatement(sql);
           ResultSet resultado = statement.executeQuery();
           retornarCategoriaHabitacion(resultado);
           statement.close();
       } catch (SQLException ex) {
           Logger.getLogger(categoriaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
   }
   
   public CategoriaHabitacion modificarCategoriaHabitacion(Connection conexion, CategoriaHabitacionDTO dto, int id){
       String sql = "UPDATE CATEGORIA_HABITACION "
               + "SET "
               + "NOMBRE = " + dto.getNombre()
               + " ,PRECIO_NOCHE =" + dto.getPrecioNoche()
               + " ,FOTO_URL =" + dto.getFotoUrl() 
               + " WHERE ID_CATEGORIA_HABITACION = " + id;
       
       try {
           statement = conexion.prepareStatement(sql);
           ResultSet resultado = statement.executeQuery();
           retornarCategoriaHabitacion(resultado);
           statement.close();
       } catch (SQLException ex) {
           Logger.getLogger(categoriaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
   }
   public boolean borrarCategoriaHabitacion(int id) {
        String sql = "DELETE FROM CATEGORIA_HABITACION "
                + "WHERE ID_CATEGORIA_HABITACION = " + id;
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
