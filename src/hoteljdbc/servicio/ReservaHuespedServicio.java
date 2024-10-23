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
    
    public ReservaHuesped mostrarReservaHuesped(Connection conexion, long idReserva,int idHuesped){
        String sql = "SELECT * FROM RESERVA_HUESPED WHERE ID_RESERVA = ? AND ID_HUESPED = ?" ;
        
        try {
            statement = conexion.prepareStatement(sql);
            statement.setLong(1, idReserva);
            statement.setInt(2,idHuesped);
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
    
    public ReservaHuesped crearReservaHuesped(Connection conexion, ReservaHuesped reservaHuesped){
        String sql = "INSERT INTO RESERVA_HUESPED(ID_RESERVA,ID_HUESPED,FECHA_RESERVA)VALUES(?,?,?)";
        try {
            statement = conexion.prepareStatement(sql);
            statement.setLong(1, reservaHuesped.getIdReserva());
            statement.setInt(2, reservaHuesped.getIdHuesped());
            statement.setDate(3, (Date) reservaHuesped.getFechaReserva());
            int filas = statement.executeUpdate();
            if ( filas > 0 ){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    if (resultado.next()){
                        statement.close();
                        return mostrarReservaHuesped(conexion,resultado.getLong(1),resultado.getInt(2));
                    }                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaHuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    public ReservaHuesped modificarReservaHuesped(Connection conexion, ReservaHuesped reservaHuesped, long idReserva,int idHuesped){
        String sql = "UDTATE RESERVAHUESPED SET ID_RESERVA = ?, ID_HUESPED = ?, FECHA_RESERVA = ? "
                + "WHERE ID_RESERVA = ? AND ID_HUESPED = ?" ;
        try {
            statement = conexion.prepareStatement(sql);
            statement.setLong(1, reservaHuesped.getIdReserva());
            statement.setInt(2, reservaHuesped.getIdHuesped());
            statement.setDate(3, (Date) reservaHuesped.getFechaReserva());
            statement.setLong(4, idReserva);
            statement.setInt(5,idHuesped);
           int filas = statement.executeUpdate();
            if ( filas > 0 ){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    if (resultado.next()){
                        statement.close();
                        return mostrarReservaHuesped(conexion,resultado.getLong(1),resultado.getInt(2));
                    }                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaHuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    
    public boolean borrarReservaHuesped(Connection conexion, ReservaHuesped reservaHuesped, long idReserva,int idHuesped){
        String sql = "DELETE FROM RESERVA_HUESPED"
                + "WHERE ID_RESERVA = ? AND ID_HUESPED = ? " ;
        try {
            statement = conexion.prepareStatement(sql);
            statement.setLong(1, idReserva);
            statement.setInt(2,idHuesped);
            statement.execute(sql);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ReservaHuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    
}
