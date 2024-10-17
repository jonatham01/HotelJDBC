
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
                        resultado.getInt("idCategoriaCama"),
                        resultado.getInt("idCategoriaHabitacion"),
                        resultado.getShort("cantidad")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaCamaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public CategoriaCamaHabitacion crearCategoriaCamaHabitacion( Connection conexion, CategoriaCamaHabitacion entidad){
        sql = " INSERT INTO CATEGORIACAMAHABITACION(idCategoriaCama,idCategoriaHabitacion,cantidad) "
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
        sql = "SELECT * FROM CATEGORIACAMAHABITACION "
                + "WHERE IDCATEGORIACAMA = ${idCategoriaCama} "
                + "AND IDCATEGORIAHABITACION = {idCategoriaHabitacion}";
        
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
        
        sql = "UPDATE CATEGORIACAMAHABITACION "
                + " SET IDCATEGORIACAMA = ${categoriaCamaHabitacion.getIdCategoriaCama()} , "
                + ",IDCATEGORIAHABITACION = {categoriaCamaHabitacion.IdCategoriaHabitacion()}  "
                + ",CANTIDAD = ${categoriaCamaHabitacion.Cantidad()} "
                + "WHERE IDCATEGORIACAMA = ${idCategoriaCama} "
                + "AND IDCATEGORIAHABITACION = {idCategoriaHabitacion}";
        
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
        sql = "DELETE FROM CATEGORIACAMAHABITACION "
                + "WHERE IDCATEGORIAHABITACION = " + idCategoriaHabitacion
                + " AND IDCATEGORIACAMA" + idCategoriaCama;
        
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
