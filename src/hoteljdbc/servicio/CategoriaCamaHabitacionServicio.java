
package hoteljdbc.servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import hoteljdbc.entidad.CategoriaCamaHabitacion;
import java.sql.Statement;
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
                + "VALUES( ? , ? , ?)";
        
        try {
            statement = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entidad.getCodigoCategoriaCama());
            statement.setInt(2, entidad.getCodigoCategoriaHabitacion());
            statement.setInt(3, entidad.getCantidad());
            int filas = statement.executeUpdate();
            if ( filas > 0 ){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    if (resultado.next()){
                         int codigoCategoriaCama = resultado.getInt(1);
                    int codigoCategoriaHabitacion = resultado.getInt(2);
                    statement.close();
                    sql = null;
                    return mostrarCategoriaHabitacion(conexion,codigoCategoriaCama,codigoCategoriaHabitacion);
                    }                    
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaCamaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql = null;
        return null;
    }
    
    public CategoriaCamaHabitacion mostrarCategoriaHabitacion( Connection conexion, int idCategoriaCama, int idCategoriaHabitacion){
        sql = "SELECT * FROM CATEGORIA_CAMA_HABITACION "
                + "WHERE CODIGO_CATEGORIACAMA = ? "
                + "AND CODIGO_CATEGORIAHABITACION = ?";
        
        try {
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, idCategoriaCama);
            statement.setInt(2,  idCategoriaHabitacion);
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
                + " SET CODIGO_CATEGORIACAMA = ? , "
                + ",CODIGO_CATEGORIAHABITACION = ?  "
                + ",CANTIDAD = ? "
                + "WHERE CODIGO_CATEGORIACAMA = ? "
                + "AND CODIGO_CATEGORIAHABITACION = ?";
        
        try {
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, categoriaCamaHabitacion.getCodigoCategoriaCama());
            statement.setInt(2, categoriaCamaHabitacion.getCodigoCategoriaHabitacion());
            statement.setShort(3, categoriaCamaHabitacion.getCantidad());
            statement.setInt(4, idCategoriaCama);
            statement.setInt(5, idCategoriaHabitacion);
            int filas = statement.executeUpdate();
            if ( filas > 0 ){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    if (resultado.next()){
                         int codigoCategoriaCama = resultado.getInt(1);
                    int codigoCategoriaHabitacion = resultado.getInt(2);
                    statement.close();
                    sql = null;
                    return mostrarCategoriaHabitacion(conexion,codigoCategoriaCama,codigoCategoriaHabitacion);
                    }                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaCamaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        sql = null;
        return null;
    }
    
    public boolean eliminarCategoriaCamaHabitacion(Connection conexion,int idCategoriaCama, int idCategoriaHabitacion){
        sql = "DELETE FROM CATEGORIA_CAMA_HABITACION WHERE CODIGO_CATEGORIAHABITACION = ? AND CODIGO_CATEGORIACAMA = ?" ;
        
         try{
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, idCategoriaCama);
            statement.setInt(2, idCategoriaHabitacion);
            statement.execute(sql);
            statement.close();
            return true;
        }catch(SQLException ex){
            Logger.getLogger(categoriaHabitacionServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
