
package hoteljdbc.servicio;

import java.sql.Connection;
import hoteljdbc.entidad.CategoriaCama;
import java.sql.*;

public class CategoriaCamaServicio {
    PreparedStatement pst;
    
    public String crearCategoriaCama(Connection conexion, CategoriaCama entidad){
        
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
                return "Se creo la categoria cama con exito";
            }
            pst.close();
        }catch(SQLException e){
             return "No se pudo crear la categoria cama con exito";
            
        }
        
        return null;
    }
    
    public CategoriaCama mostrarCategoriaCamaById(Connection conn, int id) {
        
        CategoriaCama categoriaCama = null;
        
        String sql = "SELECT * FROM CategoriaCama WHERE idCategoriaCama =" +id; 

        try {
            PreparedStatement ps = conn.prepareStatement(sql); 
            ResultSet rs = ps.executeQuery(); 
            if (rs.next()) {
                int idCategoriaCama = rs.getInt("idCategoriaCama");
                String tipo = rs.getString("tipo");
                String medidas = rs.getString("medidas");
                String fotoUrl = rs.getString("fotoUrl");
                String color = rs.getString("color");
                categoriaCama = new CategoriaCama(idCategoriaCama, tipo, medidas, fotoUrl, color);
            }
        } catch (SQLException e) {
            return null;
        } 
        return categoriaCama;
    }
    
    public String modificarCategoriaCama(Connection conexion, CategoriaCama entidad){
        
        String sql = "UPDATE categoriaCama SET TIPO ${entidad.getTipo()} MEDIDAS ${entidad.getMedidas()} FOTOURL ${entidad.getFotoUrl()} COLOR ${entidad.getColor()} WHERE IDCATEGORIACAMA = ${entidad.getIdCategoriaCama()}  ";
        try {
            pst = conexion.prepareStatement(sql) ;
            Boolean validacion = pst.execute();
            if(validacion == true){
                return "Se modifico la categoria cama con exito";
            }
            pst.close();
        }catch(SQLException e){
             return "No se pudo modificar la categoria cama con exito";
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
