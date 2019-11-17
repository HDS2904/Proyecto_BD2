/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.Section;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import principal.Conectar;

/**
 *
 * @author Angel
 */
public class SectionDAO implements ICRUD<Section>{
    
    Connection cn;
    ResultSet rs;
    PreparedStatement ps;
    CallableStatement ca;
    String mensaje = " ";
    Section sec;

    @Override
    public String Create(Section t) {

        cn = Conectar.getConnection();
        String sql = "{call PACK_MANT_SECTION.INSERT_SE(?,?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,t.getSection_group());
            ps.setInt(2,t.getId_subject());
            ps.setInt(3,t.getId_person());
            ps.setInt(4,t.getId_exam());
                       
            ps.execute();
            mensaje="DATOS GUARDADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            mensaje = "NO SE PUDO GUARDAR LOS DATOS \n" + ex.getMessage();
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SectionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
        return mensaje;
    }

    @Override
    public String Update(Section t) {
        
        cn = Conectar.getConnection();
        String sql = "{call PACK_MANT_SECTION.UPDATE_SE(?,?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_section());
            ps.setString(2,t.getSection_group());
            ps.setInt(3,t.getId_subject());
            ps.setInt(4,t.getId_person());
            ps.setInt(5,t.getId_exam());
                       
            ps.execute();
            mensaje="DATOS ACTUALIZADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            mensaje = "NO SE PUDO ACTUALIZAR LOS DATOS \n" + ex.getMessage();
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SectionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
        return mensaje;
    }

    @Override
    public String Delete(Section t) {
        cn = Conectar.getConnection();
        String sql = "{call PACK_MANT_SECTION.DELETE_SE(?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_section());
                       
            ps.execute();
            mensaje="DATOS ELIMINADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            mensaje = "NO SE PUDO ELIMINAR LOS DATOS \n" + ex.getMessage();
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SectionDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
        return mensaje;
    }

    @Override
    public Section SearchId(int t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
   
    }

    @Override
    public ArrayList<Section> ListAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
