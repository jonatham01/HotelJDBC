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
        String sql = "INSERT INTO CAMA(reciboUrl,fechaCama,idCategoriaCama,descripcionCama) "
                + "VALUES(CAMA.SQL.NEXTVAL,?,?,?,?)";
        try{
            statement = conexion.prepareStatement(sql);
            statement.setString(1, cama.getReciboUrl());
            statement.setDate(2, (Date) cama.getFechaCama());
            statement.setInt(3, cama.getIdCategoriaCama());
            statement.setString(4, cama.getDescripcionCama());
            ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
                int id = resultado.getInt("idCama");
                String reciboUrl = resultado.getString("reciboUrl");
                Date fecha = resultado.getDate("FechaCama");
                int categoriaCama = resultado.getInt("idCategoriaCama");
                String descripcion = resultado.getString("descripcionCama");
                Cama camaCreada = new Cama(id,reciboUrl,fecha,categoriaCama,descripcion);
                return camaCreada;
            }
            
            statement.close();
            
        }catch(SQLException e){
            return null;
        }
        return null;
    }
    
    public Cama mostrarCama(int id, Connection conexion)  throws SQLException{
        String sql = "SELECT * FROM CAMA WHERE IDCAMA = " + id;
        try{
            statement = conexion.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery(sql);
            if( resultado.next()){
                int idCama = resultado.getInt("idCama");
                String reciboUrl = resultado.getString("reciboUrl");
                Date fecha = resultado.getDate("FechaCama");
                int categoriaCama = resultado.getInt("idCategoriaCama");
                String descripcion = resultado.getString("descripcionCama");
                Cama cama = new Cama(idCama,reciboUrl,fecha,categoriaCama,descripcion);
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
                        + "SET RECIBOURL = ${camaDTO.getReciboUrl()} "
                        + ",FECHACAMA = ${camaDTO.getFechaCama()} "
                        + ",IDCATEGORIACAMA = ${camaDTO.getIdCategoriaCama()} "
                        + ",DESCRIPCIONCAMA = ${camaDTO.getDescripcionCama()} "
                        + "WHERE IDCAMA = ${id}  ";
                
        try {
            ResultSet resultado = statement.executeQuery(sql);
            if (resultado.next()){
                int idCama = resultado.getInt("idCama");
                String reciboUrl = resultado.getString("reciboUrl");
                Date fecha = resultado.getDate("FechaCama");
                int categoriaCama = resultado.getInt("idCategoriaCama");
                String descripcion = resultado.getString("descripcionCama");
                Cama camaModificada = new Cama(idCama,reciboUrl,fecha,categoriaCama,descripcion);
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
                + "WHERE IDCAMA = " + id;
        try{
            return statement.execute(sql);
        }catch(SQLException e){
            throw e;
        }
    }
    
}
