
package hoteljdbc.servicio;

import java.sql.Connection;
import hoteljdbc.entidad.CategoriaCama;
import java.sql.*;

public class CategoriaCamaServicio {
    PreparedStatement pst;
    
    public CategoriaCama crearCategoriaCama(Connection conexion, CategoriaCama entidad){
        
        String sql = "INSERT INTO categoria_cama (tipo,medidas,foto_url,color) "
                + "Values(?,?,?,?)";
        try {
            pst = conexion.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS) ;
            pst.setString(1,entidad.getTipo());
            pst.setString(2,entidad.getMedidas());
            pst.setString(3, entidad.getFotoUrl());
            pst.setString(4, entidad.getColor());
            int filas = pst.executeUpdate();

            
            if (filas > 0) {
                try (ResultSet resultado = pst.getGeneratedKeys()) {
                    if (resultado.next()) {
                        // Obtiene el ID generado
                        int id = resultado.getInt(1);
                        pst.close();             
                        return mostrarCategoriaCama(conexion, (int) id);
                    } 
                }
            }
    
         
        }catch(SQLException e){
             System.out.println("No se pudo crear la categoria cama con exito" + e.getMessage());
            
        }
        
        return null;
    }
    
    public CategoriaCama mostrarCategoriaCama(Connection conn, int id) {
        
        CategoriaCama categoriaCama = null;
        
        String sql = "SELECT * FROM categoria_cama WHERE id_categoria_cama =" +id; 

        try {
            PreparedStatement ps = conn.prepareStatement(sql); 
            ResultSet rs = ps.executeQuery(); 
            if (rs.next()) {
                int idCategoriaCama = rs.getInt("id_categoria_cama");
                String tipo = rs.getString("tipo");
                String medidas = rs.getString("medidas");
                String fotoUrl = rs.getString("foto_url");
                String color = rs.getString("color");
                categoriaCama = new CategoriaCama(idCategoriaCama, tipo, medidas, fotoUrl, color);
            }
        } catch (SQLException e) {
            return null;
        } 
        return categoriaCama;
    }
    
    public CategoriaCama modificarCategoriaCama(Connection conexion, CategoriaCama entidad,int id){
        
        
        String sql = "UPDATE categoria_cama SET tipo = ? ,medidas = ? , foto_url = ? , color = ? "
                + "WHERE ID_CATEGORIA_CAMA = ?" ;
        try {
            pst = conexion.prepareStatement(sql) ;
            pst.setString(1,entidad.getTipo());
            pst.setString(2,entidad.getMedidas());
            pst.setString(3, entidad.getFotoUrl());
            pst.setString(4, entidad.getColor());
            pst.setInt(5, id);
            int filas = pst.executeUpdate();
            if (filas > 0){
                return mostrarCategoriaCama(conexion,id);
            }
        }catch(SQLException e){
             System.out.println("No se pudo crear la categoria cama con exito" + e.getMessage());
        }
        
        return null;
    }
    
    public String eliminarCategoriaCama(Connection conexion, int id){
        String sql = "DELETE FROM categoria_cama  WHERE ID_CATEGORIA_CAMA = ?  ";
        try {
            pst = conexion.prepareStatement(sql) ;
            pst.setInt(1, id);
            pst.execute();
            pst.close();
            return "Se borro la categoria cama con exito";
           
        }catch(SQLException e){
             System.out.println("No se pudo borrar la categoria cama con exito");
            System.out.println(e.getMessage());
        }
        
        return null;
    }
    
   
}
