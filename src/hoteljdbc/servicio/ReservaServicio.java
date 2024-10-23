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
    
    public Reserva mostrarReserva(Connection conexion, long id){
        try {
            String sql = "SELECT * FROM RESERVA WHERE ID_RESERVA = ?";
            statement = conexion.prepareStatement(sql);
            statement.setLong(1, id);
            ResultSet resultado = statement.executeQuery();
            statement.close();
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
    

    
    public Reserva guardarReserva(Connection conexion,ReservaDTO dto){
        String sql = "INSERT INTO RESERVA(FECHA_RESERVA,ESTADO,FECHA_INICIO,FECHA_FIN,ID_CLIENTE,ID_FACTURA) "
                + "VALUES(CAMA.SQL.NEXTVAL,?,?,?,?,?,?)";
        try {
            statement = conexion.prepareStatement(sql);
            statement.setDate(1, (Date) dto.getFechaReserva());
            statement.setString(2, dto.getEstado());
            statement.setDate(3, Date.valueOf(dto.getFechaInicio()));
            statement.setDate(4, Date.valueOf(dto.getFechaFin()));
            statement.setInt(5, dto.getIdCliente());
            statement.setLong(6, dto.getIdFactura());
            int filas = statement.executeUpdate();
            if(filas > 0){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    Reserva reserva = mostrarReserva(conexion, resultado.getLong(1));
                    statement.close();
                    return reserva;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Reserva modificarReserva(Connection conexion,ReservaDTO dto, long id){
        String sql = "UPDATE RESERVA SET FECHA_RESERVA = ? , ESTADO = ? , FECHA_INICIO = ?"
                + " ,FECHA_FIN = ? , ID_CLIENTE = ? , ID_FACTURA = ? "
                + "WHERE ID_RESERVA = ?" ;
        try {
            statement = conexion.prepareStatement(sql);
            statement.setDate(1, (Date) dto.getFechaReserva());
            statement.setString(2, dto.getEstado());
            statement.setDate(3, Date.valueOf(dto.getFechaInicio()));
            statement.setDate(4, Date.valueOf(dto.getFechaFin()));
            statement.setInt(5, dto.getIdCliente());
            statement.setLong(6, dto.getIdFactura());
            statement.setLong(7, id);
            int filas = statement.executeUpdate();
            if(filas > 0){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    Reserva reserva = mostrarReserva(conexion, resultado.getLong(1));
                    statement.close();
                    return reserva;
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReservaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    
    public boolean eliminarReserva(Connection conexion, long id){
        String sql = "DELETE FROM RESERVA WHERE ID_RESERVA = ? " ;
        try {
            statement = conexion.prepareStatement(sql);
            statement.setLong(1, id);
            statement.execute(sql);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ReservaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
}
