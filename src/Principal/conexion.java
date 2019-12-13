
package Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    static Connection con = null;
    static String user = "bd2";
    static String password = "bd2";
    static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    static String driver = "oracle.jdbc.driver.OracleDriver";
    
    public static Connection getConnection() throws SQLException{
        System.out.println(url+" "+ password);
        try {
            Class.forName(driver).newInstance();
            
            con = DriverManager.getConnection(url, user, password);
            con.setAutoCommit(false);
            if(con != null){
                System.out.println("CONEXIÓN EXITOSA");
            }
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException ex) {
            throw new SQLException("No se encontró el driver de la base de dato.");
        } catch (InstantiationException | IllegalAccessException e) {
            throw new SQLException("No se puede acceder a la base de datos.");
        }
        return con;
    }
    
    public void desconectar() throws SQLException{
        try {
            con.close();
        } catch (SQLException e) {
            throw e;
        }
    }
    
}
