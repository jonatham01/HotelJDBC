
package hoteljdbc.servicio;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import hoteljdbc.entidad.Factura;
import hoteljdbc.dto.FacturaDTO;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FacturaServicio {
    PreparedStatement statement;
    
    private Factura retornarFactura(ResultSet resultado){
        try {
            if(resultado.next()){
                return new Factura(
                    resultado.getLong("id_factura"),
                    resultado.getDouble("total"),
                    resultado.getDouble("subtotal"),
                    resultado.getDouble("iva"),
                    resultado.getDouble("inc"),
                    resultado.getDouble("descuento"),
                    resultado.getString("categoria"),
                    resultado.getDate("fecha_hora")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Factura mostrarFactura(Connection conexion, long id){
        String sql = "SELECT * FROM FACTURA WHERE ID_FACTURA = ?" ;
        try {
            statement = conexion.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultado = statement.executeQuery();
            return retornarFactura(resultado);
        } catch (SQLException ex) {
            Logger.getLogger(FacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    

    public Factura crearFactura(Connection conexion, FacturaDTO dto){
        String sql = "INSERT INTO FACTURA(total,subtotal,iva,inc,descuento,categoria,fecha_hora)"
                + "VALUES(CAMA.SQL.NEXTVAL,?,?,?,?)";
        try {
            statement = conexion.prepareStatement(sql);
            if(dto != null){
                statement.setDouble(1, dto.getTotal());
                statement.setDouble(2, dto.getSubtotal());
                statement.setDouble(3, dto.getIva());
                statement.setDouble(4, dto.getInc());
                statement.setDouble(5, dto.getDescuento());
                statement.setString(6, dto.getCategoria());
                statement.setDate(7, (Date) dto.getFechaHora());
            }
            int filas = statement.executeUpdate();
            if ( filas > 0 ){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    if (resultado.next()){
                        long id = resultado.getLong(1);
                        statement.close();
                        return mostrarFactura(conexion,id);
                    }                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;        
    }
    
    public Factura modificaFactura(Connection conexion, FacturaDTO dto, int idFactura){
        String sql = "UPDATE FACTURA "
                + "SET TOTAL = ? "
                + ", SUBTOTAL = ? "
                + ", IVA = ? "
                + ", INC = ? "
                + ", DESCUENTO = ? "
                + ", CATEGORIA = ? "
                + ", FECHA_HORA = ? "
                + "WHERE ID_FACTURA = ?";
        try {
            statement = conexion.prepareStatement(sql);
            if(dto != null){
                statement.setDouble(1, dto.getTotal());
                statement.setDouble(2, dto.getSubtotal());
                statement.setDouble(3, dto.getIva());
                statement.setDouble(4, dto.getInc());
                statement.setDouble(5, dto.getDescuento());
                statement.setString(6, dto.getCategoria());
                statement.setDate(7, (Date) dto.getFechaHora());
                statement.setInt(8 , idFactura);
            }
            int filas = statement.executeUpdate();
            if ( filas > 0 ){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    if (resultado.next()){
                        long id = resultado.getLong(1);
                        statement.close();
                        return mostrarFactura(conexion,id);
                    }                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;        
    }
    
    
    
    public boolean borrarFactura(Connection conexion, long id){
        String sql = "DELETE FROM FACTURA WHERE ID_FACTURA = " +id;
        try {
            statement = conexion.prepareStatement(sql);
            statement.setLong(1, id);
            statement.execute(sql);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
