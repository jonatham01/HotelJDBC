
package hoteljdbc.servicio;

import java.sql.Connection;
import hoteljdbc.entidad.CategoriaCama;
import java.sql.*;

public class CategoriaCamaServicio {
    
    public String crearCategoriaCama(Connection conexion, CategoriaCama entidad){
        PreparedStatement pst = null;
        String sql = "INSERT INTO categoriaCama (idCategoriaCama,tipo,medidas,fotoUrl,color) "
                + "Values(CATEGORIACAMA.SEQ.NEXTVAL,?,?,?,?)";
        try {
            pst = conexion.prepareStatement(sql) ;
            pst.setString(1,entidad.getTipo());
            pst.setString(2,entidad.getMedidas());
            pst.setString(3, entidad.getFotoUrl());
            pst.setString(4, entidad.getColor());
            Boolean validacion = pst.execute();
            if(validacion == true){
                System.out.println("Se creo la categoria cama con exito");
            }
            pst.close();
        }catch(SQLException e){
             System.out.println("No se pudo crear la categoria cama con exito");
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    public void mostrarCategoriaCama(Connection conexion, CategoriaCama entidad){
        String sql = " SELECT * FROM categoriaCama";

    }
    
    public String modificarCategoriaCama(Connection conexion, CategoriaCama entidad){
        PreparedStatement pst = null;
        String sql = "UPDATE categoriaCama SET TIPO ${entidad.getTipo()} MEDIDAS ${entidad.getMedidas()} FOTOURL ${entidad.getFotoUrl()} COLOR ${entidad.getColor()} WHERE IDCATEGORIACAMA = ${entidad.getIdCategoriaCama()}  ";
        try {
            pst = conexion.prepareStatement(sql) ;
            Boolean validacion = pst.execute();
            if(validacion == true){
                System.out.println("Se modifico la categoria cama con exito");
            }
            pst.close();
        }catch(SQLException e){
             System.out.println("No se pudo modificar la categoria cama con exito");
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
    public String eliminarCategoriaCama(Connection conexion, int id){
        PreparedStatement pst = null;
        String sql = "DELETE FROM categoriaCama  WHERE IDCATEGORIACAMA = ${entidad.getIdCategoriaCama()}  ";
        try {
            pst = conexion.prepareStatement(sql) ;
            Boolean validacion = pst.execute();
            if(validacion == true){
                System.out.println("Se borro la categoria cama con exito");
            }
            pst.close();
        }catch(SQLException e){
             System.out.println("No se pudo borrar la categoria cama con exito");
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
   
}
