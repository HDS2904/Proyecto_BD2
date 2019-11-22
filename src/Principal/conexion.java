
package Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    private static Connection con = null;
    private static String user = "Jonathan_HDS";
    private static String password = "HDS2904";
    private static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    private static String driver = "oracle.jdbc.driver.OracleDriver";
    
    public static Connection getConnection() throws SQLException{
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
