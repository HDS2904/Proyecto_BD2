/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.Theme;
import Principal.conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import oracle.jdbc.OracleTypes;
import principal.Conectar;

/**
 *
 * @author Angel
 */
public class ThemeDAO implements ICRUD<Theme> {

    Connection cn;
    ResultSet rs;
    PreparedStatement ps;
    CallableStatement ca;
    String mensaje = " ";
    Theme th;
    
    @Override
    public void Create(Theme t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_THEME.INSERT_TH(?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,t.getName_theme());
                       
            ps.execute();
            mensaje="DATOS GUARDADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            throw ex;
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    public void Update(Theme t) throws Exception {
    
        
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_THEME.UPDATE_TH(?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_subject());
            ps.setString(2,t.getName_theme());
                       
            ps.execute();
            mensaje="DATOS ACTUALIZADOS EXITOSAMENTE";
            ps.close();
            
            
            
        } catch (SQLException ex) {
            throw ex;
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public void Delete(Theme t) throws Exception {
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_THEME.DELETE_TH(?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_theme());
                       
            ps.execute();
            mensaje="DATOS ELIMINADOS EXITOSAMENTE";
            ps.close();
        } catch (SQLException ex) {
            throw ex;
        }finally{
            
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

    @Override
    public Theme Search(int t) throws Exception {
        th = new Theme();
        cn = conexion.getConnection();
        String sql = "{? = call PACK_MANAGE_THEME.SEARCH_TH(?)}";
        
        
         try {
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.setInt(2, t);
            ca.execute();
            rs = (ResultSet)ca.getObject(2);
            if(rs.next()){
                
                th.setName_theme(rs.getString("NAME_THEME"));
                th.setId_theme(rs.getInt("ID_THEME"));
                th.setId_subject(rs.getInt("ID_SUBJECT"));
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
        return th;
        
    }

    @Override
    public ArrayList<Theme> ListAll() throws Exception {
    
        ArrayList<Theme> lista = new ArrayList<Theme>();
         try {
             cn = conexion.getConnection();
            String sql = "{? = call PACK_MANAGE_THEME.LIST_TH}";
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.execute();
            rs = (ResultSet)ca.getObject(1);
            while(rs.next()){
                
                th = new Theme();
                th.setName_theme(rs.getString("NAME_THEME"));
                th.setId_theme(rs.getInt("ID_THEME"));
                th.setId_subject(rs.getInt("ID_SUBJECT"));
                lista.add(th);
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
