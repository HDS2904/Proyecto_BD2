
package principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conectar {
    private static Connection con = null;
    private static String login = "Jonathan_HDS";
    private static String clave = "HDS2904";
    private static String url = "jdbc:oracle:thin:@localhost:1521:orcl";
    
    public static Connection getConnection(){
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url,login,clave);
            con.setAutoCommit(false);
            if(con != null){
                System.out.println("CONEXIÓN EXITOSA");
            }
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,"Conexión Erronea"+e.getMessage());
        }
        return con;
    }
    
    public void desconectar(){
        try {
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al intentar desconectarse");
        }
    }
    
}
