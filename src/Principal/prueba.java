/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author HDS
 */
public class prueba {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    
    public void pos() throws SQLException{
        con = conexion.getConnection();
        
        try {
            ps = con.prepareStatement("SELECT ID_PERSON FROM TEACHER WHERE CODE_TEACHER = ?");
            ps.setString(1, "17200100");
            rs = ps.executeQuery();
            if(rs.next()){
                System.out.println("musss: "+rs.getString("ID_PERSON"));
            }

        } catch (SQLException e) {
            System.out.println("error al buscar"+e.getMessage());
        }
    }
    
    
}
