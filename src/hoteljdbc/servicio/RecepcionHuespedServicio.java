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
    
    public RecepcionHuesped crearRecepcionHuesped(Connection conexion, RecepcionHuesped recepcionHuesped){
        String sql = "INSERT INTO RECEPCION_HUESPED(IDRECEPCION,IDHUESPED,FECHARECEPCION)VALUES(?,?,?)";
        try {
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,recepcionHuesped.getIdRecepcion());
            statement.setInt(2, recepcionHuesped.getIdHuesped());
            statement.setDate(3, (Date) recepcionHuesped.getFechaRecepcion());
            int filas = statement.executeUpdate();
            if ( filas > 0 ){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    if (resultado.next()){
                        int idRecepcion = resultado.getInt(1);
                        int idHuesped = resultado.getInt(1);
                        statement.close();
                        return mostrarRecepcionHuesped(conexion,idRecepcion, idHuesped);
                    }                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaHuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    public RecepcionHuesped modificarRecepcionHuesped(Connection conexion, RecepcionHuesped recepcionHuesped, int idRecepcion,int idHuesped){
        String sql = "UDTATE RECEPCION_HUESPED SET IDRECEPCION = ?, IDHUESPED = ?, FECHARECEPCION = ? "
                + "WHERE IDRECEPCION = ? AND IDHUESPED = ?" ;
        try {
            statement = conexion.prepareStatement(sql);
            statement.setInt(1,recepcionHuesped.getIdRecepcion());
            statement.setInt(2, recepcionHuesped.getIdHuesped());
            statement.setDate(3, (Date) recepcionHuesped.getFechaRecepcion());
            statement.setInt(4, idRecepcion);
            statement.setInt(5, idHuesped);
            int filas = statement.executeUpdate();
            if ( filas > 0 ){
                try(ResultSet resultado = statement.getGeneratedKeys()){
                    if (resultado.next()){
                        statement.close();
                        return mostrarRecepcionHuesped(conexion,resultado.getInt(1), resultado.getInt(2));
                    }                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaHuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public RecepcionHuesped mostrarRecepcionHuesped(Connection conexion, int idRecepcion,int idHuesped){
        String sql = "SELECT * FROM RECEPCION_HUESPED WHERE IDRECEPCION = ? AND IDHUESPED =  ?" ;
        
        try {
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, idRecepcion);
            statement.setInt(2, idHuesped);            
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
    
    public boolean borrarRecepcionHuesped(Connection conexion,int idRecepcion,int idHuesped){
        String sql = "DELETE FROM RECEPCIONHUESPED WHERE IDRECEPCION = ?  AND IDHUESPED = ?" ;
        try {
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, idRecepcion);
            statement.setInt(2, idHuesped);
            statement.execute(sql);
            statement.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ReservaHuespedServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
}
