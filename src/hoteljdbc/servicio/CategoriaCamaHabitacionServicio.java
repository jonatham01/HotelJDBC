
package hoteljdbc.servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import hoteljdbc.entidad.CategoriaCamaHabitacion;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriaCamaHabitacionServicio {
    
    PreparedStatement statement;
    String sql;
    
    private CategoriaCamaHabitacion retornarCategoria(ResultSet resultado){
        try {
            if(resultado.next()){
                return new CategoriaCamaHabitacion( 
                        resultado.getInt("codigo_categoriacama"),
                        resultado.getInt("codigo_categoriahabitacion"),
                        resultado.getShort("cantidad")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaCamaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public CategoriaCamaHabitacion crearCategoriaCamaHabitacion( Connection conexion, CategoriaCamaHabitacion entidad){
        sql = " INSERT INTO CATEGORIA_CAMA_HABITACION(codigo_categoriacama,codigo_categoriahabitacion,cantidad) "
                + "VALUES( ${entidad.getIdCategoriaCama}, ${entidad.getIdCategoriaHabitacion} , ${entidad.getCantidad} )";
        
        try {
            statement = conexion.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            CategoriaCamaHabitacion categoria = retornarCategoria(resultado);
            statement.close();
            sql = null;
            return categoria;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaCamaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql = null;
        return null;
    }
    
    public CategoriaCamaHabitacion mostrarCategoriaHabitacion( Connection conexion, int idCategoriaCama, int idCategoriaHabitacion){
        sql = "SELECT * FROM CATEGORIA_CAMA_HABITACION "
                + "WHERE CODIGO_CATEGORIACAMA = ${idCategoriaCama} "
                + "AND CODIGO_CATEGORIAHABITACION = {idCategoriaHabitacion}";
        
        try {
            statement = conexion.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            CategoriaCamaHabitacion categoria = retornarCategoria(resultado);
            statement.close();
            sql = null;
            return categoria;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaCamaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql = null;
        return null;
    }
    
    public CategoriaCamaHabitacion modificarCategoriaCamaHabitacion(Connection conexion, 
            CategoriaCamaHabitacion categoriaCamaHabitacion, 
            int idCategoriaCama, 
            int idCategoriaHabitacion){
        
        sql = "UPDATE CATEGORIA_CAMA_HABITACION "
                + " SET CODIGO_CATEGORIACAMA = ${categoriaCamaHabitacion.getIdCategoriaCama()} , "
                + ",CODIGO_CATEGORIAHABITACION = {categoriaCamaHabitacion.IdCategoriaHabitacion()}  "
                + ",CANTIDAD = ${categoriaCamaHabitacion.Cantidad()} "
                + "WHERE CODIGO_CATEGORIACAMA = ${idCategoriaCama} "
                + "AND CODIGO_CATEGORIAHABITACION = {idCategoriaHabitacion}";
        
        try {
            statement = conexion.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            CategoriaCamaHabitacion categoria = retornarCategoria(resultado);
            statement.close();
            sql = null;
            return categoria;
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaCamaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql = null;
        return null;
    }
    
    public boolean eliminarCategoriaCamaHabitacion(Connection conexion,int idCategoriaCama, int idCategoriaHabitacion){
        sql = "DELETE FROM CATEGORIA_CAMA_HABITACION "
                + "WHERE CODIGO_CATEGORIAHABITACION = " + idCategoriaHabitacion
                + " AND CODIGO_CATEGORIACAMA" + idCategoriaCama;
        
         try {
           boolean validacion = statement.execute(sql);
           statement.close();
           sql = null;
           return validacion;
       } catch (SQLException ex) {
           Logger.getLogger(categoriaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
       }
       return false;   
    }
    
}
