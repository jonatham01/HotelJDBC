package hoteljdbc.servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import hoteljdbc.entidad.Reserva;
import hoteljdbc.dto.ReservaDTO;

public class ReservaServicio {
    PreparedStatement statement;
    
    private Reserva retornarReserva(ResultSet resultado){
        try {
            if(resultado.next()){
                return new Reserva(
                        resultado.getLong("id_reserva"),
                        resultado.getDate("fecha_reserva"),
                        resultado.getString("estado"),
                        resultado.getDate("fecha_inicio").toLocalDate(),
                        resultado.getDate("fecha_fin").toLocalDate(),
                        resultado.getInt("id_cliente"),
                        resultado.getLong("id_factura")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private Reserva ejecutar(Connection conexion, String sql, ReservaDTO dto){
        try {
            statement = conexion.prepareStatement(sql);
            if(dto != null){
                statement.setDate(1, (Date) dto.getFechaReserva());
                statement.setString(2,dto.getEstado());
                statement.setDate(3,Date.valueOf(dto.getFechaInicio()) );
                statement.setDate(4, Date.valueOf(dto.getFechaFin()));
                statement.setInt(5, dto.getIdCliente());
                statement.setLong(6, dto.getIdFactura());
            }
            ResultSet resultado = statement.executeQuery();
            Reserva reserva = retornarReserva(resultado);
            statement.close();
            return reserva;
        } catch (SQLException ex) {
            Logger.getLogger(ReservaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public Reserva guardarReserva(Connection conexion,ReservaDTO dto){
        String sql = "INSERT INTO RESERVA(FECHA_RESERVA,ESTADO,FECHA_INICIO,FECHA_FIN,ID_CLIENTE,ID_FACTURA) "
                + "VALUES(CAMA.SQL.NEXTVAL,?,?,?,?,?,?)";
        return ejecutar(conexion,sql,dto);
    }
    
    public Reserva modificarReserva(Connection conexion,ReservaDTO dto, int id){
        String sql = "UPDATE RESERVA SET FECHA_RESERVA = ? , ESTADO = ? , FECHA_INICIO = ?"
                + " ,FECHA_FIN = ? , ID_CLIENTE = ? , ID_FACTURA = ? "
                + "WHERE ID_RESERVA = " + id;
        return ejecutar(conexion,sql,dto);
    }
    
    public Reserva mostrarReserva(Connection conexion, int id){
        String sql = "SELECT * FROM RESERVA WHERE ID_RESERVA = " + id;
        return ejecutar(conexion,sql,null);
    }
    
    public boolean eliminarReserva(Connection conexion, int id){
        String sql = "DELETE FROM RESERVA WHERE ID_RESERVA = " +id;
        try {
            statement = conexion.prepareStatement(sql);
            boolean validacion = statement.execute();
            statement.close();
            return validacion;
        } catch (SQLException ex) {
            Logger.getLogger(ReservaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
