
package hoteljdbc.servicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import hoteljdbc.entidad.Cliente;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteServicio {
   PreparedStatement statement;
   String sql;
    
   public Cliente crearCliente(Connection conexion, Cliente cliente){
       sql = "INSERT INTO CLIENTE(CEDULA,NOMBRE,TIPOIDENTIFICACION) VALUES(?,?,?)";
        try{
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, cliente.getCedula());
            statement.setString(2, cliente.getNombre());
            statement.setString(3, cliente.getTipoIdentificacion());
            boolean validacion = statement.execute(sql);
            statement.close();
            if(validacion){
                return cliente;
            }
        }catch(SQLException ex){
             Logger.getLogger(ClienteServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
       return null;
   }
   public Cliente mostrarCliente(Connection conexion, int cedula){
       sql = "SELECT * FROM CLIENTE WHERE CEDULA = " + cedula;
       try {
           statement = conexion.prepareStatement(sql);
           ResultSet resultado = statement.executeQuery();
            if(resultado.next()){
               Cliente cliente = new Cliente(
                       resultado.getInt("cedula"),
                       resultado.getString("nombre"),
                       resultado.getString("tipoIdentificacion")
               );
                statement.close();
                sql = null;
                return cliente;
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(ClienteServicio.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
   }
   
   public Cliente modificarCliente(Connection conexion, Cliente cliente, int cedula){
       sql = "UPDATE CLIENTE "
               + "SET CEDULA = ?, NOMBRE = ?, TIPOIDENTIFICACION = ? "
               + "WHERE CEDULA = ?";
        try {
           statement = conexion.prepareStatement(sql);
           statement.setInt(1, cliente.getCedula());
           statement.setString(2, cliente.getNombre());
           statement.setString(3, cliente.getTipoIdentificacion());
           statement.setInt(4, cedula);
           boolean validacion = statement.execute();
            if(validacion){
                statement.close();
                sql = null;
                return cliente;
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(ClienteServicio.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
   }
   
   public boolean borrarCliente(Connection conexion, int cedula){
       sql = "DELETE  FROM CLIENTE WHERE CEDULA = " + cedula;
       try {
           statement = conexion.prepareStatement(sql);
           boolean validacion = statement.execute();
            if(validacion){
                statement.close();
                sql = null;
                return true;
           }
       } catch (SQLException ex) {
           Logger.getLogger(ClienteServicio.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return false;
   }
    
}
