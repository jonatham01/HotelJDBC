package hoteljdbc.servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import hoteljdbc.dto.CamaDTO;
import hoteljdbc.entidad.Cama;
import java.sql.Date;


public class CamaServicio {
    PreparedStatement statement;
    
    public Cama crearCama(Connection conexion, CamaDTO cama) {
        String sql = "INSERT INTO CAMA(recibo_url,fecha_cama,id_categoria_cama) "
                + "VALUES(CAMA.SQL.NEXTVAL,?,?,?,?)";
        try{
            statement = conexion.prepareStatement(sql);
            statement.setString(1, cama.getReciboUrl());
            statement.setDate(2, (Date) cama.getFechaCama());
            statement.setInt(3, cama.getIdCategoriaCama());
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                int id = resultado.getInt("id_cama");
                String reciboUrl = resultado.getString("recibo_url");
                Date fecha = resultado.getDate("fecha_cama");
                int categoriaCama = resultado.getInt("id_categoria_cama");
                Cama camaCreada = new Cama(id,reciboUrl,fecha,categoriaCama);
                return camaCreada;
            }
            
            statement.close();
            
        }catch(SQLException e){
            return null;
        }
        return null;
    }
    
    public Cama mostrarCama(int id, Connection conexion)  throws SQLException{
        String sql = "SELECT * FROM CAMA WHERE ID_CAMA = " + id;
        try{
            statement = conexion.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery(sql);
            if( resultado.next()){
                int idCama = resultado.getInt("id_cama");
                String reciboUrl = resultado.getString("recibo_url");
                Date fecha = resultado.getDate("fecha_cama");
                int categoriaCama = resultado.getInt("id_categoria_cama");
                Cama cama = new Cama(idCama,reciboUrl,fecha,categoriaCama);
                return cama;
            }
            statement.close();
                    
        }catch(SQLException e){
            throw e;
        }
        return null;
    }
    
    public Cama modificarCama(Connection conexion, CamaDTO camaDTO, int id) throws SQLException{
        String sql = "UPDATE CAMA "
                        + "SET RECIBO_URL = ${camaDTO.getReciboUrl()} "
                        + ",FECHA_CAMA = ${camaDTO.getFechaCama()} "
                        + ",ID_CATEGORIA_CAMA = ${camaDTO.getIdCategoriaCama()} "
                        + "WHERE ID_CAMA = ${id}  ";
                
        try {
            ResultSet resultado = statement.executeQuery(sql);
            if (resultado.next()){
                int idCama = resultado.getInt("id_cama");
                String reciboUrl = resultado.getString("recibo_url");
                Date fecha = resultado.getDate("fecha_cama");
                int categoriaCama = resultado.getInt("id_categoria_cama");
                Cama camaModificada = new Cama(idCama,reciboUrl,fecha,categoriaCama);
                return camaModificada;
            }
            statement.close();
        }catch (SQLException e){
            throw e;
        }
        return null;
    }
    
    public boolean borrarCama(int id) throws SQLException{
        String sql = "DELETE FROM CAMA "
                + "WHERE ID_CAMA = " + id;
        try{
            return statement.execute(sql);
        }catch(SQLException e){
            throw e;
        }
    }
    
}
