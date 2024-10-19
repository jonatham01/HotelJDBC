package hoteljdbc.servicio;

import hoteljdbc.entidad.RecepcionHuesped;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecepcionHuespedServicio {
    private PreparedStatement statement;
    
    public RecepcionHuesped crearReservaHuesped(Connection conexion, RecepcionHuesped recepcionHuesped){
        String sql = "INSERT INTO RECEPCIONHUESPED(IDRECEPCION,IDHUESPED,FECHARECEPCION)VALUES(?,?,?)";
        try {
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,recepcionHuesped.getIdRecepcion());
            statement.setInt(2, recepcionHuesped.getIdHuesped());
            statement.setDate(3, (Date) recepcionHuesped.getFechaRecepcion());
            
            boolean validacion = statement.execute();
            statement.close();
            if(validacion){
                return recepcionHuesped;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaHuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    public RecepcionHuesped modificarReservaHuesped(Connection conexion, RecepcionHuesped recepcionHuesped, long idRecepcion,int idHuesped){
        String sql = "UDTATE RECEPCIONHUESPED SET IDRECEPCION = ?, IDHUESPED = ?, FECHARECEPCION = ? "
                + "WHERE IDRECEPCION = " +idRecepcion + " AND IDHUESPED = " +idHuesped;
        try {
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,recepcionHuesped.getIdRecepcion());
            statement.setInt(2, recepcionHuesped.getIdHuesped());
            statement.setDate(3, (Date) recepcionHuesped.getFechaRecepcion());
            
            boolean validacion = statement.execute();
            statement.close();
            if(validacion){
                return recepcionHuesped;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaHuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public RecepcionHuesped mostrarReservaHuesped(Connection conexion, long idRecepcion,int idHuesped){
        String sql = "SELECT * FROM RECEPCIONHUESPED"
                + "WHERE IDRECEPCION = " +idRecepcion + " AND IDHUESPED = " +idHuesped;
        
        try {
            statement = conexion.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();
            RecepcionHuesped recepcionHuesped = null;
            if(resultado.next()){
                recepcionHuesped = new RecepcionHuesped(
                        resultado.getInt("idRecepcion"),
                        resultado.getInt("idHuesped"),
                        resultado.getDate("fechaRecepcion")
                );
            }
            statement.close();
            return recepcionHuesped;
        } catch (SQLException ex) {
            Logger.getLogger(ReservaHuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
                    
    }
    
    public boolean borrarReservaHuesped(Connection conexion,long idRecepcion,int idHuesped){
        String sql = "DELETE FROM RECEPCIONHUESPED"
                + "WHERE IDRECEPCION = " +idRecepcion + " AND IDHUESPED = " +idHuesped;
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
