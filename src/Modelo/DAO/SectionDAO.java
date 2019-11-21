/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.Section;
import Principal.conexion;
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
    public void Create(Section t) throws Exception{

        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_SECTION.INSERT_SE(?,?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            
            ps.setInt(1,t.getId_subject());
            ps.setInt(2,t.getId_person());
            ps.setInt(3,t.getId_exam());
            ps.setString(4,t.getSection_group());
                       
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
    }

    @Override
    public void Update(Section t) throws Exception{
        
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_SECTION.UPDATE_SE(?,?,?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_section());
            ps.setInt(2,t.getId_subject());
            ps.setInt(3,t.getId_person());
            ps.setInt(4,t.getId_exam());
            ps.setString(5,t.getSection_group());
                       
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
        
    }

    @Override
    public void Delete(Section t) throws Exception {
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_SECTION.DELETE_SE(?)}";
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
        
    }

    @Override
    public Section Search(int t) throws Exception {
        sec = new Section();
        cn = conexion.getConnection();
        String sql = "{? = call PACK_MANAGE_SECTION.SEARCH_SE(?)}";
        
        
         try {
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.setInt(2, t);
            ca.execute();
            rs = (ResultSet)ca.getObject(2);
            if(rs.next()){
                
                sec.setId_section(rs.getInt("ID_SECTION"));
                sec.setId_subject(rs.getInt("ID_SUBJECT"));
                sec.setId_person(rs.getInt("ID_PERSON"));
                sec.setId_exam(rs.getInt("ID_EXAM"));
                sec.setSection_group(rs.getString("SECTION_GROUP"));
                
                
            }
            
            ca.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }finally{
            try {
                cn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return sec;
        
    }

    @Override
    public ArrayList<Section> ListAll() throws Exception {
    
        ArrayList<Section> lista = new ArrayList<Section>();
         try {
             cn = conexion.getConnection();
            String sql = "{? = call PACK_MANAGE_SECTION.LIST_SE}";
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.execute();
            rs = (ResultSet)ca.getObject(1);
            while(rs.next()){
                
                sec = new Section();
                 sec.setId_section(rs.getInt("ID_SECTION"));
                sec.setId_subject(rs.getInt("ID_SUBJECT"));
                sec.setId_person(rs.getInt("ID_PERSON"));
                sec.setId_exam(rs.getInt("ID_EXAM"));
                sec.setSection_group(rs.getString("SECTION_GROUP"));
                lista.add(sec);
            }            
            ca.close();
            rs.close();
        } catch (SQLException e) {
            throw e;
        }finally{
            try {
                cn.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return lista;
    }

    
}
