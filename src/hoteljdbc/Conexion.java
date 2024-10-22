
package hoteljdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    String db = "hotel"; // Nombre de la base de datos
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "28166";
    String driver = "com.mysql.cj.jdbc.Driver"; // MySQL Connector/J driver
    Connection cx;
    
   
    public Connection conectar() {
        try {
            Class.forName(driver);
            cx = DriverManager.getConnection(url + db, user, password);
            System.out.println("Se conectó a la base de datos");

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("No se conectó a la base de datos");
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return cx;
    }
     
    public void desconectar() {
        try {
            if (cx != null && !cx.isClosed()) {
                cx.close();
                System.out.println("Desconectado de la base de datos");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        conexion.conectar();
    }
}