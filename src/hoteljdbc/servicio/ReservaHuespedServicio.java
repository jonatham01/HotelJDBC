package hoteljdbc.servicio;

import hoteljdbc.entidad.ReservaHuesped;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ReservaHuespedServicio {
    PreparedStatement statement;
    
    public ReservaHuesped crearReservaHuesped(Connection conexion, ReservaHuesped reservaHuesped){
        String sql = "INSERT INTO RESERVAHUESPED(IDRESERVA,IDHUESPED,FECHARESERVA)VALUES(?,?,?)";
        try {
            statement = conexion.prepareStatement(sql);
            statement.setLong(1, reservaHuesped.getIdReserva());
            statement.setInt(2, reservaHuesped.getIdHuesped());
            statement.setDate(3, (Date) reservaHuesped.getFechaReserva());
            
            boolean validacion = statement.execute();
            statement.close();
            if(validacion){
                return reservaHuesped;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaHuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    public ReservaHuesped modificarReservaHuesped(Connection conexion, ReservaHuesped reservaHuesped, long idReserva,int idHuesped){
        String sql = "UDTATE RESERVAHUESPED SET IDRESERVA = ?, IDHUESPED = ?, FECHARESERVA = ? "
                + "WHERE IDRESERVA = " +idReserva + " AND IDHUESPED = " +idHuesped;
        try {
            statement = conexion.prepareStatement(sql);
            statement.setLong(1, reservaHuesped.getIdReserva());
            statement.setInt(2, reservaHuesped.getIdHuesped());
            statement.setDate(3, (Date) reservaHuesped.getFechaReserva());
            
            boolean validacion = statement.execute();
            statement.close();
            if(validacion){
                return reservaHuesped;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaHuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public ReservaHuesped mostrarReservaHuesped(Connection conexion, long idReserva,int idHuesped){
        String sql = "SELECT * FROM RESERVA HUESPED"
                + "WHERE IDRESERVA = " +idReserva + " AND IDHUESPED = " +idHuesped;
        
        try {
            statement = conexion.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            ReservaHuesped reservaHuesped = null;
            if(resultado.next()){
                reservaHuesped = new ReservaHuesped(
                        resultado.getLong("idReserva"),
                        resultado.getInt("idHuesped"),
                        resultado.getDate("fechaReserva")
                );
            }
            statement.close();
            return reservaHuesped;
        } catch (SQLException ex) {
            Logger.getLogger(ReservaHuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
                    
    }
    
    public boolean borrarReservaHuesped(Connection conexion, ReservaHuesped reservaHuesped, long idReserva,int idHuesped){
        String sql = "DELETE FROM RESERVAHUESPED"
                + "WHERE IDRESERVA = " +idReserva + " AND IDHUESPED = " +idHuesped;
        try {
            statement = conexion.prepareStatement(sql);    
            boolean validacion = statement.execute();
            return validacion;
        } catch (SQLException ex) {
            Logger.getLogger(ReservaHuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    
}
