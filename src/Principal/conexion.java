/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class conexion {
 
    public static Connection getConnection() throws SQLException {
        Connection cn = null;
        try {
            // Parámetros de Connexión
            String driver = "oracle.jdbc.OracleDriver";
            String url = "jdbc:oracle:thin:@localhost:1521:orcl";
            String user = "angel";
            String pwd = "angel";
            // Proceso
            Class.forName(driver).newInstance();
            cn = DriverManager.getConnection(url, user, pwd);
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException ex) {
            throw new SQLException("No se encontró el driver de la base de dato.");
        } catch (InstantiationException | IllegalAccessException e) {
            throw new SQLException("No se puede acceder a la base de datos.");
        }
        return cn;
    }

}
