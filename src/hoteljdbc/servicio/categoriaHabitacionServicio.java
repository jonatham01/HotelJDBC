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
          int id = resultado.getInt("idCategoriaHabitacion");
          String nombre = resultado.getString("nombre");
          Double precioNoche = resultado.getDouble("precioNoche");
          String fotoUrl = resultado.getString("fotoUrl");
          return new CategoriaHabitacion(id,nombre,precioNoche,fotoUrl);
      }
      return null;
   }
   
   public CategoriaHabitacion crearCategoriaHabitacion(Connection conexion, CategoriaHabitacionDTO categoriaHabitacionDTO){
       String sql = "INSERT INTO CATEGORIAHABITACION(idCategoriaHabitacion,nombre,precioNoche,fotoUrl) "
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
        String sql = "SELECT * FROM CATEGORIAHABITACION WHERE IDCATEGORIACAMA = " + id;
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
       String sql = "UPDATE CATEGORIAHABITACION "
               + "SET "
               + "NOMBRE = " + dto.getNombre()
               + " ,PRECIONOCHE =" + dto.getPrecioNoche()
               + " ,FOTOURL =" + dto.getFotoUrl() 
               + " WHERE IDCATEGORIA HABITACION = " + id;
       
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
        String sql = "DELETE FROM CATEGORIAHABITACION "
                + "WHERE IDCATEGORIAHABITACION = " + id;
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
