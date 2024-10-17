
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
                    resultado.getLong("idFactura"),
                    resultado.getDouble("total"),
                    resultado.getDouble("subtotal"),
                    resultado.getDouble("iva"),
                    resultado.getDouble("inc"),
                    resultado.getDouble("descuento"),
                    resultado.getString("categoria"),
                    resultado.getDate("fechaHora")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private Factura ejecutar(Connection conexion, String sql,FacturaDTO dto){
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
            ResultSet resultado = statement.executeQuery();
            Factura factura = retornarFactura(resultado);
            statement.close();
            return factura;
        } catch (SQLException ex) {
            Logger.getLogger(FacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;        
    }
    
    public Factura crearFactura(Connection conexion, FacturaDTO dto){
        String sql = "INSERT INTO FACTURA(total,subtotal,iva,inc,descuento,categoria,fechaHora)"
                + "VALUES(CAMA.SQL.NEXTVAL,?,?,?,?)";
        return ejecutar(conexion,sql,dto);
    }
    
    public Factura modificaFactura(Connection conexion, FacturaDTO dto, int id){
        String sql = "UPDATE FACTURA "
                + "SET TOTAL = ? "
                + ", SUBTOTAL = ? "
                + ", IVA = ? "
                + ", INC = ? "
                + ", DESCUENTO = ? "
                + ", CATEGORIA = ? "
                + ", FECHAHORA = ? "
                + "WHERE IDFACTURA = " + id;
        return ejecutar(conexion,sql,dto);
    }
    
    public Factura mostrarFactura(Connection conexion, int id){
        String sql = "SELECT * FROM FACTURA WHERE IDFACTURA = " +id;
        return ejecutar(conexion,sql,null);
    }
    
    public boolean borrarFactura(Connection conexion, int id){
        String sql = "DELETE FROM FACTURA WHERE IDFACTURA = " +id;
        try {
            statement = conexion.prepareStatement(sql);
            boolean validacion = statement.execute();
            statement.close();
            return validacion;
        } catch (SQLException ex) {
            Logger.getLogger(FacturaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
