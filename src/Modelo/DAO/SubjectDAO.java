/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.DAO;

import Interfaz.ICRUD;
import Modelo.Entidades.Subject;
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
import Principal.conexion;

/**
 *
 * @author Angel
 */
public class SubjectDAO implements ICRUD<Subject> {
    
    Connection cn;
    ResultSet rs;
    PreparedStatement ps;
    CallableStatement ca;
    String mensaje = " ";
    Subject su;
    
    @Override
    public void Create(Subject t) throws Exception{
        
        
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_SUBJECT.INSERT_SU(?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1,t.getName_subject());
                       
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
    public void Update(Subject t) throws Exception{
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_SUBJECT.UPDATE_SU(?,?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_subject());
            ps.setString(2,t.getName_subject());
                       
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
    public void Delete(Subject t) throws Exception{
    
        cn = conexion.getConnection();
        String sql = "{call PACK_MANAGE_SUBJECT.DELETE_SU(?)}";
        try {
            ps = cn.prepareStatement(sql);
            ps.setInt(1,t.getId_subject());
                       
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
    public Subject Search(int t) throws Exception{
    
        Subject su = new Subject();
        cn = conexion.getConnection();
        String sql = "{? = call PACK_MANAGE_SUBJECT.SEARCH_SU(?)}";
        
        
         try {
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.setInt(2, t);
            ca.execute();
            rs = (ResultSet)ca.getObject(2);
            if(rs.next()){
                System.out.println(rs.getString("NAME_SUBJECT"));
                su.setName_subject(rs.getString("NAME_SUBJECT"));
                su.setId_subject(rs.getInt("ID_SUBJECT"));
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
        return su;
    }

    @Override
    public ArrayList<Subject> ListAll() throws Exception{
        ArrayList<Subject> lista = new ArrayList<Subject>();
         try {
             cn = conexion.getConnection();
            String sql = "{? = call PACK_MANAGE_SUBJECT.LIST_SU}";
            ca = cn.prepareCall(sql);
            ca.registerOutParameter(1,OracleTypes.CURSOR);
            ca.execute();
            rs = (ResultSet)ca.getObject(1);
            while(rs.next()){
                
                su = new Subject();
                su.setName_subject(rs.getString("NAME_SUBJECT"));
                su.setId_subject(rs.getInt("ID_SUBJECT"));
                lista.add(su);
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
