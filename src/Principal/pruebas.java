/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try {
            Connection cn = conexion.getConnection();
            System.out.println("Conexion conforme....");
            cn.close();
        } catch (SQLException e) {
            System.out.println("error :" + e.getMessage());
        }
        
    }
    
}
